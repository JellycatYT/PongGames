package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.ponsit.dopong3.Main;

import System.AssetLoader;
import System.GameData;

public class Droplet {
	GameData data;
	TextureRegion poisonDrop;
	TextureRegion droplet;
	int skinID = 0;
	Main game;
	float speed;
public Array<Sprite> drops;
TextureRegion dropSkin;
float dTime;
int random = MathUtils.random(0,100);
public Droplet(Main game,TextureRegion dropSkin,GameData data) {
	this.game = game;
	this.data = data;
	this.dropSkin = AssetLoader.DropAtlas.findRegion("droplet");
	poisonDrop = AssetLoader.DropAtlas.findRegion("poisonDrop");
	droplet = AssetLoader.DropAtlas.findRegion("droplet");

	drops = new Array<Sprite>();
	this.game = game;
	
}


public void DropLogic(float delta) {
	input();
	speed(delta);
	dTime += delta;
	if(dTime >= 0.1f) {
		random = MathUtils.random(0,100);
		dTime = 0f;
		float dropW = 50;
		float dropH = 50;

		Sprite waterS = new Sprite(dropSkin);			
		waterS.setSize(dropW, dropH);
	waterS.setX(MathUtils.random(0f,game.win.getWorldWidth() - waterS.getWidth()));
		waterS.setY(game.win.getWorldHeight());
	if(random <= 30) {
		waterS.setRegion(poisonDrop);
	}else if(random <= 70) {
		waterS.setRegion(droplet);
	}if(random <= 10){
		
		waterS.setRegion(droplet);
		waterS.setColor(Color.RED);

	}
	else if (random <= 15){
		waterS.setRegion(droplet);		
		waterS.setSize(64f,64);
		waterS.setColor(Color.GOLD);
	
	}

		
	
		drops.add(waterS);
		

		
	}
	}
public void speed(float delta) {
	for(int i = drops.size - 1 ; i >= 0; i--) {
		Sprite waterS = drops.get(i);
		float speed = -650;
		waterS.translateY(speed * delta);
		if(waterS.getY() < game.win.getWorldHeight()/8) {
			drops.removeIndex(i);
		}}}
public void input() {
if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
	skinID = 0;
}
if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
	skinID = 1;
}
}


public boolean isPoison(Sprite sprite) {
    return sprite.getRegionX() == poisonDrop.getRegionX()
        && sprite.getRegionY() == poisonDrop.getRegionY();
}

public Array<Sprite> getDrops() {
return drops;
}
}
