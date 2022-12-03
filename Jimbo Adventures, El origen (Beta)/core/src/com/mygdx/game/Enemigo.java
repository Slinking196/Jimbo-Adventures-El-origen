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
	public void movimientoHorizontal(Body body, float delta, float accelX) {
		Vector2 velocidad = body.getLinearVelocity();
		
		if(accelX == -1) {
			velocidad.x = -1 * getWalkSpeed();
		}
		else if(accelX == 1) {
			velocidad.x = getWalkSpeed();
		}
		
		this.accelX = accelX;
	}

	@Override
	public void movimientoVertical(Body body, float delta, float accelX) {}
	
	public float getAccelX() {
		return accelX;
	}

	@Override
	public void viewLeft() {}

	@Override
	public void viewRight() {}

	@Override
	public void viewUp() {}

	@Override
	public void viewDown() {}

	@Override
	public void jump() {}

	@Override
	public boolean getViewUp() {return false;}
	@Override
	public boolean getViewLeft() {return false;}

	@Override
	public boolean getViewRight() {return false;}

	@Override
	public boolean getViewDown() {return false;}

	@Override
	public Vector2 getInitPos() {return null;}
}
