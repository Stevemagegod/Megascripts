package megascripts.dungoneering.boss;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Runebound {

	public static void Kill(){
		for(NPC Boss : NPCs.getLoaded()){
			if(Boss !=null){
				if(Boss.getName().contains("Runebound")){
					if(Boss.isOnScreen()){
					if(Players.getLocal().getInteracting()== null){
						Boss.interact("Attack");
						Task.sleep(1000,1500);
					}
					}else{
						Walking.walk(Boss);
						Camera.turnTo(Boss);
					}
				}
			}
		}
	}
}
