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
   public static List<Integer> GARRISON_PROVINCES = new ArrayList<>();
   public static int PARTISAN_VIEW_INDEX = 0;
   public static boolean PARTISAN_MAP_ON = false;
}
