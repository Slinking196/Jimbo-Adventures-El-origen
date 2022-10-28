package Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.JimboAdventures;

public abstract class Screens extends InputAdapter implements Screen {
	private static final float SCREEN_WIDTH = 1900.0f;
	private static final float SCREEN_HEIGTH = 1000.0f;
	private static final float WORLD_WIDTH = 15.0f;
	private static final float WORLD_HEIGTH = 10.5f;
	private static final float PX_TO_METERS = 80.0f;
	
	private JimboAdventures test;
	private OrthographicCamera camUI;
	private OrthographicCamera camBox2D;
	private SpriteBatch batch;
	private Stage stage;
	
	public Screens(JimboAdventures game) {
		this.test = game;
		
		stage =  new Stage(new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGTH));
		
		camUI = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGTH);
		camBox2D = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGTH);
		
		camUI.position.set(SCREEN_WIDTH / 2.0f, SCREEN_HEIGTH / 2.0f, 0);
		camBox2D.position.set(.49f, 4.8f, 0);
		//camBox2D.position.setZero();
		
		InputMultiplexer input = new InputMultiplexer(this, stage);
		Gdx.input.setInputProcessor(input);
		
		batch = new SpriteBatch();
	}
	
	public void render(float delta) {
		update(delta);
		
		stage.act(delta);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		draw(delta);
		stage.draw();
	}
	
	public void resize(int width, int heigth) {
		stage.getViewport().update(width, heigth, true);
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public OrthographicCamera getCamBox2D() {
		return camBox2D;
	}
	
	public OrthographicCamera getCamUI() {
		return camUI;
	}
	
	public void dispose() {
		
	}
	
	public abstract void draw(float delta);
	
	public abstract void update(float delta); 
	
	public float getPxToMeters() {
		return PX_TO_METERS;
	}
}