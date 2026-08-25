package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

class Menu_InGame_CivInfo_Stats_Decisions$20 extends Button_Diplomacy_Action {
   final Menu_InGame_CivInfo_Stats_Decisions this$0;

   Menu_InGame_CivInfo_Stats_Decisions$20(
      Menu_InGame_CivInfo_Stats_Decisions var1, int var2, String var3, int var4, int var5, int var6, int var7, int var8, boolean var9
   ) {
      super(var2, var3, var4, var5, var6, var7, var8, var9);
      this.this$0 = var1;
   }

   @Override
   protected void actionElement(int var1) {
      CFG.menuManager.rebuildInGame_DeclarationOfIndependence8(CFG.getActiveCivInfo());
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   protected void buildElementHover() {
      try {
         new ArrayList();
         new ArrayList();
         CFG.game
            .getCivTruce(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID());
      } catch (IndexOutOfBoundsException var3) {
         this.menuElementHover = null;
         return;
      }

      try {
         this.menuElementHover = null;
      } catch (IndexOutOfBoundsException var2) {
         this.menuElementHover = null;
      }
   }
}
