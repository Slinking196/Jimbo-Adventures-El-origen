package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Item {
	private boolean hit = false;
	private float width;
	private float height;
	private int tipo;
	private Body item;
	private TextureRegion itemImg;
	private BodyDef itemDef;
	private PolygonShape shape;
	private FixtureDef fixDef;
	
	public Item(Texture img, int tipo, float x, float y, float width, float height) {
		this.tipo = tipo;
		this.width = width;
		this.height = height;
		itemImg = new TextureRegion(img);
		
		createItem(x, y);
	}
	
	private void createItem(float x, float y) {
		itemDef = new BodyDef();
		itemDef.position.set(x, y);
		itemDef.type = BodyType.StaticBody;
		
		shape = new PolygonShape();
		shape.setAsBox(width / 2.0f, height / 2.0f);
		
		fixDef = new FixtureDef();
		fixDef.shape = shape;
	}
	
	public void setItemWorld(World world) {
		item = world.createBody(itemDef);
		item.createFixture(fixDef);
		item.setUserData(this);
		
		shape.dispose();
	}
	
	public void draw(SpriteBatch batch) {
		Vector2 pos = item.getPosition();
		
		batch.draw(itemImg, pos.x - width / 2.0f, pos.y - height / 2.0f, width / 2.0f, height / 2.0f, width, height, 1, 1, 0);
	}
	
	public void hit() {
		hit = true;
	}
	
	public boolean getHit() {
		return hit;
	}
	
	public int getTipo() {
		return tipo;
	}
}
