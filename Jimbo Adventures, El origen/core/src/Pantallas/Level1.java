package Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Bullet;
import com.mygdx.game.Caballero;
import com.mygdx.game.ClanSombra;
import com.mygdx.game.Collision;
import com.mygdx.game.Consumibles;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Jimbo;
import com.mygdx.game.JimboAdventures;
import com.mygdx.game.Snake;
import com.mygdx.game.Superficies;

import com.mygdx.game.TorretasLevel;

import Utils.Screens;

public class Level1 extends Screens {
	private World world1;
	private Box2DDebugRenderer renderer;
	private Jimbo jimbo;
	private ClanSombra clan;
	private Superficies sup;
	private Consumibles consu;
	private TorretasLevel torrlvl;
	private Snake snk;
	
	public Level1(JimboAdventures game, World world, Superficies sup, ClanSombra clan, Consumibles consu, TorretasLevel torrlvl) {
		super(game);
		this.consu = consu;
		this.clan = clan;
		this.sup = sup;
		this.torrlvl = torrlvl;
		snk = new Snake(2,2, new Texture(Gdx.files.internal("Snake.png")), world);
		world1 = world;
		world.setContactListener(new Collision());
		jimbo = new Jimbo(new Texture("Parado.png"), world1);
		
		renderer = new Box2DDebugRenderer();
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
		consu.draw(getBatch());
		torrlvl.draw(getBatch());
		clan.draw(getBatch());
		snk.draw(getBatch());
		if(!jimbo.isDead()) jimbo.draw(getBatch());
		getBatch().end();
		
		renderer.render(world1, getCamBox2D().combined);
		
	}

	@Override
	public void update(float delta) {
		Array<Body> bodies = new Array<Body>();
		
		world1.step(delta, 8, 6);
		world1.getBodies(bodies);
		
		for(Body body: bodies) {
			if(world1.isLocked()) continue;
			if(body.getUserData() instanceof Jimbo) {
				Jimbo jimbo = (Jimbo) body.getUserData();
				if(jimbo.getHit()) {
					jimbo.dead();
					bodies.removeValue(body, true);
					world1.destroyBody(body);
				}
			}
			if(body.getUserData() instanceof Bullet) {
				Bullet bull = (Bullet) body.getUserData();
				if(bull.getHit()) {
					jimbo.removeBullet(bull);
					bodies.removeValue(body, true);
					world1.destroyBody(body);
				}
			}
			if(body.getUserData() instanceof Caballero) {
				Caballero enemy = (Caballero) body.getUserData();
				if(enemy.getHit()) {
					clan.removeGuardian(enemy);
					bodies.removeValue(body, true);
					world1.destroyBody(body);
				}
			}
		}
		
		clan.update(delta, world1);
		torrlvl.update(delta, world1);
		
		if(!jimbo.isDead()) jimbo.update(delta, world1);
	}

}
