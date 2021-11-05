package com.mygdx.order;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

public class GameScreen implements Screen
{
	private final Drop game;
	private OrthographicCamera camera;
	private Animation player_animation, gold_animation, fire_animation;
	private TextureRegion player_texture_region, coin_texture_region, fireball_texture_region;
	private Texture heart_texture, background_texture;
	TextureAtlas fireball_atlas = new TextureAtlas(Gdx.files.internal("atlases/ball.atlas")), money_atlas = new TextureAtlas(Gdx.files.internal("atlases/coin.atlas")), atlas = PersonChange.atlas;
	TextureRegion[] player_animation_frames, coin_animation_frames, fireball_animation_frames;
	private boolean hardcore_gamemode_enabled = MainMenuScreen.hardcore_gamemode_enabled, treshold_reached = false, first_music_playing = true, second_music_playing = true;
	Sound fireball_sound;
	Music first_music, second_music;
	Rectangle player_rectangle;
	private int player_animation_frames_count = PersonChange.player_animation_frame_count, coin_animation_frames_count = 6, fireball_animation_frames_count = 13, player_height = 51, player_width = 24, screen_width = 800, screen_height = 480, animation_acceleration = 1, background_number = Options.background_number, music_number = Options.music_pack_number;
    String player_name =PersonChange.Name, coin_name = "d", fireball_name = "s";
	private Vector3 touch_position;
	FileHandle score_file = Gdx.files.local("data/bestscore.txt"), money_file =Gdx.files.local("data/coins.txt");
	Array<Rectangle> fireballs, hearts, coins;
	long last_fireball_time, last_heart_time, last_coin_time;
	private static long player_hearts =8, player_coins =1000, time = 800000000;
    private static double score=0,bestscore=0, speed=500.0;
	float coin_frame_time, player_frame_time, fireball_frame_time;
	public GameScreen (final Drop gam)
	{
		this.game = gam;
        heart_texture = new Texture("heart.png");
		boolean mlg_mode_enabled = MainMenuScreen.mlg_mode_enabled;
        if(mlg_mode_enabled)
        {
            player_name ="t";
			coin_name ="t";
			fireball_name ="t";
            player_animation_frames_count =30;
			coin_animation_frames_count =3;
			fireball_animation_frames_count =24;
            animation_acceleration =3;
            atlas = new TextureAtlas(Gdx.files.internal("atlases/snoop.atlas"));
            money_atlas = new TextureAtlas(Gdx.files.internal("atlases/dew.atlas"));
            fireball_atlas = new TextureAtlas(Gdx.files.internal("atlases/illumi.atlas"));
            heart_texture = new Texture("doritos.png");
        }
        MainMenuScreen.mlg_mode_enabled =false;
        player_animation_frames =new TextureRegion[player_animation_frames_count];
        for(int i=0; i< player_animation_frames_count; i++)
            player_animation_frames[i]=new TextureRegion(atlas.findRegion(player_name +Integer.toString(i+1)));
		coin_animation_frames = new TextureRegion[coin_animation_frames_count];
		for(int i=0; i< coin_animation_frames_count; i++)
			coin_animation_frames[i] = new TextureRegion(money_atlas.findRegion(coin_name +Integer.toString(i+1)));
		fireball_animation_frames = new TextureRegion[fireball_animation_frames_count];
		for(int i=0; i< fireball_animation_frames_count; i++)
			fireball_animation_frames[i] = new TextureRegion(fireball_atlas.findRegion(fireball_name +Integer.toString(i+1)));
		player_animation = new Animation(0.025f, player_animation_frames);
		fire_animation = new Animation(0.025f, fireball_animation_frames);
		gold_animation = new Animation(0.05f, coin_animation_frames);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, screen_width, screen_height);
		touch_position = new Vector3();
		Gdx.input.setCatchBackKey(true);
		switch(background_number)
		{
			case 1: {
				background_texture = new Texture("backgrounds/back1.png");
				break;
			}
			case 2: {
				background_texture = new Texture("backgrounds/back2.png");
				break;
			}
		}
		switch(music_number)
		{
			case 1: {
				first_music = Gdx.audio.newMusic(Gdx.files.internal("music/first_music.mp3"));
				second_music = Gdx.audio.newMusic(Gdx.files.internal("music/second_music.mp3"));
				break;
			}
			case 2: {
				first_music = Gdx.audio.newMusic(Gdx.files.internal("music/second_music.mp3"));
				second_music = Gdx.audio.newMusic(Gdx.files.internal("music/second_music_two.mp3"));
				break;
			}
		}
		fireball_sound = Gdx.audio.newSound(Gdx.files.internal("music/waterdrop.wav"));
		if(!Gdx.files.local("data/bestscore.txt").exists())
		{
		score_file = Gdx.files.local("data/bestscore.txt");
			score_file.writeString(Double.toString(bestscore=new BigDecimal(score).setScale(2,RoundingMode.UP).doubleValue()), false);
		}
		else bestscore =Double.valueOf(score_file.readString());
		if(!Gdx.files.local("data/coins.txt").exists()){
			money_file = Gdx.files.local("data/coins.txt");
			money_file.writeString(Long.toString(player_coins), false);
		}
		else player_coins = Long.valueOf(money_file.readString());
		first_music.play();
		player_rectangle = new Rectangle();
		player_rectangle.x = screen_width - player_width * 2;
		player_rectangle.y = 0;
		player_rectangle.width = player_width;
		player_rectangle.height = player_height;
		fireballs = new Array<Rectangle>();
		hearts = new Array<Rectangle>();
		coins = new Array<Rectangle>();
		fireball_frame_time = player_frame_time = coin_frame_time =0f;
	}
	private void Spawn_Fireball()
	{
		Rectangle raindrop = new Rectangle();
		raindrop.y = MathUtils.random(0, screen_height -33);
		raindrop.x = 0-65;
		raindrop.width = 65;
		raindrop.height = 33;
		fireballs.add(raindrop);
		last_fireball_time = TimeUtils.nanoTime();
	}
	private void Spawn_Coin()
	{
		Rectangle coin = new Rectangle();
		coin.y = MathUtils.random(0, screen_height -24);
		coin.x = 0-28;
		coin.width = 28;
		coin.height = 24;
		coins.add(coin);
		last_coin_time = TimeUtils.nanoTime();
	}
	private void Spawn_Heart()
	{
		Rectangle heart = new Rectangle();
		heart.y = MathUtils.random(0, screen_height -42);
		heart.x = 0-45;
		heart.width = 45;
		heart.height = 42;
		hearts.add(heart);
		last_heart_time = TimeUtils.nanoTime();
	}
	@Override
	public void render (float delta)
	{
					Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
					camera.update();
					fireball_frame_time += Gdx.graphics.getDeltaTime();
					player_frame_time += Gdx.graphics.getDeltaTime();
					coin_frame_time += Gdx.graphics.getDeltaTime();
					player_texture_region = player_animation.getKeyFrame(player_frame_time / 2, true);
					coin_texture_region = gold_animation.getKeyFrame(coin_frame_time / 2, true);
					fireball_texture_region = fire_animation.getKeyFrame(fireball_frame_time / animation_acceleration, true);
					game.batch.setProjectionMatrix(camera.combined);
					game.batch.begin();
					game.font.getData().setScale(1f,1f);
					game.batch.draw(background_texture, 0, 0);
					game.font.draw(game.batch, "Hearts: " + player_hearts, 40, screen_height -2);
					game.font.draw(game.batch, "Coins: " + player_coins, 385, 20);
					game.font.draw(game.batch, "Your score: " + score, 40, 20);
					game.font.draw(game.batch, "Best score: " + bestscore, 210, 20);
					game.batch.draw(player_texture_region, player_rectangle.x, player_rectangle.y);
					for (Rectangle raindrop : fireballs)
						game.batch.draw(fireball_texture_region, raindrop.x, raindrop.y);
					for (Rectangle heart : hearts)
						game.batch.draw(heart_texture, heart.x, heart.y);
					for (Rectangle coin : coins)
						game.batch.draw(coin_texture_region, coin.x, coin.y);
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
					player_hearts = 3;
					game.setScreen(new MainMenuScreen(game));
					first_music.pause();
					Gdx.files.local("data/coins.txt");
					money_file = Gdx.files.local("data/coins.txt");
					money_file.writeString(Long.toString(player_coins), false);
					if (score > bestscore)
					{
						bestscore = score;
						Gdx.files.local("data/bestscore.txt");
						score_file = Gdx.files.local("data/bestscore.txt");
						score_file.writeString(Double.toString(bestscore = new BigDecimal(score).setScale(2, RoundingMode.UP).doubleValue()), false);
					}
					score = 0;
					time = 800000000;
					speed = 500.0;
				}
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT))
		{
			try
			{
				Thread.sleep(50);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			player_hearts = 3;
			game.setScreen(new MainMenuScreen(game));
			first_music.pause();
			Gdx.files.local("data/coins.txt");
			money_file = Gdx.files.local("data/coins.txt");
			money_file.writeString(Long.toString(player_coins), false);
			if (score > bestscore)
			{
				bestscore = score;
				Gdx.files.local("data/bestscore.txt");
				score_file = Gdx.files.local("data/bestscore.txt");
				score_file.writeString(Double.toString(bestscore = new BigDecimal(score).setScale(2, RoundingMode.UP).doubleValue()), false);
			}
			score = 0;
			time = 800000000;
			speed = 500.0;
		}
				if (Gdx.input.isKeyPressed(Input.Keys.UP))
				{
					if (player_rectangle.y< screen_height - player_rectangle.height) player_rectangle.y += 500 * Gdx.graphics.getDeltaTime();
					if (player_rectangle.y>= screen_height - player_rectangle.height)
						player_rectangle.y = screen_height - player_rectangle.height;
				}
				if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
				{
					if (player_rectangle.y > 0) player_rectangle.y -= 500 * Gdx.graphics.getDeltaTime();
					if (player_rectangle.y <= 0)
						player_rectangle.y = 0;
				}
				if (Gdx.input.isTouched())
				{
					touch_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
					camera.unproject(touch_position);
					if (touch_position.x >= 0 && touch_position.x <=200)
					{
						if (player_rectangle.y > 0)
							player_rectangle.y -= 500 * Gdx.graphics.getDeltaTime();
						if (player_rectangle.y <= 0)
							player_rectangle.y = 0;
					}
					if (touch_position.x >= 600 && touch_position.x <= screen_width)
					{
						if (player_rectangle.y < screen_height - player_rectangle.height)
							player_rectangle.y += 500 * Gdx.graphics.getDeltaTime();
						if (player_rectangle.y>= screen_height - player_rectangle.height)
							player_rectangle.y = screen_height - player_rectangle.height;
					}
				}
					first_music_playing = first_music.isPlaying();
					if (!first_music_playing && score!=0) first_music.play();
//					second_music_playing = second_music.isPlaying();
//					if (!first_music_playing && !second_music_playing && score!=0) first_music.play();
					score += TimeUtils.millis() / 100000000000000.0;
					score = new BigDecimal(score).setScale(2, RoundingMode.UP).doubleValue();
					if (TimeUtils.nanoTime() - last_fireball_time > time)
						Spawn_Fireball();
					if (TimeUtils.nanoTime() - last_heart_time > time * 8)
						Spawn_Heart();
					if (TimeUtils.nanoTime() - last_coin_time > time * 5)
						Spawn_Coin();
					Iterator<Rectangle> iter = fireballs.iterator();
					Iterator<Rectangle> iter2 = hearts.iterator();
					Iterator<Rectangle> iter3 = coins.iterator();
					if(hardcore_gamemode_enabled)
					{
						if (time < 400000)
							treshold_reached = true;
					}
					else
					{
						if (time < 400000)
							treshold_reached = true;
					}
					while (iter.hasNext())
					{
						Rectangle raindrop = iter.next();
						raindrop.x += speed* Gdx.graphics.getDeltaTime();
						if(hardcore_gamemode_enabled)
						{
							if (speed<= 1000)
								speed+= 0.04;
							if (!treshold_reached) time -= 15000;
						}
						else {
							if (speed<= 1000)
								speed+= 0.02;
							if (!treshold_reached) time -= 10000;
						}
						if (raindrop.overlaps(player_rectangle))
						{
							player_hearts--;
							if (player_hearts == 0)
							{
								player_hearts = 3;
								game.setScreen(new LoseMenuScreen(game));
								first_music.pause();
								Gdx.files.local("data/coins.txt");
								money_file = Gdx.files.local("data/coins.txt");
								money_file.writeString(Long.toString(player_coins), false);
								if (score > bestscore)
								{
									bestscore = score;
									Gdx.files.local("data/bestscore.txt");
									score_file = Gdx.files.local("data/bestscore.txt");
									score_file.writeString(Double.toString(bestscore = new BigDecimal(score).setScale(2, RoundingMode.UP).doubleValue()), false);
								}
								score = 0;
								time = 800000000;
								speed = 500.0;
							}
							iter.remove();
							fireball_sound.play();
						}
						else if (raindrop.x + 65 > screen_width)
						    iter.remove();
					}
					while (iter2.hasNext())
					{
						Rectangle heart = iter2.next();
						heart.x += speed* Gdx.graphics.getDeltaTime();
                        if (heart.overlaps(player_rectangle))
						{
							player_hearts++;
							iter2.remove();
						}
						else if (heart.x + 45 > screen_width)
						    iter2.remove();
					}
					while (iter3.hasNext())
					{
						Rectangle coin = iter3.next();
						coin.x += speed* Gdx.graphics.getDeltaTime();
						if (coin.overlaps(player_rectangle))
						{
							player_coins++;
							iter3.remove();
						}
                        else if (coin.x + 28 > screen_width)
                            iter3.remove();
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
		heart_texture.dispose();
		fireball_sound.dispose();
		first_music.dispose();
		second_music.dispose();
		background_texture.dispose();
		game.batch.dispose();
		game.dispose();
		fireballs.clear();
		hearts.clear();
		coins.clear();
	}
	@Override
	public void show()
	{
		first_music.play();
	}
}