package edu.PickUps;

import actors.Door;
import actors.Player;

public class Key extends PickUpable {

	public Key(Player player, Inventory inventory) {
		super(player,inventory);
		image = "images/Key.png"; setImage(image);
	}


	@Override
	public void useItem() {
		Door.setNotifyToDel(true);
	}


	

}
