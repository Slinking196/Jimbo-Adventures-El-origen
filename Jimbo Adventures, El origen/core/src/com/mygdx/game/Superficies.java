package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class Superficies {
	private ArrayList<Obstaculo> obstaculos;
	
	public Superficies() {
		obstaculos = new ArrayList<Obstaculo>();
	}
	
	public void agregarSuperficie(Texture img, int tipo, float x, float y, float width, float hieght) {
		Obstaculo newObstaculo = new Obstaculo(img, tipo, x, y, width, hieght);
		
		obstaculos.add(newObstaculo);
	}
	
	public void setSuperficiesWorld(World world) {
		Obstaculo aux;
		
		for(int i = 0; i < obstaculos.size(); i++) {
			aux = obstaculos.get(i);
			aux.setObstaculoWorld(world);
		}
	}
	
	public void agregarSuperficie(Obstaculo obs) {
		obstaculos.add(obs);
	}
	
	public void draw(SpriteBatch batch) {
		Obstaculo aux;
		
		for(int i = 0; i < obstaculos.size(); i++) {
			aux = obstaculos.get(i);
			aux.draw(batch);
		}
	}
}
