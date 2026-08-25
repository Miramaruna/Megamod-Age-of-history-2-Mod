package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TradeRequest_List implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iGold = 0;
   public List<Integer> lProvinces = new ArrayList<>();
   public int iDeclarWarOnCivID = -1;
   public int iFormCoalitionAgainst = -1;
   public boolean defensivePact = false;
   public boolean nonAggressionPact = false;
   public boolean proclaimIndependence = false;
   public boolean militaryAccess = false;

   TradeRequest_List() {
   }
}
