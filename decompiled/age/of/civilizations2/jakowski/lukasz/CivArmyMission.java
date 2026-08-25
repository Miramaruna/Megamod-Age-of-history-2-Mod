package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class CivArmyMission implements Serializable {
   public static final long serialVersionUID = 0L;
   public CivArmyMission_Type MISSION_TYPE = CivArmyMission_Type.PREAPARE_FOR_WAR;
   public int iProvinceID;
   public int toProvinceID;
   public int iArmy = 0;
   public int MISSION_ID = 0;
   public int iObsolate = 100;
   public int TURN_ID = 0;

   CivArmyMission() {
   }

   public boolean canMakeAction(int nCivID, int iTurnsLeft) {
      return true;
   }

   public boolean action(int nCivID) {
      return true;
   }

   public boolean prepareData() {
      return true;
   }

   public void onRemove() {
   }
}
