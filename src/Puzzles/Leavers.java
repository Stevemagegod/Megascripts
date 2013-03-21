package megascripts.dungoneering.puzzle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.api.Flood;
import megascripts.dungoneering.Variable;
import megascripts.dungoneering.node.Dungeon_Doors;
import megascripts.graphic.LogHandler;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Leavers {

	public static int Pull_Leaver = 49381;
	public static int Pulled_Leaver = 49384;
	public static Tile step;
	
	public static boolean UnCompleted(){
		SceneObject Leave = SceneEntities.getNearest(Pull_Leaver);
		if(Leave == null){
			return false;
		}
		return Calc.Reach(Leave);
	}

	public static boolean isCompleted() {
		return !UnCompleted();
	}
	
	public static void solve(){
		step =getFirstLeveaerTile();
		if (ThereLeavers()) {
			Pull();
		}
	}

	private static Tile getFirstLeveaerTile() {
		ArrayList<Integer>Dist = new ArrayList<Integer>();
		ArrayList<SceneObject>objects = new ArrayList<SceneObject>();
		Dist.clear();
		objects.clear();
		SceneObject door = Actions.Set_Object(Dungeon_Doors.Door_Back()) ;
		for(SceneObject x : SceneEntities.getLoaded(Pull_Leaver)){
			if(x !=null && Calc.Reach(x)){
				int e = (int)Calculations.distance(x,door);
				Dist.add(e);
				objects.add(x);
			}
		}
		int m =Collections.min(Dist);
		int r = Dist.indexOf(m);
		if(objects.get(r)== null){
			return null;
		}
		return objects.get(r).getLocation();
	}

	private static void Pull() {
		SceneObject Leave = SceneEntities.getNearest(Pull_Leaver);
		SceneObject[] Pulled = SceneEntities.getLoaded(Pulled_Leaver);
		if(Leave !=null){
		if(!Leave.getLocation().equals(step) && Pulled.length == 0){
				Walking.walk(step);
				Camera.turnTo(step);
				for(int x = 0 ; x < 25 && Players.getLocal().isMoving() ; x++ , Task.sleep(100,150));
			}else{
		
			if(Leave.isOnScreen()){
				if(Leave.interact("Pull")){
			//	SceneObject[] Pulled = SceneEntities.getLoaded(Pulled_Leaver);
			// LogHandler.Print("We Have Pulled " + Pulled.length + " From 5",Color.green);
				}
			}else{
				Walking.walk(Leave);
				Camera.turnTo(Leave);
				for(int x = 0 ; x < 25 && (int)Calculations.distanceTo(Leave) > 4; x++ , Task.sleep(100,150));
				}
			}
		}
	}

	private static boolean ThereLeavers() {
		SceneObject Leave = SceneEntities.getNearest(Pull_Leaver);
		if(Leave == null){
			return false;
		}
		return Calc.Reach(Leave);
	}
}
