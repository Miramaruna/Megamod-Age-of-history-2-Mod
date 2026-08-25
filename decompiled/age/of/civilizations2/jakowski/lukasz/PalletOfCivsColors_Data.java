package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PalletOfCivsColors_Data {
   public List<PalletOfCivsColors_Civ_GameData> lData = new ArrayList<>();
   public List<String> lCivsTags = new ArrayList<>();
   public int iDataSize = 0;
   public String UPDATE_KEY = null;

   PalletOfCivsColors_Data() {
   }

   public final void setCivColor(String nTag, Color_GameData nColor) {
      for (int i = 0; i < this.iDataSize; i++) {
         if (this.lCivsTags.get(i).equals(nTag)) {
            this.lData.get(i).setColor(nColor);
            return;
         }
      }

      this.lData.add(new PalletOfCivsColors_Civ_GameData(nColor));
      this.lCivsTags.add(nTag);
      this.iDataSize = this.lData.size();
   }

   public final void readData(boolean isInternal) {
      this.lData = new ArrayList<>();
      this.lCivsTags = new ArrayList<>();
      this.iDataSize = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         FileHandle file = null;

         try {
            if (isInternal) {
               file = Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag());
            } else {
               file = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag());
            }

            try {
               PalletOfCivsColors_Civ_GameData nCivColor = (PalletOfCivsColors_Civ_GameData)CFG.deserialize(file.readBytes());
               CFG.game.getCiv(i).setR((int)(nCivColor.getColor().getR() * 255.0F));
               CFG.game.getCiv(i).setG((int)(nCivColor.getColor().getG() * 255.0F));
               CFG.game.getCiv(i).setB((int)(nCivColor.getColor().getB() * 255.0F));
            } catch (ClassNotFoundException var5) {
               CFG.palletManager.loadCivilizationStandardColor(0);
            } catch (IOException var6) {
               CFG.palletManager.loadCivilizationStandardColor(0);
            }
         } catch (GdxRuntimeException var7) {
            CFG.palletManager.loadCivilizationStandardColor(0);
         }
      }
   }

   public final void saveData() {
      for (int i = 0; i < this.iDataSize; i++) {
         OutputStream outputSteam = null;

         try {
            try {
               FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + CFG.game.getCiv(i + 1).getCivTag());

               try {
                  Civilization_GameData3 temp = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  if ((
                        temp.getR() != (int)(this.lData.get(i).getColor().getR() * 255.0F)
                           || temp.getG() != (int)(this.lData.get(i).getColor().getG() * 255.0F)
                           || temp.getB() != (int)(this.lData.get(i).getColor().getB() * 255.0F)
                     )
                     && (int)(this.lData.get(i).getColor().getR() * 255.0F) == 0
                     && (int)(this.lData.get(i).getColor().getG() * 255.0F) == 1
                     && (int)(this.lData.get(i).getColor().getB() * 255.0F) == 2) {
                  }
               } catch (ClassNotFoundException var23) {
               }
            } catch (GdxRuntimeException var26) {
               try {
                  FileHandle fileCiv2 = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(i + 1).getCivTag()));

                  try {
                     Civilization_GameData3 temp2 = (Civilization_GameData3)CFG.deserialize(fileCiv2.readBytes());
                     if ((
                           temp2.getR() != (int)(this.lData.get(i).getColor().getR() * 255.0F)
                              || temp2.getG() != (int)(this.lData.get(i).getColor().getG() * 255.0F)
                              || temp2.getB() != (int)(this.lData.get(i).getColor().getB() * 255.0F)
                        )
                        && (int)(this.lData.get(i).getColor().getR() * 255.0F) == 0
                        && (int)(this.lData.get(i).getColor().getG() * 255.0F) == 1
                        && (int)(this.lData.get(i).getColor().getB() * 255.0F) == 2) {
                     }
                  } catch (ClassNotFoundException var24) {
                  }
               } catch (GdxRuntimeException var25) {
               }
            }

            FileHandle fileData = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + this.lCivsTags.get(i));
            fileData.writeBytes(CFG.serialize(this.lData.get(i)), false);

            try {
               FileHandle file;
               if (CFG.readLocalFiles()) {
                  file = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/Age_of_Civilizations");
               } else {
                  file = Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/Age_of_Civilizations");
               }

               String tempTags = file.readString();
               if (tempTags.indexOf(this.lCivsTags.get(i)) < 0) {
                  FileHandle fileSave = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/Age_of_Civilizations");
                  fileSave.writeString(tempTags + this.lCivsTags.get(i) + ";", false);
               }
            } catch (GdxRuntimeException var22) {
               FileHandle fileSave2 = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/Age_of_Civilizations");
               fileSave2.writeString(this.lCivsTags.get(i) + ";", false);
            }
         } catch (IOException var27) {
         } finally {
            if (outputSteam != null) {
               try {
                  outputSteam.close();
               } catch (Exception var20) {
               }
            }
         }
      }

      try {
         FileHandle file2;
         if (CFG.readLocalFiles()) {
            file2 = Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations");
         } else {
            file2 = Gdx.files.internal("game/pallets_of_civs_colors/Age_of_Civilizations");
         }

         String tempTags2 = file2.readString();
         if (tempTags2.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) < 0) {
            FileHandle fileSave3 = Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations");
            fileSave3.writeString(tempTags2 + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
         }
      } catch (GdxRuntimeException var21) {
         FileHandle fileSave4 = Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations");
         fileSave4.writeString(CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
      }
   }

   public final int getDataSize() {
      return this.iDataSize;
   }

   public final String getUPDATE_KEY() {
      return this.UPDATE_KEY;
   }

   public final void setUPDATE_KEY(String nUPDATE_KEY) {
      this.UPDATE_KEY = nUPDATE_KEY;
   }
}
