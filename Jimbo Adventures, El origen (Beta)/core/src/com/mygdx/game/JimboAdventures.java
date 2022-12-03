package com.mygdx.game;

import com.badlogic.gdx.Game;

import Ventanas.MenuPrincipal;

public class JimboAdventures extends Game {
	
	@Override
	public void create() {
		setScreen(new MenuPrincipal(this));
	}
}
