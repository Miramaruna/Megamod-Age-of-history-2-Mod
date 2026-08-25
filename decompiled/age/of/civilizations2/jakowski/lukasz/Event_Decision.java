package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Decision implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sTitle = "";
   public List<Event_Outcome> lOutcomes = new ArrayList<>();
   public int iAIChance = 100;

   public final void executeDecision() {
      for (int i = 0; i < this.lOutcomes.size(); i++) {
         this.lOutcomes.get(i).outcomeAction();
      }
   }
}
