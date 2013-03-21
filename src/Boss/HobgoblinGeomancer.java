package megascripts.dungoneering.boss;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class HobgoblinGeomancer {
	
	public static int BookBarrage;
	
	public static void Kill_Hobgoblin_Geomancer(){
		for (NPC Boss : NPCs.getLoaded()) {
			if (Boss != null) {
				if (Boss.getName().contains("Hobgoblin Geomancer")) {
					if (Calculations.distance(Players.getLocal().getLocation(),
							Boss.getLocation()) <= 2) {
						if (!Boss.isInCombat()) {
							Boss.interact("Attack");

						} else if (Boss.getAnimation() == BookBarrage) {
							Walking.walk(new Tile(Boss.getLocation().getX(),
									Boss.getLocation().getY() + 5, 0));
							Task.sleep(1500);
						}

					} else {
						if (Calculations.distance(Players.getLocal()
								.getLocation(), Boss.getLocation()) >= 3
								&& Boss.getAnimation() != BookBarrage) {
							Walking.walk(Boss);
							Task.sleep(3000);
							Camera.turnTo(Boss);
						}

					}
				}
			}

		}
	}
	
}
