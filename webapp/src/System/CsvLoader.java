package System;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.ponsit.dopong3.Main;

import Screens.Menu;

public class CsvLoader {
	Main game;
	GameData data;
	public static String[] Levels = {"csv/Level1.csv","csv/Level2.csv"};
	int[] objectives;
	float[] objectiveMulti;
public int levelID;
	int bgID;
	int musicID;
	float Time;
	float LevelDuration;
	String levelName;
	String type;
	private FileHandle level;
	String[] bgList = {"Backgrounds/bg.png","Backgrounds/bg0.png"}; 
 String[] ostList = {"Sounds/Music/menu.ogg","Sounds/Music/DO!DO!Pong3.1.ogg"};
	Texture bg;
	Music ost;
 public CsvLoader(Main game,int levelID) {
	this.game = game;
	data = game.data;
	this.levelID = levelID;
	    this.level = Gdx.files.internal(Levels[levelID]);
	    
}

public void ParseCSV() {
	String osat[] = level.readString().split("\\r?\\n");
	bgID = Integer.parseInt(osat[2].split(",")[0].trim());	
	musicID = Integer.parseInt(osat[2].split(",")[1].trim());
	levelName = osat[2].split(",")[2].trim();
	LevelDuration = Float.parseFloat(osat[2].split(",")[3].trim());
	

System.out.println(ostList[musicID]);
System.out.println(bgList[bgID]);
}
public void LoadCSV() {
ParseCSV();
 bg = AssetLoader.loadTexture(bgList[bgID]);
	System.out.println(ostList[musicID]);
	System.out.println(bgList[bgID]);
	System.out.println(LevelDuration);
	System.out.println(levelName);

	ost = AssetLoader.loadOst(ostList[musicID]);
	ost.play();
	ost.setLooping(true);	
}
public void render() {

	game.batch.draw(bg, 0,0,game.win.getWorldWidth(),game.win.getWorldHeight());
}
public void stop() {
	 AssetLoader.unloadOst();
	ost.stop();
}
public void Timer(float delta) {
	Time += delta;
	if(LevelDuration <= Time) {
game.data.clearLevel(levelID);
game.save.save(game.data);
stopMusic();
		game.setScreen(new Menu(game));	
		
	}
}

public FileHandle getLevelFile() {
	return level;
}
public void stopMusic() {
AssetLoader.unloadMusic(ostList[musicID]);
	ost.stop();
}
}

