package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Chatbox extends SliderMenu {
   public Menu_Chatbox() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();

      for (int i = 0; i < 10; i++) {
         menuElements.add(new Text("Hej" + i, CFG.PADDING, CFG.PADDING + CFG.TEXT_HEIGHT * i + CFG.PADDING * i));
      }

      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT * 2, menuElements);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth(), this.getHeight());
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
      }
   }
}
