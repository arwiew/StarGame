package ru.geekbrains.Utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Math.Rect;
import ru.geekbrains.Math.Rnd;
import ru.geekbrains.Pool.EnemyPool;
import ru.geekbrains.Sprite.Enemy;

public class EnemyGenerator {


    private static final float METEOR_HEIGHT = 0.07f;
    private static final float METEOR_RELOAD_INTERVAL = 15f;
    private static final int METEOR_SMALL_HP = 1;
    private static final float METEOR_BULLET_HEIGHT= 0.00f;
    private static final float METEOR_BULLET_VY = -0.3f;
    private static final int METEOR_BULLET_DAMAGE = 0;
    private static final int METEOR_SCORE = 1;

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT= 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;
    private static final int ENEMY_SMALL_SCORE = 2;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT= 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.25f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 4f;
    private static final int ENEMY_MEDIUM_HP = 5;
    private static final int ENEMY_MEDIUM_SCORE = 4;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT= 0.04f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_BIG_HP = 10;
    private static final int ENEMY_BIG_SCORE = 7;

    private Rect worldBounds;

    private float generateInterval = 4f;
    private float generateTimer;

    private final TextureRegion[] meteorRegion;
    private final TextureRegion[] enemySmallRegion;
    private final TextureRegion[] enemyMediumRegion;
    private final TextureRegion[] enemyBigRegion;
    private final Vector2 meteorV = new Vector2(0f,-0.2f);
    private final Vector2 enemySmallV = new Vector2(0f, -0.2f);
    private final Vector2 enemyMediumV = new Vector2(0f, -0.03f);
    private final Vector2 enemyBigV = new Vector2(0f, -0.005f);
    private final Vector2 exitV = new Vector2(0f, -0.2f);

    private TextureRegion bulletRegion;

    private EnemyPool enemyPool;


    private int level;

    public EnemyGenerator(Rect worldBounds, EnemyPool enemyPool, TextureAtlas atlas,TextureAtlas atlas2) {
        this.worldBounds = worldBounds;
        this.enemyPool = enemyPool;
        TextureRegion textureRegionP = atlas2.findRegion("Meteor");
        this.meteorRegion = Regions.split(textureRegionP,1,2,2);
        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        this.enemySmallRegion = Regions.split(textureRegion0, 1, 2, 2);
        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        this.enemyMediumRegion = Regions.split(textureRegion1, 1, 2, 2);
        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemyBigRegion = Regions.split(textureRegion2, 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
    }

    public int getLevel() {
        return level;
    }

    public void generate(float delta, int score) {
        level = score / 50 + 1;
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.4f/level) {
                enemy.set(
                        enemySmallRegion,
                        enemySmallV,
                        exitV,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_BULLET_DAMAGE * level,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP,
                        ENEMY_SMALL_SCORE
                );
            } else if (type < 0.6f) {
                enemy.set(
                enemyMediumRegion,
                        enemyMediumV,
                        exitV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_BULLET_DAMAGE * level,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP,
                        ENEMY_MEDIUM_SCORE
                );
            }  else if (type < 0.85f) {

                enemy.set(
                        meteorRegion,
                        meteorV,
                        exitV,
                        bulletRegion,
                        METEOR_BULLET_HEIGHT,
                        METEOR_BULLET_VY,
                        METEOR_BULLET_DAMAGE,
                        METEOR_RELOAD_INTERVAL,
                        METEOR_HEIGHT,
                        METEOR_SMALL_HP,
                        METEOR_SCORE
                );

            } else {
                enemy.set(
                        enemyBigRegion,
                        enemyBigV,
                        exitV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_BULLET_DAMAGE * level,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP,
                        ENEMY_BIG_SCORE
                );
            }

            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());

        }
    }

}
