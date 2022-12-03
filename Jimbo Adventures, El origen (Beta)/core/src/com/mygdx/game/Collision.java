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
		
		if(bodyA.getUserData() instanceof Obstaculo && bodyB.getUserData() instanceof Jimbo) {
			Obstaculo obs = (Obstaculo) bodyA.getUserData();
			Jimbo jimbo = (Jimbo) bodyB.getUserData();
			
			if(obs.getTipo() == 2) {	
				jimbo.setHit(true);
			}
		}
		
		if(bodyA.getUserData() instanceof Jimbo && bodyB.getUserData() instanceof Torreta) {
			Jimbo jimbo = (Jimbo) bodyA.getUserData();
			
			jimbo.setHit(true);
		}
		
		if(bodyA.getUserData() instanceof Torreta && bodyB.getUserData() instanceof Jimbo) {
			Jimbo jimbo = (Jimbo) bodyB.getUserData();
			
			jimbo.setHit(true);
		}
		
		if(bodyA.getUserData() instanceof Obstaculo && bodyB.getUserData() instanceof Bullet) {
			Bullet bull = (Bullet) bodyB.getUserData();
			
			bull.setHit(true);
		}
		
		if(bodyA.getUserData() instanceof Bullet && bodyB.getUserData() instanceof Obstaculo) {
			Bullet bull = (Bullet) bodyA.getUserData();
			
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Caballero) {
			Bullet bull = (Bullet) bodyB.getUserData();
			Caballero enemigo = (Caballero) bodyA.getUserData();
			
			enemigo.hit();
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Caballero && bodyA.getUserData() instanceof Bullet) {
			Bullet bull = (Bullet) bodyA.getUserData();
			Caballero enemigo = (Caballero) bodyB.getUserData();
			
			enemigo.hit();
			bull.setHit(true);
		}
		
		if(bodyA.getUserData() instanceof Jimbo && bodyB.getUserData() instanceof Item) {
			Item item = (Item) bodyB.getUserData();
			
			item.hit();
		}
		
		if(bodyA.getUserData() instanceof Item && bodyB.getUserData() instanceof Jimbo) {
			Item item = (Item) bodyA.getUserData();
			
			item.hit();
		}
		
		if(bodyA.getUserData() instanceof Jimbo && bodyB.getUserData() instanceof Caballero) {
			Jimbo jimbo = (Jimbo) bodyA.getUserData();
			
			jimbo.setHit(true);
		}
		
		if(bodyA.getUserData() instanceof Caballero && bodyB.getUserData() instanceof Jimbo) {
			Jimbo jimbo = (Jimbo) bodyB.getUserData();
			
			jimbo.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Jimbo) {
			Bullet bull = (Bullet) bodyB.getUserData();
			Jimbo jimbo = (Jimbo) bodyA.getUserData();
			
			jimbo.setHit(true);
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Jimbo && bodyA.getUserData() instanceof Bullet) {
			Bullet bull = (Bullet) bodyA.getUserData();
			Jimbo jimbo = (Jimbo) bodyB.getUserData();
			
			jimbo.setHit(true);
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Bullet) {
			Bullet bull1 = (Bullet) bodyA.getUserData();
			Bullet bull2 = (Bullet) bodyB.getUserData();
			
			bull1.setHit(true);
			bull2.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Item) {
			Bullet bull = (Bullet) bodyB.getUserData();
			
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Item && bodyA.getUserData() instanceof Bullet) {
			Bullet bull = (Bullet) bodyA.getUserData();
			
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Torreta && bodyA.getUserData() instanceof Bullet) {
			Bullet bull = (Bullet) bodyA.getUserData();
			
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Torreta) {
			Bullet bull = (Bullet) bodyB.getUserData();
			
			bull.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Tail && bodyA.getUserData() instanceof Jimbo) {
			Jimbo jimbo = (Jimbo) bodyA.getUserData();
			
			jimbo.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Jimbo && bodyA.getUserData() instanceof Tail) {
			Jimbo jimbo = (Jimbo) bodyB.getUserData();
			
			jimbo.setHit(true);
		}
		
		if(bodyB.getUserData() instanceof Bullet && bodyA.getUserData() instanceof Tail) {
            Bullet bull = (Bullet) bodyB.getUserData();
            Tail tail = (Tail) bodyA.getUserData();

            tail.hit();
            bull.setHit(true);
        }
		
		if(bodyB.getUserData() instanceof Tail && bodyA.getUserData() instanceof Bullet) {
            Bullet bull = (Bullet) bodyA.getUserData();
            Tail tail = (Tail) bodyB.getUserData();

            tail.hit();
            bull.setHit(true);
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
