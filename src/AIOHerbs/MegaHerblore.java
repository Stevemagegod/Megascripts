package megascripts.aioherblore;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;



import megascripts.aioherblore.node.CleanHerbs;
import megascripts.aioherblore.node.OVERLOADS;
import megascripts.aioherblore.node.RangeExtremeTask;
import megascripts.aioherblore.node.UnfPotionTask;
import megascripts.api.SetupInfo;

public class MegaHerblore {

	public static int startlevel;

	public static void Setup(){
		startlevel = Skills.getLevel(Skills.HERBLORE);
		Variable.StartXp = Skills.getExperience(Skills.HERBLORE);
	      megascripts.SetupTest.nodes.add(new UnfPotionTask());
	      megascripts.SetupTest.nodes.add(new CleanHerbs());
	      megascripts.SetupTest.nodes.add(new RangeExtremeTask());
	      megascripts.SetupTest.nodes.add(new OVERLOADS());
		if(Variable.MODE_UNFPOTION){
			Variable.ITEM_ONE = SetupInfo.GetFirstItemID();
			Variable.ITEM_TWO = SetupInfo.GetSecondItemID();
		}else if(Variable.MODE_CLEANHERBS){
			Variable.ITEM_ONE = SetupInfo.GetFirstItemID();
		}
		NPC BankBooth = NPCs.getNearest(Bank.BANK_NPC_IDS);
		if(BankBooth !=null){
			Camera.turnTo(BankBooth);
		}
		Mouse.setSpeed(megascripts.Variable.MouseSpeed);
	}
    //START: Code generated using Enfilade's Easel
	 public static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    public static final Color color1 = new Color(0, 0, 0);

    public static final BasicStroke stroke1 = new BasicStroke(4);

    public static final Font font1 = new Font("Arial", 1, 15);

    public static final Image img1 = getImage("http://i46.tinypic.com/25inmfm.png");
    public static final Image MouseCurse = getImage("http://i48.tinypic.com/zwykh4.png");
    
    public static void Draw(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        Point p = Mouse.getLocation();
		g.drawImage(MouseCurse, p.x - 8, p.y - 8, null);
    	if(Variable.MODE_UNFPOTION){
    		Variable.PotionCount = (14-Inventory.getCount(Variable.ITEM_ONE)) + Variable.BankTime;
		}else if(Variable.MODE_CLEANHERBS){
			Variable.PotionCount = (28- Inventory.getCount(Variable.ITEM_ONE)) + Variable.BankTime;
		}else if(Variable.MODE_RANGEEXT){
			Variable.PotionCount = (27 - Inventory.getCount(Variable.RANAGEPOT)) + Variable.BankTime;
		}else if(Variable.MODE_OVERLOAD){
			Variable.PotionCount = (4-Inventory.getCount(Variable.TORSOL)) + Variable.BankTime;
		}
    	int XPGained = Skills.getExperience(Skills.HERBLORE) - Variable.StartXp;
   
       int CurrentLevel = Skills.getLevel(Skills.HERBLORE);
        g.drawImage(img1, 1, 388, null);
        g.setColor(color1);
        g.setStroke(stroke1);
        g.drawRect(9, 396, 20, 17);
        g.setFont(font1);
        g.drawString("X", 14, 412);
        g.drawString("" + Time.format(System.currentTimeMillis() - megascripts.aiofletcher.Variable.StartTime), 120, 461);
        g.drawString("" + Variable.PotionCount, 92, 487);
        g.drawString(""  + (int) ((Variable.PotionCount) * 3600000D / (System.currentTimeMillis() - megascripts.aiofletcher.Variable.StartTime)), 117, 510);
        g.drawString(""  + (CurrentLevel -startlevel), 263, 460);
        g.drawString("" + XPGained, 259, 487);
        g.drawString("" + (int) ((XPGained) * 3600000D / (System.currentTimeMillis() - megascripts.aiofletcher.Variable.StartTime)), 237, 511);
    }
    //END: Code generated using Enfilade's Easel
}
