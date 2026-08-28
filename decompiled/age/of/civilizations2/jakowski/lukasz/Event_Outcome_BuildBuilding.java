/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Buildings;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import java.util.ArrayList;
import java.util.List;

public final class Event_Outcome_BuildBuilding
extends Event_Outcome {
    private List<Integer> provinces;
    private Buildings building;
    private int buildingLevel = 0;

    Event_Outcome_BuildBuilding() {
    }

    public boolean canMakeAction() {
        return true;
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_BUILDBUILDING);
        System.out.println(this.getProvinces().size());
    }

    @Override
    public void setBuilding(Buildings building) {
        this.building = building;
    }

    @Override
    public void setBuildingLevel(int buildingLevel) {
        this.buildingLevel = buildingLevel;
    }

    @Override
    public void setProvinces(List<Integer> provinces) {
        this.provinces = provinces;
        this.editViewID();
    }

    @Override
    public int getProvId() {
        return this.provinces.get(0);
    }

    @Override
    public List<Integer> getProvinces() {
        return this.provinces;
    }

    @Override
    public Buildings getBuilding() {
        return this.building;
    }

    @Override
    public String getConditionText() {
        String var1;
        try {
            var1 = "\u041f\u043e\u0441\u0442\u0440\u043e\u0438\u0442\u044c \u0437\u0434\u0430\u043d\u0438\u0435 " + this.getBuilding().toString() + " (" + this.getBuildingLevel() + ") \u0432 " + CFG.game.getProvince(this.getProvId()).getName() + " (" + this.getProvId() + ")";
        }
        catch (IndexOutOfBoundsException var2) {
            var1 = "\u041f\u043e\u0441\u0442\u0440\u043e\u0438\u0442\u044c \u0437\u0434\u0430\u043d\u0438\u0435";
        }
        return var1;
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("\u041f\u043e\u0441\u0442\u0440\u043e\u0438\u0442\u044c \u0437\u0434\u0430\u043d\u0438\u0435: ")));
        tData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getBuilding().toString() + "(" + this.getBuildingLevel() + ") \u0432 " + CFG.game.getProvince(this.getProvId()).getName() + " (" + this.getProvId() + ")"));
        tElements.add(new MenuElement_Hover_v2_Element2(tData));
        tData.clear();
        return tElements;
    }

    @Override
    public void outcomeAction() {
        if (this.canMakeAction()) {
            block9: for (int i = 0; i <= this.provinces.size(); ++i) {
                switch (this.getBuilding()) {
                    case FORT: {
                        CFG.game.getProvince(i).setLevelOfFort(this.getBuildingLevel());
                        continue block9;
                    }
                    case WATCH_TOWER: {
                        CFG.game.getProvince(i).setLevelOfWatchTower(this.getBuildingLevel());
                        continue block9;
                    }
                    case FARM: {
                        CFG.game.getProvince(i).setLevelOfFarm(this.getBuildingLevel());
                        continue block9;
                    }
                    case LIBRARY: {
                        CFG.game.getProvince(i).setLevelOfFarm(this.getBuildingLevel());
                        continue block9;
                    }
                    case WORKSHOP: {
                        CFG.game.getProvince(i).setLevelOfWorkshop(this.getBuildingLevel());
                        continue block9;
                    }
                    case ARMOURY: {
                        CFG.game.getProvince(i).setLevelOfArmoury(this.getBuildingLevel());
                        continue block9;
                    }
                    case SUPPLY: {
                        CFG.game.getProvince(i).setLevelOfSupply(this.getBuildingLevel());
                    }
                }
            }
        }
    }

    @Override
    public int getBuildingLevel() {
        return this.buildingLevel;
    }
}
