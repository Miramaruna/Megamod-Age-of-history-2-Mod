package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Leader_Edit_Data extends SliderMenu {
   public String sName;
   public String sImage;
   public String sBorn;
   public String sWiki;

   public Menu_Leader_Edit_Data() {
      int tempW = (int)(CFG.CIV_INFO_MENU_WIDTH * 1.25F);
      int tempElemH = CFG.BUTTON_HEIGHT;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = CFG.PADDING;
      menuElements.add(
         new Button_New_Game_Players_Special(
            CFG.leader_GameData.getLeaderOfCiv().getName(), CFG.PADDING * 2, CFG.PADDING + 2, tY, tempW - CFG.PADDING * 2 - 2, true
         ) {
            @Override
            public String getTextToDraw() {
               return Menu_Leader_Edit_Data.this.sName + ": " + super.getText();
            }
         }
      );
      int var5;
      menuElements.add(
         new Button_New_Game_Players_Special(
            CFG.leader_GameData.getLeaderOfCiv().getImage(),
            CFG.PADDING * 2,
            CFG.PADDING + 2,
            var5 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2 - 2,
            true
         ) {
            @Override
            public String getTextToDraw() {
               return Menu_Leader_Edit_Data.this.sImage + ": " + super.getText();
            }
         }
      );
      menuElements.add(
         new Button_New_Game_Players_Special(
            CFG.leader_GameData.getLeaderOfCiv().getDay()
               + " "
               + Game_Calendar.getMonthName(CFG.leader_GameData.getLeaderOfCiv().getMonth())
               + " "
               + CFG.gameAges.getYear(CFG.leader_GameData.getLeaderOfCiv().getYear()),
            CFG.PADDING * 2,
            CFG.PADDING + 2,
            tY = var5 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2 - 2,
            true
         ) {
            @Override
            public String getTextToDraw() {
               return Menu_Leader_Edit_Data.this.sBorn + ": " + super.getText();
            }
         }
      );
      int var7;
      menuElements.add(
         new Button_New_Game_Players_Special(
            CFG.leader_GameData.getLeaderOfCiv().getWiki(),
            CFG.PADDING * 2,
            CFG.PADDING + 2,
            var7 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2 - 2,
            true
         ) {
            @Override
            public String getTextToDraw() {
               return Menu_Leader_Edit_Data.this.sWiki + ": " + super.getText();
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            tY = var7 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            tY,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            tY,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      int var9;
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            var9 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            var9,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            var9,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            tY = var9 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            tY,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            tY,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      int var11;
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            var11 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            var11,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            var11,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            tY = var11 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            tY,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            tY,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      int var13;
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            var13 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            var13,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            var13,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            tY = var13 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            tY,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            tY,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      int var15;
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            var15 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            var15,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            var15,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            tY = var15 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            tY,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            tY,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      int var17;
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            var17 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null,
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            var17,
            tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + (int)(CFG.BUTTON_HEIGHT * 0.8F) + (tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.8F) * 2),
            var17,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      tY = var17 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_Leader_Edit_Data.this.getPosX() - 2 + iTranslateX,
                     Menu_Leader_Edit_Data.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_Leader_Edit_Data.this.getWidth() + 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.425F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_Leader_Edit_Data.this.getPosX() + iTranslateX,
                     Menu_Leader_Edit_Data.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_Leader_Edit_Data.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_Leader_Edit_Data.this.getPosX() + iTranslateX,
                     Menu_Leader_Edit_Data.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING,
                     Menu_Leader_Edit_Data.this.getWidth(),
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_Leader_Edit_Data.this.getPosX() + iTranslateX,
                     Menu_Leader_Edit_Data.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_Leader_Edit_Data.this.getWidth()
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.9F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_Leader_Edit_Data.this.getPosX() + iTranslateX,
                     Menu_Leader_Edit_Data.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_Leader_Edit_Data.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.75F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.75F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.75F / 2.0F),
                  CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 4,
         tempW,
         Math.min(
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT - CFG.PADDING * 3
         ),
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sName = CFG.langManager.get("Name");
      this.sImage = CFG.langManager.get("ImageName");
      this.sBorn = CFG.langManager.get("Born");
      this.sWiki = CFG.langManager.get("Wiki");
      this.getTitle().setText(CFG.langManager.get("Leader"));
      this.getMenuElement(2)
         .setText(
            Game_Calendar.currentDay + " " + Game_Calendar.getMonthName(Game_Calendar.currentMonth) + " " + CFG.gameAges.getYear(Game_Calendar.currentYear)
         );
      this.getMenuElement(5)
         .setText(
            CFG.langManager.get("PopulationGrowthModifier")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_PopGrowth > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_PopGrowth * 100.0F)
               + "%"
         );
      this.getMenuElement(8)
         .setText(
            CFG.langManager.get("EconomyGrowthModifier")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_EconomyGrowth > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_EconomyGrowth * 100.0F)
               + "%"
         );
      this.getMenuElement(11)
         .setText(
            CFG.langManager.get("IncomeTaxation")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeTaxation > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeTaxation * 100.0F)
               + "%"
         );
      this.getMenuElement(14)
         .setText(
            CFG.langManager.get("IncomeProduction")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeProduction > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeProduction * 100.0F)
               + "%"
         );
      this.getMenuElement(17)
         .setText(
            CFG.langManager.get("Administration")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_Administration > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_Administration * 100.0F)
               + "%"
         );
      this.getMenuElement(20)
         .setText(
            CFG.langManager.get("Research")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_Research > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_Research * 100.0F)
               + "%"
         );
      this.getMenuElement(23)
         .setText(
            CFG.langManager.get("MilitaryUpkeep")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_MilitaryUpkeep > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_MilitaryUpkeep * 100.0F)
               + "%"
         );
      this.getMenuElement(26)
         .setText(
            CFG.langManager.get("AttackBonus")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_AttackBonus > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_AttackBonus * 100.0F)
               + "%"
         );
      this.getMenuElement(29)
         .setText(
            CFG.langManager.get("DefenseBonus")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_DefenseBonus > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_DefenseBonus * 100.0F)
               + "%"
         );
      this.getMenuElement(32)
         .setText(
            CFG.langManager.get("MovementPoints")
               + ": "
               + (CFG.leader_GameData.getLeaderOfCiv().fModifier_MovementPoints > 0.0F ? "+" : "")
               + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_MovementPoints * 100.0F)
               + "%"
         );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight(),
            true,
            true
         );
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.showKeyboard();
            return;
         case 1:
            CFG.showKeyboard();
            return;
         case 2:
            CFG.menuManager.saveLeader_Edit_Data();
            CFG.backToMenu = Menu.eGAME_LEADERS_EDIT;
            CFG.menuManager.setViewID(Menu.eSCENARIO_AGE);
            CFG.menuManager.updateSelecetScenarioAge_Slider();
            return;
         case 3:
            CFG.showKeyboard();
            return;
         case 4:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_PopGrowth -= 0.01F;
            this.updateLanguage();
            return;
         case 5:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 6:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_PopGrowth += 0.01F;
            this.updateLanguage();
            return;
         case 7:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_EconomyGrowth -= 0.01F;
            this.updateLanguage();
            return;
         case 8:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 9:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_EconomyGrowth += 0.01F;
            this.updateLanguage();
            return;
         case 10:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeTaxation -= 0.01F;
            this.updateLanguage();
            return;
         case 11:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 12:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeTaxation += 0.01F;
            this.updateLanguage();
            return;
         case 13:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeProduction -= 0.01F;
            this.updateLanguage();
            return;
         case 14:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 15:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeProduction += 0.01F;
            this.updateLanguage();
            return;
         case 16:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_Administration -= 0.01F;
            this.updateLanguage();
            return;
         case 17:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 18:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_Administration += 0.01F;
            this.updateLanguage();
            return;
         case 19:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_Research -= 0.01F;
            this.updateLanguage();
            return;
         case 20:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 21:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_Research += 0.01F;
            this.updateLanguage();
            return;
         case 22:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_MilitaryUpkeep -= 0.01F;
            this.updateLanguage();
            return;
         case 23:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 24:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_MilitaryUpkeep += 0.01F;
            this.updateLanguage();
            return;
         case 25:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_AttackBonus -= 0.01F;
            this.updateLanguage();
            return;
         case 26:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 27:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_AttackBonus += 0.01F;
            this.updateLanguage();
            return;
         case 28:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_DefenseBonus -= 0.01F;
            this.updateLanguage();
            return;
         case 29:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 30:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_DefenseBonus += 0.01F;
            this.updateLanguage();
            return;
         case 31:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_MovementPoints -= 0.01F;
            this.updateLanguage();
            return;
         case 32:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            return;
         case 33:
            CFG.leader_GameData.getLeaderOfCiv().fModifier_MovementPoints += 0.01F;
            this.updateLanguage();
            return;
      }
   }
}
