package actors;


import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class NoteWASD extends Actor {

	public static boolean notOnMap = false;
	
	public NoteWASD(){
		setImage("images/Sticky-Note_WASD.png");
	}

	public void act(){

		if(Greenfoot.isKeyDown("enter")){
			this.getWorld().removeObject(this);
			notOnMap = true;
		}
		
		if(Door.isNotifyToDel()){
			this.getWorld().removeObject(this);
		}
	}

}


