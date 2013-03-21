package megascripts.dungoneering.puzzle;

import java.util.ArrayList;

import megascripts.api.Flood;
import megascripts.dungoneering.Variable;
import megascripts.dungoneering.node.Dungeon_Doors;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.LocalPath;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Fishingferret {

	public static int Pad = 49555;
    public static int Ferrets = 11007;
    public static Tile Path;
    public static int AreaTiles_ID [] = {49555,49546};
    public static  ArrayList<Tile> RoomTile = new ArrayList<Tile>();
    
	public static boolean UnCompleted(){
		 SceneObject d = SceneEntities.getNearest(Pad);
		 if(d == null){
			 return false;
		 }
		 Variable.CurrentRoom = Flood.getArea();
		 return Variable.CurrentRoom.contains(d.getLocation());
	}
	public static boolean isCompleted(){
		return !UnCompleted();
	}
	public static void solve(){
		for(Tile x : getFerretPath(Ferrets , Pad)){
			if (x !=null){
				RoomTile.add(x);
				System.out.println("Added To Path");
			}
		}
	}

	private static Tile[] getFerretPath(int f, int p){
		SceneObject n = SceneEntities.getNearest(p);
		NPC e = NPCs.getNearest(f);
		Tile Start = null;
		Tile End = null;
		if(e !=null && n !=null){
			Start = e.getLocation();
		    End = n.getLocation();
		}
		return megascripts.api.LocalPath.findPath(Start, End);
	}
	
	private static void findPath(int f, int p) {
		Fishingferret.getFerretTile();
		SceneObject n = SceneEntities.getNearest(p);
		NPC e = NPCs.getNearest(f);
		int z = (int) Calculations.distance(n, e);
		for (int r = 0; r >= z; r++) {
           for(Tile t1 : Fishingferret.RoomTile){
           int w = (int)Calculations.distance(t1 ,n.getLocation());
           int m = (int)Calculations.distance(e ,n.getLocation());
           int d = (int)Calculations.distance(e.getLocation() ,t1.getLocation());
              if(t1 !=null && w == m-1 &&  d == 1){
            	  Path = t1;
              }
           }
		}
	}
	public static void getFerretTile() {
	   Variable.CurrentRoom = Flood.getArea();
	   for(Tile x : Variable.CurrentRoom.getTileArray()){
		   if(x !=null  && getObject(x) !=null && Dungeon_Doors.MatchID(getObject(x).getId(), AreaTiles_ID)){
			   RoomTile.add(x);
			}
	   }
	}

	private static SceneObject getObject(Tile x) {
		return SceneEntities.getAt(x);
	}

}
