package megascripts.aiofishing.node;

import java.util.Arrays;

import megascripts.aiofishing.Variable;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;

public class Fishing_PowerFishing extends Node{

	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_FISHER && Variable.POWERFISHING;
	}

	@Override
	public void execute() {
	   if(Inventory.isFull()){
		   dropAllExcept(Fishing_BankMode.FISHINGTOOL);
	   }else{
			FISH(Variable.FISH_IDS,Variable.Action);
	   }
	}

	public void dropAllExcept(final int... ids) {
        Arrays.sort(ids);
        for(final Item item : Inventory.getItems()) {
                if(Arrays.binarySearch(ids, item.getId()) < 0 && item.getWidgetChild().interact("Drop"))  {
                        Task.sleep(100, 200);
                }
        }
}
	private void FISH(int[] IDs, String Action) {
		NPC FISHSPOT = NPCs.getNearest(IDs);
		if (FISHSPOT != null && FISHSPOT.validate()) {
			if (FISHSPOT.isOnScreen()) {
				if (Players.getLocal().getAnimation() == -1) {
					FISHSPOT.interact(Action);
					Task.sleep(1000, 2000);
				}else{
					Task.sleep(50,100);
				}
			} else {
				if (Calculations.distanceTo(FISHSPOT) > 7) {
					Walking.walk(FISHSPOT);
				} else {
					Camera.turnTo(FISHSPOT);
				}
			}
		}
	}
}
