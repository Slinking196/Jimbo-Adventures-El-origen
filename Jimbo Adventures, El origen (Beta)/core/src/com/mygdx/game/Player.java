package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import Patrones.Entidad;

public class Player extends Entidad {
	private float JUMP_SPEED = 6.0f;
	
	private boolean isJump;
	private boolean isFalling;
	private boolean isWalking;
	
	private boolean viewUp = false;
	private boolean viewDown = false;
	private boolean viewLeft = false;
	private boolean viewRight = true;
	
	private boolean didJump;
	
	private float stateTime = 0;
	
	private Vector2 initPos;
	
	public Player(float x, float y) {
		setPos(new Vector2(x, y));
		initPos = new Vector2(x, y);
	}
	
	@Override
	public void update(Body body, float delta, float accelX) {
		Vector2 velocidad;
		
		setPos(body.getPosition());
		setVelocidad(body.getLinearVelocity());
		
		velocidad = getVelocidad();
		
		if(didJump) {
			didJump = false;
			isJump = true;
			stateTime = 0;
			velocidad.y = JUMP_SPEED;
		}
		
		if(accelX == -1) {
			velocidad.x = -1* getWalkSpeed();
			isWalking = !isJump && !isFalling;
		}
		else if(accelX == 1) {
			velocidad.x = getWalkSpeed();
			isWalking = !isJump && !isFalling;
		} 
		else {
			velocidad.x = 0;
			isWalking = false;
		}
		
		if(isJump) {
			if(velocidad.y <= 0) {
				isJump = false;
				isFalling = true;
				stateTime = 0;
			}
		} 
		else if(isFalling) {
			if(velocidad.y >= 0) {
				isFalling = false;
				stateTime = 0;
			}
		}
		
		body.setLinearVelocity(velocidad);
		stateTime += delta; 
		
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
		if(!isJump && !isFalling) {
			didJump = true;
		}
	}
}
