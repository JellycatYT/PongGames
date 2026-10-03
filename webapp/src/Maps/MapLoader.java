package Maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.ponsit.dopong3.Main;

import System.AssetLoader;

public class MapLoader  {
	Main game;
	int bgId;
	public int id;
	public static String Maps[] = {"csv/Maps/Map1.csv","csv/Maps/Map2.csv"};
	String bgList[] = {"Map/World Map.png","Map/World Map1.png"};
	 FileHandle Level;
	 Texture bg;
	public MapLoader(Main game,int id ) {
		this.game = game;
		this.id = id;
		this.Level = Gdx.files.internal(Maps[id]);
	}
	public void parseCsv() {
		String osat[] = Level.readString().split("\\r?\\n");
		bgId = Integer.parseInt(osat[1].split(",")[0].strip().trim());
		
	
	}
	
	
	
	public void loadCsv() {		
		parseCsv();
		bg = AssetLoader.loadTexture(bgList[bgId]);
		bg.setFilter(
			    Texture.TextureFilter.Nearest,
			    Texture.TextureFilter.Nearest

			);
	}
	public void render() {
		
		game.batch.draw(bg,0,0,game.win.getWorldWidth(),game.win.getWorldHeight());
	}
}
