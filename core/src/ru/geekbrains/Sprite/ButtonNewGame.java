package ru.geekbrains.Sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.List;

import ru.geekbrains.Base.ScaleTouchUpButton;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Pool.BulletPool;
import ru.geekbrains.Pool.EnemyPool;
import ru.geekbrains.Pool.ExplosionPool;
import ru.geekbrains.Screen.GameScreen;
import ru.geekbrains.Utils.EnemyGenerator;

public class ButtonNewGame extends ScaleTouchUpButton {

    private Game game;
    private State state;

    private enum State {PLAYNG, PAUSE, GAME_OVER}

        public ButtonNewGame(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
            this.game = game;
            setHeightProportion(0.06f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setRight(worldBounds.getRight() - 0.18f);
        setBottom(worldBounds.getBottom() + 0.3f);
    }

   @Override
    public void action() {

     //   state = State.PLAYNG;

  // game.setScreen(this.gemeScreen);
    }
}

