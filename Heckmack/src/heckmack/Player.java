package heckmack;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;

public class Player {
	private Heckmeck game;
	private int ID,sum,highest;
	public int picked,score;
	private MyButton topStone;
	private Array<Integer>  myStones;
	
	public Player(Heckmeck g,int id,int posun){
		game=g;
		ID=id;
		picked=sum=score=highest=0;
		myStones= new Array<Integer>();
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
	
	public Array<Integer> getStones(){
		return myStones;
	}
	
	public void getScore(int x){
			if(x<25)
				score+=1;
			else if(x<29)
				score+=2;
			else if(x<32)
				score+=3;
			else
				score+=4;
	}
	
	public void reduceScore(int x){
		if(x<25)
			score-=1;
		else if(x<29)
			score-=2;
		else if(x<32)
			score-=3;
		else
			score-=4;
		
	}
	
	public int getHighest(){
		myStones.sort();
		return myStones.pop();
	}
	
	
	public void addStone(MyButton s){
		myStones.add(s.getValue());
		topStone.setValue(s.getValue());
		topStone.setStyle(s.getStyle());
		getScore(s.getValue());
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
				game.grill.otoc(topStone.getValue());
				game.grill.blockBiggest(topStone.getValue());
				changeTopStone();
		}
		
	}
	
	private void changeTopStone(){
		if(myStones.size!=0){
			reduceScore(myStones.peek());
			myStones.pop();
			if(myStones.size!=0){
				topStone.setValue(myStones.get(myStones.size-1));
				topStone.setStyle(game.grill.grillStyle.get(topStone.getValue()-21));
			}
			else{
				topStone.setValue(0);
				topStone.setStyle(game.grill.empty.getStyle());
			}
		}
	}
	
	private void getTopStone(int posun){
		ChangeListener cListener= new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(myStones.size!=0){
						if(game.board.verifyT(((MyButton)actor).getValue(), getID() )){
							game.board.hraci.get(game.board.onMove).addStone(((MyButton)actor));
							changeTopStone();
							game.board.nextOne();
							Drawman.hod.setVisible(true);
							//premazanie highlightu
							for(MyButton x : game.grill.grill){
								x.setStyle(game.grill.grillStyle.get(x.getValue()-21));
								}
							for(Player x: game.board.hraci){
								if(x.getTop().getValue()!=0){
									x.getTop().setStyle(game.grill.grillStyle.get(x.getTop().getValue()-21));
									}						
						}
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
