package com.mygdx.game;

import java.io.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import Pantallas.Level1;
import Pantallas.MenuPrincipal;

public class JimboAdventures extends Game {
	
	public void create() {
		setScreen(new MenuPrincipal(this));
	}

}
