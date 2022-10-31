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
	
	public void removeBala(Bullet bala) {
		balas.remove(bala);
	}
	
	public void draw(SpriteBatch batch) {
		Bullet aux;
		
		for(int i = 0; i < balas.size(); i++) {
			aux = balas.get(i);
			aux.draw(batch);
		}
	}

	public void agregarBala(Texture img, World world, float x, float y, float width, float height,
			boolean viewRight, boolean viewLeft, boolean viewUp, boolean viewDown) {
		Bullet newBullet;
		
		if(viewRight) newBullet = new Bullet(img, world, x + width + 0.1f, y + height / 2.0f, viewRight, viewLeft, viewUp, viewDown);
		else if(viewLeft) newBullet = new Bullet(img, world, x - width / 2.0f - 0.1f, y, viewRight, viewLeft, viewUp, viewDown);
		else if(viewUp) newBullet = new Bullet(img, world, x, y + height + 0.1f, viewRight, viewLeft, viewUp, viewDown);
		else newBullet = new Bullet(img, world, x, y - 0.1f, viewRight, viewLeft, viewUp, viewDown);
		
		balas.add(newBullet);
		
	}
}
