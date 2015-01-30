package edu.PickUps;

import java.util.List;

import enums.InvSlot;
import actors.Door;
import actors.Player;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public abstract class PickUpable extends Actor {
	
	protected boolean pickedUp = false;
	protected Player player;
	protected boolean butPressed = false;
	protected boolean notifPlayer = false;
	protected int xLoc;
	protected int yLoc;
	protected String image = "";
	protected String pickUpBut = "e";
	protected String useBut;
	protected Inventory inventory;
	protected byte slotnum = 0;
	protected boolean droped = false;
	
	public PickUpable(Player player, Inventory inventory){
		this.player = player;
		this.inventory = inventory;
	}
	
	public void act(){
		
		pickUpItem();
		removeItem();
		delete();
	}
	
	
	
	public void pickUpItem(){
		@SuppressWarnings("unchecked")
		List<Player> player = this.getIntersectingObjects(Player.class);
		
		if(Greenfoot.isKeyDown(pickUpBut) && !pickedUp ){
			if(!player.isEmpty()){
				inventory.addItem(this);
				moveToInven();
				pickedUp = true;
			}
		}
		
	}
	
	public void moveToInven(){
		int slot = InvSlot.FIRSTSLOT.getLocation();
		for(int i = 1; i < 6; i++ ){
			if(i == slotnum){
				setLocation(slot,Inventory.getInventoryyloc());
			}
			slot += Inventory.getInventorySpace();
		}
	}
		
	public void removeItem(){
		if(Greenfoot.isKeyDown("r")){
			inventory.removeItem(this);
		}
	}
	
	public abstract void useItem();
	
	public void delete(){
		if(Door.isNotifyToDel()){
			this.getWorld().removeObject(this);
		}
	}
	
	
	
}
