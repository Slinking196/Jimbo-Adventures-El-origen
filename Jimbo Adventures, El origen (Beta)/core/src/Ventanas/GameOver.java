package Ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.JimboAdventures;

import Patrones.Level;
import Patrones.LevelBuilder;
import Patrones.LevelCreator;
import Utils.Screens;

public class GameOver extends Screens {
	private BitmapFont font;
	private Music gameOver;
	
	private TextureRegion fondo = new TextureRegion(new Texture("GameOver.jpg"));
	
	public GameOver(JimboAdventures game) {
		super(game);
		font = new BitmapFont();
		font.getData().setScale(3f);
		
		gameOver = Gdx.audio.newMusic(Gdx.files.internal("Sadness and Sorrow-8 Bits.wav"));
		gameOver.setLooping(true);
		gameOver.setVolume(0.5f);
		gameOver.play();
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		
		getCamUI().update();
		getBatch().setProjectionMatrix(getCamUI().combined);
		
		getBatch().begin();
		getBatch().draw(fondo, 680f, 580f, 0.5f, 0.5f, 500f, 400f, 1f, 1f, 0f);
		font.draw(getBatch(), "Volver a intentar", 500, 400);
		font.draw(getBatch(), "Volver al Menú", 1100, 400);
		font.draw(getBatch(), "Salir", 900, 280);
		getBatch().end();
		
		if(Gdx.input.isTouched() && (Gdx.input.getX() >= 500 && Gdx.input.getX() <= 810) && (Gdx.input.getY() >= 600 && Gdx.input.getY() <= 635)) {
			LevelCreator constructor = new LevelCreator();
			LevelBuilder builder = new LevelBuilder();
			
			constructor.createLevel1(builder, getGame());
			Level level1 = builder.getLevel();
			
			getGame().setScreen(level1);
			dispose();
		}
	    if(Gdx.input.isTouched() && (Gdx.input.getX() >= 1090 && Gdx.input.getX() <= 1380) && (Gdx.input.getY() >= 600 && Gdx.input.getY() <= 635)) {
			getGame().setScreen(new MenuPrincipal(getGame()));
			dispose();
		}
 		if(Gdx.input.isTouched() && (Gdx.input.getX() >= 896 && Gdx.input.getX() <= 990) && (Gdx.input.getY() >= 715 && Gdx.input.getY() <= 755)) {
			getBatch().dispose();
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
	public void dispose() {
		gameOver.dispose();
	}

	@Override
	public void draw(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
