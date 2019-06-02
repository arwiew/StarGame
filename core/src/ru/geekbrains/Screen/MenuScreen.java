package ru.geekbrains.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


import ru.geekbrains.Base.BaseScreen;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Sprite.Background;
import ru.geekbrains.Sprite.ButtonExit;
import ru.geekbrains.Sprite.ButtonPlay;
import ru.geekbrains.Sprite.Star;

public class MenuScreen extends BaseScreen {

 private static final int STAR_COUNT = 256;

 private Game game;

 private Texture bg;
 private Background background;
 private static final float LEN = 0.94f;
 private TextureAtlas atlas;
 private Star[] starArray;
 private ButtonExit buttonExit;
 private ButtonPlay buttonPlay;


    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
public void show(){
    super.show();
    bg = new Texture("fon.jpg");
    background = new Background(new TextureRegion(bg));
    atlas = new TextureAtlas("Textures/menuAtlas.tpack");
    starArray = new Star[STAR_COUNT];
    for (int i = 0; i<STAR_COUNT; i++){
        starArray[i] = new Star(atlas);
    }
    buttonExit = new ButtonExit(atlas);
    buttonPlay = new ButtonPlay(atlas, game);
}

    @Override
    public void render(float delta){
    super.render(delta);
    update(delta);
    Gdx.gl.glClearColor(0.4f, 0.3f, 0.9f, 0.5f); //МОЖНО ЗАДАТЬ ФОН
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //очистка экрана, работеат в паре с ClaerColor
    batch.begin();
    background.draw(batch);
        for (Star star : starArray){
            star.draw(batch);
        }
    buttonExit.draw(batch);
    buttonPlay.draw(batch);
    batch.end();
  }

    private void update(float delta){
    for (Star star : starArray){
        star.update(delta);
    }
    }

    @Override
    public void dispose(){
    bg.dispose();
    atlas.dispose();
    super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starArray){
            star.resize(worldBounds);
        }
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
    buttonExit.touchDown(touch, pointer);
    buttonPlay.touchDown(touch, pointer);
       return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
    buttonExit.touchUp(touch, pointer);
    buttonPlay.touchUp(touch, pointer);
        return false;
    }
}
