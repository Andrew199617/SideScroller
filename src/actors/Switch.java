package actors;

public class Switch extends Interacteble {


	public Switch() {
		image = "images/Switch.png";
		setImage(image);
	}

	@Override
	public void act() {

		useButOne = "e";
		checkButOne();
		doActionOne();
		if(!butPressedOne){
			one_PlayerNot();
			inTheActOne = false;
			notifPlayerOne = false;
		}

		delete();
	}

	@Override
	public void one_PlayerToDo() {
		image = "images/Switch_used.png";
		setImage(image);
		Door.openable();
	}

	@Override
	public void Two_PlayerToDo() {

	}

	@Override
	public void one_PlayerNot() {
		if(oneNotif){
			Door.notOpenable();
			oneNotif = false;
		}
	}

	@Override
	public void Two_PlayerNot() {

	}

}
