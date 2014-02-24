package com.me.mygdxgame;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;

public class Grill {
	Array<MyButton> grill;
	BooleanArray grillState;
	Array<ButtonStyle> grillStyle;
	Array<ButtonStyle> hlgrillStyle;
 	Heckmeck game;
	MyButton empty;
	MyButton back;
	
	public Grill(Heckmeck g){
		game=g;
		grill=new Array<MyButton>();
		grillStyle= new Array<ButtonStyle>();
		hlgrillStyle = new Array<ButtonStyle>();
		grillState= new BooleanArray();
		for(int i=0;i<16;i++){
			grillState.add(false);
		}
		getGrillStyle();
		getGrill();
	}
	
	public void otoc(int x){
		grill.get(x-21).setStyle(grillStyle.get(x-21));
		grill.get(x-21).setDisabled(false);
	}
	
	public void blockBiggest(int x){
		for(int i=grill.size-1;i>=0;i--){
			if(!grill.get(i).isDisabled()){
				if(grill.get(i).getValue()!=x){
					grill.get(i).setStyle(back.getStyle());
					grill.get(i).setDisabled(true);
					grillState.set(i, true);
					if(verify()){
						game.setEnd();
					}
				}
				break;
			}
		}
	}
	
	private void getGrillStyle(){
		TextureAtlas  buttonTextureAtlas = new TextureAtlas("grill/grill.pack");
		TextureAtlas  hlbuttonTextureAtlas = new TextureAtlas("grill/highlight.pack");
		Skin buttonSkin = new Skin(buttonTextureAtlas);
		Skin hlButtonSkin = new Skin(hlbuttonTextureAtlas);
		ButtonStyle buttonStyle;
		//------EMPTY ONE------------
		buttonStyle= new ButtonStyle();
		buttonStyle.up= buttonSkin.getDrawable("empty");
		buttonStyle.down= buttonSkin.getDrawable("empty");
		buttonStyle.checked= buttonSkin.getDrawable("empty");
		buttonStyle.pressedOffsetX = 1;
		buttonStyle.pressedOffsetY = -1;
		empty=new MyButton(0, buttonStyle);
		//------DARK SIDE--------------
		buttonStyle= new ButtonStyle();
		buttonStyle.up= buttonSkin.getDrawable("back");
		buttonStyle.down= buttonSkin.getDrawable("back");
		buttonStyle.checked= buttonSkin.getDrawable("back");
		buttonStyle.pressedOffsetX = 1;
		buttonStyle.pressedOffsetY = -1;
		back=new MyButton(0, buttonStyle);
		//---------------------------
		
		for(int i=21;i<37;i++){
			buttonStyle= new ButtonStyle();
			buttonStyle.up= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.down= buttonSkin.getDrawable("empty");
			buttonStyle.checked= buttonSkin.getDrawable(Integer.toString(i));
			buttonStyle.pressedOffsetX = 1;
			buttonStyle.pressedOffsetY = -1;
			grillStyle.add(buttonStyle);
			
			buttonStyle= new ButtonStyle();
			buttonStyle.up= hlButtonSkin.getDrawable(Integer.toString(i));
			buttonStyle.down= buttonSkin.getDrawable("empty");
			buttonStyle.checked= hlButtonSkin.getDrawable(Integer.toString(i));
			buttonStyle.pressedOffsetX = 1;
			buttonStyle.pressedOffsetY = -1;
			hlgrillStyle.add(buttonStyle);
			
			
		}
		
	}
	
	private boolean verify(){
		boolean result1=true;
		boolean result2=true;
		
		for(int i=0;i<16;i++){
			if(!grillState.get(i)){
				result1=false;
				break;
			}
		}
		for(int i=0;i<16;i++){
			if(!grill.get(i).isDisabled()){
				result2=false;
				break;
			}
		}
		return (result1 || result2);
	}
	
	private void getGrill(){
			MyButton temp;
			//------LISTENER-----------
			ChangeListener cListener= new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(!((MyButton) actor).isDisabled()){
							if(game.board.verify(((MyButton)actor).getValue())){
								game.board.hraci.get(game.board.onMove).addStone(((MyButton)actor));
								((MyButton)actor).setStyle(empty.getStyle());
								((MyButton)actor).setDisabled(true);
								if(verify()){
									game.setEnd();
								}
								game.board.nextOne();
								Drawman.roll.setVisible(true);
								for(MyButton x : grill){
									if(!x.isDisabled()){
									x.setStyle(grillStyle.get(x.getValue()-21));
									}
									}
								for(Player x: game.board.hraci){
									if(x.getTop().getValue()!=0){
										x.getTop().setStyle(grillStyle.get(x.getTop().getValue()-21));
										}						
							}
							}
							else
								System.out.println("Nemas dost bodov !");
									
							}
						else{
							System.out.println("Uz zobraty !");
						}
					}
				};
			//-------
			int y=0;
			int x=0;
			
			for(int i=21;i<37;i++){
					temp=new MyButton(i,grillStyle.get(i-21));
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
					grill.add(temp);	
				}
	}
}
