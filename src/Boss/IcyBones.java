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

public class IcyBones {

	public static void Kill_Icy_Bones() {
		for (NPC Boss : NPCs.getLoaded()) {
			if (Boss != null) {
				if (Boss.getName().toLowerCase().equals("icy bones")) {
					if (Skills.getLevel(Skills.PRAYER) >= 43) {
						if (Prayer.isModernSetActive()) {
							if (Prayer.getRemainingPoints() > 0) {
								if (!Prayer.isActive(Modern.PROTECT_FROM_MELEE)) {
									Tabs.PRAYER.open();
									Prayer.setActivated(
											Modern.PROTECT_FROM_MELEE, true);
									Task.sleep(90, 110);
									Tabs.INVENTORY.open();
								}
							}
						}
					}
					if (Skills.getLevel(Skills.PRAYER) >= 71) {
						if (!Prayer.isModernSetActive()) {
							if (Prayer.getRemainingPoints() > 0) {
								if (!Prayer.isActive(Ancient.DEFLECT_MELEE)) {
									Tabs.PRAYER.open();
									Prayer.setActivated(Ancient.DEFLECT_MELEE,
											true);
									Task.sleep(90, 110);
									Tabs.INVENTORY.open();
								}
							}
						}
					}
					if (Players.getLocal().getInteracting() == null) {
						if (Boss.isOnScreen()) {
							Boss.interact("Attack", Boss.getName());
							Task.sleep(400, 600);
						} else {
							if (!Players.getLocal().isMoving()) {
								Walking.walk(Boss.getLocation());
								Camera.turnTo(Boss);
							}
						}
					}
				}
			}
		}
	}

}
