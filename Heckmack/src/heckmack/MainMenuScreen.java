package heckmack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class MainMenuScreen implements Screen {

	GameStart game;

	OrthographicCamera camera;
	
	Stage stage;
	Texture logo;
	TextureRegion logo2,background;
	
	
	public MainMenuScreen(GameStart gam) {
		game = gam;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		stage = new Stage(800,480,true);
		stage.clear();
	    Gdx.input.setInputProcessor(stage);
	    
	    logo= new Texture(Gdx.files.internal("heckmeck2.png"));
	    Texture b=new Texture(Gdx.files.internal("back2.png"));
	    logo2 = new TextureRegion(b, 0, 0, 292, 226);
	    
	    Texture temp= new Texture(Gdx.files.internal("wat/background.png"));
		background = new TextureRegion(temp, 0, 320, 800, 480);
	    
	    
	    //-----------------
	    TextButtonStyle s= game.getButtonStyle();
	    ChangeListener cListener = new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			System.out.println("CHanged !!");			
		}
	    };
	    TextButton button= game.createButton("Option", s, 50,150, cListener);
	    
	    ChangeListener cListener2 = new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameBoard(game));
				dispose();		
			}
		    };
		TextButton button2= game.createButton("Single Player", s, 50,150, cListener2);
		
		 ChangeListener cListener3 = new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					Gdx.app.exit();			
				}
			    };
		TextButton button3= game.createButton("Exit", s, 50,150, cListener3);
		
		ChangeListener cListener4 = new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("wat");			
			}
		    };
	TextButton button4= game.createButton("Multi Player", s, 50,150, cListener4);
	    
	    //---------------
	    
		    
	    stage.addActor(button);
	    button.setPosition(30, 150);
	    stage.addActor(button2);
	    button2.setPosition(30, 250);
	    stage.addActor(button4);
	    button4.setPosition(30, 200);
	    stage.addActor(button3);
	    button3.setPosition(30, 100);
	   

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.4f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		
		
		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.draw(logo, 200, 170);
	    game.batch.draw(logo2, 400, 20);
	    game.batch.end();
	    
	    stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		camera.update();		
		
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
		stage.dispose();
	}


        //...Rest of class omitted for succinctness.

}
