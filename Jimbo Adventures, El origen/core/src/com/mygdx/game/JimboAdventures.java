package com.mygdx.game;

import java.io.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import Levels.Level1;

public class JimboAdventures extends Game {
	private Consumibles consu;
	private ClanSombra clan;
	private Superficies sup;
	private TorretasLevel torrlvl;
	private World world;
	
	public void create() {
		consu = new Consumibles();
		clan = new ClanSombra();
		torrlvl = new TorretasLevel();
		sup = new Superficies();
		world = new World(new Vector2(0, -9.8f), true);
		
		try {
			leerObstaculos("Csv\\Level1");
			leerEnemigos("Csv\\Level1");
			leerItems("Csv\\Level1");
			leerTorretas("Csv\\Level1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setScreen(new Level1(this, world, sup, clan, consu, torrlvl));
	}
	
	public void leerTorretas(String archivo) throws IOException {
		BufferedReader csvTorretas = new BufferedReader(new FileReader(archivo + "\\torretas.csv"));
		Torreta newTorreta;
		String lineaTexto;
		String valores[];
		
		lineaTexto = csvTorretas.readLine();
		while((lineaTexto = csvTorretas.readLine()) != null) {
			valores = lineaTexto.split(",");
			newTorreta = new Torreta(new Texture("Torreta.png"), world,
					Float.parseFloat(valores[0]), Float.parseFloat(valores[1]), Float.parseFloat(valores[2]),
					Float.parseFloat(valores[3]), Float.parseFloat(valores[4]), valores[5]);
			
			torrlvl.agregarTorreta(newTorreta);
		}
		csvTorretas.close();
	}
	
	public void leerItems(String archivo) throws IOException {
		BufferedReader csvItems = new BufferedReader(new FileReader(archivo + "\\items.csv"));
		Texture img;
		Item newItem;
		String lineaTexto;
		String valores[];
		
		lineaTexto = csvItems.readLine();
		while((lineaTexto = csvItems.readLine()) != null) {
			valores = lineaTexto.split(",");
			
			if(Integer.parseInt(valores[0]) == 1) img = new Texture("Five Seven.png");
			else if(Integer.parseInt(valores[0]) == 2) img = new Texture("Pocion.png");
			else if(Integer.parseInt(valores[0]) == 3) img = new Texture("Bandera.png");
			else img = new Texture("Moneda.png");
			
			newItem = new Item(img, world, Integer.parseInt(valores[0]), 
					Float.parseFloat(valores[1]), Float.parseFloat(valores[2]),
					Float.parseFloat(valores[3]), Float.parseFloat(valores[4]));
			
			consu.agregarItem(newItem);
		}
		
		csvItems.close();
	}
	
	public void leerEnemigos(String archivo) throws IOException {
		BufferedReader csvEnemigos = new BufferedReader(new FileReader(archivo + "\\enemigos.csv"));
		Caballero newCaballero;
		String lineaTexto;
		String valores[];
		
		lineaTexto = csvEnemigos.readLine();
		while((lineaTexto = csvEnemigos.readLine()) != null) {
			valores = lineaTexto.split(",");
			
			newCaballero = new Caballero(new Texture("Caballero.png"), world, 
					Float.parseFloat(valores[0]), Float.parseFloat(valores[1]), 
					Float.parseFloat(valores[2]), Float.parseFloat(valores[3]), Float.parseFloat(valores[4]));
			
			clan.agregarCaballero(newCaballero);
		}
		
		csvEnemigos.close();
	}
	
	public void leerObstaculos(String archivo) throws IOException {
		BufferedReader csvObstaculos = new BufferedReader(new FileReader(archivo + "\\obstaculos.csv"));
		Obstaculo newObstaculo;
		String lineaTexto;
		String valores[];
		
		lineaTexto = csvObstaculos.readLine();
		while((lineaTexto  = csvObstaculos.readLine()) != null) {
			valores = lineaTexto.split(",");
			
			if(Integer.parseInt(valores[0]) == 1) {
				newObstaculo =  new Obstaculo(new Texture("Tierra.jpg"), world, 
						Integer.parseInt(valores[0]), Float.parseFloat(valores[1]), Float.parseFloat(valores[2]),
						Float.parseFloat(valores[3]), Float.parseFloat(valores[4]));
				sup.agregarSuperficie(newObstaculo);
			}
			else if(Integer.parseInt(valores[0]) == 2){
				newObstaculo =  new Obstaculo(new Texture("Pinchos.png"), world, 
						Integer.parseInt(valores[0]), Float.parseFloat(valores[1]), Float.parseFloat(valores[2]),
						Float.parseFloat(valores[3]), Float.parseFloat(valores[4]));
				sup.agregarSuperficie(newObstaculo);
			}
		}
		csvObstaculos.close();
	}

}
