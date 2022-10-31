package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class BodySnake {
	private final int up = 1;
	private final int down = -1;
	private final int left = 2;
	private final int right = -2;
	private final int stop = 0;
	
	private ArrayList<Tail> body; //Secciones del cuerpo
	private World world;
	private Texture tx; //Textura del cuerpo
	private float pausa = 100; //Si velocidad acumulada es igual a pausa el objeto se mueve
	private float velAcum = 0; //Se acumula la velocidad para avanzar
	private float separacion = .35f; //Distancia de separación del cuerpo
	private float serpenteo = .2f; //Inclinación de la serpiente hacia los lados
	private boolean stateSerpenteo = true; //Cambios constantes hacia lado izquierdo y derecho
	private int cantColasInit = 10; //Inicialmente serán 5 secciones de cuerpo
	private float probAleatoriaDir = 1000; //Probabilidad de tomar una dirección aleatoria del 0 al 10000
	
	public BodySnake(float x, float y, Texture tx, World world) {
		body = new ArrayList<Tail>();
		Tail nuevaCola;
		this.world = world;
		this.tx = tx;
		for(int i = 0; i < cantColasInit; i++) {
			nuevaCola = new Tail(x,y - (i * separacion),tx, world);
			body.add(nuevaCola);
		}
	}
	
	//Validar choques aquí
	public int mover(float vel, int dir, SpriteBatch batch) {
		
		if(validarVelAcum(vel)) {
			dir = validarAleatoriaDir(dir);
			Tail tail, head;
			head = body.get(0);
			tail = body.remove(getCantColas() - 1);
			tail.deleteTail();
			dir = validarBorde(dir, head);
			//Aquí se validan choques con respecto a la dirección y posición de la cabeza
			float ladoSerpenteo = validarLadoSerpenteo();
			float posX, posY;
			if(dir == up) { //Up
				posX = head.getX() + ladoSerpenteo;
				posY = head.getY() + separacion;
			}
			else if(dir == down) { //Down
				posX = head.getX() + ladoSerpenteo;
				posY = head.getY() - separacion;
			}
			else if(dir == left) { //Left
				posX = head.getX() - separacion;
				posY = head.getY() + ladoSerpenteo;
			}
			else { //Right
				posX = head.getX() + separacion;
				posY = head.getY() + ladoSerpenteo;
			}
			/*if (posX < 0)
				posX = (int)Gdx.graphics.getWidth() + posX;
			if (posY < 0)
				posY = (int)Gdx.graphics.getHeight() + posY;
			posX = posX % (int)Gdx.graphics.getWidth();
			posY = posY % (int)Gdx.graphics.getHeight();*/
			tail = new Tail( posX, posY, tx, world);
			body.add(0, tail);
		}
		
		
		if(draw(batch)) {
			dir = stop;
		}
		return dir;
	}
	
	
	public float validarLadoSerpenteo(){
		
		float ladoSerpenteo;
		if(stateSerpenteo)
			ladoSerpenteo = serpenteo;
		else 
			ladoSerpenteo = (-1) * serpenteo;
		stateSerpenteo = !stateSerpenteo;
		//stateSerpenteo = getAleatoriaStateSerpenteo();
		return ladoSerpenteo;
	}
	
	public boolean getAleatoriaStateSerpenteo(){
		if((int) (Math.random()*2 + 1) == 1)
			return true;
		return false;
	}
	
	public boolean validarVelAcum(float vel) {
		velAcum += vel;
		if((int)(velAcum/pausa) == 1) {
			velAcum -= pausa;
			return true;
		}
		return false;
	}
	
	public int validarAleatoriaDir(int dir) {
		int numAleatorio = (int) (Math.random()*10000 + 1); //Número aleatorio entre 1 y 10000
		if(numAleatorio <= probAleatoriaDir) {
			return getDirAleatoria(dir);
		}
		return dir;
	}
	
	public int getDirAleatoria(int dir) {
		int dirAleatoria;
		while(true) {
			dirAleatoria = (int) Math.floor(Math.random()*(2+2+1)-2); //Número aleatorio entre -2 y 2
			if(dirAleatoria != dir && dirAleatoria != (-1)*dir && dirAleatoria != 0) {
				return dirAleatoria;
			}
		}
	}
	
	public int validarBorde(int dir, Tail head){
		if(head.getY() >= 10) { // borde Up
			if(dir != down) {
				dir = down;
			}
		}
		if(head.getY() <= 0) { //borde Down
			if(dir != up) {
				dir = up;
			}
		}
		if(head.getX() <= -5) { //borde Left
			if(dir != right) {
				dir = right;
			}
		}
		if(head.getX() >= 5) { //borde Right
			if(dir != left) {
				dir = left;
			}
		}
		
		/*if(head.getY() >= (int)Gdx.graphics.getHeight() || head.getY() <= 0 ||
		   head.getX() <= 0 || head.getX() >= (int)Gdx.graphics.getWidth()) {
			dir = getDirAleatoria(dir);
		}*/
		
		return dir;
	}
	
	public boolean draw(SpriteBatch batch) {
		boolean herido = false;
		Tail seccionCola;
		for(int i = 0; i < getCantColas(); i++) {
			seccionCola = body.get(i);
			if(seccionCola.isHerido()) {
				System.out.println("Entonces se marca el flag");
				herido = true;
				seccionCola.setHerido(false);
			}
			seccionCola.cambiarObjeto(i);
			if(!seccionCola.isColisionWall(world))
				seccionCola.draw(batch);
			seccionCola.cambiarObjeto();
		}
		
		return herido;
	}
	
	public void agregarCola(int dir) {
		if(body.size() == 0) {
			Tail nuevaCola = new Tail(2,2,tx,world);
			body.add(nuevaCola);
			return;
		}
		Tail nuevaCola, tail;
		tail = body.get(getCantColas() - 1);
		if(dir != down) { //Down
			nuevaCola = new Tail(tail.getX(), tail.getY() - separacion , tx, world);
		}
		else {
			nuevaCola = new Tail(tail.getX(), tail.getY() + separacion , tx, world);
		}
		body.add(nuevaCola);
	}
	
	public void eliminarCola() {
		Tail tail  = body.remove(getCantColas() - 1);
		tail.deleteTail();
		System.out.println("Se eliminó una cola");
	}
	
	public int getCantColas() {
		return body.size();
	}
	
	public float getPausa(){
		return pausa;
	}
	
	public void setPausa(float pausa){
		this.pausa = pausa;
	}
	
	public float getVelAcum(){
		return velAcum;
	}
	
	public void setVelAcum(float velAcum){
		this.velAcum = velAcum;
	}
	
	public float getSeparacion(){
		return separacion;
	}
	
	public void setSeparacion(float separacion){
		this.separacion = separacion;
	}
}
