package megascripts.aiofletcher.node;

import megascripts.aiofletcher.Variable;
import megascripts.api.Items;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class StringBows extends Node {

	public static WidgetChild BOWSLOT = Widgets.get(905, 14);
	private int before;
	@Override
	public boolean activate() {
		return Variable.MODE_BOWSTRING && megascripts.Variable.MEGA_FLETCHER;
	}

	@Override
	public void execute() {
		if(Items.contains(Variable.ITEM_ONE) && Items.contains(Variable.ITEM_TWO)){
			if (Bank.isOpen()) {
				Bank.close();
			} else {
				if (BOWSLOT.validate()) {
					BOWSLOT.click(true);
					WaitFor();
				} else {
					Items.Interact(Variable.ITEM_ONE, "Use");
					Items.Interact(Variable.ITEM_TWO, "Use");
					Task.sleep(1000, 1200);
				}
			}
		}else{
			if(Bank.isOpen()){
				Bank.depositInventory();
				Bank.withdraw(Variable.ITEM_ONE, 14);
				Bank.withdraw(Variable.ITEM_TWO, 14);
				Bank.close();
				if(Bank.isOpen()){
					Bank.close();
				}
				Variable.BankTime = Variable.BankTime + 28;
			}else{
				Bank.open();
			}
		}
	}

	private void WaitFor() { 
		Task.sleep(18500,19500);
	}

}
