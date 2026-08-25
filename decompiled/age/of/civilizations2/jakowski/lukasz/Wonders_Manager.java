package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Wonders_Manager {
   public List<Wonder> lWonders = null;
   public List<Integer> wonders_Provinces = new ArrayList<>();

   Wonders_Manager() {
   }

   public final void buildWondersProvinceID() {
      this.wonders_Provinces.clear();
      int iSize = this.lWonders.size();

      for (int i = 0; i < iSize; i++) {
         for (int j = 0; j < CFG.game.getProvincesSize(); j++) {
            if (CFG.game.getProvince(j).getMinX() <= this.lWonders.get(i).iPosX * CFG.map.getMapBG().getMapScale()
               && CFG.game.getProvince(j).getMaxX() >= this.lWonders.get(i).iPosX * CFG.map.getMapBG().getMapScale()
               && CFG.game.getProvince(j).getMinY() <= this.lWonders.get(i).iPosY * CFG.map.getMapBG().getMapScale()
               && CFG.game.getProvince(j).getMaxY() >= this.lWonders.get(i).iPosY * CFG.map.getMapBG().getMapScale()
               && CFG.game
                  .pathContains(j, this.lWonders.get(i).iPosX * CFG.map.getMapBG().getMapScale(), this.lWonders.get(i).iPosY * CFG.map.getMapBG().getMapScale())
               )
             {
               CFG.game.getProvince(j).addWonder(this.lWonders.get(i));
               this.wonders_Provinces.add(j);
               break;
            }
         }
      }

      this.lWonders.clear();
      this.lWonders = null;
   }
}
