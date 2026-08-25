package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

class Menu_InGame_CivInfo_Stats_Decisions$21 extends Button_Diplomacy_Action {
   final Menu_InGame_CivInfo_Stats_Decisions this$0;

   Menu_InGame_CivInfo_Stats_Decisions$21(
      Menu_InGame_CivInfo_Stats_Decisions var1, int var2, String var3, int var4, int var5, int var6, int var7, int var8, boolean var9
   ) {
      super(var2, var3, var4, var5, var6, var7, var8, var9);
      this.this$0 = var1;
   }

   @Override
   protected void actionElement(int var1) {
      CFG.menuManager.rebuildInGame_TakeResources(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
   }

   @Override
   protected void buildElementHover() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      var2.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 0, CFG.PADDING));
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TakeResources"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_loan, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      StringBuilder var3 = new StringBuilder();
      var3.append(CFG.langManager.get("MovementPoints"));
      var3.append(": ");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString()));
      var2.add(new MenuElement_Hover_v2_Element_Type_Text("-0.4", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      this.menuElementHover = new MenuElement_Hover_v2(var1);
   }
}
