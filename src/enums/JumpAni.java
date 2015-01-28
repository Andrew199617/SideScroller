package enums;

public enum JumpAni {
	EIGHTY("jump/Player_Jumping80.png"), 
	SEVEN("jump/Player_Jumping70.png"), 
	SIX("jump/Player_Jumping60.png"), 
	FIVE("jump/Player_Jumping50.png"),
	FOUR("jump/Player_Jumping40.png"), 
	THREE("jump/Player_Jumping30.png"), 
	TWO("jump/Player_Jumping20.png"), 
	ONE("jump/Player_Jumping10.png"),
	ZERO("jump/Player_Jumping0.png");
	
	private String s;
	
	private JumpAni(String s){
		this.s = s;
	}

	public String getS() {
		return s;
	}
}
