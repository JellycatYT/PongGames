package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ponsit.dopong3.Main;

import System.AssetLoader;
import System.GameData;

public class Pong {

    Main game;
    public Rectangle hitbox;
    Vector2 touchPos;
    public Sprite player;
    GameData data;
    Drops drops;
    Float speed = 800f;
    public Pong(TextureRegion img, Drops drops, Main game, GameData data) {
    this.drops = drops;
    this.game = game;
    this.data = data;
    touchPos = new Vector2();
    hitbox = new Rectangle();
    if(data.getSkinID() == 0) {
    img = AssetLoader.PongAtlas.findRegion("Pong");
    }
    if (data.getSkinID() == 1) {
        img = AssetLoader.PongAtlas.findRegion("Pong2");

    }
   player = new Sprite(img);
    }

  



	public void draw(SpriteBatch batch) {
    player.draw(batch);
    }

    public void skins() {
    }

    public void move(float delta) {
    	if(Gdx.input.isKeyPressed(Input.Keys.D)) {
    		player.translateX(delta * speed);
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.A)) {
    		player.translateX(delta * -speed);
    	}
    if(Gdx.input.isTouched()) {
    	touchPos.set(Gdx.input.getX(),Gdx.input.getY());
    	game.win.unproject(touchPos);
    	player.setCenterX(touchPos.x);
    }
    }

    public void collide() {
    	updateHitbox();
  for(int i = drops.drops.size - 1; i >= 0;i--) {
	  Drops drop = drops.getDrops().get(i);
	 
	  if("droplet".equals(drop.getType())) {
		  
		if(hitbox.overlaps(drop.body.getBoundingRectangle())) {
			AssetLoader.score.play();
			player.setColor(Color.WHITE);
			data.addScore(1);
			drops.drops.removeIndex(i);
		}
	  }
	  if("poisonDrop".equals(drop.getType())) {
		  if(hitbox.overlaps(drop.body.getBoundingRectangle())) {
				data.addLife(-1);
				 player.setColor(Color.FOREST);
			  drops.drops.removeIndex(i);
			} 
	  }
  }
    }

    private void updateHitbox() {
        float padding = 24f;

        hitbox.set(
            player.getX() + padding,
            player.getY() + padding,
            player.getWidth() - padding * 2,
            player.getHeight() - padding * 2
        );
    }
}