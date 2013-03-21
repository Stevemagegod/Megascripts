package megascripts.dungoneering.node;

import megascripts.api.Actions;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Enter_Dungeon extends Node{

	public static int ENTERANCE_GATE = 48496;
	public static int BROKEN_LADDER= 50552;
	@Override
	public boolean activate() {
		return megascripts.Variable.MEGA_DUNGEON && (At_Gate() || ATSurf());
	}

	public static boolean ATSurf() {
		return Actions.Object_IsValid(BROKEN_LADDER);
	}

	public static boolean At_Gate(){
		return Actions.Object_IsValid(ENTERANCE_GATE);
	}
	@Override
	public void execute() {
		if (ATSurf()) {
            SurfDown();
		} else {
			EnterDungeon(1, 4);
		}
	}
	public static void SurfDown() {
		SceneObject Surf = SceneEntities.getNearest(BROKEN_LADDER);
		if (Surf != null) {
			if (Calculations.distanceTo(Surf) < 4) {
				Surf.interact("Jump-Down");
				Task.sleep(1000,1400);
			}else{
				Walking.walk(Surf);
				
			}
		}
	}

	public static void EnterDungeon(int FloorN,int Complex) {
		int FloorNumber = 669;
		WidgetChild Floor = Widgets.get(947).getChild(FloorNumber);
		WidgetChild ComplexNumber = Widgets.get(938).getChild(getComplex(Complex));
		WidgetChild FloorConform = Widgets.get(947).getChild(766);
		WidgetChild ComplexConfrom = Widgets.get(938).getChild(37);
		WidgetChild NoParty = Widgets.get(1186).getChild(9);
		WidgetChild Yes = Widgets.get(1188).getChild(12);
		if (!Floor.validate() && !ComplexNumber.validate()
				&& !NoParty.validate() && !Yes.validate()) {
			PressEnterDungeon();
		} else {
			if (NoParty.validate()) {
				NoParty.click(true);
				Task.sleep(500,1000);
			} else if (Yes.validate()) {
				Yes.click(true);
				Task.sleep(500,1000);
			} else if (Floor.validate()) {
				Floor.click(true);
				Task.sleep(500,1000);
				FloorConform.click(true);
				Task.sleep(500,1000);
			} else if (ComplexNumber.validate()) {
				if (ComplexNumber.click(true)) {
					Task.sleep(500, 1000);
					if (ComplexConfrom.click(true)) {
						Task.sleep(500, 1000);
					}
				}
			}

		}

	}

	public static int getComplex(int complex) {
		if(complex == 1){
			return 60;
		}else if(complex == 2){
			return 61;
		}else if(complex == 3){
			return 66;
		}else if(complex == 4){
			return 75;
		}else if(complex == 5){
			return 76;
		}else if(complex == 6){
			return 81;
		}
		return 60;
	}

	public static void PressEnterDungeon() {
		SceneObject Gate = SceneEntities.getNearest(ENTERANCE_GATE);
		if (Gate != null) {
			if (Gate.isOnScreen()) {
				Gate.interact("Climb-Down");
				Task.sleep(1000,1400);
			}else{
				Walking.walk(Gate);
				
			}
		}
	}
}
