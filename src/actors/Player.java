package actors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.text.DefaultEditorKit.BeepAction;

import enums.JumpAni;
import world.BeginningMap;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Player extends Actor {

	private static final int CLIMBEND = 14;
	private static final int REGULARSPEED = 4;
	public static int Xloc= 0;
	public static int Yloc= 0;
	public static boolean climbingUp = false;
	public static boolean climbingDown = false;

	private boolean jumping = false;
	private int jumpTime;
	private boolean notDoingSome = true;
	private static final int JUMPENDTIME = 40;
	private String imageStr = "images/Player_Idle.png";
	GreenfootImage image = new GreenfootImage(imageStr);
	private boolean mirrorImage;
	private boolean imageIsMirror = false;
	private boolean changeImage = false;
	private int move = 3;
	private static final int STARTINGPLAT = BeginningMap.playerWalkingPlane;
	private int ClimbTimer;
	private int JumpAniTimer = 8;
	public boolean platformChanged;
	private boolean stillLadder = false;

	public Player(){
		setImage(image);
	}

	public void act(){


		Xloc = this.getX();
		Yloc = this.getY();

		jump();
		jumping();

		if(!jumping && !climbingUp && !climbingDown){
			notDoingSome = true;
		}


		if((!climbingUp && !climbingDown) || Yloc > BeginningMap.playerWalkingPlane - 15)
			Gravity(BeginningMap.playerWalkingPlane);
		//		if(NoteWASD.notOnMap){
		if(!climbingUp && !climbingDown && Yloc == BeginningMap.playerWalkingPlane){
			moveAD("d",false,REGULARSPEED);
			moveAD("a", true,-REGULARSPEED);
			mirrorImage();
		}
		if(climbingUp){
			stillLadder = moveAD("w", false, REGULARSPEED/2);
			if(!stillLadder)
			climbingAnimation();
		}
		if(climbingDown){
			stillLadder = moveAD("s", false, -REGULARSPEED/2);
			if(!stillLadder)
			climbingAnimation();
		}
		//		}





		dontGoUnder();

		changeImage();

		delete();


	}

	private void climbingAnimation() {
		if(imageStr == "images/Player_Climb.png"){
			ClimbTimer++;
			if(ClimbTimer == CLIMBEND){
				imageStr = "images/Player_Climb_2.png";
				changeImage = true;
				ClimbTimer = 0;
			}
		}
		else if(imageStr == "images/Player_Climb_2.png"){
			ClimbTimer++;
			if(ClimbTimer == CLIMBEND){
				imageStr = "images/Player_Climb.png";
				changeImage = true;
				ClimbTimer = 0;
			}
		}
	}

	private void changeImage() {

		try{
			if(changeImage){
				image = new GreenfootImage(imageStr);
				changeImage = false;
				setImage(image);
			}
		}
		catch(IllegalArgumentException e){
			setImage("image/Player_Idle.png");
			System.out.println("IMAGE IS MESSED UP");
		}

	}


	private void delete() {
		try{
			if(Door.isNotifyToDel()){
				throw new Exception("Changing Map");
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			if(Door.isNotifyToDel()){
				this.getWorld().removeObject(this);
			}
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

	private boolean moveAD(String dir, boolean mI,int speed) {
		if(Greenfoot.isKeyDown(dir)){
			notDoingSome = false;
			mirrorImage = mI;
			move(speed);
		}
		return !Greenfoot.isKeyDown(dir);
		
	}

	private void moveADJump(String dir, int turn,int speed) {
		if(Greenfoot.isKeyDown(dir)){
			if(getRotation() == 340  || getRotation() == 20){
				turn = 0; JumpAniTimer = 8;imageStr = JumpAni.TWO.getS();
				changeImage = true;
			}
			turn(turn);

			move(speed);


			jumpAni(turn);
		}
	}

	private void jumpAni(int turn) {
		if(turn != 0){
			switch (JumpAniTimer){
			case 8:
				imageStr = JumpAni.EIGHTY.getS();
				break;
			case 7:
				imageStr = JumpAni.SEVEN.getS();
				break;
			case 6:
				imageStr = JumpAni.SIX.getS();
				break;
			case 5:
				imageStr = JumpAni.FIVE.getS();
				break;
			case 4:
				imageStr = JumpAni.FOUR.getS();
				break;
			case 3:
				imageStr = JumpAni.THREE.getS();
				break;
			}
			JumpAniTimer --;
			changeImage = true;
		}
	}

	private void jump(){
		if (jumpTime < JUMPENDTIME && !climbingUp && !climbingDown){
			jumping = (Greenfoot.isKeyDown("space"));
		}

	}	

	private void jumping(){
		if(jumping){
			notDoingSome = false;
			move(move);
			jumpTime++;
			if(jumpTime <= JUMPENDTIME){
				if(jumpTime == 1 && !imageIsMirror) {setRotation(270);imageStr = "images/Player_Jumping.png";
				changeImage = true; move = 3;}
				else if (jumpTime == 1 && imageIsMirror){
					setRotation(90); 
					imageStr = "images/Player_Jumping_Left.png";
					changeImage = true;
					move = -3;
				}
				if(!imageIsMirror){
					moveADJump("d", 10, REGULARSPEED);
				}
				else if (imageIsMirror){
					moveADJump("a", -10, -REGULARSPEED);	
				}

			}
			else{
				jumping = false;
				notDoingSome = true;
				JumpAniTimer = 8;
			}
		}

	} 

	private void Gravity(int y){
		if(Yloc < y && notDoingSome){
			setRotation(90);
			imageStr = "images/Player_90.png";
			changeImage = true;
			move(REGULARSPEED);
		}
		else if(Yloc == y && notDoingSome){
			setRotation(0);
			if(imageStr == "images/Player_90.png"){
				imageStr = "images/Player_Idle.png";
				changeImage = true;
			}
			jumpTime = 0;
			JumpAniTimer = 8;
		}

	}

	public void catchBroken() {
		try{
			if(Yloc < BeginningMap.playerWalkingPlane + 10){
				throw new Exception("You went to low");
			}
		}
		catch(Exception e){
			setLocation(Xloc, BeginningMap.playerWalkingPlane);
			System.out.println(e.getMessage());
		}
	}

	private void dontGoUnder() {
		if(Yloc > STARTINGPLAT){
			setLocation(Xloc, STARTINGPLAT);
		}
		else if(Yloc > BeginningMap.playerWalkingPlane){
			setLocation(Xloc, BeginningMap.playerWalkingPlane);
		}
	}


	public void climbingUp(){
		if(platformChanged){
			notDoingSome = true;
			climbingUp = false;
			climbingDown = false;
			setRotation(0);
			imageStr = "images/Player_Idle.png";
			changeImage = true;
		}
		else if (!climbingUp){
			setRotation(270);
			imageStr = "images/Player_Climb.png";
			changeImage = true;
			notDoingSome = false;
			climbingUp = true;
			climbingDown = false;
		}


	}

	public void climbingDown(){
		if(!platformChanged && Yloc >= BeginningMap.playerWalkingPlane-2){
			setRotation(0);
			imageStr = "images/Player_Idle.png";
			changeImage = true;
			climbingDown = false;
			climbingUp = false;
			notDoingSome = true;
		}
		else if (!climbingDown){
			setRotation(270);
			imageStr = "images/Player_Climb.png";
			changeImage = true;
			notDoingSome = false;
			climbingDown = true;
			climbingUp = false;
		}


	}

	public void notClimbingUp(){
		
		if(climbingUp){
		resetImage();
		climbingUp = false;
		stillLadder = true;
		}

	}


	public void notClimbingDown(){
		
		
		if(climbingDown){
		resetImage();
		climbingDown = false;
		stillLadder = true;
		}
		
		
		
	}

	private void resetImage() {
		setRotation(0);
		if(Yloc > BeginningMap.playerWalkingPlane-2){
			imageStr = "images/Player_Idle.png";
		}
		else if(imageStr == "images/Player_Climb.png" )
			imageStr = "images/Player_Climb_Idle.png";
		else if(imageStr == "images/Player_Climb_2.png" )
			imageStr = "images/Player_Climb_Idle.png";
		changeImage = true;
	}


}
