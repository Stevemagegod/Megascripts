package megascripts.dungoneering.puzzle;

import java.awt.Point;

import megascripts.api.Calc;
import megascripts.api.Flood;
import megascripts.dungoneering.Variable;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class ColoredFurret {

	public static int FURRET []= {12169,12167};
	public static int PRESURE [] = {54380,54372};
	public static Tile Room;
	public static final int[] BAD_PLATES = { 54365, 54367, 54369, 54371, 54373, 54375, 54377, 54379, 54381, 54383, 54385, 54387, 54389, 54391, 54393, 54395, 54397, 54399, 54401, 54403, 33611, 33618, 33623, 33629, 33632 };
	public static final int[] RED_TILE = { 54364, 54366, 54368, 54370, 33609 } ,
	BLUE_TILE = { 54372, 54374, 54376, 54378, 33617 }, 
	GREEN_TILE = { 54380, 54382, 54384, 54386, 33619 },
    YELLOW_TILE = { 54388, 54390, 54392, 54394, 33624 }, 
    ORANGE_TILE = { 54396, 54398, 54400, 54402, 33631 };

	/*public static final int[][] tiles = {RED_TILE, BLUE_TILE, GREEN_TILE, YELLOW_TILE, ORANGE_TILE};*/
	
	public static final int[] tiles = { 54364, 54366, 54368, 54370, 33609 ,
		54372, 54374, 54376, 54378, 33617 , 54380, 54382, 54384, 54386, 33619 ,
		54388, 54390, 54392, 54394, 33624 ,  54396, 54398, 54400, 54402, 33631 };
	
	public static boolean UnCompleted() {
		SceneObject UnPreSure = SceneEntities.getNearest(PRESURE);
		if (UnPreSure == null) {
			return false;
		}
		return Calc.Reach(UnPreSure);
	}

	public static boolean IsCompleted() {
		return !UnCompleted();
	}

	/*public static void slove() {
		System.out.println("Searching..");
       if(UnFinshed(GREEN_TILE)){
    		System.out.println("Found..");
    		NPC n = NPCs.getNearest(GREEN_FURRET);
    	   Room =  getNearstTile(GREEN_FURRET, GREEN_TILE);
    	   for(int x = 0 ; x < 20 && n.isMoving() ; x++ , Task.sleep(100,150));
			if (Room.isOnScreen()) {
				Room.click(true);
			} else {
				Walking.walk(Room);
			}
			for(int x = 0 ; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1 ; x++ , Task.sleep(100,150));
			n.click(true);
       }
	}*/
	
	private static Point alignment(Tile t1, Tile t2){
		int x = t2.getX() - t1.getX();
		int y = t2.getY() - t1.getY();
		if(x != 0)
			x = x > 0? 1 : -1;
		if(y != 0)
			y = y > 0 ? 1 : -1;
		return new Point(x,y);
	}
	
	public static void solve(){

		int count = 0;
		Tile safeTile = null;
		Tile dTile = null, wTile = null;
		SceneObject obj = SceneEntities.getNearest(tiles);
		if(obj !=null){
		safeTile = obj.getLocation();
		}
		NPC ferret;// = NPCs.getNearest(tiles[i]);//wait
		Tile fTile;
		while((ferret = NPCs.getNearest(FURRET)) != null && ! (fTile = ferret.getLocation()).equals(safeTile)){
		boolean far = Calculations.distance(fTile, safeTile) > 3;
		int fX = fTile.getX(), fY = fTile.getY();
		Point m = alignment(fTile, safeTile);
		if (m.x != 0 && m.y != 0) {
			far = !far || Math.abs(Players.getLocal().getLocation().getX() - fX) < 2 || Math.abs(Players.getLocal().getLocation().getY() - fY) < 2;
			Tile check1 = new Tile(fX - m.x, fY - m.y,0);
			Tile check2 = new Tile(fX - 2 * m.x, fY - 2 * m.y,0);
			Tile backup = far ? check2 : check1;
			dTile = far ? check1 : check2;
		} else if (m.x != 0 || m.y != 0) {
			if (isGoodTile(new Tile(fX + m.x, fY + m.y,0), BAD_PLATES)) { // Scare tile check
				Tile check1 = new Tile(fX - m.x, fY - m.y,0);
				Tile check2 = new Tile(fX - 2 * m.x, fY - 2 * m.y,0);
				Tile backup = far ? check2 : check1;
				wTile = far ? check1 : check2;
				if (!isGoodTile(wTile, BAD_PLATES))
					wTile = backup;
				if (!isGoodTile(wTile, BAD_PLATES))
					wTile = null;
		}
		}}
		NPC n = NPCs.getNearest(FURRET);
		if(n !=null){
 	   for(int x = 0 ; x < 20 && n.isMoving() ; x++ , Task.sleep(100,150));
		}
			if (dTile != null) {
				Room = wTile;
				Walking.walk(dTile);
			}
		for(int x = 0 ; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1 ; x++ , Task.sleep(100,150));
		
	}
	private static boolean isGoodTile(Tile wTile, int[] badPlates) {
		for(int d : badPlates){
			int e  = 0;
			if(SceneEntities.getAt(wTile) !=null){
			 e = SceneEntities.getAt(wTile).getId();
			}
			if(d == e){
				return false;
			}
		}
		return true;
	}

	//
	public static Tile getNearstTile(int fuuret, int presure[]) {
		Variable.CurrentRoom = Flood.getArea();
		NPC n = NPCs.getNearest(fuuret);
		SceneObject d = SceneEntities.getNearest(presure);
		for(Tile roomtile: Variable.CurrentRoom.getTileArray()){
		   int x = (int)Calculations.distance(n, d);
		   int o = (int)Calculations.distance(roomtile.getRegionOffset(), d.getRegionOffset());
		   int z = (int)Calculations.distance(roomtile, n);
			if (z == 1 && o - x == 1 && !Flood.isWall(roomtile)) {
				System.out.println("Found");
				return roomtile;
			}
		}
		return null;
	}

	public static boolean UnFinshed(int[] greenTile){
		SceneObject UnPreSure = SceneEntities.getNearest(greenTile);
		if (UnPreSure == null) {
			return false;
		}
		return Calc.Reach(UnPreSure);
	}
}
