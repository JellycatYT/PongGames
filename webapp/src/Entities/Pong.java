package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ponsit.dopong3.Main;

import System.AssetLoader;
import System.GameData;

public class Pong {
	Main game;
	Vector2 touchPos;
	public Sprite player;
	TextureRegion img;
GameData data;
	Droplet drops;
	int life = 3;
 public Pong(TextureRegion img,Droplet drops,Main game,GameData data){
	 this.game = game;
	 this.data =data;
	 this.touchPos = new Vector2();
		this.img = AssetLoader.PongAtlas.findRegion("Pong");
				this.player = new Sprite(this.img);
		this.drops = drops;
	}
 public void draw(SpriteBatch batch) {
	player.draw(batch); 
 }
 public void skins() {
	 if(data.getSkinID() == 0) {
		 player.setRegion(AssetLoader.PongAtlas.findRegion("Pong"));
	 }
	 if(data.getSkinID() == 1) {
		 player.setRegion(AssetLoader.PongAtlas.findRegion("Pong2"));
	 }
 }
 public void move(float delta) {
	 float speed = 600f;
	 if(Gdx.input.isKeyPressed(Input.Keys.D) || (Gdx.input.isKeyPressed(Input.Keys.RIGHT))) {
		 player.translateX(delta * speed);
	 }
	 if(Gdx.input.isKeyPressed(Input.Keys.A)|| (Gdx.input.isKeyPressed(Input.Keys.LEFT))) {
		 player.translateX(delta * -speed);
	 }
	 if(Gdx.input.isKeyPressed(Input.Keys.W)|| (Gdx.input.isKeyPressed(Input.Keys.UP))) {
		 player.translateY(delta * speed);
	 }
	 if(Gdx.input.isKeyPressed(Input.Keys.S)|| (Gdx.input.isKeyPressed(Input.Keys.DOWN))) {
		 player.translateY(delta * -speed);
	 }
	 if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
data.setSkinID(0);
	 }
	 if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
data.setSkinID(1);	
}
	 if(Gdx.input.isTouched()) {
		 touchPos.set(Gdx.input.getX(), Gdx.input.getY());
		 game.win.unproject(touchPos);
		 player.setCenterX(touchPos.x);
	 }
 }
 public void collide() {
for(int i = drops.getDrops().size - 1; i >= 0;i--) {
	Sprite waterS = drops.drops.get(i);
	waterS.setScale(1,1);
	if(waterS.getBoundingRectangle().overlaps(player.getBoundingRectangle())) {
		if(drops.isPoison(waterS)) {
			player.setColor(Color.FOREST);
			data.addScore(-1);
			data.addLife(-1);

		}else if(waterS.getColor().equals(Color.RED)) {
			System.out.println(waterS.getColor());
			player.setColor(MathUtils.random(),MathUtils.random(),MathUtils.random(),1f);
			data.addScore(3);
			
		}else if(waterS.getColor().equals(Color.GOLD)) {
			System.out.println(waterS.getColor());
			player.setAlpha(MathUtils.random(0f,1f));

			data.addScore(3*2);
			
		}
		else {
			player.setColor(Color.WHITE);
			data.addScore(1);
		}
		
		drops.drops.removeIndex(i);

		
	}
}

 }
public void border() {
	player.setX(MathUtils.clamp(player.getX(),0,game.win.getWorldWidth() - player.getWidth()));
}
}
