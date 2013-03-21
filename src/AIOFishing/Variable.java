package megascripts.aiofishing;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Variable {

	public static int StartXp;
	public static int StartLevel;
	public static int FishGained;
	public static String Action;
	public static int [] FISH_IDS;
	public static int FLYFISH_SPOT [] = {328,329};
	public static int BAITSPOT [] = {328,329,320,323};
	public static int BARBSPOT [] = {14882};
	public static int NETSPOT [] = {322,320,313,323};
	public static int CAGESPOT [] = {321,312,324};
	public static int HARPOONSPOT [] = {321,322,313,312,324};
	public static boolean KARAMJA_BANK_MODE = false;
    public static boolean BANK_MODE;
    public static boolean POWERFISHING;
    public static Tile[] PATHTOFISH;
    public static Tile[] PATHTOBANK;
    public static Area BANKAREA;
    public static Area FISHAREA;
    public static Area KARMJA_FISHAREA= new Area(new Tile[] { new Tile(2918, 3183, 0), new Tile(2930, 3183, 0), new Tile(2929, 3172, 0), 
			new Tile(2917, 3174, 0) });
    
    public static Area KARMJA_BANKAREA= new Area(new Tile[] { new Tile(2844, 3150, 0), new Tile(2857, 3150, 0), new Tile(2857, 3137, 0), 
			new Tile(2843, 3138, 0) });
    public static Tile[] KARMJA_PATHTOFISH = new Tile[] { new Tile(2847, 3146, 0), new Tile(2852, 3147, 0), new Tile(2857, 3148, 0), 
			new Tile(2862, 3149, 0), new Tile(2867, 3150, 0), new Tile(2872, 3152, 0), 
			new Tile(2877, 3154, 0), new Tile(2880, 3158, 0), new Tile(2884, 3161, 0), 
			new Tile(2888, 3164, 0), new Tile(2892, 3167, 0), new Tile(2897, 3169, 0), 
			new Tile(2901, 3172, 0), new Tile(2905, 3173, 0), new Tile(2910, 3172, 0), 
			new Tile(2915, 3171, 0), new Tile(2920, 3172, 0), new Tile(2924, 3175, 0), 
			new Tile(2924, 3180, 0) };
    public static Tile[] KARMJA_PATHTOBANK = new Tile[] { new Tile(2923, 3180, 0), new Tile(2924, 3175, 0), new Tile(2920, 3171, 0), 
			new Tile(2915, 3171, 0), new Tile(2910, 3171, 0), new Tile(2905, 3171, 0), 
			new Tile(2900, 3170, 0), new Tile(2895, 3169, 0), new Tile(2890, 3167, 0), 
			new Tile(2885, 3164, 0), new Tile(2880, 3161, 0), new Tile(2876, 3158, 0), 
			new Tile(2871, 3156, 0), new Tile(2866, 3154, 0), new Tile(2861, 3152, 0), 
			new Tile(2857, 3149, 0), new Tile(2852, 3147, 0), new Tile(2848, 3144, 0), 
			new Tile(2845, 3142, 0) };
    public static Area BANKAREA_EDGEVILLAGE = new Area(new Tile[] { new Tile(3098, 3487, 0), new Tile(3098, 3500, 0), new Tile(3089, 3500, 0), 
			new Tile(3089, 3487, 0) });
    public static Area  FISHAREA_EDGEVILLAGE = new Area(new Tile[] { new Tile(3100, 3438, 0), new Tile(3100, 3419, 0), new Tile(3112, 3420, 0), 
			new Tile(3112, 3438, 0) });
    public static Tile[] EDGEVILLAGE_BANKTOFISH = new Tile[] { new Tile(3093, 3491, 0), new Tile(3090, 3490, 0), new Tile(3086, 3487, 0), 
			new Tile(3082, 3484, 0), new Tile(3079, 3480, 0), new Tile(3079, 3475, 0), 
			new Tile(3078, 3470, 0), new Tile(3081, 3466, 0), new Tile(3086, 3465, 0), 
			new Tile(3087, 3460, 0), new Tile(3090, 3456, 0), new Tile(3093, 3452, 0), 
			new Tile(3096, 3448, 0), new Tile(3097, 3443, 0), new Tile(3099, 3438, 0), 
			new Tile(3100, 3437, 0), new Tile(3103, 3433, 0), new Tile(3105, 3431, 0) };
    public static  Tile[] EDGEVILLAGE_FISHTOBANK = new Tile[] { new Tile(3106, 3433, 0), new Tile(3101, 3433, 0), new Tile(3098, 3437, 0), 
			new Tile(3097, 3442, 0), new Tile(3095, 3447, 0), new Tile(3094, 3452, 0), 
			new Tile(3093, 3457, 0), new Tile(3094, 3462, 0), new Tile(3091, 3466, 0), 
			new Tile(3086, 3466, 0), new Tile(3081, 3466, 0), new Tile(3078, 3470, 0), 
			new Tile(3078, 3475, 0), new Tile(3079, 3480, 0), new Tile(3082, 3484, 0), 
			new Tile(3086, 3487, 0), new Tile(3091, 3489, 0), new Tile(3094, 3490, 0) };
    public static Area CATH_FISHAREA = new Area(new Tile[] { new Tile(2831, 3438, 0), new Tile(2830, 3418, 0), new Tile(2864, 3420, 0), 
			new Tile(2859, 3435, 0) });
    public static Area CATH_BANKAREA = new Area(new Tile[] { new Tile(2812, 3446, 0), new Tile(2804, 3446, 0), new Tile(2804, 3437, 0), 
			new Tile(2812, 3437, 0) });
    public static Tile[] CATH_PATHTOFISH = new Tile[] { new Tile(2808, 3441, 0), new Tile(2808, 3436, 0), new Tile(2811, 3436, 0), 
			new Tile(2816, 3435, 0), new Tile(2821, 3435, 0), new Tile(2826, 3435, 0), 
			new Tile(2831, 3435, 0), new Tile(2836, 3435, 0), new Tile(2839, 3433, 0) };
    public static Tile[] CATH_PATHTOBANK = new Tile[] { new Tile(2859, 3428, 0), new Tile(2855, 3428, 0), new Tile(2850, 3428, 0), 
			new Tile(2846, 3431, 0), new Tile(2841, 3432, 0), new Tile(2836, 3434, 0), 
			new Tile(2832, 3435, 0), new Tile(2827, 3436, 0), new Tile(2820, 3436, 0), 
			new Tile(2812, 3436, 0), new Tile(2808, 3437, 0), new Tile(2808, 3441, 0) };
    public static Area FISHING_GUILD_BANKAREA = new Area(new Tile[] { new Tile(2587, 3425, 0), new Tile(2581, 3425, 0), new Tile(2581, 3417, 0), 
			new Tile(2587, 3417, 0) });
    public static Area FISHING_GUILD_FISHAREA  = new Area(new Tile[] { new Tile(2593, 3427, 0), new Tile(2594, 3402, 0), new Tile(2619, 3402, 0), 
			new Tile(2615, 3427, 0) });
    public static Tile[] FISHINg_GUILD_PATHTOFISH = new Tile[] { new Tile(2584, 3421, 0), new Tile(2589, 3421, 0), new Tile(2594, 3420, 0), 
			new Tile(2599, 3420, 0), new Tile(2603, 3420, 0) };
    public static Tile[] FISHINg_GUILD_PATHTOBANK= new Tile[] { new Tile(2603, 3420, 0), new Tile(2598, 3420, 0), new Tile(2593, 3419, 0), 
			new Tile(2588, 3421, 0), new Tile(2584, 3422, 0) };
}


