package megascripts.aiofletcher;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import megascripts.aiofletcher.node.LogsToBows;
import megascripts.aiofletcher.node.LogsToShaft;
import megascripts.aiofletcher.node.StringBows;
import megascripts.api.SetupInfo;

import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * 
 * @author Magorium
 *
 */
public class MegaFletcher {


	public static int startlevel;
	public static void Setup(){
		startlevel = Skills.getLevel(Skills.FLETCHING);
	      megascripts.SetupTest.nodes.add(new LogsToBows());
	      megascripts.SetupTest.nodes.add(new LogsToShaft());
	      megascripts.SetupTest.nodes.add(new StringBows());
		if(Variable.MODE_LOGTOSHAFT){
			Variable.Multi = 15;
			Variable.ITEM_ONE = Variable.NORMAL_LOG_ID;
		}else if(Variable.MODE_LOGTOBOW){
			Variable.ITEM_ONE = SetupInfo.GetFirstItemID();
		}else if(Variable.MODE_BOWSTRING){
			Variable.ITEM_ONE =SetupInfo.GetFirstItemID(); 
			Variable.ITEM_TWO =SetupInfo.GetSecondItemID(); 
		}
		NPC BankBooth = NPCs.getNearest(Bank.BANK_NPC_IDS);
		if(BankBooth !=null){
			Camera.turnTo(BankBooth);
		}
		Variable.StartXp = Skills.getExperience(Skills.FLETCHING);
		Mouse.setSpeed(megascripts.Variable.MouseSpeed);
	}
    //START: Code generated using Enfilade's Easel
	   public static  Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    public static  final Color color1 = new Color(0, 0, 0);

    public static  final BasicStroke stroke1 = new BasicStroke(4);

    public static  final Font font1 = new Font("Arial", 1, 15);

    public static  final Image img1 = getImage("http://i45.tinypic.com/2r2tcth.png");
    public static final Image MouseCurse = getImage("http://i48.tinypic.com/byjbs.png");
    
    public static  void Draw(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        Point p = Mouse.getLocation();
		g.drawImage(MouseCurse, p.x - 8, p.y - 8, null);
       if(Tabs.INVENTORY.isOpen()){
    	   Variable.Log_Count = ((28 - Inventory.getCount(Variable.ITEM_ONE)) + Variable.BankTime) * Variable.Multi;
       }
       int XPGained = Skills.getExperience(Skills.FLETCHING) - Variable.StartXp;
       
        g.drawImage(img1, 1, 389, null);
        g.setFont(font1);
        g.setColor(color1);
        g.drawString("" + Time.format(System.currentTimeMillis() - Variable.StartTime), 123, 462);
        g.drawString("" + Variable.Log_Count, 71, 489);
        g.drawString("" + (int) ((Variable.Log_Count) * 3600000D / (System.currentTimeMillis() - Variable.StartTime)), 98, 511);
        g.drawString("" + (Skills.getLevel(Skills.FLETCHING) - startlevel), 268, 462);
        g.drawString("" + XPGained, 262, 489);
        g.drawString("" + + (int) ((XPGained) * 3600000D / (System.currentTimeMillis() - Variable.StartTime)), 236, 511);
        g.setStroke(stroke1);
        g.drawRect(9, 397, 23, 17);
        g.drawString("X", 16, 413);
    }
    //END: Code generated using Enfilade's Easel
    ///


	
}
