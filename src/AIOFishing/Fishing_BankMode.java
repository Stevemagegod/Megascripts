package megascripts.aiofishing.node;


import megascripts.aiofishing.Variable;

import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;

public class Fishing_BankMode extends Node {
	
	  public static final int FISHINGTOOL [] ={309,314,995,313,307,313,11323};
	  
	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_FISHER && Variable.BANK_MODE;
	}

	@Override
	public void execute() {
		
		if (Inventory.isFull()) {
			if (ATBANK()) {
				if (Variable.KARAMJA_BANK_MODE) {
                   Exchange_FISH();
				} else {
					BANK();
				}
			} else {
				Walking.newTilePath(Variable.PATHTOBANK).traverse();
			}
		} else {
			if (ATFISH()) {
				FISH(Variable.FISH_IDS,Variable.Action);
			} else {
				Walking.newTilePath(Variable.PATHTOFISH).traverse();
			}
		}
	}

	private void Exchange_FISH() {
		NPC EXCHANGE = NPCs.getNearest(11267);
		if (EXCHANGE != null && EXCHANGE.validate()) {
			if (EXCHANGE.isOnScreen()) {
				EXCHANGE.interact("Exchange");
					Task.sleep(1000, 2000);
			} else {
				if (Calculations.distanceTo(EXCHANGE) > 7) {
					Walking.walk(EXCHANGE);
				} else {
					Camera.turnTo(EXCHANGE);
				}
			}
		}
	}

	private void BANK() {
		if (Bank.isOpen()) {
			depositAllExcept(FISHINGTOOL);
			Bank.close();
			if (Bank.isOpen()) {
				Bank.close();
			}
		} else {
			Bank.open();
		}
	}

	private boolean ATBANK() {
		return Variable.BANKAREA.contains(Players.getLocal());
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

	private boolean ATFISH() {
		return Variable.FISHAREA.contains(Players.getLocal());
	}

	private boolean depositAllExcept(final int... ids) {
        boolean deposited = false;
        int var = 0;
        final int count = Inventory.getCount(true), itemCount = Inventory
                        .getCount(true, ids);
        w: while (var < (count - itemCount)) {
                l: for (final Item item : Inventory.getItems()) {
                        if (!Widgets.get(762).validate()) {
                                break w;
                        }
                        for (final int id : ids) {
                                if (item.getId() == id)
                                        continue l;
                        }
                        final int invCount = Inventory.getCount(true, item.getId());
                        if (item.getWidgetChild().validate()) {
                                if (item.getWidgetChild().interact(
                                                invCount > 1 ? "Deposit-All" : "Deposit")) {
                                        if (waitFor(new Condition() {
                                                public boolean validate() {
                                                        return Inventory.getCount(true, item.getId()) < invCount;
                                                }
                                        }, 3000)) {
                                                var += invCount;
                                        }
                                        if (var == (count - itemCount)) {
                                                deposited = true;
                                                break w;
                                        }
                                }
                        }
                }
        }
        return deposited;
}

	private boolean waitFor(final Condition c, final long timeout) {
		boolean isValid = false;
		final long past = System.currentTimeMillis();
		final long total = (past + timeout);
		while (System.currentTimeMillis() < total) {
			if (c.validate()) {
				isValid = true;
				break;
			}
			Task.sleep(50);
		}
		return isValid;
	}
}
