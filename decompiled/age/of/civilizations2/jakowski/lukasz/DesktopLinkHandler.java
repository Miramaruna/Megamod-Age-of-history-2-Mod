package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

public class DesktopLinkHandler implements LinkHandler {
   @Override
   public void openPage(String AppURI, String WebURL) {
      Gdx.net.openURI(WebURL);
   }
}
