/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public final class Event_Outcome_RenameCity
extends Event_Outcome {
    private int provId = -1;
    private String newName = CFG.langManager.get("EnterNewName");

    Event_Outcome_RenameCity() {
    }

    public boolean canMakeAction() {
        return this.provId > 0;
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_RENAME_CITY);
    }

    @Override
    public String getConditionText() {
        String var1;
        try {
            var1 = CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getProvId() > -1 ? "\u041f\u0435\u0440\u0435\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u0442\u044c \u0433\u043e\u0440\u043e\u0434 (" + CFG.game.getProvince(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getProvId()).getName() + ") \u043d\u0430: " + CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getNewName() : "\u041f\u0435\u0440\u0435\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u0442\u044c \u0433\u043e\u0440\u043e\u0434 (\u041d\u0435 \u0443\u043a\u0430\u0437\u0430\u043d) \u043d\u0430: " + CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getNewName();
        }
        catch (IndexOutOfBoundsException var2) {
            var1 = "\u041f\u0435\u0440\u0435\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u0442\u044c \u0433\u043e\u0440\u043e\u0434";
        }
        return var1;
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        tData.add(new MenuElement_Hover_v2_Element_Type_Text("\u041f\u0435\u0440\u0435\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u0442\u044c \u0433\u043e\u0440\u043e\u0434 ("));
        tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getProvId()).getName(), Color.CORAL));
        tData.add(new MenuElement_Hover_v2_Element_Type_Text(")"));
        tData.add(new MenuElement_Hover_v2_Element_Type_Text(" \u043d\u0430: "));
        tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getNewName(), Color.CORAL));
        tElements.add(new MenuElement_Hover_v2_Element2(tData));
        tData.clear();
        return tElements;
    }

    @Override
    public String getNewName() {
        return this.newName;
    }

    @Override
    public void setNewName(String newName) {
        this.newName = newName;
    }

    @Override
    public int getProvId() {
        return this.provId;
    }

    @Override
    public void setProvId(int provId) {
        this.provId = provId;
    }

    @Override
    public void outcomeAction() {
        if (this.canMakeAction()) {
            CFG.game.getProvince(this.provId).setName(this.newName);
            for (int i = 0; i < CFG.game.getProvince(this.provId).getCitiesSize(); ++i) {
                CFG.game.getProvince(this.provId).getCity(0).setCityName(this.newName);
            }
        }
    }
}
