package com.mygdx.game;

import java.io.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import Levels.Level1;

public class JimboAdventures extends Game {
	private Superficies sup;
	private World world;
	
	public void create() {
		sup = new Superficies();
		world = new World(new Vector2(0, -9.8f), true);
		try {
			leerObstaculos("Csv\\Level1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		setScreen(new Level1(this, world, sup));
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
		}
		csvObstaculos.close();
	}

}
