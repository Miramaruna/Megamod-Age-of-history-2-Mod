package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

class Menu_InGame_FlagAction_Budget$19 extends Text_Investemnts_SliderDescGoods {
   final Menu_InGame_FlagAction_Budget this$0;

   Menu_InGame_FlagAction_Budget$19(Menu_InGame_FlagAction_Budget var1, String var2, String var3, String var4, int var5, int var6, int var7, int var8) {
      super(var2, var3, var4, var5, var6, var7, var8);
      this.this$0 = var1;
   }

   @Override
   protected void buildElementHover() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("hGoods"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("hGoods2")));
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      LanguageManager var3 = CFG.langManager;
      StringBuilder var4 = new StringBuilder();
      var4.append("");
      var4.append(
         (int)(
            CFG.ideologiesManager
                  .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                  .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               * 100.0F
         )
      );
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.get("hGoods3", var4.toString())));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("hGoods4")));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Space());
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      StringBuilder var5 = new StringBuilder();
      var5.append(CFG.langManager.get("BudgetSpendings"));
      var5.append(": ");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var5.toString()));
      StringBuilder var6 = new StringBuilder();
      var6.append("");
      var6.append((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Goods() * 100.0F));
      var6.append("%");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var6.toString(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      StringBuilder var7 = new StringBuilder();
      var7.append(CFG.langManager.get("Spendings"));
      var7.append(": ");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var7.toString()));
      StringBuilder var8 = new StringBuilder();
      var8.append("");
      var4 = new StringBuilder();
      var4.append("");
      var4.append(
         (int)CFG.game_NextTurnUpdate
            .getGoodsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).iBudget)
      );
      var8.append(CFG.getNumberWithSpaces(var4.toString()));
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var8.toString(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      this.menuElementHover = new MenuElement_Hover_v2(var1);
   }
}
