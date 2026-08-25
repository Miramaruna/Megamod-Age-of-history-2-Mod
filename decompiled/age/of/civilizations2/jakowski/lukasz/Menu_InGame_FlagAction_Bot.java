package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_FlagAction_Bot extends SliderMenu {
   public static final int ANIMATION_TIME = 225;
   public static long lTime = 0L;

   public Menu_InGame_FlagAction_Bot() {
      int tempHeight = 0;
      int tempWidth = 0;
      if (CFG.isAndroid() && !CFG.LANDSCAPE) {
         tempHeight = CFG.GAME_HEIGHT
            - (
               CFG.BUTTON_HEIGHT / 2
                  + ImageManager.getImage(Images.top_left).getHeight()
                  + CFG.PADDING * 2
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  + CFG.PADDING * 4
                  + CFG.TEXT_HEIGHT
                  + CFG.PADDING * 4
                  + (
                        CFG.GAME_HEIGHT
                           - (
                              ImageManager.getImage(Images.top_left).getHeight()
                                 + CFG.PADDING * 2
                                 + ImageManager.getImage(Images.top_flag_frame).getHeight()
                                 + CFG.PADDING * 4
                                 + CFG.TEXT_HEIGHT
                                 + CFG.PADDING * 4
                           )
                           - CFG.map.getMapBG().getMinimapHeight()
                           - CFG.PADDING * 2
                     )
                     / 2
            )
            - CFG.map.getMapBG().getMinimapHeight()
            - CFG.PADDING * 2;
         tempHeight *= 2;
         tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
      } else if (CFG.isAndroid() && CFG.LANDSCAPE) {
         tempHeight = CFG.GAME_HEIGHT
            - (
               ImageManager.getImage(Images.top_left).getHeight()
                  + CFG.PADDING * 2
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  + CFG.PADDING * 4
                  + CFG.TEXT_HEIGHT
                  + CFG.PADDING * 4
            )
            - CFG.PADDING * 2
            - CFG.BUTTON_HEIGHT / 2;
         tempWidth = CFG.GAME_WIDTH - CFG.GAME_WIDTH * 2 / 5 - CFG.PADDING * 2;
      } else {
         tempHeight = CFG.GAME_HEIGHT
            - (
               ImageManager.getImage(Images.top_left).getHeight()
                  + CFG.PADDING * 2
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  + CFG.PADDING * 4
                  + CFG.TEXT_HEIGHT
                  + CFG.PADDING * 4
            )
            - CFG.map.getMapBG().getMinimapHeight()
            - CFG.PADDING * 2
            - CFG.BUTTON_HEIGHT / 2;
         tempWidth = CFG.GAME_WIDTH - CFG.GAME_WIDTH * 2 / 5 - CFG.PADDING * 2;
      }

      ArrayList<MenuElement> menuElements = new ArrayList<>();
      ArrayList<Graph_Vertical_Data> tempData = new ArrayList<>();

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) {
            tempData.add(new Graph_Vertical_Data(i));
         }
      }

      menuElements.add(
         new Graph_Vertical(
            Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT,
            CFG.langManager.get("Civilizations"),
            CFG.langManager.get("Provinces"),
            CFG.PADDING * 2,
            CFG.PADDING * 2,
            tempWidth - CFG.PADDING * 4,
            tempHeight / 2 - CFG.PADDING * 4,
            true,
            tempData
         )
      );
      menuElements.add(new Button_Transparent(0, 0, tempWidth, tempHeight / 2, true));
      this.initMenu(
         null,
         CFG.isAndroid() && !CFG.LANDSCAPE ? CFG.PADDING * 2 : CFG.GAME_WIDTH - CFG.GAME_WIDTH * 3 / 5,
         CFG.isAndroid() && !CFG.LANDSCAPE
            ? CFG.BUTTON_HEIGHT / 2
               + ImageManager.getImage(Images.top_left).getHeight()
               + CFG.PADDING * 2
               + ImageManager.getImage(Images.top_flag_frame).getHeight()
               + CFG.PADDING * 4
               + CFG.TEXT_HEIGHT
               + CFG.PADDING * 4
               + (
                     CFG.GAME_HEIGHT
                        - (
                           ImageManager.getImage(Images.top_left).getHeight()
                              + CFG.PADDING * 2
                              + ImageManager.getImage(Images.top_flag_frame).getHeight()
                              + CFG.PADDING * 4
                              + CFG.TEXT_HEIGHT
                              + CFG.PADDING * 4
                        )
                        - CFG.map.getMapBG().getMinimapHeight()
                        - CFG.PADDING * 2
                  )
                  / 2
            : ImageManager.getImage(Images.top_left).getHeight()
               + CFG.PADDING * 2
               + ImageManager.getImage(Images.top_flag_frame).getHeight()
               + CFG.PADDING * 4
               + CFG.TEXT_HEIGHT
               + CFG.PADDING * 4
               + CFG.BUTTON_HEIGHT / 2,
         tempWidth,
         tempHeight / 2,
         menuElements,
         false,
         false
      );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      if (CFG.isAndroid() && !CFG.LANDSCAPE) {
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight(),
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight(),
               true,
               true
            );
      } else {
         ImageManager.getImage(Images.new_game_top_edge_line)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight(),
               true,
               true
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.25F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.75F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 2,
               CFG.BUTTON_HEIGHT / 4
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               CFG.BUTTON_HEIGHT / 4,
               this.getHeight() - 2
            );
         oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight()
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth() - 2,
               1
            );
         oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.375F));
         ImageManager.getImage(Images.gradient)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, 1, this.getHeight());
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth() - 2,
               1
            );
         oSB.setColor(Color.WHITE);
      }

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
      }
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      lTime = System.currentTimeMillis();
   }
}
