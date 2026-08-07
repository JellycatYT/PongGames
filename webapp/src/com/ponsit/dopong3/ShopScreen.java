package com.ponsit.dopong3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import System.GameData;
import System.Hud;

public class ShopScreen extends ScreenAdapter {
Main game;
GameData data;
Stage stage;
Table table;
TextButton Buy;
Hud HUD;
WindowStyle windowStyle;
Window win;
Group content;
Label info;
Dialog dialog;
int selectedSkin = 0;
int cost = 0;
public ShopScreen(Main game) {
	this.game = game;
	
}
public void show() {
	data = game.data;
	
	HUD = new Hud(game);
	AssetLoader.loadShop();
	AssetLoader.loadHUD();
	stage = new Stage(game.win,game.batch);
	Gdx.input.setInputProcessor(stage);
	win = new Window("", AssetLoader.skin);
	win.setPosition(game.win.getWorldWidth()*0.05f, game.win.getWorldHeight() * 0.4f);
	win.setSize(game.win.getWorldWidth()*0.9f, game.win.getWorldHeight() * 0.8f);
Buy = new TextButton("Buy",AssetLoader.skin,"Pong");
Buy.setSize(game.win.getWorldWidth()*0.25f, game.win.getWorldHeight() * 0.3f);
Buy.setPosition(game.win.getWorldWidth()*0.2f ,game.win.getWorldHeight()*0.6f);

 dialog = new Dialog("Shop", AssetLoader.skin) {

    @Override
    protected void result(Object object) {

        if("ok".equals(object)) {

            if(data.getScore() >= 200) {
                data.unlockSkin(1);
                data.setSkinID(1);

                // avaa uusi dialogi
                Dialog unlock = new Dialog("Skin unlocked ",AssetLoader.skin) {
                	protected void result(Object object) {
                		game.setScreen(new Menu(game));
                	}
                	
                };
                unlock.text("Skin Unlocked");
                unlock.button("Continue");
                unlock.show(stage);
                
                
              
            }
            else {

               Dialog error = new Dialog("Error", AssetLoader.skin) {
                	protected void result(Object object) {

                	}
            	   
                };
                   error.text("Not enough scores"); 
                   error.button("ok");
                error.show(stage);
             Dialog new1 = new Dialog("Tips",AssetLoader.skin) {
            	protected void result(Object object) {
					Dialog ok1 = new Dialog("Tip2",AssetLoader.skin) {
						
						protected void result(Object object) {
		            		game.setScreen(new Menu(game));

						}
						
					};
					ok1.text("It's not bad to listen to tips \n  \n is this true \n public static void \n String[]args){");
					ok1.button("I Guess so");
					ok1.show(stage);
				} 
            	 
             };

                new1.text("*TIP*\nBy collecting droplets\nYou can get scores\nto buy skins!");
                new1.button("OK");
                new1.show(stage);
                
               // new Dialog("Go back to menu?",AssetLoader.skin).text("?").show(stage);

            }

        }else if("cancel".equals(object)) {
        	Dialog note = new Dialog("Cancel",AssetLoader.skin) {
        		protected void result(Object object) {
        			
        		}
        		
        	};
        	game.setScreen(new Menu(game));

        }

    }
   
};
Buy.addListener(new ClickListener() {
	public void clicked(InputEvent event, float x, float y) {
		 dialog.show(stage);
	}
	
});
win.setFillParent(true); // jos haluat sen peittävän koko ruudun
dialog.text(" Buy this skin \n for 200 points?");
dialog.button("OK", "ok");
dialog.button("Cancel", "cancel");
dialog.setPosition(game.win.getWorldWidth()*0.15f, game.win.getWorldHeight() * 0.3f);
dialog.setSize(game.win.getWorldWidth() * 0.7f,game.win.getWorldHeight() * 0.5f);
win.addActor(Buy);
stage.addActor(win);

}

public void render(float delta) {
	float width = game.win.getWorldWidth();
	float height = game.win.getWorldHeight();
	
HUD.debug();
HUD.update();
	ScreenUtils.clear(0f,0f,0f,1f);
	game.win.apply();
	
	game.batch.setProjectionMatrix(game.win.getCamera().combined);
	game.batch.begin();
	
	//game.batch.draw(AssetLoader.Bg0,0,0,width,height);

	game.batch.end();
	stage.act(delta);
	stage.draw();
	HUD.getStage().act(delta);
	HUD.getStage().draw();
}
public void dispose() {
	
}
public void resize(int width , int height) {
	game.win.update(width, height,true);
	stage.getViewport().update(width, height,true);
	
	layout();
}
public void layout() {
	float width = game.win.getWorldWidth();
	float height = game.win.getWorldHeight();
	win.setPosition(width*0.05f, height * 0.4f);
	win.setSize(width*0.9f, height * 0.8f);
	
	Buy.setSize(width*0.25f, height * 0.3f);
	Buy.setPosition(width*0.2f ,height*0.6f);	
	
	dialog.setPosition(width*0.15f, height * 0.3f);
	dialog.setSize(width * 0.7f,height * 0.5f);
	

}

}

