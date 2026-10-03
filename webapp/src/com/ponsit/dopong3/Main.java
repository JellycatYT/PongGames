package com.ponsit.dopong3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import Screens.Menu;
import System.GameData;
import System.SaveData;

public class Main extends Game {
public SaveData save;
public ExtendViewport win;
public SpriteBatch batch;
public GameData data;

	public void create() {
data = new GameData();
save = new SaveData();
save.load(data);
		batch = new SpriteBatch();
		win = new ExtendViewport(1080,660);
		win.update(
			    Gdx.graphics.getWidth(),
			    Gdx.graphics.getHeight(),
			    true
			);
	
		setScreen(new Menu(this));
	}

}