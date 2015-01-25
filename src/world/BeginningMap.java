package world;


import java.awt.Color;

import interfaces.IMaps;
import actors.Door;
import actors.Note;
import actors.NoteWASD;
import actors.Player;
import actors.Stairs;
import actors.StickyNote;
import greenfoot.World;

public class BeginningMap extends World implements IMaps{

	private int delay = 50;
	public static final int WORLDHEIGHT = 810;
	public static final int WORLDWIDTH = 1440;
	private static final int PLAYERHEIGHT = 127;
	private static final int DOORHEIGHTE = 165;
	private static final int DOORHEIGHT = 140;
	private static final int STAIRHEIGHT = 379;
	public static int WalkingPlane = (WORLDHEIGHT -142);
	public static int playerWalkingPlane = (WORLDHEIGHT -142)-(PLAYERHEIGHT/2);
	
	
	public BeginningMap() {
		super(WORLDWIDTH ,WORLDHEIGHT, 1);
		this.getBackground().setColor(Color.BLACK);
		this.getBackground().fill();
		
		Map_1 map1 = new Map_1();
		this.addObject(map1, WORLDWIDTH/2, WORLDHEIGHT/2);
		
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
		
		
		
	}
	
	public void act(){
		
		if(Door.isNotifyToDel()){
			if(delay > 0){
				delay--;
			}
			else{
				Map_1 map1 = new Map_1();
				this.addObject(map1, WORLDWIDTH/2, WORLDHEIGHT/2);
				
				Player player = new Player();
				
				Stairs stair = new Stairs(player);
				this.addObject(stair, 400, WalkingPlane - (STAIRHEIGHT/2));
				
				
				enter(100,WalkingPlane, player);
				Door.setNotifyToDel(false);
				
				
			}
		}
	}

	@Override
	public void exit() {
		Door door = new Door("images/Door_Exit.png");
		this.addObject(door, WORLDWIDTH - 150, WalkingPlane - (DOORHEIGHTE/2));
		door.start = true;
	}

	@Override
	public void enter(int x, int y,Player player) {//100,WalkingPlane - (PLAYERHEIGHT/2)
		
		Door door = new Door("images/Door_Open.png");
		this.addObject(door, x, (y) - (DOORHEIGHT/2));
		this.addObject(player, x,(y) -(PLAYERHEIGHT/2) );
		
	}

}
