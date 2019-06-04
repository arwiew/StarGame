package ru.geekbrains.Sprite;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


import ru.geekbrains.Base.Sprite;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Pool.BulletPool;

public class MyShip extends Sprite {

    private static final int INVALID_POINTER = -1;

    private Vector2 v;
    private final Vector2 v0;
    private Vector2 bulletV;
    private Vector2 bulletPos;
    private Rect worldBounds;
    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private boolean pressedLeft;
    private boolean pressedRight;
    private float shootTime;
    private float delaTimer;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    // Собственный корабль, не забыть добавить пули.

/*   public MyShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("oie_2204520X3lN2eK3"));
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        v = new Vector2();
        v0 = new Vector2(0.5f, 0);
        bulletV = new Vector2(0, 0.5f);
        bulletPos = new Vector2();
     } */

     public MyShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        v = new Vector2();
        v0 = new Vector2(0.5f, 0);
        bulletV = new Vector2(0, 0.5f);
        bulletPos = new Vector2();
        shootTime = 0.1f;


    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.1f);
        setBottom(worldBounds.getBottom()+0.05f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
    if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    @Override
    public void update(float delta){
    super.update(delta);
        pos.mulAdd(v, delta);
        delaTimer = delaTimer + delta;
        if (delaTimer > shootTime) {
            shoot();
            delaTimer -= shootTime;
        }
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        } else if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
    }


     private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bulletPos.set(pos);
        bulletPos.y += getHalfHeight();
        bullet.set(this, bulletRegion, bulletPos, bulletV, 0.01f, worldBounds, 1);
    }

    private void notShoot(){

    }

}
