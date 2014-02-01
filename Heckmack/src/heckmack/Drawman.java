package heckmack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class Drawman {
	Heckmeck game;
	public static TextButton hod;
	TextButton skonci;
	public Drawman(Heckmeck g){
		game=g;
		
		//--------------BUTTON PRE HADZ A SKONCI-----------
		getButtons();
		
		hod.setPosition(690, 130);
		skonci.setPosition(690, 85);
		game.stage.addActor(hod);
		game.stage.addActor(skonci);
		
		for(int i=0;i<game.board.playersTopStones.size;i++){
	    	game.stage.addActor(game.board.playersTopStones.get(i));
	    }
	}


public void draw(){
	Gdx.gl.glClearColor(0, 0.4f, 1, 1);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
	BitmapFont f= new BitmapFont();
	game.batch.begin();
	f.draw(game.batch,"On move : "+ Integer.toString(game.board.onMove+1), 700, 440);
	f.draw(game.batch, "My top:", 700, 410);
	f.draw(game.batch, "Player 1 score : "+Integer.toString(game.board.hraci.get(0).score), 675, 380);
	f.draw(game.batch, "Player 2 score : "+Integer.toString(game.board.hraci.get(1).score), 675, 360);
	f.draw(game.batch, "Player 1:", 20, 470);
	f.draw(game.batch, "Player 2:", 20, 325);
	//f.draw(game.batch, "Player 3:", 20, 170); zatial nic
	f.draw(game.batch,"Sum : "+ game.board.hraci.get(game.board.onMove).getSum(), 700, 80);
	game.batch.end();
	
}

private void getButtons(){
	  TextButtonStyle s= game.getButtonStyle();
	    ChangeListener cListener = new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			int temp=8-game.board.hraci.get(game.board.onMove).getPicked();
			game.kocky.shuffle(temp);
			for(int i=0;i<temp;i++){
				game.kocky.hodeneKocky.get(i).setFree();
			}
			((TextButton)actor).setVisible(false);
		}
	    };
	    ChangeListener stener = new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.board.hraci.get(game.board.onMove).failedMove();
				game.board.nextOne();
				Drawman.hod.setVisible(true);
			}
		    };
	    hod= game.createButton("Hadz !", s, 50,100, cListener);
	    skonci = game.createButton("End Turn", s, 50, 100, stener) ;   
		
}

}