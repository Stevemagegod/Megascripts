package megascripts.dungoneering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.imageio.ImageIO;



import megascripts.api.Actions;
import megascripts.api.Calc;
import megascripts.api.Flood;
import megascripts.dungoneering.node.Dungeon_Doors;
import megascripts.dungoneering.node.Enter_Dungeon;
import megascripts.dungoneering.node.Loot_StartRoom;
import megascripts.dungoneering.node.Room_Job;
import megascripts.dungoneering.puzzle.FallowTheLeader;
import megascripts.dungoneering.puzzle.Fishingferret;
import megascripts.dungoneering.puzzle.Leavers;
import megascripts.dungoneering.puzzle.SlidingPuzzle;


import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.client.CombatStatus;

import megascripts.dungoneering.*;

public class MegaDungeon {

	public static void Setup(){
		Variable.StartXp = Skills.getExperience(Skills.DUNGEONEERING);
		Variable.StartLevel = Skills.getLevel(Skills.DUNGEONEERING);
		megascripts.SetupTest.nodes.add(new Enter_Dungeon());
		megascripts.SetupTest.nodes.add(new Loot_StartRoom());
		megascripts.SetupTest.nodes.add(new Dungeon_Doors());
		megascripts.SetupTest.nodes.add(new Room_Job());
		
  
		addList(Variable.ALL_KEYDOOR, Variable.YELLOW_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.GREEN_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.BLUE_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.ORANGE_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.SILVER_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.PURPLE_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.CRIMSON_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.GOLD_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.Second_YELLOW_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.Second_GREEN_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.Second_BLUE_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.Second_ORANGE_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.Second_SILVER_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.Second_PURPLE_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.Second_CRIMSON_DOORS);
		addList(Variable.ALL_KEYDOOR, Variable.Second_GOLD_DOORS);
		addList(Variable.KEY_LIST, Variable.KeyGround);
		Mouse.setSpeed(megascripts.Variable.MouseSpeed);
	}
    
	private static void addList(ArrayList<Integer> list, int[] numbers) {
		for(int i : numbers){
			list.add(i);
		}
	}


	public static int getComplexity(){
		return Variable.Current_Complexity;
	}
	public static int getCurrentFloor(){
		return Variable.Current_Floor;
	}
	public static long getLastDungeon(){
		int d = Variable.Dungeons_TIMER.size();
		if (d == 0) {
			return 0;
		}
		return Variable.Dungeons_TIMER.get(0);
	}
	public static long getSlowestDungeon(){
		int d = Variable.Dungeons_TIMER.size();
		if (d == 0) {
			return 0;
		}
		return Collections.max(Variable.Dungeons_TIMER);
	}
	public static long getDungeonTime(){
		if(!Actions.InDungeon()){
			return 0;
		}
		long d = Variable.Dungeon_Time.getElapsed();
		if (d == 0) {
			return 0;
		}
		return Variable.Dungeon_Time.getElapsed();
	}
	public static long getFastestDungeon(){
		int d = Variable.Dungeons_TIMER.size();
		if (d == 0) {
			return 0;
		}
		return Collections.min(Variable.Dungeons_TIMER);
	}
	public static long getAverageDungeon(){
		long e =0;
		int d =  Variable.Dungeons_TIMER.size();
		if(d == 0){
			return 0;
		}
		for(long x : Variable.Dungeons_TIMER){
			e = e + x;
		}
		return e/d;
	}
    //START: Code generated using Enfilade's Easel
	public static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }
	public static void drawTile(Graphics2D g, Tile tile, Color col) {
		
		top: for (Polygon poly : tile.getBounds()) {
			for (int i = 0; i < poly.npoints; i++)
				if (!Calculations.isOnScreen(new Point(poly.xpoints[i],
						poly.ypoints[i])))
					continue top;
			g.setColor(new Color(col.getRed(), col.getGreen(), col.getBlue(),
					80));
			g.fillPolygon(poly);
			g.setColor(col);
			g.drawPolygon(poly);
		}
	}
	public static void drawRSAreaMM(Graphics render, Area area, Color color) {
		Tile[] array = area.getTileArray();
		for (final Tile tile : array) {
			if (tile == null) {
				continue;
			}
			Point p = tile.getMapPoint();
				render.setColor(new Color(color.getRed(), color.getGreen(),
						color.getBlue(), 60));
				render.fillRect(p.x - 2, p.y - 2, 4, 4);
			
		}
	}

	public static String formatNumber(int start) {
		DecimalFormat nf = new DecimalFormat("0.0");
		double i = start;
		if (i >= 1000000) {
			return nf.format((i / 1000000)) + "m";
		}
		if (i >= 1000) {
			return nf.format((i / 1000)) + "k";
		}
		return "" + start;
	}

	public static int getXpHour(int xp){
		return (int) ((xp) * 3600000D / (System.currentTimeMillis() - megascripts.aiofletcher.Variable.StartTime));
	}
	public static String getTimeToNextLevel(final int skill, final int xpPerHour) {
		if (xpPerHour < 1) {
			return Time.format(0L);
		}
		return Time.format((long) (Skills.getExperienceToLevel(skill,
				Skills.getLevel(skill) + 1) * 3600000D / xpPerHour));
	}

	public static final Color color1 = new Color(255, 255, 255, 55);
    public static final Color color2 = new Color(255, 255, 255);

    public static final Font font1 = new Font("Verdana", 1, 11);

    public static final Image img1 = getImage("http://i48.tinypic.com/23mx6ht.png");
    public static final Image img2 = getImage("http://i45.tinypic.com/v49p3d.png");
    public static final Image img3 = getImage("http://i48.tinypic.com/1042grb.png");
    public static final Image Cursor = getImage("http://i49.tinypic.com/2dvn7k8.png");
    
    public static void Draw(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
    if(megascripts.dungoneering.Variable.ShowPaint){
		if (Actions.InDungeon()) {
			
			for (Tile x : Variable.EnterdDoor) {
				if (x != null) {
					drawTile(g, x, Color.GREEN);
				}
			}
			for (Tile x : Variable.KeyEnterd) {
				if (x != null) {
					drawTile(g, x, Color.blue);
				}
			}
			for (Tile x : Variable.BlackDoor) {
				if (x != null) {
					drawTile(g, x, Color.RED);
				}
			}
			for (Tile x : Fishingferret.RoomTile) {
				if (x != null) {
					drawTile(g, x, Color.RED);
				}
			}
			for(Tile x: Variable.SkillEnterd){
				if(x !=null){
					drawTile(g,x,Color.CYAN);
				}
			}
			if(Leavers.step != null){
				drawTile(g,Leavers.step,Color.red);
			}
			//if(SlidingPuzzle.EMPTY_AREA.get(2) !=null){
		//			drawTile(g,SlidingPuzzle.EMPTY_AREA.get(2),Color.CYAN);
		//	}
			
		}
		Point p = Mouse.getLocation();
		g.drawImage(Cursor, p.x - 8, p.y - 8, null);
		
        g.drawImage(img1, 0, 256, null);
        g.drawImage(img2, 381, 503, null);
        g.drawImage(img2, 191, 504, null);
        g.drawImage(img2, 6, 503, null);


        
        g.setColor(color1);
        g.fillRect(11, 414, 406, 85);

        g.setFont(font1);
        g.setColor(color2);
        g.drawString("State", 229, 523);
        g.drawString("Overall", 38, 522);
        g.drawString("Misc", 423, 523);
        if(Variable.OverAll){
        g.drawString("TimeRunning:" + Time.format(System.currentTimeMillis() - megascripts.aiofletcher.Variable.StartTime), 27, 436);
        g.drawString("Complexity:" + getComplexity(), 27, 453);
        g.drawString("Floor:" + getCurrentFloor(), 27, 469);
        g.drawString("Fastest Dungeon:" + Time.format(getFastestDungeon()), 175, 452);
        g.drawString("Slowest Dungeon:"+ Time.format(getSlowestDungeon()), 175, 468);
        g.drawString("Last Dungeon:" + Time.format(getLastDungeon()), 175, 436);
        g.drawString("Floor Completed:" + Variable.DungeonCompleted, 27, 486);
        g.drawString("Average Time:" + Time.format(getAverageDungeon()), 175, 485);
        }else if(Variable.STATE){
    
        	int XpGained = Skills.getExperience(Skills.DUNGEONEERING) - Variable.StartXp;
            int LevelGained = Skills.getLevel(Skills.DUNGEONEERING)- Variable.StartLevel;
        	int TokensGained = (int) XpGained/10;
        	g.drawString("Exp Gained:" + XpGained, 27, 436);
            g.drawString("Exp/Hour:" + formatNumber(XpGained), 27, 453);
            g.drawString("Token:" + TokensGained, 27, 469);
            g.drawString("Token/Hour:" + formatNumber(TokensGained), 200, 452);
            g.drawString("Level Gained:" + LevelGained, 200, 468);
            g.drawString("Average Dungeon PH:" + formatNumber(Variable.DungeonCompleted), 200, 436);
            g.drawString("Time To Level " + Skills.getLevel(Skills.DUNGEONEERING) +":"  + getTimeToNextLevel(Skills.DUNGEONEERING,getXpHour(XpGained)), 27, 486);
            g.drawString("Dungeon Time:" + Time.format(getDungeonTime()), 200, 485);
          }else if(Variable.Misc){
        	g.drawString("Total Deaths" + Variable.Death, 27, 436);
            g.drawString("Exp/Hour:" , 27, 453);
            g.drawString("Token:" , 27, 469);
            g.drawString("Token/Hour:" , 200, 452);
            g.drawString("Level Gained:" , 200, 468);
            g.drawString("Average Dungeon PH:", 200, 436);
            g.drawString("Time To Level " , 27, 486);
            g.drawString("Dungeon Time:" , 200, 485);
        }
    }
    g.drawImage(img3, 4, 392, null);
        }
    //END: Code generated using Enfilade's Easel



    
}
