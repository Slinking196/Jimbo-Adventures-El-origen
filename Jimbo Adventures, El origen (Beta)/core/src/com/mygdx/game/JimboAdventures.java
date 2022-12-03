package com.mygdx.game;

import com.badlogic.gdx.Game;

import Patrones.FinalLevelBuilder;
import Patrones.Level;
import Patrones.LevelBuilder;
import Patrones.LevelCreator;

public class JimboAdventures extends Game {
	
	@Override
	public void create() {
		LevelCreator creator = new LevelCreator();
		FinalLevelBuilder builder = new FinalLevelBuilder();
		
		creator.createFinalLevel(builder, this);
		
		Level level1 = builder.getLevel();
		
		setScreen(level1);
	}
}
