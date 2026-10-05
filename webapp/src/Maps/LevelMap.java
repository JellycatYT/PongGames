package Maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ponsit.dopong3.Main;

import Screens.GameScreenCSV;
import System.AssetLoader;
import System.CsvLoader;
import System.GameData;

public class LevelMap extends ScreenAdapter {
Main game;
Table table;
GameData data;
MapLoader Map;
PointLoader point;
OrthographicCamera cam;
CsvLoader level;
FileHandle csv;
Texture pong1;
Stage stage;
Stage UIStage;
List<String> lista;
ScrollPane panel;
Image pong;
int select;
int StageId;
public LevelMap(Main game) {
	this.game = game;
	data = game.data;
}
@Override
public void resize(int width, int height) {
	game.win.update(width, height,true);
	UIStage.getViewport().update(width, height,true);
	resizeUI();
	
}
public void resizeUI() {
    float width = UIStage.getViewport().getWorldWidth();
    float height = UIStage.getViewport().getWorldHeight();
    panel.setPosition(width*0.1f, height*0.1f);
    
	panel.setSize(width*0.35f, height*0.35f);    
	float scale = width / 2000;
	lista.getStyle().font.getData().setScale(scale);
}
	
	public void show() {
		AssetLoader.LoadSkin();
		level = new CsvLoader(game, 0);
		UIStage = new Stage(new ScreenViewport(),game.batch);		
		stage = new Stage(game.win,game.batch);
		table = new Table(AssetLoader.skin);
		float width = UIStage.getViewport().getWorldWidth();
		float height = UIStage.getViewport().getWorldHeight();
		Gdx.input.setInputProcessor(UIStage);
		table.setFillParent(true);
		Map = new MapLoader(game, 0);
		int id = Map.id;
		point = new PointLoader(game,id);
		Map.loadCsv();
		point.loadCsv();
		
	

		 lista = new List<String>(AssetLoader.skin);
			point.listAdd(lista);
			listInput();

		

		panel = new ScrollPane(lista,AssetLoader.skin);
		panel.setPosition(width*0.1f, height*0.1f);
		panel.setSize(width*0.35f, height*0.55f);    
		

		pong1 = new Texture("Backgrounds/Pongi.png");
		pong = new Image(pong1);
	
	
		cam = new OrthographicCamera();
		cam.setToOrtho(false, game.win.getWorldWidth(), game.win.getWorldHeight());
		game.win.setCamera(cam);
		cam.zoom = 0.40f;
	

		stage.addActor(pong);
		
		UIStage.addActor(panel);


	}

	@Override
	public void render(float delta) {
		point.Goto(pong, StageId,delta);

	
		input(delta);
		follow();

		// testaa kameraa
ScreenUtils.clear(Color.valueOf("1B88E7"));
game.win.apply();
game.batch.setProjectionMatrix(game.win.getCamera().combined);

game.batch.begin();
Map.render();
point.render();
game.batch.end();
UIStage.act(delta);
UIStage.draw();
stage.act(delta);
stage.draw();

	}
public void listInput() {
	lista.addListener(new ChangeListener(){

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			StageId = lista.getSelectedIndex();
			point.Goto(pong, StageId, select);
			cam.zoom = 1f;
			lista.clear();
			if(data.isLevelUnlocked(StageId)) {
			    game.setScreen(new GameScreenCSV(game, StageId));
			}			
	
		}});
}


	@Override
	public void dispose() {
		AssetLoader.unloadSkin();
	}
public void input(float delta) {
	float speed = 400f;
	if(Gdx.input.isKeyPressed(Input.Keys.D)) {
		cam.position.x += delta * speed;
	}
	if(Gdx.input.isKeyPressed(Input.Keys.A)) {
		cam.position.x -= delta * speed;
	}
	if(Gdx.input.isKeyPressed(Input.Keys.W)) {
		cam.position.y += delta * speed;
	}
	if(Gdx.input.isKeyPressed(Input.Keys.S)) {
		cam.position.y -= delta * speed;
	}
	if(Gdx.input.isKeyPressed(Input.Keys.R)) {
		cam.zoom += delta;
	}
	if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
		cam.zoom -= delta;

	}
	
	
}
public void follow() {
	float mapWidth = game.win.getWorldWidth();
	float mapHeight = game.win.getWorldHeight();
	 float halfWidth = game.win.getWorldWidth() * cam.zoom / 2f;
	    float halfHeight = game.win.getWorldHeight() * cam.zoom / 2f;

	    cam.position.x = MathUtils.clamp(
	        pong.getX(),
	        halfWidth,
	        mapWidth - halfWidth
	    );

	    cam.position.y = MathUtils.clamp(
	        pong.getY(),
	        halfHeight,
	        mapHeight - halfHeight
	    );

	cam.update();

}


}
