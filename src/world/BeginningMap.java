package world;


import java.awt.Color;

import edu.PickUps.Cannon_Ball;
import edu.PickUps.Inventory;
import edu.PickUps.Key;
import edu.PickUps.MatchBox;
import edu.PickUps.PickUpable;
import interfaces.IMaps;
import actors.Door;
import actors.Interacteble;
import actors.Note;
import actors.NoteWASD;
import actors.Platform;
import actors.Player;
import actors.Stairs;
import actors.StickyNote;
import actors.Switch;
import greenfoot.World;

public class BeginningMap extends World implements IMaps{

	private int delay = 50;
	public static final int WORLDHEIGHT = 810;
	public static final int WORLDWIDTH = 1440;
	public static final int PLAYERHEIGHT = 127;
	private static final int DOORHEIGHTE = 165;
	private static final int DOORHEIGHT = 140;
	
	private static final int STAIRHEIGHT = 300;
	public static int WalkingPlane = (WORLDHEIGHT -142);
	private static final int PLATFORMPLANE = WalkingPlane - STAIRHEIGHT + (Platform.PLATFORMHEIGHT/2) + 10;
	public static int playerWalkingPlane = (WORLDHEIGHT -142)-(PLAYERHEIGHT/2);
	Inventory inventory;
	
	public BeginningMap() {
		super(WORLDWIDTH ,WORLDHEIGHT, 1);
		
		this.getBackground().setColor(Color.BLACK);
		this.getBackground().fill();
		
		makeBMap();
		
	}
	
	public void act(){
		
		if(Door.isNotifyToDel()){
			makeMap1();
		}
	}

	private void makeBMap() {	
		exit();
		
		Note note = new Note("images/Sticky-Note_Small.png");
		this.addObject(note, WORLDWIDTH/2, WalkingPlane - (PLAYERHEIGHT/2));
		
		Note howto = new Note("images/Sticky-Note_Small_HowTo.png");
		this.addObject(howto, WORLDWIDTH/2, WalkingPlane - (PLAYERHEIGHT));
		
		Player player = new Player();
		enter(100,WalkingPlane, player);
		
		StickyNote sn = new StickyNote();
		this.addObject(sn, WORLDWIDTH/2, WORLDHEIGHT/2);
			
		NoteWASD wasd = new NoteWASD();
		this.addObject(wasd, WORLDWIDTH/2, WORLDHEIGHT/2);
		
		Door.setNotifyToDel(true);
	}
	
	private void makeMap1() {
		if(delay > 0){
			delay--;
		}
		else{
			Player player = new Player();
			
			exit();
			
			Interacteble stair = new Stairs(player);
			this.addObject(stair, 400, WalkingPlane - (STAIRHEIGHT/2));
			
			Switch sch = new Switch();
			this.addObject(sch, 520, PLATFORMPLANE - 80);
			
			enter(100,WalkingPlane, player);
			Door.setNotifyToDel(false);
			
			PickUpable key = new Key(player, inventory);
			this.addObject(key, 800, playerWalkingPlane);
			
			PickUpable key2 = new Key(player, inventory);
			this.addObject(key2, 600, playerWalkingPlane);
			
			PickUpable MatchBox = new MatchBox(player, inventory);
			this.addObject(MatchBox, 400, playerWalkingPlane);
			
			PickUpable CannonBall = new Cannon_Ball(player, inventory);
			this.addObject(CannonBall, 200, playerWalkingPlane);
			
			Interacteble plat = new Platform(player);
			this.addObject(plat, 400, PLATFORMPLANE);
			
			
		}
	}

	@Override
	public void exit() {
		new Map("images/Map_1.png",this);
		Door door = new Door("images/Door_Exit.png");
		this.addObject(door, WORLDWIDTH - 100, WalkingPlane - (DOORHEIGHTE/2));
	}

	@Override
	public void enter(int x, int y,Player player) {
		Door door = new Door("images/Door_Open.png");
		this.addObject(door, x, (y) - (DOORHEIGHT/2));
		this.addObject(player, x,(y) -(PLAYERHEIGHT/2) );
		inventory = new Inventory(this, player);
		
	}

}
