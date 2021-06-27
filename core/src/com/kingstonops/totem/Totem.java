package com.kingstonops.totem;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Totem extends Game {

	public Batch batch() {
		return m_batch;
	}

	public BitmapFont font() {
		return m_font;
	}

	public Engine engine(){
		return m_engine;
	}

	private Engine m_engine;
	private Batch m_batch;
	private BitmapFont m_font;

	@Override
	public void create(){
		m_engine = new Engine();
		m_engine.addSystem(new MovementSystem());

		m_font = new BitmapFont();
		m_batch = new SpriteBatch();
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose(){}


}


/*package com.kingstonops.totem;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class Totem extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shapeRenderer;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();


		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(0,1,0,1);
		shapeRenderer.circle(200,200,75);
		shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}*/
