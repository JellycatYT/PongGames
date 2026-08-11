package System;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {
	public static Skin skin;
	public static TextureAtlas atlas;
	public static TextureAtlas PongAtlas;
	public static TextureAtlas DropAtlas;
	public static TextureAtlas ButtonAtlas;
	public static Texture Bg0;
	public static Texture gameBg;
	public static Texture title;
	public static Texture box;
	public static Music ost1;
	public static Music ost2;
	public static BitmapFont gameFont;
	public static BitmapFont infoFont;
	
	public static final AssetManager PongLoader = new AssetManager();
public static void loadMenu() {
	PongLoader.load("Scene2d/source/doPongStyle.json", Skin.class, new SkinLoader.SkinParameter("Scene2d/source/doPongStyle.atlas"));
	PongLoader.load("Backgrounds/bg1.png",Texture.class);
	PongLoader.load("Title/Title.png",Texture.class);
	PongLoader.load("Sounds/Music/menu.ogg", Music.class);
	PongLoader.finishLoading();

	
	Bg0 = PongLoader.get("Backgrounds/bg1.png", Texture.class);
	skin = PongLoader.get("Scene2d/source/doPongStyle.json",Skin.class);
	title = PongLoader.get("Title/Title.png",Texture.class);
	ost1 = PongLoader.get("Sounds/Music/menu.ogg", Music.class);
}
public static void unloadMenu() {
	PongLoader.unload("Scene2d/source/doPongStyle.json");
	PongLoader.unload("Backgrounds/bg1.png");
	PongLoader.unload("Title/Title.png");
	PongLoader.unload("Sounds/Music/menu.ogg");

	
}
public static void loadGame() {
	PongLoader.load("Backgrounds/bg.png",Texture.class);
	PongLoader.load("Sounds/Music/DO!DO!Pong3.1.ogg",Music.class);
	PongLoader.finishLoading();
	gameBg = PongLoader.get("Backgrounds/bg.png",Texture.class);
	ost2 = PongLoader.get("Sounds/Music/DO!DO!Pong3.1.ogg", Music.class);
}
public static void loadPlayer() {
	PongLoader.load("Pongs/Pong.atlas",TextureAtlas.class);
	PongLoader.finishLoading();
	PongAtlas = PongLoader.get("Pongs/Pong.atlas",TextureAtlas.class);


}
public static void loadDroplet() {
	PongLoader.load("Droplets/droplets.atlas",TextureAtlas.class);
	PongLoader.finishLoading();
	DropAtlas= PongLoader.get("Droplets/droplets.atlas",TextureAtlas.class);

}
public static void loadFonts() {
	PongLoader.load("Scene2d/source/GameFont.fnt", BitmapFont.class);
	PongLoader.load("Scene2d/source/MenuFont.fnt", BitmapFont.class);
	PongLoader.finishLoading();
	gameFont = PongLoader.get("Scene2d/source/GameFont.fnt", BitmapFont.class);
	infoFont = PongLoader.get("Scene2d/source/MenuFont.fnt", BitmapFont.class);

}
public static void loadHUD() {
	PongLoader.load("Scene2d/source/GameFont.fnt", BitmapFont.class);
	PongLoader.load("Scene2d/source/MenuFont.fnt", BitmapFont.class);
	PongLoader.load("Scene2d/source/doPongStyle.json", Skin.class, new SkinLoader.SkinParameter("Scene2d/source/doPongStyle.atlas"));
	PongLoader.finishLoading();
	gameFont = PongLoader.get("Scene2d/source/GameFont.fnt"); 
	infoFont = PongLoader.get("Scene2d/source/MenuFont.fnt");
	skin = PongLoader.get("Scene2d/source/doPongStyle.json",Skin.class);

}
public static void unloadHUD() {
	
	PongLoader.unload("Scene2d/source/GameFont.fnt");
	PongLoader.unload("Scene2d/source/MenuFont.fnt");
	PongLoader.unload("Scene2d/source/doPongStyle.json");
}
public static void unloadFont() {
	PongLoader.unload("Scene2d/source/GameFont.fnt");
	PongLoader.unload("Scene2d/source/MenuFont.fnt");
	
}
public static void unloadGame() {
	PongLoader.unload("Backgrounds/bg.png");
	PongLoader.unload("Sounds/Music/DO!DO!Pong3.1.ogg");

}
public static void loadShop() {
	PongLoader.load("Scene2d/GUI/icons/icons.atlas",TextureAtlas.class);
	PongLoader.load("Backgrounds/bg1.png", Texture.class);
	PongLoader.load("Scene2d/GUI/Label.png",Texture.class);
	PongLoader.finishLoading();
	Bg0 = PongLoader.get("Backgrounds/bg1.png");
	box = PongLoader.get("Scene2d/GUI/Label.png");
	ButtonAtlas = PongLoader.get("Scene2d/GUI/icons/icons.atlas");
}
public static void unloadShop() {
	PongLoader.unload("Backgrounds/bg1.png");
	 PongLoader.unload("Scene2d/GUI/icons/icons.atlas");
}

}
