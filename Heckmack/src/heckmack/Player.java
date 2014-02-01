package heckmack;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;

public class Player {
	private Heckmeck game;
	private int ID,sum;
	public int picked;
	private MyButton topStone;
	private Array<MyButton>  myStones;
	
	public Player(Heckmeck g,int id,int posun){
		game=g;
		ID=id;
		picked=sum=0;
		myStones= new Array<MyButton>();
		getTopStone(posun);
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
		topStone.setValue(s.getValue());
		topStone.setStyle(s.getStyle());
	}
	
	public void addCubes(MyButton c, int number){
		for(int i=picked;i<picked+number;i++){
			game.kocky.hracoveKocky.get(i).setValue(c.getValue());
			game.kocky.hracoveKocky.get(i).setStyle(c.getStyle());
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
	
	public void failedMove(){
		if(myStones.size!=0){
			if(topStone.getValue() == 36){
				changeTopStone();
				game.grill.otoc(36);
			}
			else{
				game.grill.otoc(topStone.getValue());
				game.grill.blockBiggest();
				changeTopStone();
			}
		}
		
	}
	
	private void changeTopStone(){
		myStones.removeIndex(myStones.size-1);
		if(myStones.size!=0){
			topStone.setValue(myStones.get(myStones.size-1).getValue());
			topStone.setStyle(myStones.get(myStones.size-1).getStyle());
		}
		else{
			topStone.setValue(0);
			topStone.setStyle(game.grill.empty.getStyle());
		}
	}
	
	private void getTopStone(int posun){
		ChangeListener cListener= new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(myStones.size!=0){
						if(game.board.verifyT(((MyButton)actor).getValue())){
							game.board.hraci.get(game.board.onMove).addStone(((MyButton)actor));
							changeTopStone();
							game.board.nextOne();
							Drawman.hod.setVisible(true);
						}
						else
							System.out.println("Nemas dost bodov !");
							
						}
					else{
						System.out.println("Nic nemam !");
					}
				}
			};
		topStone=new MyButton(0, game.grill.empty.getStyle());
		topStone.setWidth(64);
		topStone.setHeight(128);
		topStone.setPosition(20, 325-posun);
		topStone.addListener(cListener);
	}
	
	
}
