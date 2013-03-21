package megascripts.api;

import megascripts.dungoneering.Variable;
import megascripts.dungoneering.node.Enter_Dungeon;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Actions {

	public static boolean ThereBoss() {
		int [] End_Gate = {49695, 49696,49698,49697,49699,49700};
		SceneObject Out = SceneEntities.getNearest(End_Gate);
		return Out != null && Calc.Reach(Out);
	}

	public static boolean ThreNPC() {
		for (NPC n : NPCs.getLoaded()) {
			if (n != null && n.getId() != Variable.Smuggler && Calc.Reach(n)) {
				return true;
			}
		}
		return false;
	}

	public static boolean InDungeon(){
		return !Enter_Dungeon.At_Gate() && !Enter_Dungeon.ATSurf();
	}
	public static boolean ThereKey() {
		return Actions.GroundItem_IsValid(Variable.KeyGround) && Calc.Reach(Actions.Set_GroundItem(Variable.KeyGround));
	}
	public static boolean ThereFish() {
		return Actions.GroundItem_IsValid(Variable.FISH_ID) && Calc.Reach(Actions.Set_GroundItem(Variable.FISH_ID)) && Inventory.getCount() !=28;
	}
	public static boolean AtStartRoom() {
		return Actions.NPC_IsValid(Variable.Smuggler) && Calc.Reach(Actions.Set_NPC(Variable.Smuggler));
	}
	public static SceneObject Set_Object(Filter<SceneObject> filter) {
		return SceneEntities.getNearest(filter);
	}
	public static SceneObject Set_Object(int [] ID) {
		return SceneEntities.getNearest(ID);
	}
	public static NPC Set_NPC(int e){
		return NPCs.getNearest(e);
	}
	public static GroundItem Set_GroundItem(int e){
		return GroundItems.getNearest(e);
	}
	public static GroundItem Set_GroundItem(int []e){
		return GroundItems.getNearest(e);
	}
	public static boolean NPC_IsValid(int e){
		NPC d = NPCs.getNearest(e);
		return d!=null && d.validate();
	}
	public static boolean Object_IsValid(int e){
		SceneObject d = SceneEntities.getNearest(e);
		return d!=null && d.validate();
	}
	public static boolean GroundItem_IsValid(int e){
		GroundItem d = GroundItems.getNearest(e);
		return d!=null && d.validate();
	}
	public static boolean GroundItem_IsValid(int []e){
		GroundItem d = GroundItems.getNearest(e);
		return d!=null && d.validate();
	}
	public static void loot(int item) {
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
	}
}
