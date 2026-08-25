package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;

public class UnionsManager {
   public Union_GameData createUnion_Data;
   public Unions_GameData unions;

   public UnionsManager() {
      try {
         try {
            FileHandle file = Gdx.files.local("game/unions/data");

            try {
               this.unions = (Unions_GameData)CFG.deserialize(file.readBytes());
            } catch (ClassNotFoundException var6) {
            } catch (IOException var7) {
            }
         } catch (GdxRuntimeException var8) {
            FileHandle file = Gdx.files.internal("game/unions/data");

            try {
               this.unions = (Unions_GameData)CFG.deserialize(file.readBytes());
            } catch (ClassNotFoundException var4) {
            } catch (IOException var5) {
            }
         }
      } catch (GdxRuntimeException var9) {
         this.unions = new Unions_GameData();
      }
   }

   public final void saveUnions() {
      if (this.unions.lUnions.size() > 0) {
         OutputStream osData = null;

         try {
            FileHandle fileWasteland = Gdx.files.local("game/unions/data");
            fileWasteland.writeBytes(CFG.serialize(this.unions), false);
         } catch (IOException var11) {
         } finally {
            if (osData != null) {
               try {
                  osData.close();
               } catch (Exception var10) {
               }
            }
         }
      }
   }

   public final String getUnionTag(String nTag) {
      String[] tData = nTag.split(";");

      for (int i = 0; i < tData.length; i++) {
         tData[i] = CFG.ideologiesManager.getRealTag(tData[i]);
      }

      for (int var7 = 0; var7 < this.unions.lUnions.size(); var7++) {
         for (int j = 0; j < this.unions.lUnions.get(var7).lCivsTags.size(); j++) {
            boolean found = false;

            for (int k = 0; k < tData.length; k++) {
               if (tData[k].equals(this.unions.lUnions.get(var7).lCivsTags.get(j))) {
                  found = true;
                  break;
               }
            }

            if (!found) {
               break;
            }

            if (j == this.unions.lUnions.get(var7).lCivsTags.size() - 1 && tData.length == this.unions.lUnions.get(var7).lCivsTags.size()) {
               for (int var8 = 0; var8 < CFG.game.getCivsSize(); var8++) {
                  if (this.unions.lUnions.get(var7).lCreateCivTag.equals(CFG.game.getCiv(var8).getCivTag())) {
                     return "";
                  }
               }

               return this.unions.lUnions.get(var7).lCreateCivTag;
            }
         }
      }

      return "";
   }
}
