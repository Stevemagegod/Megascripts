package megascripts.dungoneering.boss;

import java.awt.Color;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import megascripts.api.Prayer;
import megascripts.api.Prayer.Ancient;
import megascripts.api.Prayer.Modern;
import megascripts.dungoneering.Variable;
import megascripts.graphic.LogHandler;

public class Rammernaut {

	public static void Kill_Rammernaut(){
		for(NPC Boss : NPCs.getLoaded()){
			if(Boss !=null){
			if(Boss.getName().contains("Rammer")){	
			if (Boss != null) {
				if (Boss.isOnScreen()) {
					if (Players.getLocal().getInteracting() == null) {
						Boss.interact("Attack");
						Task.sleep(1000);
					}
				} else {
					Walking.walk(Boss);
					Task.sleep(1000);
				}
			}
		
		if(Skills.getLevel(Skills.PRAYER) >= 71) {
						if(!Prayer.isModernSetActive()) {
							if(Prayer.getRemainingPoints() > 0) {
								if(!Prayer.isActive(Ancient.DEFLECT_MELEE)) {
									Tabs.PRAYER.open();
									Prayer.setActivated(Ancient.DEFLECT_MELEE, true);
									Task.sleep(90, 110);
									Tabs.INVENTORY.open();
								}
							}
						}
					}
			}
			if (Boss.getMessage().contains("!")) {
				LogHandler.Print("Running From Charge!!!",Color.GREEN);
				int y = Random.nextInt(0, 3);
				Walking.walk(Variable.CurrentRoom
						.getBoundingTiles()[y]);

			} 
			}		
		}
	}
	
}
