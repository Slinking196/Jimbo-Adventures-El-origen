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

public class FinalLevelBuilder implements Builder {
	private JimboAdventures game;
	private Music levelMusic;
	private World world;
	private Jimbo jimbo;
	private Snake snake;
	private LevelType lvlType;
	private Consumibles consu;
	private Superficies sup;
	private TextureRegion fondo;
	
	
	@Override
	public void setMusic(String name) {
		levelMusic = Gdx.audio.newMusic(Gdx.files.internal(name));
		levelMusic.setLooping(true);
		levelMusic.setVolume(0.2f);
		
	}

	@Override
	public void setLevelType(LevelType lvlType) {
		this.lvlType = lvlType;	
	}
	
	@Override
	public void setConsumibles(Consumibles consu) {
		this.consu = consu;
		
	}

	@Override
	public void setClanSombra(ClanSombra clan) {
		
	}

	@Override
	public void setSuperficies(Superficies sup) {
		this.sup = sup;
		
	}

	@Override
	public void setTorretas(TorretasLevel torretas) {
		// TODO Auto-generated method stub
		
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
	public void setJimbo(Jimbo jimbo, float x, float y, int health, int balas) {
		this.jimbo = jimbo;
		this.jimbo.setInitPos(x, y);
		this.jimbo.setBalas(balas);
		this.jimbo.setVidas(health);
		this.jimbo.createBody();
	}

	@Override
	public void setGame(JimboAdventures game) {
		this.game = game;
		
	}

	@Override
	public void setSnake(Snake snake) {
		this.snake = snake;
	}
	
	public void setObjectsWorld() {
		jimbo.setPlayerWorld(world);
		consu.setItemsWorld(world);
		sup.setSuperficiesWorld(world);
	}
	
	public Level getLevel() {
		
		setObjectsWorld();
		return new Level(game, world, jimbo, snake, levelMusic, consu, null, sup, lvlType, null, fondo);
	}
}
