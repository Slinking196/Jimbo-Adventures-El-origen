package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Player {
	private float WIDTH = 0.2f;
	private float HEIGHT = 0.2f;
	private float DRAW_WIDTH = 0.4f;
	private float DRAW_HEIGHT = 0.4f;
	private float WALK_SPEED = 3.0f;
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
	
	private Vector2 pos;
	private Vector2 velocidad;
	
	public Player(float x, float y) {
		pos =  new Vector2(x, y);
	}
	
	public void update(Body body, float delta, float accelX) {
		pos = body.getPosition();
		velocidad = body.getLinearVelocity();
		
		if(didJump) {
			didJump = false;
			isJump = true;
			stateTime = 0;
			velocidad.y = JUMP_SPEED;
		}
		
		if(accelX == -1) {
			velocidad.x = -WALK_SPEED;
			isWalking = !isJump && !isFalling;
		}
		else if(accelX == 1) {
			velocidad.x = WALK_SPEED;
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
	
	public void jump() {
		if(!isJump && !isFalling) {
			didJump = true;
		}
	}
}
