package heckmack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

public class GameBoard implements Screen{
	GameStart game;
	Stage stage;
	OrthographicCamera camera;
	Array<MyButton> grill;
	Array<MyButton> kocky;
	
	public GameBoard(GameStart gam){
		game=gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		stage = new Stage(800,480,true);
		stage.clear();
	    Gdx.input.setInputProcessor(stage);
	    
	   grill = this.createGrill();
	    
	    for(int i=0;i<grill.size;i++){
	    	stage.addActor(grill.get(i));
	    }
	    
	    
	    
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.4f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
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
	
	
	Array<MyButton> createGrill(){
		Array<MyButton> result=new Array<MyButton>();
		//style------------
		TextureAtlas buttonTextureAtlas = new TextureAtlas("grill/grill.pack");
		Skin buttonSkin = new Skin(buttonTextureAtlas);
		ButtonStyle buttonStyle;
		MyButton temp;
		ChangeListener cListener;
		int y=0;
		int x=0;
		//---------------------------
		
		for(int i=21;i<37;i++){
			buttonStyle= new ButtonStyle();
			buttonStyle.up= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.down= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.checked= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.pressedOffsetX = 1;
			buttonStyle.pressedOffsetY = -1;
			temp=new MyButton(i,buttonStyle);
			temp.setWidth(64);
			temp.setHeight(128);
			if(i<29){
				temp.setPosition(120+y, 344);
				y+=70;
			}
			else{
				temp.setPosition(120+x,208);
				x+=70;
			}
			cListener= new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(((MyButton) actor).isBlocked()){
							System.out.println("BLOCKED !"+Integer.toString(((MyButton) actor).getValue()));
						}
					else{
						System.out.println("DONE BITCH");
					}
				}
			};
			temp.addListener(cListener);
			result.add(temp);
			
		}
		
		return result;
	}
	
	Array<MyButton> getCubes(){
		Array<MyButton> result=new Array<MyButton>();
		//style------------
				TextureAtlas buttonTextureAtlas = new TextureAtlas("grill/grill.pack");
				Skin buttonSkin = new Skin(buttonTextureAtlas);
				ButtonStyle buttonStyle;
				MyButton temp;
				ChangeListener cListener;
				int y=0;
				int x=0;
				//---------------------------
		
		return result;
	}

}
