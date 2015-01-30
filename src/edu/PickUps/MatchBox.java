package edu.PickUps;

import actors.Door;
import actors.Player;

public class MatchBox extends PickUpable {

	public MatchBox(Player player, Inventory inventory) {
		super(player, inventory);
		image = "images/MatchBox.png"; setImage(image);
	}


	@Override
	public void useItem() {
		Door.setNotifyToDel(true);
	}


	

}
