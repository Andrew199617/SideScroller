package actors;

import java.util.List;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Door extends Actor {

	public boolean start = false;
	private static boolean notifyToDel = false;
	private int delay = 50;
	String image;
	private static boolean openable = true;

	public Door(String s){
		changeImage(s);
		this.image = s;
	}

	public void act(){
		@SuppressWarnings("unchecked")
		List<Player> player = this.getObjectsInRange(40, Player.class);

		imageOpenable();
		
		if(openable && image != "images/Door_Open_Exit.png" && image != "images/Door_Open.png") {
			if(!player.isEmpty()){
				String key = Greenfoot.getKey();
				if (key != null && key.equals("w")){
					changeImage("images/Door_Open_Exit.png");
					start = true;
				}
			}
		}

		if(start){
			if(delay > 0){
				delay--;
			}
			else if(delay == 0){
				setNotifyToDel(true);
				this.getWorld().removeObject(this);
				openable = false;
			}
		}

	}

	public static boolean isNotifyToDel() {
		return notifyToDel;
	}

	public static void setNotifyToDel(boolean notifyToDel) {
		Door.notifyToDel = notifyToDel;
	}
	
	public static void openable(){
		openable = true;
	}

	public static void notOpenable(){
		openable = false;
	}
	
	public void imageOpenable(){
	
		if(openable && image == "images/Door_Exit.png"){
			changeImage("images/Door_Exit_Green.png");
		}
		else if (image == "images/Door_Exit.png"){
			changeImage("images/Door_Exit.png");
		}
		
	}
	
	public void changeImage(String image){
		this.image = image;
		this.setImage(image);
	}
	
	
}
