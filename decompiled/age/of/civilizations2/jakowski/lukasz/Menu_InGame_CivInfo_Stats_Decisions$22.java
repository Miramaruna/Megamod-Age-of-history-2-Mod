package age.of.civilizations2.jakowski.lukasz;

class Menu_InGame_CivInfo_Stats_Decisions$22 extends Button_Diplomacy_Action {
   Menu_InGame_CivInfo_Stats_Decisions$22(
      Menu_InGame_CivInfo_Stats_Decisions var1, int var2, String var3, int var4, int var5, int var6, int var7, int var8, boolean var9
   ) {
      super(var2, var3, var4, var5, var6, var7, var8, var9);
      this.this$0 = var1;
   }

   @Override
   protected void actionElement(int var1) {
      CFG.menuManager.rebuildInGame_SupportRebels(CFG.getActiveCivInfo(), -1);
   }

   @Override
   protected void buildElementHover() {
   }

   @Override
   protected boolean getClickable() {
      if (super.getClickable()
         && CFG.game.getActiveProvinceID() >= 0
         && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTrueOwnerOfProvince() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
         && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
         && CFG.game.getActiveProvinceID() != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()
         && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0
         && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCivID()
            != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
         && !CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar()) {
      }

      return true;
   }
}
