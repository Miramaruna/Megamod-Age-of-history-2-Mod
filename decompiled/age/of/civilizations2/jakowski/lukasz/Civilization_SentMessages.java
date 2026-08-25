package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Civilization_SentMessages implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iToCivID;
   public Message_Type messageType = Message_Type.GIFT;
   public int iSentInTurnID;

   public Civilization_SentMessages(int iToCivID, Message_Type messageType) {
      this.iToCivID = iToCivID;
      this.messageType = messageType;
      this.iSentInTurnID = Game_Calendar.TURN_ID;
   }
}
