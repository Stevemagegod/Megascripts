package megascripts.dungoneering.puzzle;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.api.Items;
import megascripts.dungoneering.Variable;

/**
 * 
 * @author Magorium
 *
 */
public class ThreeWeapionStatus {

	public static int Weapon ;
	public static int Stone = 17415;
	public static int BrokenWall = 49647;
		
	public static int Melee_Weapon = 17416;
	public static int Range_Weapon = 17418;
	public static int Magic_Weapon = 17420;
	
	public static int UnCompletd_Range[] = {11039};
	public static int UnCompletd_Melee[] = {11036};
	public static int UnCompletd_Mage[] = {000};
	public static int Status[] = {11039,11036};
	public static int widgetnumber;
	
	public static boolean Mage = false;
	public static boolean Melee = false;
	public static boolean Range = false;
	
	public static boolean IsInComplete(){
		
		NPC State = NPCs.getNearest(Status);
		return State !=null && State.validate() && Calc.Reach(State);
	}
	public static boolean IsComplete(){
		return !IsInComplete();
	}
	public static void solve(){
		if(Items.contains(Variable.Chisel)){
			if (There(UnCompletd_Melee)) {
				Mage = false;
				Melee = true;
				Range = false;
				Weapon= 17416;
				widgetnumber = 11;
			} else if (There(UnCompletd_Range)) {
				Mage = false;
				Melee = false;
				Range = true;
				Weapon= Range_Weapon;
				widgetnumber = 13;
			} else if (There(UnCompletd_Mage)) {
				Mage = true;
				Melee = false;
				Range = false;
				Weapon= Magic_Weapon;
				widgetnumber = 14;
			}
			if(Items.contains(Weapon)){
				NPC n = NPCs.getNearest(Status);
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
			}else if(Items.contains(Stone)){
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
		}else{
			Actions.loot(Variable.Chisel);
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
}
