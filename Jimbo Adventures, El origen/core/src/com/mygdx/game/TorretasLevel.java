package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class TorretasLevel {
	private ArrayList<Torreta> torretas;
	
	public TorretasLevel() {
		torretas = new ArrayList<Torreta>();
	}
	
	public void agregarTorreta(Texture img, World world, float x, float y, float width, float height, float coolDown, String view) {
		Torreta newTorreta = new Torreta(img, world, x, y, width, height, coolDown, view);
		
		torretas.add(newTorreta);
	}
	
	public void agregarTorreta(Torreta newTorreta) {
		torretas.add(newTorreta);
	}
	
	public void update(float delta, World world) {
		Torreta aux;
		 
		for(int i = 0; i < torretas.size(); i++) {
			aux = torretas.get(i);
			aux.update(delta, world);
		}
	}
	
	public void draw(SpriteBatch batch) {
		Torreta aux;
		 
		for(int i = 0; i < torretas.size(); i++) {
			aux = torretas.get(i);
			aux.draw(batch);
		}
	}
}
