package actors;

import java.util.List;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Note extends Actor {

	public static boolean isPressed = false;

	public Note(String s){
		setImage(s);
	}

	public void act(){
		List<Player> player = this.getObjectsInRange(75, Player.class);


		if(!player.isEmpty()){
			String key = Greenfoot.getKey();
			if (key != null && key.equals("e")){

				isPressed = true;
			}
		}
		
		if(Door.isNotifyToDel()){
			this.getWorld().removeObject(this);
		}

	}

}
