package actors;

import java.util.List;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Door extends Actor {

	private boolean start = false;
	private static boolean notifyToDel = false;
	private int delay = 25;


	public Door(String s){
		setImage(s);
	}

	public void act(){
		List<Player> player = this.getObjectsInRange(40, Player.class);

		if(!player.isEmpty()){
			String key = Greenfoot.getKey();
			if (key != null && key.equals("w")){
				this.setImage("images/Door_Open_Exit.png");
				start = true;
			}
		}

		if(start){
			if(delay > 0){
				delay--;
			}
			else if(delay == 0){
				setNotifyToDel(true);
				this.getWorld().removeObject(this);
			}
		}


	}

	public static boolean isNotifyToDel() {
		return notifyToDel;
	}

	public static void setNotifyToDel(boolean notifyToDel) {
		Door.notifyToDel = notifyToDel;
	}

}
