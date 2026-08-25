package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_CivDiplo_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Save_CivDiploInfo_GameData> lNonAggressionPacts = new ArrayList<>();
   public List<Save_CivDiploInfo_GameData> lTruce = new ArrayList<>();
   public List<Save_CivDiploInfo_GameData> lDefensivePact = new ArrayList<>();
   public List<Save_CivDiploInfo_GameData> lGuarantee = new ArrayList<>();
   public List<Save_CivDiploInfo_GameData> lMilitirayAccess = new ArrayList<>();

   public Save_CivDiplo_GameData(int nCivID) {
      for (int i = 0; i < CFG.game.getCiv(nCivID).lOpt_NonAggressionPact.size(); i++) {
         this.lNonAggressionPacts
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_NonAggressionPact.get(i),
                  CFG.game.getCiv(nCivID).getNonAggressionPact(CFG.game.getCiv(nCivID).lOpt_NonAggressionPact.get(i))
               )
            );
      }

      for (int var3 = 0; var3 < CFG.game.getCiv(nCivID).lOpt_Truce.size(); var3++) {
         this.lTruce
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_Truce.get(var3), CFG.game.getCiv(nCivID).getTruce(CFG.game.getCiv(nCivID).lOpt_Truce.get(var3))
               )
            );
      }

      for (int var4 = 0; var4 < CFG.game.getCiv(nCivID).lOpt_DefensivePact.size(); var4++) {
         this.lDefensivePact
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_DefensivePact.get(var4),
                  CFG.game.getCiv(nCivID).getDefensivePact(CFG.game.getCiv(nCivID).lOpt_DefensivePact.get(var4))
               )
            );
      }

      for (int var5 = 0; var5 < CFG.game.getCiv(nCivID).lOpt_Guarantee.size(); var5++) {
         this.lGuarantee
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_Guarantee.get(var5), CFG.game.getCiv(nCivID).getGuarantee(CFG.game.getCiv(nCivID).lOpt_Guarantee.get(var5))
               )
            );
      }

      for (int var6 = 0; var6 < CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.size(); var6++) {
         this.lMilitirayAccess
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(var6),
                  CFG.game.getCiv(nCivID).getMilitaryAccess(CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(var6))
               )
            );
      }
   }
}
