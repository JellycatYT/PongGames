package Maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.ponsit.dopong3.Main;

import System.AssetLoader;

public class PointLoader {
	public static String Maps[] = {"csv/Maps/Map1.csv","csv/Maps/Map2.csv"};
	public int id;
	String[] Items;
String[] dotPath = {"Map/MapPoints/Point.png"};
Main game;
int type;
int ID;
String Stage;
float x;
float y;
float width;
float height;
Texture mapDot;
FileHandle csv;
 int currentPoint = 0;
 int target = 0;
Array<PointLoader> points = new Array<>();
public PointLoader(Main game,int id) {
	this.game = game;
	this.id = id;
	this.csv = Gdx.files.internal(Maps[id]);
}
public void parseCsv() {
	String osat[] = csv.readString().split("\\r?\\n");
			for(int i = 3; i < osat.length; i++) {

				String osa = osat[i];
				if(osa.isEmpty() || osa.contains("#")) {
					continue;
				}

				String[] value = osa.split(",");
				PointLoader pointLoad = new PointLoader(game,id);
				
				pointLoad.type = Integer.parseInt(value[0].trim());
				pointLoad.ID = Integer.parseInt(value[1].trim());
				pointLoad.Stage = value[2].trim();
				pointLoad.x = Float.parseFloat(value[3].trim());
				pointLoad.y = Float.parseFloat(value[4].trim());
				pointLoad.width = Float.parseFloat(value[5].trim());
				pointLoad.height = Float.parseFloat(value[6].trim());

				points.add(pointLoad);
				
			}

}
public void loadCsv() {
	parseCsv();
mapDot =	AssetLoader.loadTexture(dotPath[type]);


}
public void render() {
	for(PointLoader point : points) {
		float x = point.x * game.win.getWorldWidth();
		float y = point.y * game.win.getWorldHeight();
		float width = point.width * game.win.getWorldWidth();
		float height = point.height * game.win.getWorldHeight();
		game.batch.draw(mapDot,x,y,width,height);
	}
}
public void Goto(Image pong,int ID,float delta) {
	for(PointLoader point:points) {
		float x = game.win.getWorldWidth() * point.x;
		float y = game.win.getWorldHeight() * point.y;
		if(point.ID == ID) {
			float speed = 3f;
			pong.setPosition(
					MathUtils.lerp(pong.getX(),x,speed * delta),
					MathUtils.lerp(pong.getY(),y ,speed * delta)

					);
			
			break;
		}
	}
	}
public void listAdd(List<String> lista) {
	Array<String> items = new Array<>();

	for(PointLoader point : points) {
		items.add(point.Stage);
	}
	lista.setItems(items);
	}
}


	
