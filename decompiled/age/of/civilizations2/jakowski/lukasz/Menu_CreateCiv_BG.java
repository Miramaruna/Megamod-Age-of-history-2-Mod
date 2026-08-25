package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateCiv_BG extends SliderMenu {
   public Menu_CreateCiv_BG() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.007F, 0.0085F, 0.012F, 0.75F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth(),
            CFG.PADDING * 2,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + this.getHeight() - CFG.PADDING * 2,
            this.getWidth(),
            CFG.PADDING * 2,
            false,
            true
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }
}
