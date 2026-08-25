package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Trigger implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Event_Conditions> lConditions = new ArrayList<>();
   public Event_Type triggerType = Event_Type.AND;

   Event_Trigger() {
   }

   public final boolean getTriggerOut() {
      for (int i = 0; i < this.lConditions.size(); i++) {
         if (this.lConditions.get(i).conditionType == Event_Type.OR && this.lConditions.get(i).outCondition()) {
            return true;
         }
      }

      for (int var2 = 0; var2 < this.lConditions.size(); var2++) {
         if (this.lConditions.get(var2).conditionType != Event_Type.OR
            && (
               this.lConditions.get(var2).conditionType == Event_Type.AND
                  ? !this.lConditions.get(var2).outCondition()
                  : this.lConditions.get(var2).conditionType == Event_Type.NOT && this.lConditions.get(var2).outCondition()
            )) {
            return false;
         }
      }

      return true;
   }

   public final String getTriggerText() {
      String out = "";

      for (int i = 0; i < this.lConditions.size() & i < 5; i++) {
         out = out + "" + this.lConditions.get(i).getConditionText() + " ";
      }

      return out;
   }
}
