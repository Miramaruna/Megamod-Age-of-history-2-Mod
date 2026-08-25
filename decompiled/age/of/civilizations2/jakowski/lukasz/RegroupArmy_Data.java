package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegroupArmy_Data implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iFromProvinceID;
   public List<Integer> lRoute = new ArrayList<>();
   public int iRouteSize = 0;
   public int iNumOfUnits = 0;
   public int iObsolate = 100;

   public RegroupArmy_Data(int nCivID, int fromProvinceID, int toProvinceID) {
      this.buildRoute(nCivID, fromProvinceID, toProvinceID);
   }

   public boolean continueMovingArmy(int nCivID) {
      return true;
   }

   public boolean buildRoute(int nCivID, int fromProvinceID, int toProvinceID) {
      this.lRoute.clear();
      this.iFromProvinceID = fromProvinceID;
      if (fromProvinceID < 0 || toProvinceID < 0 || CFG.game.getProvince(toProvinceID).getWasteland() >= 0) {
         return false;
      } else if (!CFG.game.getProvince(fromProvinceID).getSeaProvince()
         && CFG.game.getProvince(fromProvinceID).getNeighboringProvincesSize() == 0
         && CFG.game.getProvince(fromProvinceID).getLevelOfPort() <= 0) {
         return false;
      } else {
         ArrayList<Integer> was = new ArrayList<>();
         was.add(fromProvinceID);
         CFG.game.getProvince(fromProvinceID).was = true;
         ArrayList<Integer> in = new ArrayList<>();
         ArrayList<List<Integer>> inPath = new ArrayList<>();

         for (int i = 0; i < CFG.game.getProvince(fromProvinceID).getNeighboringProvincesSize(); i++) {
            if (canBeUsedInPath(nCivID, CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i), isFriendlyProvince(nCivID, toProvinceID), toProvinceID)
               )
             {
               in.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
               ArrayList<Integer> tP = new ArrayList<>();
               tP.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
               inPath.add(tP);
               was.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
               CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID()).was = true;
            }
         }

         if (!CFG.game.getProvince(fromProvinceID).getSeaProvince() && CFG.game.getProvince(fromProvinceID).getLevelOfPort() > 0) {
            for (int var10 = 0; var10 < CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvincesSize(); var10++) {
               in.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(var10)).getProvinceID());
               ArrayList<Integer> tP = new ArrayList<>();
               tP.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(var10)).getProvinceID());
               inPath.add(tP);
               was.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(var10)).getProvinceID());
               CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(var10)).getProvinceID()).was = true;
            }
         }

         this.buildPath(nCivID, was, in, inPath, fromProvinceID, toProvinceID);
         return true;
      }
   }

   public boolean buildPath(int nCivID, List<Integer> was, List<Integer> in, List<List<Integer>> inPath, int from, int lookingFor) {
      ArrayList<Integer> nIN = new ArrayList<>();
      ArrayList<List<Integer>> nINPath = new ArrayList<>();

      for (int i = 0; i < in.size(); i++) {
         if (CFG.game.getProvince(in.get(i)).getProvinceID() == lookingFor) {
            this.setPath(from, lookingFor, inPath.get(i), lookingFor);
            this.clearWas(was);
            return true;
         }
      }

      for (int var14 = 0; var14 < in.size(); var14++) {
         for (int j = 0; j < CFG.game.getProvince(in.get(var14)).getNeighboringProvincesSize(); j++) {
            if (canBeUsedInPath(
                  nCivID,
                  CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringProvinces(j)).getProvinceID(),
                  isFriendlyProvince(nCivID, lookingFor),
                  lookingFor
               )
               && !CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringProvinces(j)).getProvinceID()).was) {
               if (CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                  this.setPath(from, lookingFor, inPath.get(var14), lookingFor);
                  this.clearWas(was);
                  return true;
               }

               nIN.add(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringProvinces(j)).getProvinceID());
               ArrayList<Integer> tPL = new ArrayList<>();

               for (int u = 0; u < inPath.get(var14).size(); u++) {
                  tPL.add(inPath.get(var14).get(u));
               }

               tPL.add(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringProvinces(j)).getProvinceID());
               nINPath.add(tPL);
               CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringProvinces(j)).getProvinceID()).was = true;
               was.add(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringProvinces(j)).getProvinceID());
            }
         }

         if (!CFG.game.getProvince(in.get(var14)).getSeaProvince() && CFG.game.getProvince(in.get(var14)).getLevelOfPort() > 0) {
            for (int var17 = 0; var17 < CFG.game.getProvince(in.get(var14)).getNeighboringSeaProvincesSize(); var17++) {
               if (!CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringSeaProvinces(var17)).getProvinceID()).was) {
                  if (CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringSeaProvinces(var17)).getProvinceID() == lookingFor) {
                     this.setPath(from, lookingFor, inPath.get(var14), lookingFor);
                     this.clearWas(was);
                     return true;
                  }

                  nIN.add(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringSeaProvinces(var17)).getProvinceID());
                  ArrayList<Integer> tPL = new ArrayList<>();

                  for (int u = 0; u < inPath.get(var14).size(); u++) {
                     tPL.add(inPath.get(var14).get(u));
                  }

                  tPL.add(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringSeaProvinces(var17)).getProvinceID());
                  nINPath.add(tPL);
                  CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringSeaProvinces(var17)).getProvinceID()).was = true;
                  was.add(CFG.game.getProvince(CFG.game.getProvince(in.get(var14)).getNeighboringSeaProvinces(var17)).getProvinceID());
               }
            }
         }
      }

      try {
         return this.buildPath(nCivID, was, nIN, nINPath, from, lookingFor);
      } catch (StackOverflowError var13) {
         this.clearWas(was);
         return false;
      }
   }

   public final void clearWas(List<Integer> was) {
      for (int i = was.size() - 1; i >= 0; i--) {
         CFG.game.getProvince(was.get(i)).was = false;
      }
   }

   public final void setPath(int p1, int p2, List<Integer> lPath, int toProvinceID) {
      for (int i = 0; i < lPath.size(); i++) {
         this.lRoute.add(lPath.get(i));
      }

      if (toProvinceID != this.lRoute.get(this.lRoute.size() - 1)) {
         this.lRoute.add(toProvinceID);
      }

      this.iRouteSize = this.lRoute.size();
      this.iObsolate = Math.max(10, (int)(this.iRouteSize * 1.5F + 1.0F));
   }

   public static final boolean isFriendlyProvince(int nCivID, int toProvinceID) {
      return CFG.game.getProvince(toProvinceID).getCivID() == nCivID
         || CFG.game.getProvince(toProvinceID).getSeaProvince()
         || CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID() > 0
            && CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID()
         || CFG.game.getCiv(nCivID).getPuppetOfCivID() == CFG.game.getProvince(toProvinceID).getCivID()
         || CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() == nCivID
         || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(toProvinceID).getCivID()) > 0;
   }

   public static boolean canBeUsedInPath(int nCivID, int nProvinceID, boolean moveToFriendlyProvince, int toProvinceID) {
      if (CFG.game.getProvince(nProvinceID).getWasteland() >= 0) {
         return false;
      } else {
         return nCivID == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
               && CFG.FOG_OF_WAR == 2
               && !CFG.game.getProvince(nProvinceID).getSeaProvince()
               && nProvinceID != toProvinceID
               && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(nProvinceID)
            ? false
            : CFG.game.getProvince(nProvinceID).getCivID() == nCivID
               || CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == nCivID
               || CFG.game.getCiv(nCivID).getPuppetOfCivID() == CFG.game.getProvince(nProvinceID).getCivID()
               || !moveToFriendlyProvince
                  && CFG.game.getProvince(nProvinceID).getCivID() == 0
                  && !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES
                  && (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(nProvinceID))
               || CFG.game.getProvince(nProvinceID).getSeaProvince()
               || CFG.game.getCiv(nCivID).getAllianceID() > 0
                  && CFG.game.getCiv(nCivID).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID()
               || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(nProvinceID).getCivID()) > 0
               || !moveToFriendlyProvince && (int)CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getProvince(nProvinceID).getCivID()) == -100;
      }
   }

   public final int getFromProvinceID() {
      return this.iFromProvinceID;
   }

   public final void setFromProvinceID(int iFromProvinceID) {
      this.iFromProvinceID = iFromProvinceID;
   }

   public final int getNumOfUnits() {
      return this.iNumOfUnits;
   }

   public final void setNumOfUnits(int iNumOfUnits) {
      this.iNumOfUnits = iNumOfUnits;
   }

   public final int getRouteSize() {
      return this.iRouteSize;
   }

   public final int getRoute(int i) {
      return this.lRoute.get(i);
   }

   public final void removeRoute(int i) {
      this.lRoute.remove(i);
      this.iRouteSize = this.lRoute.size();
   }

   public final int getToProvinceID() {
      return this.lRoute.get(this.getRouteSize() - 1);
   }

   public final int getObsolate() {
      return this.iObsolate;
   }

   public final void updateObsolate() {
      this.iObsolate--;
   }
}
