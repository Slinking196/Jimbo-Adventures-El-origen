package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Item extends GameObjects{
	private float width;
	private float height;
	private int tipo;
	private Body item;
	private TextureRegion itemImg;
	
	public Item(Texture img, World world, int tipo, float x, float y, float width, float height) {
		this.tipo = tipo;
		this.width = width;
		this.height = height;
		itemImg = new TextureRegion(img);
		
		createBody(x, y, world);
	}
	
	public void createBody(float x, float y, World world) {
		BodyDef obstaculoDef = new BodyDef();
		obstaculoDef.position.set(x, y);
		obstaculoDef.type = BodyType.StaticBody;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2.0f, height / 2.0f);
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		
		item = world.createBody(obstaculoDef);
		item.createFixture(fixDef);
		
		shape.dispose();
	}
	
	public void draw(SpriteBatch batch) {
		Vector2 pos = item.getPosition();
		
		batch.draw(itemImg, pos.x - width / 2.0f, pos.y - height / 2.0f, width / 2.0f, height / 2.0f, width, height, 1, 1, 0);
	}
	
	public int getTipo() {
		return tipo;
	}
}
