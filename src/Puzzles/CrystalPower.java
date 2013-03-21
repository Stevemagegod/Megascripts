package megascripts.dungoneering.puzzle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.dungoneering.node.Dungeon_Doors;
import megascripts.graphic.LogHandler;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class CrystalPower {

	public static int OFF_CRYSTAL[] = { 49471 };
	public static int BIG_CRYSTAL[] = { 49510, 49507 };

	public static int Pad = 52206;
	public static int OFF_TILE = 49465;
	public static int RED = 49477,YELLOW = 49495,GREEN = 49486,BLUE =49468;
	
	public static boolean RED_LIGHT = false,GREEN_LIGHT = false,YELLOW_LIGHT = false,BLUE_LIGHT = false;
	public static boolean UnCompleted() {
		SceneObject crystal = SceneEntities.getNearest(BIG_CRYSTAL);
		if (crystal == null) {
			return false;
		}
		return Calc.Reach(crystal);
	}

	public static boolean isCompleted() {
		return !UnCompleted();
	}
	public static void solve(){
		if(ThereUnLight()){
			Light();
		}else{
			ArrayList<Integer> ColorIndex = new ArrayList<Integer>();
			ColorIndex.clear();
			int ri = getLightIndex(Pad,RED);
			int gi = getLightIndex(Pad,GREEN);
			int yi = getLightIndex(Pad,YELLOW);
			int bi = getLightIndex(Pad,BLUE);
			ColorIndex.add(ri);
			ColorIndex.add(gi);
			ColorIndex.add(yi);
			ColorIndex.add(bi);
			int mr = getMostReapeat(ColorIndex);
			RED_LIGHT = ColorIndex.get(0) != mr;
			GREEN_LIGHT = ColorIndex.get(1) != mr;
			YELLOW_LIGHT = ColorIndex.get(2) != mr;
			BLUE_LIGHT = ColorIndex.get(3) != mr;
			System.out.println(".......... Loading.....");
			System.out.println("Red: " +RED_LIGHT);
			System.out.println("Green: " +GREEN_LIGHT);
			System.out.println("Yellow: " +YELLOW_LIGHT);
			System.out.println("Blue: " +BLUE_LIGHT);
			System.out.println(".......... Done.....");
			
			if(RED_LIGHT){
				if(ri !=mr ){
					pause(Pad,RED);
				}else if(ri == mr -1){
					unpause(Pad,RED);
				}
			}else if(YELLOW_LIGHT){
				LogHandler.Print("Yallow Light",Color.yellow);
				if(yi !=mr ){
					pause(Pad,YELLOW);
				}else if(yi == mr -1){
					unpause(Pad,YELLOW);
				}
			}else if(GREEN_LIGHT){
				
				if(gi !=mr ){
					pause(Pad,GREEN);
					LogHandler.Print("Pause Green Light",Color.GREEN);
				}else if(gi == mr -1){
					LogHandler.Print("Unpause Green Light",Color.GREEN);
					unpause(Pad,GREEN);
				}
			}
		}
		
	}

	private static void unpause(int pad, int red) {
		  SceneObject PAD = getNearstObject(pad, red);
		  if(PAD !=null){
			  Tile d = new Tile(PAD.getLocation().getX(),PAD.getLocation().getY(),0);
			  d.click(true);
			  Task.sleep(900,1600);
		  }
	}

	private static void pause(int pad, int red) {
	  SceneObject PAD = getNearstObject(pad, red);
		if(PAD !=null){
			if(PAD.isOnScreen()){
				if (!Players.getLocal().isMoving() && !Players.getLocal().getLocation().equals(PAD.getLocation())) {
					PAD.getLocation().click(true);
				}
			}else{
				Walking.walk(PAD);
			}
		}
	}

	private static int getMostReapeat(ArrayList<Integer> ci) {
		ArrayList<Integer> Amount = new ArrayList<Integer>();
		int Count_0 = 0;
		int Count_1 = 0;
		int Count_2 = 0;
		int Count_3 = 0;
		int index [] = {1,2,3,4};
		for(int x: ci){
			if(x == 1){
				Count_0++;
			}
		}
		for(int x: ci){
			if(x == 1){
				Count_1++;
			}
		}
		for(int x: ci){
			if(x == 1){
				Count_2++;
			}
		}
		for(int x: ci){
			if(x == 1){
				Count_3++;
			}
		}
		Amount.add(Count_0);
		Amount.add(Count_1);
		Amount.add(Count_2);
		Amount.add(Count_3);
		int e =  Amount.indexOf(Collections.max(Amount));
	    int mostrepeat = index[e];
	    return mostrepeat;
	}
	private static SceneObject getNearstObject(int b,int sec) {
		ArrayList<Integer>Dist = new ArrayList<Integer>();
		ArrayList<SceneObject>objects = new ArrayList<SceneObject>();
		Dist.clear();
		objects.clear();
		SceneObject object = SceneEntities.getNearest(sec);
		for(SceneObject x : SceneEntities.getLoaded(b)){
			if(x !=null && Calc.Reach(x)){
				int e = (int)Calculations.distance(x,object);
				Dist.add(e);
				objects.add(x);
			}
		}
		int m =Collections.min(Dist);
		int re = Dist.indexOf(m);
		if(objects.get(re)== null){
			return null;
		}
		return objects.get(re);
	}

	private static int getLightIndex(int b,int sec) {
		ArrayList<Integer>Dist = new ArrayList<Integer>();
		ArrayList<SceneObject>objects = new ArrayList<SceneObject>();
		Dist.clear();
		objects.clear();
		SceneObject object = SceneEntities.getNearest(sec);
		for(SceneObject x : SceneEntities.getLoaded(b)){
			if(x !=null && Calc.Reach(x)){
				int e = (int)Calculations.distance(x,object);
				Dist.add(e);
				objects.add(x);
			}
		}
		int m =Collections.min(Dist);
		int re = Dist.indexOf(m);
		if(object != null){
			return 0;
		}
		return (int) Calculations.distance(objects.get(re), object);
	}

	private static void Light() {
		SceneObject n = SceneEntities.getNearest(OFF_CRYSTAL);
		if(n !=null){
			if(n.isOnScreen()){
				n.interact("Power-up");
				Task.sleep(600,900);
			}else{
				if(!Players.getLocal().isMoving()){
				Walking.walk(n);
				Camera.turnTo(n);
				}
			}
		}
	}

	private static boolean ThereUnLight() {
		SceneObject n = SceneEntities.getNearest(OFF_CRYSTAL);
		return n !=null && n.validate() && Calc.Reach(n);
	}



}
