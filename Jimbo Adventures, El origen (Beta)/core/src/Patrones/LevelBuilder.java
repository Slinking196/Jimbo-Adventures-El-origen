package Patrones;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ClanSombra;
import com.mygdx.game.Consumibles;
import com.mygdx.game.JimboAdventures;
import com.mygdx.game.Superficies;
import com.mygdx.game.TorretasLevel;

public class LevelBuilder implements Builder {
	private JimboAdventures game;
	private World world;
	private Jimbo jimbo;
	private Consumibles consu;
	private ClanSombra clan;
	private Superficies sup;
	private TorretasLevel torretas;
	private TextureRegion fondo;
	
	@Override
	public void setJimbo(Jimbo jimbo, float x, float y) {
		this.jimbo = jimbo;
		this.jimbo.setInitPos(x, y);
	}
	
	@Override
	public void setConsumibles(Consumibles consu) {
		this.consu = consu;
	}

	@Override
	public void setClanSombra(ClanSombra clan) {
		this.clan = clan;
	}

	@Override
	public void setSuperficies(Superficies sup) {
		this.sup = sup;
	}

	@Override
	public void setTorretas(TorretasLevel torretas) {
		this.torretas = torretas;
	}

	@Override
	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public void setFondo(Texture img) {
		this.fondo = new TextureRegion(img);
		
	}
	
	@Override
	public void setGame(JimboAdventures game) {
		this.game = game;
	}
	
	public void setObjectsWorld() {
		jimbo.setPlayerWorld(world);
		consu.setItemsWorld(world);
		clan.setCaballerosWorld(world);
		sup.setSuperficiesWorld(world);
		torretas.setTorretasWorld(world);
	}
	
	public Level getLevel() {
		
		setObjectsWorld();
		return new Level(game, world, jimbo, consu, clan, sup, torretas, fondo);
	}

}
