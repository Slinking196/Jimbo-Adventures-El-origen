package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class Consumibles implements Dibujable{
	private ArrayList<Item> items;
	
	public Consumibles() {
		items = new ArrayList<Item>();
	}
	
	public void agregarItem(Texture img, World world, int tipo, float x, float y, float width, float height) {
		Item newItem = new Item(img, world, tipo, x, y, width, height);
		
		items.add(newItem);
	}
	
	public void agregarItem(Item newItem) {
		items.add(newItem);
	}
	
	public void draw(SpriteBatch batch) {
		Item aux;
		
		for(int i = 0; i < items.size(); i++) {
			aux = items.get(i);
			aux.draw(batch);
		}
	}
}
