/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import java.util.ArrayList;
import java.util.List;

class Event_Outcome_Music
extends Event_Outcome {
    public int iValue = 0;
    public String pathToSound;
    public int civID;

    Event_Outcome_Music() {
    }

    @Override
    public int getValue() {
        return this.iValue;
    }

    @Override
    public void setValue(int nValue) {
        this.iValue = nValue;
    }

    @Override
    public void outcomeAction() {
        if (this.canMakeAction()) {
            CFG.soundsManager.playOutcomeMusic(this.pathToSound);
        }
    }

    public boolean canMakeAction() {
        try {
            return CFG.game.getCiv(this.civID).getControlledByPlayer();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return false;
        }
    }

    @Override
    public void setCivID(int civID) {
        this.civID = civID;
    }

    @Override
    public int getCivID() {
        return this.civID;
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("StartMusic") + ": " + this.getSound();
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("StartMusic");
        }
    }

    @Override
    public String getSound() {
        return this.pathToSound;
    }

    @Override
    public void setPathToSound(String path) {
        this.pathToSound = path;
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            if (this.canMakeAction()) {
                tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PathToSound") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(this.pathToSound));
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
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_MUSIC);
    }
}
