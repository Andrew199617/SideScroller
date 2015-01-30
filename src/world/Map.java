package world;

import actors.Door;
import greenfoot.Actor;

public class Map extends Actor {

	public Map(String ImageStr,BeginningMap bMap){
		setImage(ImageStr);
		bMap.addObject(this, BeginningMap.WORLDWIDTH/2, BeginningMap.WORLDHEIGHT/2);
	}
	
	public void act(){
		if(Door.isNotifyToDel()){
			this.getWorld().removeObject(this);
		}
	}
	
}
