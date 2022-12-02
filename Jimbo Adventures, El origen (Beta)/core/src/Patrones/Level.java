package Patrones;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Caballero;
import com.mygdx.game.ClanSombra;
import com.mygdx.game.Collision;
import com.mygdx.game.Consumibles;
import com.mygdx.game.Item;
import com.mygdx.game.JimboAdventures;
import com.mygdx.game.Superficies;
import com.mygdx.game.TorretasLevel;
import com.mygdx.game.Bullet;

import Utils.Screens;

public class Level extends Screens {
	private World world;
	private Jimbo jimbo;
	private Consumibles consu;
	private ClanSombra clan;
	private Superficies sup;
	private TorretasLevel torretas;
	private TextureRegion fondo;
	private Box2DDebugRenderer renderer;
	
	public Level(JimboAdventures game, World world, Jimbo jimbo ,Consumibles consu, ClanSombra clan, Superficies sup,
			TorretasLevel torretas, TextureRegion fondo) {
		
		super(game);
		this.world = world;
		this.consu = consu;
		this.clan = clan;
		this.sup = sup;
		this.torretas = torretas;
		this.fondo = fondo;
		this.jimbo = jimbo;
		
		world.setContactListener(new Collision());
		renderer = new Box2DDebugRenderer();
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
		getCamBox2D().update();
		getBatch().setProjectionMatrix(getCamBox2D().combined);
		
		getBatch().begin();
		getBatch().draw(fondo, 0 - 7.0f, -1, 4.0f , 5.0f, 15.0f, 11.0f, 1, 1, 0);
		sup.draw(getBatch());
		consu.draw(getBatch());
		torretas.draw(getBatch());
		clan.draw(getBatch());
		if(!jimbo.isDead()) jimbo.draw(getBatch());
		getBatch().end();
		
		renderer.render(world, getCamBox2D().combined);
		
	}

	@Override
	public void update(float delta) {
		Array<Body> bodies = new Array<Body>();
		
		world.step(delta, 8, 6);
		world.getBodies(bodies);
		
		for(Body body: bodies) {
			if(world.isLocked()) continue;
			if(body.getUserData() instanceof Jimbo) {
				Jimbo jimbo = (Jimbo) body.getUserData();
				if(jimbo.getHit()) {
					if(jimbo.getHealth() == 0) {
						jimbo.dead();
						bodies.removeValue(body, true);
						world.destroyBody(body);
					}
					jimbo.goToInitPos();
					jimbo.setHit(false);
				}
			}
			if(body.getUserData() instanceof Bullet) {
				Bullet bull = (Bullet) body.getUserData();
				if(bull.getHit()) {
					torretas.removeBullet(bull);
					jimbo.removeBullet(bull);
					bodies.removeValue(body, true);
					world.destroyBody(body);
				}
			}
			if(body.getUserData() instanceof Caballero) {
				Caballero enemy = (Caballero) body.getUserData();
				if(enemy.getHit()) {
					clan.removeGuardian(enemy);
					bodies.removeValue(body, true);
					world.destroyBody(body);
				}
			}
			if(body.getUserData() instanceof Item) {
				Item item = (Item) body.getUserData();
				
				if(item.getHit()) {
					if(item.getTipo() == 1) {
						System.out.println("xd");
						jimbo.takeBullet();
					}
					if(item.getTipo() == 2) jimbo.drinkPotion();
					bodies.removeValue(body, true);
					world.destroyBody(body);
					consu.removeItem(item);
				}
			}
		}
		
		clan.update(delta, world);
		torretas.update(delta, world);
		if(!jimbo.isDead()) jimbo.update(delta, world);
	}
}
