package Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ponsit.dopong3.Main;

public class Test extends ScreenAdapter  {
Main game;
ShapeRenderer render;
BitmapFont font;
Rectangle rec;
Polygon custom;
Polygon custom1;
Polygon custom2;
 Circle ball;
 Color color;
Color color1;
float g;
public Test(Main game){
	this.game = game;
}
	@Override
	public void show() {
		render = new ShapeRenderer();
		rec = new Rectangle(50,50,100 ,60);
		ball = new Circle(540,330,60);
		 color = new Color(Color.GOLD);
		
		 color1 = new Color(Color.GREEN);
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
	}

	@Override
	public void render(float delta) {
ScreenUtils.clear(0,g,0,0);
		game.win.apply();
		moveSet();
		render.setProjectionMatrix(game.win.getCamera().combined);
		render.begin(ShapeRenderer.ShapeType.Line);
		render.setColor(color);
    	render.rect(rec.x,rec.y,rec.width,rec.height);
    	render.end();
    	render.begin(ShapeRenderer.ShapeType.Filled);
    	render.setColor(color1);
    	render.circle(ball.x,ball.y,ball.radius);
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
		font.draw(game.batch, "Green Color =:"+ String.format("%.2f",g)  ,40,660);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		game.win.update(width, height,true);
	}

	

	

	@Override
	public void dispose() {
		game.batch.dispose();
		
	}
public void moveSet() {
	if(Gdx.input.isKeyPressed(Input.Keys.D)) {
rec.x += 200 * Gdx.graphics.getDeltaTime();
	}
	if(Gdx.input.isKeyPressed(Input.Keys.A)) {
		rec.x -= 200 * Gdx.graphics.getDeltaTime();
			}
	if(Gdx.input.isKeyPressed(Input.Keys.W)) {
		rec.y += 200 * Gdx.graphics.getDeltaTime();
			}
	if(Gdx.input.isKeyPressed(Input.Keys.S)) {
		rec.y -= 200 * Gdx.graphics.getDeltaTime();
			}
	if(Gdx.input.isKeyPressed(Input.Keys.R)) {
		color.set(
				MathUtils.random(),
				MathUtils.random(),
				MathUtils.random(),0f);
		
	}
	if(Gdx.input.isKeyPressed(Input.Keys.F)) {
		rec.setPosition(
						MathUtils.random(0f,game.win.getWorldWidth() - rec.getWidth()),
						MathUtils.random(0f, game.win.getWorldHeight() - rec.getHeight())
				);
	}
	if(Intersector.overlaps(ball, rec)) {
		g += 0.1f;
			
	}else {
		g -= 0.1f;
	}
	g = MathUtils.clamp(g, 0.1f, 3f);
}
}
