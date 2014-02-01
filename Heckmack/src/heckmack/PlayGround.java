package heckmack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PlayGround implements Screen {
	Heckmeck game;
	Stage stage;
	Drawman d;
	public PlayGround(Heckmeck g, Stage s){
		game=g;
		stage=s;
		d= new Drawman(game);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		d.draw();		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		if(game.getEnd()){
			System.out.println("This is the end...");
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
	

}
