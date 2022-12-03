package Patrones;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
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
import com.mygdx.game.TorretasLevel;
import com.mygdx.game.Bullet;

import Utils.LevelType;
import Utils.Screens;
import Ventanas.GameOver;
import Ventanas.WinGame;

public class Level extends Screens {
	private World world;
	private Jimbo jimbo;
	private Snake snake;
	private Consumibles consu;
	private ClanSombra clan;
	private Superficies sup;
	private TorretasLevel torretas;
	private LevelType lvlType;
	
	private Music gameMusic;
	
	private TextureRegion fondo;
	private TextureRegion vida = new TextureRegion(new Texture("Corazon.png"));
	private TextureRegion vidaImg = new TextureRegion(new Texture("Vida.png")); 
	private TextureRegion pistolImg = new TextureRegion(new Texture("Five Seven.png")); 
	private TextureRegion balaImg =  new TextureRegion(new Texture("Bala.png"));
	private Box2DDebugRenderer renderer;
	
	public Level(JimboAdventures game, World world, Jimbo jimbo, Snake snake, Music gameMusic, Consumibles consu, 
			ClanSombra clan, Superficies sup, LevelType lvlType, TorretasLevel torretas, TextureRegion fondo) {
		
		super(game);
		this.world = world;
		this.consu = consu;
		this.clan = clan;
		this.sup = sup;
		this.torretas = torretas;
		this.fondo = fondo;
		this.jimbo = jimbo;
		this.snake = snake;
		this.gameMusic = gameMusic;
		this.lvlType = lvlType;
		this.gameMusic.play();
		this.jimbo.goToInitPos();
		
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
		float sepX = 0;
		float sepY = 0;
		
		getCamBox2D().update();
		getBatch().setProjectionMatrix(getCamBox2D().combined);
		
		getBatch().begin();
		getBatch().draw(fondo, 0 - 7.0f, -1, 4.0f , 5.0f, 15.0f, 11.0f, 1, 1, 0);
		if(sup != null) sup.draw(getBatch());
		if(consu != null) consu.draw(getBatch());
		if(torretas != null)torretas.draw(getBatch());
		if(clan != null) clan.draw(getBatch());
		if(snake != null) snake.draw(getBatch());
		if(jimbo != null && !jimbo.isDead()) {
			jimbo.draw(getBatch());
			
			getBatch().draw(vidaImg, 4.7f, 9.6f, 0.5f, 0.4f, 0.6f, 0.2f, 1f, 1f, 0);
			for(int i = 0; i < jimbo.getHealth(); i++) {
				getBatch().draw(vida, 5.5f + sepX, 9.5f - sepY, 0.5f, 0.5f, 0.35f, 0.35f, 1f, 1f, 0);
				sepX += 0.5f;
				
				if(5.5f + sepX > 7.5f) {
					sepX = 0;
					sepY += 0.5f;
				}
			}
			
			getBatch().draw(pistolImg, -7.0f, 9.6f, 0.5f, 0.4f, 0.6f, 0.4f, 1f, 1f, 0);
			
			sepX = 0;
			sepY = 0;
			for(int j = 0; j < jimbo.getCantBalas(); j++) {
				getBatch().draw(balaImg, -6.1f + sepX, 9.8f - sepY, 0.5f, 0.5f, 0.1f, 0.2f, 1f, 1f, 0);
				sepX += 0.25f;
				
				if(-6.1f + sepX > -4.0f) {
					sepX = 0;
					sepY += 0.3f;
				}
			}
		}
		getBatch().end();
		
		//renderer.render(world, getCamBox2D().combined);
		
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
						getGame().setScreen(new GameOver(getGame()));
						dispose();
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
					if(item.getTipo() == 3) {
						LevelCreator creator = new LevelCreator();
						
						if(lvlType == LevelType.LEVEL_1) {
							LevelBuilder builder = new LevelBuilder();
							
							creator.createLevel2(builder, getGame());
							Level lvl2 = builder.getLevel();
							getGame().setScreen(lvl2);
						}
						else if(lvlType == LevelType.LEVEL_2) {
							FinalLevelBuilder builder = new FinalLevelBuilder();
							
							creator.createFinalLevel(builder, getGame());
							Level finalLevel = builder.getLevel();
							getGame().setScreen(finalLevel);
						}
						
						dispose();
					}
					
					bodies.removeValue(body, true);
					world.destroyBody(body);
					consu.removeItem(item);
				}
			}
		}
		if(snake != null && snake.isDead()) {
			getGame().setScreen(new WinGame(getGame()));
			dispose();
		}
		
		if(clan != null) clan.update(delta, world);
		if(torretas != null) torretas.update(delta, world);
		if(jimbo != null && !jimbo.isDead()) jimbo.update(delta, world);
	}

	@Override
	public void dispose() {
		//world.dispose();
		gameMusic.dispose();
	}
}
