package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Menu_Leader_Edit extends SliderMenu {
   public Menu_Leader_Edit() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
      menuElements.add(
         new Text(
            null, -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? new Color(0.56F, 0.56F, 0.56F, 1.0F)
                  : (
                     this.getClickable()
                        ? (
                           this.getIsHovered()
                              ? new Color(0.68F, 0.68F, 0.68F, 1.0F)
                              : new Color(
                                 CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95F
                              )
                        )
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("Save"));
      this.getMenuElement(3).setText(CFG.langManager.get("AddNewLeader"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            CFG.brushTool = false;
            CFG.menuManager.setViewID(Menu.eGAME_LEADERS);
            CFG.menuManager.setBackAnimation(true);
            return;
         case 1:
            CFG.menuManager.saveLeader_Edit_Data();
            if (CFG.leader_GameData.getLeaderOfCiv().getName().length() < 1) {
               CFG.toast.setInView("-- " + CFG.langManager.get("Name") + " --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
               CFG.toast.setTimeInView(6000);
            } else if (CFG.leader_GameData.getCivsSize() == 0) {
               CFG.toast.setInView("-- " + CFG.langManager.get("Civilizations") + " --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
               CFG.toast.setTimeInView(6000);
            } else {
               this.saveLeader();
               this.onBackPressed();
               CFG.brushTool = false;
               CFG.menuManager.setViewID(Menu.eGAME_LEADERS);
               CFG.menuManager.setBackAnimation(true);
            }
            break;
         case 2:
            CFG.map
               .getMapCoordinates()
               .centerToMinimapClick(
                  Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(),
                  Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY()
               );
      }
   }

   public final void saveLeader() {
      OutputStream os = null;

      try {
         FileHandle fileData = Gdx.files.local("game/leaders/" + CFG.leader_GameData.getLeaderOfCiv().getTag());
         fileData.writeBytes(CFG.serialize(CFG.leader_GameData), false);
      } catch (IOException var15) {
      } finally {
         if (os != null) {
            try {
               os.close();
            } catch (Exception var14) {
            }
         }
      }

      try {
         FileHandle file = CFG.readLocalFiles()
            ? Gdx.files.local("game/leaders/Age_of_Civilizations")
            : Gdx.files.internal("game/leaders/Age_of_Civilizations");
         String tempTags = file.readString();
         if (tempTags.indexOf(CFG.leader_GameData.getLeaderOfCiv().getTag()) < 0) {
            FileHandle fileSave = Gdx.files.local("game/leaders/Age_of_Civilizations");
            fileSave.writeString(tempTags + CFG.leader_GameData.getLeaderOfCiv().getTag() + ";", false);
         } else {
            String[] tempTagsSplited = tempTags.split(";");
            boolean tAdd = true;
            int iSize = tempTagsSplited.length;

            for (int i = 0; i < iSize; i++) {
               if (tempTagsSplited[i].equals(CFG.leader_GameData.getLeaderOfCiv().getTag())) {
                  tAdd = false;
                  break;
               }
            }

            if (tAdd) {
               FileHandle fileSave = Gdx.files.local("game/leaders/Age_of_Civilizations");
               fileSave.writeString(tempTags + CFG.leader_GameData.getLeaderOfCiv().getTag() + ";", false);
            }
         }
      } catch (GdxRuntimeException var17) {
         FileHandle fileSave = Gdx.files.local("game/leaders/Age_of_Civilizations");
         fileSave.writeString(CFG.leader_GameData.getLeaderOfCiv().getTag() + ";", false);
      }
   }
}
