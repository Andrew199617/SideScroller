package actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Player extends Actor {

	public static final int REGULARSPEED = 4;
	public static int Xloc= 0;
	public static int Yloc= 0;
	
	public Player(){
		setImage("images/Player.png");
	}

	public void act(){
		
		Xloc = this.getX();
		Yloc = this.getY();
		
		if(NoteWASD.notOnMap){
			if(Greenfoot.isKeyDown("d")){
				move(REGULARSPEED);
			}
			if(Greenfoot.isKeyDown("a")){
				move(-REGULARSPEED);
			}
		}
		
		if(Door.isNotifyToDel()){
			this.getWorld().removeObject(this);
		}
	
	}

}
