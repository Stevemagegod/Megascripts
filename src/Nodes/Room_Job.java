package megascripts.dungoneering.node;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.api.Combat;
import megascripts.api.Flood;
import megascripts.api.Items;
import megascripts.dungoneering.MegaDungeon;
import megascripts.dungoneering.Variable;
import megascripts.dungoneering.boss.CurrentBoss;
import megascripts.dungoneering.puzzle.CurrentPuzzle;
import megascripts.dungoneering.puzzle.Magicalconstruct;
import megascripts.dungoneering.puzzle.Monolith;
import megascripts.dungoneering.puzzle.Pondskaters;
import megascripts.dungoneering.puzzle.SlidingPuzzle;
import megascripts.graphic.LogHandler;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;


public class Room_Job extends Node{


	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_DUNGEON && !Actions.AtStartRoom() && Actions.InDungeon() &&( CurrentPuzzle.TherePuzzle() ||  Actions.ThereBoss() || Actions.ThereFish() || Actions.ThereKey() || Actions.ThreNPC());
	}

	@Override
	public void execute() {
		try{
			Variable.CurrentRoom = Flood.getArea();
			if (Actions.ThereBoss()) {
				Variable.ROOM_COLOR = Color.red;
				if (EndDungeon()) {
					End_Dungeon();
				} else {
					Eat();
					CurrentBoss.GetCurrentBossTactic();
				}

			}else if (DeadEnd()) {
				Variable.ROOM_COLOR = Color.black;
				Combat.TurOffRet();
				if (Actions.ThereKey()) {
					Loot_StartRoom.loot(Variable.KeyGround, true);
				} else {
					if (Dungeon_Doors.ThereBackDoor()) {
						Dungeon_Doors.unlockDoor(
								Actions.Set_Object(Dungeon_Doors.Door_Back()),
								false);
					}
				}
			} else if (CurrentPuzzle.TherePuzzle()) {
				CurrentPuzzle.solve();
			}else if (Actions.ThreNPC()) {
				Variable.ROOM_COLOR = Color.red;
				Combat.TurOnRet();
				Kill();
			} else if (Actions.ThereKey()) {
				Variable.ROOM_COLOR = Color.BLUE;
				Loot_StartRoom.loot(Variable.KeyGround, true);
			} else if (Actions.ThereFish()) {
				Variable.ROOM_COLOR = Color.BLUE;
				Loot_StartRoom.loot(Variable.FISH_ID, false);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}




	public static boolean DeadEnd() {
		if (Actions.AtStartRoom()) {
			return false;
		}
		int REACHED_DOOR = 0;
	    int Tunnel = 0;
	    Variable.CurrentRoom = Flood.getArea();
		for(SceneObject  Loaded : SceneEntities.getLoaded(Variable.All)){
			if(Loaded !=null && (Calc.Reach(Loaded) || Variable.CurrentRoom.contains(Loaded.getLocation()))){
				REACHED_DOOR++;
				if(Variable.BlackDoor.contains(Loaded.getLocation())){
					Tunnel++;
				}
			}
		}
		
		System.out.println("There is Only Door " + REACHED_DOOR/2 + "And Tunnel Option is " + Tunnel );
		return (REACHED_DOOR/2 == 1 || REACHED_DOOR/2 - Tunnel/2 == 1) || Variable.Break_Puzzle;
	
	}

	private void Kill() {
		try{
			Eat();
		for(NPC Monster:NPCs.getLoaded()){		
			if(Monster !=null){
				if(Players.getLocal().getInteracting() ==null && !Dungeon_Doors.MatchID(Monster.getId(), Variable.PuzzleNPC) && Monster.getId() != Variable.Smuggler){
					if(Calc.Reach(Monster)){
						if(Monster.isOnScreen()){
							Monster.interact("Attack");
							Task.sleep(400,800);
						}else{
							Walking.walk(Monster);
							Camera.turnTo(Monster);
						}
					}
				}
			}
	}
		}catch (NullPointerException e){
			e.printStackTrace();
		}
	}

	private static boolean EndDungeon() {
		SceneObject Door = SceneEntities.getNearest(49696, 49698, 49700);
		return Door != null && Calc.Reach(Door);
	}
	
	public void End_Dungeon() {
		int EndLadder [] = {49698,49696,49700};

		SceneObject Out = SceneEntities.getNearest(EndLadder);
		WidgetChild Contuine = Widgets.get(1188).getChild(2);
		Widget EndWindows = Widgets.get(933);
		WidgetChild Skip = Widgets.get(933).getChild(13);
		WidgetChild Ready = Widgets.get(933).getChild(323);
		if (Contuine.validate() || EndWindows.validate()) {
			if (Contuine.validate()) {
				Contuine.click(true);
				Task.sleep(1500);
			}
			if (EndWindows.validate()) {
				if (Skip.validate()) {
					LogHandler.Print("Ending Dungeon...",Color.BLUE);
					Skip.click(true);
					Task.sleep(1500);
				}
				if (Ready.validate()) {
					Ready.click(true);
					Task.sleep(1000,1500);
					Variable.EnterdDoor.clear();
					Variable.KeyEnterd.clear();
					Variable.BlackList.clear();
					Variable.Inevntory_KEY.clear();
					Variable.BackBasic.clear();
					//ExpGaind = (Skills.getExperience(Skills.DUNGEONEERING) -StartXP);
					//TokenGaind = (ExpGaind/10);
			       // Status = "Ready For Next Dungeon...";
					Variable.Dungeons_TIMER.add(Variable.Dungeon_Time.getElapsed());
					LogHandler.Print("Ready For Next Dungeon...",Color.GREEN);
					Task.sleep(1500,2000);
                  //  BossName = "None...";
					Reset();
					Variable.DungeonCompleted++;
					LogHandler.Print("We Done " + Variable.DungeonCompleted + " So Far", Color.red);
				}
			}
		} else {
			if (Out != null) {
				if ((Calculations.distance(Players.getLocal().getRegionOffset(), Out.getRegionOffset()) <=3)) {
					int OX = Out.getLocation().getX();
					int OY = Out.getLocation().getY();
					Walking.walk(new Tile(OX -1,OY -1,0));
					Out.interact("End-dungeon");
					Task.sleep(2000);
				} else {
					Walking.walk(Out.getLocation());
					Task.sleep(1500);
				}
			}
		}
	}

	private void Reset() {
		Monolith.Solved = false;
		Pondskaters.Solved = false;
		SlidingPuzzle.Solved = false;
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


	public Filter<SceneObject> DoorLen(){
		return new Filter<SceneObject>() {
			public boolean accept(SceneObject Skoor) {
				if (Skoor != null && MatchID(Skoor)
						&& Calc.Reach(Skoor)) {
					return true;
				} else {
					return false;
				}
			}

		};
	}
	private boolean MatchID(SceneObject Door){
		return (Dungeon_Doors.MatchIDKey(Door) ||Dungeon_Doors.MatchID(Door.getId(), Variable.Gurdain_Door)
				||Dungeon_Doors.MatchID(Door.getId(), Variable.BackDoor)); 
	}


	public static void Eat() {
		if (getHp() <= 2500) {
			if (!Tabs.INVENTORY.isOpen()) {
				Tabs.INVENTORY.open();
			}
			Item food = getFood();
			if (food != null) {
				food.getWidgetChild().interact("Eat");
			}
		}
	}

	public static int getHp() {
		return Integer.parseInt(Widgets.get(748,8).getText());
	}
}
