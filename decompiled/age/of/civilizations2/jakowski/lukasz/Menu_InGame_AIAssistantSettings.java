package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_AIAssistantSettings extends SliderMenu {
   public Menu_InGame_AIAssistantSettings() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      String[] tKeys = new String[]{
         "AI_Set_Budget",
         "AI_Set_GovChange",
         "AI_Set_Assimilation",
         "AI_Set_Colonization",
         "AI_Set_DiplomacyResponse",
         "AI_Set_DiplomacyActions"
      };
      int tW2 = (tempWidth - CFG.PADDING * 6) / 2;

      for (int i = 0; i < tKeys.length; i++) {
         final int tIndex = i;
         menuElements.add(
            new Button_FlagActionSliderStyle(CFG.langManager.get(tKeys[i]), -1, 2 + CFG.PADDING, tY, tempWidth - CFG.PADDING * 4, false) {
               @Override
               public Color getColor(boolean isActive) {
                  return CFG.COLOR_INGAME_DIPLOMACY_POINTS;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING / 2;

         for (int j = 0; j < 2; j++) {
            final int tSlot = j;
            final int tPosXFinal = CFG.PADDING * 2 + j * (tW2 + CFG.PADDING * 2);
            menuElements.add(
               new Button_EconomicPolitic(
                  CFG.langManager.get(tSlot == 0 ? "AI_Set_On" : "AI_Set_Off"),
                  -1,
                  tPosXFinal,
                  tY,
                  tW2,
                  true,
                  tSlot
               ) {
                  @Override
                  public Color getColor(boolean isActive) {
                     boolean tAllowed = aiGetFlag(tIndex);
                     boolean tActive = tSlot == 0 ? tAllowed : !tAllowed;
                     return tActive
                        ? (tSlot == 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
                        : (
                           this.getIsHovered()
                              ? CFG.COLOR_TEXT_CIV_INFO_HOVER
                              : new Color(CFG.COLOR_TEXT_CIV_INFO.r, CFG.COLOR_TEXT_CIV_INFO.g, CFG.COLOR_TEXT_CIV_INFO.b, 0.65F)
                        );
                  }

                  @Override
                  public void buildElementHover() {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AI_Settings_Info")));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(tKeys[tIndex])));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  @Override
                  public void actionElement(int iIDX) {
                     aiSetFlag(tIndex, tSlot == 0);
                     CFG.toast.setInView(
                        CFG.langManager.get(tKeys[tIndex]) + ": " + CFG.langManager.get(tSlot == 0 ? "AI_Set_On" : "AI_Set_Off"),
                        tSlot == 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     );
                     CFG.toast.setTimeInView(2500);
                     CFG.menuManager.rebuildInGame_AIAssistantSettings();
                  }
               }
            );
         }

         tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 3 / 2;
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("AI_Settings_Title"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         Math.max((CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 5, tY + CFG.PADDING),
         menuElements,
         false,
         true
      );
   }

   private static final boolean aiGetFlag(int nIndex) {
      switch (nIndex) {
         case 0:
            return AI_Assistant.ALLOW_BUDGET;
         case 1:
            return AI_Assistant.ALLOW_GOV_CHANGE;
         case 2:
            return AI_Assistant.ALLOW_ASSIMILATION;
         case 3:
            return AI_Assistant.ALLOW_COLONIZATION;
         case 4:
            return AI_Assistant.ALLOW_DIPLOMACY_RESPONSE;
         default:
            return AI_Assistant.ALLOW_DIPLOMACY_ACTIONS;
      }
   }

   private static final void aiSetFlag(int nIndex, boolean nValue) {
      switch (nIndex) {
         case 0:
            AI_Assistant.ALLOW_BUDGET = nValue;
            break;
         case 1:
            AI_Assistant.ALLOW_GOV_CHANGE = nValue;
            break;
         case 2:
            AI_Assistant.ALLOW_ASSIMILATION = nValue;
            break;
         case 3:
            AI_Assistant.ALLOW_COLONIZATION = nValue;
            break;
         case 4:
            AI_Assistant.ALLOW_DIPLOMACY_RESPONSE = nValue;
            break;
         default:
            AI_Assistant.ALLOW_DIPLOMACY_ACTIONS = nValue;
            break;
      }
   }

   public final int getElementW() {
      return (this.getWidth() - 4) / 2;
   }

   @Override
   public void actionElement(int iID) {
      if (iID >= 0 && iID < this.getMenuElementsSize()) {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }
}
