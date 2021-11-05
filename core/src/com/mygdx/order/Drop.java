package com.mygdx.order;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Drop extends Game {

    private Vector2 gravity;
    private Vector2 velocity;
    private Vector2 position;
    private Vector2 acceleration;
    private int width;
    private int height;
    SpriteBatch batch;
    BitmapFont font;

    public Drop(float x,float y,int width, int height){
        this.width=width;
        this.height=height;
        position= new Vector2(x, y);
        velocity= new Vector2(0,0);
        acceleration= new Vector2(0,480);
        gravity= new Vector2(0,300);
    }

    public void update(float delta) {
        velocity.add(gravity);
        if (velocity.y > 200) {
            velocity.y = 200;
        }
        position.add(velocity.cpy().scl(delta));
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        font.dispose();
    }
}