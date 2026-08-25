package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Flag_Overlay_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iOverlayID = 0;
   public Color_GameData oColor = new Color_GameData(1.0F, 1.0F, 1.0F);
   public int iPosX = 0;
   public int iPosY = 0;
   public int iWidth = 0;
   public int iHeight = 0;

   public Flag_Overlay_GameData(int iOverlayID) {
      this.iOverlayID = iOverlayID;
   }
}
