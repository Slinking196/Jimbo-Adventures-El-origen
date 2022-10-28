package Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.JimboAdventures;
import com.mygdx.game.Obstaculo;
import com.mygdx.game.Player;
import com.mygdx.game.Superficies;

import Utils.Screens;

public class Level1 extends Screens {
	private World world1;
	private Box2DDebugRenderer renderer;
	private Player jimbo;
	private Superficies sup;
	
	public Level1(JimboAdventures game, World world, Superficies sup) {
		super(game);
		this.sup = sup;
		world1 = world;
		jimbo = new Player(new Texture("Parado.png"), world1);
		renderer = new Box2DDebugRenderer();
		//createFloor(0 , 0, 7.0f, 0.5f);
		//createFloor(0, 2.f, .6f, .8f);
	}

	public void createFloor(float posX, float posY, float hx, float hy) {
		BodyDef jimboDef = new BodyDef();
		jimboDef.position.set(posX, posY);
		jimboDef.type = BodyType.StaticBody;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(hx, hy);
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.density = 15;
		
		Body jimbo = world1.createBody(jimboDef);
		jimbo.createFixture(fixDef);
		
		shape.dispose();
	}
	
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
		getCamBox2D().update();
		getBatch().setProjectionMatrix(getCamBox2D().combined);
		
		getBatch().begin();
		sup.draw(getBatch());
		jimbo.draw(getBatch());
		getBatch().end();
		
		renderer.render(world1, getCamBox2D().combined);
		
	}

	@Override
	public void update(float delta) {
		world1.step(delta, 8, 6);
		jimbo.update();
	}

}
