package megascripts.api;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
 
import java.util.LinkedList;
import java.util.Queue;
 
/**
 * Made by Aonor.
 * Flood fill algorithm for calculating room tiles.
 */
public class Flood {
 
    private static Tile[] flooded;
    private static  Area area;
 
    public static void CalcRoomArea(final Tile start) {
    	try{
        final Queue<Tile> queue = new LinkedList<Tile>();
        queue.add(start);
        final Queue<Tile> queue2 = new LinkedList<Tile>();
        final Queue<Tile> checked = new LinkedList<Tile>();
        int iterations = 0;
        while (!queue.isEmpty() && iterations < 1000) {
            final Tile t = queue.remove();
            if (!isWall(t) && !checked.contains(t)) {
                queue2.add(t);
                queue.add(t.derive(0, 1));
                queue.add(t.derive(1, 0));
                queue.add(t.derive(0, -1));
                queue.add(t.derive(-1, 0));
            }
            checked.add(t);
            iterations++;
        }
        flooded = queue2.toArray(new Tile[queue2.size()]);
        int min_x = Integer.MAX_VALUE, min_y = Integer.MAX_VALUE;
        int max_x = Integer.MIN_VALUE, max_y = Integer.MIN_VALUE;
        for (final Tile tile : flooded) {
            final int x = tile.getX(), y = tile.getY();
            if (x < min_x) min_x = x;
            if (y < min_y) min_y = y;
            if (x > max_x) max_x = x;
            if (y > max_y) max_y = y;
        }
        area = new Area(new Tile(min_x - 1, min_y - 1, start.getPlane()), new Tile(max_x + 2, max_y + 2, start.getPlane()));
		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}
 
    public static Tile[] getFlood() {
        return flooded;
    }
 
    public static  Area getArea() {
    	CalcRoomArea(Players.getLocal().getLocation());
        return area;
    }
 
    private static int getFlag(final Tile t) {
        final int base_x = Game.getBaseX(), base_y = Game.getBaseY();
        final int x = t.getX() - base_x, y = t.getY() - base_y;
        final Tile off = Walking.getCollisionOffset(Game.getPlane());
        return Walking.getCollisionFlags(Game.getPlane())[x - off.getX()][y - off.getY()];
    }
 
    public static boolean isWall(final Tile t) {
        return (getFlag(t) >>> 21 & 0x401) != 0;
    }
 
}