package enums;

import edu.PickUps.Inventory;
import world.BeginningMap;

public enum InvSlot {

	
	
	FIRSTSLOT(Inventory.getInventorySpace() + ((BeginningMap.WORLDWIDTH/2)-(Inventory.getInventorywidth()/2)-50)),
	LASTSLOT(Inventory.getInventorySpace()*5 + (BeginningMap.WORLDWIDTH/2-Inventory.getInventorywidth()/2));
	
	
	public int getLocation() {
		return Location;
	}

	private int Location;
	
	private InvSlot(int location){
		this.Location = location;
	}


}
