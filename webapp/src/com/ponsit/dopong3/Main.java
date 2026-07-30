package com.ponsit.dopong3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import System.GameData;

public class Main extends Game {
public ExtendViewport win;
public SpriteBatch batch;
public GameData data;
	public void create() {
data = new GameData();
		batch = new SpriteBatch();
		win = new ExtendViewport(1080,660);
		setScreen(new Menu(this));
	}

}