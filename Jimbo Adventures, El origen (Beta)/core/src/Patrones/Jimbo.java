package Patrones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.Arsenal;
import com.mygdx.game.Bullet;
import com.mygdx.game.Player;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Jimbo {
	private static Jimbo instance;
	private int health = 3;
	private int cantBalas = 200;
	private boolean hit;
	private boolean isDead = false;
	private Arsenal balas;
	private Player player;
	private Body jimboBody;
	private TextureRegion jimboImg;
	private BodyDef jimboDef;
	private PolygonShape shape;
	private FixtureDef fixDef;
	
	private Jimbo(Texture img, float x, float y) {
		jimboImg = new TextureRegion(img);
		player = new Player(x, y);
		balas = new Arsenal();
		createBody();
	}
	
	public static Jimbo getInstance(Texture img, float x, float y) {
		if(instance == null) {
			instance = new Jimbo(img, x, y);
		}
		
		return instance;
	}
	
	private void createBody() {
		jimboDef = new BodyDef();
		jimboDef.position.set(player.getPosX(), player.getPosY());
		jimboDef.type = BodyType.DynamicBody;
		
		shape = new PolygonShape();
		shape.setAsBox(player.getWidth(), player.getHeight());
		
		fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.density = 15f;
	}
	
	public void setPlayerWorld(World world) {
		jimboBody = world.createBody(jimboDef);
		jimboBody.createFixture(fixDef);
		jimboBody.setUserData(this);
		
		shape.dispose();
	}
	
	public void update(float delta, World world) {
		float accelX = 0;
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.viewLeft();
			accelX = -1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.viewRight();
			accelX = 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) player.viewUp();
		if(Gdx.input.isKeyPressed(Input.Keys.S)) player.viewDown();
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) player.jump();
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && cantBalas > 0) {
			balas.agregarBala(new Texture("Bala.png"), world, jimboBody.getPosition().x, jimboBody.getPosition().y, player.getWidth(), player.getHeight(),
								player.getViewRight(), player.getViewLeft(), player.getViewUp(), player.getViewDown());
			cantBalas--;
		}
		
		player.update(jimboBody, delta, accelX);
		jimboBody.setTransform(jimboBody.getPosition(), 0);
	}
	
	public void goToInitPos() {
		jimboBody.setTransform(player.getInitPos(), 0);
	}
	
	public void removeBullet(Bullet bala) {
		balas.removeBala(bala);
	}
	
	public void setInitPos(float x, float y) {
		player.setPos(new Vector2(x, y));
	}
	
	public void dead() {
		isDead = true;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
		
		if(hit) health--;
	}
	
	public void takeBullet() {
		cantBalas++;
	}
	
	public void drinkPotion() {
		health++;
	}
	
	public boolean getHit() {
		return hit;
	}
	
	public void draw(SpriteBatch batch) {
		Vector2 pos = jimboBody.getPosition();
		
		balas.draw(batch);
		batch.draw(jimboImg, pos.x - 0.2f, pos.y - 0.2f, .2f, 0.2f, player.getDrawWidth(), player.getDrawHeigth(), 1, 1, 0);
	}
}
