package actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class StickyNote extends Actor {
	
	

	public StickyNote(){
		GreenfootImage image = new GreenfootImage(10, 10);
		image.setTransparency(0);
		setImage(image);
	}
	
	public void act(){
	
		
		if(Note.isPressed){
			setImage("images/Sticky-Note.png");
			Note.isPressed = false;
		}

		if(!Note.isPressed){
			if(Greenfoot.isKeyDown("enter")){
				GreenfootImage image = new GreenfootImage(10, 10);
				image.setTransparency(0);
				setImage(image);
				
			}
		}
		
		if(Door.isNotifyToDel()){
			this.getWorld().removeObject(this);
		}
		
	}
	
}
