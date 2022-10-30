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

public class Bullet {
	private final float WIDTH = 0.2f;
	private final float HEIGHT = 0.04f;
	private final float VELOCITY_MAX = 10.0f;
	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
	private Body bullet;
	private TextureRegion bulletImg;
	
	public Bullet(Texture img, World world, float x, float y, boolean right, boolean left, boolean up, boolean down) {
		bulletImg = new TextureRegion(img);
		this.right = right;
		this.left = left;
		this.up = up;
		this.down = down;
		createBullet(x, y, world);
	}
	
	private void createBullet(float x, float y, World world) {
		BodyDef bulletDef = new BodyDef();
		bulletDef.position.set(x, y);
		bulletDef.type = BodyType.DynamicBody;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(WIDTH / 2, HEIGHT / 2);
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
	
		bullet = world.createBody(bulletDef);
		bullet.setGravityScale(0);
		
		if(right) bullet.setLinearVelocity(VELOCITY_MAX, 0);
		if(left) bullet.setLinearVelocity(-VELOCITY_MAX, 0);
		if(down) bullet.setLinearVelocity(0, -VELOCITY_MAX);
		if(up) bullet.setLinearVelocity(0, VELOCITY_MAX);
		
		shape.dispose();
	}

	public void draw(SpriteBatch batch) {
		Vector2 pos = bullet.getPosition();
		
		if(right) batch.draw(bulletImg, pos.x - WIDTH / 2.0f, pos.y - HEIGHT / 2.0f, WIDTH / 2.0f, HEIGHT / 2.0f, WIDTH, HEIGHT, 1, 1, 0);
		if(up) batch.draw(bulletImg, pos.x - WIDTH / 2.0f, pos.y - HEIGHT / 2.0f, WIDTH / 2.0f, HEIGHT / 2.0f, WIDTH, HEIGHT, 1, 1, 90);
		if(left) batch.draw(bulletImg, pos.x - WIDTH / 2.0f, pos.y - HEIGHT / 2.0f, WIDTH / 2.0f, HEIGHT / 2.0f, WIDTH, HEIGHT, 1, 1, 180);
		if(down) batch.draw(bulletImg, pos.x - WIDTH / 2.0f, pos.y - HEIGHT / 2.0f, WIDTH / 2.0f, HEIGHT / 2.0f, WIDTH, HEIGHT, 1, 1, 270);
	}
}
