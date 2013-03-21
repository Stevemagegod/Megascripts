package megascripts.dungoneering.puzzle;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.api.Items;
import megascripts.dungoneering.Variable;
import megascripts.dungoneering.node.Loot_StartRoom;
import megascripts.dungoneering.node.Room_Job;

public class Magicalconstruct {


	public static int ROBOT = 11003;
	public static int Rock_Object = 49543;
	public static int Stone = 17364;
	public static int UnFinshed_leg = 17366;
	public static int UnFinshed_Arm = 17365;
    public static int UnFinshed_Head = 17367;
 
	public static int leg = 17370;
	public static int Arm = 17368;
    public static int Head = 17372;
    
    public static boolean arm_t = false;
    public static boolean leg_t= false;
    public static boolean head_t= false;
	
	public static int[] Ready_Parts = { 17368, 17370, 17372 };
	public static int[] Unf_Part = { 17367, 17366, 17365 };
    
	public static boolean UnCompleted() {
		NPC Robet = NPCs.getNearest("Dormant construct","Damaged Construct");
		if (Robet == null ) {
			return false;
		}
		return Calc.Reach(Robet) ;
	}
	public static boolean Completed(){
		return !UnCompleted();
	}
	public static void solve(){
		NPC a = NPCs.getNearest("Dormant construct");
		if(a !=null && a.validate() && Calc.Reach(a)){
			a.interact("Charge");
			Task.sleep(2000,3000);
			arm_t = false;
			leg_t = false;
			head_t = false;
		}else{
		if(Items.contains(Variable.Chisel)){
			if(head_t == false  && leg_t == false && arm_t == false){
				getBrokenItem();
			}
			if(arm_t){
				if (Items.contains(Arm)) {
		           Repair(11);
				}else if (Items.contains(UnFinshed_Arm)) {
					Items.Interact(UnFinshed_Arm, "Imbue");
				} else if (!Items.contains(UnFinshed_Arm)) {
					Carft(11);
				}
			}else if(leg_t){
				if (Items.contains(leg)) {
					Repair(13);
				}else if (Items.contains(UnFinshed_leg)) {
					Items.Interact(UnFinshed_leg, "Imbue");
				} else if (!Items.contains(UnFinshed_leg)) {
					Carft(13);
				}
			}else if(head_t){
				if (Items.contains(Head)) {
					Repair(14);
				} else if (Items.contains(UnFinshed_Head)) {
					Items.Interact(UnFinshed_Head, "Imbue");
				} else if (!Items.contains(UnFinshed_Head)) {
					Carft(14);
				}
			}
			
		}else{
			Actions.loot(Variable.Chisel);
		}
		NPC d = NPCs.getNearest("Magic construct");
		if(d !=null){
		  for(int p = 0 ; p < 100 && d.isMoving(); p++,Task.sleep(100,150));
		}
		}
	}
		private static void getBrokenItem() {
			NPC Robet = getNPC();
			if(Robet !=null){
				if(Robet.isOnScreen()){
					Robet.interact("Examine");
					Task.sleep(1000,2000);
				}else{
					Camera.turnTo(Robet);
				}
			}
	}
		private static NPC getNPC() {
			return NPCs.getNearest("Damaged Construct");
		}

	private static void Repair(int i) {
		WidgetChild INTERFACE = Widgets.get(1188, i);
		if(INTERFACE.validate()){
			INTERFACE.click(true);
			Task.sleep(400,800);
		}else{
			NPC Robet =getNPC();
			if(Robet !=null){
				if(Robet.isOnScreen()){
					Robet.interact("Repair");
					Task.sleep(2500,3000);
				}else{
					Camera.turnTo(Robet);
				}
			}
		}
	}
	private static void Carft(int x) {
		WidgetChild INTERFACE = Widgets.get(1188, x);
		if(!Items.contains(Stone)){
			SceneObject Hole = SceneEntities.getNearest(Rock_Object);
			if(Hole !=null){
				if(Hole.isOnScreen()){
					Hole.interact("Take");
					Task.sleep(400,800);
				}else{
					Camera.turnTo(Hole);
				}
			}
		}else{
			if(INTERFACE.validate()){
				INTERFACE.click(true);
				Task.sleep(400,800);
			} else {
				Items.Interact(Stone, "Carve");
				Task.sleep(400, 900);
			}
		}
	}
}
