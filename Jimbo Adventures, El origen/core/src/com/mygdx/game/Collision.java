package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Collision implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Body bodyA = contact.getFixtureA().getBody();
		Body bodyB = contact.getFixtureB().getBody();
		
		if(bodyA.getUserData() instanceof Obstaculo && bodyB.getUserData() instanceof Jimbo) {
			Obstaculo obs = (Obstaculo) bodyA.getUserData();
			Jimbo jimbo = (Jimbo) bodyB.getUserData();
			
			if(obs.getTipo() == 2) {	
				jimbo.setHit(true);
			}
		}
		if(bodyA.getUserData() instanceof Torreta && bodyB.getUserData() instanceof Jimbo) {
			Jimbo jimbo = (Jimbo) bodyB.getUserData();
			
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
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Tail) {
			Bullet bull = (Bullet) bodyB.getUserData();
			Tail tail = (Tail) bodyA.getUserData();
			
			tail.hit();
			bull.setHit(true);
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
