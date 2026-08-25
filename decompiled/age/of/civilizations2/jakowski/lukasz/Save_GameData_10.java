package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Save_GameData_10 implements Serializable {
   public static final long serialVersionUID = 0L;
   public HolyRomanEmpire_GameData holyRomanEmpire_GameData;

   public final void buildData() {
      this.holyRomanEmpire_GameData = CFG.holyRomanEmpire_Manager.getHRE();
   }
}
