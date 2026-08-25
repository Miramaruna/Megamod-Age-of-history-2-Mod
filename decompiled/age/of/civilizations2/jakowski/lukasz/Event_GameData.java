package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sEventName = "";
   public String sEventTag;
   public String sEventPicture = "";
   public int fEventPictureWidth = 512;
   public int fEventPictureHeight = 96;
   private boolean worldEvent = false;
   public boolean wasFired = false;
   public boolean wasTriedToRunOnce = false;
   public boolean repeatable = false;
   public int iCivID_Recipient = -1;
   public Event_PopUp event_PopUp = new Event_PopUp();
   public List<Event_Trigger> lTriggers = new ArrayList<>();
   public List<Event_Decision> lDecisions = new ArrayList<>();
   public Event_Date eventDate_Since = new Event_Date();
   public Event_Date eventDate_Until = new Event_Date();

   public Event_GameData() {
      this.sEventTag = System.currentTimeMillis() + CFG.extraRandomTag();
      this.lDecisions = new ArrayList<>();
   }

   public final void checkTriggers() {
      for (int i = 0; i < this.getTriggersSize(); i++) {
         if (this.getTrigger(i).lConditions.size() == 0) {
            this.removeTrigger(i--);
         }
      }
   }

   public final void checkDecisions() {
      for (int i = 0; i < this.lDecisions.size(); i++) {
         if (this.lDecisions.get(i).lOutcomes.size() == 0 && this.lDecisions.get(i).sTitle.equals("")) {
            this.lDecisions.remove(i--);
         }
      }
   }

   public final String getEventTag() {
      return this.sEventTag;
   }

   public final void setEventTag(String sEventTag) {
      this.sEventTag = sEventTag;
   }

   public final String getEventName() {
      return this.sEventName;
   }

   public final void setEventName(String sEventName) {
      this.sEventName = sEventName;
   }

   public final int getCivID() {
      return this.iCivID_Recipient;
   }

   public final void setCivID(int iCivID_Recipient) {
      this.iCivID_Recipient = iCivID_Recipient;
   }

   public final boolean getWasFired() {
      return this.wasFired;
   }

   public final void setWasFired(boolean wasFired) {
      this.wasFired = wasFired;
   }

   public final Event_Date getEventDate_Since() {
      return this.eventDate_Since;
   }

   public final void setEventDate_Since(int nDay, int nMonth, int nYear) {
      this.eventDate_Since.iEventDay = nDay;
      this.eventDate_Since.iEventMonth = nMonth;
      this.eventDate_Since.iEventYear = nYear;
   }

   public final Event_Date getEventDate_Until() {
      return this.eventDate_Until;
   }

   public final void setEventDate_Until(int nDay, int nMonth, int nYear) {
      this.eventDate_Until.iEventDay = nDay;
      this.eventDate_Until.iEventMonth = nMonth;
      this.eventDate_Until.iEventYear = nYear;
   }

   public final Event_PopUp getEvent_PopUp() {
      return this.event_PopUp;
   }

   public final int getTriggersSize() {
      return this.lTriggers.size();
   }

   public final Event_Trigger getTrigger(int i) {
      return this.lTriggers.get(i);
   }

   public final void addNewTrigger() {
      this.lTriggers.add(new Event_Trigger());
   }

   public final void removeTrigger(int i) {
      this.lTriggers.remove(i);
   }

   public final String getEventPicture() {
      return this.sEventPicture;
   }

   public final void setEventPicture(String sEventPicture) {
      this.sEventPicture = sEventPicture;
   }

   public final boolean getWasTriedToRunOnce() {
      return this.wasTriedToRunOnce;
   }

   public final void setWasTriedToRunOnce(boolean wasTriedToRunOnce) {
      this.wasTriedToRunOnce = wasTriedToRunOnce;
   }

   public final boolean getRepeatable() {
      return this.repeatable;
   }

   public final void setRepeatable(boolean repeatable) {
      this.repeatable = repeatable;
   }

   public boolean getWorldEvent() {
      return this.worldEvent;
   }

   public void setWorldEvent(boolean worldEvent) {
      this.worldEvent = worldEvent;
   }

   public final int getEventPictureWidth() {
      return this.fEventPictureWidth;
   }

   public final void setEventPictureWidth(int fEventPictureWidth) {
      this.fEventPictureWidth = fEventPictureWidth;
   }

   public final int getEventPictureHeight() {
      return this.fEventPictureHeight;
   }

   public final void setEventPictureHeight(int fEventPictureHeight) {
      this.fEventPictureHeight = fEventPictureHeight;
   }
}
