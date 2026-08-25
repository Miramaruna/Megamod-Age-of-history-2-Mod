package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_2 implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Save_Civ_GameData> lCivsData = new ArrayList<>();

   public final void buildData() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         this.lCivsData.add(CFG.game.getCiv(i).civGameData);
      }
   }
}
