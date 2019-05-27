package ru.geekbrains.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.Base.Sprite;
import ru.geekbrains.Math.Rect;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(1f);
        pos.set(worldBounds.pos);
    }
}
