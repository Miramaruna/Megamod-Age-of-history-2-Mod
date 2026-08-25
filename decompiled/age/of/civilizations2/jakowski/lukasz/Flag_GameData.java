package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flag_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iDivisionID;
   public List<Color_GameData> lDivisionColors = new ArrayList<>();
   public List<Flag_Overlay_GameData> lOverlays = new ArrayList<>();
}
