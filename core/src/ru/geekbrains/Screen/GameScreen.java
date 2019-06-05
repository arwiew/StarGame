package ru.geekbrains.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.geekbrains.Base.BaseScreen;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Pool.BulletPool;
import ru.geekbrains.Pool.EnemyPool;
import ru.geekbrains.Pool.ExplosionPool;
import ru.geekbrains.Sprite.Background;
import ru.geekbrains.Sprite.Bullet;
import ru.geekbrains.Sprite.ButtonNewGame;
import ru.geekbrains.Sprite.Enemy;
import ru.geekbrains.Sprite.MessageGameOver;
import ru.geekbrains.Sprite.MyShip;
import ru.geekbrains.Sprite.Star;
import ru.geekbrains.Utils.EnemyGenerator;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private enum State {PLAYNG, PAUSE, GAME_OVER}

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private TextureAtlas atlas1;
    private Star[] starArray;
    private MyShip myShip;

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private EnemyPool enemyPool;

    private Music music;
    private Sound laserSound;
    private Sound explosionSound;
    private Sound bulletSound;

    private State state;

    private EnemyGenerator enemyGenerator;

    private MessageGameOver messageGameOver;

    private ButtonNewGame buttonNewGame;
    private Game game;

    @Override
    public void show() {
        super.show();
        music = Gdx.audio.newMusic(Gdx.files.internal("Sound/music.mp3"));
        music.setLooping(true);
        music.play();
        laserSound = Gdx.audio.newSound(Gdx.files.internal("Sound/laser.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("Sound/explosion.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("Sound/bullet.wav"));
        bg = new Texture("fon.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("Textures/mainAtlas.tpack");
        atlas1 = new TextureAtlas("Textures/Ship.pack");
        starArray = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            starArray[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, explosionSound);
        myShip = new MyShip(atlas, bulletPool, explosionPool, laserSound);
        enemyPool = new EnemyPool(explosionPool, bulletPool, bulletSound, worldBounds, myShip);
        enemyGenerator = new EnemyGenerator(worldBounds, enemyPool, atlas);
        messageGameOver = new MessageGameOver(atlas);
        state = State.PLAYNG;
        buttonNewGame = new ButtonNewGame(atlas, game);
        //  myShip = new MyShip(atlas1,bulletPool);// собственный корабль
    }

    @Override
    public void pause() {
        super.pause();
        if (state == State.GAME_OVER) {
            return;
        }
        state = State.PAUSE;
        music.pause();
    }

    @Override
    public void resume() {
        super.resume();
        if (state == State.GAME_OVER) {
            return;
        }
        state = State.PLAYNG;
        music.play();
    }

    @Override
    public void render(float delta) {
        update(delta);
        enemyDestroy();
        freeAllDestroyedActiveObjects();
        draw();
    }

    private void update(float delta) {
        for (Star star : starArray) {
            star.update(delta);
        }
        explosionPool.updateActiveSprites(delta);
        if(state == State.PLAYNG){
                myShip.update(delta);
                bulletPool.updateActiveSprites(delta);
                enemyPool.updateActiveSprites(delta);
                enemyGenerator.generate(delta);
        }

    }

    private void draw() {
        Gdx.gl.glClearColor(0.4f, 0.3f, 0.9f, 0.5f); //МОЖНО ЗАДАТЬ ФОН
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //очистка экрана, работеат в паре с ClaerColor
        batch.begin();
        background.draw(batch);
        for (Star star : starArray) {
            star.draw(batch);
        }
        explosionPool.drawActiveSprites(batch);
        if (state == State.PLAYNG) {
             myShip.draw(batch);
             bulletPool.drawActiveSprites(batch);
             enemyPool.drawActiveSprites(batch);
        } else if (state == State.GAME_OVER ) {
            messageGameOver.draw(batch);
            buttonNewGame.draw(batch);
            bulletPool.getActiveObjects().clear();
            enemyPool.getActiveObjects().clear();
            if (state == State.PLAYNG) {
                myShip.draw(batch);
                bulletPool.drawActiveSprites(batch);
                enemyPool.drawActiveSprites(batch);
            }
        }
        batch.end();
    }

    private void freeAllDestroyedActiveObjects() {
        bulletPool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starArray) {
            star.resize(worldBounds);
        }
        myShip.resize(worldBounds);
        buttonNewGame.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        bulletPool.dispose();
        explosionPool.dispose();
        atlas.dispose();
        laserSound.dispose();
        explosionSound.dispose();
        bulletSound.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYNG) {
            myShip.keyDown(keycode);
        }
        return false;
        }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYNG) {
            myShip.keyUp(keycode);
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (state == State.PLAYNG) {
            myShip.touchDown(touch, pointer);
        }
        buttonNewGame.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (state == State.PLAYNG) {
            myShip.touchUp(touch, pointer);
        }
        buttonNewGame.touchUp(touch,pointer);
        state = State.PLAYNG;
        return false;
    }

    private void enemyDestroy() {

        if (state != State.PLAYNG) {
            return;
        }
        List<Enemy> enemyCollection = enemyPool.getActiveObjects();
        List<Bullet> bulletList = bulletPool.getActiveObjects();

        for (Enemy enemy : enemyCollection) {
            if (enemy.isDestroyed()) {
                continue;
            }
            if (enemy.pos.cpy().sub(myShip.pos).len() <= 0.05f) {
                enemy.destroy(); //////////////////////////////////////////////
                myShip.destroy();
                state=State.GAME_OVER;
            }

            for (Bullet bullet : bulletList) {
                if (bullet.getOwner() != myShip || bullet.isDestroyed()) {
                    continue;
                }
                if (enemy.isBulletCollision(bullet)) {
                    enemy.damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
        }
        for (Bullet bullet : bulletList) {
            if (bullet.getOwner() == myShip || bullet.isDestroyed()) {
                continue;
            }
            if (myShip.isBulletCollision(bullet)) {
                myShip.damage(bullet.getDamage());
                bullet.destroy();
                if (myShip.isDestroyed()){
                    state = State.GAME_OVER;
                }
            }
        }
    }
}

