package heckmack;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class MyButton extends Button{
	private int value;
	private boolean blocked;
	
	
	public MyButton(int val, ButtonStyle style){
		super(style);
		value=val;
		blocked=true;
	}
	
	public void setBlocked(){
		blocked=true;
	}
	
	public void setFree(){
		blocked=false;
	}
	
	public int getValue(){
		return value;
	}
	
	public boolean isBlocked(){
		return blocked;		
	}

}
