package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Build_All extends SliderMenu {
   public static int iLastProvince;
   public int iProvinceID;
   public static int provincesCount = 0;

   public Menu_InGame_Build_All() {
      this.iProvinceID = -1;
      ArrayList<Button_Flag_JustFrame> list = new ArrayList<>();
      int n = CFG.CIV_INFO_MENU_WIDTH * 2;
      list.add(new Button_Flag_JustFrame(CFG.PADDING, CFG.PADDING, true));
      list.get(list.size() - 1).getHeight();
      int n2 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle sliderMenuTitle = new SliderMenuTitle(CFG.langManager.get("BuildAll"), CFG.BUTTON_HEIGHT * 3 / 5, true, true);
      int n3 = CFG.GAME_WIDTH / 2;
      int n4 = n / 2;
      int max;
      if (list.get(list.size() - 1).getHeight() + list.get(list.size() - 1).getPosY() + CFG.PADDING + n2
         > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         max = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - n2, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         max = CFG.PADDING + list.get(list.size() - 1).getHeight() + list.get(list.size() - 1).getPosY();
      }

      this.initMenu(sliderMenuTitle, n3 - n4, n2, n, max, list, false, true);
      this.updateLanguage();
   }

   public void resetSelections() {
      BuildingsManager.fBuildFort = false;
      BuildingsManager.fBuildWatchTower = false;
      BuildingsManager.fBuildFarm = false;
      BuildingsManager.fBuildLibrary = false;
      BuildingsManager.fBuildWorkshop = false;
      BuildingsManager.fBuildArmoury = false;
      BuildingsManager.fBuildSupply = false;
      BuildingsManager.fBuildNuclearReactor = false;
      BuildingsManager.fBuildShelter = false;
      BuildingsManager.fBuildPort = false;
   }

   public Menu_InGame_Build_All(final int iProvinceID) {
      this.resetSelections();
      ArrayList<MenuElement> list = new ArrayList<>();
      this.iProvinceID = iProvinceID;
      iLastProvince = iProvinceID;
      int n = CFG.CIV_INFO_MENU_WIDTH * 2;
      int fort_BuildCost = BuildingsManager.getFort_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfFort() + 1, BuildingsManager.iBuildInProvinceID
      );
      int tower_BuildCost = BuildingsManager.getTower_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfWatchTower() + 1, BuildingsManager.iBuildInProvinceID
      );
      int var44 = BuildingsManager.getPort_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfPort() + 1, BuildingsManager.iBuildInProvinceID
      );
      int farm_BuildCost = BuildingsManager.getFarm_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfFarm() + 1, BuildingsManager.iBuildInProvinceID
      );
      int workshop_BuildCost = BuildingsManager.getWorkshop_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfWorkshop() + 1, BuildingsManager.iBuildInProvinceID
      );
      int library_BuildCost = BuildingsManager.getLibrary_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfLibrary() + 1, BuildingsManager.iBuildInProvinceID
      );
      int armoury_BuildCost = BuildingsManager.getArmoury_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfArmoury() + 1, BuildingsManager.iBuildInProvinceID
      );
      int supply_BuildCost = BuildingsManager.getSupply_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfSupply() + 1, BuildingsManager.iBuildInProvinceID
      );
      int nuclearReactor_BuildCost = BuildingsManager.getNuclearReactor_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfNuclearReactor() + 1, BuildingsManager.iBuildInProvinceID
      );
      int Shelter_BuildCost = BuildingsManager.getShelter_BuildCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfShelter() + 1, BuildingsManager.iBuildInProvinceID
      );
      int fort_BuildMovementCost = BuildingsManager.getFort_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfFort() + 1);
      int tower_BuildMovementCost = BuildingsManager.getTower_BuildMovementCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfWatchTower() + 1
      );
      int port_BuildMovementCost = BuildingsManager.getPort_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfPort() + 1);
      int farm_BuildMovementCost = BuildingsManager.getFarm_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfFarm() + 1);
      int workshop_BuildMovementCost = BuildingsManager.getWorkshop_BuildMovementCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfWorkshop() + 1
      );
      int library_BuildMovementCost = BuildingsManager.getLibrary_BuildMovementCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfLibrary() + 1
      );
      int armoury_BuildMovementCost = BuildingsManager.getArmoury_BuildMovementCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfArmoury() + 1
      );
      int supply_BuildMovementCost = BuildingsManager.getSupply_BuildMovementCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfSupply() + 1
      );
      int nuclearReactor_BuildMovementCost = BuildingsManager.getNuclearReactor_BuildMovementCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfNuclearReactor() + 1
      );
      int Shelter_BuildMovementCost = BuildingsManager.getShelter_BuildMovementCost(
         CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfShelter() + 1
      );
      int finalIProvinceID = var44;
      list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildFort"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, 0, CFG.BUTTON_WIDTH * 2) {
         @Override
         public void actionElement(int n) {
            if (!BuildingsManager.canBuildFort(iProvinceID)) {
               BuildingsManager.fBuildFort = false;
            } else {
               BuildingsManager.fBuildFort = !BuildingsManager.fBuildFort;
            }
         }

         @Override
         public boolean getCheckboxState() {
            return BuildingsManager.fBuildFort;
         }

         @Override
         public int getWidth() {
            return Menu_InGame_Build_All.this.getElementW() * 2;
         }
      });
      int n2 = 0 + list.get(list.size() - 1).getHeight();
      list.add(
         new Button_Diplomacy_Demand(CFG.langManager.get("fBuildWatchTower"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n2, CFG.BUTTON_WIDTH * 2) {
            @Override
            public void actionElement(int n) {
               if (!BuildingsManager.canBuildTower(iProvinceID)) {
                  BuildingsManager.fBuildWatchTower = false;
               } else {
                  BuildingsManager.fBuildWatchTower = !BuildingsManager.fBuildWatchTower;
               }
            }

            @Override
            public boolean getCheckboxState() {
               return BuildingsManager.fBuildWatchTower;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Build_All.this.getElementW() * 2;
            }
         }
      );
      int n3 = n2 + list.get(list.size() - 1).getHeight();
      list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildPort"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n3, CFG.BUTTON_WIDTH * 2) {
         @Override
         public void actionElement(int n) {
            if (!BuildingsManager.canBuildPort(iProvinceID)) {
               BuildingsManager.fBuildPort = false;
            } else {
               BuildingsManager.fBuildPort = !BuildingsManager.fBuildPort;
            }
         }

         @Override
         public boolean getCheckboxState() {
            return BuildingsManager.fBuildPort;
         }

         @Override
         public int getWidth() {
            return Menu_InGame_Build_All.this.getElementW() * 2;
         }
      });
      int n5 = n3 + list.get(list.size() - 1).getHeight();
      list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildFarm"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n5, CFG.BUTTON_WIDTH * 2) {
         @Override
         public void actionElement(int n) {
            if (!BuildingsManager.canBuildFarm(iProvinceID)) {
               BuildingsManager.fBuildFarm = false;
            } else {
               BuildingsManager.fBuildFarm = !BuildingsManager.fBuildFarm;
            }
         }

         @Override
         public boolean getCheckboxState() {
            return BuildingsManager.fBuildFarm;
         }

         @Override
         public int getWidth() {
            return Menu_InGame_Build_All.this.getElementW() * 2;
         }
      });
      int n6 = n5 + list.get(list.size() - 1).getHeight();
      list.add(
         new Button_Diplomacy_Demand(CFG.langManager.get("fBuildWorkshop"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n6, CFG.BUTTON_WIDTH * 2) {
            @Override
            public void actionElement(int n) {
               if (!BuildingsManager.canBuildWorkshop(iProvinceID)) {
                  BuildingsManager.fBuildWorkshop = false;
               } else {
                  BuildingsManager.fBuildWorkshop = !BuildingsManager.fBuildWorkshop;
               }
            }

            @Override
            public boolean getCheckboxState() {
               return BuildingsManager.fBuildWorkshop;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Build_All.this.getElementW() * 2;
            }
         }
      );
      int n7 = n6 + list.get(list.size() - 1).getHeight();
      list.add(
         new Button_Diplomacy_Demand(CFG.langManager.get("fBuildLibrary"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n7, CFG.BUTTON_WIDTH * 2) {
            @Override
            public void actionElement(int n) {
               if (!BuildingsManager.canBuildLibrary(iProvinceID)) {
                  BuildingsManager.fBuildLibrary = false;
               } else {
                  BuildingsManager.fBuildLibrary = !BuildingsManager.fBuildLibrary;
               }
            }

            @Override
            public boolean getCheckboxState() {
               return BuildingsManager.fBuildLibrary;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Build_All.this.getElementW() * 2;
            }
         }
      );
      int n8 = n7 + list.get(list.size() - 1).getHeight();
      list.add(
         new Button_Diplomacy_Demand(CFG.langManager.get("fBuildArmoury"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n8, CFG.BUTTON_WIDTH * 2) {
            @Override
            public void actionElement(int n) {
               if (!BuildingsManager.canBuildArmoury(iProvinceID)) {
                  BuildingsManager.fBuildArmoury = false;
               } else {
                  BuildingsManager.fBuildArmoury = !BuildingsManager.fBuildArmoury;
               }
            }

            @Override
            public boolean getCheckboxState() {
               return BuildingsManager.fBuildArmoury;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Build_All.this.getElementW() * 2;
            }
         }
      );
      int n9 = n8 + list.get(list.size() - 1).getHeight();
      list.add(
         new Button_Diplomacy_Demand(CFG.langManager.get("fBuildSupply"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n9, CFG.BUTTON_WIDTH * 2) {
            @Override
            public void actionElement(int n) {
               if (!BuildingsManager.canBuildSupply(iProvinceID)) {
                  BuildingsManager.fBuildSupply = false;
               } else {
                  BuildingsManager.fBuildSupply = !BuildingsManager.fBuildSupply;
               }
            }

            @Override
            public boolean getCheckboxState() {
               return BuildingsManager.fBuildSupply;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Build_All.this.getElementW() * 2;
            }
         }
      );
      int n10 = n9 + list.get(list.size() - 1).getHeight();
      list.add(
         new Button_Diplomacy_Demand(
            CFG.langManager.get("fBuildNuclearReactor"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n10, CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            public void actionElement(int n) {
               BuildingsManager.fBuildNuclearReactor = !BuildingsManager.fBuildNuclearReactor;
            }

            @Override
            public boolean getCheckboxState() {
               return BuildingsManager.fBuildNuclearReactor;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Build_All.this.getElementW() * 2;
            }
         }
      );
      int n11 = n10 + list.get(list.size() - 1).getHeight();
      list.add(
         new Button_Diplomacy_Demand(CFG.langManager.get("fBuildShelter"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n11, CFG.BUTTON_WIDTH * 2) {
            @Override
            public void actionElement(int n) {
               BuildingsManager.fBuildShelter = !BuildingsManager.fBuildShelter;
            }

            @Override
            public boolean getCheckboxState() {
               return BuildingsManager.fBuildShelter;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Build_All.this.getElementW() * 2;
            }
         }
      );
      int n12 = n11 + list.get(list.size() - 1).getHeight();
      String string = CFG.langManager.get("BuildAllIn") + ": ";
      String s;
      if (CFG.game.getProvince(this.iProvinceID).getName().length() > 0) {
         s = CFG.game.getProvince(this.iProvinceID).getName();
      } else {
         s = CFG.langManager.get("Province");
      }

      list.add(
         new Button_Build_Building(
            string,
            s,
            Images.economy,
            0
               + fort_BuildCost
               + tower_BuildCost
               + farm_BuildCost
               + workshop_BuildCost
               + library_BuildCost
               + armoury_BuildCost
               + supply_BuildCost
               + nuclearReactor_BuildCost
               + Shelter_BuildCost,
            0
               + fort_BuildMovementCost
               + tower_BuildMovementCost
               + port_BuildMovementCost
               + farm_BuildMovementCost
               + workshop_BuildMovementCost
               + library_BuildMovementCost
               + armoury_BuildMovementCost
               + supply_BuildMovementCost
               + nuclearReactor_BuildMovementCost
               + Shelter_BuildMovementCost,
            0,
            n12,
            CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> listx = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type_Text> list2 = new ArrayList<>();
               list2.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               listx.add(new MenuElement_Hover_v2_Element2(list2));
               list2.clear();
               this.menuElementHover = new MenuElement_Hover_v2(listx);
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Build_All.this.getElementW() * 2;
            }
         }
      );
      list.get(list.size() - 1).setMin((int)(BuildingsManager.getFarm_TechLevel(0) * 100.0F));
      int height = list.get(list.size() - 1).getHeight();
      String value = CFG.langManager.get("Cancel");
      iProvinceID = CFG.PADDING;
      int n13 = n12 + height + CFG.PADDING;
      list.add(new Button_FlagActionSliderStyle(value, -1, iProvinceID + 2, n13, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getPosX() {
            return CFG.PADDING;
         }

         @Override
         public int getWidth() {
            return Menu_InGame_Build_All.this.getWidth() / 3;
         }
      });
      list.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Provinces") + ": " + provincesCount, -1, iProvinceID + 2, n13, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getPosX() {
            return CFG.PADDING * 2 + Menu_InGame_Build_All.this.getWidth() / 3;
         }

         @Override
         public int getWidth() {
            return Menu_InGame_Build_All.this.getWidth() / 3 - CFG.PADDING * 3;
         }

         @Override
         public void actionElement(int iID) {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
            CFG.viewsManager.disableAllViews();
            CFG.game.setActiveProvinceID(-1);
            Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.BUILDING;
            CFG.VIEW_SHOW_VALUES = false;
            CFG.selectMode = true;
            CFG.game.getSelectedProvinces().clearSelectedProvinces();

            for (int i = 0; i < CFG.game.buildInProvinces.size(); i++) {
               CFG.game.getSelectedProvinces().addProvince(CFG.game.buildInProvinces.get(i));
            }

            CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
            Game_Render_Province.updateDrawProvinces();
         }
      });
      list.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("Construct"), -1, 2, n13, CFG.BUTTON_WIDTH, true) {
            @Override
            public void drawText(SpriteBatch spriteBatch, int nx, int n2x, boolean b) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  spriteBatch,
                  this.getText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + nx,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + n2x,
                  this.getColor(b)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public boolean getClickable() {
               return true;
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_BUILD;
            }

            @Override
            public int getPosX() {
               return CFG.PADDING + Menu_InGame_Build_All.this.getWidth() / 3 * 2 - CFG.PADDING;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Build_All.this.getWidth() / 3 - CFG.PADDING;
            }
         }
      );
      int n14 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle sliderMenuTitle = new SliderMenuTitle(CFG.langManager.get("BuildAll"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
         @Override
         public void draw(SpriteBatch spriteBatch, int nx, int n2x, int n3x, int n4, boolean b) {
            ImageManager.getImage(Images.dialog_title)
               .draw2(
                  spriteBatch,
                  n2x - 2 + nx,
                  n3x - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                  n4 + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
                  this.getHeight()
               );
            ImageManager.getImage(Images.dialog_title)
               .draw2(
                  spriteBatch,
                  n2x + n4 + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + nx,
                  n3x - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                  ImageManager.getImage(Images.dialog_title).getWidth(),
                  this.getHeight(),
                  true,
                  false
               );
            spriteBatch.setColor(new Color(0.7137255F, 0.20392157F, 0.03529412F, 0.165F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  spriteBatch,
                  n2x + nx,
                  n3x - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                  n4,
                  this.getHeight() - 2,
                  false,
                  true
               );
            spriteBatch.setColor(new Color(0.7137255F, 0.20392157F, 0.03529412F, 0.375F));
            ImageManager.getImage(Images.gradient)
               .draw(
                  spriteBatch,
                  n2x + nx,
                  n3x - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                  n4,
                  this.getHeight() * 2 / 3,
                  false,
                  true
               );
            spriteBatch.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
            ImageManager.getImage(Images.gradient)
               .draw(spriteBatch, n2x + nx, n3x - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), n4, CFG.PADDING, false, true);
            spriteBatch.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
            ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, n2x + nx, n3x - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), n4, 1);
            spriteBatch.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
            ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, n2x + nx, n3x - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), n4, 1);
            spriteBatch.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
            ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, n2x + nx, n3x - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), n4, 1);
            spriteBatch.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(spriteBatch, n2x + nx, n3x - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), n4 / 2, 1);
            ImageManager.getImage(Images.slider_gradient)
               .draw(spriteBatch, n2x + n4 - n4 / 2 + nx, n3x - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), n4 / 2, 1, true, false);
            spriteBatch.setColor(Color.WHITE);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .getFlag()
               .draw(
                  spriteBatch,
                  Menu_InGame_Build_All.this.getPosX() + CFG.PADDING * 2 + nx,
                  Menu_InGame_Build_All.this.getPosY()
                     - this.getHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  spriteBatch,
                  Menu_InGame_Build_All.this.getPosX() + CFG.PADDING * 2 + nx,
                  Menu_InGame_Build_All.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
               );
            CFG.fontMain.getData().setScale(0.8F);
            CFG.drawText(
               spriteBatch,
               this.getText(),
               (int)(n4 - this.getTextWidth() * 0.8F) / 2 + n2x + nx,
               n3x + 2 - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
               Color.WHITE
            );
            CFG.fontMain.getData().setScale(1.0F);
         }
      };
      int n15 = CFG.GAME_WIDTH / 2;
      int n16 = n / 2;
      iProvinceID = list.get(list.size() - 1).getPosY();
      if (list.get(list.size() - 1).getHeight() + iProvinceID + CFG.PADDING + n14 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         iProvinceID = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - n14, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         iProvinceID = list.get(list.size() - 1).getPosY();
         iProvinceID = list.get(list.size() - 1).getHeight() + iProvinceID + CFG.PADDING;
      }

      this.initMenu(sliderMenuTitle, n15 - n16, n14, n, iProvinceID, list, true, true);
      this.updateLanguage();
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   public final void actionElement(int n) {
      this.getMenuElement(n).actionElement(n);
      if (n == this.getMenuElementsSize() - 1) {
         if (CFG.game.buildInProvinces.size() == 0) {
            this.build(this.iProvinceID);
         } else {
            for (int i = 0; i < CFG.game.buildInProvinces.size(); i++) {
               this.build(CFG.game.buildInProvinces.get(i));
            }
         }
      } else if (n == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
      } else if (n == this.getMenuElementsSize() - 3) {
         this.setVisible(false);
      }
   }

   public void build(int iProvinceID) {
      ArrayList<String> list = new ArrayList<>();
      ArrayList<Color> list2 = new ArrayList<>();
      list.add(CFG.langManager.get("BuildingsHasBuilded"));
      list2.add(Color.LIME);
      if (BuildingsManager.fBuildFort && BuildingsManager.constructFort(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildFort"));
         list2.add(Color.DARK_GRAY);
      }

      if (BuildingsManager.fBuildWatchTower && BuildingsManager.constructTower(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildTowerWatch"));
         list2.add(Color.LIGHT_GRAY);
      }

      if (BuildingsManager.fBuildPort && BuildingsManager.constructPort(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildPort"));
         list2.add(Color.DARK_GRAY);
      }

      if (BuildingsManager.fBuildFarm && BuildingsManager.constructFarm(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildFarm"));
         list2.add(Color.ORANGE);
      }

      if (BuildingsManager.fBuildWorkshop && BuildingsManager.constructWorkshop(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildWorkshop"));
         list2.add(Color.GOLD);
      }

      if (BuildingsManager.fBuildLibrary && BuildingsManager.constructLibrary(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildLibrary"));
         list2.add(Color.WHITE);
      }

      if (BuildingsManager.fBuildArmoury && BuildingsManager.constructArmoury(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildArmoury"));
         list2.add(Color.RED);
      }

      if (BuildingsManager.fBuildSupply && BuildingsManager.constructSupply(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildSupply"));
         list2.add(Color.BROWN);
      }

      if (BuildingsManager.fBuildNuclearReactor && BuildingsManager.constructNuclearReactor(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildNuclearReactor"));
         list2.add(Color.ORANGE);
      }

      if (BuildingsManager.fBuildShelter && BuildingsManager.constructShelter(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         list.add(CFG.langManager.get("BuildShelter"));
         list2.add(Color.LIGHT_GRAY);
      }

      CFG.gameAction.updateInGame_ProvinceInfo();
      if (CFG.menuManager.getInGame_ProvinceBuild_Visible()) {
         CFG.menuManager.setVisible_InGame_ProvinceBuild(true, true);
      }

      CFG.toast.setInView(list, list2);
      CFG.toast.setTimeInView(4500);
      CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      this.setVisible(false);
   }

   @Override
   public void draw(SpriteBatch spriteBatch, int n, int n2, boolean b) {
      if (Menu_InGame_OfferAlliance.lTime + 200L >= System.currentTimeMillis()) {
         Rectangle rectangle = new Rectangle(
            this.getPosX() - 2,
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth() + 4,
            -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 200.0F)))
         );
         spriteBatch.flush();
         ScissorStack.pushScissors(rectangle);
         spriteBatch.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               spriteBatch,
               this.getPosX() - 2 + n,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + n2,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               CFG.PADDING + this.getHeight(),
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               spriteBatch,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + n,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + n2,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               CFG.PADDING + this.getHeight(),
               true,
               true
            );
         spriteBatch.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               spriteBatch,
               this.getPosX() + 2 + n,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + n2,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth() - 4, 1);
         spriteBatch.setColor(Color.WHITE);
         this.drawMenu(spriteBatch, n, n2, b);
         spriteBatch.setColor(Color.WHITE);
         CFG.setRender_3(true);
         this.endClip(spriteBatch, n, n2, b);
      } else {
         spriteBatch.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               spriteBatch,
               this.getPosX() - 2 + n,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + n2,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               CFG.PADDING + this.getHeight(),
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               spriteBatch,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + n,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + n2,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               CFG.PADDING + this.getHeight(),
               true,
               true
            );
         spriteBatch.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               spriteBatch,
               this.getPosX() + 2 + n,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + n2,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth() - 4, 1);
         spriteBatch.setColor(Color.WHITE);
         this.beginClip(spriteBatch, n, n2, b);
         this.drawMenu(spriteBatch, n, n2, b);
         spriteBatch.setColor(Color.WHITE);
         this.endClip(spriteBatch, n, n2, b);
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch spriteBatch, int n, int n2, boolean b) {
      if (b) {
         super.drawScrollPos(spriteBatch, n, n2, b);
      }
   }

   public final int getElementW() {
      return this.getW() / 2;
   }

   public final int getW() {
      return this.getWidth() - 4;
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible) {
         for (int i = 0; i < this.getMenuElementsSize(); i++) {
            this.getMenuElement(i).setVisible(false);
         }
      }
   }

   @Override
   public void updateLanguage() {
   }
}
