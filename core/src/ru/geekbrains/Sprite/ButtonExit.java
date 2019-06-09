package ru.geekbrains.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.Base.ScaleTouchUpButton;
import ru.geekbrains.Math.Rect;

public class ButtonExit extends ScaleTouchUpButton {


    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
        setHeightProportion(0.135f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setLeft(worldBounds.getLeft() + 0.03f);
        setBottom(worldBounds.getBottom() + 0.03f);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
