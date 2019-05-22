package ru.geekbrains.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Base.BaseScreen;

public class MenuScreen extends BaseScreen {

 private SpriteBatch batch;
 private Texture img;
 private Texture imgLogo;
 private Vector2 touch;
 private Vector2 v;
 private Vector2 position;
 private Vector2 a,s,d,w;

@Override
public void show(){
    super.show();
    batch = new SpriteBatch();
    img = new Texture("fon.jpg");
    imgLogo = new Texture("badlogic.jpg");
    touch = new Vector2();
    v = new Vector2(3,3); //установили вектор скорости
    position = new Vector2(0,0); // установили стартовую позицию
    a = new Vector2(position.x,position.y);
    s = new Vector2(position.x,position.y);
    d = new Vector2(position.x,position.y);
    w = new Vector2(position.x,position.y);
}

@Override
    public void render(float delta){
    super.render(delta);
    Gdx.gl.glClearColor(0.4f, 0.3f, 0.9f, 0.5f); //МОЖНО ЗАДАТЬ ФОН
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //очистка экрана, работеат в паре с ClaerColor
    batch.begin();
    //	batch.setColor(0.33f,0.33f,0.33f,0.4f);
    batch.draw(img, 0, 0,1100,1800);
    //	batch.setColor(0.33f,0.7f,0.8f,0.5f);
    batch.draw(imgLogo,touch.x,touch.y,50,50);
    batch.end();
}

@Override
    public void dispose(){
    batch.dispose();
    img.dispose();
    super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      super.touchDown(screenX,screenY,pointer,button);
      touch.set(screenX, Gdx.graphics.getHeight()-screenY);
        System.out.println("tochDown"+touch.x+" "+touch.y);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
      super.keyDown(keycode);
      System.out.println(position);
        return false;
    }
}
