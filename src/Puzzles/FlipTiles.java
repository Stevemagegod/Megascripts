package megascripts.dungoneering.puzzle;

import java.awt.Color;

import megascripts.api.Calc;
import megascripts.graphic.LogHandler;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class FlipTiles {

	
	public static int Green = 49638;
	public static int Yellow = 49641;
	
	public static boolean UnCompleted(){
		SceneObject Yellow_Tile  = SceneEntities.getNearest(Yellow);
		SceneObject Green_Tile  = SceneEntities.getNearest(Green);
		if(Green_Tile == null || Yellow_Tile == null){
			return false;
		}
		return Calc.Reach(Yellow_Tile) && Calc.Reach(Green_Tile);
	}
	public static boolean isCompleted(){
		return !UnCompleted();
	}
	public static void solve(){
		SceneObject Yellow_Tile  = SceneEntities.getNearest(Yellow);
		SceneObject Green_Tile  = SceneEntities.getNearest(Green);
		if (Green_Tile != null) {
		   if(getTileCount(Green_Tile.getLocation(), Yellow) < getTileCount(Green_Tile.getLocation(), Green) ){
				LogHandler.Print("Imbue This Tile...",Color.BLUE);
			   Imbue(Green_Tile);
			}else{
				LogHandler.Print("Forcing This Tile...",Color.green);
				Force(Green_Tile);
			}
		}
	}
	public static void Imbue(SceneObject object) {
		object.interact("Imbue");
		for(int d = 0; d < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1; d++,Task.sleep(100,150));
	}
	public static void Force(SceneObject object) {
		object.interact("Force");
		for(int d = 0; d < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1; d++,Task.sleep(100,150));
	}
	public static int getTileCount(Tile x, int ID){
		int e = 0;
		
		if(getObject(x) !=null && getObject(x).getId() == ID){
			e++;
		}
		if(getObject(getWest(x)) !=null && getObject(getWest(x)).getId() == ID){
			e++;
		}
		if(getObject(getEast(x)) !=null && getObject(getEast(x)).getId() == ID){
			e++;
		}
		if(getObject(getNorth(x)) !=null && getObject(getNorth(x)).getId() == ID){
			e++;
		}
		if(getObject(getSouth(x)) !=null && getObject(getSouth(x)).getId() == ID){
			e++;
		}
		return e;
	}
	public static SceneObject getObject(Tile x){
		return SceneEntities.getAt(x);
	}
	public static Tile getWest(Tile x){
		return new Tile((x.getX() -1) , x.getY(),0);
	}
	public static Tile getEast(Tile x){
		return new Tile((x.getX()+1) , x.getY(),0);
	}
	public static Tile getNorth(Tile x){
		return new Tile(x.getX() , (x.getY() +1),0);
	}
	public static Tile getSouth(Tile x){
		return new Tile(x.getX() , (x.getY() -1),0);
	}
}
