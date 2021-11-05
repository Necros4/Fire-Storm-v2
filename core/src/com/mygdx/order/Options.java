package com.mygdx.order;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Options implements Screen {
    final Drop game;
    Texture cgb,cmp,exut,mmenu,ein,zwei,num1,num2,theback;
    Vector3 touchPos;
    public static int music_pack_number = 1, background_number = 1;
    int betweenbuttons=30;
    int hei = Gdx.graphics.getHeight();
    int why = Gdx.graphics.getWidth();
    int devicetype=0;
    Rectangle MainM,Exut,ChangeGameBack,ChangeMusicPack;
    OrthographicCamera camera;
    public Options (final Drop gam) {
        this.game = gam;
//        if (why<800&&hei<480){
//            why=800;
//            hei=480;
//        }
//        if(why==1920&&hei!=1080||why!=1920&&hei==1080){
//            why=1920;
//            hei=1080;
//        }
//        else if(why==1280&&hei!=720||why!=1280&&hei==720){
//            why=1280;
//            hei=720;
//        }
//        else if(why==800&&hei!=480||why!=800&&hei==480){
//            why=800;
//            hei=480;
//        }
        Gdx.input.setCatchBackKey(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, why, hei);
        touchPos=new Vector3();
        ChangeGameBack=new Rectangle();
        ChangeMusicPack=new Rectangle();
        MainM=new Rectangle();
        Exut=new Rectangle();
        if (hei>=1080){devicetype=1;
            Exut.y=betweenbuttons*3+50;
            MainM.x=Exut.x=ChangeGameBack.x=ChangeMusicPack.x=30;
            ChangeGameBack.width=Exut.width=MainM.width=ChangeMusicPack.width=300;
            ChangeGameBack.height=Exut.height=MainM.height=ChangeMusicPack.height=150;
        }
        else if (hei==720){devicetype=2;
            Exut.y=betweenbuttons*3+30;
            MainM.x=Exut.x=ChangeGameBack.x=ChangeMusicPack.x=23;
            ChangeGameBack.width=Exut.width=MainM.width=ChangeMusicPack.width=225;
            ChangeGameBack.height=Exut.height=MainM.height=ChangeMusicPack.height=115;
        }
        else{devicetype=3;
            Exut.y=betweenbuttons*3+15;
            MainM.x=Exut.x=ChangeGameBack.x=ChangeMusicPack.x=15;
            ChangeGameBack.height=Exut.height=MainM.height=ChangeMusicPack.height=75;
            ChangeGameBack.width=Exut.width=MainM.width=ChangeMusicPack.width=150;
        }
        ChangeGameBack.y=hei-betweenbuttons*2- ChangeGameBack.height*2;
        MainM.y=hei-betweenbuttons - MainM.height;
        ChangeMusicPack.y=Exut.y+betweenbuttons+ChangeMusicPack.height;
        mmenu = new Texture("butt/menubutton.png");
        exut=new Texture("butt/exitbutton.png");
        theback=new Texture("backgrounds/mainback.png");
        cmp=new Texture("butt/cmp.png");
        cgb=new Texture("butt/cbp.png");
        ein=new Texture("butt/one.png");
        zwei=new Texture("butt/two.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        if(music_pack_number ==1)
            num2=ein;
        else if (music_pack_number ==2)
            num2=zwei;
        if(background_number ==1)
            num1=ein;
        else if (background_number ==2)
            num1=zwei;
        game.batch.begin();
        game.batch.draw(theback, 0, 0,why,hei);
        switch (devicetype) {
            case 1:{
                game.batch.draw(mmenu, MainM.x, MainM.y,300,150);
                game.batch.draw(exut, Exut.x, Exut.y - Exut.height,300,150);
                game.batch.draw(cmp, ChangeMusicPack.x, ChangeMusicPack.y - Exut.height,300,150);
                game.batch.draw(num2, ChangeMusicPack.x + ChangeMusicPack.width + 30, ChangeMusicPack.y - Exut.height,150,150);
                game.batch.draw(cgb, ChangeGameBack.x, ChangeGameBack.y,300,150);
                game.batch.draw(num1, ChangeGameBack.x + ChangeGameBack.width + 30, ChangeGameBack.y,150,150);
                break;
            }
            case 2:{
                game.batch.draw(mmenu, MainM.x, MainM.y,225,115);
                game.batch.draw(exut, Exut.x, Exut.y - Exut.height,225,115);
                game.batch.draw(cmp, ChangeMusicPack.x, ChangeMusicPack.y - Exut.height,225,115);
                game.batch.draw(num2, ChangeMusicPack.x + ChangeMusicPack.width + 30, ChangeMusicPack.y - Exut.height,115,115);
                game.batch.draw(cgb, ChangeGameBack.x, ChangeGameBack.y,225,115);
                game.batch.draw(num1, ChangeGameBack.x + ChangeGameBack.width + 30, ChangeGameBack.y,115,115);
                break;
            }

        case 3:{
            game.batch.draw(mmenu, MainM.x, MainM.y,150,75);
            game.batch.draw(exut, Exut.x, Exut.y - Exut.height,150,75);
            game.batch.draw(cmp, ChangeMusicPack.x, ChangeMusicPack.y - Exut.height,150,75);
            game.batch.draw(num2, ChangeMusicPack.x + ChangeMusicPack.width + 30, ChangeMusicPack.y - Exut.height,75,75);
            game.batch.draw(cgb, ChangeGameBack.x, ChangeGameBack.y,150,75);
            game.batch.draw(num1, ChangeGameBack.x + ChangeGameBack.width + 30, ChangeGameBack.y,75,75);
            break;
        }
        }
        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.setScreen(new com.mygdx.order.MainMenuScreen(game));
            dispose();
        }
        if (Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if(touchPos.x>=MainM.x&&touchPos.x<=(MainM.x+MainM.width))
                if(touchPos.y>=betweenbuttons&&touchPos.y<=(betweenbuttons+MainM.height)){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    game.setScreen(new com.mygdx.order.MainMenuScreen(game));
                    dispose();
                }
            if(touchPos.x>=ChangeGameBack.x&&touchPos.x<=(ChangeGameBack.x+ChangeGameBack.width))
                if(touchPos.y>=betweenbuttons*2+ChangeGameBack.height&&touchPos.y<=(2*betweenbuttons+2*ChangeGameBack.height)){
                    if(background_number ==2)
                        background_number =1;
                    else if (background_number ==1)
                        background_number =2;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            if(touchPos.x>=Exut.x&&touchPos.x<=(Exut.x+Exut.width))
                if(touchPos.y>=hei-Exut.y&&touchPos.y<=(hei-Exut.y+Exut.height)){
                    Gdx.app.exit();
                    dispose();
                }
            if(touchPos.x>=ChangeMusicPack.x&&touchPos.x<=(ChangeMusicPack.x+ChangeMusicPack.width))
                if(touchPos.y>=hei-ChangeMusicPack.y&&touchPos.y<=(hei-ChangeMusicPack.y+ChangeMusicPack.height)){
                    if(music_pack_number ==2)
                        music_pack_number =1;
                    else if (music_pack_number ==1)
                        music_pack_number =2;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
        cgb.dispose();
        cmp.dispose();
        exut.dispose();
        mmenu.dispose();
        ein.dispose();
        zwei.dispose();
    }
}
