package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReligionManager {
   public List<String> lReligionsNames;
   public List<String> lReligionsTags;
   public List<Color> lReligionsColors;
   public List<Image> lReligionsIcons;
   public int iReligionsSize = 0;

   public ReligionManager() {
      this.loadReligions();
   }

   public final void loadReligions() {
      if (this.lReligionsIcons != null && this.lReligionsIcons.size() > 0) {
         for (int i = 0; i < this.lReligionsIcons.size(); i++) {
            this.lReligionsIcons.get(i).getTexture().dispose();
            this.lReligionsIcons.remove(i--);
         }
      }

      this.lReligionsNames = new ArrayList<>();
      this.lReligionsTags = new ArrayList<>();
      this.lReligionsColors = new ArrayList<>();
      this.lReligionsIcons = new ArrayList<>();
      List<String> tempTags = CFG.getFileNames("game/religions/");
      this.iReligionsSize = tempTags.size();

      for (int i = 0; i < this.iReligionsSize; i++) {
         FileHandle fileData = Gdx.files.internal("game/religions/" + tempTags.get(i));

         try {
            Religion_GameData tempData = (Religion_GameData)CFG.deserialize(fileData.readBytes());
            this.lReligionsNames.add(CFG.langManager.get(tempData.getName()));
            this.lReligionsTags.add(tempTags.get(i));
            this.lReligionsColors
               .add(new Color(tempData.getColor().getR(), tempData.getColor().getG(), tempData.getColor().getB(), CFG.settingsManager.PROVINCE_ALPHA));

            try {
               this.lReligionsIcons
                  .add(
                     new Image(
                        new Texture(Gdx.files.internal("UI/icons/religions/" + tempData.getIconName() + ".png"), Pixmap.Format.RGBA8888, true),
                        Texture.TextureFilter.Linear
                     )
                  );
            } catch (GdxRuntimeException var6) {
               this.lReligionsIcons
                  .add(
                     new Image(new Texture(Gdx.files.internal("UI/icons/religions/notfound.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear)
                  );
            }
         } catch (ClassNotFoundException var7) {
         } catch (IOException var8) {
         }
      }
   }

   public final void saveReligionData() {
   }

   public final String getReligionName(int i) {
      return this.lReligionsNames.get(i);
   }

   public final String getReligionTag(int i) {
      return this.lReligionsTags.get(i);
   }

   public final Color getReligionColor(int i) {
      return this.lReligionsColors.get(i);
   }

   public final Image getReligionIcon(int i) {
      return this.lReligionsIcons.get(i);
   }

   public final int getReligionsSize() {
      return this.iReligionsSize;
   }
}
