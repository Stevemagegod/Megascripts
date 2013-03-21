package megascripts.dungoneering.puzzle;

import megascripts.SetupTest;
import megascripts.api.Calc;
import megascripts.dungoneering.Variable;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Monolith {


	public static boolean Solved = false;
	
  public static boolean UnCompleted(){
	  if(Solved){
		  return false;
	  }
	  NPC m = NPCs.getNearest("Monolith");
	  if(m == null){
		  return false;
	  }
	  return Calc.Reach(m);
  }

	public static Filter<NPC> mattacker() {
		return new Filter<NPC>() {
			@Override
			public boolean accept(NPC shade) {
				NPC m = NPCs.getNearest("Monolith");
				return shade.getInteracting() !=null && shade.getLevel() > 0 && Calc.Reach(shade) && !shade.getName().equals("Monolith")
						&& shade.getInteracting().equals(m);
			}
		};
	}
	public static Filter<NPC> shades() {
		return new Filter<NPC>() {
			@Override
			public boolean accept(NPC shade) {
				return shade.getInteracting() !=null && shade.getLevel() > 0 && Calc.Reach(shade) && !shade.getName().equals("Monolith");
			}
		};
	}

	public static void solve() {
		NPC m = NPCs.getNearest("Monolith");
		if (m != null && Calc.Reach(m)) {
			if (m.getPassiveAnimation()!= 13072) {
               if(m.isOnScreen()){
            	   m.interact("Activate");
            	   Task.sleep(300,600);
               }else{
            	   if(!Players.getLocal().isMoving()){
            	   Walking.walk(m);
                   Camera.turnTo(m);
            	   }
               }
			}else{
				NPC shade = NPCs.getNearest(shades());
				NPC[] shade_group = NPCs.getLoaded(shades());
				NPC matt = NPCs.getNearest(mattacker());
				if(shade !=null){
					if(isUnderAttack(shade_group,m)){
							if (matt.isOnScreen()) {
								matt.interact("Attack");
								Task.sleep(400, 800);
							} else {
								Walking.walk(matt);
								Camera.turnTo(matt);
							}
					}else{
						if (Players.getLocal().getInteracting() == null) {
							if (shade.isOnScreen()) {
								shade.interact("Attack");
								Task.sleep(400, 800);
							} else {
								Walking.walk(shade);
								Camera.turnTo(shade);
							}
						}
					}
				}else if(shade == null){
					if(!Players.getLocal().isMoving() && Calculations.distanceTo(m) > 2){
							Walking.walk(m);
					}
				}
			}
		}
		if(SetupTest.Message.toLowerCase().contains("in the room are now unlocked")){
			Solved = true;
		}
	}

	private static boolean isUnderAttack(NPC[] s, NPC m) {
		for(NPC z :s){
			if(z !=null && z.getInteracting() !=null && z.getInteracting().equals(m)){
				return true;
			}
		}
		return false;
	}
}
