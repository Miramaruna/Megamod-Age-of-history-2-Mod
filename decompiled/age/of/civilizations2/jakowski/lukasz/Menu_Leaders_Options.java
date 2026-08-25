package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu_Leaders_Options extends SliderMenu {
   public List<String> lTags = null;
   public List<String> lCivsTags = null;
   public List<Image> lFlags = new ArrayList<>();
   public List<Integer> lLoadedFlags_TagsIDs = new ArrayList<>();

   public Menu_Leaders_Options() {
      this.lCivsTags = new ArrayList<>();
      this.lTags = new ArrayList<>();
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));

      try {
         String[] tagsSPLITED = null;
         if (!CFG.isDesktop()) {
            FileHandle tempFileT = Gdx.files.internal("game/leaders/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            tagsSPLITED = tempT.split(";");
         } else {
            List<String> tempFiles = CFG.getFileNames("game/leaders/");
            int iSize = tempFiles.size();

            for (int i = 0; i < iSize; i++) {
               if (tempFiles.get(i).equals("Age_of_Civilizations")) {
                  tempFiles.remove(i);
                  break;
               }
            }

            tagsSPLITED = new String[tempFiles.size()];
            iSize = tempFiles.size();

            for (int var21 = 0; var21 < iSize; var21++) {
               tagsSPLITED[var21] = tempFiles.get(var21);
            }
         }

         ArrayList<String> lTempNames = new ArrayList<>();
         ArrayList<String> lTempTags = new ArrayList<>();
         ArrayList<String> lTempCivsTags = new ArrayList<>();
         if (CFG.sSearch != null && CFG.sSearch.length() > 0) {
            int iSize = tagsSPLITED.length;

            for (int ix = 0; ix < iSize; ix++) {
               try {
                  try {
                     FileHandle file = Gdx.files.local("game/leaders/" + tagsSPLITED[ix]);
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(file.readBytes());
                  } catch (GdxRuntimeException var16) {
                     FileHandle filex = Gdx.files.internal("game/leaders/" + tagsSPLITED[ix]);
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(filex.readBytes());
                  }
               } catch (ClassNotFoundException var17) {
               } catch (IOException var18) {
               }

               if (CFG.leader_GameData.getLeaderOfCiv().getName().toLowerCase().indexOf(CFG.sSearch.toLowerCase()) >= 0) {
                  lTempNames.add(CFG.leader_GameData.getLeaderOfCiv().getName());
                  lTempTags.add(tagsSPLITED[ix]);
                  lTempCivsTags.add(CFG.leader_GameData.getCiv(0));
               }
            }

            for (int nPosY = 0; lTempNames.size() > 0; nPosY++) {
               int toAddID = 0;

               for (int ix = 1; ix < lTempNames.size(); ix++) {
                  if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(ix))) {
                     toAddID = ix;
                  }
               }

               menuElements.add(
                  new Button_Menu(
                     lTempNames.get(toAddID),
                     (int)(50.0F * CFG.GUI_SCALE),
                     0,
                     CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2),
                     CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               menuElements.add(
                  new Button_Menu_Classic_Wiki(
                     CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                     CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2),
                     CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               this.lCivsTags.add(lTempCivsTags.get(toAddID));
               this.lTags.add(lTempTags.get(toAddID));
               lTempNames.remove(toAddID);
               lTempTags.remove(toAddID);
               lTempCivsTags.remove(toAddID);
            }
         } else if (CFG.chosen_AlphabetCharachter == null) {
            int iSize = tagsSPLITED.length;

            for (int ixx = 0; ixx < iSize; ixx++) {
               try {
                  try {
                     FileHandle file = Gdx.files.local("game/leaders/" + tagsSPLITED[ixx]);
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(file.readBytes());
                  } catch (GdxRuntimeException var13) {
                     FileHandle filex = Gdx.files.internal("game/leaders/" + tagsSPLITED[ixx]);
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(filex.readBytes());
                  }
               } catch (ClassNotFoundException var14) {
                  CFG.exceptionStack(var14);
               } catch (IOException var15) {
                  CFG.exceptionStack(var15);
               }

               lTempNames.add(CFG.leader_GameData.getLeaderOfCiv().getName());
               lTempTags.add(tagsSPLITED[ixx]);
               lTempCivsTags.add(CFG.leader_GameData.getCiv(0));
            }

            for (int nPosY = 0; lTempNames.size() > 0; nPosY++) {
               int toAddID = 0;

               for (int ixx = 1; ixx < lTempNames.size(); ixx++) {
                  if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(ixx))) {
                     toAddID = ixx;
                  }
               }

               menuElements.add(
                  new Button_Menu(
                     lTempNames.get(toAddID),
                     (int)(50.0F * CFG.GUI_SCALE),
                     0,
                     CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2),
                     CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               menuElements.add(
                  new Button_Menu_Classic_Wiki(
                     CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                     CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2),
                     CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               this.lCivsTags.add(lTempCivsTags.get(toAddID));
               this.lTags.add(lTempTags.get(toAddID));
               lTempNames.remove(toAddID);
               lTempTags.remove(toAddID);
               lTempCivsTags.remove(toAddID);
            }
         } else {
            int iSize = tagsSPLITED.length;

            for (int ixxx = 0; ixxx < iSize; ixxx++) {
               try {
                  try {
                     FileHandle file = Gdx.files.local("game/leaders/" + tagsSPLITED[ixxx]);
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(file.readBytes());
                  } catch (GdxRuntimeException var10) {
                     FileHandle filex = Gdx.files.internal("game/leaders/" + tagsSPLITED[ixxx]);
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(filex.readBytes());
                  }
               } catch (ClassNotFoundException var11) {
               } catch (IOException var12) {
               }

               if (CFG.leader_GameData.getLeaderOfCiv().getName().charAt(0) == CFG.chosen_AlphabetCharachter.charAt(0)) {
                  lTempNames.add(CFG.leader_GameData.getLeaderOfCiv().getName());
                  lTempTags.add(tagsSPLITED[ixxx]);
                  lTempCivsTags.add(CFG.leader_GameData.getCiv(0));
               }
            }

            for (int nPosY = 0; lTempNames.size() > 0; nPosY++) {
               int toAddID = 0;

               for (int ixxx = 1; ixxx < lTempNames.size(); ixxx++) {
                  if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(ixxx))) {
                     toAddID = ixxx;
                  }
               }

               menuElements.add(
                  new Button_Menu(
                     lTempNames.get(toAddID),
                     (int)(50.0F * CFG.GUI_SCALE),
                     0,
                     CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2),
                     CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               menuElements.add(
                  new Button_Menu_Classic_Wiki(
                     CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                     CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2),
                     CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               this.lCivsTags.add(lTempCivsTags.get(toAddID));
               this.lTags.add(lTempTags.get(toAddID));
               lTempNames.remove(toAddID);
               lTempTags.remove(toAddID);
               lTempCivsTags.remove(toAddID);
            }
         }
      } catch (GdxRuntimeException var19) {
      }

      this.initMenu(
         null,
         0,
         CFG.BUTTON_HEIGHT * 3 / 4 + CFG.BUTTON_HEIGHT + CFG.PADDING,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING - (CFG.BUTTON_HEIGHT + CFG.PADDING),
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("AddNewLeader"));
   }

   @Override
   public void updateMenuElements_IsInView() {
      super.updateMenuElements_IsInView();

      for (int i = 1; i < this.getMenuElementsSize(); i += 2) {
         int tempTagID = this.getIsLoaded(this.lCivsTags.get((i - 1) / 2));
         if (this.getMenuElement(i).getIsInView()) {
            if (tempTagID < 0) {
               this.loadFlag((i - 1) / 2);
            }
         } else if (tempTagID >= 0) {
            this.lFlags.get(tempTagID).getTexture().dispose();
            this.lFlags.set(tempTagID, null);
            this.lFlags.remove(tempTagID);
            this.lLoadedFlags_TagsIDs.remove(tempTagID);
         }
      }
   }

   public final int getIsLoaded(String nCivTag) {
      for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); i++) {
         if (this.lCivsTags.get(this.lLoadedFlags_TagsIDs.get(i)).equals(nCivTag)) {
            return i;
         }
      }

      return -1;
   }

   public final int getFlagID(int nCivTagID) {
      for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); i++) {
         if (this.lLoadedFlags_TagsIDs.get(i) == nCivTagID) {
            return i;
         }
      }

      return 0;
   }

   public final void loadFlag(int nCivTagID) {
      try {
         try {
            this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + this.lCivsTags.get(nCivTagID) + ".png")), Texture.TextureFilter.Nearest));
         } catch (GdxRuntimeException var3) {
            this.lFlags
               .add(
                  new Image(
                     new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")),
                     Texture.TextureFilter.Nearest
                  )
               );
         }
      } catch (GdxRuntimeException var4) {
         this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
      }

      this.lLoadedFlags_TagsIDs.add(nCivTagID);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      int tempRandomButton = 1;

      try {
         for (int i = tempRandomButton; i < this.getMenuElementsSize(); i += 2) {
            if (this.getMenuElement(i).getIsInView()) {
               this.lFlags
                  .get(this.getFlagID((i - tempRandomButton) / 2))
                  .draw(
                     oSB,
                     this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                     this.getMenuElement(i).getPosY()
                        + this.getMenuPosY()
                        - this.lFlags.get(this.getFlagID((i - tempRandomButton) / 2)).getHeight()
                        + this.getMenuElement(i).getHeight() / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        + iTranslateY,
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                     this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                  );
            }
         }
      } catch (IndexOutOfBoundsException var7) {
      } catch (NullPointerException var8) {
      }

      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.game.setActiveProvinceID(-1);
            CFG.game.getSelectedProvinces().clearSelectedProvinces();
            CFG.selectMode = true;
            CFG.brushTool = false;
            CFG.VIEW_SHOW_VALUES = false;
            CFG.leader_GameData = new Leader_GameData();
            CFG.leader_GameData.getLeaderOfCiv().setTag(System.currentTimeMillis() + CFG.extraRandomTag());
            Game_Calendar.currentDay = CFG.leader_GameData.getLeaderOfCiv().getDay();
            Game_Calendar.currentMonth = CFG.leader_GameData.getLeaderOfCiv().getMonth();
            Game_Calendar.currentYear = CFG.leader_GameData.getLeaderOfCiv().getYear();
            CFG.CREATE_SCENARIO_AGE = CFG.gameAges.getAgeOfYear(Game_Calendar.currentYear);
            CFG.menuManager.setViewID(Menu.eGAME_LEADERS_EDIT);
            return;
         default:
            if (iID % 2 == 1) {
               CFG.game.setActiveProvinceID(-1);
               CFG.game.getSelectedProvinces().clearSelectedProvinces();
               CFG.selectMode = true;
               CFG.brushTool = false;
               CFG.VIEW_SHOW_VALUES = false;

               try {
                  try {
                     FileHandle file = Gdx.files.local("game/leaders/" + this.lTags.get((iID - 1) / 2));
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(file.readBytes());
                  } catch (GdxRuntimeException var8) {
                     FileHandle filex = Gdx.files.internal("game/leaders/" + this.lTags.get((iID - 1) / 2));
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(filex.readBytes());
                  }
               } catch (ClassNotFoundException var9) {
               } catch (IOException var10) {
               }

               Game_Calendar.currentDay = CFG.leader_GameData.getLeaderOfCiv().getDay();
               Game_Calendar.currentMonth = CFG.leader_GameData.getLeaderOfCiv().getMonth();
               Game_Calendar.currentYear = CFG.leader_GameData.getLeaderOfCiv().getYear();
               CFG.CREATE_SCENARIO_AGE = CFG.gameAges.getAgeOfYear(Game_Calendar.currentYear);
               CFG.menuManager.setViewID(Menu.eGAME_LEADERS_EDIT);
            } else {
               try {
                  try {
                     FileHandle file = Gdx.files.local("game/leaders/" + this.lTags.get((iID - 1) / 2));
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(file.readBytes());
                  } catch (GdxRuntimeException var5) {
                     FileHandle filex = Gdx.files.internal("game/leaders/" + this.lTags.get((iID - 1) / 2));
                     CFG.leader_GameData = (Leader_GameData)CFG.deserialize(filex.readBytes());
                  }
               } catch (ClassNotFoundException var6) {
               } catch (IOException var7) {
               }

               if (CFG.leader_GameData.getLeaderOfCiv().getWiki().length() > 0) {
                  try {
                     Gdx.net.openURI("https://en.wikipedia.org/wiki/" + CFG.leader_GameData.getLeaderOfCiv().getWiki());
                  } catch (GdxRuntimeException var4) {
                  }
               } else {
                  CFG.toast.setInView(CFG.langManager.get("Error"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
               }
            }
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eEDITOR);
      this.disposeData();
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible) {
         this.disposeData();
      }
   }

   public void disposeData() {
      for (int i = 0; i < this.lFlags.size(); i++) {
         this.lFlags.get(i).getTexture().dispose();
      }

      this.lFlags.clear();
      this.lLoadedFlags_TagsIDs.clear();
      this.lCivsTags.clear();
   }
}
