package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import Patrones.Jimbo;

public class Collision implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Body bodyA = contact.getFixtureA().getBody();
		Body bodyB = contact.getFixtureB().getBody();
		
		if(bodyA.getUserData() instanceof Jimbo && bodyB.getUserData() instanceof Obstaculo) {
			Obstaculo obs = (Obstaculo) bodyB.getUserData();
			Jimbo jimbo = (Jimbo) bodyA.getUserData();
			
			if(obs.getTipo() == 2) {	
				jimbo.setHit(true);
			}
		}
		
		if(bodyA.getUserData() instanceof Jimbo && bodyB.getUserData() instanceof Torreta) {
			Jimbo jimbo = (Jimbo) bodyA.getUserData();
			
			jimbo.setHit(true);
		}
		
		if(bodyA.getUserData() instanceof Obstaculo && bodyB.getUserData() instanceof Bullet) {
			Bullet bull = (Bullet) bodyB.getUserData();
			
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Caballero) {
			Bullet bull = (Bullet) bodyB.getUserData();
			Caballero enemigo = (Caballero) bodyA.getUserData();
			
			enemigo.hit();
			bull.setHit(true);
		}
		
		if(bodyA.getUserData() instanceof Jimbo && bodyB.getUserData() instanceof Item) {
			Item item = (Item) bodyB.getUserData();
			
			item.hit();
		}
		
		if(bodyA.getUserData() instanceof Jimbo && bodyB.getUserData() instanceof Caballero) {
			Jimbo jimbo = (Jimbo) bodyA.getUserData();
			
			jimbo.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Jimbo) {
			Bullet bull = (Bullet) bodyB.getUserData();
			Jimbo jimbo = (Jimbo) bodyA.getUserData();
			
			jimbo.setHit(true);
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Bullet) {
			Bullet bull1 = (Bullet) bodyB.getUserData();
			Bullet bull2 = (Bullet) bodyB.getUserData();
			
			bull1.setHit(true);
			bull2.setHit(true);
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
