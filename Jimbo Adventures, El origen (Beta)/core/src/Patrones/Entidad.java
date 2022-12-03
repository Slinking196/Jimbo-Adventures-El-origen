package Patrones;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entidad {
	private float WIDTH = 0.2f;
	private float HEIGHT = 0.2f;
	private float DRAW_WIDTH = 0.4f;
	private float DRAW_HEIGHT = 0.4f;
	private float WALK_SPEED = 3f;
	private float JUMP_SPEED = 8.0f;
	
	private boolean isJump = false;
	private boolean isFalling = false;
	private boolean isWalking;
	
	private boolean didJump;
	
	private float stateTime = 0;
	
	private Vector2 pos;
	private Vector2 velocidad;	
	
	public void update(Body body, float delta, float accelX) {
		pos = body.getPosition();
		velocidad = body.getLinearVelocity();
		
		if(didJump) {
			didJump = false;
			isJump = true;
			stateTime = 0;
			velocidad.y = JUMP_SPEED;
		}
		
		if(isJump || isFalling) {
			movimientoVertical(body, delta, accelX); 
		}
		movimientoHorizontal(body, delta, accelX);
		
		body.setLinearVelocity(velocidad);
		stateTime += delta; 
	}
	
	public abstract void movimientoHorizontal(Body body, float delta, float accelX);
	
	public abstract void movimientoVertical(Body body, float delta, float accelX);
	
	public abstract void viewLeft();

	public abstract void viewRight();

	public abstract void viewUp();

	public abstract void viewDown();

	public abstract void jump();

	public abstract boolean getViewUp();

	public abstract boolean getViewLeft();

	public abstract boolean getViewRight();

	public abstract boolean getViewDown();

	public abstract Vector2 getInitPos();
	
	public void setIsWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}
	
	public void isFalling(boolean isFalling) {
		this.isFalling = isFalling;
		isJump = false;
	}
	
	public void setIsJump(boolean isJump) {
		this.isJump = isJump;
	}
	
	public void setDidJump(boolean didJump) {
		this.didJump = didJump;
	}
	
	public boolean getIsWalking() {
		return isWalking;
	}
	
	public boolean getIsFalling() {
		return isFalling;
	}
	
	public boolean getIsJump() {
		return isJump;
	}
	
	public boolean isJump() {
		if(isJump || isFalling) {
			return true;
		}
		
		return false;
	}
	
	public boolean getDidJump() {
		return didJump;
	}
	
	public void setVelocidad(Vector2 velocidad) {
		this.velocidad = velocidad;
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	public Vector2 getVelocidad() {
		return velocidad;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public float getWalkSpeed() {
		return WALK_SPEED;
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

	public abstract float getAccelX();
}
