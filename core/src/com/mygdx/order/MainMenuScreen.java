package com.mygdx.order;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen
{
    Rectangle new_game_rectangle, exit_rectangle, change_person_rectangle, options_rectangle, gamemode_rectangle, mlg_mode_rectangle;
    Texture new_game_button_texture, exit_button_texture, options_button_texture, change_person_button_texture, mainmenu_background_texture, normal_mode_button_texture, hardcore_mode_button_texture, mlg_mode_button_texture;
    Vector3 touch_position;
    public static boolean mlg_mode_enabled = false, hardcore_gamemode_enabled = false;
    int screen_height = Gdx.graphics.getHeight(), screen_width = Gdx.graphics.getWidth(), space_between_buttons = 30, device_type = 0;
    final Drop game;
    OrthographicCamera camera;
    public MainMenuScreen(final Drop gam)
    {
        game = gam;
//        if (screen_width <800&& screen_height <480)
//        {
//            screen_width =800;
//            screen_height =480;
//        }
//        if(screen_width ==1920&& screen_height !=1080|| screen_width !=1920&& screen_height ==1080)
//        {
//            screen_width =1920;
//            screen_height =1080;
//        }
//        else if(screen_width ==1280&& screen_height !=720|| screen_width !=1280&& screen_height ==720)
//        {
//            screen_width =1280;
//            screen_height =720;
//        }
//        else if(screen_width ==800&& screen_height !=480|| screen_width !=800&& screen_height ==480)
//        {
//            screen_width =800;
//            screen_height =480;
//        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screen_width, screen_height);
        touch_position = new Vector3();
        new_game_rectangle =new Rectangle();
        exit_rectangle =new Rectangle();
        change_person_rectangle =new Rectangle();
        options_rectangle =new Rectangle();
        mlg_mode_rectangle =new Rectangle();
        gamemode_rectangle =new Rectangle();
        Gdx.input.setCatchBackKey(true);
        if (screen_height >=1080)
        {
            device_type =1;
            exit_rectangle.y= space_between_buttons *3+50;
            new_game_rectangle.x= exit_rectangle.x= change_person_rectangle.x= options_rectangle.x=30;
            mlg_mode_rectangle.width= gamemode_rectangle.width= change_person_rectangle.width= exit_rectangle.width= new_game_rectangle.width= options_rectangle.width=300;
            mlg_mode_rectangle.height= gamemode_rectangle.height= change_person_rectangle.height= exit_rectangle.height= new_game_rectangle.height= options_rectangle.height=150;
        }
        else if (screen_height ==720)
        {
            device_type =2;
            exit_rectangle.y= space_between_buttons *3+30;
            new_game_rectangle.x= exit_rectangle.x= change_person_rectangle.x= options_rectangle.x=23;
            mlg_mode_rectangle.width= gamemode_rectangle.width= change_person_rectangle.width= exit_rectangle.width= new_game_rectangle.width= options_rectangle.width=225;
            mlg_mode_rectangle.height= gamemode_rectangle.height= change_person_rectangle.height= exit_rectangle.height= new_game_rectangle.height= options_rectangle.height=115;
        }
        else
        {
            device_type =3;
            exit_rectangle.y= space_between_buttons *3+15;
            new_game_rectangle.x= exit_rectangle.x= change_person_rectangle.x= options_rectangle.x=15;
            mlg_mode_rectangle.height= gamemode_rectangle.height= change_person_rectangle.height= exit_rectangle.height= new_game_rectangle.height= options_rectangle.height=75;
            mlg_mode_rectangle.width= gamemode_rectangle.width= change_person_rectangle.width= exit_rectangle.width= new_game_rectangle.width= options_rectangle.width=150;
        }
        mlg_mode_rectangle.x= gamemode_rectangle.x= screen_width - space_between_buttons - gamemode_rectangle.width;
        mlg_mode_rectangle.y= change_person_rectangle.y= screen_height - space_between_buttons *2- change_person_rectangle.height*2;
        gamemode_rectangle.y= new_game_rectangle.y= screen_height - space_between_buttons - new_game_rectangle.height;
        options_rectangle.y= exit_rectangle.y+ space_between_buttons + options_rectangle.height;
        exit_button_texture =new Texture("butt/exitbutton.png");
        mainmenu_background_texture =new Texture("backgrounds/mainback.png");
        new_game_button_texture =new Texture("butt/newgamebutton.png");
        change_person_button_texture =new Texture("butt/changebutton.png");
        options_button_texture =new Texture("butt/options.png");
        normal_mode_button_texture =new Texture("butt/normal.png");
        hardcore_mode_button_texture =new Texture("butt/hard.png");
        mlg_mode_button_texture =new Texture("butt/mlg.png");
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
        game.batch.draw(mainmenu_background_texture,0,0, screen_width, screen_height);
                game.batch.draw(exit_button_texture, exit_rectangle.x, exit_rectangle.y - exit_rectangle.height, gamemode_rectangle.width, gamemode_rectangle.height);
                game.batch.draw(change_person_button_texture, change_person_rectangle.x, change_person_rectangle.y, gamemode_rectangle.width, gamemode_rectangle.height);
                game.batch.draw(options_button_texture, options_rectangle.x, options_rectangle.y - exit_rectangle.height, gamemode_rectangle.width, gamemode_rectangle.height);
                game.batch.draw(new_game_button_texture, new_game_rectangle.x, new_game_rectangle.y, gamemode_rectangle.width, gamemode_rectangle.height);
                if(!hardcore_gamemode_enabled)
                    game.batch.draw(normal_mode_button_texture, gamemode_rectangle.x, gamemode_rectangle.y, gamemode_rectangle.width, gamemode_rectangle.height);
                else
                    game.batch.draw(hardcore_mode_button_texture, gamemode_rectangle.x, gamemode_rectangle.y, gamemode_rectangle.width, gamemode_rectangle.height);
                 /*if(!mlg_mode_enabled)
                     game.batch.draw(mlg_mode_button_texture, mlg_mode_rectangle.x, mlg_mode_rectangle.y,gamemode_rectangle.width,gamemode_rectangle.height);*/
        game.batch.end();
                if (Gdx.input.isKeyPressed(Input.Keys.BACK))
                {
                    Gdx.app.exit();
                    dispose();
                }
        if (Gdx.input.isTouched())
        {
            touch_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if(touch_position.x>= new_game_rectangle.x&& touch_position.x<=(new_game_rectangle.x+ new_game_rectangle.width))
                if(touch_position.y>= space_between_buttons && touch_position.y<=(space_between_buttons + new_game_rectangle.height))
                {
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    game.setScreen(new com.mygdx.order.Help(game));
                    dispose();
                }
            if(touch_position.x>= change_person_rectangle.x&& touch_position.x<=(change_person_rectangle.x+ change_person_rectangle.width))
                if(touch_position.y>= space_between_buttons *2+ change_person_rectangle.height&& touch_position.y<=(2* space_between_buttons +2* change_person_rectangle.height))
                {
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    mlg_mode_enabled =false;
                    game.setScreen(new PersonChange(game));
                    dispose();
                }
            if(touch_position.x>= exit_rectangle.x&& touch_position.x<=(exit_rectangle.x+ exit_rectangle.width))
                if(touch_position.y>= screen_height - exit_rectangle.y&& touch_position.y<=(screen_height - exit_rectangle.y+ exit_rectangle.height))
                {
                    Gdx.app.exit();
                    dispose();
                }
            if(touch_position.x>= options_rectangle.x&& touch_position.x<=(options_rectangle.x+ options_rectangle.width))
                if(touch_position.y>= screen_height - options_rectangle.y&& touch_position.y<=(screen_height - options_rectangle.y+ options_rectangle.height))
                {
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    mlg_mode_enabled =false;
                    game.setScreen(new Options(game));
                    dispose();
                }
            if(touch_position.x>= screen_width - space_between_buttons - gamemode_rectangle.width&& touch_position.x<= screen_width - space_between_buttons)
                if(touch_position.y>= space_between_buttons && touch_position.y<=(space_between_buttons + gamemode_rectangle.height))
                {
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if (hardcore_gamemode_enabled)
                        hardcore_gamemode_enabled =false;
                    else hardcore_gamemode_enabled =true;
                }
            if(touch_position.x>= screen_width - space_between_buttons - gamemode_rectangle.width&& touch_position.x<= screen_width - space_between_buttons)
                if(touch_position.y>= space_between_buttons *2+ change_person_rectangle.height&& touch_position.y<=2* space_between_buttons +2* change_person_rectangle.height)
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                  //  mlg_mode_enabled =true;
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
        new_game_button_texture.dispose();
        exit_button_texture.dispose();
        change_person_button_texture.dispose();
        options_button_texture.dispose();
    }
}