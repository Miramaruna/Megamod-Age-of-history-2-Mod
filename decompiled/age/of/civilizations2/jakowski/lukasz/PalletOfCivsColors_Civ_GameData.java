package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class PalletOfCivsColors_Civ_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public Color_GameData oColor;

   public PalletOfCivsColors_Civ_GameData(Color_GameData oColor) {
      this.setColor(oColor);
   }

   public final Color_GameData getColor() {
      return this.oColor;
   }

   public final void setColor(Color_GameData oColor) {
      this.oColor = oColor;
   }
}
