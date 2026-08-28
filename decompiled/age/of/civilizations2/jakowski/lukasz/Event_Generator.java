/*
 * Ported from UWUT engine (CFR-decompiled, generateEvent_Revolt control flow reconstructed).
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_DecisionTaken;
import age.of.civilizations2.jakowski.lukasz.Event_Decision;
import age.of.civilizations2.jakowski.lukasz.Event_GameData;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome_AddCore;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome_ChangeIdeology;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome_ChangeOwner;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome_DeclareWar;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome_LiberateVassal;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome_Occupy;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome_UpdateHappinessOfCiv;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Province;
import java.util.ArrayList;
import java.util.List;

public class Event_Generator {
    protected static final String generateEventDecitionTaken(String str, int i) {
        return str + "_" + i;
    }

    protected static final String generateEventTag(String str) {
        return str + "_" + Game_Calendar.TURN_ID;
    }

    public static final void generateEvent_Capitulation(int civID, int civID2) {
        Event_GameData nEvent = new Event_GameData();
        nEvent.setCivID(CFG.PLAYER_TURNID);
        nEvent.setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        nEvent.setEventDate_Until(1, 1, 65536);
        nEvent.setEventName("Capitulation");
        nEvent.setEventPicture("capitulation.png");
        nEvent.setEventTag(Event_Generator.generateEventTag("SPlus_EventCapitulation"));
        nEvent.getEvent_PopUp().sText = CFG.game.getCiv(civID2).getCivName() + " has capitulated after so much bloody sieges, their future isnt looking good.";
        nEvent.addNewTrigger();
        nEvent.getTrigger(0);
        if (CFG.ideologiesManager.getRealTag(CFG.game.getCiv(civID2).getCivTag()).equals(CFG.ideologiesManager.getRealTag(CFG.game.getCiv(civID).getCivTag()))) {
            ArrayList<Integer> provinces = new ArrayList<Integer>();
            Civilization civ = CFG.game.getCiv(civID);
            for (int i = 0; i != civ.getNumOfProvinces(); ++i) {
                int provinceID = civ.getProvinceID(i);
                if (CFG.game.getProvince(provinceID).getTrueOwnerOfProvince() != civID2) continue;
                provinces.add(provinceID);
            }
            Event_Decision event_Decision2 = new Event_Decision();
            event_Decision2.sTitle = "Unification of nation!";
            nEvent.setEventPicture("civilwar.png");
            List<Event_Outcome> lOutcomes2 = event_Decision2.lOutcomes;
            Event_Outcome_AddCore event_Outcome_AddCore = new Event_Outcome_AddCore();
            event_Outcome_AddCore.setCivID(civID);
            event_Outcome_AddCore.setProvinces(provinces);
            lOutcomes2.add(event_Outcome_AddCore);
            Event_Outcome_Occupy event_Outcome_Occupy = new Event_Outcome_Occupy();
            event_Outcome_Occupy.setCivID(civID2);
            event_Outcome_Occupy.setCivID2(civID);
            event_Outcome_Occupy.setProvinces(provinces);
            lOutcomes2.add(event_Outcome_Occupy);
            Event_Outcome_ChangeOwner event_Outcome_ChangeOwner = new Event_Outcome_ChangeOwner();
            event_Outcome_ChangeOwner.setCivID(civID);
            event_Outcome_ChangeOwner.setCivID2(civID2);
            event_Outcome_ChangeOwner.setProvinces(provinces);
            lOutcomes2.add(event_Outcome_ChangeOwner);
            nEvent.lDecisions.add(event_Decision2);
        } else {
            Event_Decision event_Decision3 = new Event_Decision();
            event_Decision3.sTitle = CFG.langManager.get("Fine");
            nEvent.lDecisions.add(event_Decision3);
        }
        CFG.eventsManager.addEvent(nEvent);
    }

    protected static final void generateEvent_Revolt(int civID, int nCivB) {
        Civilization civ = CFG.game.getCiv(nCivB);
        int numOfProvinces = civ.getNumOfProvinces();
        for (int i = 0; i != numOfProvinces; ++i) {
            Province province = CFG.game.getProvince(civ.getProvinceID(i));
            province.setCivID(civID, true);
            int ideologyID = CFG.game.getCiv(province.getTrueOwnerOfProvince()).getIdeologyID();
            if (CFG.ideologiesManager.getIdeology((int)ideologyID).REVOLUTIONARY) {
                province.setCivID_Just(civID);
            }
        }
        List<Boolean> canChangeToIdeology = CFG.ideologiesManager.canChangeToIdeology(civID);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int j = 0; j < canChangeToIdeology.size(); ++j) {
            if (!canChangeToIdeology.get(j).booleanValue()) continue;
            list.add(j);
        }
        int n2 = CFG.game.getCiv(civID).getIdeologyID();
        int size = list.size();
        if (size != 0) {
            // empty if block
        }
        Event_GameData nEvent = new Event_GameData();
        nEvent.setCivID(civID);
        nEvent.setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        nEvent.setEventDate_Until(1, 1, 65536);
        nEvent.setEventName(CFG.langManager.get("Revolt"));
        String ai_TYPE = CFG.ideologiesManager.getIdeology((int)n2).AI_TYPE;
        String eventPicture = ai_TYPE.equals("COMMUNISM") ? "20.png" : (ai_TYPE.equals("FASCISM") ? "30.png" : (ai_TYPE.equals("MONARCHY") ? "50.png" : (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)civID).getIdeologyID()).AI_TYPE.equals("MONARCHY") ? "51.png" : "11.png")));
        nEvent.setEventPicture(eventPicture);
        nEvent.setEventTag(Event_Generator.generateEventTag("SPlus_EventRevolt"));
        nEvent.getEvent_PopUp().sText = "Revolution has happened.\nNow we have goverment of " + CFG.ideologiesManager.getIdeology(n2).getName() + ".";
        nEvent.addNewTrigger();
        nEvent.getTrigger(0);
        int iaiChance = 100;
        int iaiChance2 = 0;
        if (CFG.game.getCiv(civID).getPuppetOfCivID() != civID) {
            int ideologyID2 = CFG.game.getCiv(CFG.game.getCiv(civID).getPuppetOfCivID()).getIdeologyID();
            String ai_TYPE2 = CFG.ideologiesManager.getIdeology((int)n2).AI_TYPE;
            boolean useHigh = CFG.ideologiesManager.getIdeology((int)ideologyID2).AI_TYPE.equals(ai_TYPE2)
                && !ai_TYPE2.equals("COMMUNISM")
                && !ai_TYPE2.equals("FASCISM");
            if (useHigh) {
                int n3 = (int)CFG.game.getCiv(nCivB).getVassalLiberityDesire();
                int puppetOf = CFG.game.getCiv(civID).getPuppetOfCivID();
                iaiChance2 = n3 + 80 - DiplomacyManager.getAllianceProposal_Negative_Opinion(puppetOf, civID) - DiplomacyManager.getAllianceProposal_Negative_CivIsAtWar(puppetOf) - DiplomacyManager.getAllianceProposal_Negative_HaveACore(puppetOf, civID) - DiplomacyManager.getAllianceProposal_Negative_Distance(civID, puppetOf) - DiplomacyManager.getAllianceProposale_CivStrength(civID, puppetOf);
                iaiChance = 100 - iaiChance2;
            } else {
                int n4 = (int)CFG.game.getCiv(nCivB).getVassalLiberityDesire();
                int puppetOf = CFG.game.getCiv(civID).getPuppetOfCivID();
                iaiChance2 = n4 + 32 - DiplomacyManager.getAllianceProposal_Negative_Distance(civID, puppetOf) - DiplomacyManager.getAllianceProposale_CivStrength(civID, puppetOf);
                iaiChance = 100 - iaiChance2;
            }
        }
        Event_Decision event_Decision = new Event_Decision();
        event_Decision.iAIChance = iaiChance;
        event_Decision.sTitle = "Viva la revolution!";
        List<Event_Outcome> lOutcomes = event_Decision.lOutcomes;
        Event_Outcome_ChangeIdeology event_Outcome_ChangeIdeology = new Event_Outcome_ChangeIdeology();
        event_Outcome_ChangeIdeology.setCivID(civID);
        event_Outcome_ChangeIdeology.setValue(n2);
        lOutcomes.add(event_Outcome_ChangeIdeology);
        Event_Outcome_UpdateHappinessOfCiv event_Outcome_UpdateHappinessOfCiv = new Event_Outcome_UpdateHappinessOfCiv();
        event_Outcome_UpdateHappinessOfCiv.setCivID(civID);
        event_Outcome_UpdateHappinessOfCiv.setValue(25);
        lOutcomes.add(event_Outcome_UpdateHappinessOfCiv);
        nEvent.lDecisions.add(event_Decision);
        if (CFG.game.getCiv(civID).getPuppetOfCivID() != civID) {
            Event_Generator.generateEvent_Revolt_OwnerReact(civID, Event_Generator.generateEventTag("SPlus_EventRevolt"));
            Event_Decision event_Decision2 = new Event_Decision();
            event_Decision2.iAIChance = iaiChance2;
            event_Decision2.sTitle = "Declare independence!";
            List<Event_Outcome> lOutcomes2 = event_Decision2.lOutcomes;
            Event_Outcome_ChangeIdeology event_Outcome_ChangeIdeology2 = new Event_Outcome_ChangeIdeology();
            event_Outcome_ChangeIdeology2.setCivID(civID);
            event_Outcome_ChangeIdeology2.setValue(n2);
            lOutcomes2.add(event_Outcome_ChangeIdeology2);
            Event_Outcome_UpdateHappinessOfCiv event_Outcome_UpdateHappinessOfCiv2 = new Event_Outcome_UpdateHappinessOfCiv();
            event_Outcome_UpdateHappinessOfCiv2.setCivID(civID);
            event_Outcome_UpdateHappinessOfCiv2.setValue(35);
            lOutcomes2.add(event_Outcome_UpdateHappinessOfCiv2);
            Event_Outcome_LiberateVassal event_Outcome_LiberateVassal = new Event_Outcome_LiberateVassal();
            event_Outcome_LiberateVassal.setCivID(civID);
            lOutcomes2.add(event_Outcome_LiberateVassal);
            nEvent.lDecisions.add(event_Decision2);
        }
        CFG.eventsManager.addEvent(nEvent);
    }

    public static final void generateEvent_Revolt_OwnerReact(int n, String s) {
        int puppetOfCivID = CFG.game.getCiv(n).getPuppetOfCivID();
        Event_GameData nEvent = new Event_GameData();
        nEvent.setCivID(puppetOfCivID);
        nEvent.setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        nEvent.setEventDate_Until(1, 1, 65536);
        nEvent.setEventName("Declaretion of independance");
        nEvent.setEventPicture("11.png");
        nEvent.setEventTag(Event_Generator.generateEventTag("SPlus_EventReact"));
        nEvent.getEvent_PopUp().sText = CFG.game.getCiv(n).getCivName() + " has declared independence.";
        nEvent.addNewTrigger();
        List<Event_Conditions> lConditions = nEvent.getTrigger((int)0).lConditions;
        Event_Conditions_DecisionTaken event_Conditions_DecisionTaken = new Event_Conditions_DecisionTaken();
        event_Conditions_DecisionTaken.setCivID(n);
        event_Conditions_DecisionTaken.setText(Event_Generator.generateEventDecitionTaken(s, 1));
        lConditions.add(event_Conditions_DecisionTaken);
        int iaiChance = (int)(CFG.game.getCiv(n).getWarWeariness() * 0.8f) - DiplomacyManager.getAllianceProposal_Negative_Distance(n, puppetOfCivID) + DiplomacyManager.getAllianceProposale_CivStrength(n, puppetOfCivID);
        Event_Decision event_Decision = new Event_Decision();
        event_Decision.iAIChance = 100 - iaiChance;
        event_Decision.sTitle = "We must stop it!";
        List<Event_Outcome> lOutcomes = event_Decision.lOutcomes;
        Event_Outcome_DeclareWar event_Outcome_DeclareWar = new Event_Outcome_DeclareWar();
        event_Outcome_DeclareWar.setCivID(puppetOfCivID);
        event_Outcome_DeclareWar.setCivID2(n);
        lOutcomes.add(event_Outcome_DeclareWar);
        nEvent.lDecisions.add(event_Decision);
        Event_Decision event_Decision2 = new Event_Decision();
        event_Decision2.iAIChance = iaiChance;
        event_Decision2.sTitle = "Let them go";
        nEvent.lDecisions.add(event_Decision2);
        CFG.eventsManager.addEvent(nEvent);
    }
}
