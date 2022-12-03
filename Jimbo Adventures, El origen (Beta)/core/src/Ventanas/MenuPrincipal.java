package Ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.JimboAdventures;

import Patrones.FinalLevelBuilder;
import Patrones.Level;
import Patrones.LevelBuilder;
import Patrones.LevelCreator;
import Utils.Screens;

public class MenuPrincipal extends Screens {
	private BitmapFont font;
	private Music menuMusic;
	private TextureRegion logo = new TextureRegion(new Texture("Logo.png"));
	private TextureRegion fondo = new TextureRegion(new Texture("Menu.jpg"));
	
	public MenuPrincipal(JimboAdventures game) {
		super(game);
		font = new BitmapFont();
		font.getData().setScale(3f);
		
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Menu music.wav"));
		menuMusic.setLooping(true);
		menuMusic.setVolume(0.5f);
		menuMusic.play();
		
		//font.setColor(Color.BLACK);
	}
	
	@Override
	public void render(float delta) {
		
		getCamUI().update();
		getBatch().setProjectionMatrix(getCamUI().combined);
		
		getBatch().begin();
		getBatch().draw(fondo, 0f, 0f, 0.5f, 0.5f, 1895f, 1000f, 1f, 1f, 0f);
		getBatch().draw(logo, 650f, 580f, 0.5f, 0.5f, 500f, 400f, 1f, 1f, 0f);
		font.draw(getBatch(), "Nueva Partida", 600, 400);
		font.draw(getBatch(), "Continuar Partida", 910, 400);
		font.draw(getBatch(), "Opciones", 650, 200);
		font.draw(getBatch(), "Instrucciones", 950, 200);
		getBatch().end();
		
		if(Gdx.input.isTouched() && (Gdx.input.getX() >= 600 && Gdx.input.getX() <= 870) && (Gdx.input.getY() >= 600 && Gdx.input.getY() <= 640)) {
			LevelCreator constructor = new LevelCreator();
			LevelBuilder builder = new LevelBuilder();
			
			constructor.createLevel1(builder, getGame());
			Level level1 = builder.getLevel();
			
			getGame().setScreen(level1);
			dispose();
		}
		if(Gdx.input.isTouched() && (Gdx.input.getX() >= 950 && Gdx.input.getX() <= 1210) && (Gdx.input.getY() >= 800 && Gdx.input.getY() <= 835)) {
			//getGame().setScreen(new Instrucciones(getGame()));
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void dispose() {
		this.menuMusic.dispose();
	}
	
}
