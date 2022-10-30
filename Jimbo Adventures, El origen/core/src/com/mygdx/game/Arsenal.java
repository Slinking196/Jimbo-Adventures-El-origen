package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class Arsenal {
	private ArrayList<Bullet> balas;
	
	public Arsenal() {
		balas = new ArrayList<Bullet>();
	}
	
	public void agregarBala(Texture img, World world, float x, float y, boolean right, boolean left,  boolean up, boolean down) {
		Bullet newBullet = new Bullet(img, world, x, y, right, left, up, down);
		
		balas.add(newBullet);
	}
	
	public void agregarBala(Bullet newBullet) {
		balas.add(newBullet);
	}
	
	public void draw(SpriteBatch batch) {
		Bullet aux;
		
		for(int i = 0; i < balas.size(); i++) {
			aux = balas.get(i);
			aux.draw(batch);
		}
	}
}
