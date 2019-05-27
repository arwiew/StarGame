package ru.geekbrains.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


import ru.geekbrains.Base.BaseScreen;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Sprite.Background;
import ru.geekbrains.Sprite.IcoMove;

public class MenuScreen extends BaseScreen {


 private Texture bg;
 private Background background;
 private static final float LEN = 0.94f;
 private Texture im;
 private IcoMove icoMove;

@Override
public void show(){
    super.show();
    bg = new Texture("fon.jpg");
    im = new Texture("Ship.png");
    background = new Background(new TextureRegion(bg));
    icoMove = new IcoMove(new TextureRegion(im));
}


    @Override
    public void render(float delta){
    super.render(delta);
    update(delta);
    Gdx.gl.glClearColor(0.4f, 0.3f, 0.9f, 0.5f); //МОЖНО ЗАДАТЬ ФОН
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //очистка экрана, работеат в паре с ClaerColor
    batch.begin();
    background.draw(batch);
    icoMove.draw(batch);
    batch.end();
  }

    private void update(float delta){
        icoMove.update(delta);
    }

    @Override
    public void dispose(){
    bg.dispose();
    im.dispose();
    super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        icoMove.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
       icoMove.touchDown(touch,pointer);
       return false;
    }
}
