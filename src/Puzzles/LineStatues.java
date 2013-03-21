package megascripts.dungoneering.puzzle;

import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.api.Items;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * 
 * @author Magorium
 *
 */
public class LineStatues {


	public static int Range_Enmey = 11030;
	public static int Magic_Enmey = 11033;
	
	public static int Free_Army = 11012;
	
	public static int BrokenWall = 49647;
	public static int Weipon = 0;

	public static int Melee_Weapon = 17416;
	public static int Range_Weapon = 17418;
	public static int Magic_Weapon = 17420;
	
	public static int MeleeAmount = 0;
	public static int RangeAmount = 0;
	public static int MagicAmount = 0;
	
	public static int widgetnumber = 0;
	public static int Stone = 17415;
	

	public static boolean UnCompleted(){
		NPC fe = NPCs.getNearest(Free_Army);
		if(fe == null){
			return false;
		}
		return Calc.Reach(fe);
	}
	public static boolean isCompleted(){
		return !UnCompleted();
	}
	public static void solve(){
		if(There(Range_Enmey)){
			 widgetnumber = 11;
			 Weipon = Melee_Weapon;
			 SceneObject [] s = SceneEntities.getLoaded(Range_Enmey);
			 MeleeAmount = s.length;
		}else if(There(Magic_Enmey)){
			 widgetnumber = 13;
			 Weipon = Range_Weapon;
			 SceneObject [] s = SceneEntities.getLoaded(Range_Enmey);
			 MeleeAmount = s.length;
		}

		if(Items.contains(Weipon)){
			NPC n = NPCs.getNearest(Free_Army);
			if (n != null && n.validate()) {
				if (n.isOnScreen()) {
					n.interact("Arm");
					Task.sleep(1000, 1500);	
				    for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
				}else{
					Walking.walk(n);
					Camera.turnTo(n);
					for(int x = 0; x < 20 && Players.getLocal().isMoving() || Players.getLocal().getAnimation() !=-1;x++,Task.sleep(100,150));
				}
			}
		}else{
			if(Items.contains(Stone)){
				Carft(widgetnumber);
			}else{
				SceneObject n = SceneEntities.getNearest(BrokenWall);
				if(n !=null && n.validate()){
					if(n.isOnScreen()){
						if (Players.getLocal().getAnimation() == -1) {
							n.interact("Mine");
							Task.sleep(2200, 3200);
						}
					}else{
						Walking.walk(n);
						Camera.turnTo(n);
					}
				}
			}
		}
	}
	private static void Carft(int x) {
		WidgetChild INTERFACE = Widgets.get(1188, x);
			if(INTERFACE.validate()){
				INTERFACE.click(true);
				Task.sleep(400,800);
			} else {
				Items.Interact(Stone, "Carve");
				Task.sleep(400, 900);
			}
	}

private static boolean There(int[] melee) {
	NPC m = NPCs.getNearest(melee);
	if(m == null){
		return false;
	}
	return Calc.Reach(m);
}
private static boolean There(int melee) {
	NPC m = NPCs.getNearest(melee);
	if(m == null){
		return false;
	}
	return Calc.Reach(m);
}
}
