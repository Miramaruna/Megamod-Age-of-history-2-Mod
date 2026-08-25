package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_SocialPolicies extends SliderMenu {
   public Menu_InGame_SocialPolicies() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      String[] tMigrationOptions = new String[]{"SP_OpenDoor", "SP_Neutral", "SP_ClosedBorders"};
      String[] tTaxOptions = new String[]{"SP_Lenient", "SP_Equal", "SP_Tribute"};
      String[] tNuclearOptions = new String[]{"SP_Standard", "SP_FirstStrike", "SP_Deterrence"};
      int[] tCurrentValues = new int[]{AI_Assistant.MIGRATION_POLICY, AI_Assistant.MINORITY_TAX, AI_Assistant.NUCLEAR_DOCTRINE};
      String[][] tOptionLists = new String[][]{tMigrationOptions, tTaxOptions, tNuclearOptions};
      String[] tRowLabels = new String[]{"SP_Migration", "SP_MinorityTax", "SP_Nuclear"};
      String[] tRowInfos = new String[]{"SP_Migration_Info", "SP_Tax_Info", "SP_Nuclear_Info"};

      for (int i = 0; i < 3; i++) {
         final int tIndex = i;
         menuElements.add(
            new Button_FlagActionSliderStyle(
               CFG.langManager.get(tRowLabels[i]) + ": " + CFG.langManager.get(tOptionLists[i][tCurrentValues[i]]),
               -1,
               2 + CFG.PADDING,
               tY,
               tempWidth - CFG.PADDING * 2,
               CFG.TEXT_HEIGHT + CFG.PADDING * 4,
               true
            ) {
               @Override
               public void actionElement(int iID) {
                  switch (tIndex) {
                     case 0:
                        AI_Assistant.MIGRATION_POLICY = (AI_Assistant.MIGRATION_POLICY + 1) % 3;
                        break;
                     case 1:
                        AI_Assistant.MINORITY_TAX = (AI_Assistant.MINORITY_TAX + 1) % 3;
                        break;
                     case 2:
                        AI_Assistant.NUCLEAR_DOCTRINE = (AI_Assistant.NUCLEAR_DOCTRINE + 1) % 3;
                        break;
                  }

                  CFG.menuManager.rebuildInGame_SocialPolicies();
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(tRowInfos[tIndex]), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SP_ClickToCycle")));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }

               @Override
               public boolean getClickable() {
                  return true;
               }

               @Override
               public int getSFX() {
                  return SoundsManager.getSend();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 3 / 2;
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("SocialPolicies_Title"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         Math.max((CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 5, tY + CFG.PADDING),
         menuElements,
         false,
         true
      );
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
