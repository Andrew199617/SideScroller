package edu.PickUps;

import actors.Door;
import actors.Player;

public class Cannon_Ball extends PickUpable {

	public Cannon_Ball(Player player, Inventory inventory) {
		super(player, inventory);
		image = "images/Cannon_Ball.png"; setImage(image);
	}


	@Override
	public void useItem() {
		Door.setNotifyToDel(true);
	}


	

}
