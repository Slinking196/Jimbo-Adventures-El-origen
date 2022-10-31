package Pantallas;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Caballero;
import com.mygdx.game.ClanSombra;
import com.mygdx.game.Consumibles;
import com.mygdx.game.Item;
import com.mygdx.game.JimboAdventures;
import com.mygdx.game.Obstaculo;
import com.mygdx.game.Superficies;
import com.mygdx.game.Torreta;
import com.mygdx.game.TorretasLevel;

import Utils.Screens;

public class MenuPrincipal extends Screens {
	private Consumibles consu;
	private ClanSombra clan;
	private Superficies sup;
	private TorretasLevel torrlvl;
	private World world;
	private BitmapFont font;

	public MenuPrincipal(JimboAdventures game) {
		super(game);
		world = new World(new Vector2(0,-9.8f), true);
		consu = new Consumibles();
		clan = new ClanSombra();
		torrlvl = new TorretasLevel();
		sup = new Superficies();
		font = new BitmapFont(); // usa Arial font x defecto
		font.getData().setScale(2f);
		
		try {
			leerObstaculos("Csv\\Level1");
			leerEnemigos("Csv\\Level1");
			leerItems("Csv\\Level1");
			leerTorretas("Csv\\Level1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);

		getCamUI().update();
		getBatch().setProjectionMatrix(getCamUI().combined);

		getBatch().begin();
		getBatch().draw(new Texture("Logo.png"), 600, 500);
		font.draw(getBatch(), "Nueva Partida", 600, 400);
		font.draw(getBatch(), "Continuar Partida", 900, 400);
		font.draw(getBatch(), "Opciones", 600, 200);
		font.draw(getBatch(), "Instrucciones", 900, 200);
		//font.draw(getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 100, 300);
		getBatch().end();
		
		System.out.println("x =" + Gdx.input.getX() + "y =" + Gdx.input.getY());
		if (Gdx.input.isTouched() && (Gdx.input.getX() >= 600 && Gdx.input.getX() <= 720) && (Gdx.input.getY() >= 600 && Gdx.input.getY() <= 620)) {
			getGame().setScreen(new Level1(getGame(), world, sup, clan, consu, torrlvl));;
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
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
