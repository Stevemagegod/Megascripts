package megascripts.dungoneering.boss;

import megascripts.dungoneering.Variable;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class LuminescentIcefiend {
	 public static void Kill_LuminescentIcefiend() {
		 int SpicalRun = 13338;
			for (NPC Boss : NPCs.getLoaded()) {
				if (Boss != null) {
					if (Boss.getName().contains("Luminescent")) {
						if (Boss.isOnScreen()) {
							if (Boss.getAnimation() == SpicalRun) {
								int CORNR [] = {0,1,2,3};
								for(int c: CORNR){
								if (Variable.CurrentRoom.getBoundingTiles()[c] != null) {
									if (Boss.getAnimation() == SpicalRun) {
										Walking.walk(Variable.CurrentRoom.getBoundingTiles()[c]);
								 	    for(int x =0; x < 30 && Calculations.distanceTo(Variable.CurrentRoom.getBoundingTiles()[c]) > 5; x++, Task.sleep(100,150));
									}
								}
							}
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
