package actors;

import java.util.List;

import world.BeginningMap;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public abstract class Interacteble extends Actor {

	Player player;
	protected boolean inTheActOne = false;
	protected boolean inTheActTwo = false;
	protected boolean butPressedOne = false;
	protected boolean butPressedTwo = false;
	protected boolean notifPlayerOne = false;
	protected boolean notifPlayerTwo = false;
	protected boolean oneNotif = true;
	protected int xLoc;
	protected int yLoc;
	protected String image = "";
	protected String useButOne;
	protected String useButTwo;

	
	
	public abstract void act();

	
	public boolean checkButOne(){
		String key = Greenfoot.getKey();
		butPressedOne = (key != null && key.equals(useButOne));
		return butPressedOne;
		//		if(butPressedOne) inTheActOne = true;
		//		else inTheActOne = false;
	}

	public boolean checkButTwo(){
		String key = Greenfoot.getKey();
		butPressedTwo = (key != null && key.equals(useButTwo));
		return butPressedTwo;
		//		if(butPressedTwo) inTheActTwo = true;
		//		else inTheActTwo = false;
	}

	public void delete(){
		if(Door.isNotifyToDel()){
			this.getWorld().removeObject(this);
		}
	}

	public void doActionOne(){
		List<Player> player = this.getIntersectingObjects(Player.class);

		doActionSubset(player, inTheActTwo, useButOne, inTheActOne, notifPlayerOne, checkButOne(),true);

	}

	public void doActionTwo(){
		List<Player> player = this.getIntersectingObjects(Player.class);

		doActionSubset(player,inTheActOne, useButTwo, inTheActTwo,notifPlayerTwo,checkButTwo(),false);

	}

	private void doActionSubset(List<Player> player, boolean inTheActOther, String useButSelf, boolean inTheActSelf
			,boolean notifyPlayerSelf, boolean butPressedSelf, boolean isOne) {

		if(!inTheActOther){
			if(Greenfoot.isKeyDown(useButSelf) ){
				if(!player.isEmpty()){
					whatMethodToDo(isOne);
					inTheActSelf = true;
				}
				else if (player.isEmpty()){
					whatMethodNot(isOne);
					inTheActSelf = false;
				}
			}
		}
		
	}

	private void whatMethodToDo(boolean isOne){
		if(isOne){
			one_PlayerToDo();
		}
		else{
			Two_PlayerToDo();
		}
	}

	private void whatMethodNot(boolean isOne){
		if(isOne){
			one_PlayerNot();
		}
		else{
			Two_PlayerNot();
		}
	}

	public abstract void one_PlayerToDo();

	public abstract void Two_PlayerToDo();

	public abstract void one_PlayerNot();

	public abstract void Two_PlayerNot();
}
