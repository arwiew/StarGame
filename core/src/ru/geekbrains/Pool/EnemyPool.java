package ru.geekbrains.Pool;

import com.badlogic.gdx.audio.Sound;

import ru.geekbrains.Base.SpritesPool;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Sprite.Enemy;
import ru.geekbrains.Sprite.MyShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Sound bulletSound;
    private Rect worldBounds;
    private MyShip myShip;

    public EnemyPool(ExplosionPool explosionPool, BulletPool bulletPool, Sound bulletSound, Rect worldBounds, MyShip myShip) {
        this.explosionPool = explosionPool;
        this.bulletPool = bulletPool;
        this.bulletSound = bulletSound;
        this.worldBounds = worldBounds;
        this.myShip = myShip;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, bulletSound, worldBounds, myShip);
    }
}