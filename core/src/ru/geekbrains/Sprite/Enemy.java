package ru.geekbrains.Sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Math.Rect;
import ru.geekbrains.Pool.BulletPool;
import ru.geekbrains.Pool.ExplosionPool;

public class Enemy extends Ship {

    MyShip myShip;

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Sound bulletSound, Rect worldBounds,  MyShip myShip) {
        this.explosionPool = explosionPool;
        this.bulletPool = bulletPool;
        this.bulletSound = bulletSound;
        this.worldBounds = worldBounds;
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletV = new Vector2();
        this.exitV = new Vector2();
        this.myShip = myShip;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getTop() > worldBounds.getTop()) {
            this.v = exitV;
        } else {
            this.v = v0;
        }
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
            myShip.damage(damage);
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            Vector2 exitV,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int damage,
            float shootTime,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.exitV.set(exitV);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.shootTime = shootTime;
        this.delaTimer = delaTimer;
        setHeightProportion(height);
        this.hp = hp;
        this.v.set(v0);
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(
                bullet.getRight() < getLeft()
                        || bullet.getLeft() > getRight()
                        || bullet.getBottom() > getTop()
                        || bullet.getTop() < pos.y
        );
    }

}

