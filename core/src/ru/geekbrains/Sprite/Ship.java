package ru.geekbrains.Sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Base.Sprite;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Pool.BulletPool;
import ru.geekbrains.Pool.ExplosionPool;

public abstract class Ship extends Sprite {

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected ExplosionPool explosionPool;

    protected Vector2 v;
    protected Vector2 v0;
    protected Vector2 bulletV;
    protected Vector2 exitV;
    protected float bulletHeight;

    protected Rect worldBounds;

    protected float shootTime;
    protected float delaTimer;

    protected int damage;
    protected int hp;

    private float damageAnimateInterval = 0.1f;
    private float damageanimateTimer = damageAnimateInterval;

    protected Sound bulletSound;
    private boolean isShooting = true;

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    public Ship() {
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
                   delaTimer += delta;
            if (delaTimer >= shootTime) {
                    shoot();
                    delaTimer -= shootTime;
                    damageanimateTimer += delta;
            }
                if (damageanimateTimer >= damageanimateTimer) {
                    frame = 0;
                }
    }



    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    public void damage (int damage){
        hp -= damage;
        if (hp <= 0){
            hp = 0;
            destroy();
        }
        frame = 1;
        damageanimateTimer = 0f;
    }


    private void shoot() {
        bulletSound.play();
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
        }

    private void boom(){
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(),pos);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
