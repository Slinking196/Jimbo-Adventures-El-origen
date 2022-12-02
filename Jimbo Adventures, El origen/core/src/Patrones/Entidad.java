package Patrones;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entidad {
	private float WIDTH = 0.2f;
	private float HEIGHT = 0.2f;
	private float DRAW_WIDTH = 0.4f;
	private float DRAW_HEIGHT = 0.4f;
	private float WALK_SPEED = 3f;
	
	private Vector2 pos;
	private Vector2 velocidad;	
	
	public abstract void update(Body body, float delta, float accelX);
	
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
}
