package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CFG {
   public static boolean LOGS = false;
   public static boolean DEBUG_MODE = false;
   public static String sDEBUG = "#";
   public static final String AGE_OF_CIVLIZATIONS = "Bloody Europe II";
   public static final String VERSION = "1.01415_ELA";
   public static int iAgeOfCivilizationsWidth = -1;
   public static final String sJakowski = "Bloody Team";
   public static final String sJakowski_2 = "Bloody Team";
   public static final String sJakowskiGames = "Bloody Team";
   public static final String sJakowskiGames_2 = "Bloody Team";
   public static int iJakowskiGamesWidth;
   public static final String sJakowskiGames_Presents = "presents";
   public static int iJakowskiGames_PresentsWidth;
   public static boolean LANDSCAPE = false;
   public static EditorManager editorManager;
   public static final String FILE_UI_PATH = "UI/";
   public static final String FILE_GAME_PATH = "game/";
   public static final String FILE_MAP_PATH = "map/";
   public static final String FILE_MAP_UPDATE_PATH = "update/";
   public static final String FILE_MAPS_DATA_PATH = "data/";
   public static final String FILE_MAP_BACKGROUND_PATH = "backgrounds/";
   public static final String FILE_MAP_CONTINENTS_PATH = "continents/";
   public static final String FILE_MAP_CONTINENTS_PACKGES_PATH = "packges/";
   public static final String FILE_MAP_CONTINENTS_PACKGES_DATA_PATH = "packges_data/";
   public static final String FILE_MAP_REGIONS_PATH = "regions/";
   public static final String FILE_MAP_REGIONS_PACKGES_PATH = "packges/";
   public static final String FILE_MAP_REGIONS_PACKGES_DATA_PATH = "packges_data/";
   public static final String FILE_LANGUAGES_JUST_PATH = "languages/";
   public static final String FILE_LANGUAGES_PATH = "languages/Bundle";
   public static final String FILE_LANGUAGES_CIVS_PATH = "languages/civilizations/Bundle";
   public static final String FILE_LANGUAGES_LOADING_PATH = "languages/loading/Bundle";
   public static final String FILE_SETTINGS = "settings";
   public static final String FILE_SETTINGS_LAST_ACTIVE_MAP = "settings_map";
   public static final String FILE_SETTINGS_LOADING_STATUS = "status";
   public static final String FILE_CONFIG = "config.ini";
   public static final String FILE_UI_FONTS_PATH = "fonts/";
   public static final String FILE_UI_FONT_CHARACTERS_MAIN_PATH = "characters_main";
   public static final String FILE_UI_ICONS_PATH = "icons/";
   public static final String FILE_UI_CROWNS_PATH = "crowns/";
   public static final String FILE_UI_MOVE_STYLES_PATH = "move_styles/";
   public static final String FILE_UI_ICONS_RELIGIONS_PATH = "religions/";
   public static final String FILE_UI_BUTTONS_PATH = "buttons/";
   public static final String FILE_UI_SR_PATH = "sr/";
   public static final String FILE_UI_SR_OVER_PATH = "sr_over/";
   public static final String FILE_UI_TOPBAR_PATH = "top/";
   public static final String FILE_UI_BOTBAR_PATH = "bot/";
   public static final String FILE_UI_LINES_PATH = "lines/";
   public static final String FILE_UI_LOADING_PATH = "loading/";
   public static final String FILE_UI_FLAGS_PATH = "flags/";
   public static final String FILE_UI_TERRAIN_PATH = "terrain/";
   public static final String FILE_UI_BOTTOM_PATH = "bottom/";
   public static final String FILE_UI_EDITOR_PATH = "editor/";
   public static final String FILE_UI_DIALOG_PATH = "dialog/";
   public static final String FILE_UI_TITLE_PATH = "title/";
   public static final String FILE_UI_MAIN_MENU_PATH = "main_menu/";
   public static final String FILE_UI_NEW_GAME_PATH = "new_game/";
   public static final String FILE_UI_SLIDE_PATH = "slide/";
   public static final String FILE_UI_PICKER_PATH = "picker/";
   public static final String FILE_UI_ARMY_PATH = "army/";
   public static final String FILE_UI_DIFFICULTY_PATH = "difficulty/";
   public static final String FILE_UI_GRAPH_PATH = "graph/";
   public static final String FILE_UI_EVENTS_PATH = "events/";
   public static final String FILE_UI_EVENTS_DEFAULT = "default.png";
   public static final String FILE_GAME_LIST = "Age_of_Civilizations";
   public static final String FILE_GAME_LIST_ACTIVE = "_Active";
   public static final String FILE_MUSIC = "music/";
   public static final String FILE_SOUNDS = "sounds/";
   public static final String FILE_IDEOLOGIES_LIST = "Governments";
   public static final String FILE_AGES_LIST = "Ages";
   public static final String FILE_PLAGUES_LIST = "Diseases";
   public static final String FILE_GAME_FLAGS_EDITOR_PATH = "flags_editor/";
   public static final String FILE_GAME_FLAGS_EDITOR_DIVISIONS_PATH = "divisions/";
   public static final String FILE_GAME_FLAGS_EDITOR_DIVISIONS_LIST = "divisions";
   public static final String FILE_GAME_FLAGS_EDITOR_OVERLAYS_PATH = "overlays/";
   public static final String FILE_GAME_FLAGS_EDITOR_OVERLAYS_LIST = "overlays";
   public static final String FILE_GAME_UNIONS_PATH = "unions/";
   public static final String FILE_GAME_UNIONS_DATA = "data";
   public static final String FILE_GAME_CIVILIZATIONS_PATH = "civilizations/";
   public static final String FILE_GAME_CIVILIZATIONS_COLORS_PATH = "civilizations_colors/";
   public static final String FILE_GAME_CIVILIZATIONS_FLAGS_DATA_EXTRA_TAG = "_FD";
   public static final String FILE_GAME_CIVILIZATIONS_FLAG_H_EXTRA_TAG = "_FLH.png";
   public static final String FILE_GAME_CIVILIZATIONS_FLAG_EXTRA_TAG = "_FL.png";
   public static final String FILE_GAME_CIVILIZATIONS_EDITOR_NAME = "_NM";
   public static final String FILE_GAME_CIVILIZATIONS_WIKIPEDIA_INFO_PATH = "civilizations_informations/";
   public static final String FILE_GAME_LEADERS_PATH = "leaders/";
   public static final String FILE_GAME_LEADERS_IMG_PATH = "leadersIMG/";
   public static final String FILE_GAME_CIVILIZATIONS_EDITOR_PATH = "civilizations_editor/";
   public static final String FILE_GAME_PALLETS_OF_CIVS_COLORS_PATH = "pallets_of_civs_colors/";
   public static final String FILE_GAME_FLAGS_PATH = "flags/";
   public static final String FILE_GAME_FLAGSH_PATH = "flagsH/";
   public static final String FILE_GAME_SCENARIOS_PATH = "scenarios/";
   public static final String FILE_GAME_SCENARIOS_PROVINCE = "_PD";
   public static final String FILE_GAME_SCENARIOS_HRE = "_HRE";
   public static final String FILE_GAME_SCENARIOS_ARMIES = "_A";
   public static final String FILE_GAME_SCENARIOS_DIPLOMACY = "_D";
   public static final String FILE_GAME_SCENARIOS_WASTELAND = "_W";
   public static final String FILE_GAME_SCENARIOS_CORES = "_C";
   public static final String FILE_GAME_SCENARIOS_EVENTS = "_E";
   public static final String FILE_GAME_SCENARIOS_INFO = "_INFO";
   public static final String FILE_GAME_SCENARIOS_PREVIEW = "preview.png";
   public static final String FILE_GAME_SCENARIOS_EVENTS_IMAGES = "events/";
   public static final String FILE_GAME_SAVE_TIMELINE_PATH = "TS/";
   public static final String FILE_GAME_SAVE_TIMELINE_TURNCHANGES_PATH = "TURN/";
   public static final String FILE_GAME_SAVE_TIMELINE = "_T";
   public static final String FILE_GAME_SAVE_TIMELINE_OWNERS = "_O";
   public static final String FILE_GAME_SAVE_TIMELINE_TURN_CHANGES = "_C";
   public static final String FILE_GAME_SAVE_TIMELINE_STATS = "_S";
   public static final String FILE_GAME_ALLIANCE_NAMES_PATH = "alliance_names/";
   public static final String FILE_GAME_DIPLOMACY_COLORS_PATH = "diplomacy_colors/";
   public static final String FILE_GAME_DIPLOMACY_COLORS_PACKAGES_PATH = "packages/";
   public static final String FILE_GAME_LINES_PATH = "lines/";
   public static final String FILE_GAME_RELIGIONS_PATH = "religions/";
   public static final String FILE_GAME_TERRAIN_TYPES_PATH = "terrain_types/";
   public static final String FILE_GAME_SERVICE_RIBBONS_PATH = "service_ribbons/";
   public static final String FILE_GAME_STATISTICS_CIV_PATH = "saves/stats/civ/";
   public static final String FILE_SAVES_PATH = "saves/games/";
   public static final String FILE_MAP_INFORMATIONS = "config";
   public static final String FILE_MAP_PROVINCE_NAMES = "province_names/";
   public static final String FILE_MAP_PROVINCE_NAMES_FILE = "names";
   public static final String FILE_MAP_SUGGESTED_OWNERS_PATH = "suggested_owners/";
   public static final String FILE_MAP_PRE_DEFINED_BORDERS_PATH = "predefined_borders/";
   public static final String FILE_MAP_DATA = "data/";
   public static final String FILE_MAP_PROVINCES = "provinces/";
   public static final String FILE_MAP_ROUTES = "sea_routes/";
   public static final String FILE_MAP_WASTELAND_MAPS_PATH = "wasteland_maps/";
   public static final String FILE_MAP_FORMABLE_CIVS_PATH = "formable_civs/";
   public static final String FILE_MAP_CITIES_EDITOR = "cities/";
   public static final String FILE_MAP_TRADE_ZONES_PATH = "trade_zones/";
   public static final String FILE_MAP_TRADE_ZONES_ZONES_PATH = "zones/";
   public static final String FILE_MAP_TRADE_ZONES_UPDATES_PATH = "zones_updates/";
   public static final String FILE_MAP_TRADE_ZONES_ROUTES_PATH = "routes/";
   public static final String FILE_MAP_ARMY_BOXES = "army_boxes/";
   public static final String FILE_MAP_SCALES_BG = "scales/";
   public static final String FILE_MAP_SCALE_PROVINCE_BG = "provinces/";
   public static final String FILE_MAP_CENTER_ARMY = "center";
   public static final String FILE_MAP_CITIES = "cities/";
   public static final String FILE_MAP_CITIES_0_JSON = "cities.json";
   public static final String FILE_MAP_CITIES_1_JSON = "cities_1.json";
   public static final String FILE_MAP_CITIES_2_JSON = "cities_2.json";
   public static final String FILE_MAP_CITIES_3_JSON = "cities_3.json";
   public static final String FILE_MAP_CITIES_4_JSON = "cities_4.json";
   public static final String FILE_MAP_WONDERS = "wonders/";
   public static final String FILE_MAP_WONDERS_IMAGES = "images/";
   public static final String FILE_MAP_WONDERS_JSON = "wonders.json";
   public static final String FILE_MAP_MOUNTAINS_JSON = "mountains.json";
   public static final String FILE_MAP_REGIONS = "regions";
   public static final String FILE_MAP_ICON = "ico.png";
   public static final String WWW_WIKI = "https://en.wikipedia.org/wiki/";
   public static final String WWW_LUKASZJAKOWSKI = "http://lukaszjakowski.pl";
   public static final String WWW_AOC_FACEBOOK = "https://www.facebook.com/AgeofCivilizationsJakowski/";
   public static int GAME_WIDTH = 1;
   public static int GAME_HEIGHT = 1;
   public static int iNumOfFPS = 60;
   public static final int MIN_NUM_OF_FPS = 22;
   public static final Color BACKGROUND_COLOR = new Color(0.0F, 0.0F, 0.0F, 1.0F);
   public static final Color COLOR_MINIMAP_BORDER = new Color(0.251F, 0.192F, 0.09F, 1.0F);
   public static PalletOfCivsColors_Data editorPalletOfCivsColors_Data;
   public static Terrain_GameData3 editorTerrain_Data2;
   public static float GUI_SCALE = 1.0F;
   public static float DENSITY = 1.0F;
   public static boolean XHDPI = false;
   public static boolean XXHDPI = false;
   public static boolean XXXHDPI = false;
   public static boolean XXXXHDPI = false;
   public static boolean RENDER = true;
   public static boolean RENDER2 = true;
   public static boolean RENDER3 = true;
   public static CFG.RenderUpdate renderUpdate_3;
   public static int PALETTE_ID = 0;
   public static int NUM_OF_PROVINCES_IN_VIEW = 0;
   public static int NUM_OF_SEA_PROVINCES_IN_VIEW = 0;
   public static int NUM_OF_WASTELAND_PROVINCES_IN_VIEW = 0;
   public static int NUM_OF_REGIONS_IN_VIEW = 0;
   public static final int PROVINCE_OWNER_COLOR_INTERVAL = 725;
   public static final int PROVINCE_VIEW_COLOR_INTERVAL = 250;
   public static HashMap<String, Long> PROVINCE_BORDER_ANIMATION_TIME;
   public static SettingsManager settingsManager = new SettingsManager();
   public static int PADDING = 5;
   public static int BUTTON_HEIGHT = 68;
   public static int BUTTON_WIDTH = 90;
   public static int PREVIEW_HEIGHT = 194;
   public static final int RESIZE_PADDING_XY = 6;
   public static int CIV_COLOR_WIDTH = 3;
   public static int CIV_NAME_BG_EXTRA_WIDTH = 8;
   public static int CIV_NAME_BG_EXTRA_HEIGHT = 5;
   public static int CIV_NAME_BG_EXTRA_WIDTH_ARMY = 6;
   public static int CIV_NAME_BG_EXTRA_HEIGHT_ARMY = 4;
   public static int ARMY_BG_EXTRA_WIDTH = 3;
   public static int ARMY_BG_EXTRA_HEIGHT = 2;
   public static final Color COLOR_TEXT_PROVINCE_OWNER = new Color(0.988F, 1.0F, 0.796F, 1.0F);
   public static final Color COLOR_TEXT_PROVINCE_OWNER_HOVER = new Color(0.825F, 0.825F, 0.615F, 1.0F);
   public static final Color COLOR_TEXT_PROVINCE_OWNER_ACTIVE = new Color(0.72F, 0.74F, 0.54F, 1.0F);
   public static final Color COLOR_TEXT_RESEARCH = new Color(0.4F, 0.6F, 0.8F, 1.0F);
   public static final Color COLOR_TEXT_DEVELOPMENT = new Color(0.19607843F, 0.19607843F, 0.39215687F, 1.0F);
   public static final Color COLOR_TEXT_POPULATION = new Color(0.392F, 0.533F, 0.251F, 1.0F);
   public static final Color COLOR_TEXT_POPULATION_HOVER = new Color(0.595F, 0.743F, 0.427F, 1.0F);
   public static final Color COLOR_TEXT_POPULATION_ACTIVE = new Color(0.4F, 0.51F, 0.3F, 1.0F);
   public static final Color COLOR_TEXT_POPULATION_GROWTHRATE_MIN = new Color(0.17254902F, 0.67058825F, 0.19607843F, 1.0F);
   public static final Color COLOR_TEXT_POPULATION_GROWTHRATE_MAX = new Color(0.16862746F, 0.44313726F, 0.20784314F, 1.0F);
   public static final float PROVINCE_ALPHA_HAPPINESS = 0.5F;
   public static final Color COLOR_TEXT_HAPPINESS_MIN = new Color(0.7411765F, 0.19215687F, 0.30588236F, 1.0F);
   public static final Color COLOR_TEXT_HAPPINESS_MAX = new Color(0.9843137F, 0.9843137F, 0.019607844F, 1.0F);
   public static final Color COLOR_TEXT_RECRUITABLE_MIN = new Color(1.0F, 1.0F, 1.0F, 1.0F);
   public static final Color COLOR_TEXT_RECRUITABLE_MAX = new Color(0.11764706F, 0.13725491F, 0.29411766F, 1.0F);
   public static final Color COLOR_TEXT_REVOLUTION_MIN = new Color(0.8235294F, 0.5882353F, 0.29411766F, 1.0F);
   public static final Color COLOR_TEXT_REVOLUTION_MIN_0 = new Color(0.09019608F, 0.39215687F, 0.078431375F, 0.25F);
   public static final Color COLOR_TEXT_REVOLUTION_MAX = new Color(0.50980395F, 0.13725491F, 0.078431375F, 1.0F);
   public static final Color COLOR_TEXT_PROVINCE_STABILITY_MIN = new Color(0.5686275F, 0.13725491F, 0.09803922F, 1.0F);
   public static final Color COLOR_TEXT_PROVINCE_STABILITY_MIN_0 = new Color(0.09019608F, 0.39215687F, 0.078431375F, 0.25F);
   public static final Color COLOR_TEXT_PROVINCE_STABILITY_MAX = new Color(0.23529412F, 0.49019608F, 0.11764706F, 1.0F);
   public static final Color COLOR_DISTANCE_MIN = new Color(0.8627451F, 0.84313726F, 0.1764706F, 1.0F);
   public static final Color COLOR_DISTANCE_MAX = new Color(0.43137255F, 0.09803922F, 0.09803922F, 1.0F);
   public static final Color COLOR_TEXT_HAPPINESS_HOVER = new Color(0.99607843F, 0.5137255F, 0.007843138F, 1.0F);
   public static final Color COLOR_TEXT_HAPPINESS_ACTIVE = new Color(0.9843137F, 0.6901961F, 0.003921569F, 1.0F);
   public static final Color COLOR_TEXT_CHECKBOX_TRUE = new Color(0.55F, 0.8F, 0.0F, 0.25F);
   public static final Color COLOR_TEXT_CHECKBOX_FALSE = new Color(0.8F, 0.137F, 0.0F, 0.25F);
   public static final Color COLOR_TEXT_ECONOMY = new Color(0.776F, 0.518F, 0.227F, 1.0F);
   public static final Color COLOR_TEXT_ECONOMY_HOVER = new Color(0.708F, 0.448F, 0.173F, 1.0F);
   public static final Color COLOR_TEXT_ECONOMY_ACTIVE = new Color(0.552F, 0.36F, 0.141F, 1.0F);
   public static final Color COLOR_TEXT_TECHNOLOGY = new Color(0.8F, 0.8F, 0.8F, 1.0F);
   public static final Color COLOR_TEXT_CIV_INFO = new Color(0.40392157F, 0.41960785F, 0.43137255F, 1.0F);
   public static final Color COLOR_TEXT_CIV_INFO_HOVER = new Color(0.575F, 0.575F, 0.575F, 1.0F);
   public static final Color COLOR_TEXT_CIV_INFO_ACTIVE = new Color(0.66F, 0.66F, 0.66F, 1.0F);
   public static final Color COLOR_TEXT_CIV_INFO_TITLE = new Color(0.6862745F, 0.6862745F, 0.6862745F, 1.0F);
   public static final Color COLOR_TEXT_TOP_VIEWS = new Color(0.37254903F, 0.37254903F, 0.37254903F, 1.0F);
   public static final Color COLOR_TEXT_TOP_VIEWS_HOVER = new Color(0.44705883F, 0.4509804F, 0.45490196F, 1.0F);
   public static final Color COLOR_TEXT_TOP_VIEWS_ACTIVE = new Color(0.85490197F, 0.7490196F, 0.36862746F, 1.0F);
   public static final Color COLOR_TEXT_TOP_VIEWS_NOT_CLICKABLE = new Color(0.18431373F, 0.19215687F, 0.20784314F, 0.7F);
   public static final Color COLOR_COLOR_PICKER_RGB_BG = new Color(0.047058824F, 0.0627451F, 0.078431375F, 0.55F);
   public static final Color COLOR_LOADING_SPLIT_ACTIVE = new Color(0.96862745F, 0.76862746F, 0.41960785F, 0.65F);
   public static final Color COLOR_LOADING_SPLIT = new Color(0.77254903F, 0.6117647F, 0.2627451F, 0.35F);
   public static final Color COLOR_NEW_GAME_EDGE_LINE = new Color(0.451F, 0.329F, 0.11F, 1.0F);
   public static final Color COLOR_FLAG_FRAME = new Color(0.76862746F, 0.6117647F, 0.2627451F, 1.0F);
   public static final Color COLOR_TEXT_CIV_NAME = new Color(0.985F, 0.985F, 0.985F, 1.0F);
   public static final Color COLOR_TEXT_CIV_NAME_HOVERED = new Color(0.784F, 0.784F, 0.784F, 1.0F);
   public static final Color COLOR_TEXT_CIV_NAME_ACTIVE = new Color(0.725F, 0.725F, 0.725F, 1.0F);
   public static final Color COLOR_TEXT_RANK = new Color(0.819F, 0.819F, 0.819F, 1.0F);
   public static final Color COLOR_TEXT_RANK_HOVER = new Color(0.628F, 0.628F, 0.645F, 1.0F);
   public static final Color COLOR_TEXT_RANK_ACTIVE = new Color(0.584F, 0.584F, 0.599F, 1.0F);
   public static final Color COLOR_SLIDER_LEFT_BG = new Color(0.11764706F, 0.13725491F, 0.23529412F, 1.0F);
   public static final Color COLOR_SLIDER_RIGHT_BG = new Color(0.98039216F, 0.98039216F, 0.98039216F, 1.0F);
   public static final Color COLOR_SLIDER_LEFT_BG2 = new Color(0.078431375F, 0.23529412F, 0.039215688F, 1.0F);
   public static final Color COLOR_SLIDER_LEFT_BG3 = new Color(0.29411766F, 0.09803922F, 0.13725491F, 1.0F);
   public static final Color COLOR_SLIDER_LEFT_INSTANTLY = new Color(0.09803922F, 0.23529412F, 0.15686275F, 1.0F);
   public static final Color COLOR_CREATE_NEW_GAME_BOX_PLAYERS = new Color(0.4509804F, 0.32941177F, 0.10980392F, 1.0F);
   public static final Color COLOR_GRADIENT_DARK_BLUE = new Color(0.025F, 0.04F, 0.08F, 0.75F);
   public static final Color COLOR_GRADIENT_LIGHTER_DARK_BLUE = new Color(0.043F, 0.102F, 0.157F, 0.75F);
   public static final Color COLOR_GRADIENT_DIPLOMACY = new Color(0.09019608F, 0.16078432F, 0.26666668F, 0.75F);
   public static final Color COLOR_TEXT_MODIFIER_NEGATIVE = new Color(0.98039216F, 0.15686275F, 0.15686275F, 1.0F);
   public static final Color COLOR_TEXT_MODIFIER_NEGATIVE2 = new Color(0.7490196F, 0.18431373F, 0.14117648F, 1.0F);
   public static final Color COLOR_TEXT_MODIFIER_NEGATIVE_HOVER = new Color(0.70980394F, 0.17254902F, 0.1254902F, 1.0F);
   public static final Color COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE = new Color(0.6509804F, 0.14117648F, 0.09411765F, 1.0F);
   public static final Color COLOR_TEXT_MODIFIER_NEUTRAL = new Color(0.8F, 0.8F, 0.8F, 1.0F);
   public static final Color COLOR_TEXT_MODIFIER_NEUTRAL2 = new Color(0.8627451F, 0.78431374F, 0.27450982F, 1.0F);
   public static final Color COLOR_TEXT_MODIFIER_POSITIVE = new Color(0.007843138F, 0.5176471F, 0.011764706F, 1.0F);
   public static final Color COLOR_TEXT_MODIFIER_POSITIVE_HOVER = new Color(0.003921569F, 0.4509804F, 0.007843138F, 1.0F);
   public static final Color COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE = new Color(0.003921569F, 0.4F, 0.007843138F, 1.0F);
   public static final Color COLOR_TEXT_FREE_MOVE = new Color(0.8980392F, 0.9254902F, 0.02745098F, 1.0F);
   public static final Color COLOR_TEXT_FREE_MOVE_ACTIVE = new Color(0.6745098F, 0.68235296F, 0.007843138F, 1.0F);
   public static final Color COLOR_TEXT_FREE_MOVE_HOVER = new Color(0.7607843F, 0.7764706F, 0.015686275F, 1.0F);
   public static final Color COLOR_TEXT_PROVINCE_VALUE = new Color(0.784F, 0.588F, 0.196F, 1.0F);
   public static final Color COLOR_TEXT_PROVINCE_VALUE_HOVER = new Color(0.668F, 0.473F, 0.152F, 1.0F);
   public static final Color COLOR_TEXT_PROVINCE_VALUE_ACTIVE = new Color(0.605F, 0.414F, 0.132F, 1.0F);
   public static final Color COLOR_TEXT_GREEN = new Color(0.173F, 0.671F, 0.196F, 1.0F);
   public static final float GRAPH_DESC_TEXT_SCALE = 0.7F;
   public static final Color COLOR_TEXT_CNG_TOP_SCENARIO_NAME = new Color(0.9F, 0.9F, 0.9F, 1.0F);
   public static final Color COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER = new Color(0.78F, 0.78F, 0.78F, 1.0F);
   public static final Color COLOR_TEXT_CNG_TOP_SCENARIO_INFO = new Color(0.56F, 0.56F, 0.56F, 1.0F);
   public static final Color COLOR_TEXT_OPTIONS_NS = new Color(0.7372549F, 0.7490196F, 0.7647059F, 1.0F);
   public static final Color COLOR_TEXT_OPTIONS_NS_HOVER = new Color(0.57254905F, 0.58431375F, 0.5921569F, 1.0F);
   public static final Color COLOR_TEXT_OPTIONS_NS_ACTIVE = new Color(0.5019608F, 0.5137255F, 0.5294118F, 1.0F);
   public static final Color COLOR_TEXT_OPTIONS_LEFT_NS = new Color(0.8392157F, 0.8392157F, 0.8392157F, 1.0F);
   public static final Color COLOR_TEXT_OPTIONS_LEFT_NS_HOVER = new Color(0.7137255F, 0.7137255F, 0.7137255F, 1.0F);
   public static final Color COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE = new Color(0.6509804F, 0.6509804F, 0.6509804F, 1.0F);
   public static Graph_CircleDraw graphCircleDraw;
   public static final Color COLOR_STARTINGMONEY_MIN = new Color(0.6F, 0.20392157F, 0.023529412F, 1.0F);
   public static final Color COLOR_STARTINGMONEY_0 = new Color(0.84705883F, 0.9411765F, 0.6509804F, 1.0F);
   public static final Color COLOR_STARTINGMONEY_MAX = new Color(0.1254902F, 0.5254902F, 0.27058825F, 1.0F);
   public static final Color COLOR_BUTTON_MENU_HOVER_BG = new Color(1.0F, 1.0F, 1.0F, 0.9F);
   public static final Color COLOR_BUTTON_MENU_ACTIVE_BG = new Color(1.0F, 1.0F, 1.0F, 0.8F);
   public static final Color COLOR_BUTTON_MENU_TEXT = new Color(0.82F, 0.82F, 0.82F, 1.0F);
   public static final Color COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE = new Color(0.78F, 0.78F, 0.78F, 0.4F);
   public static final Color COLOR_BUTTON_MENU_TEXT_HOVERED = new Color(0.71F, 0.715F, 0.72F, 1.0F);
   public static final Color COLOR_BUTTON_MENU_TEXT_ACTIVE = new Color(0.1F, 0.1F, 0.1F, 1.0F);
   public static final Color COLOR_BUTTON_GAME_TEXT = new Color(0.376F, 0.388F, 0.376F, 1.0F);
   public static final Color COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE = new Color(0.674F, 0.09F, 0.066F, 0.5F);
   public static final Color COLOR_BUTTON_GAME_TEXT_ACTIVE = new Color(0.768F, 0.608F, 0.263F, 1.0F);
   public static final Color COLOR_BUTTON_GAME_TEXT_HOVERED = new Color(0.445F, 0.445F, 0.445F, 1.0F);
   public static final Color COLOR_BUTTON_GAME_TEXT_IMPORTANT = new Color(0.548F, 0.562F, 0.548F, 1.0F);
   public static final Color COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER = new Color(0.665F, 0.682F, 0.665F, 1.0F);
   public static final Color COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE = new Color(0.78F, 0.78F, 0.78F, 1.0F);
   public static final Color COLOR_TEXT_NUM_OF_PROVINCES = new Color(0.8039216F, 0.59607846F, 0.0F, 1.0F);
   public static final Color COLOR_TEXT_GOLDEN_AGE = new Color(0.9882353F, 0.8117647F, 0.2509804F, 1.0F);
   public static final Color COLOR_GRADIENT_TITLE_BLUE = new Color(0.105882354F, 0.16078432F, 0.2901961F, 0.775F);
   public static final Color COLOR_MESSAGE_TITLE = new Color(0.2F, 0.6F, 0.4F, 0.775F);
   public static final Color COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE = new Color(0.0F, 0.21960784F, 0.61960787F, 0.775F);
   public static boolean reverseDirectionX = true;
   public static boolean reverseDirectionY = true;
   public static int DIFFICULTY = 1;
   public static int FOG_OF_WAR = 1;
   public static boolean FILL_THE_MAP = true;
   public static boolean RANDOM_PLACMENT = false;
   public static boolean RANDOM_FILL = false;
   public static boolean SANDBOX_MODE = false;
   public static int SANDBOX_TECH = 50;
   public static boolean MANPOWER_SYSTEM = true;
   public static boolean DISEASES = true;
   public static boolean DesireForIndependenceVassals = true;
   public static boolean FREEPLAY_MODE;
   public static boolean SPECTATOR_MODE = false;
   public static boolean TOTAL_WAR_MODE = false;
   public static boolean NO_LIBERITY = false;
   public static int AI_CREATING_VASSALS = 96;
   public static int AI_CREATING_ALLIANCE = 19;
   public static final int DEFAULT_ARMY_NOT_SET_UPED = -1;
   public static final int DEFAULT_ARMY = 750;
   public static final int DEFAULT_ARMY_MAX = 25000;
   public static final int DEFAULT_POPULATION = 600;
   public static final int DEFAULT_POPULATION_MAX = 2000;
   public static final int DEFAULT_ECONOMY = 32000;
   public static final int DEFAULT_ECONOMY_MAX = 2000;
   public static final int DEFAULT_MONEY = 4500;
   public static final int DEFAULT_MONEY_MIN = -10000;
   public static final int DEFAULT_MONEY_MAX = 75000;
   public static final int DEFAULT_MONEY_MIN2 = -100000;
   public static final int DEFAULT_MONEY_MAX2 = 100000;
   public static final int DEFAULT_MONEY_NOT_SET_UPED = -999999;
   public static final Color RANDOM_CIVILIZATION_COLOR = new Color(0.03F, 0.03F, 0.05F, 1.0F);
   public static final String CIVILIZATION_FLAG_NOT_FOUND = "ran.png";
   public static final int MIN_POPULATION_IN_A_PROVINCE = 92;
   public static final int MIN_ECONOMY_IN_A_PROVINCE = 19;
   public static final float DEFAULT_GOODS_LEVEL = 0.2F;
   public static final float DEFAULT_RESEARACH_LEVEL = 0.0F;
   public static final float DEFAULT_INVESTMENTS_LEVEL = 0.15F;
   public static final int MAX_DIPLOMACY_POINTS = 85;
   public static int PLAYER_TURNID = 0;
   public static boolean regroupArmyMode = false;
   public static boolean chooseProvinceMode = false;
   public static int chosenProvinceID = -1;
   public static boolean migrateMode = false;
   public static boolean chooseProvinceMode_BEFORE = false;
   public static int activeProvince_BEFORE = -1;
   public static int ACTIVE_PROVINCE_INFO;
   public static int activeCivilizationArmyID = 0;
   public static boolean VIEW_SHOW_VALUES = true;
   public static boolean SHOW_ALL_MOVES = false;
   public static boolean SHOW_ONLY_COMBAT_MOVES = true;
   public static final int NUM_OF_GAMES_WON_TON_UNLOCK_SANDBOX_MODE = 3;
   public static final int COST_OF_RECRUIT_ARMY_MONEY = 5;
   public static final int COST_OF_FORM_CIVILIZATION_GOLD = 1000;
   public static final int COST_OF_FORM_CIVILIZATION_DIPLOMACY_POINTS = 24;
   public static final String RANDOM_CIV_TAG = "ran";
   public static String RANDOM_CIVILIZATION = null;
   public static CFG.TopBox topBox = new CFG.TopBox();
   public static float fTerrainMode_LinePercentage;
   public static long lTerrainMode_LineTime;
   public static String sLoading = "Loading";
   public static int iLoadingWidth;
   public static String sVERSION = "VERSION";
   public static String sAUTHOR = null;
   public static String sTOTAL;
   public static String sTOTAL_WORLDS_POPULATION;
   public static ThreadLocalRandom oR = ThreadLocalRandom.current();
   public static String sLoadingText = "";
   public static int iLoadingTextWidth = 0;
   public static long loadingTime = 0L;
   public static float LOADING_TEXT_FONT_SCALE = 0.7F;
   public static final int LOADING_CHANGE_TEXT_TIME = 2500;
   public static float PRESENTS_GAMES_SCALE = 1.0F;
   public static float PRESENTS_GAMES_SCALE2 = 0.7F;
   public static long PRESENTS_TIME = 0L;
   public static final int PRESENTS_TIME_INVIEW = 3500;
   public static int activeCivInfo = 0;
   public static Image activeCivFlag = null;
   public static Image activeCivLeader = null;
   public static int CIV_INFO_MENU_WIDTH = 280;
   public static int MENU_WIDTH_ACTION_ARMY = 55;
   public static Province_Cores_GameData province_Cores_GameData = null;
   public static FormableCivs_GameData formableCivs_GameData = null;
   public static Leader_GameData leader_GameData = null;
   public static Line_GameData editorLine_GameData = null;
   public static final float ALPHA_PROVINCE_REGIONS = 0.45F;
   public static final float ALPHA_PROVINCE_CONTINENTS = 0.7F;
   public static final float ALPHA_PROVINCE_TRADEZONES = 0.65F;
   public static Region_GameData editor_Region_GameData = null;
   public static Continent_GameData editor_Continent_GameData = null;
   public static String EDITOR_ACTIVE_GAMEDATA_TAG = null;
   public static String GO_TO_LINK = "";
   public static Package_ContinentsData editor_Package_ContinentsData = null;
   public static Package_RegionsData editor_Package_RegionsData = null;
   public static String CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG = null;
   public static final Color COLOR_BUTTON_EXTRA_DESCRIPTION = new Color(1.0F, 1.0F, 1.0F, 0.4F);
   public static final float PROVINCE_ALPHA_TERRAIN = 0.55F;
   public static TerrainTypesManager terrainTypesManager;
   public static final float PROVINCE_ALPHA_GROWTH_RATE = 0.75F;
   public static final float PROVINCE_ALPHA_GROWTH_RATE_INGAME = 0.6F;
   public static Color[] COLOR_GROWTH_RATE = new Color[]{
      new Color(1.0F, 0.9764706F, 0.64705884F, 0.75F),
      new Color(0.99607843F, 0.9607843F, 0.0F, 0.75F),
      new Color(0.99607843F, 0.8901961F, 0.0F, 0.75F),
      new Color(0.99607843F, 0.7490196F, 0.0F, 0.75F),
      new Color(0.99607843F, 0.60784316F, 0.0F, 0.75F),
      new Color(0.99607843F, 0.42352942F, 0.0F, 0.75F),
      new Color(0.99607843F, 0.23529412F, 0.0F, 0.75F),
      new Color(0.8627451F, 0.0F, 0.0F, 0.75F),
      new Color(0.54901963F, 0.0F, 0.0F, 0.75F),
      new Color(0.39215687F, 0.0F, 0.0F, 0.75F),
      new Color(0.3137255F, 0.0F, 0.0F, 0.75F)
   };
   public static final float PROVINCE_ALPHA_DISEASES = 0.725F;
   public static final float PROVINCE_ALPHA_ARMY = 0.575F;
   public static final Color COLOR_PROVINCE_ARMY_MIN = new Color(0.7058824F, 0.7058824F, 0.78431374F, 0.575F);
   public static final Color COLOR_PROVINCE_ARMY_MAX = new Color(0.96862745F, 0.9372549F, 0.39215687F, 0.575F);
   public static final float PROVINCE_ALPHA_PROVINCE_VALUE = 0.75F;
   public static int MAX_PROVINCE_VALUE = 10;
   public static final float PROVINCE_ALPHA_POPULATION = 0.6F;
   public static Color[] COLOR_POPULATION = new Color[]{
      new Color(0.8627451F, 0.93333334F, 0.78039217F, 0.6F),
      new Color(0.8F, 0.92941177F, 0.7372549F, 0.6F),
      new Color(0.6901961F, 0.89411765F, 0.59607846F, 0.6F),
      new Color(0.6117647F, 0.8666667F, 0.49019608F, 0.6F),
      new Color(0.5647059F, 0.87058824F, 0.3137255F, 0.6F),
      new Color(0.41568628F, 0.7921569F, 0.23529412F, 0.6F),
      new Color(0.37254903F, 0.7294118F, 0.19607843F, 0.6F),
      new Color(0.30588236F, 0.6039216F, 0.16078432F, 0.6F),
      new Color(0.2509804F, 0.49019608F, 0.13333334F, 0.6F),
      new Color(0.20392157F, 0.4F, 0.10980392F, 0.6F),
      new Color(0.14509805F, 0.28627452F, 0.078431375F, 0.6F)
   };
   public static Color[] COLOR_ECONOMY = new Color[]{
      new Color(1.0F, 0.92156863F, 0.8F, 0.6F),
      new Color(1.0F, 0.83137256F, 0.65882355F, 0.6F),
      new Color(1.0F, 0.77254903F, 0.56078434F, 0.6F),
      new Color(1.0F, 0.7294118F, 0.47843137F, 0.6F),
      new Color(1.0F, 0.63529414F, 0.3254902F, 0.6F),
      new Color(0.96862745F, 0.54509807F, 0.19215687F, 0.6F),
      new Color(0.9411765F, 0.4627451F, 0.019607844F, 0.6F),
      new Color(0.88235295F, 0.3882353F, 0.0627451F, 0.6F),
      new Color(0.7921569F, 0.24313726F, 0.02745098F, 0.6F),
      new Color(0.7137255F, 0.09803922F, 0.015686275F, 0.6F),
      new Color(0.654902F, 0.08627451F, 0.011764706F, 0.6F)
   };
   public static float PROVINCE_ALPHA_TECHNOLOGY_LEVEL = 0.45F;
   public static Color[] COLOR_TECHNOLOGY_LEVEL = new Color[]{
      new Color(0.94509804F, 0.95686275F, 1.0F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.8784314F, 0.8784314F, 0.9647059F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.79607844F, 0.8039216F, 1.0F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.7019608F, 0.7137255F, 0.9019608F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.6117647F, 0.627451F, 0.9411765F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.49803922F, 0.5176471F, 0.9529412F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.34901962F, 0.38039216F, 0.9019608F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.21960784F, 0.2509804F, 0.8509804F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.07450981F, 0.101960786F, 0.5803922F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.05490196F, 0.08235294F, 0.52156866F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL),
      new Color(0.043137256F, 0.07058824F, 0.43137255F, PROVINCE_ALPHA_TECHNOLOGY_LEVEL)
   };
   public static String sACTIVE_DIPLOMACY_COLORS_TAG;
   public static DiplomacyColors_GameData2 diplomacyColors_GameData;
   public static float ALPHA_DIPLOMACY = 0.35F;
   public static final int RELATION_AT_WAR = -100;
   public static final int RELATION_WHITE_PEACE_AFTER = 0;
   public static final Color COLOR_SLIDER_BORDER = new Color(0.42745098F, 0.32941177F, 0.14901961F, 1.0F);
   public static final Color COLOR_PORT_m1 = new Color(0.9607843F, 0.9607843F, 0.9607843F, 0.25F);
   public static final Color COLOR_PORT_0 = new Color(0.7607843F, 0.7647059F, 0.8039216F, 0.25F);
   public static final Color COLOR_PORT_1 = new Color(0.0F, 0.27450982F, 0.50980395F, 0.55F);
   public static final Color COLOR_FORT_1 = new Color(0.972549F, 0.63529414F, 0.3372549F, 0.55F);
   public static final Color COLOR_FORT_2 = new Color(0.9490196F, 0.52156866F, 0.14117648F, 0.55F);
   public static final Color COLOR_WATCH_TOWER = new Color(0.11764706F, 0.21176471F, 0.3372549F, 0.55F);
   public static final Color COLOR_BUILT = new Color(0.2F, 0.4F, 0.8F, 0.45F);
   public static final Color COLOR_FORTIFICATIONS_0 = new Color(0.9019608F, 0.9019608F, 0.9019608F, 0.45F);
   public static final Color COLOR_FORTIFICATIONS_1 = new Color(0.13725491F, 0.5882353F, 0.11764706F, 0.6F);
   public static final Color COLOR_FORTIFICATIONS_1_MOUNTAINS = new Color(0.105882354F, 0.43137255F, 0.09019608F, 0.6F);
   public static int PROVINCE_BORDER_THICKNESS = 1;
   public static int PROVINCE_BORDER_DASHED_THICKNESS = 1;
   public static final Color COLOR_PROVINCE_BORDER_CIV_REGION = new Color(0.9411765F, 0.7529412F, 0.15294118F, 1.0F);
   public static final float MAX_SCALE_DASHED = 4.0F;
   public static Color COLOR_PROVINCE_DASHED = new Color(0.04F, 0.04F, 0.04F, 0.64705884F);
   public static Color COLOR_PROVINCE_STRAIGHT = new Color(0.0F, 0.0F, 0.0F, 1.0F);
   public static Color COLOR_PROVINCE_SEABYSEA = new Color(0.94F, 0.94F, 0.95F, 0.07F);
   public static Color COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER = new Color(1.0F, 0.91764706F, 0.015686275F, 1.0F);
   public static float fMOVE_MENU_PERCENTAGE;
   public static long lMOVE_MENU_TIME;
   public static Menu backToMenu = Menu.eMAINMENU;
   public static Menu goToMenu = Menu.eMAINMENU;
   public static Menu goToMenu2 = Menu.eMAINMENU;
   public static String CREATE_SCENARIO_GAME_DATA_TAG = null;
   public static boolean CREATE_SCENARIO_IS_PART_OF_CAMPAIGN = false;
   public static List<Integer> lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS = new ArrayList<>();
   public static String CREATE_SCENARIO_NAME = "";
   public static String CREATE_SCENARIO_AUTHOR = "";
   public static String CREATE_SCENARIO_WIKI = "";
   public static int CREATE_SCENARIO_AGE = 0;
   public static int iCreateScenario_ActiveProvinceID;
   public static int iCreateScenario_AssignProvinces_Civ = -1;
   public static List<List<Scenario_GameData_Technology>> lCreateScenario_TechnologyBContinents;
   public static boolean RELOAD_SCENARIO = false;
   public static List<Undo_AssignProvinceCiv> lCreateScenario_UndoAssignProvincesCivID;
   public static String chosen_AlphabetCharachter = null;
   public static String sSearch = null;
   public static List<Integer> lCreateScenario_UndoWastelandProvinces;
   public static boolean bSetWasteland_AvailableProvinces = true;
   public static int iNumOfAvailableProvinces = 0;
   public static int iNumOfAvailableProvincesWidth = 0;
   public static int iNumOfWastelandProvinces = 0;
   public static int iNumOfWastelandProvincesWidth = 0;
   public static boolean MANAGE_DIPLOMACY_DRAW_HELP_LINE = true;
   public static int MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 1;
   public static int MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = 1;
   public static int MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 = 0;
   public static int MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
   public static int MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
   public static String sAtWar = null;
   public static final int DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_PACT = 40;
   public static final int DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_TRUCE = 50;
   public static final int DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_DEFENSIVE_PACT = 40;
   public static final int c = 100;
   public static final int DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_MILITARY_ACCESS = 40;
   public static MenuManager menuManager;
   public static UnionsManager unionsManager;
   public static Map map;
   public static LanguageManager langManager;
   public static SoundsManager soundsManager;
   public static EventsManager eventsManager;
   public static Game game;
   public static Game_Ages gameAges;
   public static PlagueManager plagueManager;
   public static Game_Action gameAction;
   public static Game_NewGame gameNewGame;
   public static Game_NextTurnUpdate game_NextTurnUpdate;
   public static ViewsManager viewsManager;
   public static HistoryManager historyManager;
   public static LinesManager linesManager;
   public static Toast toast;
   public static Start_The_Game_Data startTheGameData;
   public static Pallet_Manager palletManager;
   public static Ideologies_Manager ideologiesManager;
   public static AI oAI;
   public static Report_Data reportData = null;
   public static FlagManager flagManager = new FlagManager();
   public static RandomGame_Manager randomGameManager = null;
   public static HolyRomanEmpire_Manager holyRomanEmpire_Manager = null;
   public static UnionFlagsToGenerate_Manager unionFlagsToGenerate_Manager = new UnionFlagsToGenerate_Manager();
   public static TimelapseManager timelapseManager = new TimelapseManager();
   public static TutorialManager tutorialManager = new TutorialManager();
   public static PeaceTreaty_Data peaceTreatyData = new PeaceTreaty_Data();
   public static CreateVassal_Data createVassal_Data = null;
   public static TradeRequest_GameData tradeRequest = new TradeRequest_GameData();
   public static Ultimatum_GameData ultimatum = new Ultimatum_GameData();
   public static CasusBelli casusBelli;
   public static boolean brushTool = false;
   public static boolean selectMode = true;
   public static int slidePosX;
   public static int slidePosY;
   public static Color COLOR_CITY_NAME = new Color(0.9137255F, 0.9137255F, 0.9137255F, 0.85F);
   public static GlyphLayout glyphLayout = new GlyphLayout();
   public static BitmapFont fontMain = null;
   public static BitmapFont fontArmy = null;
   public static BitmapFont fontBorder = null;
   public static boolean loadedRobotoFont = false;
   public static int ARMY_HEIGHT = 1;
   public static int TEXT_HEIGHT = 1;
   public static int iProvinceNameWidth = -1;
   public static final Color COLOR_ARMY_BG = new Color(0.0F, 0.0F, 0.0F, 0.8F);
   public static final Color COLOR_ARMY_CAPITAL_BG = new Color(0.0F, 0.0F, 0.0F, 1.0F);
   public static final Color COLOR_ARMY_BG_ACTIVE = new Color(1.0F, 1.0F, 1.0F, 1.0F);
   public static final Color COLOR_ARMY_BG_SEA = new Color(0.05490196F, 0.1254902F, 0.23529412F, 1.0F);
   public static final Color COLOR_ARMY_BG_ALLIANCE = new Color(0.019607844F, 0.09803922F, 0.1764706F, 1.0F);
   public static final Color COLOR_ARMY_TEXT_ALLIANCE = new Color(0.98039216F, 0.99607843F, 0.99607843F, 1.0F);
   public static final Color COLOR_ARMY_BG_VASSAL = new Color(0.078431375F, 0.23529412F, 0.10980392F, 1.0F);
   public static final Color COLOR_ARMY_BG_MOVEUNITS = new Color(0.129F, 0.078F, 0.063F, 0.9F);
   public static final Color COLOR_ARMY_TEXT = new Color(0.88235295F, 0.88235295F, 0.27450982F, 1.0F);
   public static final Color COLOR_ARMY_TEXT_ACTIVE = new Color(0.12156863F, 0.12156863F, 0.12156863F, 1.0F);
   public static final Color COLOR_ARMY_TEXT_CAPITAL_ACTIVE = new Color(0.99215686F, 0.99607843F, 0.99607843F, 1.0F);
   public static final Color COLOR_ARMY_TEXT_SEA = new Color(0.8235294F, 0.8235294F, 0.8235294F, 1.0F);
   public static final Color COLOR_ARMY_TEXT_SEA_ACTIVE = new Color(0.5294118F, 0.54901963F, 0.5686275F, 1.0F);
   public static final float TEXT_SCALE_TOP_VIEWS = 0.6F;
   public static final Color COLOR_INGAME_GOLD = new Color(0.87058824F, 0.85882354F, 0.12941177F, 1.0F);
   public static final Color COLOR_INGAME_GOLD_HOVER = new Color(0.75686276F, 0.75686276F, 0.0F, 1.0F);
   public static final Color COLOR_INGAME_GOLD_ACTIVE = new Color(0.6901961F, 0.6901961F, 0.0F, 1.0F);
   public static final Color COLOR_INGAME_MOVEMENT = new Color(0.25882354F, 0.68235296F, 0.9019608F, 1.0F);
   public static final Color COLOR_INGAME_MOVEMENT_HOVER = new Color(0.2F, 0.6F, 0.8F, 1.0F);
   public static final Color COLOR_INGAME_MOVEMENT_ACTIVE = new Color(0.16862746F, 0.5411765F, 0.69803923F, 1.0F);
   public static final Color COLOR_INGAME_MOVEMENT_ZERO = new Color(0.7490196F, 0.18431373F, 0.14117648F, 1.0F);
   public static final Color COLOR_INGAME_MOVEMENT_ZERO_HOVER = new Color(0.6431373F, 0.10980392F, 0.08235294F, 1.0F);
   public static final Color COLOR_INGAME_MOVEMENT_ZERO_ACTIVE = new Color(0.56078434F, 0.06666667F, 0.050980393F, 1.0F);
   public static final Color COLOR_INGAME_DIPLOMACY_POINTS = new Color(1.0F, 1.0F, 1.0F, 1.0F);
   public static final Color COLOR_INGAME_DIPLOMACY_POINTS_HOVER = new Color(0.7882353F, 0.7882353F, 0.8F, 1.0F);
   public static final Color COLOR_INGAME_DIPLOMACY_POINTS_ACTIVE = new Color(0.7529412F, 0.7529412F, 0.7529412F, 1.0F);
   public static final Color COLOR_BG_GAME_MENU_SHADOW = new Color(0.0F, 0.0F, 0.0F, 0.65F);
   public static final int REBELS_FLAGS_SIZE = 6;
   public static String keyboardMessage = "";
   public static CFG.Keyboard_Action keyboardSave;
   public static CFG.Keyboard_Action keyboardDelete;
   public static CFG.Keyboard_Action_Write keyboardWrite;
   public static Menu_FlagPixel_Color FlagPixelColor;
   public static int CIV_FLAG_WIDTH = 27;
   public static int CIV_FLAG_HEIGHT = 18;
   public static final int CIV_FLAG_WIDTH_FINAL = 27;
   public static final int CIV_FLAG_HEIGHT_FINAL = 18;
   public static boolean FLIP_Y_CIV_FLAG = false;
   public static byte FLIP_Y_CIV_FLAG_COUNTER = 0;
   public static final byte FLIP_Y_CIV_FLAG_COUNTER_TRIC = 3;
   public static int flagR;
   public static int flagG;
   public static int flagB;
   public static CFG.FlagEditorMode flagEditorMode = CFG.FlagEditorMode.PENCIL;
   public static final Color COLOR_INFO_BOX_GRADIENT = new Color(0.126F, 0.149F, 0.227F, 1.0F);
   public static Dialog dialogType = Dialog.EXIT_GAME;
   public static int iSelectCivilizationPlayerID = 0;
   public static Alliances_Names_GameData editorAlliancesNames_GameData = null;
   public static int EDIT_ALLIANCE_NAMES_BUNDLE_ID = 0;
   public static String CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG = null;
   public static List<String> lRandomAlliancesNamesPackagesTags;
   public static Civilization_GameData3 editorCivilization_GameData;
   public static Achievement_Data achievement_Data = null;
   public static ServiceRibbon_Manager serviceRibbon_Manager;
   public static ServiceRibbon_GameData editorServiceRibbon_GameData;
   public static List<Color> editorServiceRibbon_Colors;
   public static int SERVICE_RIBBON_WIDTH = 58;
   public static int SERVICE_RIBBON_HEIGHT = 16;
   public static City editorCity;
   public static ByteArrayInputStream b;
   public static ObjectInputStream o;
   public static boolean regionChoosing = false;
   public static int lastAirbaseProvince = 0;
   public static final Color COLOR_AIR_GREEN = new Color(0.0F, 0.75F, 0.0F, 0.7F);
   public static final Color COLOR_AIR_YELLOW = new Color(0.95F, 0.9F, 0.0F, 0.7F);
   public static final Color COLOR_AIR_WHITE = new Color(0.75F, 0.75F, 0.75F, 0.7F);

   CFG() {
   }

   public static String getLukaszJakowski() {
      return loadedRobotoFont ? "Bloody Team" : "Bloody Team";
   }

   public static String getLukaszJakowskiGames() {
      return loadedRobotoFont ? "Bloody Team" : "Bloody Team";
   }

   public static final boolean isAndroid() {
      return Gdx.app.getType() == Application.ApplicationType.Android;
   }

   public static final boolean isDesktop() {
      return Gdx.app.getType() == Application.ApplicationType.Desktop;
   }

   public static final boolean readLocalFiles() {
      switch (Gdx.app.getType()) {
         case Android:
            return true;
         case Desktop:
            return false;
         default:
            return false;
      }
   }

   public static final void wikiInormationsLink(String sCivTag) {
      try {
         try {
            FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + sCivTag);
            String sLine = readFile.readString();
            Gdx.net.openURI("https://en.wikipedia.org/wiki/" + sLine);
         } catch (GdxRuntimeException var4) {
            FileHandle readFilex = Gdx.files.internal("game/civilizations_informations/" + ideologiesManager.getRealTag(sCivTag));
            String sLinex = readFilex.readString();
            Gdx.net.openURI("https://en.wikipedia.org/wiki/" + sLinex);
         }
      } catch (GdxRuntimeException var5) {
         toast.setInView(langManager.get("NoData"));
      }
   }

   public static final String getWikiInormationsLink(String sCivTag) {
      try {
         FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + sCivTag);
         String sLine = readFile.readString();
         return "https://en.wikipedia.org/wiki/" + sLine;
      } catch (GdxRuntimeException var5) {
         try {
            FileHandle readFilex = Gdx.files.internal("game/civilizations_informations/" + ideologiesManager.getRealTag(sCivTag));
            String sLinex = readFilex.readString();
            return "https://en.wikipedia.org/wiki/" + sLinex;
         } catch (GdxRuntimeException var4) {
            return "/";
         }
      }
   }

   public static final String getWikiInormationsLink_Clear(String sCivTag) {
      try {
         FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + sCivTag);
         return readFile.readString();
      } catch (GdxRuntimeException var5) {
         try {
            FileHandle readFilex = Gdx.files.internal("game/civilizations_informations/" + ideologiesManager.getRealTag(sCivTag));
            return readFilex.readString();
         } catch (GdxRuntimeException var4) {
            return langManager.get("NoData");
         }
      }
   }

   public static final List<String> getFileNames(String nPath) {
      Gdx.app.log("AoC2", "getFileNames: " + nPath);
      ArrayList<String> filesNames = new ArrayList<>();
      FileHandle dirHandle = Gdx.app.getType() == Application.ApplicationType.Android ? Gdx.files.internal(nPath) : Gdx.files.internal(nPath);

      for (FileHandle entry : dirHandle.list()) {
         filesNames.add(entry.name());
      }

      return filesNames;
   }

   public static final List<String> getFileNames2(String nPath) {
      Gdx.app.log("AoC2", "getFileNames: " + nPath);
      ArrayList<String> filesNames = new ArrayList<>();
      if (Gdx.app.getType() == Application.ApplicationType.Android) {
         FileHandle dirHandle = Gdx.files.internal(nPath);

         for (FileHandle entry : dirHandle.list()) {
            filesNames.add(entry.name());
         }

         ArrayList<String> filesNames2 = new ArrayList<>();
         FileHandle dirHandle2 = Gdx.files.local(nPath);

         for (FileHandle entry : dirHandle2.list()) {
            filesNames2.add(entry.name());
         }

         return filesNames2.size() > filesNames.size() ? filesNames2 : filesNames;
      } else {
         FileHandle dirHandle = Gdx.files.internal(nPath);

         for (FileHandle entry : dirHandle.list()) {
            filesNames.add(entry.name());
         }

         return filesNames;
      }
   }

   public static final int getFileNames_Length(String nPath) {
      FileHandle dirHandle = Gdx.app.getType() == Application.ApplicationType.Android ? Gdx.files.internal(nPath) : Gdx.files.internal(nPath);
      Gdx.app.log("AoC", "dirHandle.list()" + dirHandle.list().length);
      return dirHandle.list().length;
   }

   public static final String getRescouresPath() {
      if (XXXXHDPI) {
         return "XXXXH/";
      } else if (XXXHDPI) {
         return "XXXH/";
      } else if (XXHDPI) {
         return "XXH/";
      } else {
         return XHDPI ? "XH/" : "H/";
      }
   }

   public static final int getUIScale() {
      if (XXXXHDPI) {
         return 4;
      } else if (XXXHDPI) {
         return 3;
      } else if (XXHDPI) {
         return 2;
      } else {
         return XHDPI ? 1 : 0;
      }
   }

   public static Point_XY getRandomPointToCenterTheMap() {
      Random oR = new Random();
      return new Point_XY(
         oR.nextInt(map.getMapBG().getWidth() / map.getMapBG().getMapScale()), oR.nextInt(map.getMapBG().getHeight() / map.getMapBG().getMapScale())
      );
   }

   public static Color getRandomColor() {
      return new Color(oR.nextInt(256) / 255.0F, oR.nextInt(256) / 255.0F, oR.nextInt(256) / 255.0F, 1.0F);
   }

   public static Color_GameData getRandomColorGameData() {
      return new Color_GameData(oR.nextInt(256) / 255.0F, oR.nextInt(256) / 255.0F, oR.nextInt(256) / 255.0F);
   }

   public static final void updateRender(boolean enable) {
      if (enable) {
         renderUpdate_3 = new CFG.RenderUpdate() {
            @Override
            public void update(boolean nRENDER) {
               CFG.RENDER3 = nRENDER;
               CFG.RENDER2 = nRENDER;
               CFG.RENDER = CFG.RENDER3;
            }
         };
         setRender_3(true);
      } else {
         renderUpdate_3 = new CFG.RenderUpdate() {
            @Override
            public void update(boolean nRENDER) {
               CFG.RENDER3 = false;
               CFG.RENDER2 = false;
               CFG.RENDER = false;
            }
         };
      }
   }

   public static final void setRender_3(boolean nRENDER) {
      renderUpdate_3.update(nRENDER);
   }

   public static boolean getMetProvince(int nProvinceID) {
      try {
         return game.getPlayer(PLAYER_TURNID).getMetProvince(nProvinceID);
      } catch (NullPointerException var2) {
         return true;
      }
   }

   public static boolean getMetCiv(int nCivID) {
      try {
         return game.getPlayer(PLAYER_TURNID).getMetCivilization(nCivID);
      } catch (NullPointerException var2) {
         return true;
      }
   }

   public static boolean getMetCiv_AllPlayers(int nCivID) {
      for (int i = 0; i < game.getPlayersSize(); i++) {
         if (game.getCiv(game.getPlayer(i).getCivID()).getNumOfProvinces() > 0 && game.getPlayer(i).getMetCivilization(nCivID)) {
            return true;
         }
      }

      return false;
   }

   public static long getPROVINCE_BORDER_ANIMATION_TIME(String nKey) {
      try {
         return PROVINCE_BORDER_ANIMATION_TIME.get(nKey);
      } catch (NullPointerException var2) {
         return 0L;
      }
   }

   public static final void saveSettings_ActiveMap() {
      OutputStream os = null;

      try {
         FileHandle file = Gdx.files.local("settings_map");
         SaveActiveMap_GameData tempLA = new SaveActiveMap_GameData();
         tempLA.iActiveMapID = map.getActiveMapID();
         tempLA.iActiveMapScale = map.getMapScale(map.getActiveMapID());
         file.writeBytes(serialize(tempLA), false);
      } catch (IOException var11) {
         if (LOGS) {
            exceptionStack(var11);
         }
      } finally {
         if (os != null) {
            try {
               os.close();
            } catch (Exception var10) {
               if (LOGS) {
                  exceptionStack(var10);
               }
            }
         }
      }
   }

   public static final void saveSettings_LoadingStatus() {
      OutputStream os = null;

      try {
         FileHandle file = Gdx.files.local("status");
         SaveActiveMap_Status_GameData tempLStatus = new SaveActiveMap_Status_GameData();
         file.writeBytes(serialize(tempLStatus), false);
      } catch (IOException var11) {
         if (LOGS) {
            exceptionStack(var11);
         }
      } finally {
         if (os != null) {
            try {
               os.close();
            } catch (Exception var10) {
               if (LOGS) {
                  exceptionStack(var10);
               }
            }
         }
      }
   }

   public static final void saveSettings() {
      OutputStream os = null;

      try {
         FileHandle file = Gdx.files.local("settings");
         file.writeBytes(serialize(settingsManager), false);
      } catch (IOException var10) {
         if (LOGS) {
            exceptionStack(var10);
         }
      } finally {
         if (os != null) {
            try {
               os.close();
            } catch (Exception var9) {
               if (LOGS) {
                  exceptionStack(var9);
               }
            }
         }
      }
   }

   public static final void loadSettings() {
      try {
         FileHandle fileCiv = Gdx.files.local("settings");
         settingsManager = (SettingsManager)deserialize(fileCiv.readBytes());
      } catch (GdxRuntimeException var5) {
         try {
            FileHandle fileCivx = Gdx.files.internal("settings");
            settingsManager = (SettingsManager)deserialize(fileCivx.readBytes());
         } catch (GdxRuntimeException var2) {
         } catch (IOException var3) {
         } catch (ClassNotFoundException var4) {
         }
      } catch (IOException var6) {
         if (LOGS) {
            exceptionStack(var6);
         }
      } catch (ClassNotFoundException var7) {
         if (LOGS) {
            exceptionStack(var7);
         }
      }
   }

   public static Color getColor_CivInfo_Text(boolean isActive, boolean isHovered) {
      return isActive ? COLOR_TEXT_CIV_INFO_ACTIVE : (isHovered ? COLOR_TEXT_CIV_INFO_HOVER : COLOR_TEXT_CIV_INFO);
   }

   public static Color getColor_CivInfo_InGame_Text(boolean isActive, boolean isHovered) {
      return isActive ? COLOR_TEXT_CIV_INFO_ACTIVE : (isHovered ? COLOR_TEXT_CIV_INFO_HOVER : COLOR_TEXT_MODIFIER_NEUTRAL);
   }

   public static final String getDifficultyName(int i) {
      switch (i) {
         case 0:
            return langManager.get("Beginner");
         case 1:
            return langManager.get("Normal");
         case 2:
            return langManager.get("Hard");
         case 3:
         default:
            return langManager.get("Extreme");
         case 4:
            return langManager.get("Legendary");
      }
   }

   public static final String getFogOfWarName(int i) {
      switch (i) {
         case 0:
            return langManager.get("Off");
         case 2:
            return langManager.get("Discovery");
         default:
            return langManager.get("Classic");
      }
   }

   public static final int getCostOfRecruitArmyMoney(int nProvinceID) {
      int n2 = game.getProvince(nProvinceID).getCivID();
      n2 = game.getCiv(n2).getCivBuildArmyCost();
      return game.getProvince(nProvinceID).getLevelOfArmoury() <= 0 ? n2 : n2 * 7 / 10;
   }

   public static final int getCostOfRecruitArmyMoneyAutoplan(int n) {
      int n2 = game.getProvince(n).getCivID();
      n2 = game.getCiv(n2).getCivBuildArmyCost();
      return game.getProvince(n).getLevelOfArmoury() <= 0 ? n2 : n2 * 10000 / 10000;
   }

   public static final int getCostOfRecruitArmyMoney_Instantly(int nProvinceID) {
      int n2 = game.getProvince(nProvinceID).getCivID();
      n2 = game.getCiv(n2).getCivBuildArmyCost();
      return game.getProvince(nProvinceID).getLevelOfArmoury() <= 0 ? n2 * 2 : n2 * 20 / 25;
   }

   public static final void drawVersion_LEFT_BOT(SpriteBatch paramSpriteBatch, int paramInt) {
      fontMain.getData().setScale(1.0F);
      drawText(
         paramSpriteBatch,
         langManager.get("VkLink") + "BloodyEurope2",
         PADDING + paramInt,
         GAME_HEIGHT - PADDING - (int)(TEXT_HEIGHT * 1.0F),
         new Color(1.0F, 1.0F, 1.0F, 0.7F)
      );
      ImageManager.getImage(Images.diplo_war).draw(paramSpriteBatch, PADDING + paramInt, -1, (int)(TEXT_HEIGHT * 1.0F), (int)(TEXT_HEIGHT * 1.0F));
      drawText(
         paramSpriteBatch,
         "Bloody",
         PADDING + paramInt + (int)(TEXT_HEIGHT * 1.0F) + PADDING - 1,
         PADDING - 35 + (int)(TEXT_HEIGHT * 1.0F) * 3 + PADDING * 2,
         Color.RED
      );
      drawText(
         paramSpriteBatch,
         "Engine",
         (PADDING + paramInt + (int)(TEXT_HEIGHT * 1.0F) + PADDING - 1) * 4,
         PADDING - 35 + (int)(TEXT_HEIGHT * 1.0F) * 3 + PADDING * 2,
         Color.WHITE
      );
      drawText(
         paramSpriteBatch,
         langManager.get("Version") + "1.3.1",
         PADDING + paramInt,
         PADDING - 35 + (int)(TEXT_HEIGHT * 1.0F) * 4 + PADDING * 3,
         new Color(1.0F, 1.0F, 1.0F, 0.7F)
      );
      drawText(
         paramSpriteBatch,
         langManager.get("") + "",
         PADDING + paramInt,
         PADDING - 35 + (int)(TEXT_HEIGHT * 1.0F) * 5 + PADDING * 4,
         new Color(1.0F, 1.0F, 1.0F, 0.7F)
      );
      fontMain.getData().setScale(1.0F);
   }

   public static final void drawJakowskiGames_RIGHT_BOT(SpriteBatch paramSpriteBatch, int paramInt) {
      fontMain.getData().setScale(1.0F);
      String str = getLukaszJakowskiGames();
      int i = GAME_WIDTH;
      int j = PADDING;
      drawText(
         paramSpriteBatch,
         str,
         i - j - (int)(iJakowskiGamesWidth * 1.0F) + paramInt,
         GAME_HEIGHT - (int)(TEXT_HEIGHT * 1.0F) - j,
         new Color(1.0F, 1.0F, 1.0F, 0.5F)
      );
      fontMain.getData().setScale(1.0F);
   }

   public static final void drawJakowskiGames_RIGHT_BOT(SpriteBatch paramSpriteBatch, int paramInt, float paramFloat) {
      paramFloat = (1.0F - Math.min(paramFloat * 2.0F, 1.0F)) * 0.1F + 1.0F;
      fontMain.getData().setScale(paramFloat);
      String str = getLukaszJakowskiGames();
      int i = GAME_WIDTH;
      int j = PADDING;
      drawText(
         paramSpriteBatch,
         str,
         i - j - (int)(iJakowskiGamesWidth * paramFloat) + paramInt,
         GAME_HEIGHT - (int)(TEXT_HEIGHT * paramFloat) - j,
         new Color(1.0F, 1.0F, 1.0F, 0.5F)
      );
      fontMain.getData().setScale(1.0F);
   }

   public static final void drawLoading1(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, float nProgress) {
      if (System.currentTimeMillis() - 2500L > loadingTime) {
         try {
            sLoadingText = langManager.getLoading("L" + oR.nextInt(langManager.iLoading_NumOfTexts)) + "..";
            loadingTime = System.currentTimeMillis();
            glyphLayout.setText(fontMain, sLoadingText);
            iLoadingTextWidth = (int)(glyphLayout.width * LOADING_TEXT_FONT_SCALE);
         } catch (IllegalArgumentException var7) {
            if (LOGS) {
               exceptionStack(var7);
            }
         }
      }

      if (PRESENTS_TIME == 0L) {
         PRESENTS_TIME = System.currentTimeMillis();
      }

      if (System.currentTimeMillis() < PRESENTS_TIME + 3500L) {
         ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast))
            .draw(oSB, 0, -ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
         oSB.setColor(1.0F, 1.0F, 1.0F, Images.backgroundAlpha);
         ImageManager.getImage(Images.backgrounds.get(Images.background))
            .draw(oSB, 0, -ImageManager.getImage(Images.backgrounds.get(Images.background)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
         oSB.setColor(Color.WHITE);
         fontMain.getData().setScale(PRESENTS_GAMES_SCALE);
         drawText(
            oSB,
            getLukaszJakowskiGames(),
            GAME_WIDTH / 2 - (int)(iJakowskiGamesWidth * PRESENTS_GAMES_SCALE / 2.0F),
            GAME_HEIGHT / 2 - (int)(TEXT_HEIGHT * PRESENTS_GAMES_SCALE) - PADDING,
            COLOR_TEXT_NUM_OF_PROVINCES
         );
         fontMain.getData().setScale(PRESENTS_GAMES_SCALE2);
         drawText(
            oSB,
            "presents",
            GAME_WIDTH / 2 - (int)(iJakowskiGames_PresentsWidth * PRESENTS_GAMES_SCALE2 / 2.0F),
            GAME_HEIGHT / 2 + PADDING,
            COLOR_TEXT_NUM_OF_PROVINCES
         );
         fontMain.getData().setScale(1.0F);
         oSB.setColor(Color.WHITE);
      }

      if (System.currentTimeMillis() >= PRESENTS_TIME + 437L) {
         ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast))
            .draw(oSB, 0, -ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, Images.backgroundAlpha));
         ImageManager.getImage(Images.backgrounds.get(Images.background))
            .draw(oSB, 0, -ImageManager.getImage(Images.backgrounds.get(Images.background)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
         oSB.setColor(new Color(0.019607844F, 0.02745098F, 0.03529412F, 0.75F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), (int)(nWidth * nProgress), nHeight);
         oSB.setColor(new Color(0.043137256F, 0.05882353F, 0.07450981F, 0.65F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nPosX + (int)(nWidth * nProgress),
               nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               nWidth - (int)(nWidth * nProgress),
               nHeight
            );
         oSB.setColor(COLOR_LOADING_SPLIT);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(oSB, nPosX + (int)(nWidth * nProgress), nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), 1, nHeight);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.loading_rect_edge)
            .draw2(
               oSB,
               nPosX,
               nPosY - ImageManager.getImage(Images.loading_rect_edge).getHeight(),
               nWidth - ImageManager.getImage(Images.loading_rect_edge).getWidth(),
               nHeight - ImageManager.getImage(Images.loading_rect_edge).getHeight()
            );
         ImageManager.getImage(Images.loading_rect_edge)
            .draw2(
               oSB,
               nPosX + nWidth - ImageManager.getImage(Images.loading_rect_edge).getWidth(),
               nPosY - ImageManager.getImage(Images.loading_rect_edge).getHeight(),
               ImageManager.getImage(Images.loading_rect_edge).getWidth(),
               nHeight - ImageManager.getImage(Images.loading_rect_edge).getHeight(),
               true
            );
         ImageManager.getImage(Images.loading_rect_edge)
            .draw2(
               oSB,
               nPosX,
               nPosY + nHeight - ImageManager.getImage(Images.loading_rect_edge).getHeight() * 2,
               nWidth - ImageManager.getImage(Images.loading_rect_edge).getWidth(),
               ImageManager.getImage(Images.loading_rect_edge).getHeight(),
               false,
               true
            );
         ImageManager.getImage(Images.loading_rect_edge)
            .draw(
               oSB,
               nPosX + nWidth - ImageManager.getImage(Images.loading_rect_edge).getWidth(),
               nPosY + nHeight - ImageManager.getImage(Images.loading_rect_edge).getHeight(),
               true,
               true
            );
         oSB.setColor(new Color(COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.45F));
         ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
         ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
         fontMain.getData().setScale(LOADING_TEXT_FONT_SCALE);
         drawTextWithShadow(
            oSB,
            sLoadingText,
            nPosX + nWidth / 2 - iLoadingTextWidth / 2,
            nPosY + (nHeight - (int)(TEXT_HEIGHT * LOADING_TEXT_FONT_SCALE)) / 2,
            new Color(0.7019608F, 0.5568628F, 0.23921569F, 1.0F)
         );
         fontMain.getData().setScale(0.8F);
         drawTextWithShadow(
            oSB,
            sLoading + " " + (int)(nProgress * 100.0F) + "%",
            nPosX,
            nPosY - PADDING - (int)(TEXT_HEIGHT * 0.8F),
            new Color(0.7019608F, 0.5568628F, 0.23921569F, 1.0F)
         );
         fontMain.getData().setScale(1.0F);
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.35F));
         ImageManager.getImage(Images.gameLogo)
            .draw2(
               oSB,
               nPosX + nWidth - ImageManager.getImage(Images.gameLogo).getWidth(),
               nPosY - PADDING - ImageManager.getImage(Images.gameLogo).getHeight() * 2,
               ImageManager.getImage(Images.gameLogo).getWidth(),
               ImageManager.getImage(Images.gameLogo).getHeight()
            );
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.gameLogo)
            .draw2(
               oSB,
               nPosX + nWidth - ImageManager.getImage(Images.gameLogo).getWidth(),
               nPosY - PADDING - ImageManager.getImage(Images.gameLogo).getHeight() * 2,
               (int)(ImageManager.getImage(Images.gameLogo).getWidth() * nProgress),
               ImageManager.getImage(Images.gameLogo).getHeight()
            );
      }
   }

   protected static final void drawLoading(SpriteBatch paramSpriteBatch, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat) {
      if (System.currentTimeMillis() - 2500L > loadingTime) {
         try {
            StringBuilder stringBuilder1 = new StringBuilder();
            LanguageManager languageManager = langManager;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("L");
            stringBuilder2.append(oR.nextInt(langManager.iLoading_NumOfTexts));
            stringBuilder1.append(languageManager.getLoading(stringBuilder2.toString()));
            stringBuilder1.append("..");
            sLoadingText = stringBuilder1.toString();
            loadingTime = System.currentTimeMillis();
            glyphLayout.setText(fontMain, sLoadingText);
            iLoadingTextWidth = (int)(glyphLayout.width * LOADING_TEXT_FONT_SCALE);
         } catch (IllegalArgumentException var10) {
            if (LOGS) {
               exceptionStack(var10);
            }
         }
      }

      if (PRESENTS_TIME == 0L) {
         PRESENTS_TIME = System.currentTimeMillis();
      }

      if (System.currentTimeMillis() < PRESENTS_TIME + 3500L) {
         ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast))
            .draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
         paramSpriteBatch.setColor(1.0F, 1.0F, 1.0F, Images.backgroundAlpha);
         ImageManager.getImage(Images.backgrounds.get(Images.background))
            .draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.backgrounds.get(Images.background)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
         paramSpriteBatch.setColor(Color.WHITE);
         paramSpriteBatch.setColor(Color.BLACK);
         ImageManager.getImage(Images.line_32_vertical)
            .draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.line_32_vertical).getHeight(), GAME_WIDTH, GAME_HEIGHT);
         paramSpriteBatch.setColor(Color.WHITE);
         fontMain.getData().setScale(PRESENTS_GAMES_SCALE);
         String str = getLukaszJakowskiGames();
         int k = GAME_WIDTH / 2;
         float f1 = iJakowskiGamesWidth;
         float f2 = PRESENTS_GAMES_SCALE;
         drawText(paramSpriteBatch, str, k - (int)(f1 * f2 / 2.0F), GAME_HEIGHT / 2 - (int)(TEXT_HEIGHT * f2) - PADDING, COLOR_INGAME_DIPLOMACY_POINTS);
         fontMain.getData().setScale(PRESENTS_GAMES_SCALE2);
         drawText(
            paramSpriteBatch,
            "",
            GAME_WIDTH / 2 - (int)(iJakowskiGames_PresentsWidth * PRESENTS_GAMES_SCALE2 / 2.0F),
            GAME_HEIGHT / 2 + PADDING,
            COLOR_TEXT_POPULATION_ACTIVE
         );
         fontMain.getData().setScale(1.0F);
         paramSpriteBatch.setColor(Color.WHITE);
      }

      if (System.currentTimeMillis() >= PRESENTS_TIME + 437L) {
         ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast))
            .draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
         paramSpriteBatch.setColor(new Color(1.0F, 1.0F, 1.0F, Images.backgroundAlpha));
         ImageManager.getImage(Images.backgrounds.get(Images.background))
            .draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.backgrounds.get(Images.background)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
         paramSpriteBatch.setColor(new Color(0.50980395F, 0.13725491F, 0.078431375F, 0.75F));
         Image image = ImageManager.getImage(Images.pix255_255_255);
         int j = ImageManager.getImage(Images.pix255_255_255).getHeight();
         int i = (int)(paramInt3 * paramFloat);
         image.draw(paramSpriteBatch, paramInt1, paramInt2 - j, i, paramInt4);
         paramSpriteBatch.setColor(new Color(0.043137256F, 0.05882353F, 0.07450981F, 0.65F));
         image = ImageManager.getImage(Images.pix255_255_255);
         j = paramInt1 + i;
         image.draw(paramSpriteBatch, j, paramInt2 - ImageManager.getImage(Images.pix255_255_255).getHeight(), paramInt3 - i, paramInt4);
         paramSpriteBatch.setColor(COLOR_LOADING_SPLIT);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(paramSpriteBatch, j, paramInt2 - ImageManager.getImage(Images.pix255_255_255).getHeight(), 1, paramInt4);
         paramSpriteBatch.setColor(Color.WHITE);
         ImageManager.getImage(Images.loading_rect_edge)
            .draw2(
               paramSpriteBatch,
               paramInt1,
               paramInt2 - ImageManager.getImage(Images.loading_rect_edge).getHeight(),
               paramInt3 - ImageManager.getImage(Images.loading_rect_edge).getWidth(),
               paramInt4 - ImageManager.getImage(Images.loading_rect_edge).getHeight()
            );
         image = ImageManager.getImage(Images.loading_rect_edge);
         i = paramInt1 + paramInt3;
         image.draw2(
            paramSpriteBatch,
            i - ImageManager.getImage(Images.loading_rect_edge).getWidth(),
            paramInt2 - ImageManager.getImage(Images.loading_rect_edge).getHeight(),
            ImageManager.getImage(Images.loading_rect_edge).getWidth(),
            paramInt4 - ImageManager.getImage(Images.loading_rect_edge).getHeight(),
            true
         );
         image = ImageManager.getImage(Images.loading_rect_edge);
         j = paramInt2 + paramInt4;
         image.draw2(
            paramSpriteBatch,
            paramInt1,
            j - ImageManager.getImage(Images.loading_rect_edge).getHeight() * 2,
            paramInt3 - ImageManager.getImage(Images.loading_rect_edge).getWidth(),
            ImageManager.getImage(Images.loading_rect_edge).getHeight(),
            false,
            true
         );
         ImageManager.getImage(Images.loading_rect_edge)
            .draw(
               paramSpriteBatch,
               i - ImageManager.getImage(Images.loading_rect_edge).getWidth(),
               j - ImageManager.getImage(Images.loading_rect_edge).getHeight(),
               true,
               true
            );
         paramSpriteBatch.setColor(new Color(COLOR_INGAME_GOLD.r, COLOR_INGAME_GOLD.g, COLOR_INGAME_GOLD.b, 0.45F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(paramSpriteBatch, paramInt1, paramInt2 - ImageManager.getImage(Images.line_32_off1).getHeight(), paramInt3, 1);
         ImageManager.getImage(Images.line_32_off1)
            .draw(paramSpriteBatch, paramInt1, j - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), paramInt3, 1);
         fontMain.getData().setScale(LOADING_TEXT_FONT_SCALE);
         drawTextWithShadow(
            paramSpriteBatch,
            sLoadingText,
            paramInt3 / 2 + paramInt1 - iLoadingTextWidth / 2,
            paramInt2 + (paramInt4 - (int)(TEXT_HEIGHT * LOADING_TEXT_FONT_SCALE)) / 2,
            new Color(0.75686276F, 0.75686276F, 0.75686276F, 1.0F)
         );
         fontMain.getData().setScale(0.8F);
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append(sLoading);
         stringBuilder.append(" ");
         stringBuilder.append((int)(100.0F * paramFloat));
         stringBuilder.append("%");
         drawTextWithShadow(
            paramSpriteBatch,
            stringBuilder.toString(),
            paramInt1,
            paramInt2 - PADDING - (int)(TEXT_HEIGHT * 0.8F),
            new Color(0.75686276F, 0.75686276F, 0.75686276F, 1.0F)
         );
         fontMain.getData().setScale(1.0F);
         paramSpriteBatch.setColor(new Color(1.0F, 1.0F, 1.0F, 0.35F));
         ImageManager.getImage(Images.gameLogo)
            .draw2(
               paramSpriteBatch,
               i - ImageManager.getImage(Images.gameLogo).getWidth(),
               paramInt2 - PADDING - ImageManager.getImage(Images.gameLogo).getHeight() * 2,
               ImageManager.getImage(Images.gameLogo).getWidth(),
               ImageManager.getImage(Images.gameLogo).getHeight()
            );
         paramSpriteBatch.setColor(Color.WHITE);
         ImageManager.getImage(Images.gameLogo)
            .draw2(
               paramSpriteBatch,
               i - ImageManager.getImage(Images.gameLogo).getWidth(),
               paramInt2 - PADDING - ImageManager.getImage(Images.gameLogo).getHeight() * 2,
               (int)(ImageManager.getImage(Images.gameLogo).getWidth() * paramFloat),
               ImageManager.getImage(Images.gameLogo).getHeight()
            );
      }
   }

   public static final float getLoadingPadding() {
      return isAndroid() && !LANDSCAPE ? 0.1F : 0.092F;
   }

   public static final void drawLogo_Square(SpriteBatch oSB, int nPosX, int nPosY, int tempSize) {
      oSB.setColor(Color.BLACK);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), tempSize, tempSize);
      oSB.setColor(Color.WHITE);
      map.getMapBG().drawMap_LogoSquare(oSB, nPosX, nPosY, tempSize, tempSize);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 1.0F));
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), tempSize, (int)(tempSize * 0.15F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            nPosX,
            nPosY - ImageManager.getImage(Images.gradient).getHeight() + tempSize - (int)(tempSize * 0.15F),
            tempSize,
            (int)(tempSize * 0.15F),
            false,
            true
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(tempSize * 0.15F), tempSize, false, false);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX + tempSize - (int)(tempSize * 0.15F),
            nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)(tempSize * 0.15F),
            tempSize,
            true,
            false
         );
      oSB.setColor(COLOR_FLAG_FRAME);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 1, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, tempSize - 2, 1);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX + 1, nPosY + tempSize - ImageManager.getImage(Images.pix255_255_255).getHeight() - 2, tempSize - 2, 1);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 1, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, 1, tempSize - 2);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX + tempSize - 2, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, 1, tempSize - 2);
      oSB.setColor(Color.WHITE);
      if (ImageManager.getImage(Images.gameLogo).getWidth() > tempSize * 0.5F) {
         ImageManager.getImage(Images.gameLogo)
            .draw(
               oSB,
               nPosX + tempSize - PADDING - (int)(ImageManager.getImage(Images.gameLogo).getWidth() * 0.5F),
               nPosY
                  + tempSize
                  - PADDING
                  - ImageManager.getImage(Images.gameLogo).getHeight()
                  - (int)(ImageManager.getImage(Images.gameLogo).getHeight() * 0.5F),
               (int)(ImageManager.getImage(Images.gameLogo).getWidth() * 0.5F),
               (int)(ImageManager.getImage(Images.gameLogo).getHeight() * 0.5F)
            );
      } else {
         ImageManager.getImage(Images.gameLogo)
            .draw(
               oSB,
               nPosX + tempSize - PADDING - ImageManager.getImage(Images.gameLogo).getWidth(),
               nPosY + tempSize - PADDING - ImageManager.getImage(Images.gameLogo).getHeight()
            );
      }
   }

   public static final int getActiveCivInfo_BasedOnActiveProvinceID(int nProvinceID) {
      if (nProvinceID >= 0) {
         if (FOG_OF_WAR == 2) {
            return game.getProvince(nProvinceID).getCivID() > 0 && getMetProvince(nProvinceID)
               ? game.getProvince(nProvinceID).getCivID()
               : game.getPlayer(PLAYER_TURNID).getCivID();
         } else {
            return game.getProvince(nProvinceID).getCivID() > 0 ? game.getProvince(nProvinceID).getCivID() : game.getPlayer(PLAYER_TURNID).getCivID();
         }
      } else {
         return game.getPlayer(PLAYER_TURNID).getCivID();
      }
   }

   public static final int getActiveCivInfo() {
      return activeCivInfo;
   }

   public static final void setActiveCivInfoFlag(Image nFlag) {
      try {
         disposeActiveCivFlag();
         activeCivFlag = nFlag;
      } catch (NullPointerException var2) {
         exceptionStack(var2);
      }
   }

   public static final void setActiveCivInfo(int nActiveCivInfo) {
      try {
         disposeActiveCivFlag();
         activeCivInfo = nActiveCivInfo;
         if (game.getCiv(activeCivInfo).getCivTag().indexOf(59) > 0) {
            unionFlagsToGenerate_Manager.lFlags.add(new UnionFlagsToGenerate());
            int tGenerateID = unionFlagsToGenerate_Manager.lFlags.size() - 1;
            String[] tempD = game.getCiv(activeCivInfo).getCivTag().split(";");

            for (int i = 0; i < tempD.length; i++) {
               unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).lTags.add(tempD[i]);
            }

            unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).typeOfAction = UnionFlagsToGenerate_TypesOfAction.ACTIVE_CIV_INFO;
            return;
         }

         try {
            try {
               activeCivFlag = new Image(
                  new Texture(Gdx.files.internal("game/flagsH/" + game.getCiv(activeCivInfo).getCivTag() + ".png")), Texture.TextureFilter.Linear
               );
            } catch (GdxRuntimeException var10) {
               if (ideologiesManager.getIdeology(game.getCiv(nActiveCivInfo).getIdeologyID()).REVOLUTIONARY) {
                  activeCivFlag = new Image(
                     new Texture(
                        Gdx.files
                           .internal(
                              "game/flagsH/rb" + (game.getCiv(nActiveCivInfo).getCivID() + game.getCiv(nActiveCivInfo).getCivTag().charAt(0)) % 6 + ".png"
                           )
                     ),
                     Texture.TextureFilter.Nearest
                  );
                  return;
               }

               try {
                  activeCivFlag = new Image(
                     new Texture(Gdx.files.internal("game/flagsH/" + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag()) + ".png")),
                     Texture.TextureFilter.Linear
                  );
               } catch (GdxRuntimeException var9) {
                  if (isAndroid()) {
                     try {
                        activeCivFlag = new Image(
                           new Texture(
                              Gdx.files
                                 .local(
                                    "game/civilizations_editor/"
                                       + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag())
                                       + "/"
                                       + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag())
                                       + "_FLH.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     } catch (GdxRuntimeException var5) {
                        activeCivFlag = new Image(
                           new Texture(
                              Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag())
                                       + "/"
                                       + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag())
                                       + "_FLH.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     }
                  } else {
                     activeCivFlag = new Image(
                        new Texture(
                           Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag())
                                    + "/"
                                    + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag())
                                    + "_FLH.png"
                              )
                        ),
                        Texture.TextureFilter.Linear
                     );
                  }
               }
            }
         } catch (GdxRuntimeException var11) {
            activeCivFlag = null;
            if (LOGS) {
               exceptionStack(var11);
            }
         } catch (OutOfMemoryError var12) {
            activeCivFlag = null;
            if (LOGS) {
               exceptionStack(var12);
            }
         } catch (RuntimeException var13) {
            activeCivFlag = null;
            if (LOGS) {
               exceptionStack(var13);
            }
         }

         if (game.getCiv(activeCivInfo).civGameData.leaderData != null && game.getCiv(activeCivInfo).civGameData.leaderData.getImage().length() > 0) {
            try {
               try {
                  activeCivLeader = new Image(
                     new Texture(Gdx.files.internal("game/leadersIMG/" + game.getCiv(activeCivInfo).civGameData.leaderData.getImage())),
                     Texture.TextureFilter.Linear
                  );
               } catch (GdxRuntimeException var4) {
                  activeCivLeader = new Image(
                     new Texture(Gdx.files.local("game/leadersIMG/" + game.getCiv(activeCivInfo).civGameData.leaderData.getImage())),
                     Texture.TextureFilter.Linear
                  );
               }
            } catch (GdxRuntimeException var6) {
               activeCivLeader = null;
               if (LOGS) {
                  exceptionStack(var6);
               }
            } catch (OutOfMemoryError var7) {
               activeCivLeader = null;
               if (LOGS) {
                  exceptionStack(var7);
               }
            } catch (RuntimeException var8) {
               activeCivLeader = null;
               if (LOGS) {
                  exceptionStack(var8);
               }
            }
         }
      } catch (NullPointerException var14) {
         if (LOGS) {
            exceptionStack(var14);
         }
      }
   }

   public static final void updateActiveCivInfo_CreateNewGame() {
      menuManager.getCreate_NewGame_Civ_Info().getMenuElement(1).setText("" + game.getCiv(activeCivInfo).getCivName());
      menuManager.getCreate_NewGame_Civ_Info().getMenuElement(0).setText("" + game.getCiv(activeCivInfo).getRankPosition());
      menuManager.getCreate_NewGame_Civ_Info().getMenuElement(3).setCurrent(game.getCiv(activeCivInfo).getNumOfProvinces());
      menuManager.getCreate_NewGame_Civ_Info()
         .getMenuElement(4)
         .setVisible(game.getCiv(activeCivInfo).civGameData.leaderData != null && game.getCiv(activeCivInfo).civGameData.leaderData.getName().length() > 0);
      if (menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).getVisible()) {
         menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).setText(game.getCiv(activeCivInfo).civGameData.leaderData.getName());
      }

      if (menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).getVisible()) {
         menuManager.getCreate_NewGame_Civ_Info().getMenuElement(3).setHeight(PADDING * 2 + TEXT_HEIGHT);
         menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).setHeight(PADDING * 2 + TEXT_HEIGHT);
         int totalH = menuManager.getCreate_NewGame_Civ_Info().getHeight();
         int elemH = (int)(TEXT_HEIGHT + TEXT_HEIGHT * 0.8F * 2.0F + PADDING * 2);
         totalH -= elemH;
         totalH = Math.min(totalH, menuManager.getCreate_NewGame_Civ_Info().getMenuElement(2).getPosY() * 2);
         menuManager.getCreate_NewGame_Civ_Info().getMenuElement(1).setPosY(totalH / 2);
         menuManager.getCreate_NewGame_Civ_Info()
            .getMenuElement(4)
            .setPosY(menuManager.getCreate_NewGame_Civ_Info().getMenuElement(1).getPosY() + TEXT_HEIGHT + PADDING);
         menuManager.getCreate_NewGame_Civ_Info()
            .getMenuElement(3)
            .setPosY((int)(menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).getPosY() + TEXT_HEIGHT * 0.8F + PADDING));
      } else {
         menuManager.getCreate_NewGame_Civ_Info().getMenuElement(3).setHeight(PADDING * 4 + TEXT_HEIGHT);
         menuManager.getCreate_NewGame_Civ_Info()
            .getMenuElement(1)
            .setPosY(
               menuManager.getCreate_NewGame_Civ_Info().getMenuElement(2).getPosY()
                  + menuManager.getCreate_NewGame_Civ_Info().getMenuElement(2).getHeight() / 2
                  - (int)((TEXT_HEIGHT + TEXT_HEIGHT * 0.8F + PADDING * 2) / 2.0F)
            );
         menuManager.getCreate_NewGame_Civ_Info()
            .getMenuElement(3)
            .setPosY(menuManager.getCreate_NewGame_Civ_Info().getMenuElement(1).getPosY() + TEXT_HEIGHT + PADDING);
      }

      menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(1).setText("" + getNumberWithSpaces("" + game.getCiv(activeCivInfo).countPopulation()));

      try {
         menuManager.getCreate_NewGame_Civ_Info_Stats()
            .getMenuElement(3)
            .setText(
               ""
                  + (
                     game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCitiesSize() > 0
                        ? game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCity(0).getCityName()
                        : game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getName()
                  )
            );
      } catch (IndexOutOfBoundsException var6) {
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(3).setText(langManager.get("NoData"));
      }

      try {
         int nLargestCity = game.getCiv(activeCivInfo).getProvinceID(0);

         for (int i = 1; i < game.getCiv(activeCivInfo).getNumOfProvinces(); i++) {
            if (game.getProvince(nLargestCity).getPopulationData().getPopulation()
               < game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getPopulation()) {
               nLargestCity = game.getCiv(activeCivInfo).getProvinceID(i);
            }
         }

         menuManager.getCreate_NewGame_Civ_Info_Stats()
            .getMenuElement(5)
            .setText(
               game.getProvince(nLargestCity).getCitiesSize() > 0
                  ? game.getProvince(nLargestCity).getCity(0).getCityName()
                  : game.getProvince(nLargestCity).getName()
            );
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(5).setCurrent(nLargestCity);
      } catch (IndexOutOfBoundsException var7) {
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(5).setText(langManager.get("NoData"));
      }

      ArrayList<Integer> nCivs = new ArrayList<>();
      ArrayList<Integer> nData = new ArrayList<>();
      boolean addNewData = true;

      for (int ix = 0; ix < game.getCiv(activeCivInfo).getNumOfProvinces(); ix++) {
         for (int j = 0; j < game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getNationalitiesSize(); j++) {
            addNewData = true;

            for (int k = 0; k < nCivs.size(); k++) {
               if (nCivs.get(k) == game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getCivID(j)) {
                  addNewData = false;
                  nData.set(k, nData.get(k) + game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getPopulationID(j));
                  break;
               }
            }

            if (addNewData) {
               nCivs.add(game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getCivID(j));
               nData.add(game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getPopulationID(j));
            }
         }
      }

      if (nCivs.size() == 0) {
         nCivs.add(activeCivInfo);
         nData.add(1);
      }

      addNewData = menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(6).getIsInView();
      menuManager.getCreate_NewGame_Civ_Info_Stats()
         .setMenuElement(
            6,
            new Graph_Circle(
               menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(6).getPosX(),
               menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(6).getPosY(),
               nData,
               nCivs,
               null
            ) {
               @Override
               public void buildElementHover() {
                  this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
               }
            }
         );
      menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(6).setIsInView(addNewData);
      menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(8).setText("" + (int)(game.getCiv(activeCivInfo).getTechnologyLevel() * 100.0F) / 100.0F);
      menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(10).setText("" + getNumberWithSpaces("" + game.getCiv(activeCivInfo).countEconomy()));
      new Random();
      menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(12).setCurrent(getCivDifficulty(activeCivInfo));
      menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(13).setCurrent(game.getCiv(activeCivInfo).getHappiness());
      menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setCurrent(game.getCiv(activeCivInfo).getIdeologyID());
      if (game.getCiv(activeCivInfo).getIsPartOfHolyRomanEmpire()) {
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setCurrent(-1);
         if (holyRomanEmpire_Manager.getHRE().getIsEmperor(activeCivInfo)) {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setText(langManager.get("Emperor"));
         } else if (holyRomanEmpire_Manager.getHRE().getIsElector(activeCivInfo)) {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setText(langManager.get("Elector"));
         } else {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setText(langManager.get("Prince"));
         }
      } else {
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setCurrent(game.getCiv(activeCivInfo).getIdeologyID());
         menuManager.getCreate_NewGame_Civ_Info_Stats()
            .getMenuElement(14)
            .setText("" + ideologiesManager.getIdeology(game.getCiv(activeCivInfo).getIdeologyID()).getName());
      }

      menuManager.rebuildCreate_NewGame_Civ_Info_Diplomacy();
      if (activeCivLeader != null) {
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(0).setVisible(false);
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(2).setVisible(false);
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(4).setVisible(false);
      } else {
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(0).setVisible(true);
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(2).setVisible(true);
         menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(4).setVisible(true);
      }
   }

   public static final int getCivDifficulty(int nCivID) {
      float fOut = 5.0F;
      if (ideologiesManager.getIdeology(game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED > 0) {
         fOut += 10.8F;
      }

      fOut += 65.0F * game.getCiv(nCivID).getRankPosition() / game.getCivsSize();
      ArrayList<Integer> tempNeighboors = new ArrayList<>();

      for (int i = 0; i < game.getCiv(nCivID).getNumOfProvinces(); i++) {
         for (int j = 0; j < game.getProvince(game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); j++) {
            if (game.getProvince(game.getProvince(game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() > 0) {
               boolean wasAdded = false;

               for (int k = 0; k < tempNeighboors.size(); k++) {
                  if (tempNeighboors.get(k) == game.getProvince(game.getProvince(game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID()) {
                     wasAdded = true;
                     break;
                  }
               }

               if (!wasAdded) {
                  tempNeighboors.add(game.getProvince(game.getProvince(game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID());
               }
            }
         }
      }

      fOut += tempNeighboors.size();

      for (int kx = 0; kx < tempNeighboors.size(); kx++) {
         fOut += 2.68F * Math.min((float)game.getCiv(tempNeighboors.get(kx)).getRankScore() / game.getCiv(nCivID).getRankScore(), 1.85F);
      }

      return Math.min((int)fOut, 100);
   }

   public static final void updateActiveCivInfo_InGame() {
      menuManager.getInGame_CivInfo().getMenuElement(1).setText("" + game.getCiv(activeCivInfo).getCivName());
      menuManager.getInGame_CivInfo().getMenuElement(0).setText("" + game.getCiv(activeCivInfo).getRankPosition());
      menuManager.getInGame_CivInfo().getMenuElement(3).setCurrent(game.getCiv(activeCivInfo).getNumOfProvinces());
      menuManager.getInGame_CivInfo()
         .getMenuElement(4)
         .setVisible(game.getCiv(activeCivInfo).civGameData.leaderData != null && game.getCiv(activeCivInfo).civGameData.leaderData.getName().length() > 0);
      if (menuManager.getInGame_CivInfo().getMenuElement(4).getVisible()) {
         menuManager.getInGame_CivInfo().getMenuElement(4).setText(game.getCiv(activeCivInfo).civGameData.leaderData.getName());
      }

      if (menuManager.getInGame_CivInfo().getMenuElement(4).getVisible()) {
         menuManager.getInGame_CivInfo().getMenuElement(3).setHeight(PADDING * 2 + TEXT_HEIGHT);
         menuManager.getInGame_CivInfo().getMenuElement(4).setHeight(PADDING * 2 + TEXT_HEIGHT);
         int totalH = menuManager.getInGame_CivInfo().getHeight();
         int elemH = (int)(TEXT_HEIGHT + TEXT_HEIGHT * 0.8F * 2.0F + PADDING * 2);
         totalH -= elemH;
         totalH = Math.min(totalH, menuManager.getInGame_CivInfo().getMenuElement(2).getPosY() * 2);
         menuManager.getInGame_CivInfo().getMenuElement(1).setPosY(totalH / 2);
         menuManager.getInGame_CivInfo().getMenuElement(4).setPosY(menuManager.getInGame_CivInfo().getMenuElement(1).getPosY() + TEXT_HEIGHT + PADDING);
         menuManager.getInGame_CivInfo()
            .getMenuElement(3)
            .setPosY((int)(menuManager.getInGame_CivInfo().getMenuElement(4).getPosY() + TEXT_HEIGHT * 0.8F + PADDING));
      } else {
         menuManager.getInGame_CivInfo().getMenuElement(3).setHeight(PADDING * 4 + TEXT_HEIGHT);
         menuManager.getInGame_CivInfo()
            .getMenuElement(1)
            .setPosY(
               menuManager.getInGame_CivInfo().getMenuElement(2).getPosY()
                  + menuManager.getInGame_CivInfo().getMenuElement(2).getHeight() / 2
                  - (int)((TEXT_HEIGHT + TEXT_HEIGHT * 0.8F + PADDING * 2) / 2.0F)
            );
         menuManager.getInGame_CivInfo().getMenuElement(3).setPosY(menuManager.getInGame_CivInfo().getMenuElement(1).getPosY() + TEXT_HEIGHT + PADDING);
      }

      menuManager.getInGame_CivInfo_Stats().getMenuElement(1).setText("" + getNumberWithSpaces("" + game.getCiv(activeCivInfo).countPopulation()));

      try {
         if (FOG_OF_WAR == 2) {
            if (game.getPlayer(PLAYER_TURNID).getMetProvince(game.getCiv(activeCivInfo).getCapitalProvinceID())) {
               menuManager.getInGame_CivInfo_Stats()
                  .getMenuElement(3)
                  .setText(
                     ""
                        + (
                           game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCitiesSize() > 0
                              ? game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCity(0).getCityName()
                              : game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getName()
                        )
                  );
            } else {
               menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setText(langManager.get("NoData"));
               menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setCurrent(-1);
            }
         } else {
            menuManager.getInGame_CivInfo_Stats()
               .getMenuElement(3)
               .setText(
                  ""
                     + (
                        game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCitiesSize() > 0
                           ? game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCity(0).getCityName()
                           : game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getName()
                     )
               );
         }
      } catch (IndexOutOfBoundsException var6) {
         menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setText(langManager.get("NoData"));
         menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setCurrent(-1);
      }

      try {
         int nLargestCity = game.getCiv(activeCivInfo).getProvinceID(0);

         for (int i = 1; i < game.getCiv(activeCivInfo).getNumOfProvinces(); i++) {
            if (game.getProvince(nLargestCity).getPopulationData().getPopulation()
               < game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getPopulation()) {
               nLargestCity = game.getCiv(activeCivInfo).getProvinceID(i);
            }
         }

         if (FOG_OF_WAR == 2) {
            if (game.getPlayer(PLAYER_TURNID).getMetProvince(nLargestCity)) {
               menuManager.getInGame_CivInfo_Stats()
                  .getMenuElement(5)
                  .setText(
                     game.getProvince(nLargestCity).getCitiesSize() > 0
                        ? game.getProvince(nLargestCity).getCity(0).getCityName()
                        : game.getProvince(nLargestCity).getName()
                  );
               menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setCurrent(nLargestCity);
            } else {
               menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setText(langManager.get("NoData"));
               menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setCurrent(-1);
            }
         } else {
            menuManager.getInGame_CivInfo_Stats()
               .getMenuElement(5)
               .setText(
                  game.getProvince(nLargestCity).getCitiesSize() > 0
                     ? game.getProvince(nLargestCity).getCity(0).getCityName()
                     : game.getProvince(nLargestCity).getName()
               );
            menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setCurrent(nLargestCity);
         }
      } catch (IndexOutOfBoundsException var7) {
         menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setText(langManager.get("NoData"));
         menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setCurrent(-1);
      }

      ArrayList<Integer> nCivs = new ArrayList<>();
      ArrayList<Integer> nData = new ArrayList<>();
      boolean addNewData = true;

      for (int ix = 0; ix < game.getCiv(activeCivInfo).getNumOfProvinces(); ix++) {
         for (int j = 0; j < game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getNationalitiesSize(); j++) {
            addNewData = true;

            for (int k = 0; k < nCivs.size(); k++) {
               if (nCivs.get(k) == game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getCivID(j)) {
                  addNewData = false;
                  nData.set(k, nData.get(k) + game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getPopulationID(j));
                  break;
               }
            }

            if (addNewData) {
               nCivs.add(game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getCivID(j));
               nData.add(game.getProvince(game.getCiv(activeCivInfo).getProvinceID(ix)).getPopulationData().getPopulationID(j));
            }
         }
      }

      if (nCivs.size() == 0) {
         nCivs.add(activeCivInfo);
         nData.add(1);
      }

      addNewData = menuManager.getInGame_CivInfo_Stats().getMenuElement(6).getIsInView();
      menuManager.getInGame_CivInfo_Stats()
         .setMenuElement(
            6,
            new Graph_Circle(
               menuManager.getInGame_CivInfo_Stats().getMenuElement(6).getPosX(),
               menuManager.getInGame_CivInfo_Stats().getMenuElement(6).getPosY(),
               nData,
               nCivs,
               null
            ) {
               @Override
               public void buildElementHover() {
                  this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
               }
            }
         );
      menuManager.getInGame_CivInfo_Stats().getMenuElement(6).setIsInView(addNewData);
      menuManager.getInGame_CivInfo_Stats().getMenuElement(8).setText("" + (int)(game.getCiv(activeCivInfo).getTechnologyLevel() * 100.0F) / 100.0F);
      menuManager.getInGame_CivInfo_Stats().getMenuElement(10).setText("" + getNumberWithSpaces("" + game.getCiv(activeCivInfo).countEconomy()));
      menuManager.getInGame_CivInfo_Stats().getMenuElement(11).setCurrent(game.getCiv(activeCivInfo).getHappiness());
      menuManager.getInGame_CivInfo_Stats().getMenuElement(13).setCurrent((int)(game.getCiv(activeCivInfo).getStability() * 100.0F));
      if (game.getCiv(activeCivInfo).getIsPartOfHolyRomanEmpire()) {
         menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setCurrent(-1);
         if (holyRomanEmpire_Manager.getHRE().getIsEmperor(activeCivInfo)) {
            menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setText(langManager.get("Emperor"));
         } else if (holyRomanEmpire_Manager.getHRE().getIsElector(activeCivInfo)) {
            menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setText(langManager.get("Elector"));
         } else {
            menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setText(langManager.get("Prince"));
         }
      } else {
         menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setCurrent(game.getCiv(activeCivInfo).getIdeologyID());
         menuManager.getInGame_CivInfo_Stats()
            .getMenuElement(12)
            .setText("" + ideologiesManager.getIdeology(game.getCiv(activeCivInfo).getIdeologyID()).getName());
      }

      menuManager.rebuildInGame_Civ_Info_Diplomacy();
      menuManager.setVisible_InGame_CivInfo_Decisions(game.getPlayer(PLAYER_TURNID).getCivID() == activeCivInfo);
      if (menuManager.getVisible_InGame_CivInfo_Stats_Opinions()) {
         menuManager.rebuildInGame_Civ_Info_Opinions();
      }

      if (activeCivLeader != null) {
         menuManager.getInGame_CivInfo_Stats().getMenuElement(0).setVisible(false);
         menuManager.getInGame_CivInfo_Stats().getMenuElement(2).setVisible(false);
         menuManager.getInGame_CivInfo_Stats().getMenuElement(4).setVisible(false);
      } else {
         menuManager.getInGame_CivInfo_Stats().getMenuElement(0).setVisible(true);
         menuManager.getInGame_CivInfo_Stats().getMenuElement(2).setVisible(true);
         menuManager.getInGame_CivInfo_Stats().getMenuElement(4).setVisible(true);
      }
   }

   public static final void updateCreateAVassal_CivInfo() {
      if (createVassal_Data.sCivTag != null) {
         menuManager.getCreateAVassal_Info().getMenuElement(0).setText(langManager.getCiv(createVassal_Data.sCivTag));
      }

      menuManager.getCreateAVassal_Info().getMenuElement(2).setCurrent(game.getSelectedProvinces().getProvincesSize());
      int tempPopulation = 0;

      for (int i = 0; i < game.getSelectedProvinces().getProvincesSize(); i++) {
         tempPopulation += game.getProvince(game.getSelectedProvinces().getProvince(i)).getPopulationData().getPopulation();
      }

      menuManager.getCreateAVassal_Stats().getMenuElement(1).setText("" + getNumberWithSpaces("" + tempPopulation));
      if (createVassal_Data.iCapitalProvinceID >= 0) {
         menuManager.getCreateAVassal_Stats()
            .getMenuElement(3)
            .setText(
               ""
                  + (
                     game.getProvince(createVassal_Data.iCapitalProvinceID).getCitiesSize() > 0
                        ? game.getProvince(createVassal_Data.iCapitalProvinceID).getCity(0).getCityName()
                        : game.getProvince(createVassal_Data.iCapitalProvinceID).getName()
                  )
            );
      } else {
         menuManager.getCreateAVassal_Stats().getMenuElement(3).setText("-");
      }

      int nLargestCity = -1;
      if (game.getSelectedProvinces().getProvincesSize() > 0) {
         nLargestCity = 0;

         for (int i = 1; i < game.getSelectedProvinces().getProvincesSize(); i++) {
            if (game.getProvince(game.getSelectedProvinces().getProvince(nLargestCity)).getPopulationData().getPopulation()
               < game.getProvince(game.getSelectedProvinces().getProvince(i)).getPopulationData().getPopulation()) {
               nLargestCity = i;
            }
         }
      }

      if (nLargestCity >= 0) {
         menuManager.getCreateAVassal_Stats()
            .getMenuElement(5)
            .setText(
               game.getProvince(game.getSelectedProvinces().getProvince(nLargestCity)).getCitiesSize() > 0
                  ? game.getProvince(game.getSelectedProvinces().getProvince(nLargestCity)).getCity(0).getCityName()
                  : game.getProvince(game.getSelectedProvinces().getProvince(nLargestCity)).getName()
            );
         menuManager.getCreateAVassal_Stats().getMenuElement(5).setCurrent(game.getSelectedProvinces().getProvince(nLargestCity));
      } else {
         menuManager.getCreateAVassal_Stats().getMenuElement(5).setText("-");
         menuManager.getCreateAVassal_Stats().getMenuElement(5).setCurrent(-1);
      }

      ArrayList<Integer> nCivs = new ArrayList<>();
      ArrayList<Integer> nData = new ArrayList<>();
      boolean addNewData = true;
      if (game.getSelectedProvinces().getProvincesSize() > 0) {
         for (int ix = 0; ix < game.getSelectedProvinces().getProvincesSize(); ix++) {
            for (int j = 0; j < game.getProvince(game.getSelectedProvinces().getProvince(ix)).getPopulationData().getNationalitiesSize(); j++) {
               addNewData = true;

               for (int k = 0; k < nCivs.size(); k++) {
                  if (nCivs.get(k) == game.getProvince(game.getSelectedProvinces().getProvince(ix)).getPopulationData().getCivID(j)) {
                     addNewData = false;
                     nData.set(k, nData.get(k) + game.getProvince(game.getSelectedProvinces().getProvince(ix)).getPopulationData().getPopulationID(j));
                     break;
                  }
               }

               if (addNewData) {
                  nCivs.add(game.getProvince(game.getSelectedProvinces().getProvince(ix)).getPopulationData().getCivID(j));
                  nData.add(game.getProvince(game.getSelectedProvinces().getProvince(ix)).getPopulationData().getPopulationID(j));
               }
            }
         }
      } else {
         nCivs.add(game.getPlayer(PLAYER_TURNID).getCivID());
         nData.add(1);
      }

      addNewData = menuManager.getCreateAVassal_Stats().getMenuElement(6).getIsInView();
      menuManager.getCreateAVassal_Stats()
         .setMenuElement(
            6,
            new Graph_Circle(
               menuManager.getCreateAVassal_Stats().getMenuElement(6).getPosX(),
               menuManager.getCreateAVassal_Stats().getMenuElement(6).getPosY(),
               nData,
               nCivs,
               null
            ) {
               @Override
               public void buildElementHover() {
                  this.menuElementHover = CFG.game.getHover_PopulationOfCiv_CreateAVassal();
               }
            }
         );
      menuManager.getCreateAVassal_Stats().getMenuElement(6).setIsInView(addNewData);
      menuManager.getCreateAVassal_Stats()
         .getMenuElement(8)
         .setText("" + (int)(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getTechnologyLevel() * 0.72F * 100.0F) / 100.0F);
      int tempEconomy = 0;

      for (int ix = 0; ix < game.getSelectedProvinces().getProvincesSize(); ix++) {
         tempEconomy += game.getProvince(game.getSelectedProvinces().getProvince(ix)).getEconomy();
      }

      menuManager.getCreateAVassal_Stats().getMenuElement(10).setText("" + getNumberWithSpaces("" + tempEconomy));
      if (game.getSelectedProvinces().getProvincesSize() > 0) {
         float tHappiness = 0.0F;

         for (int ix = 0; ix < game.getSelectedProvinces().getProvincesSize(); ix++) {
            tHappiness += game.getProvince(game.getSelectedProvinces().getProvincesSize()).getHappiness() * 100.0F;
         }

         menuManager.getCreateAVassal_Stats().getMenuElement(11).setCurrent((int)(tHappiness / game.getSelectedProvinces().getProvincesSize()));
      } else {
         menuManager.getCreateAVassal_Stats().getMenuElement(11).setCurrent(0);
      }

      if (createVassal_Data.sCivTag != null) {
         menuManager.getCreateAVassal_Stats().getMenuElement(12).setCurrent(ideologiesManager.getIdeologyID(createVassal_Data.sCivTag));
         menuManager.getCreateAVassal_Stats()
            .getMenuElement(12)
            .setText(ideologiesManager.getIdeology(ideologiesManager.getIdeologyID(createVassal_Data.sCivTag)).getName());
      } else {
         menuManager.getCreateAVassal_Stats().getMenuElement(12).setCurrent(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getIdeologyID());
         menuManager.getCreateAVassal_Stats()
            .getMenuElement(12)
            .setText(ideologiesManager.getIdeology(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getIdeologyID()).getName());
      }
   }

   public static final void disposeActiveCivLeader() {
      try {
         if (activeCivLeader != null) {
            activeCivLeader.getTexture().dispose();
            activeCivLeader = null;
         }
      } catch (RuntimeException var1) {
         if (LOGS) {
            exceptionStack(var1);
         }
      }
   }

   public static final void disposeActiveCivFlag() {
      try {
         if (activeCivFlag != null) {
            activeCivFlag.getTexture().dispose();
            activeCivFlag = null;
            activeCivInfo = 0;
         }

         disposeActiveCivLeader();
      } catch (RuntimeException var1) {
         if (LOGS) {
            exceptionStack(var1);
         }
      }
   }

   public static final Image getActiveCivFlag() {
      return activeCivFlag == null ? game.getCiv(activeCivInfo).getFlag() : activeCivFlag;
   }

   public static final String getPercentage(int nA, int nB, int nPrecision) {
      float nOut = (float)nA / nB * 100.0F;
      return nOut - Math.floor(nOut) == 0.0 ? "" + (int)nOut : ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
   }

   public static final String getPercentage_Max100(int nA, int nB, int nPrecision) {
      float nOut = (float)nA / nB * 100.0F;
      if (nOut > 100.0F) {
         nOut = 100.0F;
      }

      return nOut - Math.floor(nOut) == 0.0 ? "" + (int)nOut : ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
   }

   public static final String getPercentage(float nA, float nB, int nPrecision) {
      float nOut = nA / nB;
      if (nOut > 100.0F) {
         nOut = 100.0F;
      }

      return ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
   }

   public static final String getPercentage_Max100(float nA, float nB, int nPrecision) {
      float nOut = nA / nB;
      return ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
   }

   public static final String getPercentage_Max100_X100(float nA, float nB, int nPrecision) {
      float nOut = nA / nB * 100.0F;
      return ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
   }

   public static final int getMetersToFeet(int nValue) {
      return (int)(nValue * 3.2808F);
   }

   public static final String getNumberWithSpaces(String nValue) {
      String nOut = "";

      for (int i = nValue.length(); i > 0; i -= 3) {
         nOut = " " + nValue.substring(i - 3 > 0 ? i - 3 : 0, i) + nOut;
      }

      return nOut.charAt(0) == ' ' ? nOut.substring(1, nOut.length()) : nOut;
   }

   public static final String getNumber_SHORT(int nValue) {
      if (nValue < 1000) {
         return "" + nValue;
      } else if (nValue < 1000000) {
         String outValue = "" + nValue / 1000.0F;

         try {
            Gdx.app.log("AoC", "" + outValue.charAt(outValue.indexOf(".") + 1));
            return ""
               + (
                  outValue.charAt(outValue.indexOf(".") + 1) == '0'
                     ? "" + nValue / 1000 + langManager.get("Value_Thousand")
                     : outValue.substring(0, outValue.indexOf(".") + 2) + langManager.get("Value_Thousand")
               );
         } catch (IndexOutOfBoundsException var3) {
            return "" + nValue / 1000 + langManager.get("Value_Thousand");
         }
      } else {
         String outValue = "" + nValue / 1000000.0F;

         try {
            return ""
               + (
                  outValue.charAt(outValue.indexOf(".") + 1) == '0'
                     ? "" + nValue / 1000 + langManager.get("Value_Million")
                     : outValue.substring(0, outValue.indexOf(".") + 2) + langManager.get("Value_Million")
               );
         } catch (IndexOutOfBoundsException var4) {
            return "" + nValue / 1000 + langManager.get("Value_Million");
         }
      }
   }

   public static final int getHappinesImage(int nHappinesss) {
      return nHappinesss > 60 ? Images.happiness : (nHappinesss > 35 ? Images.happiness1 : Images.happiness2);
   }

   public static final boolean compareAlphabetic_TwoString(String a, String b) {
      for (int i = 0; i < a.length() && i < b.length(); i++) {
         if (a.charAt(i) < b.charAt(i)) {
            return false;
         }

         if (a.charAt(i) != b.charAt(i)) {
            return true;
         }
      }

      return false;
   }

   public static boolean getIsInFormableCiv(int nProvinceID) {
      try {
         for (int i = 0; i < formableCivs_GameData.getProvincesSize(); i++) {
            if (formableCivs_GameData.getProvinceID(i) == nProvinceID) {
               return true;
            }
         }
      } catch (NullPointerException var2) {
         if (LOGS) {
            exceptionStack(var2);
         }
      }

      return false;
   }

   public static final String getContinentDataName(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("map/data/continents/packges_data/" + sTag);
         Continent_GameData tempContinentGameData = (Continent_GameData)deserialize(file.readBytes());
         return tempContinentGameData.getName();
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      }

      return sTag;
   }

   public static final String getRegionDataName(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("map/data/regions/packges_data/" + sTag);
         Region_GameData tempRegionGameData = (Region_GameData)deserialize(file.readBytes());
         return tempRegionGameData.getName();
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      }

      return sTag;
   }

   public static final Color getContinentDataColor(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("map/data/continents/packges_data/" + sTag);
         Continent_GameData tempContinentGameData = (Continent_GameData)deserialize(file.readBytes());
         return new Color(tempContinentGameData.getR(), tempContinentGameData.getG(), tempContinentGameData.getB(), 0.7F);
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      }

      return new Color(1.0F, 1.0F, 1.0F, 0.7F);
   }

   public static final Color getRegionDataColor(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("map/data/regions/packges_data/" + sTag);
         Region_GameData tempRegionGameData = (Region_GameData)deserialize(file.readBytes());
         return new Color(tempRegionGameData.getR(), tempRegionGameData.getG(), tempRegionGameData.getB(), 0.45F);
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      }

      return new Color(1.0F, 1.0F, 1.0F, 0.45F);
   }

   public static final String getPackageContinentDataName(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("map/data/continents/packges/" + sTag);
         Package_ContinentsData tempPackageContinentGameData = (Package_ContinentsData)deserialize(file.readBytes());
         return tempPackageContinentGameData.getPackageName();
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      }

      return sTag;
   }

   public static final String getPackageRegionDataName(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("map/data/regions/packges/" + sTag);
         Package_RegionsData tempPackageRegionsGameData = (Package_RegionsData)deserialize(file.readBytes());
         return tempPackageRegionsGameData.getPackageName();
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      }

      return sTag;
   }

   public static final String getPackageDiplomacyColorsDataName(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("game/diplomacy_colors/packages/" + sTag);
         DiplomacyColors_GameData2 tempPackageGameData = (DiplomacyColors_GameData2)deserialize(file.readBytes());
         return tempPackageGameData.getName();
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      }

      return sTag;
   }

   public static final String getPackageContinentData_AllNames(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("map/data/continents/packges/" + sTag);
         Package_ContinentsData tempPackageContinentGameData = (Package_ContinentsData)deserialize(file.readBytes());
         String tempOutput = "";

         for (int i = 0; i < tempPackageContinentGameData.getContinentsTagsSize(); i++) {
            tempOutput = tempOutput
               + getContinentDataName(tempPackageContinentGameData.getContinentTag(i))
               + (i < tempPackageContinentGameData.getContinentsTagsSize() - 1 ? ", " : "");
         }

         return tempOutput;
      } catch (ClassNotFoundException var5) {
      } catch (IOException var6) {
      }

      return langManager.get("Error");
   }

   public static final String getPackageRegionsData_AllNames(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("map/data/regions/packges/" + sTag);
         Package_RegionsData tempPackageRegionGameData = (Package_RegionsData)deserialize(file.readBytes());
         String tempOutput = "";

         for (int i = 0; i < tempPackageRegionGameData.getRegionsTagsSize(); i++) {
            tempOutput = tempOutput
               + getRegionDataName(tempPackageRegionGameData.getRegionTag(i))
               + (i < tempPackageRegionGameData.getRegionsTagsSize() - 1 ? ", " : "");
         }

         return tempOutput;
      } catch (ClassNotFoundException var5) {
      } catch (IOException var6) {
      }

      return langManager.get("Error");
   }

   public static final Color getGrowthRateColor(int nGrowthRate, float nAlpha) {
      switch (nGrowthRate / 10) {
         case 0:
            return getColorStep(COLOR_GROWTH_RATE[0], COLOR_GROWTH_RATE[1], nGrowthRate % 10, 10, nAlpha);
         case 1:
            return getColorStep(COLOR_GROWTH_RATE[1], COLOR_GROWTH_RATE[2], nGrowthRate % 10, 10, nAlpha);
         case 2:
            return getColorStep(COLOR_GROWTH_RATE[2], COLOR_GROWTH_RATE[3], nGrowthRate % 10, 10, nAlpha);
         case 3:
            return getColorStep(COLOR_GROWTH_RATE[3], COLOR_GROWTH_RATE[4], nGrowthRate % 10, 10, nAlpha);
         case 4:
            return getColorStep(COLOR_GROWTH_RATE[4], COLOR_GROWTH_RATE[5], nGrowthRate % 10, 10, nAlpha);
         case 5:
            return getColorStep(COLOR_GROWTH_RATE[5], COLOR_GROWTH_RATE[6], nGrowthRate % 10, 10, nAlpha);
         case 6:
            return getColorStep(COLOR_GROWTH_RATE[6], COLOR_GROWTH_RATE[7], nGrowthRate % 10, 10, nAlpha);
         case 7:
            return getColorStep(COLOR_GROWTH_RATE[7], COLOR_GROWTH_RATE[8], nGrowthRate % 10, 10, nAlpha);
         case 8:
            return getColorStep(COLOR_GROWTH_RATE[8], COLOR_GROWTH_RATE[9], nGrowthRate % 10, 10, nAlpha);
         case 9:
            return getColorStep(COLOR_GROWTH_RATE[9], COLOR_GROWTH_RATE[10], nGrowthRate % 10, 10, nAlpha);
         case 10:
            return new Color(
               COLOR_GROWTH_RATE[COLOR_GROWTH_RATE.length - 1].r,
               COLOR_GROWTH_RATE[COLOR_GROWTH_RATE.length - 1].g,
               COLOR_GROWTH_RATE[COLOR_GROWTH_RATE.length - 1].b,
               nAlpha
            );
         default:
            return new Color(
               COLOR_GROWTH_RATE[COLOR_GROWTH_RATE.length - 1].r,
               COLOR_GROWTH_RATE[COLOR_GROWTH_RATE.length - 1].g,
               COLOR_GROWTH_RATE[COLOR_GROWTH_RATE.length - 1].b,
               nAlpha
            );
      }
   }

   public static final void updateMAX_Army() {
      MAX_PROVINCE_VALUE = 0;
      if (FOG_OF_WAR == 0) {
         for (int i = 0; i < game.getProvincesSize(); i++) {
            if (game.getProvince(i).getWasteland() < 0 && game.getProvinceArmy(i) > MAX_PROVINCE_VALUE) {
               MAX_PROVINCE_VALUE = game.getProvinceArmy(i);
            }
         }
      } else {
         for (int ix = 0; ix < game.getProvincesSize(); ix++) {
            if (game.getProvince(ix).getWasteland() < 0 && game.getPlayer(PLAYER_TURNID).getFogOfWar(ix) && game.getProvinceArmy(ix) > MAX_PROVINCE_VALUE) {
               MAX_PROVINCE_VALUE = game.getProvinceArmy(ix);
            }
         }
      }
   }

   public static final Color getProvinceArmyColor_Neutral(int nData) {
      return new Color(COLOR_PROVINCE_ARMY_MAX.r, COLOR_PROVINCE_ARMY_MAX.g, COLOR_PROVINCE_ARMY_MAX.b, 0.2875F + 0.2875F * ((float)nData / MAX_PROVINCE_VALUE));
   }

   public static final Color getProvinceArmyColor_Own(int nData) {
      return new Color(
         diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
         diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
         diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
         0.2875F + 0.2875F * ((float)nData / MAX_PROVINCE_VALUE)
      );
   }

   public static final Color getProvinceArmyColor_AtWar(int nData) {
      return new Color(
         diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
         diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
         diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
         0.2875F + 0.2875F * ((float)nData / MAX_PROVINCE_VALUE)
      );
   }

   public static final Color getProvinceArmyColor_Alliance(int nData) {
      return new Color(
         diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
         diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
         diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
         0.2875F + 0.2875F * ((float)nData / MAX_PROVINCE_VALUE)
      );
   }

   public static final void updateMAX_PROVINCE_VALUE() {
      MAX_PROVINCE_VALUE = 1;

      for (int i = 0; i < game.getProvincesSize(); i++) {
         if (!game.getProvince(i).getSeaProvince() && game.getProvince(i).getWasteland() < 0 && game.getProvinceValue(i) > MAX_PROVINCE_VALUE) {
            MAX_PROVINCE_VALUE = game.getProvinceValue(i);
         }
      }
   }

   public static final Color getProvinceValueColor(int nData) {
      return getColorStep(
         new Color(1.0F, 1.0F, 0.8039216F, 0.75F),
         new Color(0.9098039F, 0.09411765F, 0.09411765F, 0.75F),
         nData,
         MAX_PROVINCE_VALUE,
         0.67499995F + 0.075F * ((float)nData / MAX_PROVINCE_VALUE)
      );
   }

   public static final Color getPopulationColor(int nData, float nAlpha) {
      switch (nData / 10) {
         case 0:
            return getColorStep(COLOR_POPULATION[0], COLOR_POPULATION[1], nData % 10, 10, nAlpha);
         case 1:
            return getColorStep(COLOR_POPULATION[1], COLOR_POPULATION[2], nData % 10, 10, nAlpha);
         case 2:
            return getColorStep(COLOR_POPULATION[2], COLOR_POPULATION[3], nData % 10, 10, nAlpha);
         case 3:
            return getColorStep(COLOR_POPULATION[3], COLOR_POPULATION[4], nData % 10, 10, nAlpha);
         case 4:
            return getColorStep(COLOR_POPULATION[4], COLOR_POPULATION[5], nData % 10, 10, nAlpha);
         case 5:
            return getColorStep(COLOR_POPULATION[5], COLOR_POPULATION[6], nData % 10, 10, nAlpha);
         case 6:
            return getColorStep(COLOR_POPULATION[6], COLOR_POPULATION[7], nData % 10, 10, nAlpha);
         case 7:
            return getColorStep(COLOR_POPULATION[7], COLOR_POPULATION[8], nData % 10, 10, nAlpha);
         case 8:
            return getColorStep(COLOR_POPULATION[8], COLOR_POPULATION[9], nData % 10, 10, nAlpha);
         case 9:
            return getColorStep(COLOR_POPULATION[9], COLOR_POPULATION[10], nData % 10, 10, nAlpha);
         case 10:
            return new Color(COLOR_POPULATION[10].r, COLOR_POPULATION[10].g, COLOR_POPULATION[10].b, nAlpha);
         default:
            return new Color(COLOR_POPULATION[10].r, COLOR_POPULATION[10].g, COLOR_POPULATION[10].b, nAlpha);
      }
   }

   public static final Color getEconomyColor(int nData, float nAlpha) {
      switch (nData / 10) {
         case 0:
            return getColorStep(COLOR_ECONOMY[0], COLOR_ECONOMY[1], nData % 10, 10, nAlpha);
         case 1:
            return getColorStep(COLOR_ECONOMY[1], COLOR_ECONOMY[2], nData % 10, 10, nAlpha);
         case 2:
            return getColorStep(COLOR_ECONOMY[2], COLOR_ECONOMY[3], nData % 10, 10, nAlpha);
         case 3:
            return getColorStep(COLOR_ECONOMY[3], COLOR_ECONOMY[4], nData % 10, 10, nAlpha);
         case 4:
            return getColorStep(COLOR_ECONOMY[4], COLOR_ECONOMY[5], nData % 10, 10, nAlpha);
         case 5:
            return getColorStep(COLOR_ECONOMY[5], COLOR_ECONOMY[6], nData % 10, 10, nAlpha);
         case 6:
            return getColorStep(COLOR_ECONOMY[6], COLOR_ECONOMY[7], nData % 10, 10, nAlpha);
         case 7:
            return getColorStep(COLOR_ECONOMY[7], COLOR_ECONOMY[8], nData % 10, 10, nAlpha);
         case 8:
            return getColorStep(COLOR_ECONOMY[8], COLOR_ECONOMY[9], nData % 10, 10, nAlpha);
         case 9:
            return getColorStep(COLOR_ECONOMY[9], COLOR_ECONOMY[10], nData % 10, 10, nAlpha);
         case 10:
            return new Color(COLOR_ECONOMY[10].r, COLOR_ECONOMY[10].g, COLOR_ECONOMY[10].b, nAlpha);
         default:
            return new Color(COLOR_ECONOMY[10].r, COLOR_ECONOMY[10].g, COLOR_ECONOMY[10].b, nAlpha);
      }
   }

   public static final Color getTechnologyLevelColor(int nData, float nAlpha) {
      switch (nData / 10) {
         case 0:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[0], COLOR_TECHNOLOGY_LEVEL[1], nData % 10, 10, nAlpha);
         case 1:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[1], COLOR_TECHNOLOGY_LEVEL[2], nData % 10, 10, nAlpha);
         case 2:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[2], COLOR_TECHNOLOGY_LEVEL[3], nData % 10, 10, nAlpha);
         case 3:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[3], COLOR_TECHNOLOGY_LEVEL[4], nData % 10, 10, nAlpha);
         case 4:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[4], COLOR_TECHNOLOGY_LEVEL[5], nData % 10, 10, nAlpha);
         case 5:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[5], COLOR_TECHNOLOGY_LEVEL[6], nData % 10, 10, nAlpha);
         case 6:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[6], COLOR_TECHNOLOGY_LEVEL[7], nData % 10, 10, nAlpha);
         case 7:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[7], COLOR_TECHNOLOGY_LEVEL[8], nData % 10, 10, nAlpha);
         case 8:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[8], COLOR_TECHNOLOGY_LEVEL[9], nData % 10, 10, nAlpha);
         case 9:
            return getColorStep(COLOR_TECHNOLOGY_LEVEL[9], COLOR_TECHNOLOGY_LEVEL[10], nData % 10, 10, nAlpha);
         case 10:
            return new Color(COLOR_TECHNOLOGY_LEVEL[10].r, COLOR_TECHNOLOGY_LEVEL[10].g, COLOR_TECHNOLOGY_LEVEL[10].b, nAlpha);
         default:
            return new Color(COLOR_TECHNOLOGY_LEVEL[10].r, COLOR_TECHNOLOGY_LEVEL[10].g, COLOR_TECHNOLOGY_LEVEL[10].b, nAlpha);
      }
   }

   public static final void initEditdiplomacyColors_GameData() {
      diplomacyColors_GameData = new DiplomacyColors_GameData2();
      diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES = new Color_GameData(0.2F, 0.6F, 1.0F);
      diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR = new Color_GameData(0.8F, 0.0F, 0.0F);
      diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE = new Color_GameData(0.0F, 0.4F, 1.0F);
      diplomacyColors_GameData.COLOR_DIPLOMACY_PACT = new Color_GameData(1.0F, 1.0F, 0.6F);
      diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX = new Color_GameData(0.8F, 0.8F, 0.0F);
      diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL = new Color_GameData(0.28235295F, 0.47843137F, 0.8627451F);
      diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE = new Color_GameData(0.7254902F, 0.28235295F, 0.8627451F);
      diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL = new Color_GameData(0.9411765F, 0.9411765F, 0.9411765F);
      diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS = new Color_GameData(0.9411765F, 0.9411765F, 0.9411765F);
      diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT = new Color_GameData(0.9411765F, 0.9411765F, 0.9411765F);
      Color_GameData[] tempCOLOR_DIPLOMACY_NEGATIVE = new Color_GameData[]{
         new Color_GameData(0.92941177F, 0.627451F, 0.5882353F),
         new Color_GameData(0.89411765F, 0.5568628F, 0.45490196F),
         new Color_GameData(0.85490197F, 0.48235294F, 0.32156864F),
         new Color_GameData(0.8039216F, 0.40784314F, 0.20784314F),
         new Color_GameData(0.77254903F, 0.3647059F, 0.2F),
         new Color_GameData(0.73333335F, 0.3254902F, 0.2F),
         new Color_GameData(0.69411767F, 0.28627452F, 0.2F),
         new Color_GameData(0.654902F, 0.2509804F, 0.2F),
         new Color_GameData(0.62352943F, 0.22352941F, 0.2F),
         new Color_GameData(0.6F, 0.2F, 0.2F)
      };
      diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE = tempCOLOR_DIPLOMACY_NEGATIVE;
      Color_GameData[] tempCOLOR_DIPLOMACY_POSITIVE = new Color_GameData[]{
         new Color_GameData(0.6F, 0.8F, 0.6F),
         new Color_GameData(0.5176471F, 0.7607843F, 0.43137255F),
         new Color_GameData(0.40392157F, 0.70980394F, 0.2627451F),
         new Color_GameData(0.3019608F, 0.654902F, 0.12156863F),
         new Color_GameData(0.20392157F, 0.5921569F, 0.003921569F),
         new Color_GameData(0.14901961F, 0.5647059F, 0.0F),
         new Color_GameData(0.09411765F, 0.5137255F, 0.0F),
         new Color_GameData(0.05490196F, 0.46666667F, 0.0F),
         new Color_GameData(0.023529412F, 0.42745098F, 0.0F),
         new Color_GameData(0.0F, 0.4F, 0.0F)
      };
      diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE = tempCOLOR_DIPLOMACY_POSITIVE;
   }

   public static final void loadDiplomacyColors_GameData(String sTag) {
      try {
         FileHandle file = Gdx.files.internal("game/diplomacy_colors/packages/" + sTag);
         diplomacyColors_GameData = (DiplomacyColors_GameData2)deserialize(file.readBytes());
         return;
      } catch (ClassNotFoundException var2) {
      } catch (IOException var3) {
      }

      initEditdiplomacyColors_GameData();
   }

   public static final Color getRelationColor(int nRelation, float nAlpha) {
      switch (nRelation / 10) {
         case -10:
            return new Color(
               diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
               diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
               diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
               nAlpha
            );
         case -9:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case -8:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case -7:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case -6:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case -5:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case -4:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case -3:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case -2:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case -1:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case 0:
            if (nRelation > 0) {
               return getColorStep(
                  new Color(
                     diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                     diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                     diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                     nAlpha
                  ),
                  new Color(
                     diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getR(),
                     diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getG(),
                     diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getB(),
                     nAlpha
                  ),
                  nRelation % 10,
                  10,
                  nAlpha
               );
            }

            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getB(),
                  nAlpha
               ),
               -nRelation % 10,
               10,
               nAlpha
            );
         case 1:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getB(),
                  nAlpha
               ),
               nRelation % 10,
               10,
               nAlpha
            );
         case 2:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getB(),
                  nAlpha
               ),
               nRelation % 10,
               10,
               nAlpha
            );
         case 3:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getB(),
                  nAlpha
               ),
               nRelation % 10,
               10,
               nAlpha
            );
         case 4:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getB(),
                  nAlpha
               ),
               nRelation % 10,
               10,
               nAlpha
            );
         case 5:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getB(),
                  nAlpha
               ),
               nRelation % 10,
               10,
               nAlpha
            );
         case 6:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getB(),
                  nAlpha
               ),
               nRelation % 10,
               10,
               nAlpha
            );
         case 7:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getB(),
                  nAlpha
               ),
               nRelation % 10,
               10,
               nAlpha
            );
         case 8:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getB(),
                  nAlpha
               ),
               nRelation % 10,
               10,
               nAlpha
            );
         case 9:
            return getColorStep(
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getB(),
                  nAlpha
               ),
               new Color(
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[9].getR(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[9].getG(),
                  diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[9].getB(),
                  nAlpha
               ),
               nRelation % 10,
               10,
               nAlpha
            );
         case 10:
            return new Color(
               diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
               diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
               diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
               nAlpha
            );
         default:
            return new Color(0.0F, 0.0F, 0.0F, ALPHA_DIPLOMACY);
      }
   }

   public static final Color getPactColor(int nNumOfTurns, float nAlpha) {
      return getColorStep(
         new Color(
            diplomacyColors_GameData.COLOR_DIPLOMACY_PACT.getR(),
            diplomacyColors_GameData.COLOR_DIPLOMACY_PACT.getG(),
            diplomacyColors_GameData.COLOR_DIPLOMACY_PACT.getB(),
            nAlpha
         ),
         new Color(
            diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX.getR(),
            diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX.getG(),
            diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX.getB(),
            nAlpha
         ),
         nNumOfTurns,
         40,
         nAlpha
      );
   }

   public static final Color getTruceColor(float nAlpha) {
      return new Color(1.0F, 1.0F, 1.0F, nAlpha);
   }

   public static void updateColorDashed() {
      try {
         COLOR_PROVINCE_DASHED = map.getMapScale().getCurrentScale() > 1.0F
            ? (
               map.getMapScale().getCurrentScale() < 4.0F
                  ? new Color(
                     COLOR_PROVINCE_DASHED.r, COLOR_PROVINCE_DASHED.g, COLOR_PROVINCE_DASHED.b, 0.65F - 0.1F * (map.getMapScale().getCurrentScale() / 4.0F)
                  )
                  : new Color(COLOR_PROVINCE_DASHED.r, COLOR_PROVINCE_DASHED.g, COLOR_PROVINCE_DASHED.b, 0.54999995F)
            )
            : new Color(COLOR_PROVINCE_DASHED.r, COLOR_PROVINCE_DASHED.g, COLOR_PROVINCE_DASHED.b, 0.65F);
      } catch (NullPointerException var1) {
         COLOR_PROVINCE_DASHED = new Color(COLOR_PROVINCE_DASHED.r, COLOR_PROVINCE_DASHED.g, COLOR_PROVINCE_DASHED.b, 0.65F);
      }
   }

   public static final String extraRandomTag() {
      Random oR = new Random();
      String output = "";

      for (int i = 0; i < 8; i++) {
         output = output + (char)(97 + oR.nextInt(26));
      }

      return output;
   }

   public static final String extraRandm_UPDATE_KEY() {
      Random oR = new Random();
      String output = "";

      for (int i = 0; i < 14; i++) {
         output = output + (char)(97 + oR.nextInt(26));
      }

      return output;
   }

   public static final void buildCreateScenario_TechnologyLevelsByContinents() {
      initCreateScenario_TechnologyLevelsByContinents_Civ();

      for (int i = 1; i < game.getCivsSize(); i++) {
         lCreateScenario_TechnologyBContinents.add(new ArrayList<>());
      }
   }

   public static final void addCreateScenario_TechnologyLevelsByContinents_Civ() {
      lCreateScenario_TechnologyBContinents.add(new ArrayList<>());
      Gdx.app.log("AoC", "add: " + lCreateScenario_TechnologyBContinents.size());
   }

   public static final void initCreateScenario_TechnologyLevelsByContinents_Civ() {
      if (lCreateScenario_TechnologyBContinents != null) {
         lCreateScenario_TechnologyBContinents.clear();
         lCreateScenario_TechnologyBContinents = null;
      }

      lCreateScenario_TechnologyBContinents = new ArrayList<>();
   }

   public static final void addCreateScenario_TechnologyLevelsByContinents_Civ(List<Scenario_GameData_Technology> nData) {
      if (nData == null) {
         lCreateScenario_TechnologyBContinents.add(new ArrayList<>());
      } else {
         lCreateScenario_TechnologyBContinents.add(nData);
      }
   }

   public static final void removeCreateScenario_TechnologyLevelsByContinents_Civ(int i) {
      lCreateScenario_TechnologyBContinents.remove(i);
      Gdx.app.log("AoC", "remove: " + lCreateScenario_TechnologyBContinents.size());
   }

   public static final void setCreateScenario_TechnologyLevelsByContinents_Continent(int nCivID, int nContinentID, int nPercentage) {
      for (int i = 0; i < lCreateScenario_TechnologyBContinents.get(nCivID).size(); i++) {
         if (nContinentID == lCreateScenario_TechnologyBContinents.get(nCivID).get(i).getContinentID()) {
            lCreateScenario_TechnologyBContinents.get(nCivID).get(i).setPercentage(nPercentage);
            return;
         }
      }

      lCreateScenario_TechnologyBContinents.get(nCivID).add(new Scenario_GameData_Technology(nContinentID, nPercentage));
   }

   public static final int getCreateScenario_TechnologyLevelsByContinents_Continent(int nCivID, int nContinentID) {
      try {
         for (int i = 0; i < lCreateScenario_TechnologyBContinents.get(nCivID).size(); i++) {
            if (nContinentID == lCreateScenario_TechnologyBContinents.get(nCivID).get(i).getContinentID()) {
               return lCreateScenario_TechnologyBContinents.get(nCivID).get(i).getPercentage();
            }
         }

         return 100;
      } catch (IndexOutOfBoundsException var3) {
         if (LOGS) {
            exceptionStack(var3);
         }

         return 100;
      }
   }

   public static final void addUndoAssignProvinces(int iProvinceID, int iCivID) {
      if (lCreateScenario_UndoAssignProvincesCivID.size() > 499) {
         lCreateScenario_UndoAssignProvincesCivID.remove(0);
      }

      lCreateScenario_UndoAssignProvincesCivID.add(new Undo_AssignProvinceCiv(iProvinceID, iCivID));
      menuManager.setCreate_Scenario_Assign_UndoButton(true);
   }

   public static void removeUndoAssignProvinces() {
      if (lCreateScenario_UndoAssignProvincesCivID.size() > 0) {
         lCreateScenario_UndoAssignProvincesCivID.remove(lCreateScenario_UndoAssignProvincesCivID.size() - 1);
      }

      if (lCreateScenario_UndoAssignProvincesCivID.size() == 0) {
         menuManager.setCreate_Scenario_Assign_UndoButton(false);
      }
   }

   public static final boolean canFormACiv(int nCivID, String nCivTag, boolean bDisposeData) {
      if (!doesNotExists_FormableCiv(nCivTag)) {
         return false;
      } else if (!game.isAtPeace(nCivID)) {
         return false;
      } else if (game.getCiv(nCivID).getMoney() < game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getSanboxMovePoints()) {
         return false;
      } else if (game.getCiv(nCivID).getDiplomacyPoints() < 24) {
         return false;
      } else if (game.getCiv(nCivID).getCivID() != game.getCiv(nCivID).getPuppetOfCivID()) {
         return false;
      } else {
         if (bDisposeData) {
            loadFormableCiv_GameData(nCivTag);
         }

         if (!ownAllProvinces_FormableCiv(nCivID)) {
            if (bDisposeData) {
               formableCivs_GameData = null;
            }

            return false;
         } else {
            if (bDisposeData) {
               formableCivs_GameData = null;
            }

            return true;
         }
      }
   }

   public static final void createUnion(int nCivA, int nCivB) {
      if (nCivA != nCivB && nCivA > 0 && nCivB > 0 && nCivA < game.getCivsSize() && nCivB < game.getCivsSize() && !game.getCivsAtWar(nCivA, nCivB)) {
         Gdx.app.log("AoC", "createUnion: 000000");
         if (!game.getCiv(nCivA).getControlledByPlayer()
            && (game.getCiv(nCivB).getControlledByPlayer() || game.getCiv(nCivA).getNumOfProvinces() < game.getCiv(nCivB).getNumOfProvinces())) {
            int tempD = nCivA;
            nCivA = nCivB;
            nCivB = tempD;
         }

         Gdx.app.log("AoC", "createUnion: 111111");
         int i2 = 0;

         while (i2 < game.getCiv(nCivB).getNumOfProvinces()) {
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getCore().addNewCore(nCivA, Game_Calendar.TURN_ID);

            for (int j = 0; j < game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().getNationalitiesSize(); j++) {
               if (game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().getCivID(j) == nCivB) {
                  game.getProvince(game.getCiv(nCivB).getProvinceID(i2))
                     .getPopulationData()
                     .setPopulationOfCivID(
                        nCivA,
                        game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().getPopulationOfCivID(nCivA)
                           + game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().getPopulationOfCivID(nCivB)
                     );
                  game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().setPopulationOfCivID(nCivB, 0);
               }
            }

            int nProvID = game.getCiv(nCivB).getProvinceID(i2);
            int nArmyA = game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getArmyCivID(nCivA);
            int nArmyB = game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getArmyCivID(nCivB);
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).updateArmy(nCivA, 0);
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).updateArmy(nCivB, 0);
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).setTrueOwnerOfProvince(nCivA);
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).setCivID(nCivA, false);
            game.getProvince(nProvID).updateArmy(nCivA, nArmyA + nArmyB);
         }

         Gdx.app.log("AoC", "createUnion: 2222");
         String nUnionTag = unionsManager.getUnionTag(game.getCiv(nCivA).getCivTag() + ";" + game.getCiv(nCivB).getCivTag());
         boolean generateFlag = false;
         if (nUnionTag.length() == 0) {
            nUnionTag = game.getCiv(nCivA).getCivTag() + ";" + game.getCiv(nCivB).getCivTag();
            generateFlag = true;
            game.getCiv(nCivA).setR((int)(game.getCiv(nCivA).getR() / 2.0F + game.getCiv(nCivB).getR() / 2.0F));
            game.getCiv(nCivA).setG((int)(game.getCiv(nCivA).getG() / 2.0F + game.getCiv(nCivB).getG() / 2.0F));
            game.getCiv(nCivA).setB((int)(game.getCiv(nCivA).getB() / 2.0F + game.getCiv(nCivB).getB() / 2.0F));
            game.getCiv(nCivA).setCivTag(nUnionTag);
         } else {
            game.getCiv(nCivA).setCivTag(nUnionTag);
            palletManager.loadCivilizationStandardColor(nCivA);
         }

         Gdx.app.log("AoC", "createUnion: 3333");

         for (int i = 1; i < game.getCivsSize(); i++) {
            if (game.getCiv(i).getPuppetOfCivID() == nCivB && nCivB != i) {
               game.getCiv(i).setPuppetOfCivID(nCivA);
            }
         }

         if (game.getActiveProvinceID() >= 0) {
            int tD = game.getActiveProvinceID();
            game.setActiveProvinceID(-1);
            game.setActiveProvinceID(tD);
         }

         if (game.getCiv(nCivB).getAllianceID() > 0) {
            game.getAlliance(game.getCiv(nCivB).getAllianceID()).removeCivilization(nCivB);
            game.getCiv(nCivB).setAllianceID(0);
         }

         game.buildCivilizationRegions(nCivA);
         Gdx.app.log("AoC", "createUnion: 444");

         for (int var14 = 0; var14 < game.getCiv(nCivA).getNumOfProvinces(); var14++) {
            game.getProvince(game.getCiv(nCivA).getProvinceID(var14)).setFromCivID(0);
         }

         for (int var15 = 0; var15 < game.getCiv(nCivB).getArmyInAnotherProvinceSize(); var15++) {
            game.getProvince(game.getCiv(nCivB).getArmyInAnotherProvince(var15))
               .updateArmy(
                  nCivA,
                  game.getProvince(game.getCiv(nCivB).getArmyInAnotherProvince(var15)).getArmyCivID(nCivA)
                     + game.getProvince(game.getCiv(nCivB).getArmyInAnotherProvince(var15)).getArmyCivID(nCivB)
               );
            game.getProvince(game.getCiv(nCivB).getArmyInAnotherProvince(var15)).updateArmy(nCivB, 0);
         }

         game.getCiv(nCivA).setNumOfUnits(0);
         game.getCiv(nCivB).setNumOfUnits(0);
         game.getCiv(nCivA).buildNumOfUnits();
         Gdx.app.log("AoC", "createUnion: 5555");
         if (game.getPlayerID_ByCivID(nCivB) >= 0) {
            game.removePlayer(game.getPlayerID_ByCivID(nCivB));
            game.getCiv(nCivB).setControlledByPlayer(false);
            PLAYER_TURNID = game.getPlayerID_ByCivID(nCivA);
         }

         Gdx.app.log("AoC", "createUnion: 6666");

         for (int var16 = 0; var16 < game.getCiv(nCivB).getMoveUnitsSize(); var16++) {
            game.getCiv(nCivA)
               .newMove(
                  game.getCiv(nCivB).getMoveUnits(var16).getFromProvinceID(),
                  game.getCiv(nCivB).getMoveUnits(var16).getToProvinceID(),
                  game.getCiv(nCivB).getMoveUnits(var16).getNumOfUnits(),
                  true
               );
         }

         for (int var17 = 0; var17 < game.getCiv(nCivB).getMoveUnitsPlunderSize(); var17++) {
            game.getCiv(nCivA)
               .newPlunder(game.getCiv(nCivB).getMoveUnits_Plunder(var17).getFromProvinceID(), game.getCiv(nCivB).getMoveUnits_Plunder(var17).getNumOfUnits());
         }

         for (int var18 = 0; var18 < game.getCiv(nCivB).getRecruitArmySize(); var18++) {
            game.getCiv(nCivA).recruitArmy(game.getCiv(nCivB).getRecruitArmy(var18).getProvinceID(), game.getCiv(nCivB).getRecruitArmy(var18).getArmy());
         }

         for (int var19 = 0; var19 < game.getCiv(nCivB).getConstructionsSize(); var19++) {
            game.getCiv(nCivA).addNewConstruction(game.getCiv(nCivB).getConstruction(var19));
         }

         Gdx.app.log("AoC", "createUnion: 7777");
         game.getCiv(nCivB).clearConstructions();
         game.getCiv(nCivB).clearMoveUnits();
         game.getCiv(nCivB).clearMoveUnits_Plunder();
         game.getCiv(nCivB).clearRegroupArmy();
         game.getCiv(nCivB).clearRecruitArmy();
         game.getCiv(nCivA).setMoney(game.getCiv(nCivA).getMoney() + game.getCiv(nCivB).getMoney());
         game.getCiv(nCivB).setMoney(0L);
         gameNewGame.updateFormableCivilizations(nCivA);
         gameNewGame.updateFormableCivilizations(nCivB);
         if (game.getCiv(nCivB).getCapitalProvinceID() >= 0) {
            for (int k = 0; k < game.getProvince(game.getCiv(nCivB).getCapitalProvinceID()).getCitiesSize(); k++) {
               if (game.getProvince(game.getCiv(nCivB).getCapitalProvinceID()).getCity(k).getCityLevel() == getEditorCityLevel(0)) {
                  game.getProvince(game.getCiv(nCivB).getCapitalProvinceID()).getCity(k).setCityLevel(getEditorCityLevel(1));
               }
            }

            game.getProvince(game.getCiv(nCivB).getCapitalProvinceID()).setIsCapital(false);
         }

         Gdx.app.log("AoC", "createUnion: 8888");

         for (int var20 = 1; var20 < game.getCivsSize(); var20++) {
            if (var20 != nCivB && var20 != nCivA && game.getCiv(var20).getNumOfProvinces() > 0) {
               if (game.getCivsAtWar(var20, nCivB)) {
                  int nWarID = game.getWarID(var20, nCivB);
                  if (nWarID >= 0 && nWarID < game.getWarsSize()) {
                     if (game.getCivsAtWar(var20, nCivA)) {
                        game.getWar(nWarID).updateAfterUnion(nCivA, nCivB);
                     } else {
                        game.war_CheckDiplomacy(var20, nCivA);
                        game.setCivRelation_OfCivB(var20, nCivA, -100.0F);
                        game.setCivRelation_OfCivB(nCivA, var20, -100.0F);
                        game.getWar(nWarID).updateAfterUnion(nCivA, nCivB);
                     }
                  }
               } else if (!game.getCivsAtWar(var20, nCivA)) {
                  game.setCivRelation_OfCivB(nCivA, var20, (game.getCivRelation_OfCivB(nCivA, var20) + game.getCivRelation_OfCivB(nCivB, var20)) / 2.0F);
                  game.setCivRelation_OfCivB(var20, nCivA, (game.getCivRelation_OfCivB(var20, nCivA) + game.getCivRelation_OfCivB(var20, nCivB)) / 2.0F);
               }
            }
         }

         Gdx.app.log("AoC", "createUnion: 9999");
         if (!game.getCiv(nCivA).getControlledByPlayer()) {
            game.getCiv(nCivA).buildCivPersonality();
         }

         for (int var21 = 0; var21 < game.getCiv(nCivB).getLoansSize(); var21++) {
            game.getCiv(nCivA).addLoan(game.getCiv(nCivB).getLoan(var21).iGoldPerTurn, game.getCiv(nCivB).getLoan(var21).iTurnsLeft);
         }

         game.getCiv(nCivB).clearLoans();

         for (int var22 = game.getCiv(nCivB).getFestivalsSize() - 1; var22 >= 0; var22--) {
            game.getCiv(nCivA).addFestival(game.getCiv(nCivB).getFestival(var22));
            game.getCiv(nCivB).removeFestival(var22);
         }

         for (int var23 = game.getCiv(nCivB).getAssimilatesSize() - 1; var23 >= 0; var23--) {
            game.getCiv(nCivA).addAssimilate(game.getCiv(nCivB).getAssimilate(var23));
            game.getCiv(nCivB).removeAssimilate(var23);
         }

         for (int var24 = game.getCiv(nCivB).getInvestsSize() - 1; var24 >= 0; var24--) {
            game.getCiv(nCivA).addInvest(game.getCiv(nCivB).getInvest(var24));
            game.getCiv(nCivB).removeInvest(var24);
         }

         if ((game.getPlayer(PLAYER_TURNID).getCivID() == nCivA || game.getPlayer(PLAYER_TURNID).getCivID() == nCivB) && FOG_OF_WAR > 0) {
            for (int var25 = 0; var25 < game.getProvincesSize(); var25++) {
               game.getProvince(var25).updateDrawArmy();
            }
         }

         Gdx.app.log("AoC", "createUnion: 10");

         try {
            if (holyRomanEmpire_Manager.holyRomanEmpire.getIsEmperor(nCivB)) {
               holyRomanEmpire_Manager.holyRomanEmpire.setEmperor(nCivA);
            }
         } catch (NullPointerException var12) {
            exceptionStack(var12);
         } catch (IndexOutOfBoundsException var13) {
            exceptionStack(var13);
         }

         Gdx.app.log("AoC", "createUnion: 11");
         gameAction.buildRank_Score(nCivA);
         gameAction.buildRank_Score(nCivB);
         gameAction.buildRank_Positions();
         Gdx.app.log("AoC", "createUnion: 12");
         if (game.getPlayer(PLAYER_TURNID).getCivID() == nCivA || game.getPlayer(PLAYER_TURNID).getCivID() == nCivB) {
            menuManager.updateInGame_TOP_All(game.getPlayer(PLAYER_TURNID).getCivID());
         }

         Gdx.app.log("AoC", "createUnion: 13");
         if (gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
            setActiveCivInfo(getActiveCivInfo());
         }

         Gdx.app.log("AoC", "createUnion: 14");
         if (gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
            game.getCiv(nCivA).loadFlag();
         } else {
            unionFlagsToGenerate_Manager.addFlagToLoad(nCivA);
         }

         Gdx.app.log("AoC", "createUnion: 15");
         if (generateFlag) {
            for (int i3 = 0; i3 < game.getPlayersSize(); i3++) {
               if (game.getPlayer(i3).getCivID() == nCivA || game.getPlayer(i3).getCivID() == nCivB) {
                  unionFlagsToGenerate_Manager.lFlags.add(new UnionFlagsToGenerate());
                  int tGenerateID = unionFlagsToGenerate_Manager.lFlags.size() - 1;
                  String[] tempD = game.getCiv(game.getPlayer(i3).getCivID()).getCivTag().split(";");

                  for (int jx = 0; jx < tempD.length; jx++) {
                     unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).lTags.add(tempD[jx]);
                  }

                  unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).typeOfAction = UnionFlagsToGenerate_TypesOfAction.PLAYER_ID;
                  unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).iID = game.getPlayer(i3).getCivID();
               }
            }
         } else {
            for (int i4 = 0; i4 < game.getPlayersSize(); i4++) {
               if (game.getPlayer(i4).getCivID() == nCivA || game.getPlayer(i4).getCivID() == nCivB) {
                  game.getPlayer(i4).loadPlayersFlag();
               }
            }
         }

         Gdx.app.log("AoC", "createUnion: 16");

         try {
            if (holyRomanEmpire_Manager.getHRE().getEmperor() == nCivB) {
               holyRomanEmpire_Manager.getHRE().addPrince(nCivA);
               holyRomanEmpire_Manager.getHRE().setEmperor(nCivA);
            }
         } catch (NullPointerException var10) {
         } catch (IndexOutOfBoundsException var11) {
         }

         if ((game.getCiv(nCivA).getControlledByPlayer() || game.getCiv(nCivB).getControlledByPlayer()) && isDesktop()) {
         }

         historyManager.addHistoryLog(new HistoryLog_Union(nCivA));
         Gdx.app.log("AoC", "createUnion: END");
      }
   }

   public static final void formCiv(int nCivID) {
      if (canFormACiv(nCivID, formableCivs_GameData.getFormableCivTag(), false)) {
         game.getCiv(nCivID).clearTagsCanForm();
         game.getCiv(nCivID).setCivTag(formableCivs_GameData.getFormableCivTag());
         game.getCiv(nCivID).setCivName(langManager.getCiv(game.getCiv(nCivID).getCivTag()));
         game.getCiv(nCivID).loadFlag();

         for (int i = 0; i < game.getCiv(nCivID).getCivRegionsSize(); i++) {
            game.getCiv(nCivID).getCivRegion(i).buildScaleOfText();
         }

         if (game.getProvince(formableCivs_GameData.getCapitalProvinceID()).getWasteland() < 0
            && !game.getProvince(formableCivs_GameData.getCapitalProvinceID()).getSeaProvince()
            && formableCivs_GameData.getCapitalProvinceID() != game.getCiv(nCivID).getCapitalProvinceID()) {
            if (game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
               for (int k = 0; k < game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCitiesSize(); k++) {
                  if (game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCity(k).getCityLevel() == getEditorCityLevel(0)) {
                     game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCity(k).setCityLevel(getEditorCityLevel(1));
                  }
               }

               game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(false);
            }

            game.getCiv(nCivID).setCapitalProvinceID(formableCivs_GameData.getCapitalProvinceID());
            game.getProvince(formableCivs_GameData.getCapitalProvinceID()).setIsCapital(true);
            if (game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
               game.getCiv(nCivID).setCoreCapitalProvinceID(game.getCiv(nCivID).getCapitalProvinceID());
               if (game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
                  game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).setCityLevel(getEditorCityLevel(0));
               }
            }
         }

         game.getCiv(nCivID).updateCivilizationIdeology();
         game.getCiv(nCivID).setMoney(game.getCiv(nCivID).getMoney() - 1000L);
         game.getCiv(nCivID).setDiplomacyPoints(game.getCiv(nCivID).getDiplomacyPoints() - 24);

         try {
            try {
               try {
                  FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + formableCivs_GameData.getFormableCivTag());
                  Civilization_GameData3 tempCivData = (Civilization_GameData3)deserialize(fileCiv.readBytes());
                  game.getCiv(nCivID).setR(tempCivData.getR());
                  game.getCiv(nCivID).setG(tempCivData.getG());
                  game.getCiv(nCivID).setB(tempCivData.getB());
               } catch (GdxRuntimeException var11) {
                  FileHandle fileCivx = Gdx.files.internal("game/civilizations/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()));
                  Civilization_GameData3 tempCivDatax = (Civilization_GameData3)deserialize(fileCivx.readBytes());
                  int tempIdeologyID = ideologiesManager.getIdeologyID(formableCivs_GameData.getFormableCivTag());
                  Color tempColor = getColorMixed(
                     new Color(tempCivDatax.getR() / 255.0F, tempCivDatax.getG() / 255.0F, tempCivDatax.getB() / 255.0F, 0.775F),
                     new Color(
                        ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                        ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                        ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                        0.225F
                     )
                  );
                  game.getCiv(nCivID).setR((int)(tempColor.r * 255.0F));
                  game.getCiv(nCivID).setG((int)(tempColor.g * 255.0F));
                  game.getCiv(nCivID).setB((int)(tempColor.b * 255.0F));
               }
            } catch (GdxRuntimeException var12) {
               try {
                  FileHandle fileCiv = Gdx.files.local("game/civilizations/" + formableCivs_GameData.getFormableCivTag());
                  Civilization_GameData3 tempCivData = (Civilization_GameData3)deserialize(fileCiv.readBytes());
                  game.getCiv(nCivID).setR(tempCivData.getR());
                  game.getCiv(nCivID).setG(tempCivData.getG());
                  game.getCiv(nCivID).setB(tempCivData.getB());
               } catch (GdxRuntimeException var10) {
                  try {
                     FileHandle fileCivx = Gdx.files.local("game/civilizations/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()));
                     Civilization_GameData3 tempCivDatax = (Civilization_GameData3)deserialize(fileCivx.readBytes());
                     int tempIdeologyID = ideologiesManager.getIdeologyID(formableCivs_GameData.getFormableCivTag());
                     Color tempColor = getColorMixed(
                        new Color(tempCivDatax.getR() / 255.0F, tempCivDatax.getG() / 255.0F, tempCivDatax.getB() / 255.0F, 0.775F),
                        new Color(
                           ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                           ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                           ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                           0.225F
                        )
                     );
                     game.getCiv(nCivID).setR((int)(tempColor.r * 255.0F));
                     game.getCiv(nCivID).setG((int)(tempColor.g * 255.0F));
                     game.getCiv(nCivID).setB((int)(tempColor.b * 255.0F));
                  } catch (GdxRuntimeException var9) {
                     try {
                        if (isAndroid()) {
                           try {
                              FileHandle fileCivxx = Gdx.files
                                 .local(
                                    "game/civilizations_editor/"
                                       + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag())
                                       + "/"
                                       + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag())
                                 );
                              Civilization_GameData3 tempCivDataxx = (Civilization_GameData3)deserialize(fileCivxx.readBytes());
                              game.getCiv(nCivID).setR(tempCivDataxx.getR());
                              game.getCiv(nCivID).setG(tempCivDataxx.getG());
                              game.getCiv(nCivID).setB(tempCivDataxx.getB());
                           } catch (GdxRuntimeException var7) {
                              FileHandle fileCivxxx = Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag())
                                       + "/"
                                       + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag())
                                 );
                              Civilization_GameData3 tempCivDataxxx = (Civilization_GameData3)deserialize(fileCivxxx.readBytes());
                              game.getCiv(nCivID).setR(tempCivDataxxx.getR());
                              game.getCiv(nCivID).setG(tempCivDataxxx.getG());
                              game.getCiv(nCivID).setB(tempCivDataxxx.getB());
                           }
                        } else {
                           FileHandle fileCivxx = Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag())
                                    + "/"
                                    + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag())
                              );
                           Civilization_GameData3 tempCivDataxx = (Civilization_GameData3)deserialize(fileCivxx.readBytes());
                           game.getCiv(nCivID).setR(tempCivDataxx.getR());
                           game.getCiv(nCivID).setG(tempCivDataxx.getG());
                           game.getCiv(nCivID).setB(tempCivDataxx.getB());
                        }
                     } catch (GdxRuntimeException var8) {
                     }
                  }
               }
            }
         } catch (ClassNotFoundException var13) {
            if (LOGS) {
               exceptionStack(var13);
            }
         } catch (IOException var14) {
            if (LOGS) {
               exceptionStack(var14);
            }
         }

         if (game.getCiv(nCivID).getControlledByPlayer() && isDesktop()) {
         }

         gameNewGame.updateFormableCivilizations(nCivID);

         for (int i = 0; i < game.getCiv(nCivID).getNumOfProvinces(); i++) {
            game.getProvince(game.getCiv(nCivID).getProvinceID(i)).setFromCivID(0);
         }
      }
   }

   public static final void loadFormableCiv_GameData(String nCivTag) {
      try {
         try {
            FileHandle file = Gdx.files.local("map/" + map.getFile_ActiveMap_Path() + "formable_civs/" + nCivTag);
            formableCivs_GameData = (FormableCivs_GameData)deserialize(file.readBytes());
         } catch (GdxRuntimeException var3) {
            FileHandle filex = Gdx.files.internal("map/" + map.getFile_ActiveMap_Path() + "formable_civs/" + nCivTag);
            formableCivs_GameData = (FormableCivs_GameData)deserialize(filex.readBytes());
         }
      } catch (ClassNotFoundException var4) {
         if (LOGS) {
            exceptionStack(var4);
         }
      } catch (IOException var5) {
         if (LOGS) {
            exceptionStack(var5);
         }
      }
   }

   public static final boolean doesNotExists_FormableCiv(String nCivTag) {
      for (int i = 1; i < game.getCivsSize(); i++) {
         if (nCivTag.equals(game.getCiv(i).getCivTag())) {
            return false;
         }
      }

      return true;
   }

   public static final boolean ownAllProvinces_FormableCiv(int nCivID) {
      for (int i = 0; i < formableCivs_GameData.getProvincesSize(); i++) {
         if (game.getProvince(formableCivs_GameData.getProvinceID(i)).getWasteland() < 0
            && game.getProvince(formableCivs_GameData.getProvinceID(i)).getCivID() != nCivID) {
            return false;
         }
      }

      return true;
   }

   public static final boolean isInTheGame(String nCivTag) {
      for (int i = 1; i < game.getCivsSize(); i++) {
         if (nCivTag.equals(game.getCiv(i).getCivTag())) {
            return true;
         }
      }

      return false;
   }

   public static final boolean isInTheGame_Or_IsFormableCiv(String nCivTag) {
      for (int i = 1; i < game.getCivsSize(); i++) {
         if (nCivTag.equals(game.getCiv(i).getCivTag())) {
            return true;
         }
      }

      for (int var3 = 1; var3 < game.getCivsSize(); var3++) {
         for (int j = 0; j < game.getCiv(var3).getTagsCanFormSize(); j++) {
            if (nCivTag.equals(game.getCiv(var3).getTagsCanForm(j))) {
               return true;
            }
         }
      }

      return false;
   }

   public static final boolean isInFormableCivs(String nCivTag) {
      if (formableCivs_GameData.getFormableCivTag() != null && formableCivs_GameData.getFormableCivTag().equals(nCivTag)) {
         return true;
      } else {
         for (int i = 0; i < formableCivs_GameData.getClaimantsSize(); i++) {
            if (nCivTag.equals(formableCivs_GameData.getClaimant(i))) {
               return true;
            }
         }

         return false;
      }
   }

   public static final boolean isInLeaderCivs(String nCivTag) {
      for (int i = 0; i < leader_GameData.getCivsSize(); i++) {
         if (nCivTag.equals(leader_GameData.getCiv(i))) {
            return true;
         }
      }

      return false;
   }

   public static final void addUndoWastelandProvince(int iProvinceID) {
      if (lCreateScenario_UndoWastelandProvinces.size() > 99) {
         lCreateScenario_UndoWastelandProvinces.remove(0);
      }

      lCreateScenario_UndoWastelandProvinces.add(iProvinceID);
      if (menuManager.getInCreateScenario_Available_Provinces()) {
         menuManager.setCreate_Scenario_AvailableProvinces_UndoButton(true);
      } else if (menuManager.getInMapEditor_WastelandMaps_Edit()) {
         menuManager.setMapEditor_WastelandMaps_Edit_UndoButton(true);
      }
   }

   public static void removeUndoWastelandProvince() {
      if (lCreateScenario_UndoWastelandProvinces.size() > 0) {
         lCreateScenario_UndoWastelandProvinces.remove(lCreateScenario_UndoWastelandProvinces.size() - 1);
      }

      if (lCreateScenario_UndoWastelandProvinces.size() == 0) {
         if (menuManager.getInCreateScenario_Available_Provinces()) {
            menuManager.setCreate_Scenario_AvailableProvinces_UndoButton(false);
         } else if (menuManager.getInMapEditor_WastelandMaps_Edit()) {
            menuManager.setMapEditor_WastelandMaps_Edit_UndoButton(false);
         }
      }
   }

   public static final void updateNumOfAvailableProvinces() {
      iNumOfWastelandProvinces = 0;
      iNumOfAvailableProvinces = 0;

      for (int i = 0; i < game.getProvincesSize(); i++) {
         if (!game.getProvince(i).getSeaProvince()) {
            if (game.getProvince(i).getWasteland() >= 0) {
               iNumOfWastelandProvinces++;
            } else {
               iNumOfAvailableProvinces++;
            }
         }
      }

      glyphLayout.setText(fontMain, "" + iNumOfAvailableProvinces);
      iNumOfAvailableProvincesWidth = (int)glyphLayout.width;
      glyphLayout.setText(fontMain, "" + iNumOfWastelandProvinces);
      iNumOfWastelandProvincesWidth = (int)glyphLayout.width;
   }

   public static final void resetManageDiplomacyIDs() {
      MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
      MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
      MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = -1;
      MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = 1;
      MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 = 0;
   }

   public static final void loadFontMain() {
      if (fontMain != null) {
         fontMain.dispose();
         fontMain = null;
      }

      String sFont;
      if ((sFont = langManager.get("font")).equals("font")) {
         sFont = "rbold.ttf";
      }

      loadedRobotoFont = sFont.equals("rbold.ttf");
      FreeTypeFontGenerator genarator = null;

      try {
         genarator = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/" + sFont));
      } catch (GdxRuntimeException var3) {
         genarator = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/rbold.ttf"));
      }

      FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
      Gdx.app.log("AoC", langManager.get("charset"));
      params.characters = langManager.get("charset");
      params.size = Math.max(settingsManager.FONT_MAIN_SIZE, 6);
      params.color = Color.WHITE;
      params.minFilter = Texture.TextureFilter.Linear;
      params.magFilter = Texture.TextureFilter.Linear;
      fontMain = genarator.generateFont(params);
      genarator.dispose();
      glyphLayout.setText(fontMain, "AyӏdZOP38901ERLj");
      TEXT_HEIGHT = (int)glyphLayout.height;
      settingsManager.updateCitiesFontScale();
   }

   public static final void loadFontArmy() {
      if (fontArmy != null) {
         fontArmy.dispose();
         fontArmy = null;
      }

      String sFont;
      if ((sFont = langManager.get("fontArmy")).equals("fontArmy")) {
         sFont = "rbold.ttf";
      }

      FreeTypeFontGenerator genaratorArmy = null;

      try {
         genaratorArmy = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/" + sFont));
      } catch (GdxRuntimeException var3) {
         genaratorArmy = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/rbold.ttf"));
      }

      FreeTypeFontGenerator.FreeTypeFontParameter paramsArmy = new FreeTypeFontGenerator.FreeTypeFontParameter();
      paramsArmy.size = Math.max(settingsManager.FONT_ARMY_SIZE, 6);
      paramsArmy.color = Color.WHITE;
      paramsArmy.minFilter = Texture.TextureFilter.Linear;
      paramsArmy.magFilter = Texture.TextureFilter.Linear;
      paramsArmy.characters = "0123456789+-.,%?!";
      fontArmy = genaratorArmy.generateFont(paramsArmy);
      genaratorArmy.dispose();
      glyphLayout.setText(fontArmy, "-+1234567890");
      ARMY_HEIGHT = (int)glyphLayout.height;
   }

   public static final void loadFontBorder() {
      if (fontBorder != null) {
         fontBorder.dispose();
         fontBorder = null;
      }

      String sFont;
      if ((sFont = langManager.get("font2")).equals("font2")) {
         sFont = "rbold.ttf";
      }

      FreeTypeFontGenerator genarator = null;

      try {
         genarator = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/" + sFont));
      } catch (GdxRuntimeException var3) {
         genarator = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/rbold.ttf"));
      }

      FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
      params.characters = langManager.get("charset");
      params.size = settingsManager.FONT_BORDER_SIZE;
      params.color = new Color(
         settingsManager.civNamesFontColor.getR(),
         settingsManager.civNamesFontColor.getG(),
         settingsManager.civNamesFontColor.getB(),
         settingsManager.civNamesFontColor_ALPHA
      );
      params.minFilter = Texture.TextureFilter.Linear;
      params.magFilter = Texture.TextureFilter.Linear;
      params.borderColor = new Color(
         settingsManager.civNamesFontColorBorder.getR(),
         settingsManager.civNamesFontColorBorder.getG(),
         settingsManager.civNamesFontColorBorder.getB(),
         settingsManager.civNamesFontColorBorder_ALPHA
      );
      params.borderWidth = settingsManager.FONT_BORDER_WIDTH_OF_BORDER;
      fontBorder = genarator.generateFont(params);
      genarator.dispose();
   }

   public static final void drawText(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color) {
      try {
         fontMain.setColor(color);
         fontMain.draw(oSB, sText, (float)nPosX, (float)(-nPosY));
      } catch (NullPointerException var6) {
      } catch (IndexOutOfBoundsException var7) {
      }
   }

   public static final void drawTextBorder(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color) {
      try {
         fontBorder.setColor(color);
         fontBorder.draw(oSB, sText, (float)nPosX, (float)(-nPosY));
      } catch (NullPointerException var6) {
         if (LOGS) {
            exceptionStack(var6);
         }
      } catch (IndexOutOfBoundsException var7) {
         if (LOGS) {
            exceptionStack(var7);
         }
      }
   }

   public static final void drawTextWithShadow(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color) {
      try {
         fontMain.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
         fontMain.draw(oSB, sText, (float)(nPosX - 1), (float)(-nPosY - 1));
         fontMain.setColor(color);
         fontMain.draw(oSB, sText, (float)nPosX, (float)(-nPosY));
      } catch (NullPointerException var6) {
      } catch (IndexOutOfBoundsException var7) {
      }
   }

   public static final void drawTextWithShadowRotated(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color, float rotate) {
      Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();

      try {
         Matrix4 mx4Font = new Matrix4();
         mx4Font.rotate(new Vector3(0.0F, 0.0F, 1.0F), rotate);
         mx4Font.trn(nPosX, -nPosY, 0.0F);
         oSB.setTransformMatrix(mx4Font);
         fontMain.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
         fontMain.draw(oSB, sText, -1.0F, -1.0F);
         fontMain.setColor(color);
         fontMain.draw(oSB, sText, 0.0F, 0.0F);
      } catch (NullPointerException var12) {
         if (LOGS) {
            exceptionStack(var12);
         }
      } catch (IndexOutOfBoundsException var13) {
         if (LOGS) {
            exceptionStack(var13);
         }
      } finally {
         oSB.setTransformMatrix(oldTransformMatrix);
      }
   }

   public static final void drawTextRotated(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color, float rotate) {
      Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();

      try {
         Matrix4 mx4Font = new Matrix4();
         mx4Font.rotate(new Vector3(0.0F, 0.0F, 1.0F), rotate);
         mx4Font.trn(nPosX, -nPosY, 0.0F);
         oSB.setTransformMatrix(mx4Font);
         fontMain.setColor(color);
         fontMain.draw(oSB, sText, 0.0F, 0.0F);
      } catch (NullPointerException var12) {
         if (LOGS) {
            exceptionStack(var12);
         }
      } catch (IndexOutOfBoundsException var13) {
         if (LOGS) {
            exceptionStack(var13);
         }
      } finally {
         oSB.setTransformMatrix(oldTransformMatrix);
      }
   }

   public static final void drawTextRotatedBorder(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color, float rotate) {
      Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();

      try {
         Matrix4 mx4Font = new Matrix4();
         mx4Font.rotate(new Vector3(0.0F, 0.0F, 1.0F), rotate);
         mx4Font.trn(nPosX, -nPosY, 0.0F);
         oSB.setTransformMatrix(mx4Font);
         fontBorder.setColor(color);
         fontBorder.draw(oSB, sText, 0.0F, 0.0F);
      } catch (NullPointerException var12) {
         if (LOGS) {
            exceptionStack(var12);
         }
      } catch (IndexOutOfBoundsException var13) {
         if (LOGS) {
            exceptionStack(var13);
         }
      } finally {
         oSB.setTransformMatrix(oldTransformMatrix);
      }
   }

   public static final void drawArmyText(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color) {
      try {
         fontArmy.setColor(color);
         fontArmy.draw(oSB, sText, (float)nPosX, (float)(-nPosY));
      } catch (NullPointerException var6) {
         if (LOGS) {
            exceptionStack(var6);
         }
      } catch (IndexOutOfBoundsException var7) {
         if (LOGS) {
            exceptionStack(var7);
         }
      }
   }

   public static final void drawArmyText_WithShadow(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color) {
      try {
         fontArmy.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
         fontArmy.draw(oSB, sText, (float)(nPosX - 1), (float)(-nPosY - 1));
         fontArmy.setColor(color);
         fontArmy.draw(oSB, sText, (float)nPosX, (float)(-nPosY));
      } catch (NullPointerException var6) {
         if (LOGS) {
            exceptionStack(var6);
         }
      } catch (IndexOutOfBoundsException var7) {
         if (LOGS) {
            exceptionStack(var7);
         }
      }
   }

   public static final int getDarker(int iColor, int iMod) {
      return Math.round((float)Math.max(0, iColor - iMod));
   }

   public static final Color getDarker(Color nColor, int iMod, float nAlpha) {
      return new Color(
         Math.round(Math.max(0.0F, nColor.r * 255.0F - iMod) / 255.0F),
         Math.round(Math.max(0.0F, nColor.g * 255.0F - iMod) / 255.0F),
         Math.round(Math.max(0.0F, nColor.b * 255.0F - iMod) / 255.0F),
         nAlpha
      );
   }

   public static final float getColorStep(int iOld, int iNew, int iColorStepID, int numOfSteps) {
      return (iOld + (iOld > iNew ? (float)((iNew - iOld) * iColorStepID) / numOfSteps : (float)((iNew - iOld) * iColorStepID) / numOfSteps)) / 255.0F;
   }

   public static final Color getColorStep(Color iOld, Color iNew, int iColorStepID, int numOfSteps, float fAlpha) {
      return new Color(
         iOld.r + (iOld.r > iNew.r ? (iNew.r - iOld.r) * iColorStepID / numOfSteps : (iNew.r - iOld.r) * iColorStepID / numOfSteps),
         iOld.g + (iOld.g > iNew.g ? (iNew.g - iOld.g) * iColorStepID / numOfSteps : (iNew.g - iOld.g) * iColorStepID / numOfSteps),
         iOld.b + (iOld.b > iNew.b ? (iNew.b - iOld.b) * iColorStepID / numOfSteps : (iNew.b - iOld.b) * iColorStepID / numOfSteps),
         fAlpha
      );
   }

   public static final Color getColorStep_WithAlpha(Color iOld, Color iNew, int iColorStepID, int numOfSteps) {
      return new Color(
         iOld.r + (iOld.r > iNew.r ? (iNew.r - iOld.r) * iColorStepID / numOfSteps : (iNew.r - iOld.r) * iColorStepID / numOfSteps),
         iOld.g + (iOld.g > iNew.g ? (iNew.g - iOld.g) * iColorStepID / numOfSteps : (iNew.g - iOld.g) * iColorStepID / numOfSteps),
         iOld.b + (iOld.b > iNew.b ? (iNew.b - iOld.b) * iColorStepID / numOfSteps : (iNew.b - iOld.b) * iColorStepID / numOfSteps),
         iOld.a + (iOld.a > iNew.a ? (iNew.a - iOld.a) * iColorStepID / numOfSteps : (iNew.a - iOld.a) * iColorStepID / numOfSteps)
      );
   }

   public static final Color getColorMixed(Color iOld, Color iNew) {
      float tA = 1.0F - (1.0F - iOld.a) * (1.0F - iNew.a);
      return new Color(
         iNew.r * iNew.a / tA + iOld.r * iOld.a * (1.0F - iNew.a) / tA,
         iNew.g * iNew.a / tA + iOld.g * iOld.a * (1.0F - iNew.a) / tA,
         iNew.b * iNew.a / tA + iOld.b * iOld.a * (1.0F - iNew.a) / tA,
         iOld.a
      );
   }

   public static final float changeAnimationPos(int animationStepID, float animationChangeViewPos, boolean backAnimation, int nWidth) {
      switch (animationStepID) {
         case 0:
         case 1:
         case 12:
            animationChangeViewPos -= nWidth * 2.5F / 100.0F * (backAnimation ? -1 : 1);
            break;
         case 2:
         case 3:
         case 10:
         case 11:
            animationChangeViewPos -= nWidth * 5.0F / 100.0F * (backAnimation ? -1 : 1);
            break;
         case 4:
         case 5:
         case 8:
         case 9:
            animationChangeViewPos -= nWidth * 10.0F / 100.0F * (backAnimation ? -1 : 1);
            break;
         case 6:
         case 7:
            animationChangeViewPos -= nWidth * 15.0F / 100.0F * (backAnimation ? -1 : 1);
            break;
         case 13:
            animationChangeViewPos = -nWidth * (backAnimation ? -1 : 1);
      }

      return animationChangeViewPos;
   }

   public static final void showKeyboard() {
      showKeyboard(menuManager.getActiveMenuElementID());
   }

   public static final void showKeyboard(int nMenuElemenID) {
      showKeyboard(menuManager.getActiveSliderMenuID(), nMenuElemenID);
   }

   public static final void showKeyboard(int nActiveSliderMenuID, int nMenuElemenID) {
      if (Keyboard.colorPickerMode || Keyboard.commandsMode) {
         Keyboard.colorPickerMode = false;
         Keyboard.commandsMode = false;
      }

      updateKeyboard_Actions();
      if (Keyboard.numbers) {
         Keyboard.numbers = false;
         menuManager.getKeyboard().actionClose();
      }

      menuManager.setKeyboardActiveSliderMenuID(nActiveSliderMenuID);
      menuManager.setKeyboardActiveMenuElementID(nMenuElemenID);
      keyboardMessage = menuManager.getActiveMenu()
         .get(menuManager.getKeyboardActiveSliderMenuID())
         .getMenuElement(menuManager.getKeyboardActiveMenuElementID())
         .getText();
      menuManager.getKeyboard().setVisible(true);
   }

   public static final void showKeyboard_ColorPickerRGB(String nText) {
      if (!Keyboard.colorPickerMode) {
         Keyboard.colorPickerMode = true;
         Keyboard.commandsMode = false;
      }

      updateKeyboard_Actions();
      Keyboard.numbers = true;
      menuManager.getKeyboard().actionClose();
      keyboardMessage = nText;
      menuManager.getKeyboard().setVisible(true);
   }

   public static final void showKeyboard_Commands() {
      if (!Keyboard.commandsMode) {
         Keyboard.commandsMode = true;
      }

      updateKeyboard_Actions();
      menuManager.setKeyboardActiveMenuElementID(menuManager.getActiveMenuElementID());
      keyboardMessage = "";
      menuManager.getKeyboard().setVisible(true);
   }

   public static final void updateKeyboard_Actions() {
      if (Keyboard.colorPickerMode) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.menuManager
                  .getColorPicker()
                  .RGBtoHSV(
                     Keyboard.activeColor_RGB_ID == 0 ? CFG.getKeyboardMessage_RGB() : (int)(CFG.menuManager.getColorPicker().getActiveColor().r * 255.0F),
                     Keyboard.activeColor_RGB_ID == 1 ? CFG.getKeyboardMessage_RGB() : (int)(CFG.menuManager.getColorPicker().getActiveColor().g * 255.0F),
                     Keyboard.activeColor_RGB_ID == 2 ? CFG.getKeyboardMessage_RGB() : (int)(CFG.menuManager.getColorPicker().getActiveColor().b * 255.0F)
                  );
               CFG.menuManager.getColorPicker().getColorPickerAction().update();
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 3 && (CFG.keyboardMessage = CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1)).length() == 3
                  )
                {
                  CFG.keyboardMessage = CFG.keyboardMessage + 0;
               }

               CFG.keyboardSave.action();
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               try {
                  if (nChar.charAt(0) >= '0' && nChar.charAt(0) <= '9') {
                     CFG.keyboardMessage = CFG.keyboardMessage.length() > 2 && CFG.keyboardMessage.charAt(3) == '0'
                        ? CFG.keyboardMessage.substring(0, 3) + nChar
                        : CFG.keyboardMessage + nChar;

                     try {
                        if (CFG.keyboardMessage.length() > 2 && Integer.parseInt(CFG.keyboardMessage.substring(3, CFG.keyboardMessage.length())) > 255) {
                           CFG.keyboardMessage = CFG.keyboardMessage.substring(0, 3) + "255";
                        }
                     } catch (IllegalArgumentException var3) {
                     }

                     CFG.keyboardSave.action();
                  }
               } catch (IndexOutOfBoundsException var4) {
                  CFG.keyboardMessage = CFG.keyboardMessage + nChar;
               }
            }
         };
      } else if (Keyboard.allianceRenameMode) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.game.getAlliance(Keyboard.allianceID).setAllianceName(CFG.keyboardMessage);
               CFG.toast.setInView("" + CFG.keyboardMessage, CFG.game.getAlliance(Keyboard.allianceID).getColorOfAlliance().getColor());
               Keyboard.allianceRenameMode = false;
               CFG.keyboardMessage = "";
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "";
            }
         };
         updateKeyboard_DefaultWrite();
      } else if (Keyboard.commandsMode) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               Commands.execute(CFG.keyboardMessage);
               CFG.keyboardMessage = "";
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "";
            }
         };
         updateKeyboard_DefaultWrite();
      } else if (Keyboard.changeCivilizationNameMode > 0 && menuManager.getInGameView()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.game.getCiv(Keyboard.changeCivilizationNameMode).setCivName(CFG.keyboardMessage);
                  CFG.game.setActiveProvinceID(CFG.game.getActiveProvinceID());

                  for (int i = 0; i < CFG.game.getCiv(Keyboard.changeCivilizationNameMode).getCivRegionsSize(); i++) {
                     CFG.game.getCiv(Keyboard.changeCivilizationNameMode).getCivRegion(i).buildScaleOfText();
                  }

                  if (CFG.menuManager.getInGameView()) {
                     CFG.updateActiveCivInfo_InGame();
                  } else if (CFG.menuManager.getInCreateNewGame()) {
                     CFG.updateActiveCivInfo_CreateNewGame();
                  }
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "";
            }
         };
         updateKeyboard_DefaultWrite();
      } else if (Keyboard.changeProvinceNameMode > 0 && menuManager.getInGameView()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.game.getProvince(Keyboard.changeProvinceNameMode).setName(CFG.keyboardMessage);
                  CFG.game.setActiveProvinceID(Keyboard.changeProvinceNameMode);

                  try {
                     if (Keyboard.changeCityNameIDToo >= 0) {
                        CFG.game.getProvince(Keyboard.changeProvinceNameMode).getCity(Keyboard.changeCityNameIDToo).setCityName(CFG.keyboardMessage);
                     }
                  } catch (IndexOutOfBoundsException var2) {
                  }
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "";
            }
         };
         updateKeyboard_DefaultWrite();
      } else if (menuManager.getInCreateScenario_Civilizations_Select()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Civilizations_SelectList();
                  CFG.menuManager.getCreateScenario_Civilizations_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Civilizations_SelectList();
                  CFG.menuManager.getCreateScenario_Civilizations_SelectAlphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Civilizations_SelectList();
                  CFG.menuManager.getCreateScenario_Civilizations_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Civilizations_SelectList();
                  CFG.menuManager.getCreateScenario_Civilizations_SelectAlphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Civilizations_SelectList();
                  CFG.menuManager.getCreateScenario_Civilizations_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Civilizations_SelectList();
                  CFG.menuManager.getCreateScenario_Civilizations_SelectAlphabet();
               }
            }
         };
      } else if (menuManager.getInCreateScenario_Cores_AddCore()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCore_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCore_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCore_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCore_Alphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCore_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCore_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCore_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCore_Alphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCore_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCore_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCore_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCore_Alphabet();
               }
            }
         };
      } else if (menuManager.getInCreateScenario_Cores_AddCiv()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
               }
            }
         };
      } else if (menuManager.getInUnions_AddCiv()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildUnions_AddCiv_List();
                  CFG.menuManager.getUnions_AddCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildUnions_AddCiv_List();
                  CFG.menuManager.getUnions_AddCiv_Alphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildUnions_AddCiv_List();
                  CFG.menuManager.getUnions_AddCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildUnions_AddCiv_List();
                  CFG.menuManager.getUnions_AddCiv_Alphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildUnions_AddCiv_List();
                  CFG.menuManager.getUnions_AddCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildUnions_AddCiv_List();
                  CFG.menuManager.getUnions_AddCiv_Alphabet();
               }
            }
         };
      } else if (menuManager.getInCreateVassal_Select()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateVassal_SelectList();
                  CFG.menuManager.getCreateVassal_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateVassal_SelectList();
                  CFG.menuManager.getCreateVassal_SelectAlphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateVassal_SelectList();
                  CFG.menuManager.getCreateVassal_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateVassal_SelectList();
                  CFG.menuManager.getCreateVassal_SelectAlphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateVassal_SelectList();
                  CFG.menuManager.getCreateVassal_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateVassal_SelectList();
                  CFG.menuManager.getCreateVassal_SelectAlphabet();
               }
            }
         };
      } else if (menuManager.getInMapEditor_FormableCivs_SelectFormable()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectAlphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectAlphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectAlphabet();
               }
            }
         };
      } else if (menuManager.getInGameCivs()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildGameCivs_SelectList();
                  CFG.menuManager.getGameCivs_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildGameCivs_SelectList();
                  CFG.menuManager.getGameCivs_SelectAlphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildGameCivs_SelectList();
                  CFG.menuManager.getGameCivs_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildGameCivs_SelectList();
                  CFG.menuManager.getGameCivs_SelectAlphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildGameCivs_SelectList();
                  CFG.menuManager.getGameCivs_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildGameCivs_SelectList();
                  CFG.menuManager.getGameCivs_SelectAlphabet();
               }
            }
         };
      } else if (menuManager.getInMapEditor_FormableCivs()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildMapEditor_FormableCivs();
                  CFG.menuManager.getMapEditor_FormableCivs_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildMapEditor_FormableCivs();
                  CFG.menuManager.getMapEditor_FormableCivs_Alphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildMapEditor_FormableCivs();
                  CFG.menuManager.getMapEditor_FormableCivs_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildMapEditor_FormableCivs();
                  CFG.menuManager.getMapEditor_FormableCivs_Alphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildMapEditor_FormableCivs();
                  CFG.menuManager.getMapEditor_FormableCivs_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildMapEditor_FormableCivs();
                  CFG.menuManager.getMapEditor_FormableCivs_Alphabet();
               }
            }
         };
      } else if (menuManager.getInLeaders()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildLeaders();
                  CFG.menuManager.getLeaders_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildLeaders();
                  CFG.menuManager.getLeaders_Alphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildLeaders();
                  CFG.menuManager.getLeaders_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildLeaders();
                  CFG.menuManager.getLeaders_Alphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildLeaders();
                  CFG.menuManager.getLeaders_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildLeaders();
                  CFG.menuManager.getLeaders_Alphabet();
               }
            }
         };
      } else if (menuManager.getInMapEditor_FormableCivs_SelectClaimant()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                  CFG.menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
               }
            }
         };
      } else if (menuManager.getInLeader_Edit_SelectCivs()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildLeader_Edit_SelectCivs_List();
                  CFG.menuManager.getLeaders_SelectCivs_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildLeader_Edit_SelectCivs_List();
                  CFG.menuManager.getLeaders_SelectCivs_Alphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildLeader_Edit_SelectCivs_List();
                  CFG.menuManager.getLeaders_SelectCivs_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildLeader_Edit_SelectCivs_List();
                  CFG.menuManager.getLeaders_SelectCivs_Alphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildLeader_Edit_SelectCivs_List();
                  CFG.menuManager.getLeaders_SelectCivs_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildLeader_Edit_SelectCivs_List();
                  CFG.menuManager.getLeaders_SelectCivs_Alphabet();
               }
            }
         };
      } else if (menuManager.getInRandomGame_Civilizations_Select()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildRandomGame_Civilizations_SelectList();
                  CFG.menuManager.getRandomGame_Civilizations_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildRandomGame_Civilizations_SelectList();
                  CFG.menuManager.getRandomGame_Civilizations_SelectAlphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildRandomGame_Civilizations_SelectList();
                  CFG.menuManager.getRandomGame_Civilizations_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildRandomGame_Civilizations_SelectList();
                  CFG.menuManager.getRandomGame_Civilizations_SelectAlphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildRandomGame_Civilizations_SelectList();
                  CFG.menuManager.getRandomGame_Civilizations_SelectAlphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildRandomGame_Civilizations_SelectList();
                  CFG.menuManager.getRandomGame_Civilizations_SelectAlphabet();
               }
            }
         };
      } else if (menuManager.getInCreateScenario_Events_SelectCiv()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                  CFG.menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                  CFG.menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                  CFG.menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                  CFG.menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                  CFG.menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                  CFG.menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
               }
            }
         };
      } else if (menuManager.getInCreateScenario_Events_AddCiv()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if (CFG.keyboardMessage.length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Events_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Events_AddCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Events_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Events_AddCiv_Alphabet();
               }
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               if ((CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "").length()
                  > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Events_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Events_AddCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Events_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Events_AddCiv_Alphabet();
               }
            }
         };
         keyboardWrite = new CFG.Keyboard_Action_Write() {
            @Override
            public void action(String nChar) {
               if ((CFG.keyboardMessage = CFG.keyboardMessage + nChar).length() > 0) {
                  CFG.sSearch = CFG.keyboardMessage;
                  CFG.menuManager.rebuildCreateScenario_Events_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Events_AddCiv_Alphabet();
               } else {
                  CFG.sSearch = null;
                  CFG.menuManager.rebuildCreateScenario_Events_AddCiv_List();
                  CFG.menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
               }
            }
         };
      } else if (menuManager.getInCreateCity()) {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.menuManager
                  .getActiveMenu()
                  .get(CFG.menuManager.getKeyboardActiveSliderMenuID())
                  .getMenuElement(CFG.menuManager.getKeyboardActiveMenuElementID())
                  .setText(CFG.keyboardMessage);
               CFG.editorCity.setCityName(CFG.keyboardMessage);
               CFG.menuManager.getCreateCity_UpdateSaveButton();
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "";
               CFG.keyboardSave.action();
               CFG.menuManager.getCreateCity_UpdateSaveButton();
            }
         };
         updateKeyboard_DefaultWrite();
      } else {
         keyboardSave = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.menuManager
                  .getActiveMenu()
                  .get(CFG.menuManager.getKeyboardActiveSliderMenuID())
                  .getMenuElement(CFG.menuManager.getKeyboardActiveMenuElementID())
                  .setText(CFG.keyboardMessage);
            }
         };
         keyboardDelete = new CFG.Keyboard_Action() {
            @Override
            public void action() {
               CFG.keyboardMessage = CFG.keyboardMessage.length() > 1 ? CFG.keyboardMessage.substring(0, CFG.keyboardMessage.length() - 1) : "";
            }
         };
         updateKeyboard_DefaultWrite();
      }
   }

   public static final void updateKeyboard_DefaultWrite() {
      keyboardWrite = new CFG.Keyboard_Action_Write() {
         @Override
         public void action(String nChar) {
            CFG.keyboardMessage = CFG.keyboardMessage + nChar;
         }
      };
   }

   public static final int getKeyboardMessage_RGB() {
      try {
         int nRGB = Integer.parseInt(keyboardMessage.substring(3, keyboardMessage.length()));
         if (nRGB > 255) {
            nRGB = 255;
         } else if (nRGB < 0) {
            nRGB = 0;
         }

         return nRGB;
      } catch (IllegalArgumentException var1) {
         if (LOGS) {
            exceptionStack(var1);
         }
      } catch (StringIndexOutOfBoundsException var2) {
         if (LOGS) {
            exceptionStack(var2);
         }
      }

      return 0;
   }

   public static final void drawRect(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY, nWidth, 1);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + 1, 1, nHeight - 2);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + nWidth, nPosY, 1, nHeight);
   }

   public static final void drawRect_InfoBox_Right_Title(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      oSB.setColor(new Color(COLOR_GRADIENT_DARK_BLUE.r, COLOR_GRADIENT_DARK_BLUE.g, COLOR_GRADIENT_DARK_BLUE.b, 0.35F));
      ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
      oSB.setColor(new Color(COLOR_INFO_BOX_GRADIENT.r, COLOR_INFO_BOX_GRADIENT.g, COLOR_INFO_BOX_GRADIENT.b, 0.375F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, false, false);
      oSB.setColor(new Color(COLOR_INFO_BOX_GRADIENT.r, COLOR_INFO_BOX_GRADIENT.g, COLOR_INFO_BOX_GRADIENT.b, 0.7F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX + nWidth - nWidth / 2, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, true, false);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.45F);
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.375F);
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY + nHeight - nHeight / 5 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5, false, true);
      oSB.setColor(COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.55F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, nWidth, 1, true, false);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX, nPosY + nHeight - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawRect_InfoBox_Left_Title(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      oSB.setColor(new Color(COLOR_GRADIENT_DARK_BLUE.r, COLOR_GRADIENT_DARK_BLUE.g, COLOR_GRADIENT_DARK_BLUE.b, 0.35F));
      ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
      oSB.setColor(new Color(COLOR_INFO_BOX_GRADIENT.r, COLOR_INFO_BOX_GRADIENT.g, COLOR_INFO_BOX_GRADIENT.b, 0.7F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, false, false);
      oSB.setColor(new Color(COLOR_INFO_BOX_GRADIENT.r, COLOR_INFO_BOX_GRADIENT.g, COLOR_INFO_BOX_GRADIENT.b, 0.375F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX + nWidth - nWidth / 2, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, true, false);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.45F);
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.375F);
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY + nHeight - nHeight / 5 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5, false, true);
      oSB.setColor(COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, false, false);
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, false, false);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.55F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, nWidth, 1, true, false);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX, nPosY + nHeight - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawRect_InfoBox_Left(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      oSB.setColor(new Color(COLOR_GRADIENT_DARK_BLUE.r, COLOR_GRADIENT_DARK_BLUE.g, COLOR_GRADIENT_DARK_BLUE.b, 0.35F));
      ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
      oSB.setColor(new Color(COLOR_INFO_BOX_GRADIENT.r, COLOR_INFO_BOX_GRADIENT.g, COLOR_INFO_BOX_GRADIENT.b, 0.375F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, false, false);
      oSB.setColor(new Color(COLOR_INFO_BOX_GRADIENT.r, COLOR_INFO_BOX_GRADIENT.g, COLOR_INFO_BOX_GRADIENT.b, 0.475F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX + nWidth - nWidth / 2, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, true, false);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.45F);
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.375F);
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY + nHeight - nHeight / 5 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5, false, true);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.475F));
      ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1);
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), 1, nHeight - 2);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.175F));
      drawRect(oSB, nPosX - 1, nPosY - 2, nWidth + 1, nHeight + 2);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawRect_InfoBox_Right(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      oSB.setColor(new Color(COLOR_GRADIENT_DARK_BLUE.r, COLOR_GRADIENT_DARK_BLUE.g, COLOR_GRADIENT_DARK_BLUE.b, 0.35F));
      ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
      oSB.setColor(new Color(COLOR_INFO_BOX_GRADIENT.r, COLOR_INFO_BOX_GRADIENT.g, COLOR_INFO_BOX_GRADIENT.b, 0.475F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, false, false);
      oSB.setColor(new Color(COLOR_INFO_BOX_GRADIENT.r, COLOR_INFO_BOX_GRADIENT.g, COLOR_INFO_BOX_GRADIENT.b, 0.375F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX + nWidth - nWidth / 2, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, true, false);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.45F);
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5);
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.375F);
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY + nHeight - nHeight / 5 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5, false, true);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.475F));
      ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX + nWidth - 1, nPosY + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), 1, nHeight - 2);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.175F));
      drawRect(oSB, nPosX - 1, nPosY - 2, nWidth + 1, nHeight + 2);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawRect_NewGameBox_EDGE(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            nPosX,
            nPosY - ImageManager.getImage(Images.new_game_top_edge).getHeight(),
            nWidth - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            nHeight - ImageManager.getImage(Images.new_game_top_edge).getHeight()
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            nPosX + nWidth - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            nPosY - ImageManager.getImage(Images.new_game_top_edge).getHeight(),
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            nHeight - ImageManager.getImage(Images.new_game_top_edge).getHeight(),
            true,
            false
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            nPosX,
            nPosY + nHeight - ImageManager.getImage(Images.new_game_top_edge).getHeight() * 2,
            nWidth - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            ImageManager.getImage(Images.new_game_top_edge).getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            nPosX + nWidth - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            nPosY + nHeight - ImageManager.getImage(Images.new_game_top_edge).getHeight() * 2,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            ImageManager.getImage(Images.new_game_top_edge).getHeight(),
            true,
            true
         );
   }

   public static final void drawRect_NewGameBox(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            nPosX,
            nPosY - ImageManager.getImage(Images.new_game_box).getHeight(),
            nWidth - ImageManager.getImage(Images.new_game_box).getWidth(),
            nHeight - ImageManager.getImage(Images.new_game_box).getHeight()
         );
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            nPosX + nWidth - ImageManager.getImage(Images.new_game_box).getWidth(),
            nPosY - ImageManager.getImage(Images.new_game_box).getHeight(),
            ImageManager.getImage(Images.new_game_box).getWidth(),
            nHeight - ImageManager.getImage(Images.new_game_box).getHeight(),
            true
         );
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            nPosX,
            nPosY + nHeight - ImageManager.getImage(Images.new_game_box).getHeight() - ImageManager.getImage(Images.new_game_box).getHeight(),
            nWidth - ImageManager.getImage(Images.new_game_box).getWidth(),
            ImageManager.getImage(Images.new_game_box).getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_box)
         .draw(
            oSB,
            nPosX + nWidth - ImageManager.getImage(Images.new_game_box).getWidth(),
            nPosY + nHeight - ImageManager.getImage(Images.new_game_box).getHeight(),
            true,
            true
         );
   }

   public static final void drawEditorTitle_Edge_R(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
      ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, true, true);
      oSB.setColor(new Color(0.025F, 0.03F, 0.092F, 0.225F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, iHeight - 2);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.75F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, 1);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawEditorTitle_Edge_R_Reflected(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
      ImageManager.getImage(Images.editor_top)
         .draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, false, true);
      oSB.setColor(new Color(0.025F, 0.03F, 0.092F, 0.225F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, iHeight - 2);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.75F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, 1);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawEditorTitle_Edge_LR(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
      ImageManager.getImage(Images.editor_top)
         .draw2(
            oSB,
            nPosX,
            nPosY - ImageManager.getImage(Images.editor_top).getHeight(),
            ImageManager.getImage(Images.editor_top).getWidth(),
            iHeight + 1,
            false,
            true
         );
      ImageManager.getImage(Images.editor_top)
         .draw2(
            oSB,
            nPosX + ImageManager.getImage(Images.editor_top).getWidth(),
            nPosY - ImageManager.getImage(Images.editor_top).getHeight(),
            iWidth - ImageManager.getImage(Images.editor_top).getWidth(),
            iHeight + 1,
            true,
            true
         );
      oSB.setColor(new Color(0.025F, 0.03F, 0.092F, 0.225F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, iHeight - 2);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.75F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, 1);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawEditorTitle_Bot_Edge_LR(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
      ImageManager.getImage(Images.editor_top)
         .draw2(
            oSB,
            nPosX,
            nPosY - ImageManager.getImage(Images.editor_top).getHeight(),
            ImageManager.getImage(Images.editor_top).getWidth(),
            iHeight + 1,
            false,
            false
         );
      ImageManager.getImage(Images.editor_top)
         .draw2(
            oSB,
            nPosX + ImageManager.getImage(Images.editor_top).getWidth(),
            nPosY - ImageManager.getImage(Images.editor_top).getHeight(),
            iWidth - ImageManager.getImage(Images.editor_top).getWidth(),
            iHeight + 1,
            true,
            false
         );
      oSB.setColor(new Color(0.025F, 0.03F, 0.092F, 0.225F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight() + 2, iWidth, iHeight - 2);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.75F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, 1);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawEditorButtons_Bot_Edge_R(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
      ImageManager.getImage(Images.editor_top)
         .draw2(oSB, nPosX, nPosY - 1 - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, true, false);
      ImageManager.getImage(Images.editor_top_line)
         .draw2(oSB, nPosX + iWidth - 1, nPosY - 2, ImageManager.getImage(Images.editor_top_line).getWidth(), iHeight + 1, false, true);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.75F));
      ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), iWidth - PADDING, 1);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawEditorButtons_Bot_Edge_R_Reflected(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
      ImageManager.getImage(Images.editor_top)
         .draw2(oSB, nPosX, nPosY - 1 - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, false, false);
      ImageManager.getImage(Images.editor_top_line)
         .draw2(oSB, nPosX - 1, nPosY - 2, ImageManager.getImage(Images.editor_top_line).getWidth(), iHeight + 1, true, true);
      oSB.setColor(new Color(COLOR_FLAG_FRAME.r, COLOR_FLAG_FRAME.g, COLOR_FLAG_FRAME.b, 0.75F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX + PADDING, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), iWidth - PADDING, 1, true, false);
      oSB.setColor(Color.WHITE);
   }

   public static final void drawEditorButtons_Top_Edge_R(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
      ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, true, true);
      ImageManager.getImage(Images.editor_top_line)
         .draw2(
            oSB,
            nPosX + iWidth - 1,
            nPosY - ImageManager.getImage(Images.editor_top_line).getHeight(),
            ImageManager.getImage(Images.editor_top_line).getWidth(),
            iHeight + 1,
            false,
            true
         );
   }

   public static final void drawEditorButtons_Top_Edge_R_Reflected(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
      ImageManager.getImage(Images.editor_top)
         .draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, false, true);
      ImageManager.getImage(Images.editor_top_line)
         .draw2(
            oSB,
            nPosX - 1,
            nPosY - ImageManager.getImage(Images.editor_top_line).getHeight(),
            ImageManager.getImage(Images.editor_top_line).getWidth(),
            iHeight + 1,
            true,
            true
         );
   }

   public static final void drawBG_WithGradient(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
      oSB.setColor(new Color(0.0F, 0.01F, 0.012F, 0.45F));
      ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), iWidth, iHeight);
      oSB.setColor(new Color(0.0F, 0.01F, 0.012F, 0.32F));
      ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), iWidth, iHeight);
      oSB.setColor(new Color(0.0F, 0.01F, 0.012F, 0.75F));
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), iWidth, iHeight / 4);
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight() + iHeight - iHeight / 4, iWidth, iHeight, false, true);
      oSB.setColor(COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), iWidth, 1);
      ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), iWidth, 1);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() - 1, iWidth, 1);
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, iWidth, 1);
      oSB.setColor(Color.WHITE);
   }

   public static final void setDialogType(Dialog nDialogType) {
      dialogType = nDialogType;
      menuManager.getDialogMenu().getMenuElement(1).setClickable(true);
      menuManager.getDialogMenu().getMenuElement(2).setClickable(true);

      try {
         switch (dialogType) {
            case EXIT_GAME:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ExitTheGame"));
               break;
            case SELECT_CIVILIZATION:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("PlayAs") + " " + game.getCiv(game.getProvince(game.getActiveProvinceID()).getCivID()).getCivName() + "?");
               break;
            case THROW_NUCLEAR_WARHEAD:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ConfirmThrowingNuclearWarhead"));
               break;
            case PAUSE_GAME:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + " " + langManager.get("ExitToMainMenu") + "?");
               break;
            case CREATE_RANDOM_GAME_EXIT_MAIN_MENU:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + " " + langManager.get("ExitToMainMenu") + "?");
               break;
            case PEACE_TREATY_BACK_ARE_YOU_SURE:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Back") + "? " + langManager.get("AreYouSure"));
               break;
            case SEND_DEMANDS:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SendDemands") + "?");
               break;
            case PEACE_TREARY_ACCEPT:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AcceptOffer") + "?");
               break;
            case PEACE_TREARY_REFUSE:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Refuse") + "?");
               break;
            case ABADON:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("AbandonProvince") + "? " + game.getProvince(Menu_InGame_Abadon.iProvinceID).getName());
               break;
            case COLONIZE_PROVINCE:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Colonize") + "?");
               break;
            case END_GAME_SPECTACTOR:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SpectatorMode") + "?");
               break;
            case END_GAME_EXIT_MAIN_MENU:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ExitToMainMenu") + "?");
               break;
            case END_GAME_ONE_MORE_TURN:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("JustOneMoreTurnIPromise") + "?");
               break;
            case CONTINUE_AFTER_END_GAME:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Back") + "?");
               break;
            case EXIT_CREATOR:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ExitScenarioEditor") + "?");
               break;
            case PEACE_TREATY_TAKE_ALL:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(game.getCiv(MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getCivName() + ". " + langManager.get("TakeAll") + "?");
               break;
            case CREATE_SCENARIO_REMOVE_CIVILIZATION:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("Remove") + " " + game.getCiv(game.getProvince(iCreateScenario_ActiveProvinceID).getCivID()).getCivName() + "?");
               break;
            case CREATE_SCENARIO_ASSIGN_CIVILIZATION:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("Select") + " " + game.getCiv(game.getProvince(game.getActiveProvinceID()).getCivID()).getCivName() + "?");
               break;
            case TRADE_REQUEST_SELECT_CIV:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("Select") + " " + game.getCiv(game.getProvince(game.getActiveProvinceID()).getCivID()).getCivName() + "?");
               break;
            case SAVE_SCENARIO:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SaveScenario") + "?");
               break;
            case CREATE_SCENARIO_REMOVE_EVENT:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("Remove") + "? " + eventsManager.getEvent(eventsManager.iCreateEvent_EditEventID) + "?");
               break;
            case CONFIRM_LANGUAGE:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Language") + ": " + langManager.get("LANGUAGENAME") + "?");
               break;
            case CREATE_SCENARIO_EVENTS_EDIT_BACK:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Back") + "?");
               break;
            case CREATE_SCENARIO_EVENTS_EDIT_SAVE:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SaveEvent") + "?");
               break;
            case SURRENDER:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("Surrender") + "? " + game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCivName());
               break;
            case FORM_A_CIV:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("FormX", langManager.getCiv(formableCivs_GameData.getFormableCivTag())) + "?");
               break;
            case DESELET_ALL_SELECTED_PROVINCES:
            case DESELET_ALL_SELECTED_PROVINCES_CREATE_A_VASSAL:
            case DESELET_ALL_SELECTED_PROVINCES_CREATE_HOLY_ROMAN_EMPIRE:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("DeselectAll") + "?");
               break;
            case NO_ORDERS:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("NoOrders"));
               break;
            case REVERSE_WASTELAND:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Reverse") + "?");
               break;
            case CONFIRM_END_TURN:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SumbitOrders"));
               break;
            case START_TUTORIAL:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("StartTheTutorial"));
               break;
            case REMOVE_RANDOM_ALLIANCES_NAMES_BUNDLE:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + "?");
               break;
            case REMOVE_TRADE_ZONE:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + "?");
               break;
            case SAVE_THE_GAME:
            case SAVE_THE_GAME_OPTIONS:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SaveTheGame") + "?");
               break;
            case ALL_NOT_SAVED_PROGRESS_WILL_BE_LOST:
            case ALL_NOT_SAVED_PROGRESS_WILL_BE_LOST2:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AllNotSavedProgressFromLastGameWillBeLostContinue"));
               break;
            case GO_TO_WIKI:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Open") + " " + getWikiInormationsLink(EDITOR_ACTIVE_GAMEDATA_TAG) + "?");
               break;
            case GO_TO_WIKI_SCENARIO:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("Open") + " " + "https://en.wikipedia.org/wiki/" + EDITOR_ACTIVE_GAMEDATA_TAG + "?");
               break;
            case GO_TO_LINK:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Open") + " " + GO_TO_LINK + "?");
               break;
            case RELEASE_A_VASSAL:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ReleaseAVassal") + "?");
               break;
            case SHUFFLE_CIVILIZATIONS:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ShuffleCivilizations") + "?");
               break;
            case GENERATE_SUGGESTED_OWNERS:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("GenerateSuggestedCivilizations") + "?");
               break;
            case GENERATE_PRE_DEFINED_BORDERS:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("GeneratePreDefinedBorders") + "?");
               break;
            case GENERATE_SEA_ROUTES:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("GenerateSeaRoutes") + "?");
               break;
            case MAP_EDITOR_WASTELANDMAPS_WORLD_FILL:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + "?");
               break;
            case DELETE_SAVEDGAME:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("DeleteSavedGame"));
               break;
            case MAP_EDITOR_SEA_ARMY_BOXES_ROMVE:
               menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Remove") + " " + (MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 + 1) + "?");
               break;
            case REMOVE_PRINCE:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(
                     langManager.get("Remove")
                        + " "
                        + game.getCiv(holyRomanEmpire_Manager.getHRE().getPrince(MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID)).getCivName()
                        + "?"
                  );
               break;
            case CONVERT_ARMY_POSITION_TO_ANOTHER_SCALE:
            case CONVERT_PORT_POSITION_TO_ANOTHER_SCALE:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(
                     langManager.get("AreYouSure")
                        + "? "
                        + langManager.get("Scale")
                        + " "
                        + map.getMapScale(map.getActiveMapID())
                        + " -> "
                        + langManager.get("Scale")
                        + " "
                        + MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2
                        + "?"
                  );
               break;
            case MANAGE_DIPLOMACY_REMOVE_CIVILIZATION_FROM_ALLIANCE:
               menuManager.getDialogMenu()
                  .getMenuElement(3)
                  .setText(langManager.get("Remove") + " " + game.getCiv(MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getCivName() + "?");
               break;
            case REMOVE_CITY:
               FileHandle fileData = Gdx.files.internal("map/" + map.getFile_ActiveMap_Path() + "data/cities/" + EDITOR_ACTIVE_GAMEDATA_TAG);

               try {
                  editorCity = (City)deserialize(fileData.readBytes());
                  menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Remove") + " " + editorCity.getCityName() + "?");
               } catch (ClassNotFoundException var3) {
               } catch (IOException var4) {
               }
         }
      } catch (IndexOutOfBoundsException var5) {
         exceptionStack(var5);
      }

      menuManager.getDialogMenu().setVisible(true);
   }

   public static final void dialog_True() {
      MenuElement_Hover_v2.resetAnimation();

      try {
         switch (dialogType) {
            case EXIT_GAME:
               System.exit(0);
               break;
            case SELECT_CIVILIZATION:
               if (game.getPlayer(iSelectCivilizationPlayerID).getCivID() > 0) {
                  game.disableDrawCivilizationRegions(game.getPlayer(iSelectCivilizationPlayerID).getCivID());
               }

               game.getPlayer(iSelectCivilizationPlayerID).setCivID(game.getProvince(game.getActiveProvinceID()).getCivID());
               game.enableDrawCivilizationRegions(game.getPlayer(iSelectCivilizationPlayerID).getCivID(), 0);
               menuManager.setViewID(Menu.eNEWGAME_PLAYERS);
               map.getMapCoordinates().centerToProvinceID(game.getCiv(game.getPlayer(iSelectCivilizationPlayerID).getCivID()).getCapitalProvinceID());
               game.setActiveProvinceID(-1);
               break;
            case THROW_NUCLEAR_WARHEAD:
               Menu_InGame_ProvinceAction_ThrowNuclearWarhead.nuclearBOOM();
               break;
            case PAUSE_GAME:
               FREEPLAY_MODE = false;
               map.getMapScale().setNewCurrentScaleByButton2(0.175F);
               menuManager.setViewID(Menu.eGAMES);
               menuManager.setOrderOfMenu_Games();
               map.getMapBG().updateWorldMap_Shaders();
               viewsManager.disableAllViews();
               tutorialManager.updateDrawTutorial(false);
               break;
            case CREATE_RANDOM_GAME_EXIT_MAIN_MENU:
               Menu_RandomGame.backToGames();
               break;
            case PEACE_TREATY_BACK_ARE_YOU_SURE:
               menuManager.setViewID(Menu.eINGAME);

               for (int ixx = 0; ixx < game.getProvincesSize(); ixx++) {
                  game.getProvince(ixx).getArmy_Obj(0).updateArmyWidth_Just(ixx);
               }

               game.checkProvinceActionMenu();
               map.getMapBG().updateWorldMap_Shaders();
               viewsManager.setActiveViewID(game.getPlayer(PLAYER_TURNID).iACTIVE_VIEW_MODE);
               game.setActiveProvinceID(game.getPlayer(PLAYER_TURNID).iBefore_ActiveProvince);
               menuManager.getDialogMenu().setVisible(false);
               break;
            case SEND_DEMANDS:
               DiplomacyManager.sendPeaceTreaty(
                  game.getWar(peaceTreatyData.peaceTreatyGameData.iWarID).getIsAggressor(game.getPlayer(PLAYER_TURNID).getCivID()),
                  game.getPlayer(PLAYER_TURNID).getCivID(),
                  peaceTreatyData.peaceTreatyGameData
               );
               Menu_PeaceTreaty.backToInGame();
               menuManager.setVisible_Menu_InGame_CurrentWars(true);
               if (menuManager.getVisibleInGame_WarDetails()) {
                  menuManager.setVisibleInGame_WarDetails(false);
               }

               toast.setInView(langManager.get("Sent") + "!", COLOR_TEXT_MODIFIER_POSITIVE);
               toast.setTimeInView(4500);
               break;
            case PEACE_TREARY_ACCEPT:
               DiplomacyManager.acceptPeaceTreaty(game.getPlayer(PLAYER_TURNID).getCivID(), game.sRespondToPeaceTreatyID);
               Menu_PeaceTreaty_Response.backToInGame();
               menuManager.setVisible_Menu_InGame_CurrentWars(true);
               break;
            case PEACE_TREARY_REFUSE:
               DiplomacyManager.declinePeaceTreaty(game.getPlayer(PLAYER_TURNID).getCivID(), game.sRespondToPeaceTreatyID);
               Menu_PeaceTreaty_Response.backToInGame();
               toast.setInView(langManager.get("Refused"), COLOR_TEXT_MODIFIER_NEGATIVE2);
               menuManager.setVisible_Menu_InGame_CurrentWars(true);
               break;
            case ABADON:
               if (gameAction.abadonProvince(Menu_InGame_Abadon.iProvinceID, game.getPlayer(PLAYER_TURNID).getCivID())) {
                  toast.setInView(langManager.get("Abandoned") + "!", COLOR_TEXT_MODIFIER_POSITIVE);
                  toast.setTimeInView(4500);
                  game.setActiveProvinceID(-1);
                  game.setActiveProvinceID(Menu_InGame_Abadon.iProvinceID);
                  if (menuManager.getInGame_ProvinceBuild_Visible()) {
                     menuManager.setVisible_InGame_ProvinceBuild(false, false);
                  }

                  gameAction.buildRank_Score(game.getPlayer(PLAYER_TURNID).getCivID());
                  game.buildCivilizationRegions(game.getPlayer(PLAYER_TURNID).getCivID());
               }

               updateActiveCivInfo_InGame();
               menuManager.updateInGame_TOP_All(game.getPlayer(PLAYER_TURNID).getCivID());
               menuManager.setVisibleInGame_SendMessage(false);
               break;
            case COLONIZE_PROVINCE:
               int nProvID = game.getActiveProvinceID();
               if (DiplomacyManager.colonizeWastelandProvince(nProvID, game.getPlayer(PLAYER_TURNID).getCivID())) {
                  menuManager.rebuildMenu_InGame_CityHaveBeenFounded(nProvID, game.getPlayer(PLAYER_TURNID).getCivID());
               }

               menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
               game.buildCivilizationRegions(game.getPlayer(PLAYER_TURNID).getCivID());
               soundsManager.playSound(SoundsManager.SOUND_MOVE_ARMY2);
               menuManager.updateInGame_TOP_All(game.getPlayer(PLAYER_TURNID).getCivID());
               menuManager.setVisible_Menu_InGame_CurrentWars(true);
            case PEACE_TREATY_TAKE_ALL:
               for (int jxx = 0; jxx < peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); jxx++) {
                  if (peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(jxx).iCivID == MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                     for (int k = 0; k < peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(jxx).lProvincesLost.size(); k++) {
                        if (peaceTreatyData.drawProvinceOwners.get(peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(jxx).lProvincesLost.get(k)).isTaken
                              < 0
                           && (
                              game.getProvince(peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(jxx).lProvincesLost.get(k)).getCivID()
                                    == peaceTreatyData.iBrushCivID
                                 || game.getProvince(peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(jxx).lProvincesLost.get(k))
                                       .getTrueOwnerOfProvince()
                                    == peaceTreatyData.iBrushCivID
                           )) {
                           peaceTreatyData.takeProvince(
                              peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(jxx).lProvincesLost.get(k),
                              peaceTreatyData.iBrushCivID,
                              game.getPlayer(peaceTreatyData.iPlayerTurnID).getCivID()
                           );
                        }
                     }

                     return;
                  }
               }

               for (int jxxx = 0; jxxx < peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); jxxx++) {
                  if (peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(jxxx).iCivID == MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                     for (int kx = 0; kx < peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(jxxx).lProvincesLost.size(); kx++) {
                        if (peaceTreatyData.drawProvinceOwners.get(peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(jxxx).lProvincesLost.get(kx)).isTaken
                              < 0
                           && (
                              game.getProvince(peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(jxxx).lProvincesLost.get(kx)).getCivID()
                                    == peaceTreatyData.iBrushCivID
                                 || game.getProvince(peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(jxxx).lProvincesLost.get(kx))
                                       .getTrueOwnerOfProvince()
                                    == peaceTreatyData.iBrushCivID
                           )) {
                           peaceTreatyData.takeProvince(
                              peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(jxxx).lProvincesLost.get(kx),
                              peaceTreatyData.iBrushCivID,
                              game.getPlayer(peaceTreatyData.iPlayerTurnID).getCivID()
                           );
                        }
                     }

                     return;
                  }
               }
               break;
            case END_GAME_SPECTACTOR:
               try {
                  if (!SPECTATOR_MODE) {
                     int tNumOfPlayersInGame = 0;

                     for (int ix = 0; ix < game.getPlayersSize(); ix++) {
                        if (game.getCiv(game.getPlayer(ix).getCivID()).getNumOfProvinces() > 0 && ix != PLAYER_TURNID) {
                           tNumOfPlayersInGame++;
                        }
                     }

                     if (tNumOfPlayersInGame == 0) {
                        startTheGameData = new Start_The_Game_Data(true);
                        Menu_StartTheGame_Reverse.END_GAME_MODE = false;
                        menuManager.setViewID(Menu.eEND_THE_GAME);
                     } else {
                        Menu_InGame_EndOfGame.clickBack();
                     }
                  }
               } catch (IndexOutOfBoundsException var9) {
                  exceptionStack(var9);
               } catch (NullPointerException var10) {
                  exceptionStack(var10);
               }
               break;
            case END_GAME_EXIT_MAIN_MENU:
               FREEPLAY_MODE = false;
               menuManager.setVisible_CreateNewGame_Options_Scenarios(false);
               startTheGameData = new Start_The_Game_Data(true);
               Menu_StartTheGame_Reverse.END_GAME_MODE = true;
               menuManager.setViewID(Menu.eEND_THE_GAME);
               break;
            case END_GAME_ONE_MORE_TURN:
               Menu_InGame_EndOfGame.clickBack();
               break;
            case CONTINUE_AFTER_END_GAME:
               Menu_Victory.clickBack();
               gameAction.hideExtraViews();
               menuManager.setVisible_InGame_EndOfGame(true);
               break;
            case EXIT_CREATOR:
               game.setActiveProvinceID(-1);
               menuManager.setViewID(Menu.eEDITOR_SCENARIOS);
               map.getMapCoordinates().centerToRandomMapPosition();
               Menu_CreateScenario_Settings.disposePreview();
               break;
            case CREATE_SCENARIO_REMOVE_CIVILIZATION:
               toast.setInView(game.getCiv(game.getProvince(iCreateScenario_ActiveProvinceID).getCivID()).getCivName() + " - " + langManager.get("Removed"));
               toast.setTimeInView(3000);
               game.disableDrawCivilizationRegions(game.getProvince(iCreateScenario_ActiveProvinceID).getCivID());
               game.createScenarioRemoveCivilization(game.getProvince(iCreateScenario_ActiveProvinceID).getCivID());
               updateCreateScenario_Civilizations();
               menuManager.rebuildCreateScenario_Civilizations_Suggest();
               game.setActiveProvinceID(game.getActiveProvinceID());
               break;
            case CREATE_SCENARIO_ASSIGN_CIVILIZATION:
               game.disableDrawCivilizationRegions(iCreateScenario_AssignProvinces_Civ);
               iCreateScenario_AssignProvinces_Civ = game.getProvince(game.getActiveProvinceID()).getCivID();
               game.enableDrawCivilizationRegions(iCreateScenario_AssignProvinces_Civ, 0);
               break;
            case TRADE_REQUEST_SELECT_CIV:
               if (Menu_InGame_TradeRequest_SelectCiv.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_DECLAREWAR) {
                  tradeRequest.listLEFT.iDeclarWarOnCivID = game.getProvince(game.getActiveProvinceID()).getCivID();
               } else if (Menu_InGame_TradeRequest_SelectCiv.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_DECLAREWAR) {
                  tradeRequest.listRight.iDeclarWarOnCivID = game.getProvince(game.getActiveProvinceID()).getCivID();
               } else if (Menu_InGame_TradeRequest_SelectCiv.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_COALITION) {
                  tradeRequest.listLEFT.iFormCoalitionAgainst = game.getProvince(game.getActiveProvinceID()).getCivID();
               } else if (Menu_InGame_TradeRequest_SelectCiv.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_COALITION) {
                  tradeRequest.listRight.iFormCoalitionAgainst = game.getProvince(game.getActiveProvinceID()).getCivID();
               }

               menuManager.setViewID(Menu.eINGAME);
               menuManager.rebuildInGame_TradeRequest_Just();
               Game_Render_Province.updateDrawProvinces();
               viewsManager.setActiveViewID(game.getPlayer(PLAYER_TURNID).iACTIVE_VIEW_MODE);
               break;
            case SAVE_SCENARIO:
               game.saveScenario();
               backToMenu = Menu.eEDITOR_SCENARIOS;
               menuManager.setViewID(Menu.eGENERATE_PREVIEW);
               Menu_CreateScenario_Settings.disposePreview();
               break;
            case CREATE_SCENARIO_REMOVE_EVENT:
               eventsManager.removeEvent(eventsManager.iCreateEvent_EditEventID);
               menuManager.setVisibleCreateScenario_Events_Edit(false);
               break;
            case CONFIRM_LANGUAGE:
               menuManager.getInSelectLanguage_OnBackPressed();
               break;
            case CREATE_SCENARIO_EVENTS_EDIT_BACK:
               menuManager.setVisibleCreateScenario_Events_Edit(false);
               break;
            case CREATE_SCENARIO_EVENTS_EDIT_SAVE:
               eventsManager.setEvent(eventsManager.iCreateEvent_EditEventID, eventsManager.lCreateScenario_Event);
               menuManager.setVisibleCreateScenario_Events_Edit(false);
               break;
            case SURRENDER:
               menuManager.setVisible_InGame_Options(false);
               menuManager.setViewID(Menu.eDEFEAT);
               map.getMapBG().updateWorldMap_Shaders();
               break;
            case FORM_A_CIV:
               formCiv(game.getPlayer(PLAYER_TURNID).getCivID());
               setActiveCivInfo(activeCivInfo);
               menuManager.setViewIDWithoutAnimation(Menu.eINGAME);
               menuManager.setVisible_InGame_CivInfo(false);
               map.getMapBG().updateWorldMap_Shaders();
               game.setActiveProvinceID(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCapitalProvinceID());
               game.getPlayer(PLAYER_TURNID).loadPlayersFlag();
               break;
            case DESELET_ALL_SELECTED_PROVINCES:
               game.getSelectedProvinces().clearSelectedProvinces();
               selectMode = true;
               break;
            case DESELET_ALL_SELECTED_PROVINCES_CREATE_A_VASSAL:
               game.getSelectedProvinces().clearSelectedProvinces();
               selectMode = true;
               createVassal_Data.iCapitalProvinceID = -1;
               updateCreateAVassal_CivInfo();
               break;
            case DESELET_ALL_SELECTED_PROVINCES_CREATE_HOLY_ROMAN_EMPIRE:
               game.getSelectedProvinces().clearSelectedProvinces();
               selectMode = true;

               for (int jxx = 0; jxx < game.getProvincesSize(); jxx++) {
                  game.getProvince(jxx).setIsPartOfHolyRomanEmpire(false);
               }

               for (int jxx = 1; jxx < game.getCivsSize(); jxx++) {
                  game.getCiv(jxx).setIsPartOfHolyRomanEmpire(false);
               }

               holyRomanEmpire_Manager.initHolyRomanEmpire();
               menuManager.rebuildCreateScenario_HolyRomanEmpire_Princes();
               break;
            case NO_ORDERS:
               gameAction.nextTurn();
               break;
            case REVERSE_WASTELAND:
               for (int jx = 0; jx < game.getProvincesSize(); jx++) {
                  if (!game.getProvince(jx).getSeaProvince()) {
                     if (game.getProvince(jx).getWasteland() < 0) {
                        game.getProvince(jx).setWasteland(0);
                     } else {
                        game.getProvince(jx).setWasteland(-1);
                     }
                  }
               }

               updateNumOfAvailableProvinces();
               game.buildWastelandLevels();
               toast.setInView(langManager.get("Done"));
               break;
            case CONFIRM_END_TURN:
               gameAction.nextTurn();
               break;
            case START_TUTORIAL:
               if (SaveManager.gameCanBeContinued) {
                  game.setActiveProvinceID(-1);
                  RANDOM_FILL = false;
                  RANDOM_PLACMENT = false;
                  PLAYER_TURNID = 0;
                  game.getPlayer(0).initMetProvince(true);
                  game.loadScenario(false);
                  game.initPlayers();
                  CFG.soundsManager.playNewGameSFX();
                  SaveManager.gameCanBeContinued = false;
                  Menu_Games.clickNewGame();
                  menuManager.rebuildCivilizations_Info_Players();
                  Game_Render_Province.updateDrawProvinces();
               }

               FOG_OF_WAR = 1;
               SPECTATOR_MODE = false;
               game.initPlayers();
               PLAYER_TURNID = 0;
               if (ideologiesManager.getIdeology(game.getCiv(game.getPlayer(0).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
                  for (int j = 1; j < game.getCivsSize(); j++) {
                     if (game.getCiv(j).getNumOfProvinces() > 0 && ideologiesManager.getIdeology(game.getCiv(j).getIdeologyID()).CAN_BECOME_CIVILIZED < 0) {
                        game.getPlayer(0).setCivID(j);
                        break;
                     }
                  }
               }

               if (game.getProvince(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getLevelOfPort() >= 0) {
                  game.getProvince(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCapitalProvinceID()).setLevelOfPort(1);
               }

               for (int jx = 0;
                  jx < game.getProvince(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getNeighboringProvincesSize();
                  jx++
               ) {
                  game.getProvince(game.getProvince(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getNeighboringProvinces(jx))
                     .setWasteland(-1);
                  game.getProvince(game.getProvince(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getNeighboringProvinces(jx))
                     .setCivID(game.getPlayer(PLAYER_TURNID).getCivID(), false, false);
               }

               game.getProvince(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCapitalProvinceID()).setLevelOfFarm(1);
               if (game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getMoney() < 5000L) {
                  game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).setMoney(5000L);
               }

               int decWarOn = -1;

               for (int i = 1; i < game.getCivsSize(); i++) {
                  if (i != game.getPlayer(PLAYER_TURNID).getCivID() && game.getCiv(i).getNumOfProvinces() > 0) {
                     decWarOn = i;
                     break;
                  }
               }

               if (decWarOn > 0) {
                  game.setCivTruce(game.getPlayer(PLAYER_TURNID).getCivID(), decWarOn, 0);
                  game.setCivNonAggressionPact(game.getPlayer(PLAYER_TURNID).getCivID(), decWarOn, 0);
                  game.declareWar(decWarOn, game.getPlayer(PLAYER_TURNID).getCivID(), false);
               }

               game.setActiveProvinceID(game.getActiveProvinceID());
               Menu_CreateNewGame.newGame();
               tutorialManager.startTutorial();
               if (game.getProvince(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getArmy(0) <= 0) {
                  game.getProvince(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCapitalProvinceID()).updateArmy(750);
               }
               break;
            case REMOVE_RANDOM_ALLIANCES_NAMES_BUNDLE:
               editorAlliancesNames_GameData.removeBundle(EDIT_ALLIANCE_NAMES_BUNDLE_ID);
               menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE_CREATE);
            case REMOVE_TRADE_ZONE:
            default:
               break;
            case SAVE_THE_GAME:
               SaveManager.saveRequest = true;
               toast.setInView(langManager.get("GameWillBeSavedInNextTurn"), COLOR_TEXT_NUM_OF_PROVINCES);
               toast.setTimeInView(6000);
               menuManager.setVisible_Menu_InGame_CurrentWars(true);
               break;
            case SAVE_THE_GAME_OPTIONS:
               SaveManager.saveRequest = true;
               toast.setInView(langManager.get("GameWillBeSavedInNextTurn"), COLOR_TEXT_NUM_OF_PROVINCES);
               toast.setTimeInView(6000);
               menuManager.setVisible_Menu_InGame_CurrentWars(true);
               Menu_InGame_Options.clickBack();
               break;
            case ALL_NOT_SAVED_PROGRESS_WILL_BE_LOST:
               game.setActiveProvinceID(-1);
               RANDOM_FILL = false;
               RANDOM_PLACMENT = false;
               PLAYER_TURNID = 0;
               game.getPlayer(0).initMetProvince(true);
               game.loadScenario(false);
               game.initPlayers();
               SaveManager.gameCanBeContinued = false;
               Menu_Games.clickNewGame();
               menuManager.rebuildCivilizations_Info_Players();
               Game_Render_Province.updateDrawProvinces();
               break;
            case ALL_NOT_SAVED_PROGRESS_WILL_BE_LOST2:
               Menu_Games.clickRandomGame();
               break;
            case GO_TO_WIKI:
               wikiInormationsLink(EDITOR_ACTIVE_GAMEDATA_TAG);
               break;
            case GO_TO_WIKI_SCENARIO:
               try {
                  Gdx.net.openURI("https://en.wikipedia.org/wiki/" + EDITOR_ACTIVE_GAMEDATA_TAG);
               } catch (GdxRuntimeException var7) {
                  toast.setInView(langManager.get("NoData"));
               }
               break;
            case GO_TO_LINK:
               try {
                  Gdx.net.openURI(GO_TO_LINK);
               } catch (GdxRuntimeException var6) {
                  toast.setInView(langManager.get("NoData"));
               }
               break;
            case RELEASE_A_VASSAL:
               try {
                  List<Integer> tempProvinces = new ArrayList<>();

                  for (int ix = 0; ix < game.getSelectedProvinces().getProvincesSize(); ix++) {
                     tempProvinces.add(game.getSelectedProvinces().getProvince(ix));
                  }

                  int nVassalID = game.releaseAVasssal(
                     createVassal_Data.sCivTag, tempProvinces, createVassal_Data.iCapitalProvinceID, game.getPlayer(PLAYER_TURNID).getCivID(), false
                  );
                  if (nVassalID >= 0) {
                     menuManager.rebuildMenu_InGame_VassalReleased(nVassalID);
                  }

                  if (createVassal_Data.playAsVassal) {
                     int nCivID = game.getPlayer(PLAYER_TURNID).getCivID();
                     game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).setControlledByPlayer(false);

                     for (int l = 1; l < game.getCivsSize(); l++) {
                        if (game.getCiv(l).getCivTag().equals(createVassal_Data.sCivTag)) {
                           nCivID = l;
                           break;
                        }
                     }

                     game.getPlayer(PLAYER_TURNID).setCivID(nCivID);
                     game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).setControlledByPlayer(true);
                     game.getPlayer(PLAYER_TURNID).loadPlayersFlag();
                     menuManager.updateInGame_TOP_All(game.getPlayer(PLAYER_TURNID).getCivID());
                  }

                  gameAction.buildFogOfWar(PLAYER_TURNID);
                  brushTool = false;
                  menuManager.setViewID(Menu.eINGAME);
                  Game_Render_Province.updateDrawProvinces();
                  map.getMapBG().updateWorldMap_Shaders();
                  createVassal_Data.dispose();
                  createVassal_Data = null;
                  viewsManager.setActiveViewID(game.getPlayer(PLAYER_TURNID).iACTIVE_VIEW_MODE);
                  menuManager.updateInGame_TOP_All(game.getPlayer(PLAYER_TURNID).getCivID());
                  menuManager.setVisible_Menu_InGame_CurrentWars(true);
                  if (menuManager.getVisibleInGame_Tribute()) {
                     menuManager.rebuildInGame_Tribute();
                  }

                  if (isDesktop()) {
                  }
               } catch (IndexOutOfBoundsException var8) {
                  viewsManager.setActiveViewID(game.getPlayer(PLAYER_TURNID).iACTIVE_VIEW_MODE);
                  menuManager.updateInGame_TOP_All(game.getPlayer(PLAYER_TURNID).getCivID());
               }
               break;
            case SHUFFLE_CIVILIZATIONS:
               game.shuffleCivilizations();
               menuManager.setVisible_CreateNewGame_Options(false);
               break;
            case GENERATE_SUGGESTED_OWNERS:
               menuManager.setViewID(Menu.eLOAD_GENERATE_SUGGESTED_OWNERS);
               break;
            case GENERATE_PRE_DEFINED_BORDERS:
               menuManager.setViewID(Menu.eLOAD_GENERATE_PRE_DEFINED_BORDERS);
               break;
            case GENERATE_SEA_ROUTES:
               game.deleteSeaPaths();
               game.buildSeaPaths();
               toast.setInView(langManager.get("Done"));
               break;
            case MAP_EDITOR_WASTELANDMAPS_WORLD_FILL:
               int tempWasteland = 0;
               int tempPlayable = 0;

               for (int m = 0; m < game.getProvincesSize(); m++) {
                  if (game.getProvince(m).getWasteland() >= 0) {
                     tempWasteland++;
                  } else {
                     tempPlayable++;
                  }
               }

               if (tempWasteland > tempPlayable) {
                  for (int mx = 0; mx < game.getProvincesSize(); mx++) {
                     if (!game.getProvince(mx).getSeaProvince()) {
                        game.getProvince(mx).setWasteland(-1);
                     }
                  }
               } else {
                  for (int mxx = 0; mxx < game.getProvincesSize(); mxx++) {
                     if (!game.getProvince(mxx).getSeaProvince()) {
                        game.getProvince(mxx).setWasteland(0);
                     }
                  }

                  game.buildWastelandLevels();
               }

               toast.setInView(langManager.get("Done"));
               break;
            case DELETE_SAVEDGAME:
               SaveManager.deleteSavedGame(MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID);
               menuManager.setViewID(Menu.eLOADGAME);
               break;
            case MAP_EDITOR_SEA_ARMY_BOXES_ROMVE:
               game.getProvince(MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().remove(MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2);
               toast.setInView(langManager.get("Removed"));
               menuManager.setViewIDWithoutAnimation(Menu.eMAP_EDITOR_ARMY_SEA_BOXES_EDIT);
               break;
            case REMOVE_PRINCE:
               toast.setInView(
                  langManager.get("Removed")
                     + ": "
                     + game.getCiv(holyRomanEmpire_Manager.getHRE().getPrince(MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID)).getCivName(),
                  COLOR_BUTTON_GAME_TEXT_ACTIVE
               );
               holyRomanEmpire_Manager.getHRE().removePrince(holyRomanEmpire_Manager.getHRE().getPrince(MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID));
               menuManager.rebuildCreateScenario_HolyRomanEmpire_Princes();
               break;
            case CONVERT_ARMY_POSITION_TO_ANOTHER_SCALE:
               game.convertProvincesArmyPositionToAnotherScale(MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2);
               menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_POSITION);
               menuManager.setBackAnimation(true);
               editorManager.setInUse(Editors.eSHIFT_ARMY);
               break;
            case CONVERT_PORT_POSITION_TO_ANOTHER_SCALE:
               game.convertProvincesPortPositionToAnotherScale(MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2);
               menuManager.setViewID(Menu.eMAP_EDITOR_PORT_POSITION);
               menuManager.setBackAnimation(true);
               editorManager.setInUse(Editors.eSHIFT_PORT);
               break;
            case MANAGE_DIPLOMACY_REMOVE_CIVILIZATION_FROM_ALLIANCE:
               if (MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0) {
                  game.getCiv(MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).setAllianceID(0);
                  game.getAlliance(MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).removeCivilization(MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
                  menuManager.setViewIDWithoutAnimation(Menu.eCUSTOMIZE_ALLIANCE);
               }
               break;
            case REMOVE_CITY:
               Gdx.files.local("map/" + map.getFile_ActiveMap_Path() + "data/cities/" + EDITOR_ACTIVE_GAMEDATA_TAG).delete();

               try {
                  FileHandle file = Gdx.files.internal("map/" + map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations");
                  String tempTags = file.readString();
                  String[] tSplited = tempTags.split(";");
                  if (tSplited.length <= 1) {
                     Gdx.files.local("map/" + map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations").delete();
                  } else {
                     FileHandle fileSave = Gdx.files.local("map/" + map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations");
                     fileSave.writeString("", false);
                     int i2 = 0;

                     for (int iSize = tSplited.length; i2 < iSize; i2++) {
                        if (!tSplited[i2].equals(EDITOR_ACTIVE_GAMEDATA_TAG)) {
                           fileSave.writeString(tSplited[i2] + ";", true);
                        }
                     }
                  }
               } catch (GdxRuntimeException var11) {
                  if (LOGS) {
                     exceptionStack(var11);
                  }
               }

               menuManager.setViewID(Menu.eEDITOR_CITIES);
         }
      } catch (IndexOutOfBoundsException var12) {
         exceptionStack(var12);
      } catch (NullPointerException var13) {
         exceptionStack(var13);
      }
   }

   public static final void dialog_False() {
      MenuElement_Hover_v2.resetAnimation();
      menuManager.getDialogMenu().onBackPressed();
   }

   public static final void updateCreateScenario_Civilizations() {
      if (game.getActiveProvinceID() >= 0) {
         if (game.getProvince(game.getActiveProvinceID()).getSeaProvince() || game.getProvince(game.getActiveProvinceID()).getWasteland() >= 0) {
            menuManager.getCreateScenario_Civilizations().getMenuElement(3).setClickable(false);
            menuManager.getCreateScenario_Civilizations().getMenuElement(4).setClickable(false);
            menuManager.getCreateScenario_Civilizations().getMenuElement(5).setClickable(false);
            menuManager.getCreateScenario_Civilizations().getMenuElement(6).setClickable(false);
            menuManager.setVisible_CreateScenario_Civilizations_Suggest(false);
            menuManager.setVisible_CreateScenario_Civilizations_Ideologies(false);
         } else if (game.getProvince(game.getActiveProvinceID()).getCivID() > 0) {
            if (game.getProvince(game.getActiveProvinceID()).getIsCapital()) {
               menuManager.getCreateScenario_Civilizations().getMenuElement(3).setVisible(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(3).setClickable(false);
               menuManager.getCreateScenario_Civilizations().getMenuElement(4).setVisible(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(4).setClickable(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(5).setVisible(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(5).setClickable(false);
               menuManager.getCreateScenario_Civilizations().getMenuElement(6).setVisible(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(6).setClickable(true);
               menuManager.setVisible_CreateScenario_Civilizations_Suggest(false);
               menuManager.rebuildCreateScenario_Civilizations_Ideologies();
            } else {
               menuManager.getCreateScenario_Civilizations().getMenuElement(3).setVisible(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(3).setClickable(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(4).setVisible(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(4).setClickable(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(5).setVisible(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(5).setClickable(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(6).setVisible(true);
               menuManager.getCreateScenario_Civilizations().getMenuElement(6).setClickable(true);
               menuManager.setVisible_CreateScenario_Civilizations_Ideologies(false);
               menuManager.rebuildCreateScenario_Civilizations_Suggest();
            }
         } else {
            menuManager.getCreateScenario_Civilizations().getMenuElement(3).setVisible(true);
            menuManager.getCreateScenario_Civilizations().getMenuElement(3).setClickable(true);
            menuManager.getCreateScenario_Civilizations().getMenuElement(4).setVisible(true);
            menuManager.getCreateScenario_Civilizations().getMenuElement(4).setClickable(false);
            menuManager.getCreateScenario_Civilizations().getMenuElement(5).setVisible(true);
            menuManager.getCreateScenario_Civilizations().getMenuElement(5).setClickable(false);
            menuManager.getCreateScenario_Civilizations().getMenuElement(6).setVisible(true);
            menuManager.getCreateScenario_Civilizations().getMenuElement(6).setClickable(false);
            menuManager.setVisible_CreateScenario_Civilizations_Ideologies(false);
            menuManager.rebuildCreateScenario_Civilizations_Suggest();
         }
      } else {
         menuManager.getCreateScenario_Civilizations().getMenuElement(3).setVisible(false);
         menuManager.getCreateScenario_Civilizations().getMenuElement(4).setVisible(false);
         menuManager.getCreateScenario_Civilizations().getMenuElement(5).setVisible(false);
         menuManager.getCreateScenario_Civilizations().getMenuElement(6).setVisible(false);
         menuManager.setVisible_CreateScenario_Civilizations_Suggest(false);
         menuManager.setVisible_CreateScenario_Civilizations_Ideologies(false);
      }
   }

   public static final String getAlliances_Random_Names_All_BundleID(Alliances_Names_GameData nEditorAlliancesNames_GameData, int iID) {
      String output = "";

      for (int i = 0; i < nEditorAlliancesNames_GameData.getBundle(iID).getWordsSize(); i++) {
         output = output
            + nEditorAlliancesNames_GameData.getBundle(iID).getWord(i)
            + (i < nEditorAlliancesNames_GameData.getBundle(iID).getWordsSize() - 1 ? ", " : "");
      }

      return output;
   }

   public static final String getRandomAllianceName(Alliances_Names_GameData nEditorAlliancesNames_GameData) {
      String output = "";

      try {
         Random oR = new Random();

         for (int i = 0; i < nEditorAlliancesNames_GameData.getSize(); i++) {
            output = output
               + nEditorAlliancesNames_GameData.getBundle(i).getWord(oR.nextInt(nEditorAlliancesNames_GameData.getBundle(i).getWordsSize()))
               + (i < nEditorAlliancesNames_GameData.getSize() - 1 ? " " : "");
         }
      } catch (IllegalArgumentException var4) {
         if (LOGS) {
            exceptionStack(var4);
         }
      }

      return output;
   }

   public static final void loadRandomAlliancesNames() {
      lRandomAlliancesNamesPackagesTags = new ArrayList<>();

      try {
         FileHandle fileList = Gdx.files.internal("game/alliance_names/Age_of_Civilizations.json");
         String fileContent = fileList.readString();
         Json json = new Json();
         json.setElementType(CFG.ConfigAlliancesData.class, "Data_Random_Alliance_Names", CFG.Data_Random_Alliance_Names.class);
         new CFG.ConfigAlliancesData();
         CFG.ConfigAlliancesData data = json.fromJson(CFG.ConfigAlliancesData.class, fileContent);

         for (Object e : data.Data_Random_Alliance_Names) {
            CFG.Data_Random_Alliance_Names tempData = (CFG.Data_Random_Alliance_Names)e;
            if (tempData.Enabled) {
               lRandomAlliancesNamesPackagesTags.add(tempData.Tag);
            }
         }
      } catch (GdxRuntimeException var7) {
         if (LOGS) {
            exceptionStack(var7);
         }
      }
   }

   public static final String getRandomAllianceName(int inc) {
      if (inc++ > 100) {
         return "";
      } else {
         Random oR = new Random();

         try {
            FileHandle file = Gdx.files
               .internal("game/alliance_names/" + lRandomAlliancesNamesPackagesTags.get(oR.nextInt(lRandomAlliancesNamesPackagesTags.size())));
            Alliances_Names_GameData tempGameData = (Alliances_Names_GameData)deserialize(file.readBytes());
            String output = "";

            for (int i = 0; i < tempGameData.getSize(); i++) {
               output = output
                  + tempGameData.getBundle(i).getWord(oR.nextInt(tempGameData.getBundle(i).getWordsSize()))
                  + (i == tempGameData.getSize() - 1 ? "" : " ");
            }

            for (int var9 = 0; var9 < game.getAlliancesSize(); var9++) {
               if (game.getAlliance(var9).getAllianceName().equals(output)) {
                  return getRandomAllianceName(inc);
               }
            }

            return output;
         } catch (ClassNotFoundException var6) {
         } catch (IOException var7) {
         }

         return "";
      }
   }

   public static final void editorServiceRibbon_Colors_Add() {
      if (editorServiceRibbon_Colors.size() == 0) {
         editorServiceRibbon_Colors.add(new Color(0.9843137F, 0.015686275F, 0.0F, 1.0F));
      } else if (editorServiceRibbon_Colors.size() == 1) {
         editorServiceRibbon_Colors.add(new Color(1.0F, 1.0F, 1.0F, 1.0F));
      } else if (editorServiceRibbon_Colors.size() == 2) {
         editorServiceRibbon_Colors.add(new Color(0.15294118F, 0.3019608F, 0.60784316F, 1.0F));
      } else if (editorServiceRibbon_Colors.size() == 3) {
         editorServiceRibbon_Colors.add(new Color(0.08627451F, 0.14901961F, 0.4509804F, 1.0F));
      } else {
         editorServiceRibbon_Colors.add(getRandomColor());
      }
   }

   public static final String getCityLevelName(int iLevel) {
      switch (iLevel) {
         case 0:
            return langManager.get("Capital");
         case 1:
            return langManager.get("City");
         case 2:
            return langManager.get("Town");
         case 3:
            return langManager.get("Village");
         case 4:
            return langManager.get("Hamlet");
         default:
            return langManager.get("Hamlet");
      }
   }

   public static final int getEditorCityLevel(int nLevel) {
      switch (nLevel) {
         case 0:
            return Images.city;
         case 1:
            return Images.city2;
         case 2:
            return Images.city3;
         case 3:
            return Images.city4;
         case 4:
            return Images.city5;
         default:
            return Images.city2;
      }
   }

   public static final int getCityLevel_Population(float nMax, int nProvincePop, int nCityID) {
      if (nProvincePop / nMax >= 0.85F + 0.2F * nCityID) {
         return Images.city2;
      } else if (nProvincePop / nMax >= 0.55F + 0.2F * nCityID) {
         return Images.city3;
      } else {
         return nProvincePop / nMax >= 0.325F + 0.2F * nCityID ? Images.city4 : Images.city5;
      }
   }

   public static final int getEditorCityLevel_Ref(int nLevel) {
      if (nLevel == Images.city) {
         return 0;
      } else if (nLevel == Images.city2) {
         return 1;
      } else if (nLevel == Images.city3) {
         return 2;
      } else if (nLevel == Images.city4) {
         return 3;
      } else {
         return nLevel == Images.city5 ? 4 : 2;
      }
   }

   public static final byte[] serialize(Object obj) throws IOException {
      ByteArrayOutputStream b = new ByteArrayOutputStream();
      ObjectOutputStream o = new ObjectOutputStream(b);
      o.writeObject(obj);
      return b.toByteArray();
   }

   public static final Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
      b = new ByteArrayInputStream(bytes);
      o = new ObjectInputStream(b);
      return o.readUnshared();
   }

   public static void exceptionStack(Throwable e) {
      e.printStackTrace();
   }

   protected static final void showKeyboard_RenameAlliance() {
      if (!Keyboard.allianceRenameMode) {
         Keyboard.allianceRenameMode = true;
      }

      Keyboard.commandsMode = false;
      Keyboard.colorPickerMode = false;
      updateKeyboard_Actions();
      menuManager.setKeyboardActiveMenuElementID(menuManager.getActiveMenuElementID());
      keyboardMessage = "";
      menuManager.getKeyboard().setVisible(true);
   }

   public static class ConfigAlliancesData {
      public String Age_of_Civilizations;
      public ArrayList Data_Random_Alliance_Names;
   }

   public static class ConfigScenarioInfo {
      public String Age_of_Civilizations;
      public ArrayList Data_Scenario_Info;
   }

   public static class Data_Random_Alliance_Names {
      public String Tag;
      public boolean Enabled;
   }

   public static class Data_Scenario_Info {
      public String Name;
      public String Author;
      public String Wiki;
      public int Civs;
      public int Age;
      public int Year;
      public int Month;
      public int Day;
   }

   public static enum FlagEditorMode {
      PENCIL,
      PAINT_BUCKET;
   }

   interface Keyboard_Action {
      void action();
   }

   interface Keyboard_Action_Write {
      void action(String var1);
   }

   interface RenderUpdate {
      void update(boolean var1);
   }

   public static class TopBox {
      public int iFlagX;
      public int iFlagY;
      public int iCircleShift;
      public int leftExtraViewPadding;
   }
}
