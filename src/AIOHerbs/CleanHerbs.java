package megascripts.aioherblore.node;

import megascripts.aioherblore.Variable;
import megascripts.api.Items;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;

public class CleanHerbs extends Node {

	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_HERBLORE && Variable.MODE_CLEANHERBS;
	}

	@Override
	public void execute() {
		if (Items.contains(Variable.ITEM_ONE)) {
			if (Bank.isOpen()) {
				Bank.close();
			} else {
				for (Item it : Inventory.getItems()) {
					if (it != null && it.getId() == Variable.ITEM_ONE) {
						it.getWidgetChild().interact("Clean");
					}
				}
			}
		} else {
			if (Bank.isOpen()) {
				Bank.depositInventory();
				Bank.withdraw(Variable.ITEM_ONE, 28);
				Variable.BankTime = Variable.BankTime + 28;
				Bank.close();
			} else {
				Bank.open();
			}
		}
	}

}
