package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Torreta extends GameObjects{
	private float width;
	private float height;
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private float coolDown;
	private float lastBullet = 0;
	private Arsenal balas;
	private Body torreta;
	private TextureRegion torretaImg;
	
	public Torreta(Texture img, World world, float x, float y, float width, float height, float coolDown,String view) {
		this.width = width;
		this.height = height;
		this.coolDown = coolDown;
		torretaImg = new TextureRegion(img);
		balas = new Arsenal();
		
		if(view.equals("DOWN")) viewDown();
		if(view.equals("UP")) viewUp();
		if(view.equals("RIGHT")) viewRight();
		if(view.equals("LEFT")) viewLeft();
		
		createBody(x, y, world);
	}
	
	public void createBody( float x, float y, World world) {
		BodyDef torretaDef = new BodyDef();
		torretaDef.position.set(x, y);
		torretaDef.type = BodyType.StaticBody;
		
		PolygonShape shape = new PolygonShape();
		if (left || right) shape.setAsBox(width / 2.0f, height / 2.0f);
		if (down || up) shape.setAsBox(height / 2.0f, width / 2.0f);
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		//fixDef.density = 30.0f;
		
		torreta = world.createBody(torretaDef);
		torreta.createFixture(fixDef);
		torreta.setUserData(this);
		
		shape.dispose();
	}
	
	public void viewDown() {
		left = false;
		right = false;
		up = false;
		down = true;
	}
	
	public void viewUp() {
		left = false;
		right = false;
		up = true;
		down = false;
	}
	
	public void viewRight() {
		left = false;
		right = true;
		up = false;
		down = false;
	}
	
	public void viewLeft() {
		left = true;
		right = false;
		up = false;
		down = false;
	}
	
	public void update(float delta, World world) {
		Vector2 pos = torreta.getPosition();
		
		if(TimeUtils.nanoTime() - lastBullet > coolDown/*100000000*/) {
			balas.agregarBala(new Texture("Bala.png"), world, pos.x, pos.y, right, left, up, down);
			lastBullet = TimeUtils.nanoTime();
		}
	}
	
	public void draw(SpriteBatch batch) {
		Vector2 pos = torreta.getPosition();
		
		balas.draw(batch);
		if(left) batch.draw(torretaImg, pos.x - width / 2.0f, pos.y - height/ 2.0f, width / 2.0f, height / 2.0f, width, height, 1, 1, 0);
		if(down) batch.draw(torretaImg, pos.x - width / 2.0f, pos.y - height/ 2.0f, width / 2.0f, height / 2.0f, width, height, 1, 1, 90);
		if(right) batch.draw(torretaImg, pos.x - width / 2.0f, pos.y - height/ 2.0f, width / 2.0f, height / 2.0f, width, height, 1, 1, 180);
		if(up) batch.draw(torretaImg, pos.x - width / 2.0f, pos.y - height/ 2.0f, width / 2.0f, height / 2.0f, width, height, 1, 1, 270);
		
	}
}
