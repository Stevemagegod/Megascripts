package megascripts.dungoneering.puzzle;

import java.util.ArrayList;

import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.api.Flood;
import megascripts.dungoneering.Variable;
import megascripts.graphic.LogHandler;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class GrooveSpike {

	public static int Spikes [] = {49390,49392,49394,49396,49398,49400,49402,49404,49406,49408,49410,49412};
	public static int[] Spike_Line1 = {Spikes[0],Spikes[1],Spikes[2],Spikes[3]};
	public static int[] Spike_Line2 = {Spikes[4],Spikes[5],Spikes[6],Spikes[7]};
	public static int[] Spike_Line3 = {Spikes[8],Spikes[9],Spikes[10],Spikes[11]};
    public static int[][] Lines = {Spike_Line1,Spike_Line2,Spike_Line3};

	
	public static boolean UnCompleted(){
		SceneObject Spike = SceneEntities.getNearest(Spikes);
		if(Spike == null){
			return false;
		}
		return Calc.Reach(Spike) && DistCheck();
	}
	private static boolean DistCheck() {
      int a = (int)Calculations.distanceTo(SceneEntities.getNearest(Lines[0]));
      int b = (int)Calculations.distanceTo(SceneEntities.getNearest(Lines[1]));
      int c = (int)Calculations.distanceTo(SceneEntities.getNearest(Lines[2]));
		return a == 0 || b == 0 || b == 1 || a == 2 ||  c == 1;
	}
	public static boolean IsCompleted(){
		return !UnCompleted();
	}
	public static void solve(){
		for(int[] line : Lines){
		for(SceneObject sp: SceneEntities.getLoaded(line)){
			if(sp !=null){
				int a= (int)Calculations.distanceTo(sp);
				if(sp.isOnScreen() && a <= 2){
					sp.interact("Step-onto");
                    Task.sleep(1000,1500);
					for(int x= 0; x < 20 && Players.getLocal().isMoving() ||  Players.getLocal().getAnimation() !=-1 ; x++,Task.sleep(100,150));
                    Task.sleep(1500,2500);
                    if(a == 0){
                    	
                    }
				}else{
					Walking.walk(sp);
					for(int x= 0; x < 20 && Players.getLocal().isMoving() ; x++,Task.sleep(100,150));
				}
			}
		}
		}
	}
}
