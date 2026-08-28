package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class AI_Assistant {
   public static boolean ENABLED = false;
   public static int PRIORITY_ENEMY = -1;
   public static List<Integer> PRIORITY_COUNTRIES = new ArrayList<>();
   public static boolean ALLOW_BUDGET = false;
   public static boolean ALLOW_GOV_CHANGE = false;
   public static boolean ALLOW_ASSIMILATION = false;
   public static boolean ALLOW_COLONIZATION = false;
   public static boolean ALLOW_DIPLOMACY_RESPONSE = false;
   public static boolean ALLOW_DIPLOMACY_ACTIONS = false;
   public static int FOREIGN_POLICY = 0;
   public static int MIGRATION_POLICY = 1;
   public static int MINORITY_TAX = 1;
   public static int NUCLEAR_DOCTRINE = 0;
    public static List<Integer> FORT_STRIP_PROVINCES = new ArrayList<>();
    public static List<Integer> PARTISAN_HOTSPOTS = new ArrayList<>();
    public static java.util.Map<Integer, Integer> PARTISAN_UPRISINGS = new java.util.HashMap<>();
    public static java.util.Map<Integer, Float> CIV_FAITH = new java.util.HashMap<>();
    public static java.util.Map<Integer, Integer> CIV_FAITH_BASE = new java.util.HashMap<>();

    public static float getFaith(int civID) {
       if (civID <= 0) {
          return 1.0F;
       }
       int tTotal = 0;
       int tControlled = 0;
       for (int p = 0; p < CFG.game.getProvincesSize(); p++) {
          Province pr = CFG.game.getProvince(p);
          if (pr.getTrueOwnerOfProvince() == civID) {
             tTotal++;
             if (!pr.isOccupied()) {
                tControlled++;
             }
          }
       }
       Integer tBase = CIV_FAITH_BASE.get(civID);
       if (tBase == null || tTotal > tBase) {
          tBase = tTotal;
          CIV_FAITH_BASE.put(civID, tBase);
       }
       if (tBase <= 0) {
          CIV_FAITH.put(civID, 0.0F);
          return 0.0F;
       }
       float tShare = (float)tControlled / (float)tBase;
       float tFaith = tShare >= 0.5F ? 1.0F : tShare / 0.5F;
       CIV_FAITH.put(civID, tFaith);
       return tFaith;
    }

    public static float getFaithRaw(int civID) {
       if (civID <= 0) {
          return 1.0F;
       }
       int tTotal = 0;
       int tControlled = 0;
       for (int p = 0; p < CFG.game.getProvincesSize(); p++) {
          Province pr = CFG.game.getProvince(p);
          if (pr.getTrueOwnerOfProvince() == civID) {
             tTotal++;
             if (!pr.isOccupied()) {
                tControlled++;
             }
          }
       }
       Integer tBase = CIV_FAITH_BASE.get(civID);
       if (tBase == null || tTotal > tBase) {
          tBase = tTotal;
          CIV_FAITH_BASE.put(civID, tBase);
       }
       if (tBase <= 0) {
          return 0.0F;
       }
        return (float)tControlled / (float)tBase;
     }

     public static final int GARRISON_SUPPRESS_MIN = 1000;
     public static List<Integer> GARRISON_PROVINCES = new ArrayList<>();
    public static int PARTISAN_VIEW_INDEX = 0;
     private static int PARTISAN_IMG = -1;
     private static int WARFAITH_IMG = -1;
      private static int WARFAITH_ENEMY_IMG = -1;
      private static int GARRISON_LINE_IMG = -1;
      private static int FORT_LINE_IMG = -1;
      private static int PRIORITY_COUNTRY_IMG = -1;
      private static int RISK_UPRISING_IMG = -1;
      private static boolean ICONS_REGISTERED = false;

    private static void registerIcons() {
       if (ICONS_REGISTERED) {
          return;
       }

       ICONS_REGISTERED = true;

        try {
           PARTISAN_IMG = ImageManager.addImage("UI/partisan_icon.png");
           com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icons registered partisan=" + PARTISAN_IMG);
        } catch (Exception varE) {
           com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icon register FALLBACK: " + varE.getMessage());
        }
        try {
           WARFAITH_IMG = ImageManager.addImage("UI/war_faith.png");
           com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icons registered warfaith=" + WARFAITH_IMG);
        } catch (Exception varE) {
           com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icon register FALLBACK: " + varE.getMessage());
        }
         try {
            WARFAITH_ENEMY_IMG = ImageManager.addImage("UI/war_faith_enemy.png");
            com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icons registered warfaith_enemy=" + WARFAITH_ENEMY_IMG);
         } catch (Exception varE) {
            com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icon register FALLBACK: " + varE.getMessage());
         }
         try {
            GARRISON_LINE_IMG = ImageManager.addImage("UI/garrison_line.png");
            com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icons registered garrisonline=" + GARRISON_LINE_IMG);
         } catch (Exception varE) {
            com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icon register FALLBACK: " + varE.getMessage());
         }
         try {
            FORT_LINE_IMG = ImageManager.addImage("UI/fort_line.png");
            com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icons registered fortline=" + FORT_LINE_IMG);
         } catch (Exception varE) {
            com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icon register FALLBACK: " + varE.getMessage());
         }
          try {
             PRIORITY_COUNTRY_IMG = ImageManager.addImage("UI/priority_enemy.png");
             com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icons registered prioritycountry=" + PRIORITY_COUNTRY_IMG);
          } catch (Exception varE) {
             com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icon register FALLBACK: " + varE.getMessage());
          }
          try {
             RISK_UPRISING_IMG = ImageManager.addImage("UI/risk_uprising.png");
             com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icons registered riskuprising=" + RISK_UPRISING_IMG);
          } catch (Exception varE) {
             com.badlogic.gdx.Gdx.app.log("AoC", "MARKERS: icon register FALLBACK: " + varE.getMessage());
          }
       }

     public static int getPartisanImageID() {
        registerIcons();
        return PARTISAN_IMG;
     }
     public static int getWarFaithImageID() {
        registerIcons();
        return WARFAITH_IMG;
     }
      public static int getWarFaithEnemyImageID() {
         registerIcons();
         return WARFAITH_ENEMY_IMG;
      }
      public static int getGarrisonLineImageID() {
         registerIcons();
         return GARRISON_LINE_IMG;
      }
      public static int getFortLineImageID() {
         registerIcons();
         return FORT_LINE_IMG;
      }
      public static int getPriorityCountryImageID() {
         registerIcons();
         return PRIORITY_COUNTRY_IMG;
      }
      public static int getRiskUprisingImageID() {
         registerIcons();
         return RISK_UPRISING_IMG;
      }
      public static boolean PARTISAN_MAP_ON = false;
   public static boolean IS_ISSUING_ORDERS = false;
   public static boolean FRONTLINE_ON = true;
   public static boolean RISK_UPRISING_ON = true;
}
