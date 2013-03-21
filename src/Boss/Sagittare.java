package megascripts.dungoneering.boss;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

import megascripts.api.Prayer;
import megascripts.api.Prayer.Ancient;
import megascripts.api.Prayer.Modern;

public class Sagittare {
	
	 public static void Kill_Sagittare() {
		 int specialAttack = 0; //Needs to be filled in!!!
		 for (NPC Boss : NPCs.getLoaded()) {
				if (Boss != null) {
					if (Boss.getName().contains("Sagittare")) {
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
						if (Boss.isOnScreen()) {
							if (Boss.getAnimation() == specialAttack) {
								int LX = Players.getLocal().getLocation().getX();
								int LY = Players.getLocal().getLocation().getY();
								Walking.walk(new Tile(LX + (Random.nextInt(1, 3)), LY + (Random.nextInt(1, 3)) , 0));
								Task.sleep(2000);
							} else {
								if (!Boss.isInCombat()) {
									Boss.interact("Attack");
									Task.sleep(1500);
								}
							}
						} else {
							Walking.walk(Boss.getLocation());
							Task.sleep(1500);
						}
					}
				}
			}
		}
}
