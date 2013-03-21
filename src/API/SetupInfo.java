package megascripts.api;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * 
 * @author Magorium
 * 
 */
public class SetupInfo {

	public static final WidgetChild FirstItemSlot = Widgets.get(679, 0).getChild(0);
	public static final WidgetChild SecondItemSlot = Widgets.get(679, 0).getChild(14);
	
	public static int GetFirstItemID() {
       return FirstItemSlot.getChildId();
	}
	public static int GetSecondItemID() {
	       return SecondItemSlot.getChildId();
		}
}
