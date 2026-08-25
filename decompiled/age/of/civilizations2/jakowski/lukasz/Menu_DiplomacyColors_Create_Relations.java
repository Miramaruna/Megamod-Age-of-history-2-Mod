package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_DiplomacyColors_Create_Relations extends SliderMenu {
   public Menu_DiplomacyColors_Create_Relations() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempPosX = CFG.PADDING;
      menuElements.add(
         new Button_Game("-100", -1, tempPosX, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var3;
      menuElements.add(
         new Button_Game("-90", -1, var3 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("-80", -1, tempPosX = var3 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var5;
      menuElements.add(
         new Button_Game("-70", -1, var5 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("-60", -1, tempPosX = var5 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var7;
      menuElements.add(
         new Button_Game("-50", -1, var7 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("-40", -1, tempPosX = var7 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var9;
      menuElements.add(
         new Button_Game("-30", -1, var9 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("-20", -1, tempPosX = var9 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var11;
      menuElements.add(
         new Button_Game("-10", -1, var11 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("NEUTRAL", -1, tempPosX = var11 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var13;
      menuElements.add(
         new Button_Game("10", -1, var13 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("20", -1, tempPosX = var13 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var15;
      menuElements.add(
         new Button_Game("30", -1, var15 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("40", -1, tempPosX = var15 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var17;
      menuElements.add(
         new Button_Game("50", -1, var17 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("60", -1, tempPosX = var17 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var19;
      menuElements.add(
         new Button_Game("70", -1, var19 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("80", -1, tempPosX = var19 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var21;
      menuElements.add(
         new Button_Game("90", -1, var21 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game("100", -1, tempPosX = var21 + CFG.PADDING + CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[9].getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[9].getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[9].getB(),
                     1.0F
                  )
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 2,
                     CFG.PADDING
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var23;
      menuElements.add(
         new Button_Transparent((var23 = tempPosX + CFG.PADDING + CFG.BUTTON_WIDTH) - CFG.PADDING, CFG.PADDING, CFG.PADDING, CFG.BUTTON_HEIGHT, false)
      );
      this.initMenu(null, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements);
      this.updateLanguage();
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R(
         oSB,
         iTranslateX,
         this.getPosY() + iTranslateY,
         this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth(),
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      CFG.menuManager.getColorPicker().setPosX(CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 5);
      CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 7);
      if (iID < 10) {
         CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = 9 - iID;
         CFG.menuManager
            .getColorPicker()
            .setActiveRGBColor(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9 - iID].getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9 - iID].getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9 - iID].getB()
            );
         CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.COLOR_DIPLOMACY_NEGATIVE);
      } else if (iID > 11) {
         CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = iID - 11;
         CFG.menuManager
            .getColorPicker()
            .setActiveRGBColor(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[iID - 11].getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[iID - 11].getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[iID - 11].getB()
            );
         CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.COLOR_DIPLOMACY_POSITIVE);
      } else {
         CFG.menuManager
            .getColorPicker()
            .setActiveRGBColor(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB()
            );
         CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.COLOR_DIPLOMACY_NEUTRAL);
      }
   }
}
