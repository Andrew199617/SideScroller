package actors;

import java.util.List;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public abstract class Interacteble extends Actor {

	Player player;
	protected boolean inTheActOne = false;
	protected boolean inTheActTwo = false;
	protected boolean butPressedOne = false;
	protected boolean butPressedTwo = false;
	protected boolean notifPlayer = false;
	protected int xLoc;
	protected int yLoc;
	protected String image = "";
	protected String useButOne;
	protected String useButTwo;

	public abstract void act();

	public void checkButOne(){
		String key = Greenfoot.getKey();
		butPressedOne = (key != null && key.equals(useButOne));
//		if(butPressedOne) inTheActOne = true;
//		else inTheActOne = false;
	}

	public void checkButTwo(){
		String key = Greenfoot.getKey();
		butPressedTwo = (key != null && key.equals(useButTwo));
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

		if(!inTheActTwo){
			if(Greenfoot.isKeyDown(useButOne)){
				if(!player.isEmpty()){
					if(!notifPlayer){
						one_PlayerToDo();
						inTheActOne = true;
						notifPlayer = true;
					}
				}
				else{
					one_PlayerNot();
					inTheActOne = false;
					notifPlayer = false;
				}
			}
			else if(!butPressedOne && notifPlayer){
				one_PlayerNot();
				inTheActOne = false;
				notifPlayer = false;
			}
		}

	}

	public void doActionTwo(){
		List<Player> player = this.getIntersectingObjects(Player.class);

		if(!inTheActOne){
			if(Greenfoot.isKeyDown(useButTwo)){
				if(!player.isEmpty()){
					if(!notifPlayer){
						Two_PlayerToDo();
						inTheActTwo = true;
						notifPlayer = true;
					}
				}
				else{
					Two_PlayerNot();
					inTheActTwo = false;
					notifPlayer = false;
				}
			}
			else if(!butPressedTwo && notifPlayer){
				Two_PlayerNot();
				inTheActTwo = false;
				notifPlayer = false;
			}
		}

	}

	public abstract void one_PlayerToDo();

	public abstract void Two_PlayerToDo();

	public abstract void one_PlayerNot();

	public abstract void Two_PlayerNot();
}
