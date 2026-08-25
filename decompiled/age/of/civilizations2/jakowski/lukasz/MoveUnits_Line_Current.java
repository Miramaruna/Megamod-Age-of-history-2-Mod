package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveUnits_Line_Current extends MoveUnits_Line {
   public MoveUnits_Line_Current(int fromProvinceID, int toProvinceID) {
      super(fromProvinceID, toProvinceID);
      MOVE_WIDTH = this.getImageID().getWidth();
   }

   @Override
   public void updateColor(SpriteBatch oSB) {
      try {
         oSB.setColor(
            CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(CFG.activeCivilizationArmyID)).getR() / 255.0F,
            CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(CFG.activeCivilizationArmyID)).getG() / 255.0F,
            CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(CFG.activeCivilizationArmyID)).getB() / 255.0F,
            1.0F
         );
      } catch (IndexOutOfBoundsException var3) {
         oSB.setColor(Color.WHITE);
      }
   }
}
