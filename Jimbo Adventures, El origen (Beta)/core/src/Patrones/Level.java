package Patrones;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ClanSombra;
import com.mygdx.game.Consumibles;
import com.mygdx.game.JimboAdventures;
import com.mygdx.game.Superficies;
import com.mygdx.game.TorretasLevel;

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
		System.out.println("xd");
		this.world = world;
		this.consu = consu;
		this.clan = clan;
		this.sup = sup;
		this.torretas = torretas;
		this.fondo = fondo;
		this.jimbo = jimbo;
		
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
		world.step(delta, 8, 6);
		clan.update(delta, world);
		torretas.update(delta, world);
		if(!jimbo.isDead()) jimbo.update(delta, world);
	}
}
