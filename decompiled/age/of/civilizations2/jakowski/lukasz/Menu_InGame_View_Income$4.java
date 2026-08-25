package age.of.civilizations2.jakowski.lukasz;

class Menu_InGame_View_Income$4 extends Button_View_Income {
   Menu_InGame_View_Income$4(Menu_InGame_View_Income var1, int var2, String var3, int var4, int var5, int var6, int var7, boolean var8) {
      super(var2, var3, var4, var5, var6, var7, var8);
      this.this$0 = var1;
   }

   @Override
   protected void actionElement(int var1) {
      if (CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.INPUT_ORDERS
         || CFG.SPECTATOR_MODE
         || this.getCurrent() != CFG.game.getActiveProvinceID()
         || CFG.game.getProvince(this.getCurrent()).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
         || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isInConstruction(this.getCurrent(), ConstructionType.Workshop2) > 0) {
         CFG.game.setActiveProvinceID(this.getCurrent());
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
         if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
            CFG.toast.setInView(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         }
      } else if (this.getCurrent() >= 0) {
         CFG.menuManager.rebuildInGame_BuildWorkshop2(this.getCurrent());
      }
   }
}
