package Patrones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Caballero;
import com.mygdx.game.ClanSombra;
import com.mygdx.game.Consumibles;
import com.mygdx.game.Item;
import com.mygdx.game.JimboAdventures;
import com.mygdx.game.Obstaculo;
import com.mygdx.game.Snake;
import com.mygdx.game.Superficies;
import com.mygdx.game.Torreta;
import com.mygdx.game.TorretasLevel;

public class LevelCreator {
	public void createLevel1(Builder builder, JimboAdventures game) {
		builder.setWorld(new World(new Vector2(0,-20f), true));
		builder.setFondo(new Texture("Paisaje 1.png"));
		builder.setGame(game);
		builder.setJimbo(Jimbo.getInstance(new Texture("Parado.png"), -6.5f, 2.0f), -6.5f, 2.0f);
		try {
			builder.setConsumibles(leerItems("Csv\\Level1"));
			builder.setClanSombra(leerEnemigos("Csv\\\\Level1"));
			builder.setSuperficies(leerObstaculos("Csv\\Level1"));
			builder.setTorretas(leerTorretas("Csv\\Level1"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createLevel2(Builder builder, JimboAdventures game) {
		try {
			builder.setConsumibles(leerItems("Csv\\Level2"));
			builder.setClanSombra(leerEnemigos("Csv\\\\Level2"));
			builder.setSuperficies(leerObstaculos("Csv\\Level2"));
			builder.setTorretas(leerTorretas("Csv\\Level2"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		builder.setWorld(new World(new Vector2(0,-13f), true));
		builder.setFondo(new Texture("Paisaje 1.png"));
		builder.setGame(game);
	}
	
	/*public void createLevel3(Builder builder, JimboAdventures game) {
		try {
			builder.setConsumibles(leerItems("Csv\\Level3"));
			builder.setClanSombra(leerEnemigos("Csv\\\\Level3"));
			builder.setSuperficies(leerObstaculos("Csv\\Level3"));
			builder.setTorretas(leerTorretas("Csv\\Level3"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		builder.setWorld(new World(new Vector2(0,-13f), true));
		builder.setFondo(new Texture("Paisaje 1.png"));
		builder.setGame(game);
	}*/
	
	public void createFinalLevel(Builder builder, JimboAdventures game) {
		World world = new World(new Vector2(0,-20f), true);
		
		builder.setWorld(world);
		builder.setSnake(new Snake(0, 4, new Texture("Snake.png"), world));
		builder.setJimbo(Jimbo.getInstance(new Texture("Parado.png"), -6.5f, 2.0f), -6.5f, 2.0f);
		builder.setFondo(new Texture("Paisaje 1.png"));
		builder.setGame(game);
		try {
			builder.setConsumibles(leerItems("Csv\\FinalLevel"));
			builder.setSuperficies(leerObstaculos("Csv\\FinalLevel"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TorretasLevel leerTorretas(String archivo) throws IOException {
		BufferedReader csvTorretas = new BufferedReader(new FileReader(archivo + "\\torretas.csv"));
		TorretasLevel torrlvl = new TorretasLevel();
		Torreta newTorreta;
		String lineaTexto;
		String valores[];
		
		lineaTexto = csvTorretas.readLine();
		while((lineaTexto = csvTorretas.readLine()) != null) {
			valores = lineaTexto.split(",");
			newTorreta = new Torreta(new Texture("Torreta.png"),
					Float.parseFloat(valores[0]), Float.parseFloat(valores[1]), Float.parseFloat(valores[2]),
					Float.parseFloat(valores[3]), Float.parseFloat(valores[4]), valores[5]);
			
			torrlvl.agregarTorreta(newTorreta);
		}
		csvTorretas.close();
	
		return torrlvl;
	}
	
	public Consumibles leerItems(String archivo) throws IOException {
		BufferedReader csvItems = new BufferedReader(new FileReader(archivo + "\\items.csv"));
		Consumibles consu = new Consumibles();
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
			
			newItem = new Item(img, Integer.parseInt(valores[0]), 
					Float.parseFloat(valores[1]), Float.parseFloat(valores[2]),
					Float.parseFloat(valores[3]), Float.parseFloat(valores[4]));
			
			consu.agregarItem(newItem);
		}
		
		csvItems.close();
		
		return consu;
	}
	
	public ClanSombra leerEnemigos(String archivo) throws IOException {
		BufferedReader csvEnemigos = new BufferedReader(new FileReader(archivo + "\\enemigos.csv"));
		ClanSombra clan = new ClanSombra();
		Caballero newCaballero;
		String lineaTexto;
		String valores[];
		
		lineaTexto = csvEnemigos.readLine();
		while((lineaTexto = csvEnemigos.readLine()) != null) {
			valores = lineaTexto.split(",");
			
			newCaballero = new Caballero(new Texture("Caballero.png"), 
					Float.parseFloat(valores[0]), Float.parseFloat(valores[1]), 
					Float.parseFloat(valores[2]), Float.parseFloat(valores[3]), Float.parseFloat(valores[4]));
			
			clan.agregarCaballero(newCaballero);
		}
		
		csvEnemigos.close();
		
		return clan;
	}
	
	public Superficies leerObstaculos(String archivo) throws IOException {
		BufferedReader csvObstaculos = new BufferedReader(new FileReader(archivo + "\\obstaculos.csv"));
		Superficies sup = new Superficies();
		Obstaculo newObstaculo;
		String lineaTexto;
		String valores[];
		
		lineaTexto = csvObstaculos.readLine();
		while((lineaTexto  = csvObstaculos.readLine()) != null) {
			valores = lineaTexto.split(",");
			
			if(Integer.parseInt(valores[0]) == 1) {
				newObstaculo =  new Obstaculo(new Texture("Tierra.jpg"), 
						Integer.parseInt(valores[0]), Float.parseFloat(valores[1]), Float.parseFloat(valores[2]),
						Float.parseFloat(valores[3]), Float.parseFloat(valores[4]));
				sup.agregarSuperficie(newObstaculo);
			}
			else if(Integer.parseInt(valores[0]) == 2){
				newObstaculo =  new Obstaculo(new Texture("Pinchos.png"), 
						Integer.parseInt(valores[0]), Float.parseFloat(valores[1]), Float.parseFloat(valores[2]),
						Float.parseFloat(valores[3]), Float.parseFloat(valores[4]));
				sup.agregarSuperficie(newObstaculo);
			}
		}
		csvObstaculos.close();
		
		return sup;
	}
}
