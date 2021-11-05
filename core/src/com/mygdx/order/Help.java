package com.mygdx.order;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;



public class Help implements Screen
{
    final Drop game;
    Vector3 touchPos;
    int why=800;
    int hei=480;
    Texture up,down,nap,controls;
    OrthographicCamera camera;
    int background_number=Options.background_number;
    public Help(final Drop gam){
     game=gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, why, hei);
        touchPos = new Vector3();
        Gdx.input.setCatchBackKey(true);
        switch(background_number)
        {
            case 1: {
                nap = new Texture("backgrounds/back1.png");
                break;
            }
            case 2: {
                nap = new Texture("backgrounds/back2.png");
                break;
            }
        }
        up=new Texture("butt/up.png");
        down=new Texture("butt/down.png");
       // nap=new Texture("backgrounds/mainback.png");
        controls=new Texture("butt/controls.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(nap,0,0);
        game.batch.draw(down,0,0);
        game.batch.draw(up,600,0);
        game.batch.draw(controls,200,0);
        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           game.setScreen(new MainMenuScreen(game));
            dispose();
        }
        if (Gdx.input.isTouched()){
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.setScreen(new GameScreen(game));
            dispose();
                }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
