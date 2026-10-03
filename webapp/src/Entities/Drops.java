package Entities;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.ponsit.dopong3.Main;

import System.AssetLoader;
import System.CsvLoader;

public class Drops {
Main game;
public Sprite body;
float time;
float speed;
float x;
float y;
float width;
float height;
float WorldTimer;
float worldY;
boolean isSpawned;
String type;
String path;
FileHandle level;
TextureRegion typeRegion;

Array<Drops> drops = new Array<>();
public Drops(Main game, FileHandle level) {
	this.game = game;
	this.level = level;
}

public void parseCsv() {
	String osat[] = level.readString().split("\\r?\\n");
	for(int i = 7; i < osat.length; i++) {
		String osa = osat[i];
		if(osa.isEmpty() || osa.contains("#")) {
			continue;
		}
		String[] value = osa.split(",");
		Drops drop = new Drops(game,level);
		drop.time = Float.parseFloat(value[0].trim());
		drop.speed = Float.parseFloat(value[1].trim());
		drop.x = Float.parseFloat(value[2].trim());
		drop.y = Float.parseFloat(value[3].trim());

		drop.type = value[4].trim();
		
		drop.typeRegion = AssetLoader.DropAtlas.findRegion(drop.type);
		drop.body = new Sprite(drop.typeRegion);

		drop.width = Float.parseFloat(value[5].trim());
		drop.height = Float.parseFloat(value[6].trim());
		 
drops.add(drop);
		/*
		System.out.print(time + " ");
		System.out.print(speed + " ");
		System.out.print(x + " ");
		System.out.print(type + " ");
		System.out.println();
*/
	}
 
}






public void update(float delta) {
	WorldTimer += delta;
	for(int i = drops.size - 1; i >= 0; i--) {
		Drops drop = drops.get(i);
		if(!drop.isSpawned && WorldTimer >= drop.time) {
			drop.isSpawned = true;
			drop.worldY = drop.y * game.win.getWorldHeight();
			
		}
		if(drop.isSpawned) {
			drop.worldY += drop.speed * delta;
		}
		if(drop.worldY < -10) {
			drops.removeIndex(i);
			AssetLoader.score.play();
		}
	}
}
public void loadCsv() {
	 parseCsv();
		typeRegion = AssetLoader.loadDrops(path);

}
public void render() {
	
for(Drops drop:drops) {
	if(!drop.isSpawned) 
	continue;
	 float x = drop.x * game.win.getWorldWidth();
	  float y = drop.worldY;
	  float width = drop.typeRegion.getRegionWidth() * drop.width;
	  float height = drop.typeRegion.getRegionHeight() * drop.height;
	  drop.body.setPosition(x, y);
	  drop.body.setSize(width, height);
	  drop.body.draw(game.batch);
}
}
public Array<Drops> getDrops() {
	return drops;
}
public String getType() {
    return type;
}
}