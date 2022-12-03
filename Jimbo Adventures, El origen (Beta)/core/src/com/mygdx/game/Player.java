package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import Patrones.Entidad;

public class Player extends Entidad {
	private boolean viewUp = false;
	private boolean viewDown = false;
	private boolean viewLeft = false;
	private boolean viewRight = true;
	
	private float stateTime = 0;
	
	private Vector2 initPos;
	
	public Player(float x, float y) {
		setPos(new Vector2(x, y));
		initPos = new Vector2(x, y);
	}
	
	@Override
	public void movimientoHorizontal(Body body, float delta, float accelX) {
		Vector2 velocidad = getVelocidad();
		
		if(accelX == -1) {
			velocidad.x = -1* getWalkSpeed();
			setIsWalking(!getIsJump() && !getIsFalling());
		}
		else if(accelX == 1) {
			velocidad.x = getWalkSpeed();
			setIsWalking(!getIsJump() && !getIsFalling());
		} 
		else {
			velocidad.x = 0;
			setIsWalking(false);
		}
	}

	@Override
	public void movimientoVertical(Body body, float delta, float accelX) {
		Vector2 velocidad = getVelocidad();
		
		if(getIsJump()) {
			if(velocidad.y <= 0) {
				isFalling(true);
			}
		} 
		else if(getIsFalling()) {
			if(velocidad.y >= 0) {
				isFalling(false);
				stateTime = 0;
			}
		}
	}
	
	public void viewDown() {
		viewDown = true;
		viewUp = false;
		viewLeft = false;
		viewRight = false;
	}
	
	public void viewUp() {
		viewDown = false;
		viewUp = true;
		viewLeft = false;
		viewRight = false;
	}
	
	public void viewRight() {
		viewDown = false;
		viewUp = false;
		viewLeft = false;
		viewRight = true;
	}
	
	public void viewLeft() {
		viewDown = false;
		viewUp = false;
		viewLeft = true;
		viewRight = false;
	}
	
	public Vector2 getInitPos() {
		return initPos;
	}
	
	public boolean getViewDown() {
		return viewDown;
	}
	
	public boolean getViewUp() {
		return viewUp;
	}
	
	public boolean getViewRight() {
		return viewRight;
	}
	
	public boolean getViewLeft() {
		return viewLeft;
	}
	
	public void jump() {
		if(!isJump()) {
			setDidJump(true);
		}
	}

	@Override
	public float getAccelX() {return 0;}
}
