package megascripts.dungoneering.boss;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import megascripts.api.Prayer;
import megascripts.api.Prayer.Ancient;
import megascripts.api.Prayer.Modern;

public class SkeletalHorde {

	public static void Kill(){
		if(Skills.getLevel(Skills.PRAYER) >= 37) {
			if(Prayer.isModernSetActive()) {
				if(Prayer.getRemainingPoints() > 0) {
					if(!Prayer.isActive(Modern.PROTECT_FROM_MAGIC)) {
						Tabs.PRAYER.open();
						Prayer.setActivated(Modern.PROTECT_FROM_MAGIC, true);
						Task.sleep(90, 110);
						Tabs.INVENTORY.open();
					}
				}
			}
		} if(Skills.getLevel(Skills.PRAYER) >= 65) {
			if(!Prayer.isModernSetActive()) {
				if(Prayer.getRemainingPoints() > 0) {
					if(!Prayer.isActive(Ancient.DEFLECT_MAGIC)) {
						Tabs.PRAYER.open();
						Prayer.setActivated(Ancient.DEFLECT_MAGIC, true);
						Task.sleep(90, 110);
						Tabs.INVENTORY.open();
					}
				}
			}
		}
	     if(Settings.get(172) == 0){
	            TurOffRet();
	        }
	     if(Players.getLocal().getHpPercent() <= 40){
	     for(NPC Healer:NPCs.getLoaded()){
	    	 if(Healer !=null){
	    		 if(Healer.getName().contains("Divine")){
	    			 if(Calculations.distanceTo(Healer) > 3){
	    				 Walking.walk(Healer);
	    			 }else{
	    				 for(NPC Target:NPCs.getLoaded()){
	    					 if(Target !=null){
	    						 if(!Target.getName().contains("Divine")){
	    							 if(Calculations.distanceTo(Target) <= 2){
	    								 if(Players.getLocal().getInteracting() == null){
	    									 Target.interact("Attack");
	    									 Task.sleep(1000,1500);
	    								 }
	    							 }
	    						 }
	    					 }
	    				 }
	    			 }
	    			 
	    		 }
	    	 }
	     }
	     }else{
	    	 for(NPC Target:NPCs.getLoaded()){
				 if(Target !=null){
					 if(!Target.getName().contains("Divine")){
						 if(!Target.isOnScreen()){
							 Walking.walk(Target);
							 Camera.turnTo(Target);
							 Task.sleep(1000,1500);
						 }else{
							 if(Players.getLocal().getInteracting() == null){
								 Target.interact("Attack");
								 Task.sleep(1000,1500);
							 }
						 }
					 }
				 }
			 }
	     }
	}

	public static void TurOffRet() {
		if (Settings.get(172) == 0) {
			if (!(Tabs.getCurrent() == Tabs.ATTACK)) {
				Tabs.ATTACK.open();
			}
			if (Widgets.get(884).getChild(12).getTextureId() != 655) {
				WidgetChild OffRetalite = Widgets.get(884).getChild(11);
				OffRetalite.click(true);
				Task.sleep(400,600);

			}
			Tabs.INVENTORY.open();
		}
	}
}
