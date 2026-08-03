package com.ponsit.dopong3;

 


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import System.Hud;
import Test.Test;
import Test.Test1;
 

public class Menu extends ScreenAdapter{
	Hud HUD;
    Main game;
	Table table;
	Stage stage;
	TextButton play;
	TextButton quit;
	TextButton shop;

	public Menu(Main game) {
		this.game = game;
	}
	@Override
  public void show() {
    	AssetLoader.loadHUD();
		AssetLoader.loadMenu();
    	AssetLoader.ost1.play();
    	AssetLoader.ost1.setLooping(true);
    	stage = new Stage(game.win,game.batch);

    	Gdx.input.setInputProcessor(stage);
    	play = new TextButton("Play",AssetLoader.skin);
    	quit = new TextButton("Quit", AssetLoader.skin);
    	shop = new TextButton("SHOP", AssetLoader.skin,"Buy");
    	HUD = new Hud(game);

    	inputs();
    	
    	table = new Table();
    	table.setY(-300);
    	table.addAction(Actions.moveTo(0,0,0.6f));
    	table.setFillParent(true);
    	table.center();
shop.addAction(Actions.moveTo(game.win.getWorldWidth()*0.009f,game.win.getWorldHeight()*0.15f,0.6f));

    	table.add(play)
    	     .width(380)
    	     .height(100)
    	     .padBottom(40);
    	table.row();

    	table.add(quit)
    	     .width(380)
    	     .height(100).padBottom(-200);
stage.addActor(shop);
stage.addActor(table);

System.out.println(
	    Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight() +
	    " world=" +
	    game.win.getWorldWidth() + "x" +
	    game.win.getWorldHeight()
	);
    }
    @Override
    public void render(float delta) {

    	float w = game.win.getWorldWidth();
    	float h = game.win.getWorldHeight();

    	HUD.update();
    	HUD.debug();
    	
    	secret();
    	ScreenUtils.clear(0,0,0,0);
    	game.win.apply();
    	game.batch.setProjectionMatrix(game.win.getCamera().combined);
    	game.batch.begin();
    	game.batch.draw(AssetLoader.menuBg, 0,0,game.win.getWorldWidth(),game.win.getWorldHeight());
    	game.batch.draw(AssetLoader.title, w * 0.26f, h * 0.60f, w * 0.40f, h * 0.40f);
    	game.batch.end();
    	stage.act(delta);
    	stage.draw();
   HUD.getStage().act(delta);
   HUD.getStage().draw();
    }
    public void isWeb() {
    	if(Gdx.app.getType() == Application.ApplicationType.WebGL) {
    		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    	}}
    public void dispose(){
    	stage.dispose();
    	AssetLoader.unloadMenu();
    	AssetLoader.unloadHUD();
    }
    private void inputs() {
    	play.addListener(new ClickListener() {
    		
    		@Override
    		
    		public void clicked(InputEvent e, float x,float y) {
    			if(table.getTouchable() == Touchable.disabled) return;
    			isWeb();

    			table.setTouchable(Touchable.disabled);
    			 fadeOut(() -> {
    				 AssetLoader.unloadMenu();
   					game.setScreen(new GameScreen(game));
 					
    			 });
    		
    		}
    	});
    	
    	quit.addListener(new ClickListener() {
    		@Override
    		public void clicked(InputEvent e, float x, float y) {
    			if(table.getTouchable() == Touchable.disabled) return;
    			isWeb();


    			table.setTouchable(Touchable.disabled);
    			fadeOut(() ->{
    				AssetLoader.unloadMenu();
					AssetLoader.PongLoader.dispose();
        			Gdx.app.exit();
    			});
    			
    		}
    	});
    	shop.addListener(new ClickListener() {
    		
    		@Override
    		
    		public void clicked(InputEvent e, float x,float y) {
    			
    			if(table.getTouchable() == Touchable.disabled) return;
    			isWeb();
    			shop.addAction(Actions.sequence(
    					Actions.parallel(
    							Actions.fadeOut(1f)
    							)
    					)
    					);
    		
    			table.setTouchable(Touchable.disabled);
    			
    			 fadeOut(() -> {
    				 AssetLoader.unloadMenu();
   					game.setScreen(new GameScreen(game));
 					
    			 });
    		
    		}
    	});
    }

  
    private void fadeOut(Runnable nextAction) {
        table.clearActions();

        table.addAction(
            Actions.sequence(
                Actions.fadeOut(1f),
                Actions.fadeIn(0f),
                Actions.run(nextAction)
            )
        );
    }
    
    public void resize(int width,int heigth) {
     game.win.update(width, heigth,true);
     stage.getViewport().update(width, heigth,true);
    }
    public void secret() {
    	if(Gdx.input.isKeyJustPressed(Input.Keys.O)) {
    		fadeOut(()->{
				AssetLoader.unloadMenu();
				
    		});
			game.setScreen(new Test(game));
    		    	
    }
    	if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
    		fadeOut(()->{
				AssetLoader.unloadMenu();

    		});
			game.setScreen(new Test1(game));

    	}
    	
    	
    	}
}
