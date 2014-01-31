package heckmack;


import com.badlogic.gdx.utils.Array;

public class Player {
	private GameBoard board;
	private int ID,picked,sum;
	private MyButton topStone;
	private Array<MyButton>  myStones;
	
	public Player(GameBoard g,int id,int posun){
		board=g;
		ID=id;
		picked=sum=0;
		myStones= new Array<MyButton>();
		topStone=new MyButton(0, board.empty.getStyle());
		topStone.setWidth(64);
		topStone.setHeight(128);
		topStone.setPosition(20, 325-posun);
	}
	
	public int getID(){
		return ID;
	}
	
	public int getPicked(){
		return picked;
	}
	
	public MyButton getTop(){
		return topStone;
	}
	
	public Array<MyButton> getStones(){
		return myStones;
	}
	
	
	public void addStone(MyButton s){
		myStones.add(s);
		topStone=new MyButton(s.getValue(), s.getStyle());
	}
	
	public void addCubes(MyButton c, int number){
		for(int i=picked;i<picked+number;i++){
			board.playerCubes.get(i).setValue(c.getValue());
			board.playerCubes.get(i).setStyle(c.getStyle());
		}
		picked+=number;
		if(c.getValue() !=6)
			sum+=number*c.getValue();
		else
			sum+=number*5;
	}
	
	public int getSum(){
		return sum;
	}
	
	public void hraj() {
		picked=sum=0;
	}
	
	
}
