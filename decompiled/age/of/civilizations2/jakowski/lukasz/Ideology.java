package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Ideology {
   public String sName;
   public String extraTag;
   public int GOV_GROUP_ID;
   public String AI_TYPE;
   public Image iCrownImage = null;
   public Image iCrownVassalImage = null;
   public Image iCrownImageScaled = null;
   public float MIN_GOODS = 0.15F;
   public float MIN_INVESTMENTS = 0.1F;
   public float RESEARCH_COST = 0.15F;
   public float ACCEPTABLE_TAXATION = 0.25F;
   public float MILITARY_UPKEEP = 1.0F;
   public float ADMINISTRATION_COST = 1.0F;
   public float ADMINISTRATION_COST_DISTANCE = 1.0F;
   public float ADMINISTRATION_COST_CAPITAL = 0.6F;
   public float INCOME_TAXATION = 1.0F;
   public float INCOME_PRODUCTION = 1.0F;
   public int COST_OF_MOVE = 6;
   public int COST_OF_MOVE_SAME_PROVINCE = 4;
   public int COST_OF_MOVE_OWN_PROVINCE = 2;
   public int COST_OF_DISBAND = 20;
   public int COST_OF_PLUNDER = 12;
   public int COST_OF_RECRUIT = 2;
   public int DEFENSE_BONUS = 2;
   public int CAN_BECOME_CIVILIZED = -1;
   public float CIVILIZE_TECH_LEVEL = 1.0F;
   public int AVAILABLE_SINCE_AGE_ID = 0;
   public boolean REVOLUTIONARY = false;
   public Color oColor;

   public final float getMin_Goods(int nCivID) {
      return this.MIN_GOODS;
   }

   public final float getMin_Goods() {
      return this.MIN_GOODS;
   }

   public Ideology(
      String sName,
      String extraTag,
      int iR,
      int iG,
      int iB,
      float MIN_GOODS,
      float MIN_INVESTMENTS,
      int COST_OF_MOVE,
      int COST_OF_MOVE_SAME_PROVINCE,
      int COST_OF_MOVE_OWN_PROVINCE,
      float RESEARCH_COST,
      float ACCEPTABLE_TAXATION,
      int DEFENSE_BONUS,
      float MILITARY_UPKEEP,
      float ADMINISTRATION_COST,
      float ADMINISTRATION_COST_DISTANCE,
      float INCOME_TAXATION,
      float INCOME_PRODUCTION,
      int COST_OF_RECRUIT,
      int COST_OF_DISBAND,
      int COST_OF_PLUNDER,
      int CAN_BECOME_CIVILIZED,
      int AVAILABLE_SINCE_AGE_ID,
      float ADMINISTRATION_COST_CAPITAL,
      float CIVILIZE_TECH_LEVEL,
      String AI_TYPE,
      boolean REVOLUTIONARY,
      int GOV_GROUP_ID
   ) {
      this.sName = sName;
      this.extraTag = extraTag;
      this.oColor = new Color(iR / 255.0F, iG / 255.0F, iB / 255.0F, 0.425F);
      this.GOV_GROUP_ID = GOV_GROUP_ID;
      if (RESEARCH_COST < 0.01F) {
         RESEARCH_COST = 0.01F;
      }

      if (ACCEPTABLE_TAXATION < 0.01F) {
         ACCEPTABLE_TAXATION = 0.01F;
      }

      if (ACCEPTABLE_TAXATION > 0.99F) {
         ACCEPTABLE_TAXATION = 0.99F;
      }

      if (MIN_GOODS < 0.01) {
         MIN_GOODS = 0.01F;
      }

      if (MIN_GOODS > 1.0F) {
         MIN_GOODS = 1.0F;
      }

      if (MIN_INVESTMENTS < 0.01) {
         MIN_INVESTMENTS = 0.01F;
      }

      if (MIN_INVESTMENTS > 1.0F) {
         MIN_INVESTMENTS = 1.0F;
      }

      this.ACCEPTABLE_TAXATION = ACCEPTABLE_TAXATION;
      this.MIN_GOODS = MIN_GOODS;
      this.MIN_INVESTMENTS = MIN_INVESTMENTS;
      this.RESEARCH_COST = RESEARCH_COST;
      if (COST_OF_MOVE < 0) {
         COST_OF_MOVE = 0;
      }

      if (COST_OF_MOVE_SAME_PROVINCE < 0) {
         COST_OF_MOVE_SAME_PROVINCE = 0;
      }

      if (COST_OF_MOVE_OWN_PROVINCE < 0) {
         COST_OF_MOVE_OWN_PROVINCE = 0;
      }

      if (COST_OF_DISBAND < 0) {
         COST_OF_DISBAND = 0;
      }

      if (COST_OF_PLUNDER < 0) {
         COST_OF_PLUNDER = 0;
      }

      this.COST_OF_MOVE = COST_OF_MOVE;
      this.COST_OF_MOVE_SAME_PROVINCE = COST_OF_MOVE_SAME_PROVINCE;
      this.COST_OF_MOVE_OWN_PROVINCE = COST_OF_MOVE_OWN_PROVINCE;
      this.COST_OF_DISBAND = COST_OF_DISBAND;
      this.COST_OF_PLUNDER = COST_OF_PLUNDER;
      if (DEFENSE_BONUS < -40) {
         DEFENSE_BONUS = 40;
      }

      if (DEFENSE_BONUS > 40) {
         DEFENSE_BONUS = 40;
      }

      this.DEFENSE_BONUS = DEFENSE_BONUS;
      if (MILITARY_UPKEEP < 0.1) {
         MILITARY_UPKEEP = 0.1F;
      }

      this.MILITARY_UPKEEP = MILITARY_UPKEEP;
      if (ADMINISTRATION_COST < 0.1F) {
         ADMINISTRATION_COST = 0.1F;
      }

      this.ADMINISTRATION_COST = ADMINISTRATION_COST;
      if (ADMINISTRATION_COST_DISTANCE < 0.01) {
         ADMINISTRATION_COST_DISTANCE = 0.01F;
      }

      this.ADMINISTRATION_COST_DISTANCE = ADMINISTRATION_COST_DISTANCE;
      if (INCOME_TAXATION < 0.01) {
         INCOME_TAXATION = 0.01F;
      }

      this.INCOME_TAXATION = INCOME_TAXATION;
      if (INCOME_PRODUCTION < 0.01F) {
         INCOME_PRODUCTION = 0.01F;
      }

      this.INCOME_PRODUCTION = INCOME_PRODUCTION;
      if (COST_OF_RECRUIT < 1) {
         COST_OF_RECRUIT = 1;
      }

      this.COST_OF_RECRUIT = COST_OF_RECRUIT;
      this.CAN_BECOME_CIVILIZED = CAN_BECOME_CIVILIZED;
      this.AVAILABLE_SINCE_AGE_ID = AVAILABLE_SINCE_AGE_ID;
      if (ADMINISTRATION_COST_CAPITAL < 0.01) {
         ADMINISTRATION_COST_CAPITAL = 0.01F;
      } else if (ADMINISTRATION_COST_CAPITAL > 1.0F) {
         ADMINISTRATION_COST_CAPITAL = 1.0F;
      }

      this.ADMINISTRATION_COST_CAPITAL = ADMINISTRATION_COST_CAPITAL;
      if (CIVILIZE_TECH_LEVEL < 0.01) {
         CIVILIZE_TECH_LEVEL = 0.01F;
      }

      this.CIVILIZE_TECH_LEVEL = CIVILIZE_TECH_LEVEL;
      this.REVOLUTIONARY = REVOLUTIONARY;
      this.AI_TYPE = AI_TYPE;

      try {
         this.iCrownImage = new Image(
            new Texture(Gdx.files.internal("UI/icons/crowns/crown" + extraTag + ".png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear
         );
      } catch (GdxRuntimeException var32) {
         this.iCrownImage = new Image(new Texture(Gdx.files.internal("UI/icons/crowns/crown.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear);
      }

      try {
         this.iCrownVassalImage = new Image(
            new Texture(Gdx.files.internal("UI/icons/crowns/crown_x" + extraTag + ".png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear
         );
      } catch (GdxRuntimeException var31) {
         this.iCrownVassalImage = new Image(
            new Texture(Gdx.files.internal("UI/icons/crowns/crown_x.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear
         );
      }

      try {
         this.iCrownImageScaled = new Image(
            new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "crowns/crown" + extraTag + ".png"), Pixmap.Format.RGBA8888, true),
            Texture.TextureFilter.Linear
         );
      } catch (GdxRuntimeException var30) {
         this.iCrownImageScaled = new Image(
            new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "crowns/crown.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear
         );
      }
   }

   public final String getName() {
      return this.sName;
   }

   public final void setName(String sName) {
      this.sName = sName;
   }

   public final String getExtraTag() {
      return this.extraTag;
   }

   public final void setExtraTag(String extraTag) {
      this.extraTag = extraTag;
   }

   public final Color getColor() {
      return this.oColor;
   }

   public final Image getiCrownImage() {
      return this.iCrownImage;
   }

   public final Image getiCrownVassalImage() {
      return this.iCrownVassalImage;
   }

   public final Image getCrownImageScaled() {
      return this.iCrownImageScaled;
   }
}
