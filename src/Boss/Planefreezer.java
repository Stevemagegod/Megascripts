package megascripts.dungoneering.boss;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;


public class Planefreezer {

	public static void Kill(){
		for(NPC Boss : NPCs.getLoaded()){
			if(Boss.getName().contains("Plane-freezer"))
		if(Boss !=null){
			if(Calculations.distanceTo(Boss.getLocation()) <= 3){
				if(Players.getLocal().getInteracting() ==null){
				Boss.interact("Attack");
				Task.sleep(2000);
				}
			}else{
			Walking.walk(Boss);
			Task.sleep(2000);
			}
		}
		}
	}
}
