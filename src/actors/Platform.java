package actors;

import java.util.List;

import greenfoot.GreenfootImage;
import world.BeginningMap;

public class Platform extends Interacteble {

	public static final int PLATFORMHEIGHT = 22;
	public static final int PLATFORMWIDTH = 300;
	Player player;
	
	public Platform(Player player){
		this.player = player;
		image = "images/Platform.png";
		GreenfootImage pillars = new GreenfootImage("images/Platform_bot.png");
		setImage(image);
	}
	
	@Override
	public void act() {
		List<Player> player = this.getNeighbours(PLATFORMHEIGHT + 10, true, Player.class);
		
		if(Player.climbingUp){
			Player.climbingDown = false;
		}
		else if (Player.climbingDown ){
			BeginningMap.playerWalkingPlane = (BeginningMap.WORLDHEIGHT -142)-(BeginningMap.PLAYERHEIGHT/2);
			this.player.platformChanged = false;
		}
		else if(Player.Yloc + BeginningMap.PLAYERHEIGHT/2 + 1 < getY() && Player.Xloc > getX() - (PLATFORMWIDTH/2) && Player.Xloc < getX() + (PLATFORMWIDTH/2) ){
			BeginningMap.playerWalkingPlane = getY() - PLATFORMHEIGHT/2 - (BeginningMap.PLAYERHEIGHT/2);
			this.player.platformChanged = true;
		}
		else{
			BeginningMap.playerWalkingPlane = (BeginningMap.WORLDHEIGHT -142)-(BeginningMap.PLAYERHEIGHT/2);
			this.player.platformChanged = false;
		}
		
	}

	@Override
	public void one_PlayerToDo() {
		
	}

	@Override
	public void Two_PlayerToDo() {
		
	}

	@Override
	public void one_PlayerNot() {
		
	}

	@Override
	public void Two_PlayerNot() {
		
	}

}
