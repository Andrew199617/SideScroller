package world;

import actors.Door;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Map_1 extends Actor {

	public Map_1(){
		setImage("images/Map_1.png");
	}
	
	public void act(){
		
		if(Door.isNotifyToDel()){
			this.getWorld().removeObject(this);
		}
	}
	
}
