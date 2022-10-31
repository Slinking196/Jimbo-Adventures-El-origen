package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Tail extends GameObjects{
	private TextureRegion tailImg;
	private float width = .5f;
	private float height = .5f;
	private Vector2 pos;
	private Body cuerpoTail;
	private Fixture fixture;
	private boolean herido = false; 
	
	public Tail(float x, float y, Texture tx, World world) {
		tailImg = new TextureRegion(tx);
		pos = new Vector2();
		pos.x = x;
		pos.y = y;
		createBody(x, y, world);
	}
	
	public void createBody(float x, float y, World world) {
		BodyDef tailDef = new BodyDef();
		tailDef.position.set(x, y);
		tailDef.type = BodyType.DynamicBody;
		tailDef.gravityScale = 0;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2, height/2);
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		
		cuerpoTail = world.createBody(tailDef);
		cuerpoTail.setUserData(getUserData());
		fixture = cuerpoTail.createFixture(fixDef);
		shape.dispose();
	}
	
	public Tail getUserData() {
		return this;
	}
	
	public void cambiarObjeto(int i){
		cuerpoTail.setUserData(i);
	}
	
	public void cambiarObjeto(){
		cuerpoTail.setUserData(this);
	}
	
	public void deleteTail() {
		cuerpoTail.destroyFixture(fixture);
	}
	
	public boolean isColisionWall(World world) {
		Array<Contact> contacts = world.getContactList();
		for(Contact contact: contacts) {
			Fixture a = contact.getFixtureA();
			Fixture b = contact.getFixtureB();
			if(a.getBody().getUserData() instanceof Integer) {
				if(b.getBody().getType() == BodyType.StaticBody) {
					return true;
				}	
			}
			else if(b.getBody().getUserData() instanceof Integer) {
				if(a.getBody().getType() == BodyType.StaticBody) {
					return true;
				}	
			}
		}
		return false;
		
	}
	
	/**/
	
	public float getX() {
    	return pos.x;
    }
    
    public float getY() {
    	return pos.y;
    }
    
    public void hit() {
    	herido = true;
    }
    
    public void setHerido(boolean estado) {
    	herido = estado;
    }
    
    public boolean isHerido() {
    	return herido;
    }

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(tailImg, pos.x - width / 2, pos.y - height / 2, width / 2, height / 2, width, height, 1, 1, 0);
	}

}
