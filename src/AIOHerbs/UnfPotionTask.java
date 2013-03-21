package megascripts.aioherblore.node;

import megascripts.aioherblore.Variable;
import megascripts.api.Items;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class UnfPotionTask extends Node {

	public static WidgetChild MakeAll = Widgets.get(905, 14);

	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_HERBLORE && Variable.MODE_UNFPOTION;
	}

	@Override
	public void execute() {
		if (Items.contains(Variable.ITEM_ONE)
				&& Items.contains(Variable.ITEM_TWO)) {
		
			if (Bank.isOpen()) {
				Bank.close();
			} else {
				if (MakeAll.validate()) {
					MakeAll.click(true);
					Task.sleep(8500, 10000);
				} else {
					Items.Interact(Variable.ITEM_ONE, "Use");
					Items.Interact(Variable.ITEM_TWO, "Use");
					Task.sleep(1000, 1200);
				}
			}
		} else {
			if (Bank.isOpen()) {
				Bank.depositInventory();
				Bank.withdraw(Variable.ITEM_ONE, 14);
				Bank.withdraw(Variable.ITEM_TWO, 14);
				Variable.BankTime = Variable.BankTime + 14;
				if (Bank.isOpen()) {
					Bank.close();
				}
			} else {
				Bank.open();
			}
		}
	}

}
