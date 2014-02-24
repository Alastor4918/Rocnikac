package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Intro implements Screen {

	Heckmeck game;

	OrthographicCamera camera;
	
	Texture logo,cucak,skrtic;
	
	TextureRegion background;
	
	BitmapFont font;

	public Intro(Heckmeck gam) {
		game=gam;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		logo= new Texture(Gdx.files.internal("data/heckmeck2.png"));
		cucak=new Texture(Gdx.files.internal("data/cucak.png"));
		skrtic=new Texture(Gdx.files.internal("data/skrtic.png"));
		
		Texture temp= new Texture(Gdx.files.internal("data/background.png"));
		background = new TextureRegion(temp, 0, 320, 800, 480);
		
		font = new BitmapFont();
		font.setColor(Color.BLUE);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.4f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.draw(logo, 200, 150);
		game.batch.draw(cucak, 520, 0);
		game.batch.draw(skrtic, 50, 5);
		font.draw(game.batch, "Tap to continue...", 330, 220);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new MainMenuScreen(game));
			dispose();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}


        //...Rest of class omitted for succinctness.

}