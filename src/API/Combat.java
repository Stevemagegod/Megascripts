package megascripts.api;



import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * 
 * @author Magorium
 *
 */
public class Combat {

	public static void TurOffRet() {
		if (Settings.get(172) == 0) {
			if (!(Tabs.getCurrent() == Tabs.ATTACK)) {
				Tabs.ATTACK.open();
			}else{
				WidgetChild OffRetalite = Widgets.get(464).getChild(5);
				OffRetalite.click(true);
				Task.sleep(400, 600);
			}
			Tabs.INVENTORY.open();
		}
	}
	/*
	 * Turn On
	 */
	public static void TurOnRet() {

		if (Settings.get(172) != 0) {
			if (Tabs.getCurrent() != Tabs.ATTACK) {
				Tabs.ATTACK.open();
			}
			WidgetChild OffRetalite = Widgets.get(464,5);
			if (Tabs.ATTACK.isOpen()) {
				OffRetalite.click(true);
				Task.sleep(400,800);
			}

				Tabs.INVENTORY.open();
			
		}
	}

}
