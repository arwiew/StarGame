package ru.geekbrains.Sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.Base.ScaleTouchUpButton;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Screen.GameScreen;

public class ButtonNewGame extends ScaleTouchUpButton {

    private Game game;
    private State state;
    private GameScreen gameScreen;

    private enum State {PLAYNG, PAUSE, GAME_OVER}

        public ButtonNewGame(TextureAtlas atlas, Game game, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
            this.game = game;
            this.gameScreen = gameScreen;
            setHeightProportion(0.06f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setRight(worldBounds.getRight() - 0.18f);
        setBottom(worldBounds.getBottom() + 0.35f);
    }

   @Override
    public void action() {

    }
}

