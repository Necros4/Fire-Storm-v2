package com.mygdx.order;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class LoseMenuScreen implements Screen
{
    Rectangle NewG,Exut,Chon;
    Texture newgame,exut,change,theback;
    Vector3 touchPos;
    int devicetype=0;
    int hei = Gdx.graphics.getHeight();
    int why = Gdx.graphics.getWidth();
    int betweenbuttons=30;
    final Drop game;
    OrthographicCamera camera;
    public LoseMenuScreen(final Drop gam)
    {
        game = gam;
//        if (why<800&&hei<480)
//        {
//            why=800;
//            hei=480;
//        }
//        if(why==1920&&hei!=1080||why!=1920&&hei==1080)
//        {
//            why=1920;
//            hei=1080;
//        }
//        else if(why==1280&&hei!=720||why!=1280&&hei==720)
//        {
//            why=1280;
//            hei=720;
//        }
//        else if(why==800&&hei!=480||why!=800&&hei==480)
//        {
//            why=800;
//            hei=480;
//        }
        Gdx.input.setCatchBackKey(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, why, hei);
        touchPos = new Vector3();
        NewG=new Rectangle();
        Exut=new Rectangle();
        Chon=new Rectangle();
        if (hei>=1080){devicetype=1;
            Exut.y=betweenbuttons*3+50;
            NewG.x=Exut.x=Chon.x=30;
            Chon.width=Exut.width=NewG.width=300;
            Chon.height=Exut.height=NewG.height=150;
        }
        else if (hei==720){devicetype=2;
            Exut.y=betweenbuttons*3+30;
            NewG.x=Exut.x=Chon.x=23;
            Chon.width=Exut.width=NewG.width=225;
            Chon.height=Exut.height=NewG.height=115;
        }
        else{devicetype=3;
            Exut.y=betweenbuttons*3+15;
            NewG.x=Exut.x=Chon.x=15;
            Chon.height=Exut.height=NewG.height=75;
            Chon.width=Exut.width=NewG.width=150;
        }
        Chon.y=hei-betweenbuttons*2- Chon.height*2;
        NewG.y=hei-betweenbuttons - NewG.height;
        theback=new Texture("backgrounds/gameover.png");
        newgame=new Texture("butt/newgamebutton.png");
        change=new Texture("butt/menubutton.png");
        exut=new Texture("butt/exitbutton.png");
    }
    @Override
    public void show()
    {
    }
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(theback,0,0,why,hei);
        switch (devicetype)
        {
            case 1:
            {
                game.batch.draw(newgame, NewG.x, NewG.y,300,150);
                game.batch.draw(exut, Exut.x, Exut.y-Exut.height,300,150);
                game.batch.draw(change, Chon.x, Chon.y,300,150);
                break;
            }
            case 2:
            {
                game.batch.draw(newgame, NewG.x, NewG.y,225,115);
                game.batch.draw(exut, Exut.x, Exut.y-Exut.height,225,115);
                game.batch.draw(change, Chon.x, Chon.y,225,115);
                break;
            }
            case 3:
            {
                game.batch.draw(newgame, NewG.x, NewG.y,150,75);
                game.batch.draw(exut, Exut.x, Exut.y-Exut.height,150,75);
                game.batch.draw(change, Chon.x, Chon.y,150,75);
                break;
            }
        }
        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
        {
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            game.setScreen(new com.mygdx.order.MainMenuScreen(game));
            dispose();
        }
        if (Gdx.input.isTouched())
        {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if(touchPos.x>=NewG.x&&touchPos.x<=(NewG.x+NewG.width))
                if(touchPos.y>=betweenbuttons&&touchPos.y<=(betweenbuttons+NewG.height))
                {
                    game.setScreen(new com.mygdx.order.GameScreen(game));
                    dispose();
                }
            if(touchPos.x>=Chon.x&&touchPos.x<=(Chon.x+Chon.width))
                if(touchPos.y>=betweenbuttons*2+Chon.height&&touchPos.y<=(2*betweenbuttons+2*Chon.height))
                {
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    game.setScreen(new com.mygdx.order.MainMenuScreen(game));
                    dispose();
                }
            if(touchPos.x>=Exut.x&&touchPos.x<=(Exut.x+Exut.width))
                if(touchPos.y>=hei-Exut.y&&touchPos.y<=(hei-Exut.y+Exut.height))
                {
                    Gdx.app.exit();
                    dispose();
                }
        }
    }
    @Override
    public void resize(int width, int height)
    {
    }
    @Override
    public void pause()
    {
    }
    @Override
    public void resume()
    {
    }
    @Override
    public void hide()
    {
    }
    @Override
    public void dispose()
    {
        newgame.dispose();
        exut.dispose();
        change.dispose();
    }
}