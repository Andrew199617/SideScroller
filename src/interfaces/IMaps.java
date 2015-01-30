package interfaces;

import edu.PickUps.Inventory;
import actors.Player;

public interface IMaps {
	
	public void exit();
	
	public void enter(int x, int y, Player player);
	
}
