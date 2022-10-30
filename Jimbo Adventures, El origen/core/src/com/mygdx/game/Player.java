package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class Player {
	private float lastBullet = 0;
	private Arsenal balas;
	private Jimbo player;
	private Body jimboBody;
	private TextureRegion jimboImg;
	
	public Player(Texture img, World world) {
		jimboImg = new TextureRegion(img);
		player = new Jimbo(0.2f,0.6f);
		balas = new Arsenal();
		createPlayer(world);
	}
	
	private void createPlayer(World world) {
		BodyDef jimboDef = new BodyDef();
		jimboDef.position.set(player.getPosX(), player.getPosY());
		jimboDef.type = BodyType.DynamicBody;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(player.getWidth(), player.getHeight());
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.density = 15f;
		
		jimboBody = world.createBody(jimboDef);
		jimboBody.createFixture(fixDef);
		
		shape.dispose();
	}
	
	public void update(float delta, World world) {
		float accelX = 0;
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.viewLeft();
			accelX = -1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.viewRight();
			accelX = 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) player.viewUp();
		if(Gdx.input.isKeyPressed(Input.Keys.S)) player.viewDown();
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) player.jump();
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) balas.agregarBala(new Texture("Bala.png"), world, jimboBody.getPosition().x, jimboBody.getPosition().y,
																			player.getViewRight(), player.getViewLeft(), player.getViewUp(), player.getViewDown());
		
		player.update(jimboBody, delta, accelX);
	}
	
	
	public void draw(SpriteBatch batch) {
		Vector2 pos = jimboBody.getPosition();
		
		balas.draw(batch);
		batch.draw(jimboImg, pos.x - 0.2f, pos.y - 0.2f, .2f, 0.2f, player.getDrawWidth(), player.getDrawHeigth(), 1, 1, 0);
	}
 }