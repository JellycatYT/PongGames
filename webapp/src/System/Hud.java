package System;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.ponsit.dopong3.Main;

public class Hud {
	boolean debugOn = true;
 Stage stage;
	String osName = System.getProperty("os.name");
	Main game;
	GameData data;
	Table table;
Label scoreText;
Label skinID;
Label versionText;
Label debugLabel;
Label fpsLabel;
Label runtimeLabel;
Label osLabel;
Label clockLabel;
Label platformLabel;
Label lifeLabel;

LocalTime clock = LocalTime.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

public Hud(Main game) {
	
	this.game = game;
	this.data = game.data;
	stage = new Stage(game.win,game.batch);
	this.table = new Table();
	stage.addActor(table);
	this.scoreText = new Label(" " + data.getScore() ,AssetLoader.skin);
	this.skinID = new Label(" " + data.getSkinID() ,AssetLoader.skin);
	this.fpsLabel = new Label(" " + Gdx.graphics.getFramesPerSecond() ,AssetLoader.skin);
	this.runtimeLabel = new Label(" ",AssetLoader.skin);
	this.osLabel = new Label("OS: "+osName, AssetLoader.skin);
	this.clockLabel = new Label("",  AssetLoader.skin);
	this.platformLabel = new Label("Platform name"+Gdx.app.getType(),AssetLoader.skin);
	this.lifeLabel = new Label("Life:"+data.getLife(), AssetLoader.skin);
table.top();
table.setFillParent(true);

table.add(fpsLabel).expandX().left();
table.add(osLabel).expandX().right();
table.row();
table.add(skinID).expandX().left();
table.add(clockLabel).expandX().right();
table.row();
table.add(scoreText).expandX().left();
table.add(platformLabel).expandX().right();
table.row();
table.add(lifeLabel).expandX().left();

}
	public void update() {
		fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
		skinID.setText("SkinID:" + data.getSkinID());
		scoreText.setText("Score:" + data.getScore());
		clockLabel.setText("CLOCK: " + LocalTime.now().format(formatter));
		osLabel.setText("OS: "+osName);
		platformLabel.setText("Platform name="+Gdx.app.getType());
		lifeLabel.setText("Life:"+data.getLife());

	}
	public Stage getStage() {
		return stage;
	}
	public void debug() {
		if((Gdx.input.isKeyJustPressed(Input.Keys.I))) {
			if(debugOn) {
				debugOn = false;
				stage.getRoot().setVisible(false);
			}else {
				stage.getRoot().setVisible(true);			
				debugOn = true;

			}
	}
		
	}
	public void hide() {
		stage.getRoot().setVisible(false);
	}
	}
