package com.ponsit.dopong3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

import System.AssetLoader;
import System.GameData;
import System.Hud;

public class ShopScreen extends ScreenAdapter {
Main game;
GameData data;
Stage stage;
Table table;
TextButton Buy;
Hud HUD;
Window win;
Group content;
Label info;
Dialog dialog;
Image icon;
int selectedSkin = 0;
int cost;
TextureRegion icons;
SelectBox<String> select;
public ShopScreen(Main game) {
	this.game = game;
	
}
public void show() {
	data = game.data;
data.unlockSkin(0);	
	HUD = new Hud(game);
	AssetLoader.loadShop();
	AssetLoader.loadHUD();
	stage = new Stage(game.win,game.batch);
	Gdx.input.setInputProcessor(stage);
	win = new Window("", AssetLoader.skin);
	win.setPosition(game.win.getWorldWidth()*0.05f, game.win.getWorldHeight() * 0.4f);
	win.setSize(game.win.getWorldWidth()*0.9f, game.win.getWorldHeight() * 0.8f);
	icons = new TextureRegion(AssetLoader.ButtonAtlas.findRegion("icon1"));
	icon = new Image(icons);
	icon.setPosition(game.win.getWorldWidth()*0.205f,game.win.getWorldHeight()*0.65f );
	icon.setSize(game.win.getWorldWidth()*0.20f,game.win.getWorldHeight()*0.25f);
Buy = new TextButton("Buy",AssetLoader.skin);
Buy.setSize(game.win.getWorldWidth()*0.15f, game.win.getWorldHeight() * 0.35f);
Buy.setPosition(game.win.getWorldWidth()*0.2f ,game.win.getWorldHeight()*0.3f);
select = new SelectBox<String>(AssetLoader.skin);
select.setItems(data.skins);
select.setSize(game.win.getWorldWidth()*0.45f, game.win.getWorldHeight()*0.2f);
select.setPosition(game.win.getWorldWidth()*0.05f, game.win.getWorldHeight()*0.05f);

 dialog = new Dialog("Shop", AssetLoader.skin) {

    @Override
    protected void result(Object object) {
int skinID = 0;
        if("ok".equals(object)) {
        	if(select.getSelected().equals(data.skins[0])) {
        		cost = 0;
        		skinID = 0;
        	
        		
        	}
        	else if(select.getSelected().equals(data.skins[1])) {
        		cost = 200;
        		skinID = 1;
        		
        	}else {
        		return;
        	}
        	if(data.isUnlocked(skinID)) {
        		 Dialog already = new Dialog("Shop",AssetLoader.skin) {
            		 protected void result(Object object) {
            			 game.setScreen(new Menu(game));
            		 }
            	   };
            	   data.setSkinID(skinID);
            	   already.text("Skin was already unlocked!");
                   already.button("Continue");
                   already.show(stage);

                   return; 
               
        	}if(data.getScore() >= cost) {
        		 data.takeScore(cost);
          	   data.unlockSkin(skinID);
          	   data.setSkinID(skinID);
          	   Dialog unlock = new Dialog("Unlocked",AssetLoader.skin) {
          		 protected void result(Object object) {
          			 game.setScreen(new Menu(game));
          		 }
          	   };
          	   unlock.text("Skin Unlocked");
          	   unlock.button("Continue");
          	   unlock.show(stage);
             
        	}else {
        		Dialog error = new Dialog("Error",AssetLoader.skin) {
             		 protected void result(Object object) {
             			 game.setScreen(new Menu(game));
             		 }
             	   };
             	   error.text("Not Enough scores");
             	   error.button("Continue");
             	   error.show(stage);
        	}
        	
        }else if("cancel".equals(object)) {
        	Dialog note = new Dialog("Cancel",AssetLoader.skin) {
        		protected void result(Object object) {
        			game.setScreen(new Menu(game));
        		}
        		
        	};

        }

    }
};
select.addListener(new ChangeListener(){
	public void changed(ChangeEvent event, Actor actor) {
		if(select.getSelected().equals(data.skins[0])) {
			cost = 0;
			icon.setDrawable(new TextureRegionDrawable(
    			    AssetLoader.ButtonAtlas.findRegion("icon1")
    			));
		}
		if(select.getSelected().equals(data.skins[1])) {
			cost = 200;
			icon.setDrawable(new TextureRegionDrawable(
    			    AssetLoader.ButtonAtlas.findRegion("icon2")
    			));
		}
	}
});

Buy.addListener(new ClickListener() {
	public void clicked(InputEvent event, float x, float y) {
		 dialog.show(stage);
		 dialog.getContentTable().clearChildren();
		 dialog.text(" Buy this skin \n for "  + cost + " points?");
		 dialog.show(stage);
	}
	
});

win.setFillParent(true); 
dialog.button("OK", "ok");
dialog.button("Cancel", "cancel");
dialog.setPosition(game.win.getWorldWidth()*0.15f, game.win.getWorldHeight() * 0.3f);
dialog.setSize(game.win.getWorldWidth() * 0.7f,game.win.getWorldHeight() * 0.5f);
win.addActor(select);
win.addActor(Buy);
win.addActor(icon);
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
	AssetLoader.unloadShop();
	AssetLoader.unloadHUD();
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
	
	Buy.setSize(width*0.35f, height * 0.15f);
	Buy.setPosition(width*0.1f ,height*0.3f);	
	
	select.setSize(game.win.getWorldWidth()*0.45f, game.win.getWorldHeight()*0.2f);
	select.setPosition(width*0.05f ,height*0.05f);	

	
	dialog.setPosition(width*0.05f, height * 0.3f);
	dialog.setSize(width * 0.7f,height * 0.5f);
	
icon.setPosition(width*0.205f, height*0.65f);
icon.setSize(width*0.20f, height*0.25f);

}

}

