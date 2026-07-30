package Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ponsit.dopong3.Main;

public class Test1 extends ScreenAdapter {
Main game;
BitmapFont font;
ShapeRenderer render;
Rectangle rec;
Circle ball;
Polygon custom;
Polygon custom1;
Polygon custom2;
float r = 0f;
float g = 0f;
float b = 0f;
float timer = 0f;
public Test1(Main game) {
	this.game = game;
}
	public void show() {
		render = new ShapeRenderer();
		rec = new Rectangle(320,300,50,50);
	 font = new BitmapFont(Gdx.files.internal("Scene2d/source/GameFont.fnt"));
		float[] vertices = {0,0,50,100,100,0};
		float[] vertices2 = {0,0,0,35,35,35,35,0};
		float[] vertices3 = {0,0,0,40,20,60,40,40,40,0};
		// 0,0,50,100,100,0    △
		// 0,0,0,35,35,35,35,0 ■
		//
		
		custom = new Polygon(vertices);
		custom1 = new Polygon(vertices2);
		custom2 = new Polygon(vertices3);

		custom.setPosition(400, 300);
		custom1.setPosition(200, 300);
		custom2.setPosition(200, 100);
		String triangle= "\u25B3";
		String cube="\u25A0";
		String pentagon="⬠";
		System.out.println(triangle);
		System.out.println(cube);
		System.out.println(pentagon);
		System.out.println(
			    Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight() +
			    " world=" +
			    game.win.getWorldWidth() + "x" +
			    game.win.getWorldHeight()
			);
		System.out.println("Camera: " + game.win.getCamera().viewportWidth + " x " +
                game.win.getCamera().viewportHeight);
	}
	public void render(float delta) {
	
input(delta);
timer += delta;

if(timer >= Double.MIN_VALUE) {
	r = MathUtils.random();
	g = MathUtils.random();
	b = MathUtils.random();

timer = 0f;
}
ScreenUtils.clear(r,g,b,1);	


		game.win.apply();		
		game.batch.setProjectionMatrix(game.win.getCamera().combined);
		render.setProjectionMatrix(game.win.getCamera().combined);
		render.begin(ShapeRenderer.ShapeType.Filled);
    	render.rect(rec.x,rec.y,rec.width,rec.height);

		render.end();
		render.begin(ShapeRenderer.ShapeType.Line);
		render.polygon(custom.getTransformedVertices());
		render.end();
		render.begin(ShapeRenderer.ShapeType.Line);
		render.polygon(custom1.getTransformedVertices());
		render.end();
		render.begin(ShapeRenderer.ShapeType.Line);
		render.polygon(custom2.getTransformedVertices());
		render.end();
		game.batch.begin();
		font.draw(game.batch, "Screen Color =:"+ String.format("%.2f",r) + String.format("%.2f",g) + String.format("%.2f",b) ,40,660);
		game.batch.end();
	}
	public void resize(int width,int height) {
		game.win.update(width, height,true);
	}
	public void input(float delta) {
	float speed = 120f;
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			rec.x += delta * speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			rec.x -= delta * speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			rec.y += delta * speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			rec.y -= delta * speed;
		}
	}
	
	public void dispose() {
		
	}
}
