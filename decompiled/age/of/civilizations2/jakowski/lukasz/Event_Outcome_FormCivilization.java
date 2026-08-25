package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_FormCivilization extends Event_Outcome {
   public int iCivID = -1;
   public String sTag = "";

   Event_Outcome_FormCivilization() {
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
   public String getText() {
      return this.sTag;
   }

   @Override
   public void setText(String nText) {
      this.sTag = nText;
   }

   @Override
   public boolean updateCivIDAfterRemove(int nRemovedCivID) {
      if (this.iCivID == nRemovedCivID) {
         this.iCivID = -1;
         return true;
      } else {
         if (nRemovedCivID < this.iCivID) {
            this.iCivID--;
         }

         return false;
      }
   }

   @Override
   public void outcomeAction() {
      if (this.canMakeAction()) {
         int civAlreadyIsAdded = -1;
         int i = 0;

         while (true) {
            if (i < CFG.game.getCivsSize()) {
               if (!CFG.game.getCiv(i).getCivTag().equals(this.sTag)) {
                  i++;
                  continue;
               }

               civAlreadyIsAdded = i;
               if (CFG.game.getCiv(i).getNumOfProvinces() > 0 || i == this.getCivID()) {
                  return;
               }
            }

            if (civAlreadyIsAdded > 0) {
               String tempTag = CFG.game.getCiv(this.getCivID()).getCivTag();
               CFG.game.getCiv(this.getCivID()).setCivTag(CFG.game.getCiv(civAlreadyIsAdded).getCivTag());
               CFG.game.getCiv(civAlreadyIsAdded).setCivTag(tempTag);
               CFG.game.getCiv(this.getCivID()).loadFlag();
               CFG.game.getCiv(civAlreadyIsAdded).loadFlag();

               for (int i3 = 0; i3 < CFG.game.getCiv(this.getCivID()).getCivRegionsSize(); i3++) {
                  CFG.game.getCiv(this.getCivID()).getCivRegion(i3).buildScaleOfText();
               }

               for (int var29 = 0; var29 < CFG.game.getCiv(civAlreadyIsAdded).getCivRegionsSize(); var29++) {
                  CFG.game.getCiv(civAlreadyIsAdded).getCivRegion(var29).buildScaleOfText();
               }

               int tColor = CFG.game.getCiv(this.getCivID()).getR();
               CFG.game.getCiv(this.getCivID()).setR(CFG.game.getCiv(civAlreadyIsAdded).getR());
               CFG.game.getCiv(civAlreadyIsAdded).setR(tColor);
               tColor = CFG.game.getCiv(this.getCivID()).getG();
               CFG.game.getCiv(this.getCivID()).setG(CFG.game.getCiv(civAlreadyIsAdded).getG());
               CFG.game.getCiv(civAlreadyIsAdded).setG(tColor);
               tColor = CFG.game.getCiv(this.getCivID()).getB();
               CFG.game.getCiv(this.getCivID()).setB(CFG.game.getCiv(civAlreadyIsAdded).getB());
               CFG.game.getCiv(civAlreadyIsAdded).setB(tColor);
               CFG.game.getCiv(this.getCivID()).updateCivilizationIdeology();
               CFG.game.getCiv(civAlreadyIsAdded).updateCivilizationIdeology();
               CFG.gameNewGame.updateFormableCivilizations(this.getCivID());
               CFG.gameNewGame.updateFormableCivilizations(civAlreadyIsAdded);

               for (int i2 = 0; i2 < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); i2++) {
                  CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i2)).setFromCivID(0);
               }

               for (int var18 = 0; var18 < CFG.game.getCiv(civAlreadyIsAdded).getNumOfProvinces(); var18++) {
                  CFG.game.getProvince(CFG.game.getCiv(civAlreadyIsAdded).getProvinceID(var18)).setFromCivID(0);
               }

               for (int var19 = 0; var19 < CFG.game.getPlayersSize(); var19++) {
                  if (CFG.game.getPlayer(var19).getCivID() == this.getCivID() || CFG.game.getPlayer(var19).getCivID() == civAlreadyIsAdded) {
                     CFG.game.getPlayer(var19).loadPlayersFlag();
                  }
               }

               CFG.eventsManager.swapIDsOfCivs(this.getCivID(), civAlreadyIsAdded);
               if (CFG.game.getActiveProvinceID() >= 0) {
                  int tID = CFG.game.getActiveProvinceID();
                  CFG.game.setActiveProvinceID(-1);
                  CFG.game.setActiveProvinceID(tID);
               }
            } else {
               CFG.game.getCiv(this.getCivID()).setCivTag(this.getText());
               CFG.game.getCiv(this.getCivID()).setCivName(CFG.langManager.getCiv(CFG.game.getCiv(this.getCivID()).getCivTag()));
               CFG.game.getCiv(this.getCivID()).loadFlag();

               for (int var17 = 0; var17 < CFG.game.getCiv(this.getCivID()).getCivRegionsSize(); var17++) {
                  CFG.game.getCiv(this.getCivID()).getCivRegion(var17).buildScaleOfText();
               }

               CFG.game.getCiv(this.getCivID()).updateCivilizationIdeology();

               try {
                  try {
                     try {
                        FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + this.getText());
                        Civilization_GameData3 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        CFG.game.getCiv(this.getCivID()).setR(tempCivData.getR());
                        CFG.game.getCiv(this.getCivID()).setG(tempCivData.getG());
                        CFG.game.getCiv(this.getCivID()).setB(tempCivData.getB());
                     } catch (GdxRuntimeException var13) {
                        FileHandle fileCivx = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(this.getText()));
                        Civilization_GameData3 tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                        int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(this.getText());
                        Color tempColor = CFG.getColorMixed(
                           new Color(tempCivDatax.getR() / 255.0F, tempCivDatax.getG() / 255.0F, tempCivDatax.getB() / 255.0F, 0.775F),
                           new Color(
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                              0.225F
                           )
                        );
                        CFG.game.getCiv(this.getCivID()).setR((int)(tempColor.r * 255.0F));
                        CFG.game.getCiv(this.getCivID()).setG((int)(tempColor.g * 255.0F));
                        CFG.game.getCiv(this.getCivID()).setB((int)(tempColor.b * 255.0F));
                     }
                  } catch (GdxRuntimeException var14) {
                     try {
                        FileHandle fileCiv = Gdx.files.local("game/civilizations/" + this.getText());
                        Civilization_GameData3 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        CFG.game.getCiv(this.getCivID()).setR(tempCivData.getR());
                        CFG.game.getCiv(this.getCivID()).setG(tempCivData.getG());
                        CFG.game.getCiv(this.getCivID()).setB(tempCivData.getB());
                     } catch (GdxRuntimeException var12) {
                        try {
                           FileHandle fileCivx = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(this.getText()));
                           Civilization_GameData3 tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                           int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(this.getText());
                           Color tempColor = CFG.getColorMixed(
                              new Color(tempCivDatax.getR() / 255.0F, tempCivDatax.getG() / 255.0F, tempCivDatax.getB() / 255.0F, 0.775F),
                              new Color(
                                 CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                                 CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                                 CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                                 0.225F
                              )
                           );
                           CFG.game.getCiv(this.getCivID()).setR((int)(tempColor.r * 255.0F));
                           CFG.game.getCiv(this.getCivID()).setG((int)(tempColor.g * 255.0F));
                           CFG.game.getCiv(this.getCivID()).setB((int)(tempColor.b * 255.0F));
                        } catch (GdxRuntimeException var11) {
                           try {
                              if (CFG.isAndroid()) {
                                 try {
                                    FileHandle fileCivxx = Gdx.files
                                       .local(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(this.getText())
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(this.getText())
                                       );
                                    Civilization_GameData3 tempCivDataxx = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                                    CFG.game.getCiv(this.getCivID()).setR(tempCivDataxx.getR());
                                    CFG.game.getCiv(this.getCivID()).setG(tempCivDataxx.getG());
                                    CFG.game.getCiv(this.getCivID()).setB(tempCivDataxx.getB());
                                 } catch (GdxRuntimeException var9) {
                                    FileHandle fileCivxxx = Gdx.files
                                       .internal(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(this.getText())
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(this.getText())
                                       );
                                    Civilization_GameData3 tempCivDataxxx = (Civilization_GameData3)CFG.deserialize(fileCivxxx.readBytes());
                                    CFG.game.getCiv(this.getCivID()).setR(tempCivDataxxx.getR());
                                    CFG.game.getCiv(this.getCivID()).setG(tempCivDataxxx.getG());
                                    CFG.game.getCiv(this.getCivID()).setB(tempCivDataxxx.getB());
                                 }
                              } else {
                                 FileHandle fileCivxx = Gdx.files
                                    .internal(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(this.getText())
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(this.getText())
                                    );
                                 Civilization_GameData3 tempCivDataxx = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                                 CFG.game.getCiv(this.getCivID()).setR(tempCivDataxx.getR());
                                 CFG.game.getCiv(this.getCivID()).setG(tempCivDataxx.getG());
                                 CFG.game.getCiv(this.getCivID()).setB(tempCivDataxx.getB());
                              }
                           } catch (GdxRuntimeException var10) {
                           }
                        }
                     }
                  }
               } catch (ClassNotFoundException var15) {
               } catch (IOException var16) {
               }

               CFG.gameNewGame.updateFormableCivilizations(this.getCivID());

               for (int i4 = 0; i4 < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); i4++) {
                  CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i4)).setFromCivID(0);
               }

               if (CFG.game.getActiveProvinceID() >= 0) {
                  int tID = CFG.game.getActiveProvinceID();
                  CFG.game.setActiveProvinceID(-1);
                  CFG.game.setActiveProvinceID(tID);
               }
            }
            break;
         }
      }
   }

   public boolean canMakeAction() {
      try {
         for (int i = 0; i < CFG.game.getCivsSize(); i++) {
            if (CFG.game.getCiv(i).getCivTag().equals(this.sTag)) {
               if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                  return false;
               }
               break;
            }
         }

         return this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize() && !this.sTag.equals("");
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   public List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();
         if (this.canMakeAction()) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("FormCivilization") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID(), 0, CFG.PADDING));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID()).getCivName()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(" -> ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.getCiv(this.getText())));
            tElements.add(new MenuElement_Hover_v2_Element2(tData));
            tData.clear();
         }

         return tElements;
      } catch (IndexOutOfBoundsException var3) {
      } catch (NullPointerException var4) {
      }

      return new ArrayList<>();
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("FormCivilization")
            + ": "
            + CFG.game.getCiv(this.getCivID()).getCivName()
            + " -> "
            + CFG.langManager.getCiv(this.getText());
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("FormCivilization");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_FORM_CIV);
   }
}
