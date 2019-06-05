package ru.geekbrains.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Base.Sprite;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Math.Rnd;

public class Star extends Sprite {

    private Vector2 speedStar;
    private Rect worldbounds;
    private float height;

        public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        float speedStarX = Rnd.nextFloat(-0.005f, 0.005f);
        float speedStarY = Rnd.nextFloat(-0.5f, -0.1f);
        speedStar = new Vector2(speedStarX,speedStarY);
        height = Rnd.nextFloat(0.005f, 0.015f);
        setHeightProportion(height);
    }

    @Override
    public void update(float delta) {
            if (height >= 0.015f){
                height = 0.01f;
            } else {
                height = height+ 0.0001f;
            }
            setHeightProportion(height);
        pos.mulAdd(speedStar,delta);
        if (getRight() < worldbounds.getLeft()){
            setLeft(worldbounds.getRight());
        }
        if (getLeft() > worldbounds.getRight()){
            setRight(worldbounds.getLeft());
        }
        if (getTop() < worldbounds.getBottom()){
            setBottom(worldbounds.getTop());
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldbounds = worldBounds;
        float posX = Rnd.nextFloat(worldbounds.getLeft(),worldbounds.getRight());
        float posY = Rnd.nextFloat(worldbounds.getBottom(), worldbounds.getTop());
        pos.set(posX,posY);
    }
}
