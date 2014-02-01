package heckmack;

import com.badlogic.gdx.utils.Array;

public class Board {
	public Heckmeck game;
	static Array<Player> hraci;
	Array<MyButton> playersTopStones;
	static int onMove;
	public int pocetHracov=0;
	
	public Board(Heckmeck g){
		game=g;
		onMove=0;		
	}
	
	public void preparePlayers(){
		hraci= new Array<Player>();
		playersTopStones=new Array<MyButton>();
		for(int i=0;i<pocetHracov;i++){
		    hraci.add(new Player(game, i, i*145));
		    playersTopStones.add(hraci.get(i).getTop());
		}
	}
	
	public void nextOne(){
		onMove=(onMove+1)%hraci.size;
		game.kocky.setBlank(game.kocky.hodeneKocky);
		game.kocky.setBlank(game.kocky.hracoveKocky);
		for(int i=0;i<7;i++){
			game.kocky.pickedN.set(i, false);
		}
		Drawman.hod.setVisible(true);
		hraci.get(onMove).hraj();
	}
	
	public boolean verify(int x){
		if(x <= hraci.get(onMove).getSum() && game.kocky.pickedN.get(6)==true)
			return true;
		else
			return false;
	}
	
	public boolean verifyT(int x){
		if(hraci.get(onMove).getID() != onMove && x == hraci.get(onMove).getSum() && game.kocky.pickedN.get(6)==true)
			return true;
		else
			return false;
	}
}
