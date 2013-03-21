package megascripts.dungoneering.boss;

import megascripts.api.Calc;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import megascripts.api.Prayer;
import megascripts.api.Prayer.Ancient;
import megascripts.api.Prayer.Modern;
import megascripts.dungoneering.Variable;

public class NightGazer {
	public static void TurOffRet() {

		if (!(Tabs.getCurrent() == Tabs.ATTACK)) {
			Tabs.ATTACK.open();
			Task.sleep(1500);
		}
		if (Widgets.get(884).getChild(12).getTextureId() != 655) {
			Task.sleep(400);
			WidgetChild OffRetalite = Widgets.get(884).getChild(11);
			OffRetalite.click(true);
			Task.sleep(400);

		}
		Tabs.INVENTORY.open();
		Task.sleep(1000);
	}
	public static void Kill(){
		if(Settings.get(172) == 0){
			TurOffRet();
	}
		NPC Boss = NPCs.getNearest("Night-gazer Khighorahk");
		if(Boss.getAnimation() == 13429 || Boss.getAnimation() == 13427){
			int y = Random.nextInt(0, 3);
			Walking.walk(Variable.CurrentRoom.getBoundingTiles()[y]);
		}else{
		if(Boss !=null){
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
			}
			if(ThereUnlit()){
				Lit();
			}else{
				if(Players.getLocal().getInteracting() == null){
					if(Boss.isOnScreen()){
						Boss.interact("Attack");
						Task.sleep(1500);
					}else{
						Walking.walk(Boss);
						Task.sleep(1500);
					}
				}
			}
		}
		}
	}

	private static void Lit() {
		SceneObject Unlit = SceneEntities.getNearest(49265);
		if(Unlit !=null){
				if(Unlit.isOnScreen()){
					Mouse.click(Unlit.getCentralPoint(), true);
					Task.sleep(1500);
				}else{
					Walking.walk(Unlit);
					Camera.turnTo(Unlit);
					Task.sleep(1500);
					}
		}
	}

	private static boolean ThereUnlit() {
		SceneObject Unlit = SceneEntities.getNearest(49265);
		if(Unlit !=null){
			if(Calc.Reach(Unlit)){
				return true;
			}
		}
		return false;
	}
}
