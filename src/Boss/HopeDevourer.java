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

public class HopeDevourer {

	public static void Kill(){
		for(NPC Boss : NPCs.getLoaded()){
			if(Boss !=null){
				if(Boss.getName().contains("Hope")){
					if(Boss.getMessage().contains("r")){
						Prayer.setActivated(Ancient.DEFLECT_MELEE, false);
						Prayer.setActivated(Modern.PROTECT_FROM_MELEE, false);
					}else{
						if(Skills.getLevel(Skills.PRAYER) >= Modern.PROTECT_FROM_MELEE.getRequiredLevel()) {
							if(Prayer.isModernSetActive()) {
								if(Prayer.getRemainingPoints() > 0) {
									if(!Prayer.isActive(Modern.PROTECT_FROM_MELEE)) {
										Tabs.PRAYER.open();
										Prayer.setActivated(Modern.PROTECT_FROM_MELEE, true);
										Task.sleep(90, 110);
										Tabs.INVENTORY.open();
									}
								}
							}
						} if(Skills.getLevel(Skills.PRAYER) >= Ancient.DEFLECT_MELEE.getRequiredLevel()) {
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
					if (Boss.isOnScreen()) {
						if (Players.getLocal().getInteracting() == null) {
							Boss.interact("Attack");
							Task.sleep(1000, 1500);
						}
					} else {
						Walking.walk(Boss);
						Camera.turnTo(Boss);
						Task.sleep(1000, 1500);
					}
				}
			}
		}
	}
}
