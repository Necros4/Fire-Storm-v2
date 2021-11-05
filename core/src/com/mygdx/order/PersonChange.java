package com.mygdx.order;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class PersonChange implements Screen{
    final Drop game;
    boolean IfFileThere = Gdx.files.local("data/p2.txt").exists(),gg=Gdx.files.local("data/p3.txt").exists(),wp=Gdx.files.local("data/coins.txt").exists(),gg1=Gdx.files.local("data/p4.txt").exists(),gg2=Gdx.files.local("data/p5.txt").exists(),gg3=Gdx.files.local("data/p6.txt").exists();
    FileHandle handle;
    public static int coins=0;
    boolean kek1=Gdx.files.local("data/p7.txt").exists(),kek2=Gdx.files.local("data/p8.txt").exists(),kek3=Gdx.files.local("data/p9.txt").exists();
    public static boolean enable1=false,enable2=false,enable3=false,enable4=false,enable5=false,enable6=false,enable7=false,enable8=false;
    public Texture left,right,theback;
    int resolution = 0, arrow_width;
    public static int player_animation_frame_count = 5;
    Vector3 touchPos;
    public static String Name="per";
    float pt;
    public static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlases/person1.atlas"));
    TextureRegion[] q;
    TextureRegion player_number;
    Animation w;
    int hei = Gdx.graphics.getHeight();
    int why = Gdx.graphics.getWidth();
    public static int numerperson=1;
    OrthographicCamera camera;
    public PersonChange(final Drop gam){
        game = gam;
//        if (screen_width<800&&screen_height<480){
//            screen_width=800;
//            screen_height=480;
//        }
//        if(screen_width==1920&&screen_height!=1080||screen_width!=1920&&screen_height==1080){
//            screen_width=1920;
//            screen_height=1080;
//        }
//        else if(screen_width==1280&&screen_height!=720||screen_width!=1280&&screen_height==720){
//            screen_width=1280;
//            screen_height=720;
//        }
//        else if(screen_width==800&&screen_height!=480||screen_width!=800&&screen_height==480){
//            screen_width=800;
//            screen_height=480;
//        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false, why, hei);
        touchPos=new Vector3();
        if (hei>=1080){
            resolution =1;
            arrow_width =512;
            }
        else if (hei==720){
            resolution =2;
            arrow_width =292;
            }
        else {
            resolution = 3;
            arrow_width = 222;
        }
        Gdx.input.setCatchBackKey(true);
        handle= Gdx.files.local("data/p2.txt");
        if(!IfFileThere)
            handle.writeString(String.valueOf(enable1), false);
        else enable1 =Boolean.valueOf(handle.readString());
        handle= Gdx.files.local("data/p3.txt");
        if(!gg)
            handle.writeString(String.valueOf(enable2), false);
        else enable2 =Boolean.valueOf(handle.readString());
        handle= Gdx.files.local("data/coins.txt");
        if(!wp)
                handle.writeString(Integer.toString(coins), false);
        else
        coins=Integer.valueOf(handle.readString());
        handle= Gdx.files.local("data/p4.txt");
        if(!gg1)
            handle.writeString(String.valueOf(enable3), false);
        else enable3 =Boolean.valueOf(handle.readString());
        handle= Gdx.files.local("data/p5.txt");
        if(!gg2)
            handle.writeString(String.valueOf(enable4), false);
        else enable4 =Boolean.valueOf(handle.readString());
        handle= Gdx.files.local("data/p6.txt");
        if(!gg3)
            handle.writeString(String.valueOf(enable5), false);
        else enable5 =Boolean.valueOf(handle.readString());
        handle= Gdx.files.local("data/p7.txt");
        if(!kek1)
            handle.writeString(String.valueOf(enable6), false);
        else enable6 =Boolean.valueOf(handle.readString());
        handle= Gdx.files.local("data/p8.txt");
        if(!kek2)
            handle.writeString(String.valueOf(enable7), false);
        else enable7 =Boolean.valueOf(handle.readString());
        handle= Gdx.files.local("data/p9.txt");
        if(!kek3)
            handle.writeString(String.valueOf(enable8), false);
        else enable8 =Boolean.valueOf(handle.readString());
        theback=new Texture("backgrounds/mainback.png");
        left = new Texture("butt/chevronl.png");
        right = new Texture("butt/chevronr.png");
        pt=0f;
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        pt+=Gdx.graphics.getDeltaTime();
        switch (numerperson) {
            case 1: {
                player_animation_frame_count =5;Name="per";atlas = new TextureAtlas(Gdx.files.internal("atlases/person1.atlas"));break;}
            case 2: {
                player_animation_frame_count =12;Name="flashanim";atlas = new TextureAtlas(Gdx.files.internal("atlases/person2.atlas"));break;}
            case 3: {
                player_animation_frame_count =7;Name="a";atlas = new TextureAtlas(Gdx.files.internal("atlases/person3.atlas"));break;}
            case 4: {
                player_animation_frame_count =4;Name="r";atlas = new TextureAtlas(Gdx.files.internal("atlases/person4.atlas"));break;}
            case 5: {
                player_animation_frame_count =4;Name="sor";atlas = new TextureAtlas(Gdx.files.internal("atlases/person5.atlas"));break;}
            case 6: {
                player_animation_frame_count =5;Name="n";atlas = new TextureAtlas(Gdx.files.internal("atlases/person6.atlas"));break;}
            case 7: {
                player_animation_frame_count =15;Name="t";atlas = new TextureAtlas(Gdx.files.internal("atlases/person7.atlas"));break;}
            case 8: {
                player_animation_frame_count =16;Name="t";atlas = new TextureAtlas(Gdx.files.internal("atlases/person8.atlas"));break;}
            case 9: {
                player_animation_frame_count =7;Name="t";atlas = new TextureAtlas(Gdx.files.internal("atlases/person9.atlas"));break;}
        }
        q=new TextureRegion[player_animation_frame_count];
        for(int i = 0; i< player_animation_frame_count; i++)
            q[i]=new TextureRegion(atlas.findRegion(Name+Integer.toString(i+1)));
        w=new Animation(0.025f,q);
        player_number = w.getKeyFrame(pt / 2, true);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.getData().setScale(3f,3f);
        game.batch.draw(theback,0,0,why,hei);
        game.font.draw(game.batch, "Coins: " + coins, 40, hei);
        if(numerperson!=1)
                game.batch.draw(left, 0, hei / 2- arrow_width /2, arrow_width, arrow_width);
        if(numerperson!=9)
                game.batch.draw(right, why- arrow_width, hei / 2- arrow_width /2, arrow_width, arrow_width);
        switch (numerperson){
            case 1:{
                if(resolution ==1)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,57,129);
                else if (resolution ==2)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,36,57);
                else
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,24,52);
                break;
            }
            case 2:{
                if(resolution ==1)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,106,117);
                else if (resolution ==2)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,70,78);
                else
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,44,48);
                if(!enable1)
                    game.font.draw(game.batch,"60 coins required!",why/2-60,hei);
                break;
            }
            case 3:{
                if(resolution ==1)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,120,120);
                else if (resolution ==2)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,80,80);
                else
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,50,50);
                if(!enable2)
                    game.font.draw(game.batch,"80 coins required!",why/2-60,hei);
                break;
            }
            case 4:{
                if(resolution ==1)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,120,127);
                else if (resolution ==2)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,80,84);
                else
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,50,52);
                if(!enable3)
                    game.font.draw(game.batch,"120 coins required!",why/2-60,hei);
                break;
            }
            case 5:{
                if(resolution ==1)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,120,153);
                else if (resolution ==2)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,80,102);
                else
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,50,64);
                if(!enable4)
                    game.font.draw(game.batch,"200 coins required!",why/2-60,hei);
                break;
            }
            case 6:{
                if(resolution ==1)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,120,137);
                else if (resolution ==2)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,80,91);
                else
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,50,57);
                if(!enable5)
                    game.font.draw(game.batch,"300 coins required!",why/2-60,hei);
                break;
            }
            case 7:{
                if(resolution ==1)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,79,122);
                else if (resolution ==2)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,50,76);
                else
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,33,51);
                if(!enable6)
                    game.font.draw(game.batch,"500 coins required!",why/2-60,hei);
                break;
            }
            case 8:{
                if(resolution ==1)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,105,122);
                else if (resolution ==2)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,66,76);
                else
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,44,51);
                if(!enable7)
                    game.font.draw(game.batch,"800 coins required!",why/2-60,hei);
                break;
            }
            case 9:{
                if(resolution ==1)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,76,122);
                else if (resolution ==2)
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,48,76);
                else
                    game.batch.draw(player_number, why / 2, hei / 2- arrow_width /10,32,51);
                if(!enable8)
                    game.font.draw(game.batch,"1000 coins required!",why/2-60,hei);
                break;
            }
        }
        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if (numerperson == 1 || (enable1 && numerperson == 2) || (enable2 && numerperson == 3) || (enable3 && numerperson == 4) || (enable4 && numerperson == 5) || (enable5 && numerperson == 6) || (enable6 && numerperson == 7) || (enable7 && numerperson == 8) || (enable8 && numerperson == 9)) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.setScreen(new com.mygdx.order.MainMenuScreen(game));
                dispose();
            } else {
                numerperson = 1;
                render(delta);
                game.setScreen(new com.mygdx.order.MainMenuScreen(game));
                dispose();
            }
        }
        if (Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if(touchPos.x>=0&&touchPos.x<= arrow_width)
                {if(numerperson>1)
                    numerperson--;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dispose();
                }
            else if(touchPos.x>=why- arrow_width &&touchPos.x<=why)
                {if(numerperson<9)
                    numerperson++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dispose();
                }
            else{
                if(numerperson==1||(enable1&&numerperson==2)||(enable2&&numerperson==3)||(enable3&&numerperson==4)||(enable4&&numerperson==5)||(enable5&&numerperson==6)||(enable6&&numerperson==7)||(enable7&&numerperson==8)||(enable8&&numerperson==9)){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    game.setScreen(new com.mygdx.order.MainMenuScreen(game));
                    dispose();
                }
            else {
                    switch (numerperson){
                        case 2: {
                                if (coins>=60) {coins -= 60;
                                enable1 = true;
                                Gdx.files.local("data/p2.txt");
                                handle = Gdx.files.local("data/p2.txt");
                                handle.writeString(String.valueOf(enable1), false);}
                        break;}
                        case 3: {
                                if (coins>=80) {coins -= 80;
                                enable2=true;
                                Gdx.files.local("data/p3.txt");
                                handle = Gdx.files.local("data/p3.txt");
                                handle.writeString(String.valueOf(enable2),false);}break;}
                        case 4: {
                            if (coins>=120) {coins -= 120;
                                enable3=true;
                                Gdx.files.local("data/p4.txt");
                                handle = Gdx.files.local("data/p4.txt");
                                handle.writeString(String.valueOf(enable3),false);}break;}
                        case 5: {
                            if (coins>=200) {coins -= 200;
                                enable4=true;
                                Gdx.files.local("data/p5.txt");
                                handle = Gdx.files.local("data/p5.txt");
                                handle.writeString(String.valueOf(enable4),false);}break;}
                        case 6: {
                            if (coins>=300) {coins -= 300;
                                enable5=true;
                                Gdx.files.local("data/p6.txt");
                                handle = Gdx.files.local("data/p6.txt");
                                handle.writeString(String.valueOf(enable5),false);}break;}
                        case 7: {
                            if (coins>=500) {coins -= 500;
                                enable6=true;
                                Gdx.files.local("data/p7.txt");
                                handle = Gdx.files.local("data/p7.txt");
                                handle.writeString(String.valueOf(enable6),false);}break;}
                        case 8: {
                            if (coins>=800) {coins -= 800;
                                enable7=true;
                                Gdx.files.local("data/p8.txt");
                                handle = Gdx.files.local("data/p8.txt");
                                handle.writeString(String.valueOf(enable7),false);}break;}
                        case 9: {
                            if (coins>=1000) {coins -= 1000;
                                enable8=true;
                                Gdx.files.local("data/p9.txt");
                                handle = Gdx.files.local("data/p9.txt");
                                handle.writeString(String.valueOf(enable8),false);}break;}
                        }

                    }
                }
                    Gdx.files.local("data/coins.txt");
                    handle = Gdx.files.local("data/coins.txt");
                    handle.writeString(Integer.toString(coins),false);
                    try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
