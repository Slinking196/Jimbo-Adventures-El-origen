package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Enemigo {
	private float WIDTH = 0.2f;
	private float HEIGHT = 0.2f;
	private float DRAW_WIDTH = 0.4f;
	private float DRAW_HEIGHT = 0.4f;
	private float WALK_SPEED = 3f;
	
	private float accelX = 1;
	private Vector2 pos;
	private Vector2 velocidad;
	
	public Enemigo(float x, float y, float accelX) {
		this.accelX = accelX;
		pos =  new Vector2(x, y);
	}
	
	public void update(Body body, float delta, float accelX) {
		pos = body.getPosition();
		velocidad = body.getLinearVelocity();
		
		if(accelX == -1) {
			velocidad.x = -WALK_SPEED;
		}
		else if(accelX == 1) {
			velocidad.x = WALK_SPEED;
		}
		
		this.accelX = accelX;
		body.setLinearVelocity(velocidad);
	}
	
	public float getAccelX() {
		return accelX;
	}
	
	public float getPosY() {
		return pos.y;
	}
	
	public float getPosX() {
		return pos.x;
	}
	
	public float getDrawHeigth() {
		return DRAW_HEIGHT;
	}
	public float getDrawWidth() {
		return DRAW_WIDTH;
	}
	public float getHeight() {
		return HEIGHT;
	}
	
	public float getWidth() {
		return WIDTH;
	}
}
