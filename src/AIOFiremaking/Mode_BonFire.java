package megascripts.aiofiremaking.node;

import megascripts.aiofiremaking.Variable;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Mode_BonFire extends Node{

	public static int Fire[] = {70764,70765,70758,70761,70755,70757};
	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_FIREMAKING;
	}

	@Override
	public void execute() {
		if(Inventory.getCount(Variable.LOG_ID) == 0){
			if(AtBank()){
				Bank();
			}else{
				Walking.newTilePath(Variable.PathToBank).traverse();
			}
		}else{
			if(AtFire()){
				Fire();
			}else{
				Walking.newTilePath(Variable.PathToFM).traverse();
			}
		}
	}

	private void Fire() {
		for (NPC n : NPCs.getLoaded()) {
			if (n != null) {
				if (Variable.FMAREA.contains(n)) {
					if (n.getId() == 15451) {
						n.click(true);
						Task.sleep(1000, 2000);
					}
				}
			}
		}
		if (ThereFire()) {
			SceneObject Fires = SceneEntities.getNearest(Fire);
			if (Fires != null) {
				if (Fires.isOnScreen()) {
					if (WaitFor()) {
								Fires.interact("Add-logs");
								Task.sleep(3500, 4000);
					
					}
				} else {
					if (Calculations.distanceTo(Fires) > 7) {
						Inventory.getItem(Variable.LOG_ID).getWidgetChild()
								.interact("Light");
						Task.sleep(600, 700);
					} else {
						Camera.turnTo(Fires);
					}
				}
			}
		} else {
			Inventory.getItem(Variable.LOG_ID).getWidgetChild()
					.interact("Light");
			Task.sleep(3000, 4000);
		}
	}
	

	private boolean WaitFor() {
		if(Inventory.isFull()){
			return true;
		}
		int Count = 0;
		for (int i = 0; i < 13; i++) {
			if(Players.getLocal().getAnimation() == -1){
				Count++;
				Task.sleep(250,300);
			}
		}
		return Count == 13;
	}

	private boolean ThereFire() {
		for (SceneObject Fire : SceneEntities.getLoaded()) {
			if (Fire != null) {
				if (Variable.FMAREA.contains(Fire)) {
					if ((Fire.getId() == 70764) || (Fire.getId() == 70764)
							|| (Fire.getId() == 70765)
							|| (Fire.getId() == 70758)
							|| (Fire.getId() == 70761)
							|| (Fire.getId() == 70755)
							|| (Fire.getId() == 70757)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	private void Bank() {
		if(Bank.isOpen()){
			Bank.depositInventory();
			Bank.withdraw(Variable.LOG_ID, 28);
			Bank.close();
		}else{
			Bank.open();
		}
	}



	private boolean AtFire() {
		return Variable.FMAREA.contains(Players.getLocal());
	}

	private boolean AtBank() {
		return Variable.BANKAREA.contains(Players.getLocal());
	}

}
