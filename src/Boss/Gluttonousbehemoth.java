package megascripts.dungoneering.boss;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

import megascripts.api.Prayer;
import megascripts.api.Prayer.Ancient;
import megascripts.api.Prayer.Modern;


public class Gluttonousbehemoth {
	static int Gluttonous_Food = 49283;

	public static void KillGluttonousbehemoth() {
		SceneObject Food = SceneEntities.getNearest(Gluttonous_Food);
		for (NPC Boss : NPCs.getLoaded()) {
			if (Boss != null) {
				if (Boss.getName().contains("Gluttonous behemoth")) {
					if(Skills.getLevel(Skills.PRAYER) >= 43) {
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
					} if(Skills.getLevel(Skills.PRAYER) >= 71) {
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
					if (Calculations.distanceTo(Food.getRegionOffset()) <= 3) {
						if (!Boss.isInCombat()) {
							Boss.interact("Attack");
							Task.sleep(1500);
						}
					} else {
						if (!(Players.getLocal().isMoving())) {
							Walking.walk(Food.getLocation());
							Task.sleep(1500);
						} else {
							Task.sleep(500);
						}
					}
				}
			}
		}
	}

}
