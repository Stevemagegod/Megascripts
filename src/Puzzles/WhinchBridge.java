package megascripts.dungoneering.puzzle;

import java.awt.Color;

import megascripts.api.Calc;
import megascripts.api.Items;
import megascripts.graphic.LogHandler;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class WhinchBridge {

	public static int Meatcorn_Box = 54568;
	public static int Crate = 54645;
	
	public static int MeatCorn = 19665;
	public static int Broken_Hook = 19663;
	
	public static int Spinning_Wheel = 49934;
	public static int Anvil = 54257;
	
	public static int Rope = 19667;
	public static int Fixed_Hook = 19664;
	
	public static int HookRope = 19668;
	public static int Edge = 39912;
	
	public static boolean UnCompleted(){
		SceneObject Crates = SceneEntities.getNearest(Crate);
		if(Crates == null){
			return false;
		}
		return Calc.Reach(Crates);
	}
	public static boolean IsCompleted(){
		return !UnCompleted();
	}
	public static void solve(){
		if(Items.contains(HookRope)){
			Interact(Edge,"Grapple",false);
			Task.sleep(900,1200);
		}else if (Items.contains(Fixed_Hook) && Items.contains(Rope)) {
			Items.Interact(Rope, "Use");
			Items.Interact(Fixed_Hook, "Use");
			Task.sleep(1000,1500);
		} else {
			if (!Items.contains(Rope)) {
				if (Items.contains(MeatCorn)) {
					InteractSpin(Spinning_Wheel, "Use");
				} else if (!Items.contains(MeatCorn)) {
					Interact(Meatcorn_Box, "Take-from");
				}
			} else {
				if (Items.contains(Broken_Hook)) {
					InteractAnvil(Anvil, "Use");
				} else if (!Items.contains(Broken_Hook)) {
					Interact(Crate, "Take-from");
				}
			}
		}
	}
	public static void Interact(int id,String Action,boolean r){
		for(SceneObject n : SceneEntities.getLoaded(id)){
		if (n != null && n.validate()) {
			if (n.isOnScreen()) {
				Camera.turnTo(n);
				n.interact(Action);
				Task.sleep(1000,1500);
				for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
			} else {
				Walking.walk(n);
				Camera.turnTo(n);
				for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
			}
		}
		}
	}
	public static void InteractSpin(int id,String Action){
		for(SceneObject n : SceneEntities.getLoaded(id)){
		if (n != null && n.validate() && Calc.Reach(n)) {
			if (n.isOnScreen()) {
				Camera.turnTo(n);
				Items.Interact(MeatCorn, "Use");
				n.interact(Action);
				Task.sleep(1000,1500);
				for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
			} else {
				Walking.walk(n);
				Camera.turnTo(n);
				for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
			}
		}
		}
	}
	public static void Interact(int id,String Action){
		for(SceneObject n : SceneEntities.getLoaded(id)){
		if (n != null && n.validate() && Calc.Reach(n)) {
			if (n.isOnScreen()) {
				Camera.turnTo(n);
				n.interact(Action);
				Task.sleep(500,900);
				for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
			} else {
				Walking.walk(n);
				Camera.turnTo(n);
				for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
			}
		}
		}
	}
	public static void InteractAnvil(int id,String Action){
		for(SceneObject n : SceneEntities.getLoaded(id)){
		if (n != null && n.validate() && Calc.Reach(n)) {
			if (n.isOnScreen()) {
				Camera.turnTo(n);
				Items.Interact(Broken_Hook, "Use");
				n.interact(Action);
				Task.sleep(500,900);
				for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
			} else {
				Walking.walk(n);
				Camera.turnTo(n);
				for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
			}
		}
		}
	}
}
