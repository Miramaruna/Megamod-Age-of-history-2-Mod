package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class TradeRequest_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iCivLEFT = 0;
   public int iCivRIGHT = 0;
   public TradeRequest_List listLEFT = new TradeRequest_List();
   public TradeRequest_List listRight = new TradeRequest_List();

   TradeRequest_GameData() {
   }

   public final boolean canBeSend() {
      return this.listLEFT.iGold > 0
         || this.listRight.iGold > 0
         || this.listLEFT.lProvinces.size() > 0
         || this.listRight.lProvinces.size() > 0
         || this.listLEFT.iDeclarWarOnCivID > 0
         || this.listRight.iDeclarWarOnCivID > 0
         || this.listLEFT.iFormCoalitionAgainst > 0
         || this.listRight.iFormCoalitionAgainst > 0
         || this.listLEFT.defensivePact
         || this.listRight.defensivePact
         || this.listLEFT.nonAggressionPact
         || this.listRight.nonAggressionPact
         || this.listLEFT.proclaimIndependence
         || this.listRight.proclaimIndependence
         || this.listLEFT.militaryAccess
         || this.listRight.militaryAccess;
   }
}
