package ru.geekbrains.Sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.Base.ScaleTouchUpButton;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Screen.GameScreen;

public class ButtonPlay extends ScaleTouchUpButton {

private Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
        setHeightProportion(0.17f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setRight(worldBounds.getRight() - 0.03f);
        setBottom(worldBounds.getBottom() + 0.03f);
    }

    @Override
    public void action() {
    game.setScreen(new GameScreen());
    }
}
