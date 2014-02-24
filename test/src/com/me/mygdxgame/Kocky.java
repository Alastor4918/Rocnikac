package com.me.mygdxgame;


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
	public Array<ButtonStyle> hlstyl;
	public  Array<MyButton> hodeneKocky;
	public  Array<MyButton> hracoveKocky;
	public  BooleanArray pickedN;
	private Array<Integer> sortedCubes;
	Heckmeck game;
	

	public Kocky(Heckmeck g) {
		game=g;
		styl= new Array<ButtonStyle>();
		hlstyl =  new Array<ButtonStyle>();
		hracoveKocky=new Array<MyButton>();
		sortedCubes = new Array<Integer>();
		
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
		Boolean mam=false;
		Random r= new Random();
		int tempi=0;
		for(int i=0;i<x;i++){
			sortedCubes.add(r.nextInt(6)+1);
		}
		sortedCubes.sort();
		sortedCubes.reverse();
		for(int i=0;i<x;i++){
			hodeneKocky.get(i).setValue(sortedCubes.get(i));
			for(MyButton y : hracoveKocky){
				if(y.getValue()==hodeneKocky.get(i).getValue()){
					mam=true;
				}
			}
			if(mam){
			hodeneKocky.get(i).setStyle(styl.get(sortedCubes.get(i)));
			}else{
				hodeneKocky.get(i).setStyle(hlstyl.get(sortedCubes.get(i)));			
			}
			mam=false;
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
								hodeneKocky.get(i).setStyle(styl.get(0));
						}
						hodeneKocky.get(i).setBlocked();
					}
					game.board.hraci.get(game.board.onMove).addCubes(((MyButton)actor), counter);	
					pickedN.set(((MyButton)actor).getValue(), true);
					//zrusi highlight kociek ktore si hrac nevybral
					for(MyButton x : hodeneKocky){
						if(x.getStyle() !=styl.get(0))
							x.setStyle(styl.get(x.getValue()));
					}
					//highlightneme roll button
					Drawman.roll.setVisible(true);
					//highlight mensich rovnych
					for(MyButton x : game.grill.grill){
						if(game.board.verify(x.getValue())){
							if(!x.isDisabled()){
							x.setStyle(game.grill.hlgrillStyle.get(x.getValue()-21));		
							}
						}else{
							if(!x.isDisabled()){
							x.setStyle(game.grill.grillStyle.get(x.getValue()-21));
							}
						}
					}
					//hightlight playerovych vrchnych kamenoch
					for(Player x: game.board.hraci){
						if(game.board.verifyT(x.getTop().getValue(),game.board.onMove)){
							if(x.getTop().getValue()!=0){
							x.getTop().setStyle(game.grill.hlgrillStyle.get(x.getTop().getValue()-21));
							}
						}else{
							if(x.getTop().getValue()!=0){
							x.getTop().setStyle(game.grill.grillStyle.get(x.getTop().getValue()-21));
							}
						}						
					}
					
					for(int i=0;i<8;i++){
						if(hracoveKocky.get(i).getValue()>0){
							hracoveKocky.get(i).setStyle(styl.get(hracoveKocky.get(i).getValue()));
						}
					}
					
					
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
				TextureAtlas  cubesTextureAtlas = new TextureAtlas("cubes/cubes.pack");
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
				
				cubesTextureAtlas = new TextureAtlas("cubes/hlcubes.pack");
				buttonSkin = new Skin(cubesTextureAtlas);
				for(int i=0;i<7;i++){
					buttonStyle= new ButtonStyle();
					buttonStyle.up= buttonSkin.getDrawable(Integer.toString(i));
					buttonStyle.down= buttonSkin.getDrawable(Integer.toString(i));
					buttonStyle.checked= buttonSkin.getDrawable(Integer.toString(i));
					buttonStyle.pressedOffsetX = 1;
					buttonStyle.pressedOffsetY = -1;
					hlstyl.add(buttonStyle);
 				}
				
				
	}
	
}

