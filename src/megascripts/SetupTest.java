package megascripts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import megascripts.aiofiremaking.MegaFiremaking;
import megascripts.aiofishing.MegaFisher;
import megascripts.aiofletcher.MegaFletcher;
import megascripts.aiofletcher.Variable;
import megascripts.aiofletcher.node.LogsToBows;
import megascripts.aioherblore.MegaHerblore;
import megascripts.api.Actions;
import megascripts.api.Prayer;
import megascripts.dungoneering.MegaDungeon;
import megascripts.dungoneering.node.Dungeon_Doors;
import megascripts.dungoneering.puzzle.Magicalconstruct;

import megascripts.graphic.General_GUI;
import megascripts.graphic.LogHandler;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;

import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.bot.Context;
import org.powerbot.game.client.Client;

import megascripts.SetupTest.MousePaint;


@Manifest(authors = { "Magorium" }, name = "MegaScripts",description = "Supporting Many Aio Skills Scripts ")
public class SetupTest extends ActiveScript implements PaintListener,MessageListener,MouseListener {

	public static final LinkedList<Node> nodes = new LinkedList<Node>();
	MousePaint mousePaint = new MousePaint();
	public static String Message;
	@Override
	public void onStart() {
		General_GUI gui = new General_GUI();
		gui.setVisible(true);
		Variable.StartTime = System.currentTimeMillis();
       Mouse.setSpeed(Speed.VERY_FAST);
	}


	@Override
	public int loop() {



		if (Game.isLoggedIn()) {
			if(megascripts.Variable.MEGA_DUNGEON){
				if(megascripts.dungoneering.Variable.LeaveDungeon){
					if(!Actions.InDungeon()){
						megascripts.dungoneering.Variable.LeaveDungeon = false;
					}
					Dungeon_Doors.Leave_Dungeon();
				}else{
					for (Node tree : nodes) {
						if (tree.activate() && tree != null) {
							tree.execute();
						}
					}
				}
			}else{
				for (Node tree : nodes) {
					if (tree.activate() && tree != null) {
						tree.execute();
					}
				}
			}
       }
		return Random.nextInt(0, 500);
	}
	public class MousePaint {
        
        public int waveSize = 0;
       
        @SuppressWarnings({"serial", "unused"})
        public class MousePathPoint extends Point {

                private long finishTime;
                private double lastingTime;

                public MousePathPoint(int x, int y, int lastingTime) {
                        super(x, y);
                        this.lastingTime = lastingTime;
                        finishTime = System.currentTimeMillis() + lastingTime;
                }

                public boolean isUp() {
                        return System.currentTimeMillis() > finishTime;
                }
               
        }

        public double getRot(int ticks){
            return (System.currentTimeMillis() % (360 * ticks)) / ticks;
        }
       
        public LinkedList<MousePathPoint> MousePath = new LinkedList<MousePathPoint>();
       
        public void drawTrail(Graphics g1) {
                Graphics2D g = (Graphics2D) g1;
                g.setStroke(new BasicStroke(1));
                while (!MousePath.isEmpty() && MousePath.peek().isUp()) {
                        MousePath.remove();
                }
                Point clientCursor = Mouse.getLocation();
                MousePathPoint mpp = new MousePathPoint(clientCursor.x, clientCursor.y, 250);
                if (MousePath.isEmpty() || !MousePath.getLast().equals(mpp)) {
                        MousePath.add(mpp);
                }
                MousePathPoint lastPoint = null;
                for (MousePathPoint a : MousePath) {
                        if (lastPoint != null) {
                                long mpt = System.currentTimeMillis() - Mouse.getPressTime();
                                if (Mouse.getPressTime() == -1 || mpt >= 250) {
                                        g.setColor(Color.MAGENTA);
                                }
                                if (mpt < 250) {
                                g.setColor(Color.RED);
                                }
                                g.drawLine(a.x, a.y, lastPoint.x, lastPoint.y);
                        }
                        lastPoint = a;
                }
        }
               
        public void drawMouse(Graphics g1) {
                Graphics2D g = (Graphics2D) g1;
                g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                g.setStroke(new BasicStroke(3));
                g.setColor(Color.BLACK);
            g.drawOval(Mouse.getLocation().x - 8, Mouse.getLocation().y - 8, 15, 15);
            g.setStroke(new BasicStroke(1));
            g.setColor(new Color(0, 0, 0, 114));
            g.fillOval(Mouse.getLocation().x - 8, Mouse.getLocation().y - 8, 15, 15);
                Point MouseLoc = Mouse.getLocation();
            long mpt = System.currentTimeMillis() - Mouse.getPressTime();
            g.rotate(Math.toRadians(getRot(5)), Mouse.getLocation().x, Mouse.getLocation().y);
            if (Mouse.getPressTime() == -1 || mpt >= 250) {
                g.setColor(Color.MAGENTA);
                g.drawLine(MouseLoc.x - 5, MouseLoc.y, MouseLoc.x + 5, MouseLoc.y);
                        g.drawLine(MouseLoc.x, MouseLoc.y - 5, MouseLoc.x, MouseLoc.y + 5);
            }
            if (mpt < 250) {
                g.setColor(Color.RED);
                g.drawLine(MouseLoc.x - 5, MouseLoc.y, MouseLoc.x + 5, MouseLoc.y);
                        g.drawLine(MouseLoc.x, MouseLoc.y - 5, MouseLoc.x, MouseLoc.y + 5);
            }
        }             
       
        public void draw(Graphics g1) {
                 Graphics2D g = (Graphics2D) g1;
                 g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                 drawTrail(g);
                 drawMouse(g);
        }
}
	@Override
	public void onRepaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		if (megascripts.Variable.MEGA_FLETCHER) {
			MegaFletcher.Draw(g1);
		} else if (megascripts.Variable.MEGA_HERBLORE) {
			MegaHerblore.Draw(g1);
		} else if (megascripts.Variable.MEGA_FISHER) {
			MegaFisher.Draw(g1);
		} else if(megascripts.Variable.MEGA_FIREMAKING){
			MegaFiremaking.Draw(g1);
		} else if(megascripts.Variable.MEGA_DUNGEON){
            MegaDungeon.Draw(g1);
		}

		LogHandler.Draw(g);
	}

	@Override
	public void messageReceived(MessageEvent e) {
		Message = e.getMessage();
		String d = e.getMessage();
		if(megascripts.Variable.MEGA_FISHER){
			if(d.contains("You catch")){
				megascripts.aiofishing.Variable.FishGained++;
			}
		}else if(megascripts.Variable.MEGA_FIREMAKING){
			if(d.contains("You add") || d.contains("The fire catches")){
				megascripts.aiofiremaking.Variable.FireMade++;
			}
		}else if(megascripts.Variable.MEGA_DUNGEON){
			if(d.toLowerCase().contains("level of")&& d.toLowerCase().contains("you need")){
				LogHandler.Print("Aborting This puzzle , Don't have Required Level",Color.red);
				megascripts.dungoneering.Variable.Break_Puzzle = true;
			}
			if(d.toLowerCase().contains("the act of simply retrieving")){
				megascripts.dungoneering.puzzle.Pondskaters.Solved = true;
			}
			if(d.toLowerCase().contains("a broken construct")){
				if(d.toLowerCase().contains("head")){
					LogHandler.Print("This Machine is Missing: Head",Color.green);
					Magicalconstruct.arm_t = false;
					Magicalconstruct.leg_t= false;
					Magicalconstruct.head_t= true;
				}else if(d.toLowerCase().contains("leg")){
					LogHandler.Print("This Machine is Missing: Leg",Color.green);
					Magicalconstruct.arm_t = false;
					Magicalconstruct.leg_t= true;
					Magicalconstruct.head_t= false;
				}else if(d.toLowerCase().contains("arm")){
					LogHandler.Print("This Machine is Missing: Arm",Color.green);
					Magicalconstruct.arm_t = true;
					Magicalconstruct.leg_t= false;
					Magicalconstruct.head_t= false;
				}
			}
			if(d.toLowerCase().contains("oh dear")){
				LogHandler.Print("Died,Death Walking...");
				megascripts.dungoneering.Variable.Death++;
			}
			if(d.contains("Floor") && d.contains("Complexity")){
				String[] Messages = d.split(" ");
				String FloorNumber = Messages[1].replace("<col=641d9e>", "");
				megascripts.dungoneering.Variable.Current_Floor = Integer.parseInt(FloorNumber);
				String ComplexityNumber = Messages[6].replace("<col=641d9e>", "");
				megascripts.dungoneering.Variable.Current_Complexity = Integer.parseInt(ComplexityNumber);
				LogHandler.Print("We Are At Floor: " + megascripts.dungoneering.Variable.Current_Floor,Color.blue);
				LogHandler.Print("Current Complexity is: " +megascripts.dungoneering.Variable.Current_Complexity,Color.blue);
				megascripts.dungoneering.Variable.Dungeon_Time = new Timer(2000000000);
				if (megascripts.dungoneering.Variable.Dungeon_Time !=null && megascripts.dungoneering.Variable.Dungeon_Time.getElapsed() != 0) {
					megascripts.dungoneering.Variable.Dungeon_Time.reset();
				}
				megascripts.dungoneering.Variable.DungeonStarted  = true;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Rectangle Button1 = new Rectangle(7, 507, 102, 22);
		Rectangle Button2 = new Rectangle(191, 506, 102, 22);
		Rectangle Button3 = new Rectangle(383, 504, 110, 27);
		Rectangle hide = new Rectangle(2, 391, 25, 21);

		if(Button1.contains(e.getPoint())){
			megascripts.dungoneering.Variable.OverAll = true;
			megascripts.dungoneering.Variable.STATE = false;
			megascripts.dungoneering.Variable.Misc = false;
		}
		if(Button2.contains(e.getPoint())){

			megascripts.dungoneering.Variable.OverAll = false;
			megascripts.dungoneering.Variable.STATE = true;
			megascripts.dungoneering.Variable.Misc = false;

		}
		if(Button3.contains(e.getPoint())){
			megascripts.dungoneering.Variable.OverAll = false;
			megascripts.dungoneering.Variable.STATE = false;
			megascripts.dungoneering.Variable.Misc = true;
		}
		if(hide.contains(e.getPoint())){
			megascripts.dungoneering.Variable.ShowPaint = !megascripts.dungoneering.Variable.ShowPaint;
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
