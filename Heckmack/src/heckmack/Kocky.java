package heckmack;

import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;


public class Kocky{
	public Array<ButtonStyle> styl;
	public  Array<MyButton> hodeneKocky;
	public  Array<MyButton> hracoveKocky;
	public  BooleanArray pickedN;
	private LinkedList<Integer> sortedCubes;
	Heckmeck game;
	

	public Kocky(Heckmeck g) {
		game=g;
		styl= new Array<ButtonStyle>();
		hracoveKocky=new Array<MyButton>();
		sortedCubes = new LinkedList<Integer>();
		
		//---------------TVORBA STYLOV KOCIEK-------------
		getStyl();
		//----------------PRIPRAVA BOOLARRAY-----------
		 pickedN= new BooleanArray();
		    for(int i=0;i<7;i++){
		    	pickedN.add(false);
		 }
		//--------------KOCKY NA HADZANIE-----------------
		hodeneKocky=new Array<MyButton>();
		throwingDice();
	    //-----------HRACOVE KOCKY---------------
		playerDice();
		setBlank(hracoveKocky);
	}
	

	public void setBlank(Array<MyButton> x){
		for(int i=0;i<8;i++){
			x.get(i).setValue(0);
			x.get(i).setStyle(styl.get(0));
		}
	}
	
	public void shuffle(int x){
		Random r= new Random();
		int tempi=0;
		for(int i=0;i<x;i++){
			sortedCubes.add(r.nextInt(6)+1);
		}
		java.util.Collections.sort(sortedCubes);
		java.util.Collections.reverse(sortedCubes);
		for(int i=0;i<x;i++){
			hodeneKocky.get(i).setValue(sortedCubes.get(i));
			hodeneKocky.get(i).setStyle(styl.get(sortedCubes.get(i)));
			tempi++;
		}
		sortedCubes.clear();
		for(int i=tempi;i<8;i++){
			hodeneKocky.get(i).setValue(0);
			hodeneKocky.get(i).setStyle(styl.get(0));
		}
	}
	
	private void playerDice(){
		int y=0;
		for(int i=0;i<8;i++){
			hracoveKocky.add(new MyButton(0, new ButtonStyle()));
			hracoveKocky.get(i).setWidth(64);
			hracoveKocky.get(i).setHeight(64);
			hracoveKocky.get(i).setPosition(120+y, 36);
			y+=70;
		}
	}
	
	private void throwingDice(){
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
					game.board.hraci.get(game.board.onMove).addCubes(((MyButton)actor), counter);	
					pickedN.set(((MyButton)actor).getValue(), true);
					Drawman.hod.setVisible(true);
					}
				else{
					System.out.println("NEMOZES");
				}
			}
		};
		int y=0;
	    for(int i=0;i<8;i++){
	    	hodeneKocky.add(new MyButton(0, new ButtonStyle()));
	    	hodeneKocky.get(i).setWidth(64);
			hodeneKocky.get(i).setHeight(64);
			hodeneKocky.get(i).setPosition(120+y, 120);
			hodeneKocky.get(i).addListener(listener);
			y+=70;
	    }
	}
	
	private void getStyl(){
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
					styl.add(buttonStyle);
				}
	}
		
}

