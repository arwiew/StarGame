package ru.geekbrains.Sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Base.Sprite;
import ru.geekbrains.Math.Rect;

public class Ship extends Sprite {

    private Vector2 touch;
    private Vector2 speed;
    private Vector2 buf;



    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("oie_2204520X3lN2eK3"));
        regions[frame].getTexture().setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        touch = new Vector2();
        speed = new Vector2();
        buf = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
       this.touch = touch;
       speed.set(touch.cpy().sub(pos)).setLength(0.005f);
       return false;

    }

    @Override
    public void update(float delta) {
    super.update(delta);
    buf.set(touch);
    if (buf.sub(pos).len()<= 0.005f){
        pos.set(touch);
    } else {
        pos.add(speed);
    }
    }
}