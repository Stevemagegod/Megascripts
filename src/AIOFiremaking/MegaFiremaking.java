package megascripts.aiofiremaking;

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

import megascripts.aiofiremaking.node.Mode_BonFire;
import megascripts.aiofiremaking.Variable;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;


public class MegaFiremaking {

	public static void Setup(){

		Variable.StartLevel = Skills.getLevel(Skills.FIREMAKING);
		Variable.StartXp = Skills.getExperience(Skills.FIREMAKING);
		megascripts.SetupTest.nodes.add(new Mode_BonFire());
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

    public static final Font font1 = new Font("Arial", 1, 12);

    public static final Image img1 = getImage("http://i46.tinypic.com/343rhx5.png");
    public static final Image MouseCurse = getImage("http://i48.tinypic.com/byjbs.png");
    
    public static void Draw(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        Point p = Mouse.getLocation();
        int XPGAINED = Skills.getExperience(Skills.FIREMAKING) - Variable.StartXp;
		g.drawImage(MouseCurse, p.x - 8, p.y - 8, null);
        g.drawImage(img1, 0, 389, null);
        g.setFont(font1);
        g.setColor(color1);
        g.drawString("" + Time.format(System.currentTimeMillis() - megascripts.aiofletcher.Variable.StartTime), 122, 461);
        g.drawString("" + megascripts.aiofiremaking.Variable.FireMade, 68, 487);
        g.drawString("" + (int) (( megascripts.aiofiremaking.Variable.FireMade) * 3600000D / (System.currentTimeMillis() -  megascripts.aiofletcher.Variable.StartTime)), 93, 510);
        g.drawString("" + (Skills.getLevel(Skills.FIREMAKING) - Variable.StartLevel), 264, 461);
        g.drawString("" + XPGAINED, 258, 486);
        g.drawString("" + (int) ((XPGAINED) * 3600000D / (System.currentTimeMillis() -  megascripts.aiofletcher.Variable.StartTime)), 238, 510);
        g.setStroke(stroke1);
        g.drawRect(10, 398, 19, 17);
        g.drawString("X", 17, 412);
    }
    //END: Code generated using Enfilade's Easel
}
