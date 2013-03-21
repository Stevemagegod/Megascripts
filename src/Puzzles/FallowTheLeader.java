package megascripts.dungoneering.puzzle;

import java.util.ArrayList;
import java.util.Collections;



import megascripts.api.Calc;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.powerbot.game.api.wrappers.*;

public class FallowTheLeader {


	
static final int WAVE = 863, NOD = 855, SHAKE = 856, LAUGH = 861, CRY = 860;
   static final int Leader = 10966, PAD = 52206;
    
	public static boolean UnCompleted(){
		NPC lead = NPCs.getNearest(Leader);
		if(lead == null){
			return false;
		}
		return Calc.Reach(lead);
	}

	public static boolean isCompleted(){
		return !UnCompleted();
	}
	public static void solve(){
		SceneObject tile = getCurrentTile();
		if (tile != null) {
			if (Players.getLocal().getLocation().equals(tile.getLocation())) {
                	Choose(getEmoteName());
			} else {
				if (tile.isOnScreen()) {
					tile.click(true);
				} else {
					Walking.walk(tile);
				}
			    for(int d = 0 ; d < 20 && Players.getLocal().isMoving(); d++, Task.sleep(100, 150));
			}
		}
	}

	private static String getEmoteName() {
		NPC lead = NPCs.getNearest(Leader);
		if (lead.getAnimation() == WAVE) {
			return "wave";
		} else if (lead.getAnimation() == SHAKE) {
			return "Shake";
		} else if (lead.getAnimation() == NOD) {
			return "Nod";
		} else if (lead.getAnimation() == CRY) {
			return "Cry";
		} else if (lead.getAnimation() == LAUGH) {
			return "Laugh";
		}
		return null;
	}

	private static void Choose(String emote) {
		WidgetChild Option = getWidgetEmote(emote);
		if(Option.validate()){
			Option.click(true);
			Task.sleep(200,600);
		}
	}

	private static WidgetChild getWidgetEmote(String emote) {
		for(WidgetChild e: Widgets.get(1188).getChildren()){
			if(e.getText().contains(emote)){
				return e;
			}
		}
		return null;
	}

	private static SceneObject getCurrentTile() {
		ArrayList<Integer> Dist = new ArrayList<Integer>();
		ArrayList<SceneObject> Objects = new ArrayList<SceneObject>();
		NPC lead = NPCs.getNearest(Leader);
		Dist.clear();
		for(SceneObject t : SceneEntities.getLoaded(PAD)){
			if(t !=null){
				int e = (int)Calculations.distance(t,lead);
				Dist.add(e);
				Objects.add(t);
			}
		}
		int x  = Collections.min(Dist);
		int r = Dist.indexOf(x);
		if(Objects.get(r)== null){
			return null;
		}
		return  Objects.get(r);
	}
}
