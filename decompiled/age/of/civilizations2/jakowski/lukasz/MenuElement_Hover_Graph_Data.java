package age.of.civilizations2.jakowski.lukasz;

public class MenuElement_Hover_Graph_Data {
   public int iCivID;
   public String sText;

   public MenuElement_Hover_Graph_Data(int iCivID, String sText) {
      this.iCivID = iCivID;
      this.sText = sText;
   }

   public final int getCivID() {
      return this.iCivID;
   }

   public final void setCivID(int iCivID) {
      this.iCivID = iCivID;
   }

   public final String getText() {
      return this.sText;
   }

   public final void setText(String sText) {
      this.sText = sText;
   }
}
