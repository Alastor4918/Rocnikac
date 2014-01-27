package heckmack;

import com.badlogic.gdx.utils.Array;

public class Player {
	private GameBoard board;
	private int ID;
	private Array<MyButton> myCubes;
	private Array<MyButton>  myStones;
	
	public Player(GameBoard g,int id){
		board=g;
		ID=id;
		myCubes=new Array<MyButton>();
		myStones= new Array<MyButton>();
	}
	
	public int getID(){
		return ID;
	}
	
	public Array<MyButton> getStones(){
		return myStones;
	}
	
	public Array<MyButton> getCubes(){
		return myCubes;
	}
	
	public void addStone(MyButton s){
		myStones.add(s);
	}
	
	public void addCubes(MyButton c, int number){
		MyButton temp= c;
		for(int i=0;i<number;i++){
			myCubes.add(temp);
		}
	}
	
	
}
