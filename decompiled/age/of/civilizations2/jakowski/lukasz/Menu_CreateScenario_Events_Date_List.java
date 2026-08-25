package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.Random;

public class Menu_CreateScenario_Events_Date_List extends SliderMenu {
   public Menu_CreateScenario_Events_Date_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();

      for (int i = 0; i < CFG.gameAges.getAgesSize(); i++) {
         menuElements.add(
            new Button_Menu_Age(
               CFG.gameAges.getYear(CFG.gameAges.getAge(i).getBeginningYear()) + " - " + CFG.gameAges.getYear(CFG.gameAges.getAge(i).getEndYear()),
               -1,
               0,
               CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1),
               CFG.BUTTON_WIDTH * 2,
               CFG.BUTTON_HEIGHT,
               Game_Calendar.CURRENT_AGEID <= i
            )
         );
         menuElements.add(
            new Button_Menu_ReflectedBG(
               CFG.gameAges.getAge(i).getName(),
               CFG.PADDING * 5,
               CFG.BUTTON_WIDTH * 2,
               CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1),
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2,
               CFG.BUTTON_HEIGHT,
               Game_Calendar.CURRENT_AGEID <= i
            )
         );
      }

      this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 5, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 5), menuElements);
      this.updateLanguage();
   }

   @Override
   public boolean getMenuElementIsActive(boolean sliderMenuIsActive, int i) {
      return super.getMenuElementIsActive(sliderMenuIsActive, i) || i % 2 == 0 && i / 2 == CFG.eventsManager.iCreateEvent_Age;
   }

   @Override
   public final void actionElement(int iID) {
      if (CFG.eventsManager.iCreateEvent_Age != iID / 2) {
         CFG.eventsManager.iCreateEvent_Age = iID / 2;
         CFG.eventsManager.iCreateEvent_Year = Math.max(
               Math.abs(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getBeginningYear()),
               Math.abs(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getEndYear())
            )
            - Math.min(
               Math.abs(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getBeginningYear()),
               Math.abs(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getEndYear())
            );
         Random oR = new Random();
         CFG.eventsManager.iCreateEvent_Year = CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getBeginningYear()
            + oR.nextInt(CFG.eventsManager.iCreateEvent_Year);
         ArrayList<String> tMess = new ArrayList<>();
         ArrayList<Color> tColor = new ArrayList<>();
         tMess.add(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName());
         tColor.add(Color.WHITE);
         tMess.add(
            CFG.gameAges.getYear(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getBeginningYear())
               + " - "
               + CFG.gameAges.getYear(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getEndYear())
         );
         tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
         CFG.toast.setInView(tMess, tColor);
         CFG.menuManager.updateCreateScanerio_Events_Slider();
      }
   }
}
