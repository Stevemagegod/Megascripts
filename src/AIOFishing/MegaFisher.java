package megascripts.aiofishing;

import java.awt.*;

import java.io.IOException;
import java.net.URL;

import javax.imageio.*;


import megascripts.aiofishing.node.Fishing_BankMode;
import megascripts.aiofishing.node.Fishing_PowerFishing;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;

public class MegaFisher {

	
	public static void Setup(){
		megascripts.SetupTest.nodes.add(new Fishing_BankMode());
		megascripts.SetupTest.nodes.add(new Fishing_PowerFishing());
		Variable.StartXp = Skills.getExperience(Skills.FISHING);
		Variable.StartLevel = Skills.getLevel(Skills.FISHING);
		//

		
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

    public static final BasicStroke stroke1 = new BasicStroke(3);

    public static final Font font1 = new Font("Arial", 1, 16);
    public static final Font font2 = new Font("Arial", 1, 14);

    public static final Image img1 = getImage("http://i47.tinypic.com/116kj85.png");
    public static final Image Cursor = getImage("http://i49.tinypic.com/2dvn7k8.png");
    public static void Draw(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        int XPGAINED = Skills.getExperience(Skills.FISHING) - Variable.StartXp;
		
		Point p = Mouse.getLocation();
		g.drawImage(Cursor, p.x - 8, p.y - 8, null);
        g.drawImage(img1, 1, 389, null);
        g.setColor(color1);
        g.setStroke(stroke1);
        g.drawRect(17, 400, 21, 17);
        g.setFont(font1);
        g.drawString("X", 23, 416);
        g.setFont(font2);
        g.drawString(""+ Time.format(System.currentTimeMillis() - megascripts.aiofletcher.Variable.StartTime), 123, 461);
        g.drawString(""+ Variable.FishGained, 71, 488);
        g.drawString("" + (int) ((Variable.FishGained) * 3600000D / (System.currentTimeMillis() - megascripts.aiofletcher.Variable.StartTime)), 98, 510);
        g.drawString("" + (Skills.getLevel(Skills.FISHING) - Variable.StartLevel), 266, 464);
        g.drawString("" + XPGAINED, 259, 485);
        g.drawString("" + (int) ((XPGAINED) * 3600000D / (System.currentTimeMillis() - megascripts.aiofletcher.Variable.StartTime)), 238, 511);
    }
    //END: Code generated using Enfilade's Easel
}
