package edu.PickUps;

import actors.Door;
import actors.Player;
import world.BeginningMap;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Inventory extends Actor {

	public static int getInventorywidth() {
		return INVENTORYWIDTH;
	}

	public static int getInventoryyloc() {
		return INVENTORYYLOC;
	}

	public static int getInventorySpace() {
		return INVENTORYSPACE;
	}

	private static final int INVENTORYWIDTH = 520;
	private static final int INVENTORYHEIGHT = 80;
	private static final int INVENTORYSPACE = INVENTORYWIDTH/5;
	private static final int INVENTORYYLOC = INVENTORYHEIGHT/2 + 10;
	private boolean changeImage = false;
	private String imageStr = "images/Inventory.png";
	GreenfootImage image = new GreenfootImage(imageStr);
	PickUpable[] Slots = new PickUpable[5];
	Player player;

	public Inventory(BeginningMap bMap, Player player){
		setImage(image);
		this.player = player;
		bMap.addObject(this, BeginningMap.WORLDWIDTH/2, INVENTORYYLOC);
	}

	public void act(){
		useItem();
		hide();
	}

	public void addItem(PickUpable pickup){

		for(int i = 0; i < Slots.length; i++){
			if(Slots[i] == null ){
				Slots[i] = pickup;
				setUseBut(i, Slots[i]);
				System.out.println(Slots[i].toString());
				break;
			}
		}
	}

	private void setUseBut(int check, PickUpable slo) throws Error {
		switch (check) {
		case 0:
			changeSlo(slo, "1", (byte) 1);
			break;
		case 1:
			changeSlo(slo, "2", (byte) 2);
			break;
		case 2:
			changeSlo(slo, "3", (byte) 3);
			break;
		case 3:
			changeSlo(slo, "4", (byte) 4);
			break;
		case 4:
			changeSlo(slo, "5", (byte) 5);
			break;
		default: throw new Error();
		}
	}

	private void changeSlo(PickUpable slo,String useBut, byte slotNum) {
		slo.useBut = useBut;
		slo.slotnum = slotNum;
	}

	public void removeItem(PickUpable pickUp){
		for(int i = 0; i < Slots.length; i++){
			if(Slots[i] == pickUp ){
				Slots[i].setLocation(Player.Xloc, Player.Yloc);
				Slots[i].pickedUp = false;
				Slots[i] = null;
				break;
			}
		}
		
	}

	public void useItem(){
		for(int i = 0; i < Slots.length; i++){
			if(Slots[i] != null ){
				if(Greenfoot.isKeyDown(Slots[i].useBut)){
					Slots[i].useItem();
					break;
				}
			}
		}
	}

	public void hide(){
		if(Door.isNotifyToDel()){
			image.setTransparency(0);
			setImage(image);
			changeImage = true;
		}
		else if(changeImage){
			image.setTransparency(255);
			setImage(image);
			changeImage = false;
		}
	}

}
