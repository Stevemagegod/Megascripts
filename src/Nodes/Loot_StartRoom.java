package megascripts.dungoneering.node;

import java.awt.Color;
import java.awt.Point;

import megascripts.dungoneering.*;
import megascripts.graphic.LogHandler;
import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.api.Items;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Loot_StartRoom extends Node {

	
	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_DUNGEON && Actions.InDungeon() && Actions.AtStartRoom() && (Actions.ThereFish() || Actions.ThereKey());
	}
	@Override
	public void execute() {
		if (Variable.DungeonStarted && Inventory.contains(Variable.DUNGEON_RING)) {
			Items.Interact(Variable.DUNGEON_RING, "Wear");
			Task.sleep(300, 700);
			Variable.DungeonStarted = false;
		}
			if (Actions.ThereFish()) {
				lootTable(Variable.FISH_ID);
			} else if (Actions.ThereKey()) {
				loot(Variable.KeyGround, true);
			}
		
	}

	public static void lootTable(int [] FishesID) {
		GroundItem items = GroundItems.getNearest(FishesID);
		if (!Inventory.isFull()) {
			if (items != null) {
				if (items.isOnScreen()) {
					Point p = getOnScreenPoint(items);
					if (p != null) {
						Mouse.click(p, false);
					}
					Menu.select("Take", items.getGroundItem().getName());
					Task.sleep(500,800);
				for(int x = 0 ; x < 40 && (Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1); x++, Task.sleep(100, 150));
				} else {
					Camera.turnTo(items.getLocation());
					Walking.walk(items.getLocation());

				}
			}
		}
	}
	private static Point getOnScreenPoint(GroundItem gi) {
		if(gi == null || !gi.validate() || !gi.isOnScreen())return null;
		final int surfaceHeight = 500;
		SceneObject surface = SceneEntities.getAt(gi.getLocation());
		return (surface != null && surface.validate() && surface.getType() == SceneEntities.TYPE_INTERACTIVE) ? gi.getLocation().getPoint(.5d, .5d, -surfaceHeight) : gi.getCentralPoint();
	}
	
	public static void loot(int item[], boolean Keya) {
		GroundItem loot = GroundItems.getNearest(item);
		if (loot != null) {
			if (loot.isOnScreen()) {
				if (loot.getLocation().canReach()) {
					Camera.turnTo(loot);
					String name = loot.getGroundItem().getName();
					loot.interact("Take", name);
					Task.sleep(500, 650);
					if (Players.getLocal().isMoving()) {
						while (Players.getLocal().isMoving()) {
							Task.sleep(10, 30);
						}
					}
				}
			} else {
				Walking.walk(loot.getLocation());
				Camera.turnTo(loot.getLocation());
			}
		}
		if (Keya) {
			Variable.Inevntory_KEY.add(loot.getId());
		}
	}

}
