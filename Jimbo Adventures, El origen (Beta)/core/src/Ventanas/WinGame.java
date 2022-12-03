package Ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.JimboAdventures;

import Utils.Screens;

public class WinGame extends Screens{
	private BitmapFont font;
	private Music winGame;
	
	private TextureRegion fondo = new TextureRegion(new Texture("Victory.gif"));
	
	public WinGame(JimboAdventures game) {
		super(game);
		font = new BitmapFont();
		font.getData().setScale(3f);
		
		winGame = Gdx.audio.newMusic(Gdx.files.internal("Kirby Victory Dance.wav"));
		winGame.setVolume(0.2f);
		winGame.play();
	}
	
	@Override
	
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		
		getCamUI().update();
		getBatch().setProjectionMatrix(getCamUI().combined);
		
		getBatch().begin();
		getBatch().draw(fondo, 680f, 580f, 0.5f, 0.5f, 500f, 400f, 1f, 1f, 0f);
		font.draw(getBatch(), "Volver al Menú", 770f, 500f);
		getBatch().end();
		
		if(Gdx.input.isTouched() && (Gdx.input.getX() >= 770 && Gdx.input.getX() <= 1050) && (Gdx.input.getY() >= 500 && Gdx.input.getY() <= 535)) {
			getGame().setScreen(new MenuPrincipal(getGame()));
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
	public void dispose() {
		winGame.dispose();
		
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
