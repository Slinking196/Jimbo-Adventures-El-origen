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
import com.mygdx.game.Snake;
import com.mygdx.game.Superficies;
import com.mygdx.game.Tail;
import com.mygdx.game.TorretasLevel;
import com.mygdx.game.Bullet;

import Utils.Screens;

public class Level extends Screens {
	private World world;
	private Jimbo jimbo;
	private Snake snake;
	private Consumibles consu;
	private ClanSombra clan;
	private Superficies sup;
	private TorretasLevel torretas;
	private TextureRegion fondo;
	private Box2DDebugRenderer renderer;
	
	public Level(JimboAdventures game, World world, Jimbo jimbo, Snake snake, Consumibles consu, ClanSombra clan, Superficies sup,
			TorretasLevel torretas, TextureRegion fondo) {
		
		super(game);
		this.world = world;
		this.consu = consu;
		this.clan = clan;
		this.sup = sup;
		this.torretas = torretas;
		this.fondo = fondo;
		this.jimbo = jimbo;
		this.snake = snake;
		
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
		if(sup != null) sup.draw(getBatch());
		if(consu != null) consu.draw(getBatch());
		if(torretas != null)torretas.draw(getBatch());
		if(clan != null) clan.draw(getBatch());
		if(snake != null) snake.draw(getBatch());
		if(jimbo != null && !jimbo.isDead()) jimbo.draw(getBatch());
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
					if(torretas != null) torretas.removeBullet(bull);
					if(jimbo != null) jimbo.removeBullet(bull);
					bodies.removeValue(body, true);
					world.destroyBody(body);
				}
			}
			if(body.getUserData() instanceof Caballero) {
				Caballero enemy = (Caballero) body.getUserData();
				if(enemy.getHit()) {
					if(clan != null) clan.removeGuardian(enemy);
					bodies.removeValue(body, true);
					world.destroyBody(body);
				}
			}
			if(body.getUserData() instanceof Item) {
				Item item = (Item) body.getUserData();
				
				if(item.getHit()) {
					if(item.getTipo() == 1) {
						if(jimbo != null) jimbo.takeBullet();
					}
					if(item.getTipo() == 2) jimbo.drinkPotion();
					bodies.removeValue(body, true);
					world.destroyBody(body);
					consu.removeItem(item);
				}
			}
		}
		
		if(clan != null) clan.update(delta, world);
		if(torretas != null) torretas.update(delta, world);
		if(jimbo != null && !jimbo.isDead()) jimbo.update(delta, world);
	}
}
