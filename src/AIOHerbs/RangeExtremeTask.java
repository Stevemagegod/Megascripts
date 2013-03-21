package megascripts.aioherblore.node;



import megascripts.aioherblore.Variable;
import megascripts.api.Items;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class RangeExtremeTask extends Node{

	public static WidgetChild ONE_BUTTON = Widgets.get(905,14);
	@Override
	public boolean activate() {
		return Variable.MODE_RANGEEXT && megascripts.Variable.MEGA_HERBLORE;
	}

	@Override
	public void execute() {
		if(Items.contains(Variable.SPIKES)){
			if(Items.contains(Variable.RANAGEPOT)){
				if(ONE_BUTTON.validate()){
					ONE_BUTTON.click(true);
					Task.sleep(31000,31500);
				}else{
					Items.Interact(Variable.SPIKES, "Use");
					Items.Interact(Variable.RANAGEPOT, "Use");
					Task.sleep(1000,1200);
				}
			}else{
				if(Bank.isOpen()){
					Bank.deposit(15325, 27);
					Bank.withdraw(Variable.RANAGEPOT, 27);
					Variable.BankTime = Variable.BankTime + 27;
					Bank.close();
				}else{
					Bank.open();
				}	
			}
		}else{
			if(Bank.isOpen()){
				Bank.depositInventory();
				Bank.withdraw(Variable.SPIKES, 100000);
				Bank.close();
			}else{
				Bank.open();
			}
		}
	}

}
