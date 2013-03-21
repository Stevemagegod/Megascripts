package megascripts.aiofletcher.node;

import megascripts.aiofletcher.*;
import megascripts.api.Items;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class LogsToShaft extends Node {

	public static WidgetChild KNIFE_WIDGET = Widgets.get(1179, 12);
	public static WidgetChild SHAFT_WIDGET = Widgets.get(905, 14);

	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_FLETCHER && Variable.MODE_LOGTOSHAFT;
	}

	@Override
	public void execute() {
		if (Items.contains(Variable.ITEM_ONE)) {
			if (Bank.isOpen()) {
				Bank.close();
			} else {
				if (KNIFE_WIDGET.validate()) {
					KNIFE_WIDGET.click(true);
					Task.sleep(1000, 1200);
				} else if (SHAFT_WIDGET.validate()) {
					SHAFT_WIDGET.click(true);
					Task.sleep(1000, 1200);
					WaitFor();
				} else {
					Items.Interact(Variable.ITEM_ONE, "Craft");
					Task.sleep(1000, 1200);
				}
			}
		} else {
			if (Bank.isOpen()) {
				Bank.depositInventory();
				Bank.withdraw(Variable.ITEM_ONE, 28);
				Bank.close();
					if(Bank.isOpen()){
						Bank.close();
					}
					Variable.BankTime = Variable.BankTime + 28;
			} else {
				Bank.open();
			}
		}
	}

	private void WaitFor() {
		for (int x = 0; x < 2000 && Players.getLocal().getAnimation() != -1; x++, Task
				.sleep(100, 150))
			;
	}

}
