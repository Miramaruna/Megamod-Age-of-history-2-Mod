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
      int playerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
      int[] tImages = new int[]{Images.diplo_war, Images.defensive_position, Images.act_plunder};
      boolean tRow = false;

      for (int i = 0; i < tKeys.length; i++) {
         final int tIndex = i;
         boolean tOn = aiGetFlag(tIndex);
         ArrayList<Integer> nValues = new ArrayList<>();
         ArrayList<Integer> nCivs = new ArrayList<>();
         if (tOn) {
            nValues.add(1);
            nCivs.add(playerCivID);
            nValues.add(0);
            nCivs.add(0);
         } else {
            nValues.add(0);
            nCivs.add(playerCivID);
            nValues.add(1);
            nCivs.add(0);
         }
         menuElements.add(
            new Graph_Circle_UpgradingArmy(
               false,
               tImages[i % tImages.length],
               tRow,
               CFG.langManager.get(tKeys[i]),
               CFG.COLOR_INGAME_DIPLOMACY_POINTS,
               2 + CFG.PADDING,
               tY,
               nValues,
               nCivs,
               playerCivID,
               CFG.langManager.get(tOn ? "AI_Set_On" : "AI_Set_Off"),
               "",
               ""
            ) {
               @Override
               public int getWidth() {
                  return tempWidth - CFG.PADDING * 4;
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
               public void actionElement(int iID) {
                  aiSetFlag(tIndex, !aiGetFlag(tIndex));
                  CFG.toast.setInView(
                     CFG.langManager.get(tKeys[tIndex]) + ": " + CFG.langManager.get(aiGetFlag(tIndex) ? "AI_Set_On" : "AI_Set_Off"),
                     aiGetFlag(tIndex) ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  );
                  CFG.toast.setTimeInView(2500);
                  CFG.menuManager.rebuildInGame_AIAssistantSettings();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
         tRow = !tRow;
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
