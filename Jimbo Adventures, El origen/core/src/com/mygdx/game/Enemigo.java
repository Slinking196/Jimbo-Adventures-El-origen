package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import Patrones.Entidad;

public class Enemigo extends Entidad {
	private float accelX = 1;
	
	public Enemigo(float x, float y, float accelX) {
		this.accelX = accelX;
		setPos(new Vector2(x, y));
	}
	
	@Override
	public void update(Body body, float delta, float accelX) {
		Vector2 velocidad;
		
		setPos(body.getPosition());
		setVelocidad(body.getLinearVelocity());
		velocidad = getVelocidad();
		
		if(accelX == -1) {
			velocidad.x = -1 * getWalkSpeed();
		}
		else if(accelX == 1) {
			velocidad.x = getWalkSpeed();
		}
		
		this.accelX = accelX;
		body.setLinearVelocity(velocidad);
		
	}
	
	public float getAccelX() {
		return accelX;
	}
	
}
