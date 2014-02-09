package heckmack;


import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;

public class GameBoard implements Screen{
	Heckmeck game;
	Stage stage;
	OrthographicCamera camera;
	TextButton hod;
	MyButton empty;
	Array<MyButton> grill;
	Array<ButtonStyle> kocky;
	Array<MyButton> hodeneKocky;
	Array<MyButton> playerCubes;
	Array<MyButton> playersTopStones;
	BooleanArray pickedN;
	Array<Player> hraci;
	private int onMove;
	
	
	public GameBoard(Heckmeck gam, int numP){
		//-----BASE-----------------
		game=gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		stage = new Stage(800,480,true);
		stage.clear();
	    Gdx.input.setInputProcessor(stage);
	    grill = this.getGrill();
	    pickedN= new BooleanArray();
	    for(int i=0;i<7;i++){
	    	pickedN.add(false);
	    }
	    //---------------HRACI-----------------
	    playersTopStones=new Array<MyButton>();
	    hraci= new Array<Player>();
	    for(int i=0;i<numP;i++){
	    	hraci.add(new Player(game, i, i*145));
	    	playersTopStones.add(hraci.get(i).getTop());
	    }
	    //--------------HRACOVE KOCKY---------------
	    onMove=0;
	    playerCubes=new Array<MyButton>();
		int y=0;
		for(int i=0;i<8;i++){
			playerCubes.add(new MyButton(0, new ButtonStyle()));
			playerCubes.get(i).setWidth(64);
			playerCubes.get(i).setHeight(64);
			playerCubes.get(i).setPosition(120+y, 36);
			y+=70;
		}
	    //--------------BUTTON PRE HADZ A SKONCI-----------
	    TextButtonStyle s= game.getButtonStyle();
	    ChangeListener cListener = new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			int temp=8-hraci.get(onMove).getPicked();
			shuffle(temp);
			for(int i=0;i<temp;i++){
				hodeneKocky.get(i).setFree();
			}
		}
	    };
	    ChangeListener stener = new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				nextOne();
			}
		    };
	    TextButton hod= game.createButton("Hadz !", s, 50,100, cListener);
	    TextButton skonci = game.createButton("End Turn", s, 50, 100, stener) ;   
	    
	    
	    //------------GRILL IDE NA STAGE -------------------
	    for(int i=0;i<grill.size;i++){
	    	stage.addActor(grill.get(i));
	    }
	    //------------TVORBA PREDLOHY PRE KOCKY
	    kocky= this.getCubes();
	    //------------KOCKY NA HADZANIE-------------
	    hodeneKocky=new Array<MyButton>();
	    ChangeListener listener= new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(!((MyButton)actor).isBlocked() && !pickedN.get(((MyButton)actor).getValue())){
					int counter=0;
					for(int i=0;i<hodeneKocky.size;i++){
						if(hodeneKocky.get(i).getValue() ==((MyButton)actor).getValue() ){
								counter++;
						}
						hodeneKocky.get(i).setBlocked();
					}
					hraci.get(onMove).addCubes(((MyButton)actor), counter);	
					pickedN.set(((MyButton)actor).getValue(), true);
					}
				else{
					System.out.println("NEMOZES");
				}
			}
		};
		y=0;
	    for(int i=0;i<8;i++){
	    	hodeneKocky.add(new MyButton(0, new ButtonStyle()));
	    	hodeneKocky.get(i).setWidth(64);
			hodeneKocky.get(i).setHeight(64);
			hodeneKocky.get(i).setPosition(120+y, 120);
			hodeneKocky.get(i).addListener(listener);
			y+=70;
	    }
	    //-----------------KOCKY IDU NA STAGE--------------
	    shuffle(0);
	    for(int i=0;i<hodeneKocky.size;i++){
	    	stage.addActor(hodeneKocky.get(i));
	    }
	    
	    
	    //----------------UMIESTNENIE BUTTONOV+HRACOVYCH KOCIEK+VRCHNE KAMENE+ HODENIE NA STAGE----------------
	    hod.setPosition(690, 130);
	    skonci.setPosition(690, 85);
	    stage.addActor(hod);
	    stage.addActor(skonci);
	    
	    setBlank(playerCubes);
	    for(int i=0;i<8;i++){
	    	stage.addActor(playerCubes.get(i));
	    }
	    
	    for(int i=0;i<playersTopStones.size;i++){
	    	stage.addActor(playersTopStones.get(i));
	    }
	}

	@Override
	public void render(float delta) {
		
			Gdx.gl.glClearColor(0, 0.4f, 1, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			BitmapFont f= new BitmapFont();
			game.batch.begin();
			f.draw(game.batch,"On move : "+ Integer.toString(onMove), 700, 440);
			f.draw(game.batch, "My top:", 700, 410);
			f.draw(game.batch, "Player 1:", 20, 470);
			f.draw(game.batch, "Player 2:", 20, 325);
			//f.draw(game.batch, "Player 3:", 20, 170); zatial nic
			f.draw(game.batch,"Sum : "+ hraci.get(onMove).getSum(), 700, 80);
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
		game.dispose();
	}
	
	
	public void nextOne(){
		this.onMove=(this.onMove+1)%this.hraci.size;
		setBlank(hodeneKocky);
		setBlank(playerCubes);
		for(int i=0;i<7;i++){
			pickedN.set(i, false);
		}
		this.hraci.get(onMove).hraj();
	}
	
	
	public void setBlank(Array<MyButton> x){
		for(int i=0;i<8;i++){
			x.get(i).setValue(0);
			x.get(i).setStyle(this.kocky.get(0));
		}
	}
	
	public void shuffle(int x){
		Random r= new Random();
		
		int tempi=0;
		for(int i=0;i<x;i++){
			int temp= r.nextInt(6)+1;
			hodeneKocky.get(i).setValue(temp);
			hodeneKocky.get(i).setStyle(this.kocky.get(temp));
			tempi++;
		}
		
		for(int i=tempi;i<8;i++){
			hodeneKocky.get(i).setValue(0);
			hodeneKocky.get(i).setStyle(this.kocky.get(0));
		}
	}
	
	Array<MyButton> getGrill(){
		Array<MyButton> result=new Array<MyButton>();
		//style------------
		TextureAtlas  buttonTextureAtlas = new TextureAtlas("grill/grill.pack");
		Skin buttonSkin = new Skin(buttonTextureAtlas);
		ButtonStyle buttonStyle;
		MyButton temp;
		//------LISTENER-----------
		ChangeListener cListener= new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(((MyButton) actor).isBlocked()){
						System.out.println("BLOCKED !"+Integer.toString(((MyButton) actor).getValue()));
						((MyButton)actor).setChecked(true);
						((MyButton)actor).setFree();
					}
				else{
					System.out.println("DONE BITCH");
					((MyButton)actor).setBlocked();
				}
			}
		};
		//-------
		int y=0;
		int x=0;
		//------EMPTY ONE------------
		buttonStyle= new ButtonStyle();
		buttonStyle.up= buttonSkin.getDrawable("empty");
		buttonStyle.down= buttonSkin.getDrawable("empty");
		buttonStyle.checked= buttonSkin.getDrawable("empty");
		buttonStyle.pressedOffsetX = 1;
		buttonStyle.pressedOffsetY = -1;
		empty=new MyButton(0, buttonStyle);
		//---------------------------
		
		for(int i=21;i<37;i++){
			buttonStyle= new ButtonStyle();
			buttonStyle.up= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.down= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.checked= buttonSkin.getDrawable("empty");
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
			temp.addListener(cListener);
			result.add(temp);
			
		}
		
		return result;
	}
	
	Array<ButtonStyle> getCubes(){
		Array<ButtonStyle> result=new Array<ButtonStyle>();
		//style------------
			TextureAtlas  cubesTextureAtlas = new TextureAtlas("cubes/cube.pack");
			Skin buttonSkin = new Skin(cubesTextureAtlas);
			ButtonStyle buttonStyle;
		//---------------------------
		
		for(int i=0;i<7;i++){
			buttonStyle= new ButtonStyle();
			buttonStyle.up= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.down= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.checked= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.pressedOffsetX = 1;
			buttonStyle.pressedOffsetY = -1;
			result.add(buttonStyle);
		}
		return result;
	}

}
