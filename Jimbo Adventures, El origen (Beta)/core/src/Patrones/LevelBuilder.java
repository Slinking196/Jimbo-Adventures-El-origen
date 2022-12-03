package Patrones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ClanSombra;
import com.mygdx.game.Consumibles;
import com.mygdx.game.JimboAdventures;
import com.mygdx.game.Snake;
import com.mygdx.game.Superficies;
import com.mygdx.game.TorretasLevel;

import Utils.LevelType;

public class LevelBuilder implements Builder {
	private JimboAdventures game;
	private Music levelMusic;
	private World world;
	private Jimbo jimbo;
	private LevelType lvlType;
	private Consumibles consu;
	private ClanSombra clan;
	private Superficies sup;
	private TorretasLevel torretas;
	private TextureRegion fondo;
	
	@Override
	public void setJimbo(Jimbo jimbo, float x, float y, int health, int balas) {
		this.jimbo = jimbo;
		this.jimbo.createBody();
		this.jimbo.setInitPos(x, y);
		this.jimbo.setBalas(balas);
		this.jimbo.setVidas(health);
	}
	
	@Override
	public void setLevelType(LevelType lvlType) {
		this.lvlType = lvlType;
		
	}
	
	@Override
	public void setMusic(String name) {
		levelMusic = Gdx.audio.newMusic(Gdx.files.internal(name));
		levelMusic.setLooping(true);
		levelMusic.setVolume(0.5f);
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
	
	@Override
	public void setSnake(Snake snake) {
		// TODO Auto-generated method stub
		
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
		return new Level(game, world, jimbo, null, levelMusic, consu, clan, sup, lvlType, torretas, fondo);
	}

}
