package megascripts.dungoneering.boss;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;

import megascripts.api.Prayer;
import megascripts.api.Prayer.Ancient;
import megascripts.api.Prayer.Modern;
public class TokashTheBloodchiller {
	
	public static void Kill_Tokash_The_Bloodchiller(){
		for(NPC Boss : NPCs.getLoaded()){
			if(Boss != null){
				if(Boss.getName().contains("Bloodchiller")){
					if(Skills.getLevel(Skills.PRAYER) >= 37) {
						if(Prayer.isModernSetActive()) {
							if(Prayer.getRemainingPoints() > 0) {
								if(!Prayer.isActive(Modern.PROTECT_FROM_MAGIC)) {
									Tabs.PRAYER.open();
									Prayer.setActivated(Modern.PROTECT_FROM_MAGIC, true);
									Task.sleep(90, 110);
									Tabs.INVENTORY.open();
								}
							}
						}
					} if(Skills.getLevel(Skills.PRAYER) >= 65) {
						if(!Prayer.isModernSetActive()) {
							if(Prayer.getRemainingPoints() > 0) {
								if(!Prayer.isActive(Ancient.DEFLECT_MAGIC)) {
									Tabs.PRAYER.open();
									Prayer.setActivated(Ancient.DEFLECT_MAGIC, true);
									Task.sleep(90, 110);
									Tabs.INVENTORY.open();
								}
							}
						}
					}
					if(Boss.isOnScreen()){
					if(Players.getLocal().getInteracting() == null){
						Boss.interact("Attack");
						Task.sleep(2000);
					}
					}else{
						if(!Players.getLocal().isMoving()){
							Walking.walk(Boss.getLocation());
							Camera.turnTo(Boss);
							Task.sleep(2000);
						}
					}
				}
			}
		}
	}
	
}
