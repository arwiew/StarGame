package ru.geekbrains.Pool;

import ru.geekbrains.Base.SpritesPool;
import ru.geekbrains.Sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {

        return new Bullet();
    }
}