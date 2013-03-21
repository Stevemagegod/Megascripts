package megascripts.dungoneering.puzzle;

import megascripts.dungoneering.node.Room_Job;

public class CurrentPuzzle {

   public static boolean TherePuzzle(){
	
	   return CrystalPower.UnCompleted() || SlidingPuzzle.UnCompleted() || Monolith.UnCompleted() || FallowTheLeader.UnCompleted() || LineStatues.UnCompleted() || WhinchBridge.UnCompleted() || ColoredFurret.UnCompleted() ||  FlipTiles.UnCompleted() || GrooveSpike.UnCompleted() || Pondskaters.UnCompleted() || Fishingferret.UnCompleted() || Magicalconstruct.UnCompleted()  || ThreeWeapionStatus.IsInComplete() || Leavers.UnCompleted();
   }

	public static void solve() {
		try {
			Room_Job.Eat();
			if(CrystalPower.UnCompleted()){
				CrystalPower.solve();
			} else if (SlidingPuzzle.UnCompleted()) {
				SlidingPuzzle.solve();
			}else if(Monolith.UnCompleted()){
				Monolith.solve();
			}else if(FallowTheLeader.UnCompleted()){
				FallowTheLeader.solve();
			}else if(LineStatues.UnCompleted()){
				LineStatues.solve();
			}else if(WhinchBridge.UnCompleted()){
				WhinchBridge.solve();
			}else if(ColoredFurret.UnCompleted()){
				ColoredFurret.solve();
			}else if (FlipTiles.UnCompleted()) {
				FlipTiles.solve();
			} else if (GrooveSpike.UnCompleted()) {
				GrooveSpike.solve();
			}else if(Pondskaters.UnCompleted()){
				Pondskaters.solve();
			}else if(Fishingferret.UnCompleted()){
				Fishingferret.solve();
			}else if (Magicalconstruct.UnCompleted()) {
				Magicalconstruct.solve();
			}else if(ThreeWeapionStatus.IsInComplete()){
				ThreeWeapionStatus.solve();
			}else if(Leavers.UnCompleted()){
				Leavers.solve();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}
