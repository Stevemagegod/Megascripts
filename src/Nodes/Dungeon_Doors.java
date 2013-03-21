package megascripts.dungoneering.node;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import megascripts.SetupTest;
import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.api.Flood;
import megascripts.api.Items;
import megascripts.dungoneering.Variable;
import megascripts.dungoneering.boss.CurrentBoss;
import megascripts.dungoneering.puzzle.CurrentPuzzle;
import megascripts.graphic.LogEntry;
import megascripts.graphic.LogHandler;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.powerbot.game.bot.Context;
import org.powerbot.game.client.Client;

public class Dungeon_Doors extends Node{

	
	
	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_DUNGEON && Actions.InDungeon() && !Actions.ThereFish() && !Actions.ThereBoss() &&!Actions.ThereKey() && !Actions.ThreNPC() && !CurrentPuzzle.TherePuzzle();
	}

	@Override
	public void execute() {
		try {
			Variable.ROOM_COLOR = Color.green;
			Variable.CurrentRoom = Flood.getArea();
			if (ThereSkillDoor()) {
				unlockDoor_Skill(Actions.Set_Object(Skill_Door()));
			} else if (ThereGuardainDoor()) {
				unlockDoor(Actions.Set_Object(Gurdain_Door()), true);
			} else if (ThereBasicDoor()) {
				unlockDoor(Actions.Set_Object(Basic_Door()), true);
			} else if (ThereKeyDoor()) {
				UnlockKeyDoor();
			} else if (ThereBackDoor()) {
				unlockDoor(Actions.Set_Object(Door_Back()), false);
			} else if (Actions.AtStartRoom()) {
				Variable.EnterdDoor.clear();
				Variable.BlackList.clear();
			}else if(!Actions.ThereBoss()){
				LogHandler.Print("We got Lost On This Dungeon , Abandon.." , Color.red);
				Variable.LeaveDungeon = true;
			}
		Variable.CurrentRoom = Flood.getArea();
		}catch (NullPointerException e){
			e.printStackTrace();
		}
	}


	public static void Leave_Dungeon() {
		if(Actions.InDungeon()){
		WidgetChild Leave = Widgets.get(1186, 8);
		WidgetChild OPTION_ONE = Widgets.get(1188, 3);
		int PARTY_INTERFACE = 939;
		int LEAVE_BUTTON = 13;
		if (OPTION_ONE.validate()) {
			OPTION_ONE.click(true);
			Task.sleep(300, 700);
		
		} else if (Leave.validate()) {
			Leave.click(true);
			Task.sleep(400, 700);
		} else {
			if (Widgets.get(PARTY_INTERFACE, LEAVE_BUTTON).visible()) {
				Widgets.get(PARTY_INTERFACE, LEAVE_BUTTON).click(true);
				Task.sleep(400, 800);
			} else {
				if (Inventory.contains(Variable.DUNGEON_RING)) {
					Items.Interact(Variable.DUNGEON_RING,"Open party interface");
					Task.sleep(300, 700);
				} else {
					Tabs.EQUIPMENT.open();
					WidgetChild RING_SLOT = Widgets.get(387, 33);
					RING_SLOT.interact("Open party interface");
					Task.sleep(2500, 2700);
				}
			}
		}
		}
	}
	

	private boolean ThereSkillDoor() {
		return Actions.Set_Object(Skill_Door()) !=null && Actions.Object_IsValid(Actions.Set_Object(Skill_Door()).getId());
	}
	public static void unlockDoor_Skill(SceneObject n){
		boolean remove = false;
		int x = 0;
		int y = 0;
		if(n !=null && n.validate()){
			if(Calculations.distanceTo(n) < 2){
				for (int d = 0; d < 15; d++) {
					if (Calculations.distanceTo(n) < 2) {
				     	 x = n.getLocation().getX();
				     	 y = n.getLocation().getY();
						Point Central = n.getModel().getCentralPoint();
						Mouse.move(Central.x, Central.y);
						if (Menu.contains("Enter") || Menu.contains("Unlock")
								|| Menu.contains("Open") || Menu.contains("Imbue-energy")
								|| Menu.contains("Dismiss") || Menu.contains("Repair-key")
								|| Menu.contains("Fix-pulley") || Menu.contains("Chop-down")
								|| Menu.contains("Mine") 	|| Menu.contains("Burn") ) {
							Mouse.click(Central.x, Central.y, true);
							Task.sleep(1000, 1500);
							for(int dz = 0 ; dz < 40 && (Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1); dz++, Task.sleep(100, 150));
							
						}
					}
					if(SetupTest.Message.contains("level of") || SetupTest.Message.contains("unable to")){
						LogHandler.Print("You Don't have Required Level for This Door , Removing...",Color.red);
						Variable.BlackDoor.add(n.getLocation());
						remove= true;
					}
				}
				if(!remove){
				    Variable.SkillEnterd.add(new Tile(x+1,y, 0));
			     	Variable.SkillEnterd.add(new Tile(x-1,y, 0));
			     	Variable.SkillEnterd.add(new Tile(x,y+1, 0));
			     	Variable.SkillEnterd.add(new Tile(x,y-1, 0));
			     	Variable.EnterdDoor.add(new Tile(x+1,y, 0));
			     	Variable.EnterdDoor.add(new Tile(x-1,y, 0));
			     	Variable.EnterdDoor.add(new Tile(x,y+1, 0));
					Variable.EnterdDoor.add(new Tile(x,y-1, 0));
				Add_BackDoor(n);
				}
			}else{
				Walking.walk(n);
				Task.sleep(500,600);
				for(int d = 0 ; d < 40 && (Players.getLocal().isMoving() || Players.getLocal().getAnimation() == -1); d++, Task.sleep(100, 150));
			}
		}
	}
	private static void Add_Area(SceneObject n, ArrayList<Tile> se) {
     	int x = n.getLocation().getX();
     	int y = n.getLocation().getY();
     	se.add(new Tile(x+1,y, 0));
     	se.add(new Tile(x-1,y, 0));
     	se.add(new Tile(x,y+1, 0));
     	se.add(new Tile(x,y-1, 0));
	}

	public static Filter<SceneObject> Skill_Door(){
        return new Filter<SceneObject>() {
				public boolean accept(SceneObject SDoor) {
		        	if (SDoor != null
		        			&& (MatchId(SDoor,Variable.ALL_SKILL_DOOR) || Variable.SkillEnterd.contains(SDoor.getLocation()))
		        			&& !Variable.EnterdDoor.contains(SDoor.getLocation())
		        			&& !Variable.BlackDoor.contains(SDoor.getLocation())
							&& Calc.Reach(SDoor)) {
						return true;
					} else {
						return false;
					}
				}

			};
       }
	
	public static boolean ThereBackDoor(){
		return Actions.Set_Object(Door_Back()) !=null && Actions.Object_IsValid(Actions.Set_Object(Door_Back()).getId());
	}
	public static Filter<SceneObject> Door_Back(){
        return new Filter<SceneObject>() {
				public boolean accept(SceneObject GDoor) {
		        	if (GDoor != null
		        			&& MatchID(GDoor.getId(),Variable.BackDoor)
		        			&& !Variable.BlackList.contains(GDoor.getLocation())
		        			&& !Variable.BlackDoor.contains(GDoor.getLocation())
							&& Calc.Reach(GDoor)) {
						return true;
					} else {
						return false;
					}
				}

			}; 
       }
	private static void UnlockKeyDoor() {
		SceneObject KDoor = SceneEntities.getNearest(Key_Door());
		if(KDoor !=null){
			if(KDoor.validate()){

				if (Calculations.distanceTo(KDoor.getLocation()) <= 2) {
					for (int x = 0; x < 15; x++) {
						if (Calculations.distanceTo(KDoor.getLocation()) <= 2) {
							Point Centrals = KDoor.getModel().getCentralPoint();
							Mouse.move(Centrals.x, Centrals.y);
							if (Menu.contains("Enter")
									|| Menu.contains("Unlock")
									|| Menu.contains("Open")) {
								Mouse.click(Centrals.x, Centrals.y, true);
								Task.sleep(500, 700);
								for(int d = 0 ; d < 20 && Players.getLocal().isMoving(); d++, Task.sleep(100, 150));
							}

						}
					}

				
			     	int x = KDoor.getLocation().getX();
			     	int y = KDoor.getLocation().getY();
				     Variable.KeyEnterd.add(new Tile(x+1,y, 0));
			     	Variable.KeyEnterd.add(new Tile(x-1,y, 0));
			     	Variable.KeyEnterd.add(new Tile(x,y+1, 0));
			     	Variable.KeyEnterd.add(new Tile(x,y-1, 0));
			     	Variable.EnterdDoor.add(new Tile(x+1,y, 0));
			     	Variable.EnterdDoor.add(new Tile(x-1,y, 0));
			     	Variable.EnterdDoor.add(new Tile(x,y+1, 0));
					Variable.EnterdDoor.add(new Tile(x,y-1, 0));

					Add_BackDoor(KDoor);
		     		
			}else{
				Walking.walk(KDoor);
				Camera.turnTo(KDoor);
				}
			}
		}
	}
	


	public static void unlockDoor(SceneObject n,boolean e){
		boolean DeadEnd = false;
		if(n !=null && n.validate()){
			if(Calculations.distanceTo(n) < 2){
				if(!e){
					DeadEnd = Room_Job.DeadEnd();
				}
				for (int x = 0; x < 15; x++) {
					if (Calculations.distanceTo(n) < 2) {
						Point Central = n.getModel().getCentralPoint();
						Mouse.move(Central.x, Central.y);
						if (Menu.contains("Enter") || Menu.contains("Unlock")
								|| Menu.contains("Open")) {
							Mouse.click(Central.x, Central.y, true);
							Task.sleep(1000, 1500);
							for(int d = 0 ; d < 20 && Players.getLocal().isMoving(); d++, Task.sleep(100, 150));

						}
					}
				}
				if(e){
				Add_BackDoor(n);
				}else{
				if(DeadEnd){
					Variable.Break_Puzzle = false;
					SceneObject Back = SceneEntities.getNearest(Variable.All);
					Variable.BlackDoor.add(Back.getLocation());
					LogHandler.Print("Added This Door To Black List" , Color.red);
				}
  					Variable.BlackList.add(n.getLocation());
				}
				System.out.println("Finshed Enter Door Sccusfully");
			}else{
				Walking.walk(n);
				Task.sleep(500,600);
				for(int d = 0 ; d < 20 && Players.getLocal().isMoving(); d++, Task.sleep(100, 150));
			}
		}
	}
	private static void Add_BackDoor(SceneObject n) {
		Variable.EnterdDoor.add(n.getLocation());
		SceneObject backDoor = Actions.Set_Object(Variable.BackDoor);
		if(backDoor !=null){
			Variable.EnterdDoor.add(backDoor.getLocation());
		Variable.BackBasic.add(backDoor.getLocation());
		}
	}

	/*
	 * Basic Doors
	 */
	public static boolean ThereBasicDoor(){
		return Actions.Set_Object(Basic_Door()) !=null && Actions.Object_IsValid(Actions.Set_Object(Basic_Door()).getId());
	}
	public static Filter<SceneObject> Basic_Door(){
		 return new Filter<SceneObject>() {
					public boolean accept(SceneObject Basic) {
						return (Basic != null && MatchID(Basic.getId(),Variable.BackDoor)
								&& !Variable.BlackDoor.contains(Basic.getLocation())
								&& !Variable.EnterdDoor.contains(Basic.getLocation())
								&& !Variable.BackBasic.contains(Basic.getLocation())
								&& Calc.Reach(Basic));
					}
				};
}

	private boolean ThereGuardainDoor() {
		return Actions.Set_Object(Gurdain_Door())!=null &&  Actions.Object_IsValid(Actions.Set_Object(Gurdain_Door()).getId());
	}
	public Filter<SceneObject> Gurdain_Door() {
		return new Filter<SceneObject>() {
			public boolean accept(SceneObject GDoor) {
				return (GDoor != null
						&& MatchID(GDoor.getId(), Variable.Gurdain_Door)
						&& !Variable.EnterdDoor.contains(GDoor.getLocation())
						&& !Variable.BlackDoor.contains(GDoor.getLocation()) 
						&& (Calc.Reach(GDoor) || Variable.CurrentRoom.contains(GDoor.getLocation())));
			}

		};
	}

	public static boolean MatchID(int id, int[] Numbers) {
		for(int i:Numbers){
			if(id == i){
				return true;
			}
		}
		return false;
	}
	/*
	 * Key Door
	 */
	public static boolean ThereKeyDoor(){
		return Actions.Set_Object(Key_Door())!=null &&  Actions.Object_IsValid(Actions.Set_Object(Key_Door()).getId());
	}
	public static Filter<SceneObject> Key_Door(){
		 return new Filter<SceneObject>() {
					public boolean accept(SceneObject KDoor) {
						if (KDoor != null 
								&& (Calc.Reach(KDoor) ||Variable.CurrentRoom.contains(KDoor.getLocation()))
								&& (Variable.KeyEnterd.contains(KDoor.getLocation()) || MatchedKey(KDoor))
								&& KDoor.validate()
								&& MatchIDKey(KDoor)
								&& !Variable.EnterdDoor.contains(KDoor.getLocation())
								&& !Variable.BlackDoor.contains(KDoor.getLocation())) {
							return true;
						} else {
							return false;
						}
			
					}
				};
		}


	private static boolean MatchId(SceneObject sd,int[][] as) {
		for(int x[] : as){
			for(int e: x){
				if(sd.getId() == e){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean MatchIDKey(SceneObject object) {
		return (MatchID(object.getId(),Variable.YELLOW_DOORS) 
				|| MatchID(object.getId(),Variable.GREEN_DOORS)
				|| MatchID(object.getId(),Variable.BLUE_DOORS)
				|| MatchID(object.getId(),Variable.ORANGE_DOORS)
				|| MatchID(object.getId(),Variable.SILVER_DOORS)
				|| MatchID(object.getId(),Variable.PURPLE_DOORS)
				|| MatchID(object.getId(),Variable.CRIMSON_DOORS)
				|| MatchID(object.getId(),Variable.GOLD_DOORS)
				|| MatchID(object.getId(),Variable.Second_YELLOW_DOORS) 
				|| MatchID(object.getId(),Variable.Second_GREEN_DOORS)
				|| MatchID(object.getId(),Variable.Second_BLUE_DOORS)
				|| MatchID(object.getId(),Variable.Second_ORANGE_DOORS)
				|| MatchID(object.getId(),Variable.Second_SILVER_DOORS)
				|| MatchID(object.getId(),Variable.Second_PURPLE_DOORS)
				|| MatchID(object.getId(),Variable.Second_CRIMSON_DOORS)
				|| MatchID(object.getId(),Variable.Second_GOLD_DOORS));
	}
	public static boolean MatchedKey(SceneObject object) {
		if(MatchID(object.getId(),Variable.YELLOW_DOORS)){

			Variable.CurrentKey = Variable.YellowKey[GetNumber(object.getId(),Variable.YELLOW_DOORS)];
			
		}else if(MatchID(object.getId(),Variable.GREEN_DOORS)){

			Variable.CurrentKey =Variable.GreenKey[GetNumber(object.getId(),Variable.GREEN_DOORS)];
			
		}else if(MatchID(object.getId(),Variable.BLUE_DOORS)){
	
			Variable.CurrentKey = Variable.BlueKey[GetNumber(object.getId(),Variable.BLUE_DOORS)];
			
		}else if(MatchID(object.getId(),Variable.ORANGE_DOORS)){

			Variable.CurrentKey = Variable.OrangeKey[GetNumber(object.getId(),Variable.ORANGE_DOORS)];
			
		}else if(MatchID(object.getId(),Variable.SILVER_DOORS)){
	
			Variable.CurrentKey = Variable.SilverKey[GetNumber(object.getId(),Variable.SILVER_DOORS)];
			
		}else if(MatchID(object.getId(),Variable.PURPLE_DOORS)){
		
			Variable.CurrentKey = Variable.PurbleKey[GetNumber(object.getId(),Variable.PURPLE_DOORS)];
			
		}else if(MatchID(object.getId(),Variable.CRIMSON_DOORS)){

			Variable.CurrentKey = Variable.CrimsonKey[GetNumber(object.getId(),Variable.CRIMSON_DOORS)];
			
		}else if(MatchID(object.getId(),Variable.GOLD_DOORS)){
		
			Variable.CurrentKey = Variable.GoldKey[GetNumber(object.getId(),Variable.GOLD_DOORS)];
		
		}
		
		return(Variable.Inevntory_KEY.contains(Variable.CurrentKey) || Variable.KeyEnterd.contains(object.getLocation()));
   	 
	}
	private static int GetNumber(int id,int[] grup){
		int p = 0;
		for(int i : grup){
			if(i == id){
				return p;
			}
			p++;
		}
		return p;
	}

	private static Item getFood() {
		for (Item i : Inventory.getItems()) {
			for (int s : Variable.FISH_ID) {
				if (i.getId() == s) {
					return i;
				}
			}
		}
		return null;
	}

	public static void Eat() {
		if (Players.getLocal().getHpPercent() <= 50) {
			if (!Tabs.INVENTORY.isOpen()) {
				Tabs.INVENTORY.open();
			}
			Item food = getFood();
			if (food != null) {
				food.getWidgetChild().interact("Eat");
			}
		}
	}
}
