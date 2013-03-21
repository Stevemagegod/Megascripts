package megascripts.dungoneering.boss;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import megascripts.api.Prayer;
import megascripts.api.Prayer.Ancient;
import megascripts.api.Prayer.Modern;
import megascripts.dungoneering.Variable;


public class LexicusRunewright {

	public static void Kill(){
		NPC Boss = NPCs.getNearest("Lexicus Runewright");
		if(Boss !=null){
			if(Boss.isOnScreen()){
				if (Skills.getLevel(Skills.MAGIC) >= 37) {
					if (Prayer.isModernSetActive()) {
						if (Prayer.getRemainingPoints() > 0) {
							if (!Prayer.isActive(Modern.PROTECT_FROM_MAGIC)) {
								Tabs.PRAYER.open();
								Prayer.setActivated(Modern.PROTECT_FROM_MAGIC,
										true);
								Task.sleep(90, 110);
								Tabs.INVENTORY.open();
							}
						}
					}
				}
				if(Boss.getMessage().contains("Book")){
					int y = Random.nextInt(0, 3);
					if(!(Players.getLocal().isMoving())){
					Walking.walk(Variable.CurrentRoom.getBoundingTiles()[y]);
					Task.sleep(1000);
					}
				}
				if(!(Players.getLocal().isMoving())){
					if(Players.getLocal().getInteracting() != Boss){
				
				Boss.interact("Attack");
				Task.sleep(2000);
					}
				}
			}else{
				Walking.walk(Boss);
				Camera.turnTo(Boss);
				Task.sleep(1000);
			}
		}
	}
}
