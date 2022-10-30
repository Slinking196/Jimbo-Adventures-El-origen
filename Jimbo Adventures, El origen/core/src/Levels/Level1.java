package Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Caballero;
import com.mygdx.game.ClanSombra;
import com.mygdx.game.Consumibles;
import com.mygdx.game.Item;
import com.mygdx.game.JimboAdventures;
import com.mygdx.game.Player;
import com.mygdx.game.Superficies;

import Utils.Screens;

public class Level1 extends Screens {
	private World world1;
	private Box2DDebugRenderer renderer;
	private Player jimbo;
	private ClanSombra clan;
	private Superficies sup;
	private Consumibles consu;
	
	public Level1(JimboAdventures game, World world, Superficies sup, ClanSombra clan, Consumibles consu) {
		super(game);
		this.consu = consu;
		this.clan = clan;
		this.sup = sup;
		world1 = world;
		jimbo = new Player(new Texture("Parado.png"), world1);
		
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
		clan.draw(getBatch());
		jimbo.draw(getBatch());
		getBatch().end();
		
		renderer.render(world1, getCamBox2D().combined);
		
	}

	@Override
	public void update(float delta) {
		world1.step(delta, 8, 6);
		clan.update(delta, world1);
		jimbo.update(delta, world1);
	}

}
