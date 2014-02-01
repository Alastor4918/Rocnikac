package heckmack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.utils.Array;

public class EndingScreen implements Screen {
	Heckmeck game;
	Array<Integer> scores;
	String winner;
	int winnerPoints;
	
	public EndingScreen(Heckmeck g){
		game=g;
		scores=new Array<Integer>();
		getWinner();
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		Gdx.gl.glClearColor(0, 0.4f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		game.font.draw(game.batch, "Vyhral hrac cislo : "+(winner+1), 350, 240);
		game.font.draw(game.batch, "S poctom cervikov : "+winnerPoints, 350, 220);
		game.batch.end();
		
		if (Gdx.input.isTouched()) {
			Gdx.app.exit();
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
		
	}
	
	private void getWinner(){
		int max,id;
		max=id=0;
		for(int i=0;i<game.board.hraci.size;i++){
			scores.add(game.board.hraci.get(i).score);
			if(scores.get(i)>max){
				max=scores.get(i);
				id=i;
			}
		}
		winnerPoints=max;
		winner=Integer.toString(id);
	}

}
