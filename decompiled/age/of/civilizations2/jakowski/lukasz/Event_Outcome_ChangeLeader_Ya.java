/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome;
import age.of.civilizations2.jakowski.lukasz.LeaderOfCiv_GameData;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import java.util.ArrayList;
import java.util.List;

class Event_Outcome_ChangeLeader_Ya
extends Event_Outcome {
    public int iCivID = -1;
    public LeaderOfCiv_GameData nLeader = new LeaderOfCiv_GameData();

    Event_Outcome_ChangeLeader_Ya() {
    }

    @Override
    public int getCivID() {
        return this.iCivID;
    }

    @Override
    public void setCivID(int nCivID) {
        this.iCivID = nCivID;
    }

    @Override
    public LeaderOfCiv_GameData getLeader() {
        return this.nLeader;
    }

    @Override
    public void setLeader(LeaderOfCiv_GameData nLeader) {
        this.nLeader = nLeader;
    }

    @Override
    public boolean updateCivIDAfterRemove(int nRemovedCivID) {
        if (this.iCivID == nRemovedCivID) {
            this.iCivID = -1;
            return true;
        }
        if (nRemovedCivID < this.iCivID) {
            --this.iCivID;
        }
        return false;
    }

    @Override
    public void outcomeAction() {
        if (this.canMakeAction()) {
            CFG.game.getCiv((int)this.getCivID()).civGameData.setLeader(this.nLeader);
            if (CFG.game.getCiv(this.getCivID()).getControlledByPlayer()) {
                CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }
        }
    }

    public boolean canMakeAction() {
        try {
            return this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize() && CFG.game.getCiv(this.getCivID()).getNumOfProvinces() > 0;
        }
        catch (IndexOutOfBoundsException var2) {
            return false;
        }
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("ChangeLeader") + ": " + CFG.game.getCiv(this.getCivID()).getCivName() + ", " + this.nLeader.getName();
        }
        catch (IndexOutOfBoundsException var2) {
            return CFG.langManager.get("ChangeLeader");
        }
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            if (this.canMakeAction()) {
                tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangeLeader") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + this.nLeader.getName()));
                tElements.add(new MenuElement_Hover_v2_Element2(tData));
                tData.clear();
            }
            return tElements;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return new ArrayList<MenuElement_Hover_v2_Element2>();
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_LEADER);
    }
}
