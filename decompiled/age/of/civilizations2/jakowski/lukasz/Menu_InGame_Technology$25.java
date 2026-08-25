package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

class Menu_InGame_Technology$25 extends Button_FlagActionSliderStyle {
   Menu_InGame_Technology$25(
      Menu_InGame_Technology this$0, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable
   ) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
      this.this$0 = this$0;
   }

   @Override
   protected int getPosX() {
      return this.this$0.getElementW() * 2 - this.getWidth() - CFG.PADDING;
   }

   @Override
   protected void buildElementHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.this$0.iCivID, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Research") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("+2.25%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.this$0.iCivID, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Экономика") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("-2.25%", CFG.COLOR_TEXT_REVOLUTION_MAX));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected void actionElement(int iID) {
      SkillsManager.add_Education(this.this$0.iCivID);
      this.this$0.getMenuElement(0).setMin(CFG.game.getCiv(this.this$0.iCivID).civGameData.skills.getPointsLeft(this.this$0.iCivID));
      this.this$0.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv(this.this$0.iCivID).civGameData.skills.POINTS_Education);
      this.this$0.rebuildBudgetView();
      Menu_InGame.updateOverBudget();
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }
}
