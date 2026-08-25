package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu_Packages_RandomAllianceNames extends SliderMenu {
   public List<String> lTags = new ArrayList<>();
   public List<Boolean> lEnabled = new ArrayList<>();

   public Menu_Packages_RandomAllianceNames() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));

      try {
         FileHandle file = Gdx.files.internal("game/alliance_names/Age_of_Civilizations.json");
         String fileContent = file.readString();
         Json json = new Json();
         json.setElementType(CFG.ConfigAlliancesData.class, "Data_Random_Alliance_Names", CFG.Data_Random_Alliance_Names.class);
         new CFG.ConfigAlliancesData();
         CFG.ConfigAlliancesData data = json.fromJson(CFG.ConfigAlliancesData.class, fileContent);
         int tempI = 0;

         for (Object e : data.Data_Random_Alliance_Names) {
            CFG.Data_Random_Alliance_Names tempData = (CFG.Data_Random_Alliance_Names)e;
            menuElements.add(
               new Button_Menu(
                  this.getPackageName(tempData.Tag),
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * (tempI + 1) + CFG.PADDING * (tempI + 2),
                  CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
            menuElements.add(
               new Button_Menu_ReflectedBG(
                  this.getPackageName(tempData.Tag),
                  -1,
                  CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2,
                  CFG.BUTTON_HEIGHT * (tempI + 1) + CFG.PADDING * (tempI + 2),
                  CFG.BUTTON_WIDTH * 2,
                  CFG.BUTTON_HEIGHT,
                  true
               ) {
                  @Override
                  public final Color getColor(boolean isActive) {
                     return isActive
                        ? new Color(0.941F, 1.0F, 0.0F, 1.0F)
                        : (
                           this.getClickable()
                              ? (this.getCheckboxState() ? new Color(0.396F, 0.576F, 0.012F, 1.0F) : new Color(0.643F, 0.113F, 0.008F, 1.0F))
                              : new Color(0.674F, 0.09F, 0.066F, 0.5F)
                        );
                  }
               }
            );
            menuElements.get(menuElements.size() - 1).setCheckboxState(tempData.Enabled);
            this.lTags.add(tempData.Tag);
            this.lEnabled.add(tempData.Enabled);
            tempI++;
         }
      } catch (GdxRuntimeException var10) {
      }

      this.initMenuWithBackButton(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("CreateNewPackage"));

      for (int i = 3; i < this.getMenuElementsSize(); i += 2) {
         this.getMenuElement(i).setText(this.lEnabled.get((i - 2) / 2) ? CFG.langManager.get("Disable") : CFG.langManager.get("Enable"));
      }

      this.getTitle().setText(CFG.langManager.get("RandomAlliancesNamesPackages"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            break;
         case 1:
            CFG.editorAlliancesNames_GameData = new Alliances_Names_GameData();
            CFG.CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
            CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE_CREATE);
            break;
         default:
            if (iID % 2 == 0) {
               try {
                  CFG.CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG = this.lTags.get((iID - 2) / 2);
                  FileHandle file = Gdx.files.internal("game/alliance_names/" + CFG.CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG);
                  CFG.editorAlliancesNames_GameData = (Alliances_Names_GameData)CFG.deserialize(file.readBytes());
                  CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE_CREATE);
               } catch (ClassNotFoundException var3) {
               } catch (IOException var4) {
               }
            } else {
               this.lEnabled.set((iID - 2) / 2, !this.lEnabled.get((iID - 2) / 2));
               this.getMenuElement(iID).setText(this.lEnabled.get((iID - 2) / 2) ? CFG.langManager.get("Disable") : CFG.langManager.get("Enable"));
               this.getMenuElement(iID).setCheckboxState(this.lEnabled.get((iID - 2) / 2));
               CFG.toast.setInView(this.lEnabled.get((iID - 2) / 2) ? CFG.langManager.get("Enabled") : CFG.langManager.get("Disabled"));
               this.updateEnabled((iID - 2) / 2, this.lEnabled.get((iID - 2) / 2));
            }
      }
   }

   public final void updateEnabled(int i, boolean nValue) {
      FileHandle file = Gdx.files.internal("game/alliance_names/Age_of_Civilizations.json");
      String fileContent = file.readString();
      Json json = new Json();
      json.setElementType(CFG.ConfigAlliancesData.class, "Data_Random_Alliance_Names", CFG.Data_Random_Alliance_Names.class);
      new CFG.ConfigAlliancesData();
      CFG.ConfigAlliancesData data = json.fromJson(CFG.ConfigAlliancesData.class, fileContent);
      CFG.ConfigAlliancesData configData = new CFG.ConfigAlliancesData();
      configData.Age_of_Civilizations = "Data";
      new ArrayList();
      CFG.Data_Random_Alliance_Names tempUpdated = (CFG.Data_Random_Alliance_Names)data.Data_Random_Alliance_Names.get(i);
      tempUpdated.Enabled = nValue;
      data.Data_Random_Alliance_Names.set(i, tempUpdated);
      ArrayList dataList = data.Data_Random_Alliance_Names;
      configData.Data_Random_Alliance_Names = data.Data_Random_Alliance_Names;
      Json jsonSave = new Json();
      jsonSave.setOutputType(JsonWriter.OutputType.json);
      jsonSave.setElementType(CFG.ConfigAlliancesData.class, "Data_Random_Alliance_Names", CFG.Data_Random_Alliance_Names.class);
      FileHandle fileSave = Gdx.files.local("game/alliance_names/Age_of_Civilizations.json");
      fileSave.writeString(jsonSave.prettyPrint(configData), false);
   }

   public final String getPackageName(String nTag) {
      try {
         FileHandle file = Gdx.files.internal("game/alliance_names/" + nTag);
         Alliances_Names_GameData tempAllianceNamesData = (Alliances_Names_GameData)CFG.deserialize(file.readBytes());
         return tempAllianceNamesData.getPackageName();
      } catch (ClassNotFoundException var4) {
      } catch (IOException var5) {
      }

      return nTag;
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eGAME_EDITOR);
      CFG.menuManager.setBackAnimation(true);
      CFG.loadRandomAlliancesNames();
   }
}
