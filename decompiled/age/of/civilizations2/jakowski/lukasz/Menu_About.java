package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_About extends SliderMenu {
   public Menu_About() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = CFG.BUTTON_WIDTH / 2;
      menuElements.add(
         new Text_Scale("Age of Civilizations II", 0, CFG.BUTTON_WIDTH / 2, tY, 1.0F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void actionElement(int iID) {
               CFG.GO_TO_LINK = "http://www.AgeofCivilizationsGame.com";
               CFG.setDialogType(Dialog.GO_TO_LINK);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.AgeofCivilizationsGame.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.85F));
               } else if (this.getIsHovered()) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.925F));
               }

               ImageManager.getImage(Images.gameLogo).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
               oSB.setColor(Color.WHITE);
            }

            @Override
            public int getWidth() {
               return ImageManager.getImage(Images.gameLogo).getWidth();
            }

            @Override
            public int getHeight() {
               return ImageManager.getImage(Images.gameLogo).getHeight();
            }
         }
      );
      int var3;
      menuElements.add(
         new Text_Scale(
            "www.AgeofCivilizationsGame.com",
            0,
            CFG.BUTTON_WIDTH / 2,
            var3 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            0.9F
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void actionElement(int iID) {
               CFG.GO_TO_LINK = "http://www.AgeofCivilizationsGame.com";
               CFG.setDialogType(Dialog.GO_TO_LINK);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.AgeofCivilizationsGame.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      tY = var3 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
      int var5;
      menuElements.add(
         new Text_Scale("Programmer and Designer", 0, CFG.BUTTON_WIDTH / 2, var5 = tY + CFG.BUTTON_HEIGHT / 4, 1.0F) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("Developer", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void actionElement(int iID) {
               CFG.GO_TO_LINK = "http://www.LukaszJakowski.pl";
               CFG.setDialogType(Dialog.GO_TO_LINK);
            }
         }
      );
      menuElements.add(
         new Text_Scale(
            CFG.getLukaszJakowski(), 0, CFG.BUTTON_WIDTH / 2, tY = var5 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0.9F
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0F, 1.0F, 1.0F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804F, 0.11764706F, 0.23921569F, 1.0F), 0, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getLukaszJakowski(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0F, 1.0F, 1.0F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804F, 0.11764706F, 0.23921569F, 1.0F), 0, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("One man army"));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.LukaszJakowski.pl", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               CFG.GO_TO_LINK = "http://www.LukaszJakowski.pl";
               CFG.setDialogType(Dialog.GO_TO_LINK);
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               float fScale = CFG.TEXT_HEIGHT * 0.9F / ImageManager.getImage(Images.flag_rect).getHeight();
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + (int)(this.getTextWidth() * 0.9F) + CFG.PADDING + iTranslateX,
                     this.getPosY()
                        + 1
                        + (int)(this.getHeight() / 2.0F - CFG.TEXT_HEIGHT * 0.9F / 2.0F)
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * fScale),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * fScale)
                  );
               oSB.setColor(new Color(0.8509804F, 0.11764706F, 0.23921569F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + (int)(this.getTextWidth() * 0.9F) + CFG.PADDING + iTranslateX,
                     this.getPosY()
                        + 1
                        + (int)(this.getHeight() / 2.0F - CFG.TEXT_HEIGHT * 0.9F / 2.0F)
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        + (int)(ImageManager.getImage(Images.flag_rect).getHeight() * fScale) / 2
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * fScale),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * fScale)
                        - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * fScale) / 2
                  );
               oSB.setColor(Color.WHITE);
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX() + (int)(this.getTextWidth() * 0.9F) + CFG.PADDING + iTranslateX,
                     this.getPosY()
                        + 1
                        + (int)(this.getHeight() / 2.0F - CFG.TEXT_HEIGHT * 0.9F / 2.0F)
                        - ImageManager.getImage(Images.flag_rect).getHeight()
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * fScale),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * fScale)
                  );
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
      int var8;
      menuElements.add(
         new Text_Scale("Publisher", 0, CFG.BUTTON_WIDTH / 2, var8 = tY + CFG.BUTTON_HEIGHT / 4, 1.0F) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0F, 1.0F, 1.0F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804F, 0.11764706F, 0.23921569F, 1.0F), 0, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getLukaszJakowskiGames(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0F, 1.0F, 1.0F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804F, 0.11764706F, 0.23921569F, 1.0F), 0, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("Poland"));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.LukaszJakowski.pl", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void actionElement(int iID) {
               CFG.GO_TO_LINK = "http://www.LukaszJakowski.pl";
               CFG.setDialogType(Dialog.GO_TO_LINK);
            }
         }
      );
      menuElements.add(
         new Text_Scale(
            CFG.getLukaszJakowskiGames(), 0, CFG.BUTTON_WIDTH / 2, tY = var8 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0.9F
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0F, 1.0F, 1.0F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804F, 0.11764706F, 0.23921569F, 1.0F), 0, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getLukaszJakowskiGames(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0F, 1.0F, 1.0F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804F, 0.11764706F, 0.23921569F, 1.0F), 0, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("Poland"));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.LukaszJakowski.pl", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               CFG.GO_TO_LINK = "http://www.LukaszJakowski.pl";
               CFG.setDialogType(Dialog.GO_TO_LINK);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
      int var11;
      menuElements.add(
         new Text_Scale(CFG.langManager.get("Music"), 0, CFG.BUTTON_WIDTH / 2, var11 = tY + CFG.BUTTON_HEIGHT / 4, 1.0F) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Music"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      menuElements.add(
         new Text_Scale("Kevin Macleod", 0, CFG.BUTTON_WIDTH / 2, tY = var11 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0.9F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.23529412F, 0.23137255F, 0.43137255F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.69803923F, 0.13333334F, 0.20392157F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0F, 1.0F, 1.0F, 1.0F), 0, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("Kevin Macleod", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://www.youtube.com/user/kmmusic", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               CFG.GO_TO_LINK = "https://www.youtube.com/user/kmmusic";
               CFG.setDialogType(Dialog.GO_TO_LINK);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
      int var14;
      menuElements.add(
         new Text_Scale(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", 0, CFG.BUTTON_WIDTH / 2, var14 = tY + CFG.BUTTON_HEIGHT / 4, 0.9F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0F, 1.0F, 1.0F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804F, 0.11764706F, 0.23921569F, 1.0F), 0, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getLukaszJakowski(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0F, 1.0F, 1.0F, 1.0F), 0, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804F, 0.11764706F, 0.23921569F, 1.0F), 0, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("One man army"));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.LukaszJakowski.pl", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_Scale(
            "Twitter: @jakowskidev", 0, CFG.BUTTON_WIDTH / 2, tY = var14 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0.9F
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void actionElement(int iID) {
               CFG.GO_TO_LINK = "http://www.twitter.com/jakowskidev";
               CFG.setDialogType(Dialog.GO_TO_LINK);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.twitter.com/jakowskidev", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var16;
      menuElements.add(
         new Text_Scale("2016 - 2018", 0, CFG.BUTTON_WIDTH / 2, var16 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0.85F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      tY = var16 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
      int var18;
      menuElements.add(
         new Text_Scale("Special thanks to", 0, CFG.BUTTON_WIDTH / 2, var18 = tY + CFG.BUTTON_HEIGHT / 4, 0.8F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      menuElements.add(
         new Text_Scale("James Kerr", 0, CFG.BUTTON_WIDTH / 2, tY = var18 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0.75F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      int var20;
      menuElements.add(
         new Text_Scale("Marcin Jakowski", 0, CFG.BUTTON_WIDTH / 2, var20 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0.75F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      menuElements.add(
         new Text_Scale("Dementor", 0, CFG.BUTTON_WIDTH / 2, tY = var20 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0.75F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      int var22;
      menuElements.add(
         new Text_Scale("And you!", 0, CFG.BUTTON_WIDTH / 2, var22 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0.75F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void actionElement(int iID) {
               CFG.showKeyboard(iID);
            }
         }
      );
      tY = var22 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
      int var24;
      menuElements.add(
         new Text_Scale("Translators", 0, CFG.BUTTON_WIDTH / 2, var24 = tY + CFG.BUTTON_HEIGHT / 4, 0.8F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      menuElements.add(
         new Text_Scrollable(
            "Boaz Geurtsen, Alexis Perrin, Rodrigo Santana, Pavel Maurizio-Tudor, Karolis Griskus, Elliot, Juan José Vásquez Coronado, Vincent Desmeulles, Jacky Chiu, Vincenzo Marsala, Māris Ozols., Raiyuu, ADAM TRUNEČKA, Tien Nguyen, Denny Schmäcke, Mavel, Gabriele Licata, Vincent Vos, K.Laszlo, V.Bence, K.Tamás, Elias Nickel, Pane Peter Masahiro, Alvaro Huércano Rubens, Jakub Vlcek, Nikita Ukrainian, Kirill Martynyuk, Ilya Rusakov, Vyacheslav Chaschin, Demid Nagapetyan, Pane-Peter-Masahiro, Алекса Шушић",
            CFG.BUTTON_WIDTH / 2,
            tY = var24 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            (CFG.GAME_WIDTH - CFG.BUTTON_WIDTH) / 4,
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
      menuElements.add(new Button_Transparent(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true) {
         @Override
         public void actionElement(int iID) {
            Menu_About.this.onBackPressed();
         }
      });
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, iTranslateX, iTranslateY - ImageManager.getImage(Images.gradient).getHeight(), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT * 3 / 4);
      oSB.setColor(new Color(0.0123F, 0.0123F, 0.0123F, 0.3F));
      ImageManager.getImage(Images.patt_square)
         .draw(oSB, iTranslateX, iTranslateY - ImageManager.getImage(Images.patt_square).getHeight(), CFG.GAME_WIDTH, this.getHeight(), 0.0F, 0);
      oSB.setColor(Color.WHITE);
      CFG.drawLogo_Square(
         oSB,
         CFG.GAME_WIDTH - (CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 2) - CFG.BUTTON_WIDTH / 2 + iTranslateX,
         CFG.BUTTON_WIDTH / 2 + iTranslateY,
         CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 2
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAINMENU);
      CFG.menuManager.setBackAnimation(true);
   }
}
