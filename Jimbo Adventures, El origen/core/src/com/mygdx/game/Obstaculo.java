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

public class Obstaculo implements Dibujable{
	private float width;
	private float height;
	private int tipo;
	private Body obstaculo;
	private TextureRegion obstaculoImg;
	
	public Obstaculo(Texture img, World world, int tipo, float x, float y, float width, float height) {
		obstaculoImg = new TextureRegion(img);
		this.tipo = tipo;
		this.width = width * 2;
		this.height = height * 2;
		
		createObstaculo(world, x, y, width, height);
	}
	
	private void createObstaculo(World world, float x, float y, float width, float hieght) {
		BodyDef obstaculoDef = new BodyDef();
		obstaculoDef.position.set(x, y);
		obstaculoDef.type = BodyType.StaticBody;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, hieght);
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		
		obstaculo = world.createBody(obstaculoDef);
		obstaculo.createFixture(fixDef);
		obstaculo.setUserData(this);
		
		shape.dispose();
	}
	
	public void draw(SpriteBatch batch) {
		Vector2 pos = obstaculo.getPosition();
		
		batch.draw(obstaculoImg, pos.x - width / 2, pos.y - height / 2, width / 2, height / 2, width, height, 1, 1, 0);
	}
	
	public int getTipo() {
		return tipo;
	}
}
