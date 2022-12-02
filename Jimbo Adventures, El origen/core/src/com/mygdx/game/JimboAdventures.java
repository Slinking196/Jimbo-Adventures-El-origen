package com.mygdx.game;

import com.badlogic.gdx.Game;

import Patrones.Level;
import Patrones.LevelBuilder;
import Patrones.LevelCreator;

public class JimboAdventures extends Game {
	
	@Override
	public void create() {
		LevelCreator creator = new LevelCreator();
		LevelBuilder builder = new LevelBuilder();
		
		creator.createLevel1(builder, this);
		
		Level level1 = builder.getLevel();
		
		setScreen(level1);
	}
}
