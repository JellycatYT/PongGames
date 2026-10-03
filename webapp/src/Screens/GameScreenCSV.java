package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ponsit.dopong3.Main;

import Entities.Drops;
import Entities.Pong;
import System.AssetLoader;
import System.CsvLoader;
import System.GameData;
import System.Hud;

public class GameScreenCSV extends ScreenAdapter {
    Main game;
    Array<Drops> Drops;
   Pong pong;
GameData data;
int levelID;
Drops drops;
CsvLoader level;
Hud hud;

boolean gameover = false;
    public GameScreenCSV(Main game, int levelID){
        this.game = game;
        this.levelID = levelID;
    }

    @Override
    public void show() {
    	data = game.data;
    	game.win.update(
    		        Gdx.graphics.getWidth(),
    		        Gdx.graphics.getHeight(),
    		        true
    		    );
data.setLife(3);
AssetLoader.loadHUD();
AssetLoader.loadPlayer();
AssetLoader.loadGame();
AssetLoader.loadDroplet();
AssetLoader.LoadSounds();
 hud = new Hud(game);

level = new CsvLoader(game,levelID);
level.LoadCSV();
drops = new Drops(game,level.getLevelFile());
drops.parseCsv();

pong = new Pong(AssetLoader.PongAtlas.findRegion("pong"), drops, game, data);



pong.player.setPosition(game.win.getWorldWidth()*0.5f, game.win.getWorldWidth()*0.10f);
pong.player.setSize(150,150);
    }


    @Override
    public void render(float delta) {
    	hud.update();
    	dead();
    	level.Timer(delta);
    	pong.move(delta);
    	pong.collide();
    	drops.update(delta);
    	ScreenUtils.clear(0,0,0,1);
    	
    	    game.win.apply();
game.batch.setProjectionMatrix(game.win.getCamera().combined);
game.batch.begin();
level.render();
drops.render();
pong.draw(game.batch);

game.batch.end();
hud.getStage().act(delta);
hud.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
game.win.update(width, height,true);
pong.player.setPosition(game.win.getWorldWidth()*0.5f, game.win.getWorldWidth()*0.10f);
pong.player.setSize(150,150);
    }
public void dead() {
	if(game.data.getLife() == 0 && !gameover) {

		gameover = true;
		level.stopMusic();
		
		
		game.setScreen(new Menu(game));
	}
}

    @Override
    public void dispose() {
level.stopMusic();
    }

    
}