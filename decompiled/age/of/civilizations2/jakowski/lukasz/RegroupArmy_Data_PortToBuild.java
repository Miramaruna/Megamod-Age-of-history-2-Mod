package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class RegroupArmy_Data_PortToBuild extends RegroupArmy_Data {
   public RegroupArmy_Data_PortToBuild(int nCivID, int fromProvinceID, int toProvinceID) {
      super(nCivID, fromProvinceID, toProvinceID);
   }

   @Override
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

         if (!CFG.game.getProvince(fromProvinceID).getSeaProvince()) {
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

   @Override
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

         if (!CFG.game.getProvince(in.get(var14)).getSeaProvince()) {
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
}
