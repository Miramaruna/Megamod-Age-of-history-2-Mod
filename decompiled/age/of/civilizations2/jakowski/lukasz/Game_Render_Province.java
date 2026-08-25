package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game_Render_Province {
   public static Game_Render_Province.DrawProvinces drawProvinces;
   public static long PROVINCE_COLOR_ANIMATION_TIMER = 0L;
   public static final float ALPHA_PEACE_TREATY_PROVINCES = 0.25F;

   Game_Render_Province() {
   }

   public static final Color getProvince_PortColor(int nProvinceID) {
      switch (CFG.game.getProvince(nProvinceID).getLevelOfPort()) {
         case -1:
            return CFG.COLOR_PORT_m1;
         case 0:
            return CFG.COLOR_PORT_0;
         default:
            return CFG.COLOR_PORT_1;
      }
   }

   public static final Color getProvince_FortColor(int nProvinceID) {
      switch (CFG.game.getProvince(nProvinceID).getLevelOfFort()) {
         case -1:
         case 0:
            return CFG.COLOR_PORT_m1;
         case 1:
            return CFG.COLOR_FORT_1;
         default:
            return CFG.COLOR_FORT_2;
      }
   }

   public static final Color getProvince_WatchTowerColor(int nProvinceID) {
      switch (CFG.game.getProvince(nProvinceID).getLevelOfFort()) {
         case -1:
         case 0:
            return CFG.COLOR_PORT_m1;
         default:
            return CFG.COLOR_WATCH_TOWER;
      }
   }

   public static final void updateDrawProvinces() {
      if (CFG.menuManager.getInGameView()) {
         if (CFG.viewsManager.getActiveViewID() >= 0) {
            drawProvinces = CFG.viewsManager.getActiveView().drawProvinces;
         } else {
            updateDrawProvinces_Standard();
         }
      } else if (CFG.menuManager.getInCreateNewGame()) {
         if (CFG.viewsManager.getActiveViewID() >= 0) {
            drawProvinces = CFG.viewsManager.getActiveView().drawProvinces;
         } else {
            updateDrawProvinces_Standard();
         }
      } else if (CFG.menuManager.getInGame_TradeSelectCiv()) {
         updateDrawProvinces_Standard();
      } else if (CFG.menuManager.getInGame_CreateAVassal()) {
         if (CFG.viewsManager.getActiveViewID() >= 0) {
            drawProvinces = CFG.viewsManager.getActiveView().drawProvinces;
         } else if (CFG.FOG_OF_WAR == 2) {
            if (!CFG.VIEW_SHOW_VALUES) {
               drawProvinces = new Game_Render_Province.DrawProvinces() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                              if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()
                                 == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()) {
                                 CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                              } else {
                                 oSB.setColor(
                                    new Color(
                                       CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                       CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                       CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                       CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.5F
                                    )
                                 );
                              }

                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                    CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                    CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                    CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.5F
                                 )
                              );
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           }
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
                  }
               };
            } else {
               updateDrawProvinces_Standard();
            }
         } else if (!CFG.VIEW_SHOW_VALUES) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()
                           == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()) {
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                 CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.5F
                              )
                           );
                        }

                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(
                           new Color(
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                              CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.5F
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            };
         } else {
            updateDrawProvinces_Standard();
         }
      } else if (CFG.menuManager.getInGame_SelectProvinces()) {
         if (CFG.FOG_OF_WAR == 2) {
            if (!CFG.VIEW_SHOW_VALUES) {
               drawProvinces = new Game_Render_Province.DrawProvinces() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                              if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()
                                 == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()) {
                                 CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                              } else {
                                 oSB.setColor(
                                    new Color(
                                       CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                       CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                       CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                       CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.5F
                                    )
                                 );
                              }

                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                    CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                    CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                    CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.5F
                                 )
                              );
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           }
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
                  }
               };
            } else {
               updateDrawProvinces_Standard();
            }
         } else if (!CFG.VIEW_SHOW_VALUES) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()
                           == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()) {
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                 CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.5F
                              )
                           );
                        }

                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(
                           new Color(
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                              CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.5F
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            };
         } else {
            updateDrawProvinces_Standard();
         }
      } else if (CFG.menuManager.getInGame_ShowProvinces()) {
         drawProvinces = CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                           oSB.setColor(
                              new Color(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                 CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.7F
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                           oSB.setColor(
                              new Color(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                 CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.7F
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                        oSB.setColor(
                           new Color(
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                              CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.7F
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(
                           new Color(
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                              CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.7F
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            };
      } else if (CFG.menuManager.getInManageDiplomacy()) {
         if (CFG.menuManager.getInManageDiplomacy_Pacts3()) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (CFG.game.getActiveProvinceID() < 0
                     || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                     if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           }
                        }
                     } else {
                        for (int ix = 0; ix < CFG.NUM_OF_PROVINCES_IN_VIEW; ix++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() != 0) {
                              if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                       CFG.ALPHA_DIPLOMACY
                                    )
                                 );
                              } else if (CFG.game
                                    .getCivNonAggressionPact(
                                       CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1
                                    )
                                 > 0) {
                                 oSB.setColor(
                                    CFG.getPactColor(
                                       CFG.game
                                          .getCivNonAggressionPact(
                                             CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1
                                          ),
                                       CFG.ALPHA_DIPLOMACY
                                    )
                                 );
                              } else {
                                 int tempRelation = (int)CFG.game
                                    .getCivRelation_OfCivB(
                                       CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1
                                    );
                                 if (tempRelation == 0) {
                                    oSB.setColor(
                                       new Color(
                                          CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                          CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                          CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                          CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                       )
                                    );
                                 } else {
                                    oSB.setColor(
                                       CFG.getRelationColor(
                                          tempRelation,
                                          CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F + CFG.ALPHA_DIPLOMACY * 2.0F / 5.0F * (Math.abs(tempRelation) / 100.0F)
                                       )
                                    );
                                 }
                              }

                              CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).drawLandProvince(oSB);
                           }
                        }
                     }
                  } else {
                     for (int ixx = 0; ixx < CFG.NUM_OF_PROVINCES_IN_VIEW; ixx++) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID() != 0) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID()
                              == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 )
                              );
                           } else if (CFG.game
                                 .getCivNonAggressionPact(
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID(),
                                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                 )
                              > 0) {
                              oSB.setColor(
                                 CFG.getPactColor(
                                    CFG.game
                                       .getCivNonAggressionPact(
                                          CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID(),
                                          CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                       ),
                                    CFG.ALPHA_DIPLOMACY
                                 )
                              );
                           } else {
                              int tempRelation = (int)CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID(),
                                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                 );
                              if (tempRelation == 0) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                       CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                    )
                                 );
                              } else {
                                 oSB.setColor(
                                    CFG.getRelationColor(
                                       tempRelation, CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F + CFG.ALPHA_DIPLOMACY * 2.0F / 5.0F * (Math.abs(tempRelation) / 100.0F)
                                    )
                                 );
                              }
                           }

                           CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).drawLandProvince(oSB);
                        }
                     }
                  }
               }
            };
         } else if (CFG.menuManager.getInManageDiplomacy_Truces()) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (CFG.game.getActiveProvinceID() < 0
                     || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                     if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           }
                        }
                     } else {
                        for (int ix = 0; ix < CFG.NUM_OF_PROVINCES_IN_VIEW; ix++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() != 0) {
                              if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                       CFG.ALPHA_DIPLOMACY
                                    )
                                 );
                              } else if (CFG.game
                                    .getCivTruce(CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1)
                                 > 0) {
                                 oSB.setColor(CFG.getTruceColor(CFG.ALPHA_DIPLOMACY));
                              } else {
                                 int tempRelation = (int)CFG.game
                                    .getCivRelation_OfCivB(
                                       CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1
                                    );
                                 if (tempRelation == 0) {
                                    oSB.setColor(
                                       new Color(
                                          CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                          CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                          CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                          CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                       )
                                    );
                                 } else {
                                    oSB.setColor(
                                       CFG.getRelationColor(
                                          tempRelation,
                                          CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F + CFG.ALPHA_DIPLOMACY * 2.0F / 5.0F * (Math.abs(tempRelation) / 100.0F)
                                       )
                                    );
                                 }
                              }

                              CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).drawLandProvince(oSB);
                           }
                        }
                     }
                  } else {
                     for (int ixx = 0; ixx < CFG.NUM_OF_PROVINCES_IN_VIEW; ixx++) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID() != 0) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID()
                              == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 )
                              );
                           } else if (CFG.game
                                 .getCivTruce(
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID(),
                                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                 )
                              > 0) {
                              oSB.setColor(CFG.getTruceColor(CFG.ALPHA_DIPLOMACY));
                           } else {
                              int tempRelation = (int)CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID(),
                                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                 );
                              if (tempRelation == 0) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                       CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                    )
                                 );
                              } else {
                                 oSB.setColor(
                                    CFG.getRelationColor(
                                       tempRelation, CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F + CFG.ALPHA_DIPLOMACY * 2.0F / 5.0F * (Math.abs(tempRelation) / 100.0F)
                                    )
                                 );
                              }
                           }

                           CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).drawLandProvince(oSB);
                        }
                     }
                  }
               }
            };
         } else if (CFG.menuManager.getInManageDiplomacy_Guarantee()) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (CFG.game.getActiveProvinceID() < 0
                     || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                     if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           }
                        }
                     } else {
                        for (int ix = 0; ix < CFG.NUM_OF_PROVINCES_IN_VIEW; ix++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() != 0) {
                              if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                       CFG.ALPHA_DIPLOMACY
                                    )
                                 );
                              } else if (CFG.game
                                    .getGuarantee(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID())
                                 > 0) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getB(),
                                       CFG.ALPHA_DIPLOMACY
                                    )
                                 );
                              } else {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                       CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                    )
                                 );
                              }

                              CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).drawLandProvince(oSB);
                           }
                        }
                     }
                  } else {
                     for (int ixx = 0; ixx < CFG.NUM_OF_PROVINCES_IN_VIEW; ixx++) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID() != 0) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID()
                              == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 )
                              );
                           } else if (CFG.game
                                 .getGuarantee(
                                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(),
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID()
                                 )
                              > 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 )
                              );
                           } else {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                           }

                           CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).drawLandProvince(oSB);
                        }
                     }
                  }
               }
            };
         } else if (CFG.menuManager.getInManageDiplomacy_DefensivePact()) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (CFG.game.getActiveProvinceID() < 0
                     || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                     if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           }
                        }
                     } else {
                        for (int ix = 0; ix < CFG.NUM_OF_PROVINCES_IN_VIEW; ix++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() != 0) {
                              if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                       CFG.ALPHA_DIPLOMACY
                                    )
                                 );
                              } else if (CFG.game
                                    .getDefensivePact(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID())
                                 > 0) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getB(),
                                       CFG.ALPHA_DIPLOMACY
                                    )
                                 );
                              } else {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                       CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                    )
                                 );
                              }

                              CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).drawLandProvince(oSB);
                           }
                        }
                     }
                  } else {
                     for (int ixx = 0; ixx < CFG.NUM_OF_PROVINCES_IN_VIEW; ixx++) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID() != 0) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID()
                              == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 )
                              );
                           } else if (CFG.game
                                 .getDefensivePact(
                                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(),
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID()
                                 )
                              > 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 )
                              );
                           } else {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                           }

                           CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).drawLandProvince(oSB);
                        }
                     }
                  }
               }
            };
         } else if (CFG.menuManager.getInManageDiplomacy_MilitaryAccess()) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (CFG.game.getActiveProvinceID() < 0
                     || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                     if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           }
                        }
                     } else {
                        for (int ix = 0; ix < CFG.NUM_OF_PROVINCES_IN_VIEW; ix++) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() != 0) {
                              if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                       CFG.ALPHA_DIPLOMACY
                                    )
                                 );
                              } else if (CFG.game
                                    .getMilitaryAccess(
                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID()
                                    )
                                 > 0) {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getB(),
                                       CFG.ALPHA_DIPLOMACY
                                    )
                                 );
                              } else {
                                 oSB.setColor(
                                    new Color(
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                       CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                       CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                    )
                                 );
                              }

                              CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).drawLandProvince(oSB);
                           }
                        }
                     }
                  } else {
                     for (int ixx = 0; ixx < CFG.NUM_OF_PROVINCES_IN_VIEW; ixx++) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID() != 0) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID()
                              == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 )
                              );
                           } else if (CFG.game
                                 .getMilitaryAccess(
                                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(),
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).getCivID()
                                 )
                              > 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 )
                              );
                           } else {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                           }

                           CFG.game.getProvince(CFG.game.getProvinceInViewID(ixx)).drawLandProvince(oSB);
                        }
                     }
                  }
               }
            };
         } else if (CFG.menuManager.getInManageDiplomacy_Relations_Interactive()) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else {
                           int tempRelation = (int)CFG.game
                              .getCivRelation_OfCivB(
                                 CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()
                              );
                           if (tempRelation == 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                           } else {
                              oSB.setColor(
                                 CFG.getRelationColor(
                                    tempRelation, CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F + CFG.ALPHA_DIPLOMACY * 2.0F / 5.0F * (Math.abs(tempRelation) / 100.0F)
                                 )
                              );
                           }
                        }

                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            };
         } else if (CFG.menuManager.getInGame_Timeline() || CFG.menuManager.getInGame_ScreenShot() || CFG.menuManager.getInVictory()) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
               }
            };
         } else if (CFG.menuManager.getInManageDiplomacy_Vassals()) {
            drawProvinces = new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  int nActiveCivID = 0;
                  if (CFG.game.getActiveProvinceID() >= 0) {
                     nActiveCivID = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getPuppetOfCivID();
                  }

                  for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                        if (nActiveCivID == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (nActiveCivID == CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID()) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                 CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                              )
                           );
                        }

                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            };
         } else if (CFG.menuManager.getInManageDiplomacy_Alliances()) {
            updateDrawProvinces_ManageDiplomacyAlliances();
         } else {
            updateDrawProvinces_Standard();
         }
      } else if (CFG.menuManager.getInCustomizeAlliance()) {
         updateDrawProvinces_ManageDiplomacyAlliances();
      } else if (CFG.menuManager.getInMapEditor_Create_NewContinent()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               oSB.setColor(new Color(CFG.editor_Continent_GameData.getR(), CFG.editor_Continent_GameData.getG(), CFG.editor_Continent_GameData.getB(), 0.7F));

               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInMapEditor_Create_NewRegion()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               oSB.setColor(new Color(CFG.editor_Region_GameData.getR(), CFG.editor_Region_GameData.getG(), CFG.editor_Region_GameData.getB(), 0.45F));

               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInGameEditor_Create_DiplomacyPackage()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               oSB.setColor(
                  new Color(
                     CFG.menuManager.getColorPicker().getActiveColor().r,
                     CFG.menuManager.getColorPicker().getActiveColor().g,
                     CFG.menuManager.getColorPicker().getActiveColor().b,
                     CFG.ALPHA_DIPLOMACY
                  )
               );

               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInCreateScenario_TechnologyLevels()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                     oSB.setColor(
                        CFG.getTechnologyLevelColor(
                           (int)(
                              CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getTechnologyLevel()
                                 * CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() - 1,
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion()
                                 )
                           ),
                           CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL
                        )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
         };
      } else if (CFG.menuManager.getInCreateScenario_Happiness()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                     oSB.setColor(
                        CFG.getColorStep(
                           CFG.COLOR_TEXT_HAPPINESS_MIN,
                           CFG.COLOR_TEXT_HAPPINESS_MAX,
                           CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getHappiness(),
                           100,
                           0.5F
                        )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
         };
      } else if (CFG.menuManager.getInCreateScenario_StartingMoney()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  int tempMoney = (int)(
                     CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getMoney() == -999999L
                        ? CFG.game.getGameScenarios().getScenario_StartingMoney()
                        : CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getMoney()
                  );
                  if (tempMoney < 0) {
                     oSB.setColor(
                        CFG.getColorStep(CFG.COLOR_STARTINGMONEY_0, CFG.COLOR_STARTINGMONEY_MIN, -tempMoney, 100000, CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL)
                     );
                  } else {
                     oSB.setColor(
                        CFG.getColorStep(CFG.COLOR_STARTINGMONEY_0, CFG.COLOR_STARTINGMONEY_MAX, tempMoney, 100000, CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL)
                     );
                  }

                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInEditor_GameCivs()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               oSB.setColor(
                  new Color(
                     CFG.editorCivilization_GameData.getR() / 255.0F,
                     CFG.editorCivilization_GameData.getG() / 255.0F,
                     CFG.editorCivilization_GameData.getB() / 255.0F,
                     CFG.ALPHA_DIPLOMACY
                  )
               );

               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInCreateCivilization()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               oSB.setColor(
                  new Color(
                     CFG.editorCivilization_GameData.getR() / 255.0F,
                     CFG.editorCivilization_GameData.getG() / 255.0F,
                     CFG.editorCivilization_GameData.getB() / 255.0F,
                     CFG.ALPHA_DIPLOMACY
                  )
               );

               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInRandomGame()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
            }
         };
      } else if (CFG.menuManager.getInGameEditor_TerrainAdd()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               oSB.setColor(
                  new Color(
                     CFG.editorTerrain_Data2.getColor().getR(), CFG.editorTerrain_Data2.getColor().getG(), CFG.editorTerrain_Data2.getColor().getB(), 0.55F
                  )
               );

               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInMapEditor_Terrain()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  oSB.setColor(CFG.terrainTypesManager.getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTerrainTypeID()));
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInMapEditor_Continents()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  oSB.setColor(CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getContinent()));
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInMapEditor_Regions()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  oSB.setColor(CFG.map.getMapRegions().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion()));
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInMapEditor_GrowthRate()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  oSB.setColor(CFG.getGrowthRateColor((int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getGrowthRate_Population() * 100.0F), 0.75F));
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else if (CFG.menuManager.getInPrintAMap()) {
         drawProvinces = new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));

               for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         };
      } else {
         updateDrawProvinces_Standard();
      }
   }

   public static final void updateDrawProvinces_Standard() {
      drawProvinces = CFG.FOG_OF_WAR == 2 ? new Game_Render_Province.DrawProvinces() {
         @Override
         public void draw(SpriteBatch oSB) {
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
               CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor_FoG_Discovery(oSB);
               CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }

            Game_Render_Province.drawOccupiedProvinces_FogOfWar(oSB);
         }
      } : new Game_Render_Province.DrawProvinces() {
         @Override
         public void draw(SpriteBatch oSB) {
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
               if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }

            Game_Render_Province.drawOccupiedProvinces(oSB);
         }
      };
   }

   public static final void updateDrawProvinces_ManageDiplomacyAlliances() {
      drawProvinces = new Game_Render_Province.DrawProvinces() {
         @Override
         public void draw(SpriteBatch oSB) {
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
               if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                  if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID() == 0) {
                     oSB.setColor(
                        new Color(
                           CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                           CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                           CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                           CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                        )
                     );
                  } else {
                     oSB.setColor(
                        CFG.game
                           .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID())
                           .getColorOfAlliance()
                           .getR(),
                        CFG.game
                           .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID())
                           .getColorOfAlliance()
                           .getG(),
                        CFG.game
                           .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID())
                           .getColorOfAlliance()
                           .getB(),
                        CFG.ALPHA_DIPLOMACY * 1.25F
                     );
                  }

                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         }
      };
   }

   public static final void drawProvinces(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID() != 0) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
         }
      }
   }

   public static final void drawOccupiedProvinces(SpriteBatch oSB) {
      oSB.setShader(AoCGame.shaderAlpha2);

      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()
            )
          {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
         }
      }

      oSB.setShader(AoCGame.defaultShader);
   }

   public static final void drawOccupiedProvinces_FogOfWar(SpriteBatch oSB) {
      oSB.setShader(AoCGame.shaderAlpha2);

      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()
            && CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
         }
      }

      oSB.setShader(AoCGame.defaultShader);
   }

   public static final void drawProvinces_NextPlayer_Turn(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
         }
      }
   }

   public static final void drawProvinces_CivilizationView(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == Menu_InGame_CivilizationView.iCivID) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
         }
      }
   }

   public static final void drawProvinces_CivilizationView_FogOfWar(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))
            && CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == Menu_InGame_CivilizationView.iCivID) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
         }
      }
   }

   public static final void drawProvinces_FormableCiv(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.formableCivs_GameData.getProvincesSize(); i++) {
         if (CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getDrawProvince()
            && CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getWasteland() < 0) {
            if (CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID()
               == CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivID()) {
               CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).setProvinceColor(oSB);
            } else {
               oSB.setColor(
                  new Color(
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r,
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g,
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b,
                     CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.85F
                  )
               );
            }

            CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).drawLandProvince(oSB);
            CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).setDrawProvince(false);
         }
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getDrawProvince()
            && CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID()
               == CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivID()) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).setProvinceColor(oSB);
            oSB.setColor(
               new Color(
                  CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID()).getR() / 255.0F,
                  CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID()).getG() / 255.0F,
                  CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID()).getB() / 255.0F,
                  CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.35F
               )
            );
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
         }

         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).setDrawProvince(true);
      }
   }

   public static final void drawProvinces_FormableCiv_FogOfWarDiscovery(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.formableCivs_GameData.getProvincesSize(); i++) {
         if (CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getDrawProvince()
            && CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getWasteland() < 0) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.formableCivs_GameData.getProvinceID(i))
               && CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID()
                  == CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivID()) {
               CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).setProvinceColor(oSB);
            } else if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.formableCivs_GameData.getProvinceID(i))) {
               oSB.setColor(
                  new Color(
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r,
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g,
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b,
                     CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.85F
                  )
               );
            } else {
               oSB.setColor(
                  new Color(
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r,
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g,
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b,
                     CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.75F
                  )
               );
            }

            CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).drawLandProvince(oSB);
            CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).setDrawProvince(false);
         }
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(var2))
            && CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getDrawProvince()
            && CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID()
               == CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivID()) {
            oSB.setColor(
               new Color(
                  CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID()).getR() / 255.0F,
                  CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID()).getG() / 255.0F,
                  CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID()).getB() / 255.0F,
                  CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.35F
               )
            );
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
         }

         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).setDrawProvince(true);
      }
   }

   public static final void drawProvinces_LoadAI_RTO(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();
      if (CFG.FOG_OF_WAR == 2) {
         for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
               CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
               CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
         }
      } else {
         for (int ix = 0; ix < CFG.NUM_OF_PROVINCES_IN_VIEW; ix++) {
            if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).getCivID()).getControlledByPlayer()) {
               CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).setProvinceColor(oSB);
               CFG.game.getProvince(CFG.game.getProvinceInViewID(ix)).drawLandProvince(oSB);
            }
         }
      }
   }

   public static final void drawProvinces_Timeline(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         if (CFG.timelapseManager.timelineOwners.get(CFG.game.getWastelandProvinceInViewID(i)) > 0) {
            CFG.game
               .getProvince(CFG.game.getWastelandProvinceInViewID(i))
               .setCivilizationProvinceColor(oSB, CFG.timelapseManager.timelineOwners.get(CFG.game.getWastelandProvinceInViewID(i)));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
         }
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         if (CFG.timelapseManager.timelineOwners.get(CFG.game.getProvinceInViewID(var2)) > 0) {
            CFG.game
               .getProvince(CFG.game.getProvinceInViewID(var2))
               .setCivilizationProvinceColor(oSB, CFG.timelapseManager.timelineOwners.get(CFG.game.getProvinceInViewID(var2)));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
         }
      }

      oSB.setShader(AoCGame.shaderAlpha2);

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         if (CFG.timelapseManager.timelineOwners_IsOccupied.get(CFG.game.getProvinceInViewID(var3))) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawOccupiedProvince(oSB);
         }
      }

      oSB.setShader(AoCGame.defaultShader);
   }

   public static final void drawProvinces_Timeline_FogOfWar(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         if (CFG.timelapseManager.timelineOwners.get(CFG.game.getWastelandProvinceInViewID(i)) > 0) {
            CFG.game
               .getProvince(CFG.game.getWastelandProvinceInViewID(i))
               .setCivilizationProvinceColor(oSB, CFG.timelapseManager.timelineOwners.get(CFG.game.getWastelandProvinceInViewID(i)));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
         }
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(var2))) {
            if (CFG.timelapseManager.timelineOwners.get(CFG.game.getProvinceInViewID(var2)) > 0) {
               CFG.game
                  .getProvince(CFG.game.getProvinceInViewID(var2))
                  .setCivilizationProvinceColor(oSB, CFG.timelapseManager.timelineOwners.get(CFG.game.getProvinceInViewID(var2)));
               CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
            }
         } else {
            oSB.setColor(
               new Color(
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
               )
            );
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
         }
      }

      oSB.setShader(AoCGame.shaderAlpha2);

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         if (CFG.timelapseManager.timelineOwners_IsOccupied.get(CFG.game.getProvinceInViewID(var3))) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawOccupiedProvince(oSB);
         }
      }

      oSB.setShader(AoCGame.defaultShader);
   }

   public static final void drawProvincesBorder_Timeline(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_Timeline(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_Timeline(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_Timeline_OnlyCivilizationBorder(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_Timeline_Only_CivilizationBorder(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_Timeline_Only_CivilizationBorder(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_PeaceTreaty(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_PeaceTreaty_Wasteland(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_PeaceTreaty(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_PeaceTreaty_Only_CivilizationBorder(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_PeaceTreaty_Wasteland(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_PeaceTreaty_Only_CivilizationBorder(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_PeaceTreaty_FogOfWarDiscovery(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_PeaceTreaty_Wasteland(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_PeaceTreaty_FogOfWarDiscovery(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_PeaceTreaty_FogOfWarDiscovery_Only_CivilizationBorder(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_PeaceTreaty_Wasteland(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_PeaceTreaty_FogOfWarDiscovery_Only_CivilizationBorder(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvinces_PeaceTreaty(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         if (CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID != 0) {
            if (CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID < 0) {
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID * -1).getR() / 255.0F,
                     CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID * -1).getG() / 255.0F,
                     CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID * -1).getB() / 255.0F,
                     CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.25F
                  )
               );
               CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
            } else if (!CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).isToTake
               || CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).isTaken > 0) {
               CFG.game
                  .getProvince(CFG.game.getProvinceInViewID(i))
                  .setCivilizationProvinceColor(oSB, CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID);
               CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
         }
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawWastelandProvince_PeaceTreaty(oSB);
      }

      oSB.setShader(AoCGame.shaderAlpha2);

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         if (CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(var3)).isToTake
            && CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(var3)).isTaken < 0) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawOccupiedProvince(oSB);
         }
      }

      oSB.setShader(AoCGame.defaultShader);
   }

   public static final void drawProvinces_PeaceTreaty_FogOfWarDiscovery(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
            if (CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID != 0) {
               if (CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID < 0) {
                  oSB.setColor(
                     new Color(
                        CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID * -1).getR() / 255.0F,
                        CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID * -1).getG() / 255.0F,
                        CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID * -1).getB() / 255.0F,
                        CFG.settingsManager.PROVINCE_ALPHA / 255.0F * 0.25F
                     )
                  );
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               } else if (!CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).isToTake
                  || CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).isTaken > 0) {
                  CFG.game
                     .getProvince(CFG.game.getProvinceInViewID(i))
                     .setCivilizationProvinceColor(oSB, CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(i)).iCivID);
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         } else {
            oSB.setColor(
               new Color(
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * ((float)CFG.startTheGameData.getProvincesAlpha() / CFG.settingsManager.PROVINCE_ALPHA)
               )
            );
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
         }
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawWastelandProvince_PeaceTreaty(oSB);
      }

      oSB.setShader(AoCGame.shaderAlpha2);

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         if (CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(var3)).isToTake
            && CFG.peaceTreatyData.drawProvinceOwners.get(CFG.game.getProvinceInViewID(var3)).isTaken < 0) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawOccupiedProvince(oSB);
         }
      }

      oSB.setShader(AoCGame.defaultShader);
   }

   public static final void drawProvincesInCreateNewGameSelectAvailableCivs(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID() != 0) {
            if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID()).getIsAvailable()) {
               CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).setProvinceColor(oSB);
            } else {
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, CFG.settingsManager.PROVINCE_ALPHA * 0.6F / 255.0F));
            }

            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
         }
      }
   }

   public static final void drawProvincesInCreateRandomGame(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
      }

      int var2 = 0;

      while (var2 < CFG.NUM_OF_PROVINCES_IN_VIEW) {
         var2++;
      }
   }

   public static final void drawProvincesInGame(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();
      drawProvincesInGame_StandardWasteland_FogOFWar(oSB);
      drawProvinces.draw(oSB);

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         CFG.game.getProvince(i).drawNuclearExplosion(oSB);
      }
   }

   public static final void drawProvincesInGame_StandardWasteland(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
      }
   }

   public static final void drawProvincesInGame_StandardWasteland_FogOFWar(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         if (CFG.getMetProvince(CFG.game.getWastelandProvinceInViewID(i))) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
         } else {
            oSB.setColor(
               new Color(
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
               )
            );
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
         }
      }
   }

   public static final void drawProvinces_InLoad_PreDefinedBorders(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
         }
      }
   }

   public static final void drawProvincesInMapEditor_Connections(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.1F));

      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
      }

      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.4F));
      if (CFG.VIEW_SHOW_VALUES) {
         for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).getProvinceBordersLandByLandSize(); j++) {
               drawProvincesInMapEditor_Connections_Line(
                  oSB,
                  Images.pix255_255_255,
                  CFG.game.getProvinceInViewID(var3),
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).getProvinceBordersLandByLand().get(j).getWithProvinceID()
               );
            }

            for (int var5 = 0; var5 < CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).getProvinceBordersLandBySeaSize(); var5++) {
               drawProvincesInMapEditor_Connections_Line(
                  oSB,
                  Images.line_33,
                  CFG.game.getProvinceInViewID(var3),
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).getProvinceBordersLandBySea().get(var5).getWithProvinceID()
               );
            }
         }

         for (int var4 = 0; var4 < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; var4++) {
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var4)).getProvinceBordersLandBySeaSize(); j++) {
               drawProvincesInMapEditor_Connections_Line(
                  oSB,
                  Images.line_33,
                  CFG.game.getSeaProvinceInViewID(var4),
                  CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var4)).getProvinceBordersLandBySea().get(j).getWithProvinceID()
               );
            }

            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.25F));

            for (int var7 = 0; var7 < CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var4)).getProvinceBordersSeaBySeaSize(); var7++) {
               drawProvincesInMapEditor_Connections_Line(
                  oSB,
                  Images.line_33,
                  CFG.game.getSeaProvinceInViewID(var4),
                  CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var4)).getProvinceBordersSeaBySea().get(var7).getWithProvinceID()
               );
            }
         }
      }
   }

   public static final void drawProvincesInMapEditor_Connections_Line(SpriteBatch oSB, int nImageID, int fromProvinceID, int toProvinceID) {
      if (CFG.game.getProvince(toProvinceID).getDrawProvince()) {
         int iWidth = (int)Math.ceil(
            Math.sqrt(
               (
                        CFG.game.getProvince(toProvinceID).getCenterX()
                           + CFG.game.getProvince(toProvinceID).getShiftX()
                           + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX()
                           - (
                              CFG.game.getProvince(fromProvinceID).getCenterX()
                                 + CFG.game.getProvince(fromProvinceID).getShiftX()
                                 + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()
                           )
                     )
                     * (
                        CFG.game.getProvince(toProvinceID).getCenterX()
                           + CFG.game.getProvince(toProvinceID).getShiftX()
                           + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX()
                           - (
                              CFG.game.getProvince(fromProvinceID).getCenterX()
                                 + CFG.game.getProvince(fromProvinceID).getShiftX()
                                 + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()
                           )
                     )
                  + (
                        CFG.game.getProvince(fromProvinceID).getCenterY()
                           + CFG.game.getProvince(fromProvinceID).getShiftY()
                           - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY())
                     )
                     * (
                        CFG.game.getProvince(fromProvinceID).getCenterY()
                           + CFG.game.getProvince(fromProvinceID).getShiftY()
                           - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY())
                     )
            )
         );
         float fAngle = (float)(
            Math.atan2(
                  CFG.game.getProvince(fromProvinceID).getCenterY()
                     + CFG.game.getProvince(fromProvinceID).getShiftY()
                     - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY()),
                  -(
                        CFG.game.getProvince(fromProvinceID).getCenterX()
                           + CFG.game.getProvince(fromProvinceID).getShiftX()
                           + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()
                     )
                     + CFG.game.getProvince(toProvinceID).getCenterX()
                     + CFG.game.getProvince(toProvinceID).getShiftX()
                     + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX()
               )
               * 180.0
               / Math.PI
         );
         ImageManager.getImage(nImageID)
            .draw(
               oSB,
               CFG.game.getProvince(fromProvinceID).getCenterX()
                  + CFG.game.getProvince(fromProvinceID).getShiftX()
                  + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX(),
               CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY(),
               iWidth,
               ImageManager.getImage(nImageID).getHeight(),
               fAngle,
               0
            );
      }
   }

   public static final void drawProvincesInMapEditor_SeaProvinces(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         if (CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).getLevelOfPort() >= -1) {
            oSB.setColor(new Color(0.1254902F, 0.2901961F, 0.043137256F, 0.6F));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
         } else if (CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).getLevelOfPort() == -1) {
            oSB.setColor(new Color(0.02745098F, 0.12941177F, 0.18431373F, 0.6F));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
         } else {
            oSB.setColor(new Color(0.007843138F, 0.09411765F, 0.13725491F, 0.6F));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
         }
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getLevelOfPort() >= -1) {
            oSB.setColor(new Color(0.1254902F, 0.2901961F, 0.043137256F, 0.6F));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvince_ActiveProvince(oSB);
         } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getLevelOfPort() == -1) {
            oSB.setColor(new Color(0.02745098F, 0.12941177F, 0.18431373F, 0.6F));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvince_ActiveProvince(oSB);
         } else {
            oSB.setColor(new Color(0.007843138F, 0.09411765F, 0.13725491F, 0.6F));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvince_ActiveProvince(oSB);
         }
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; var3++) {
         if (CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var3)).getLevelOfPort() >= -1) {
            oSB.setColor(new Color(0.1254902F, 0.2901961F, 0.043137256F, 0.6F));
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var3)).drawProvince_ActiveProvince(oSB);
         } else if (CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var3)).getLevelOfPort() == -1) {
            oSB.setColor(new Color(0.02745098F, 0.12941177F, 0.18431373F, 0.6F));
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var3)).drawProvince_ActiveProvince(oSB);
         } else {
            oSB.setColor(new Color(0.007843138F, 0.09411765F, 0.13725491F, 0.6F));
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var3)).drawProvince_ActiveProvince(oSB);
         }
      }
   }

   public static final void drawProvincesInGameEditorRegions(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         oSB.setColor(Menu_GameEditor_Regions.lColors.get(CFG.game.getRegionID(CFG.game.getWastelandProvinceInViewID(i))));
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         oSB.setColor(Menu_GameEditor_Regions.lColors.get(CFG.game.getRegionID(CFG.game.getProvinceInViewID(var2))));
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; var3++) {
         oSB.setColor(Menu_GameEditor_Regions.lColors.get(CFG.game.getRegionID(CFG.game.getSeaProvinceInViewID(var3))));
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(var3)).drawProvince_ActiveProvince(oSB);
      }

      if (CFG.game.getActiveProvinceID() >= 0) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.2F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX() + CFG.map.getMapCoordinates().getPosX(),
               CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY() + CFG.map.getMapCoordinates().getPosY(),
               CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX()
                  - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX(),
               CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY()
                  - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY()
            );
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.6F));
         CFG.drawRect(
            oSB,
            CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX() + CFG.map.getMapCoordinates().getPosX(),
            CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY() + CFG.map.getMapCoordinates().getPosY(),
            CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX()
               - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX(),
            CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY()
               - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY()
         );
         if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.2F));
            ImageManager.getImage(Images.pix255_255_255)
               .draw(
                  oSB,
                  CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX()
                     + CFG.map.getMapCoordinates().getPosX()
                     + CFG.map.getMapBG().getWidth(),
                  CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY() + CFG.map.getMapCoordinates().getPosY(),
                  CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX()
                     - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX(),
                  CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY()
                     - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY()
               );
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.6F));
            CFG.drawRect(
               oSB,
               CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX()
                  + CFG.map.getMapCoordinates().getPosX()
                  + CFG.map.getMapBG().getWidth(),
               CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY() + CFG.map.getMapCoordinates().getPosY(),
               CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX()
                  - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX(),
               CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY()
                  - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY()
            );
         }
      }
   }

   public static final void drawProvincesInMapEditor_ArmySeaBoxes(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();
      if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.15F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX() + CFG.map.getMapCoordinates().getPosX(),
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY() + CFG.map.getMapCoordinates().getPosY(),
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxX() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX(),
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxY() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY()
            );
         oSB.setColor(
            new Color(
               CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.8F
            )
         );
         CFG.drawRect(
            oSB,
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX() + CFG.map.getMapCoordinates().getPosX(),
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY() + CFG.map.getMapCoordinates().getPosY(),
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxX() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX(),
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxY() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY()
         );
         if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.15F));
            ImageManager.getImage(Images.pix255_255_255)
               .draw(
                  oSB,
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(),
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY() + CFG.map.getMapCoordinates().getPosY(),
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxX() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX(),
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxY() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY()
               );
            oSB.setColor(
               new Color(
                  CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.8F
               )
            );
            CFG.drawRect(
               oSB,
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(),
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY() + CFG.map.getMapCoordinates().getPosY(),
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxX() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX(),
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxY() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY()
            );
         }
      }

      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         if (CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes() != null) {
            for (int j = CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().size() - 1; j >= 0; j--) {
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.05F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX()
                        + CFG.map.getMapCoordinates().getPosX(),
                     CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY()
                        + CFG.map.getMapCoordinates().getPosY(),
                     CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosX()
                        - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX(),
                     CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosY()
                        - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY()
                  );
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.075F));
               CFG.drawRect(
                  oSB,
                  CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX(),
                  CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(),
                  CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosX()
                     - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX(),
                  CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosY()
                     - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY()
               );
               if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.05F));
                  ImageManager.getImage(Images.pix255_255_255)
                     .draw(
                        oSB,
                        CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX()
                           + CFG.map.getMapCoordinates().getPosX()
                           + CFG.map.getMapBG().getWidth(),
                        CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY()
                           + CFG.map.getMapCoordinates().getPosY(),
                        CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosX()
                           - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX(),
                        CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosY()
                           - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY()
                     );
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.075F));
                  CFG.drawRect(
                     oSB,
                     CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX()
                        + CFG.map.getMapCoordinates().getPosX()
                        + CFG.map.getMapBG().getWidth(),
                     CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY()
                        + CFG.map.getMapCoordinates().getPosY(),
                     CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosX()
                        - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX(),
                     CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosY()
                        - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY()
                  );
               }
            }
         }
      }
   }

   public static final void drawProvincesInMapEditor_ArmySeaBoxes_Edit(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();
      if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 >= 0 && CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getSeaProvince()) {
         oSB.setColor(
            new Color(
               CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.3F
            )
         );
         CFG.drawRect(
            oSB,
            CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX() + CFG.map.getMapCoordinates().getPosX(),
            CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY() + CFG.map.getMapCoordinates().getPosY(),
            CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX(),
            CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY()
         );
         if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
            oSB.setColor(
               new Color(
                  CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.3F
               )
            );
            CFG.drawRect(
               oSB,
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(),
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY() + CFG.map.getMapCoordinates().getPosY(),
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX(),
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY()
            );
         }
      }

      if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null) {
         for (int j = CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size() - 1; j >= 0; j--) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.05F));
            ImageManager.getImage(Images.pix255_255_255)
               .draw(
                  oSB,
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()
                     + CFG.map.getMapCoordinates().getPosX(),
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                     + CFG.map.getMapCoordinates().getPosY(),
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX()
                     - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(),
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY()
                     - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
               );
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.075F));
            CFG.drawRect(
               oSB,
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()
                  + CFG.map.getMapCoordinates().getPosX(),
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                  + CFG.map.getMapCoordinates().getPosY(),
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX()
                  - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(),
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY()
                  - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
            );
            if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.05F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()
                        + CFG.map.getMapCoordinates().getPosX()
                        + CFG.map.getMapBG().getWidth(),
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                        + CFG.map.getMapCoordinates().getPosY(),
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX()
                        - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(),
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY()
                        - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                  );
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.075F));
               CFG.drawRect(
                  oSB,
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()
                     + CFG.map.getMapCoordinates().getPosX()
                     + CFG.map.getMapBG().getWidth(),
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                     + CFG.map.getMapCoordinates().getPosY(),
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX()
                     - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(),
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY()
                     - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
               );
            }
         }
      }

      if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null) {
         for (int jx = CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size() - 1; jx >= 0; jx--) {
            CFG.glyphLayout.setText(CFG.fontMain, "" + (jx + 1));
            CFG.drawText(
               oSB,
               "" + (jx + 1),
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosX()
                  + CFG.map.getMapCoordinates().getPosX()
                  + (
                        CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getEndPosX()
                           - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosX()
                     )
                     / 2
                  - (int)CFG.glyphLayout.width / 2,
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosY()
                  + CFG.map.getMapCoordinates().getPosY()
                  + (
                        CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getEndPosY()
                           - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosY()
                     )
                     / 2
                  - CFG.TEXT_HEIGHT / 2,
               new Color(1.0F, 1.0F, 1.0F, 0.4F)
            );
            if (!CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
            }
         }
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesInMapEditor_ArmySeaBoxes_Add(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();
      if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 >= 0 && CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getSeaProvince()) {
         oSB.setColor(
            new Color(
               CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.3F
            )
         );
         CFG.drawRect(
            oSB,
            CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX() + CFG.map.getMapCoordinates().getPosX(),
            -ImageManager.getImage(Images.pix255_255_255).getHeight()
               + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY()
               + CFG.map.getMapCoordinates().getPosY(),
            CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX(),
            CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY()
         );
         if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
            oSB.setColor(
               new Color(
                  CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.3F
               )
            );
            CFG.drawRect(
               oSB,
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(),
               -ImageManager.getImage(Images.pix255_255_255).getHeight()
                  + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY()
                  + CFG.map.getMapCoordinates().getPosY(),
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX(),
               CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY()
            );
         }
      }

      if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null) {
         for (int j = CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size() - 1; j >= 0; j--) {
            if (j != CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2) {
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.05F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()
                        + CFG.map.getMapCoordinates().getPosX(),
                     -ImageManager.getImage(Images.pix255_255_255).getHeight()
                        + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                        + CFG.map.getMapCoordinates().getPosY(),
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX()
                        - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(),
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY()
                        - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                  );
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.075F));
               CFG.drawRect(
                  oSB,
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()
                     + CFG.map.getMapCoordinates().getPosX(),
                  -ImageManager.getImage(Images.pix255_255_255).getHeight()
                     + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                     + CFG.map.getMapCoordinates().getPosY(),
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX()
                     - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(),
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY()
                     - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
               );
               if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.05F));
                  ImageManager.getImage(Images.pix255_255_255)
                     .draw(
                        oSB,
                        CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()
                           + CFG.map.getMapCoordinates().getPosX()
                           + CFG.map.getMapBG().getWidth(),
                        -ImageManager.getImage(Images.pix255_255_255).getHeight()
                           + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                           + CFG.map.getMapCoordinates().getPosY(),
                        CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX()
                           - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(),
                        CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY()
                           - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                     );
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.075F));
                  CFG.drawRect(
                     oSB,
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()
                        + CFG.map.getMapCoordinates().getPosX()
                        + CFG.map.getMapBG().getWidth(),
                     -ImageManager.getImage(Images.pix255_255_255).getHeight()
                        + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                        + CFG.map.getMapCoordinates().getPosY(),
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX()
                        - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(),
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY()
                        - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()
                  );
               }
            }
         }
      }

      if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null) {
         for (int jx = CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size() - 1; jx >= 0; jx--) {
            if (jx != CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2) {
               CFG.glyphLayout.setText(CFG.fontMain, "" + (jx + 1));
               CFG.drawText(
                  oSB,
                  "" + (jx + 1),
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosX()
                     + CFG.map.getMapCoordinates().getPosX()
                     + (
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getEndPosX()
                              - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosX()
                        )
                        / 2
                     - (int)CFG.glyphLayout.width / 2,
                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosY()
                     + CFG.map.getMapCoordinates().getPosY()
                     + (
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getEndPosY()
                              - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosY()
                        )
                        / 2
                     - CFG.TEXT_HEIGHT / 2,
                  new Color(1.0F, 1.0F, 1.0F, 0.4F)
               );
               if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                  CFG.drawText(
                     oSB,
                     "" + (jx + 1),
                     CFG.map.getMapBG().getWidth()
                        + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosX()
                        + CFG.map.getMapCoordinates().getPosX()
                        + (
                              CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getEndPosX()
                                 - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosX()
                           )
                           / 2
                        - (int)CFG.glyphLayout.width / 2,
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosY()
                        + CFG.map.getMapCoordinates().getPosY()
                        + (
                              CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getEndPosY()
                                 - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(jx).getStartPosY()
                           )
                           / 2
                        - CFG.TEXT_HEIGHT / 2,
                     new Color(1.0F, 1.0F, 1.0F, 0.4F)
                  );
               }
            }
         }
      }

      if (Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() >= 0 && Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() >= 0) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.15F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(),
               -ImageManager.getImage(Images.pix255_255_255).getHeight()
                  + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY()
                  + CFG.map.getMapCoordinates().getPosY(),
               Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX(),
               Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY()
            );
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.45F));
         CFG.drawRect(
            oSB,
            Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(),
            -ImageManager.getImage(Images.pix255_255_255).getHeight()
               + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY()
               + CFG.map.getMapCoordinates().getPosY(),
            Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX(),
            Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY()
         );
         if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.15F));
            ImageManager.getImage(Images.pix255_255_255)
               .draw(
                  oSB,
                  CFG.map.getMapBG().getWidth() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(),
                  -ImageManager.getImage(Images.pix255_255_255).getHeight()
                     + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY()
                     + CFG.map.getMapCoordinates().getPosY(),
                  Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX(),
                  Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY()
               );
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.45F));
            CFG.drawRect(
               oSB,
               CFG.map.getMapBG().getWidth() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(),
               -ImageManager.getImage(Images.pix255_255_255).getHeight()
                  + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY()
                  + CFG.map.getMapCoordinates().getPosY(),
               Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX(),
               Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY()
            );
         }
      }

      oSB.setColor(Color.RED);
      if (Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() >= 0) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(),
               Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() + CFG.map.getMapCoordinates().getPosY()
            );
         if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
            ImageManager.getImage(Images.pix255_255_255)
               .draw(
                  oSB,
                  CFG.map.getMapBG().getWidth() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(),
                  Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() + CFG.map.getMapCoordinates().getPosY()
               );
         }
      }

      if (Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() >= 0) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(),
               Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() + CFG.map.getMapCoordinates().getPosY()
            );
         if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
            ImageManager.getImage(Images.pix255_255_255)
               .draw(
                  oSB,
                  CFG.map.getMapBG().getWidth() + Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(),
                  Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() + CFG.map.getMapCoordinates().getPosY()
               );
         }
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesInStartGame(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();
      CFG.startTheGameData.updateData();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB, CFG.startTheGameData.getWastelandAlpha() / 255.0F);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID() != 0) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getIsCapital()) {
               CFG.game
                  .getProvince(CFG.game.getProvinceInViewID(var2))
                  .setCivilizationProvinceColor(
                     oSB, CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID(), CFG.startTheGameData.getCapitalsAlpha() / 255.0F
                  );
            } else {
               CFG.game
                  .getProvince(CFG.game.getProvinceInViewID(var2))
                  .setCivilizationProvinceColor(
                     oSB, CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID(), CFG.startTheGameData.getProvincesAlpha() / 255.0F
                  );
            }

            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
         }
      }
   }

   public static final void drawProvincesInStartGame_FogOfWarDiscovery(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();
      CFG.startTheGameData.updateData();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         if (CFG.getMetProvince(CFG.game.getWastelandProvinceInViewID(i))) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB, CFG.startTheGameData.getWastelandAlpha() / 255.0F);
         } else {
            oSB.setColor(
               new Color(
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * ((float)CFG.startTheGameData.getProvincesAlpha() / CFG.settingsManager.PROVINCE_ALPHA)
               )
            );
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
         }
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         if (CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID() != 0) {
            if (CFG.getMetProvince(CFG.game.getProvinceInViewID(var2))) {
               if (CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getIsCapital()) {
                  CFG.game
                     .getProvince(CFG.game.getProvinceInViewID(var2))
                     .setCivilizationProvinceColor(
                        oSB, CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID(), CFG.startTheGameData.getCapitalsAlpha() / 255.0F
                     );
               } else {
                  CFG.game
                     .getProvince(CFG.game.getProvinceInViewID(var2))
                     .setCivilizationProvinceColor(
                        oSB, CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).getCivID(), CFG.startTheGameData.getProvincesAlpha() / 255.0F
                     );
               }
            } else {
               oSB.setColor(
                  new Color(
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                        * ((float)CFG.startTheGameData.getProvincesAlpha() / CFG.settingsManager.PROVINCE_ALPHA)
                  )
               );
            }

            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
         } else if (!CFG.getMetProvince(CFG.game.getProvinceInViewID(var2))) {
            oSB.setColor(
               new Color(
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * ((float)CFG.startTheGameData.getProvincesAlpha() / CFG.settingsManager.PROVINCE_ALPHA)
               )
            );
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
         }
      }
   }

   public static final void drawProvinces_PrintAMap(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();
      oSB.setColor(Color.WHITE);

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawLandProvince(oSB);
      }
   }

   public static final void drawProvincesIn_MapEditor_WastelandMaps(SpriteBatch oSB) {
      CFG.game.updateProvincesInView();

      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
      }
   }

   public static final void drawProvincesBorder(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_Only_CivilizationBorder(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_Only_CivilizationBorder_InGame(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_Only_CivilizationBorder_InGame_AndSea(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_Only_CivilizationBorder_Capitals(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvinceBorder_OnlyCivilizationBorder_Capitals(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var3)).drawProvinceBorder_OnlyCivilizationBorder_Capitals(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_Only_CivilizationBorder_Capitals_FogOfWarDiscovery(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvinceBorder_OnlyCivilizationBorder_Capitals_FogOfWarDiscovery(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var3)).drawProvinceBorder_OnlyCivilizationBorder_Capitals_FogOfWarDiscoveryWasteland(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_NextPlayer(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_NextPlayerTurn(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_NextPlayerTurn(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_CivilizationView(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_CivilizationView(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_CivilizationView(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_LoadAI_RTO(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      if (CFG.FOG_OF_WAR == 2) {
         for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_LoadAI_RTO_FogOfWarDiscovery(oSB);
         }

         for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_LoadAI_RTO_FogOfWarDiscovery(oSB);
         }
      } else {
         for (int var4 = 0; var4 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var4++) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var4)).drawProvinceBorder_LoadAI_RTO(oSB);
         }

         for (int var5 = 0; var5 < CFG.NUM_OF_PROVINCES_IN_VIEW; var5++) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(var5)).drawProvinceBorder_LoadAI_RTO(oSB);
         }
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_TerrainMode(SpriteBatch oSB) {
      if ((
            CFG.fTerrainMode_LinePercentage = CFG.fTerrainMode_LinePercentage
               + (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0F * 100.0F
         )
         > 100.0F) {
         CFG.fTerrainMode_LinePercentage = 100.0F;
      }

      CFG.lTerrainMode_LineTime = System.currentTimeMillis();

      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvinceBorder_TerrainMode(oSB);
      }
   }

   public static final void drawProvincesBorder_ContinentMode(SpriteBatch oSB) {
      if ((
            CFG.fTerrainMode_LinePercentage = CFG.fTerrainMode_LinePercentage
               + (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0F * 100.0F
         )
         > 100.0F) {
         CFG.fTerrainMode_LinePercentage = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lTerrainMode_LineTime = System.currentTimeMillis();

      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvinceBorder_ContinentMode(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var3)).drawProvinceBorder_ContinentModeWasteland(oSB);
      }
   }

   public static final void drawProvincesBorder_ContinentMode_FogOfWarDiscovey(SpriteBatch oSB) {
      if ((
            CFG.fTerrainMode_LinePercentage = CFG.fTerrainMode_LinePercentage
               + (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0F * 100.0F
         )
         > 100.0F) {
         CFG.fTerrainMode_LinePercentage = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lTerrainMode_LineTime = System.currentTimeMillis();

      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvinceBorder_ContinentMode_FogOfWarDiscovery(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var3)).drawProvinceBorder_ContinentModeWasteland(oSB);
      }
   }

   public static final void drawProvincesBorder_RegionsMode(SpriteBatch oSB) {
      if ((
            CFG.fTerrainMode_LinePercentage = CFG.fTerrainMode_LinePercentage
               + (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0F * 100.0F
         )
         > 100.0F) {
         CFG.fTerrainMode_LinePercentage = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lTerrainMode_LineTime = System.currentTimeMillis();

      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvinceBorder_RegionMode(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var3)).drawProvinceBorder_RegionModeWasteland(oSB);
      }
   }

   public static final void drawProvincesBorder_RegionsMode_FogOfWarDiscovery(SpriteBatch oSB) {
      if ((
            CFG.fTerrainMode_LinePercentage = CFG.fTerrainMode_LinePercentage
               + (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0F * 100.0F
         )
         > 100.0F) {
         CFG.fTerrainMode_LinePercentage = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lTerrainMode_LineTime = System.currentTimeMillis();

      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var2)).drawProvinceBorder_RegionMode_FogOfWarDiscovery(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var3)).drawProvinceBorder_RegionModeWasteland(oSB);
      }
   }

   public static final void drawProvincesBorderInStartGame(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorderInStartGame(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorderInStartGame_Wasteland(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorderInStartGame(oSB);
      }
   }

   public static final void drawProvincesBorderInStartGame_FogOfWar(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorderInStartGame(oSB);
      }

      if (Game_Calendar.getColonizationOfWastelandIsEnabled()) {
         for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorderInStartGame(oSB);
         }
      } else {
         for (int var3 = 0; var3 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var3++) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var3)).drawProvinceBorderInStartGame_Wasteland(oSB);
         }
      }

      for (int var4 = 0; var4 < CFG.NUM_OF_PROVINCES_IN_VIEW; var4++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var4)).drawProvinceBorderInStartGame(oSB);
      }
   }

   public static final void drawLandProvincesBorder(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder(oSB);
      }
   }

   public static final void drawProvincesBorder_PrintAMap(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder_PrintAMap(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_PrintAMap(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_PrintAMap(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_CreateRandomGame(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_CreateRandomGameWasteland(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_CreateRandomGame(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final void drawProvincesBorder_DrawJustInnerBorder(SpriteBatch oSB) {
      for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; i++) {
         CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
      }

      for (int var2 = 0; var2 < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; var2++) {
         CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(var2)).drawProvinceBorder_CreateRandomGame(oSB);
      }

      for (int var3 = 0; var3 < CFG.NUM_OF_PROVINCES_IN_VIEW; var3++) {
         CFG.game.getProvince(CFG.game.getProvinceInViewID(var3)).drawProvinceBorder_CreateRandomGame(oSB);
      }

      oSB.setColor(Color.WHITE);
   }

   interface DrawProvinces {
      void draw(SpriteBatch var1);
   }
}
