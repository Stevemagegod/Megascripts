package megascripts.aiofiremaking;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Variable {

	public static int StartLevel;
	public static int StartXp;
	public static int FireMade;
	public static int LOG_ID;
	public static Area BANKAREA;
	public static Area FMAREA;
	public static Tile[] PathToBank;
	public static Tile[] PathToFM;
	public static Area EDGE_BANKAREA = new Area(new Tile[] { new Tile(3098, 3487, 0), new Tile(3098, 3500, 0), new Tile(3089, 3500, 0), 
			new Tile(3089, 3487, 0) });
	public static Area EDGE_FMAREA = new Area(new Tile[] { new Tile(3089, 3485, 0), new Tile(3089, 3493, 0), new Tile(3082, 3493, 0), 
			new Tile(3082, 3485, 0) });
	public static Tile[] EDGE_PATHTOFM = new Tile[] { new Tile(3093, 3490, 0), new Tile(3088, 3490, 0), new Tile(3085, 3488, 0) };
	public static Tile[] EDGE_PATHTOBANK= new Tile[] { new Tile(3086, 3488, 0), new Tile(3090, 3490, 0), new Tile(3094, 3490, 0) };
 
	public static Area FALAEast_BANKAREA = new Area(new Tile[] { new Tile(3021, 3352, 0), new Tile(3021, 3357, 0), new Tile(3018, 3357, 0), 
			new Tile(3018, 3359, 0), new Tile(3007, 3359, 0), new Tile(3007, 3352, 0) });
	public static Area FALAEast_FMAREA = new Area(new Tile[] { new Tile(3007, 3359, 0), new Tile(3018, 3359, 0), new Tile(3018, 3366, 0), 
			new Tile(3007, 3366, 0) });
	public static Tile[] FALAEast_PATHTOFM = new Tile[] { new Tile(3012, 3355, 0), new Tile(3012, 3358, 0), new Tile(3012, 3362, 0) };
	public static Tile[] FALAEast_PATHTOBANK = new Tile[] { new Tile(3012, 3362, 0), new Tile(3012, 3357, 0), new Tile(3012, 3354, 0) };
	public static Area FALAWest_BANKAREA  = new Area(new Tile[] { new Tile(2949, 3367, 0), new Tile(2941, 3367, 0), new Tile(2941, 3374, 0), 
			new Tile(2947, 3374, 0), new Tile(2947, 3369, 0) });
	public static Area FALAWest_FMAREA = new Area(new Tile[] { new Tile(2939, 3373, 0), new Tile(2950, 3373, 0), new Tile(2950, 3377, 0), 
			new Tile(2939, 3377, 0) });
	public static Tile[] FALAWest_PATHTOFM  = new Tile[] { new Tile(2944, 3368, 0), new Tile(2945, 3372, 0), new Tile(2945, 3375, 0) };
	public static Tile[] FALAWest_PATHTOBANK = new Tile[] { new Tile(2945, 3375, 0), new Tile(2945, 3371, 0), new Tile(2945, 3368, 0) };

	public static Area Camelot_BANKAREA = new Area(new Tile[] { new Tile(2727, 3486, 0), new Tile(2727, 3489, 0), new Tile(2730, 3489, 0), 
			new Tile(2730, 3498, 0), new Tile(2719, 3498, 0), new Tile(2719, 3496, 0), 
			new Tile(2717, 3496, 0), new Tile(2717, 3493, 0), new Tile(2719, 3493, 0), 
			new Tile(2719, 3489, 0), new Tile(2722, 3489, 0), new Tile(2722, 3486, 0) });
	public static Area Camelot_FMAREA  = new Area(new Tile[] { new Tile(2718, 3486, 0), new Tile(2730, 3486, 0), new Tile(2730, 3481, 0), 
			new Tile(2717, 3481, 0) });
	public static Tile[] Camelot_PATHTOFM = new Tile[] { new Tile(2724, 3492, 0), new Tile(2724, 3487, 0), new Tile(2724, 3484, 0) };
	public static Tile[] Camelot_PATHTOBANK= new Tile[] { new Tile(2725, 3485, 0), new Tile(2725, 3490, 0), new Tile(2724, 3493, 0) };
}
