package com.ponsit.dopong3;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

import Entities.Droplet;
import Entities.Pong;
import System.GameData;

public class GameScreen extends ScreenAdapter {
    Main game;
   Pong pong;
Droplet drop;
GameData data;
boolean gameStopper = false;
    public GameScreen(Main game) {
        this.game = game;
        
    }

    @Override
    public void show() {
data = game.data;
data.setLife(3);

    	System.out.println("GameScreen!");
AssetLoader.loadDroplet();
AssetLoader.loadGame();
AssetLoader.loadPlayer();
AssetLoader.loadFonts();
drop = new Droplet(game, AssetLoader.DropAtlas.findRegion("droplet"),data );
pong = new Pong(AssetLoader.PongAtlas.findRegion("Pong"), drop, game, data);

pong.player.setPosition(540, 100);
AssetLoader.ost2.play();
AssetLoader.ost2.setLooping(true);
    }
public void isDead() {
	if(data.getLife() <= 0) {
		AssetLoader.ost2.stop();
gameStopper = true;
game.setScreen(new Menu(game));
	}
}
public void stop(float delta) {
if(!gameStopper) {
	isDead();

	   pong.border();
	   pong.skins();
	drop.DropLogic(delta);
    pong.move(delta);
    pong.collide();

}
}
    @Override
    public void render(float delta) {
    	  stop(delta);
    	
    	    ScreenUtils.clear(0,0,0,1);
        
    	game.win.apply();
game.batch.setProjectionMatrix(game.win.getCamera().combined);
game.batch.begin();
game.batch.draw(AssetLoader.gameBg, 0, 0,game.win.getWorldWidth(),game.win.getWorldHeight());
pong.draw(game.batch);
AssetLoader.gameFont.draw(game.batch, "Score ="+data.getScore()+"]"  ,20,game.win.getWorldHeight());
AssetLoader.gameFont.draw(game.batch, "[DO!DO!PONG! 3.0]:"  ,20,game.win.getWorldHeight()-100);
AssetLoader.gameFont.draw(game.batch, "[Health ="+ data.getLife()+"]" ,20,game.win.getWorldHeight()-150);
AssetLoader.gameFont.draw(game.batch, "[SkinID ="+ data.getSkinID()+"]" ,20,game.win.getWorldHeight()-200);

for(Sprite s : drop.getDrops()) {
	s.draw(game.batch);
}

game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
game.win.update(width, height,true);
    }


    @Override
    public void dispose() {
AssetLoader.unloadGame();
AssetLoader.unloadFont();
    }
 
    
}