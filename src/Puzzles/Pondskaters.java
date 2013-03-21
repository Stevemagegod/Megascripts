package megascripts.dungoneering.puzzle;

import megascripts.api.Calc;
import megascripts.api.Flood;
import megascripts.dungoneering.Variable;

import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Pondskaters {

	public static int [] Pondskaters = {12089,12091,12092,12093};
	public static boolean Solved = false;
	public static boolean UnCompleted(){
		if(Solved){
			return false;
		}
		NPC n = NPCs.getNearest(Pondskaters);
		if(n == null){
			return false;
		}
		Variable.CurrentRoom = Flood.getArea();
		return Variable.CurrentRoom.contains(n.getLocation());
	}

	public static boolean isCompleted(){
		return !UnCompleted();
	}
	public static void solve(){
		NPC a = getKeyNpc();
		if(a !=null){
			a.interact("Catch");
		}
	}

	private static NPC getKeyNpc() {
		for(NPC  n : NPCs.getLoaded(Pondskaters)){
			if(n !=null && n.getAnimation() == 14405){
				return n;
			}
		}
		return null;
	}
}
