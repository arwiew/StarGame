package ru.geekbrains.Base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Math.MatrixUtils;
import ru.geekbrains.Math.Rect;

public abstract class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;
    private Vector2 touch;
    private Rect screenBounds;
    private Rect worldBounds;
    private Rect glBounds;

    private Matrix4 worldToGl;
    private Matrix3 screenToWorld;


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        System.out.println("show");
        batch = new SpriteBatch();
        this.batch = new SpriteBatch();
        this.touch = new Vector2();
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBounds = new Rect(0,0,1f,1f);
        this.worldToGl = new Matrix4();
        this.screenToWorld = new Matrix3();

    }
    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        screenBounds.setSize(width,height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);
        System.out.println("width"+ " " + width +" "+"height"+" "+height);

        float aspect = width / (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f*aspect);
        MatrixUtils.calcTransitionMatrix(worldToGl,worldBounds,glBounds);
        batch.setProjectionMatrix(worldToGl);
        MatrixUtils.calcTransitionMatrix(screenToWorld,screenBounds,worldBounds);
        resize(worldBounds);
    }

    public void resize(Rect worldBounds) {
        System.out.println("resize worldBounds.width = " + worldBounds.getWidth() + " worldBounds.height = " + worldBounds.getHeight());
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        System.out.println("dispose");
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown");
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp");
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keyTyped");
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenBounds.getHeight()-screenY).mul(screenToWorld);
        touchDown(touch,pointer);
        System.out.println("tochDown");
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer){
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenBounds.getHeight()-screenY).mul(screenToWorld);
        touchUp(touch,pointer);
        System.out.println("touchUp");
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        System.out.println("touchUp");
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch.set(screenX, screenBounds.getHeight()-screenY).mul(screenToWorld);
        touchDragged(touch,pointer);
        System.out.println("touchDragged");
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("touchDragged");
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        System.out.println("scrolled");
        return false;
    }

}
