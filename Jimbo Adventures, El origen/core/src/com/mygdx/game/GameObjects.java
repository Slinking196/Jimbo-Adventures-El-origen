package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.World;

public abstract class GameObjects implements Dibujable{
	
	public abstract void createBody(float x, float y, World world);
	
}
