package megascripts.dungoneering.boss;

import megascripts.graphic.LogHandler;

import org.powerbot.game.api.methods.interactive.NPCs;

import org.powerbot.game.api.wrappers.interactive.NPC;


public class CurrentBoss {
    static int SpeiclAt; //shadow forger
    static int BookBarrage; // goblin
    public static String BossName = "No Boss";

    public static void GetCurrentBossTactic() {
        for (NPC BossCheck : NPCs.getLoaded()) {
            if (BossCheck != null) {
                if (BossCheck.getId() != 11226) {
                    if (BossCheck.getName().contains("Gluttonous behemoth")) {
                      Gluttonousbehemoth.KillGluttonousbehemoth();
                    } else if (BossCheck.getName().contains("Rammer")) {
                        Rammernaut.Kill_Rammernaut();
                        
                    } else if (BossCheck.getName().contains("Astea Frostweb")) {
                        AsteaFrostweb.Kill_Astea_Frostweb();
                    } else if (BossCheck.getName().contains("Luminescent")) {
                        LuminescentIcefiend.Kill_LuminescentIcefiend();
                    } else if (BossCheck.getName().contains("Icy Bones")) {
                        IcyBones.Kill_Icy_Bones();
                    } else if (BossCheck.getName().contains("Bloodchiller")) {
                        TokashTheBloodchiller.Kill_Tokash_The_Bloodchiller();
                    } else if (BossCheck.getName().contains("Har'Lakk the Riftsplitter")) {
         
                    } else if (BossCheck.getName().contains("Hobgoblin Geomancer")) {
                        HobgoblinGeomancer.Kill_Hobgoblin_Geomancer();
                    } else if (BossCheck.getName().contains("Sagittare")) {
                        Sagittare.Kill_Sagittare();
                    } else if (BossCheck.getName().contains("Bulwark beast")) {
                        BulwarkBeast.Kill_Bulwark_Beast();
                    } else if (BossCheck.getName().contains("Unholy cursebearer")) {
                    	UnholyCursebearer.Kill_Unholy_Cursebearer();
                    } else if (BossCheck.getName().contains("Bal'lak the Pummeller")) {
              
                    } else if (BossCheck.getName().contains("Stomp")) {
                        Stomp.Kill();
                    } else if (BossCheck.getName().contains("Lexicus Runewright")) {
                        LexicusRunewright.Kill();
                    } else if (BossCheck.getName().contains("Night-gazer Khighorahk")) {
                        NightGazer.Kill();
                    } else if (BossCheck.getName().contains("Plane-freezer Lakhrahnaz")) {
                    	BossName = "Plane-freezer Lakhrahnaz";
                    	Planefreezer.Kill();
                    }else if(BossCheck.getName().contains("Shadow-Forger Ihlakhizan")){
                       Shadow.Kill();
                    }else if(BossCheck.getName().contains("Dread")){
                       Dreadnaut.Kill_Dreadnaut();
                    }else if (BossCheck.getName().contains("Flesh-Spoiler")) {
                    	FleshSpoiler.Kill();
                    }else if(BossCheck.getName().contains("Runebound")){
                    	Runebound.Kill();
                    }else if(BossCheck.getName().contains("Hope")){
                    	HopeDevourer.Kill();
                    }else if(BossCheck.getName().contains("Horde")){
                    	SkeletalHorde.Kill();
					}else if(BossCheck.getName().contains("Gulega")){
						WarpedGulega.Kill();
					}
				}
			}
		}

	}
}