package megascripts.aioherblore.node;

import megascripts.aioherblore.Variable;
import megascripts.api.Items;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class OVERLOADS extends Node {

	public static WidgetChild MakeAll = Widgets.get(905, 14);

	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_HERBLORE && Variable.MODE_OVERLOAD;
	}

	@Override
	public void execute() {
		if (Items.contains(Variable.EXTREMEPOTIONS[0])
				&& Items.contains(Variable.EXTREMEPOTIONS[1])
				&& Items.contains(Variable.EXTREMEPOTIONS[2])
				&& Items.contains(Variable.EXTREMEPOTIONS[3])
				&& Items.contains(Variable.EXTREMEPOTIONS[4])
				&& Items.contains(Variable.TORSOL)) {
			if (Bank.isOpen()) {
				Bank.close();
			} else {
				if (MakeAll.validate()) {
					MakeAll.click(true);
					Task.sleep(5000, 7000);
				} else {
					Items.Interact(Variable.TORSOL, "Use");
					Items.Interact(Variable.EXTREMEPOTIONS[4], "Use");
					Task.sleep(1000, 1200);
				}
			}
		} else {
			if (Bank.isOpen()) {
				Bank.depositInventory();
				Bank.withdraw(Variable.EXTREMEPOTIONS[0], 4);
				Bank.withdraw(Variable.EXTREMEPOTIONS[1], 4);
				Bank.withdraw(Variable.EXTREMEPOTIONS[2], 4);
				Bank.withdraw(Variable.EXTREMEPOTIONS[3], 4);	
				Bank.withdraw(Variable.EXTREMEPOTIONS[4], 4);
				Bank.withdraw(Variable.TORSOL, 4);
				Variable.BankTime = Variable.BankTime + 4;
				if (Bank.isOpen()) {
					Bank.close();
				}
			} else {
				Bank.open();
			}
		}
	}

}
