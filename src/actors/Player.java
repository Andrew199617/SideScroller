package actors;

import world.BeginningMap;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Player extends Actor {

	private static final int REGULARSPEED = 4;
	private static int Xloc= 0;
	private static int Yloc= 0;
	private boolean climbingUp = false;
	private boolean climbingDown = false;

	private boolean jumping = false;
	private int jumpTime;
	private boolean setRot;
	private boolean notDoingSome = true;
	private static final int JUMPENDTIME = 20;
	private String imageStr = "images/Player_Idle.png";
	GreenfootImage image = new GreenfootImage(imageStr);
	private boolean mirrorImage;
	private boolean imageIsMirror = false;
	private boolean changeImage = false;
	
	
	public Player(){
		setImage(image);
	}

	public void act(){
		if(changeImage){
			image = new GreenfootImage(imageStr);
			changeImage = false;
		}
		setImage(image);
		
		Xloc = this.getX();
		Yloc = this.getY();

		jump();
		jumping();
		//		if(NoteWASD.notOnMap){
		if(!climbingUp && !climbingDown && Yloc == BeginningMap.playerWalkingPlane){
			moveAD("d",false,REGULARSPEED);
			moveAD("a", true,-REGULARSPEED);
//			mirrorImage();
		}
		if(climbingUp){
			moveAD("w", false, REGULARSPEED/2);
		}
		if(climbingDown){
			moveAD("s", false, -REGULARSPEED/2);
			if(Yloc >= BeginningMap.playerWalkingPlane){
				setLocation(Xloc, BeginningMap.playerWalkingPlane);
			}
		}
		//		}

		dontGoUnder();
		
		if(!climbingUp && !climbingDown)
		Gravity(BeginningMap.playerWalkingPlane);
		
		if(Door.isNotifyToDel()){
			this.getWorld().removeObject(this);
		}


	}

	private void mirrorImage(){
		if(mirrorImage){
			if(!imageIsMirror){
			image.mirrorHorizontally();
			imageIsMirror = true;
			}
		}
		else{
			if(imageIsMirror){
				image.mirrorHorizontally();
				imageIsMirror = false;
			}
				
		}
	}
	
	private void moveAD(String dir, boolean mI,int speed) {
		if(Greenfoot.isKeyDown(dir)){
			notDoingSome = false;
			mirrorImage = mI;
			mirrorImage();
			move(speed);
		}
	}

	private void jump(){
		if (jumpTime < JUMPENDTIME){
		jumping = (Greenfoot.isKeyDown("space"));
		}
		
	}	

	private void jumping(){
		if(jumping){
			notDoingSome = false;
			move(REGULARSPEED/2 *3);
			jumpTime++;
			if(jumpTime <= JUMPENDTIME){
				if(jumpTime == 1) {setRotation(270); }
			}
			else{
				jumping = false;
			}
		}
		if(!jumping){
			jumping = false;
			notDoingSome = true;
		}
		
	} 

	private void Gravity(int y){
		if(Yloc < y && notDoingSome){
			setRotation(90);
			move(REGULARSPEED/4*3);
		}
		else if(Yloc == y && notDoingSome){
			setRotation(0);
			jumpTime = 0;
		}
	}
	
	private void dontGoUnder() {
		if(Yloc > BeginningMap.playerWalkingPlane){
			setLocation(Xloc, BeginningMap.playerWalkingPlane);
			notDoingSome = true;
		}
	}


	public void climbingUp(){
		if (!climbingUp){
			setRotation(270);
			imageStr = "images/Player_Climb.png";
			changeImage = true;
		}
		climbingUp = true;
	}

	public void climbingDown(){
		if (!climbingDown){
			setRotation(270);
			imageStr = "images/Player_Climb.png";
			changeImage = true;
		}
		climbingDown = true;
	}

	public void notClimbingUp(){
		setRotation(0);
		imageStr = "images/Player_Idle.png";
		changeImage = true;
		climbingUp = false;
	}

	public void notClimbingDown(){
		setRotation(0);
		imageStr = "images/Player_Idle.png";
		mirrorImage();
		changeImage = true;
		climbingDown = false;
	}


}
