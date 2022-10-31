package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class ClanSombra {
	private ArrayList<Caballero> caballeros;
	
	public ClanSombra() {
		caballeros = new ArrayList<Caballero>();
	}
	
	public void agregarCaballero(Texture img, World world, float x, float y, float accelX, float limitInfX, float limitSupX) {
		Caballero newCaballero = new Caballero(img, world, x, y, accelX, limitInfX, limitSupX);
		
		caballeros.add(newCaballero);
	}
	
	public void agregarCaballero(Caballero newCaballero) {
		caballeros.add(newCaballero);
	}
	
	public void removeGuardian(Caballero cab) {
		caballeros.remove(cab);
	}
	
	public void update(float delta, World world) {
		Caballero aux;
		
		for(int i = 0; i < caballeros.size(); i++) {
			aux = caballeros.get(i);
			aux.update(delta, world);
		}
	}
	
	public void draw(SpriteBatch batch) {
		Caballero aux;
		
		for(int i = 0; i < caballeros.size(); i++) {
			aux = caballeros.get(i);
			aux.draw(batch);
		}
	}
}
