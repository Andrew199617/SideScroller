
package actors;

public class Stairs extends Interacteble {

	


	public Stairs(Player player){
		image = "images/Stairs.png";
		setImage(image);
		this.player = player;
	}

	@Override
	public void act() {
		
		
		useButOne = "w";
		useButTwo = "s";
		checkButOne();
		checkButTwo();
		doActionOne();
		doActionTwo();

		delete();

	}

	@Override
	public void one_PlayerToDo() {
		this.player.climbingUp();
		
	}

	@Override
	public void one_PlayerNot() {
		this.player.notClimbingUp();
		
	}

	@Override
	public void Two_PlayerToDo() {
		this.player.climbingDown();
		
	}

	@Override
	public void Two_PlayerNot() {
		this.player.notClimbingDown();
		
	}



}
