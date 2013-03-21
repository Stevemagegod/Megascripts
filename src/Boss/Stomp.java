package megascripts.dungoneering.boss;

import java.awt.Color;

import megascripts.graphic.LogHandler;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Stomp {

	public static void Kill(){
		if(ThereEmpty()){
			NPC Boss = NPCs.getNearest("Stomp");
			if(Boss !=null){
				if(Boss.isOnScreen()){
				if(Players.getLocal().getInteracting() == null){
					Boss.interact("Attack");
					Task.sleep(1000);
				}
				}else{
					Walking.walk(Boss);
					Task.sleep(1000);
				}
			}
		}else{

			if(ThereBlue()){
				int BlueKey = 15750;
				int BlueObject [] = {49279,49278};
				Loot(BlueKey,BlueObject);
			}else if(ThereRed()){
				int RedKey = 15752;
				int RedObject [] = {49274,49275};
				Loot(RedKey,RedObject);
			}else if(ThereGreen()){
				int GreenKey = 15751;
				int GreenObject [] = {49276,49277};
				Loot(GreenKey,GreenObject);
			}
		}
	}

	private static void Loot(int Key,int Object[]) {
		SceneObject[] Stones = SceneEntities.getLoaded(Object);
		System.out.println(Stones.length);
		if(Inventory.getCount(Key) >= Stones.length ){
			SceneObject Stone = SceneEntities.getNearest(Object);
			if(Stone !=null){
				
				if(Stone.isOnScreen()){
					
					Mouse.click(Stone.getCentralPoint(), true);
					Task.sleep(1000);
				}else{
					Walking.walk(Stone);
					Task.sleep(1000);
				}
			}
		}else{
			GroundItem Keys = GroundItems.getNearest(Key);
			if(Keys !=null){
				if(Keys.getLocation().canReach()){
				if(Keys.isOnScreen()){
						Mouse.click(Keys.getCentralPoint(),true);
						Task.sleep(1000);
				}else{
					Walking.walk(Keys);
					Task.sleep(1000);
				}
				}
			}
		}
	}

	public static boolean ThereBlue(){
		for (SceneObject o : SceneEntities.getLoaded()) {
			if (o != null) {
				if (o.getId() == 49278 || o.getId() == 49279) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean ThereGreen(){
		for (SceneObject o : SceneEntities.getLoaded()) {
			if (o != null) {
				if (o.getId() == 49276 || o.getId() == 49277) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean ThereRed(){
		for (SceneObject o : SceneEntities.getLoaded()) {
			if (o != null) {
				if (o.getId() == 49275 || o.getId() == 49274) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean ThereEmpty() {
		for (SceneObject o : SceneEntities.getLoaded()) {
			if (o != null) {
				if (o.getId() == 49271) {
					return true;
				}
			}
		}
		return false;
	}
}
