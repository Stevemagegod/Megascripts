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

public class LogsToBows extends Node {

	public static WidgetChild KNIFE_WIDGET = Widgets.get(1179, 12);
	public static WidgetChild LONGBOW_WIDGET_NORMAL = Widgets.get(905, 16);
	public static WidgetChild SHORTBOW_WIDGET_NORMAL = Widgets.get(905, 15);
	public static WidgetChild LONGBOW_WIDGET = Widgets.get(905, 15);
	public static WidgetChild SHORTBOW_WIDGET = Widgets.get(905, 14);

	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_FLETCHER && Variable.MODE_LOGTOBOW;
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
				} else if (LONGBOW_WIDGET.validate()) {
					if (Variable.OPTION_LONGBOW) {
						if (Variable.ITEM_ONE == Variable.NORMAL_LOG_ID) {
							LONGBOW_WIDGET_NORMAL.click(true);
							Task.sleep(1000, 1200);
						} else {
							LONGBOW_WIDGET.click(true);
							Task.sleep(1000, 1200);
						}
					} else {
						if (Variable.ITEM_ONE == Variable.NORMAL_LOG_ID) {
							SHORTBOW_WIDGET_NORMAL.click(true);
							Task.sleep(1000, 1200);
						} else {
							SHORTBOW_WIDGET.click(true);
							Task.sleep(1000, 1200);
						}
					}
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
				if (Bank.isOpen()) {
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
