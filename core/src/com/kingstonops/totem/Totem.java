package com.kingstonops.totem;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.kingstonops.totem.input.InputSystem;
import com.kingstonops.totem.input.RawInput;
import com.kingstonops.totem.physics.ColliderSystem;
import com.kingstonops.totem.physics.MovementSystem;
import com.kingstonops.totem.player.PlayerControllerSystem;
import com.kingstonops.totem.rendering.CameraSystem;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.screens.GameScreen;
import com.kingstonops.totem.world.AISystem;
import com.kingstonops.totem.world.DoorSystem;
import com.kingstonops.totem.world.WorldSystem;
import com.kingstonops.totem.world.guys.DialougeSystem;
import com.kingstonops.totem.world.guys.InteractionSystem;

public class Totem extends Game {

	public static String VERSION = "a0_0_1";
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
	private RawInput m_raw_input;
	private Batch m_batch;
	private BitmapFont m_font;

	@Override
	public void create(){
		Box2D.init();
		m_engine = new Engine();
		m_engine.addSystem(new DialougeSystem(this));
		m_engine.addSystem(new InteractionSystem(this));
		m_engine.addSystem(new AISystem(this));
		m_engine.addSystem(new WorldSystem(this));
		m_engine.addSystem(new ColliderSystem());
		m_engine.addSystem(new MovementSystem());
		m_engine.addSystem(new DoorSystem(this));
		m_engine.addSystem(new PlayerControllerSystem(m_engine));
		InputSystem input = new InputSystem();

		Gdx.input.setInputProcessor(input);
		m_engine.addSystem(input);
		RenderSystem r = new RenderSystem(m_engine);
		r.setProcessing(true);
		m_engine.addSystem(r);

		m_engine.addSystem(new CameraSystem());

		m_font = new BitmapFont();
		m_batch = new SpriteBatch();
		setScreen(new GameScreen(this));
		System.out.println("created Totem!");
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
