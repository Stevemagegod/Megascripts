package megascripts.dungoneering.boss;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.*;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import megascripts.api.Prayer;
import megascripts.api.Prayer.Ancient;
import megascripts.api.Prayer.Modern;
import java.util.Calendar;

public class Shadow {
    static int SpeicalAttack = 13030;


    public static void TurOffRet() {
        if (!(Tabs.getCurrent() == Tabs.ATTACK)) {
            Tabs.ATTACK.open();
            Task.sleep(1500);
        }
        if (Widgets.get(884).getChild(12).getTextureId() != 655) {
            Task.sleep(400);
            WidgetChild OffRetalite = Widgets.get(884).getChild(11);
            OffRetalite.click(true);
            Task.sleep(400);

        }
        Tabs.INVENTORY.open();
        Task.sleep(1000);
    }


    public static void Kill(){
        if(Settings.get(172) == 0){
            TurOffRet();
        }
        NPC Boss = NPCs.getNearest("Shadow-Forger Ihlakhizan");
        if(Boss.getAnimation()!= SpeicalAttack&&Players.getLocal().getInteracting()==null){
            Boss.interact("Attack");
            Task.sleep(300);
        }
        if(Boss.getAnimation()!=SpeicalAttack&& Calculations.distance(Boss,Players.getLocal().getLocation())>=4){
            Walking.walk(Boss);
        }
        if(Boss.getAnimation() == SpeicalAttack){
            Walking.walk(new Tile (Boss.getLocation().getX(),Boss.getLocation().getY() + 4,4));
        }else{
            if(Boss !=null){
                if(Skills.getLevel(Skills.PRAYER) >= 43) {
                    if(Prayer.isModernSetActive()) {
                        if(Prayer.getRemainingPoints() > 0) {
                            if(!Prayer.isActive(Modern.PROTECT_FROM_MELEE)) {
                                Tabs.PRAYER.open();
                                Prayer.setActivated(Modern.PROTECT_FROM_MELEE, true);
                                Task.sleep(90, 110);
                                Tabs.INVENTORY.open();
                            }
                        }
                    }
                }
            }
        }
    }
}
