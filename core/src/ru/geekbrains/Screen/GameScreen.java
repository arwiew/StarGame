package ru.geekbrains.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Base.BaseScreen;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Sprite.Background;
import ru.geekbrains.Sprite.Ship;
import ru.geekbrains.Sprite.Star;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private TextureAtlas atlas1;
    private Star[] starArray;
    private Ship ship;

    @Override
    public void show() {
        super.show();
        bg = new Texture("fon.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("Textures/mainAtlas.tpack");
        atlas1 = new TextureAtlas("Textures/Ship.pack");
        starArray = new Star[STAR_COUNT];
        for (int i = 0; i<STAR_COUNT; i++){
            starArray[i] = new Star(atlas);
        }
        ship = new Ship(atlas1);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    private void update(float delta){
        for (Star star : starArray){
            star.update(delta);
        }
        ship.update(delta);

    }

    private void draw(){
        Gdx.gl.glClearColor(0.4f, 0.3f, 0.9f, 0.5f); //МОЖНО ЗАДАТЬ ФОН
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //очистка экрана, работеат в паре с ClaerColor
        batch.begin();
        background.draw(batch);
        for (Star star : starArray){
            star.draw(batch);
        }
        ship.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starArray){
            star.resize(worldBounds);
        }
        ship.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        ship.touchDown(touch,pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        ship.touchUp(touch, pointer);
        return false;
    }
}
