package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Graph_Vertical_Data {
   public int iCivID;
   public List<Graph_Vertical_Data_Value> lValues = new ArrayList<>();
   public boolean inView = true;
   public long lTime = 0L;
   public static final int ANIMATION_TIME = 725;

   public Graph_Vertical_Data(int iCivID) {
      this.iCivID = iCivID;
   }

   public final void drawData(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Color> nColors) {
      if (this.lTime == 0L) {
         this.lTime = System.currentTimeMillis();
      }

      int tempValuesHeight = 0;
      if (this.lTime + 725L > System.currentTimeMillis()) {
         int tempHeight = 0;

         for (int i = 0; i < this.lValues.size(); i++) {
            tempHeight += this.lValues.get(i).getHeight();
         }

         int var17;
         tempValuesHeight = var17 = (int)(tempHeight * ((float)(System.currentTimeMillis() - this.lTime) / 725.0F));
         int tempAnimationHeight = 0;

         for (int var15 = 0; var15 < this.lValues.size(); var15++) {
            try {
               this.lValues
                  .get(var15)
                  .draw(
                     oSB,
                     iPosX,
                     iPosY + iHeight,
                     iWidth,
                     tempAnimationHeight,
                     var17 >= this.lValues.get(var15).getHeight() ? this.lValues.get(var15).getHeight() : var17,
                     nColors.get(this.lValues.get(var15).getDataTypeID())
                  );
            } catch (IndexOutOfBoundsException var14) {
               this.lValues
                  .get(var15)
                  .draw(
                     oSB,
                     iPosX,
                     iPosY + iHeight,
                     iWidth,
                     tempAnimationHeight,
                     var17 >= this.lValues.get(var15).getHeight() ? this.lValues.get(var15).getHeight() : var17,
                     Color.WHITE
                  );
            }

            tempAnimationHeight += this.lValues.get(var15).getHeight();
            if ((var17 -= this.lValues.get(var15).getHeight()) <= 0) {
               break;
            }
         }

         CFG.setRender_3(true);
      } else {
         for (int i = 0; i < this.lValues.size(); i++) {
            try {
               this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, nColors.get(this.lValues.get(i).getDataTypeID()));
            } catch (IndexOutOfBoundsException var13) {
               this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, Color.WHITE);
            }

            tempValuesHeight += this.lValues.get(i).getHeight();
         }
      }

      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextRotated(
         oSB,
         "" + this.getValue(),
         iPosX + iWidth / 2 - (int)(CFG.TEXT_HEIGHT * 0.8F / 2.0F),
         iPosY + iHeight - CFG.PADDING,
         new Color(1.0F, 1.0F, 1.0F, 0.45F),
         90.0F
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);

      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               iPosX,
               iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT - CFG.game.getCiv(this.iCivID).getFlag().getHeight(),
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (IndexOutOfBoundsException var12) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               iPosX,
               iPosY + iHeight - tempValuesHeight - CFG.PADDING - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() - CFG.CIV_FLAG_HEIGHT,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT);
   }

   public final void drawData_ONLY_SPLTTED(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Color> nColors) {
      if (this.lTime == 0L) {
         this.lTime = System.currentTimeMillis();
      }

      int tempValuesHeight = 0;
      if (this.lTime + 725L > System.currentTimeMillis()) {
         int tempHeight = 0;

         for (int i = 0; i < this.lValues.size(); i++) {
            tempHeight += this.lValues.get(i).getHeight();
         }

         int var16;
         tempValuesHeight = var16 = (int)(tempHeight * ((float)(System.currentTimeMillis() - this.lTime) / 725.0F));
         int tempAnimationHeight = 0;

         for (int var14 = 0; var14 < this.lValues.size(); var14++) {
            try {
               this.lValues
                  .get(var14)
                  .draw(
                     oSB,
                     iPosX,
                     iPosY + iHeight,
                     iWidth,
                     tempAnimationHeight,
                     var16 >= this.lValues.get(var14).getHeight() ? this.lValues.get(var14).getHeight() : var16,
                     nColors.get(this.lValues.get(var14).getDataTypeID())
                  );
            } catch (IndexOutOfBoundsException var13) {
               this.lValues
                  .get(var14)
                  .draw(
                     oSB,
                     iPosX,
                     iPosY + iHeight,
                     iWidth,
                     tempAnimationHeight,
                     var16 >= this.lValues.get(var14).getHeight() ? this.lValues.get(var14).getHeight() : var16,
                     Color.WHITE
                  );
            }

            tempAnimationHeight += this.lValues.get(var14).getHeight();
            if ((var16 -= this.lValues.get(var14).getHeight()) <= 0) {
               break;
            }
         }

         CFG.setRender_3(true);
      } else {
         for (int i = 0; i < this.lValues.size(); i++) {
            try {
               this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, nColors.get(this.lValues.get(i).getDataTypeID()));
            } catch (IndexOutOfBoundsException var12) {
               this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, Color.WHITE);
            }

            tempValuesHeight += this.lValues.get(i).getHeight();
         }
      }

      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextRotated(
         oSB,
         "" + this.getValue() / 100.0F,
         iPosX + iWidth / 2 - (int)(CFG.TEXT_HEIGHT * 0.8F / 2.0F),
         iPosY + iHeight - CFG.PADDING,
         new Color(1.0F, 1.0F, 1.0F, 0.45F),
         90.0F
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            iPosX,
            iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT - CFG.game.getCiv(this.iCivID).getFlag().getHeight(),
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT);
   }

   public final void buildHeights(int nGraphHeight, int nMaxValue) {
      for (int i = 0; i < this.lValues.size(); i++) {
         this.lValues.get(i).setHeight((int)((float)this.lValues.get(i).getValue() / nMaxValue * nGraphHeight));
      }
   }

   public final void buildContintentData() {
      this.lValues.clear();
      ArrayList<Integer> numOfProvincesByContinents = new ArrayList<>();

      for (int i = 0; i < CFG.map.getMapContinents().getContinentsSize(); i++) {
         numOfProvincesByContinents.add(0);
      }

      for (int var6 = 0; var6 < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); var6++) {
         numOfProvincesByContinents.set(
            CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(var6)).getContinent(),
            numOfProvincesByContinents.get(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(var6)).getContinent()) + 1
         );
      }

      ArrayList<Graph_Vertical_Data_Value_Continent> tempValues = new ArrayList<>();

      for (int i2 = 0; i2 < CFG.map.getMapContinents().getContinentsSize(); i2++) {
         if (numOfProvincesByContinents.get(i2) > 0) {
            tempValues.add(new Graph_Vertical_Data_Value_Continent(numOfProvincesByContinents.get(i2), i2));
         }
      }

      while (tempValues.size() > 0) {
         int tempMaxID = 0;

         for (int i3 = 1; i3 < tempValues.size(); i3++) {
            if (tempValues.get(tempMaxID).getValue() < tempValues.get(i3).getValue()) {
               tempMaxID = i3;
            }
         }

         this.lValues.add(tempValues.get(tempMaxID));
         tempValues.remove(tempMaxID);
      }
   }

   public final void buildPopulationData() {
      this.lValues.clear();
      ArrayList<Integer> numOfPopulation = new ArrayList<>();

      for (int i2 = 0; i2 < CFG.game.getCivsSize(); i2++) {
         numOfPopulation.add(0);
      }

      for (int var7 = 0; var7 < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); var7++) {
         for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(var7)).getPopulationData().getNationalitiesSize(); j++) {
            numOfPopulation.set(
               CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(var7)).getPopulationData().getCivID(j),
               numOfPopulation.get(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(var7)).getPopulationData().getCivID(j))
                  + CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(var7)).getPopulationData().getPopulationID(j)
            );
         }
      }

      int nSecondBiggestPopulationID = 0;
      int nRestOfPopulation = 0;

      for (int i = nSecondBiggestPopulationID + 1; i < CFG.game.getCivsSize(); i++) {
         if (numOfPopulation.get(nSecondBiggestPopulationID) < numOfPopulation.get(i) && i != this.iCivID) {
            nSecondBiggestPopulationID = i;
         }
      }

      for (int var6 = 0; var6 < CFG.game.getCivsSize(); var6++) {
         if (var6 != nSecondBiggestPopulationID && var6 != this.iCivID) {
            nRestOfPopulation += numOfPopulation.get(var6);
         }
      }

      this.lValues.add(new Graph_Vertical_Data_Value_Population(numOfPopulation.get(this.iCivID), this.iCivID));
      this.lValues.add(new Graph_Vertical_Data_Value_Population(numOfPopulation.get(nSecondBiggestPopulationID), nSecondBiggestPopulationID));
      this.lValues.add(new Graph_Vertical_Data_Value_Population(nRestOfPopulation, 0));
   }

   public final void buildPopulationOfCivilizationAllAroundTheWorldData(int nOfCivID) {
      this.lValues.clear();
      int nPopulation = 0;

      for (int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); i++) {
         nPopulation += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(nOfCivID);
      }

      this.lValues.add(new Graph_Vertical_Data_Value_PopulationAllAroundTheWorld(nPopulation, nOfCivID));
   }

   public final void buildArmiesData() {
      this.lValues.clear();
      int nNumOfUnits = CFG.game.getCiv(this.iCivID).getNumOfUnits();

      for (int i = 0; i < CFG.game.getCiv(this.iCivID).getArmyInAnotherProvinceSize(); i++) {
         nNumOfUnits += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getArmyInAnotherProvince(i)).getArmyCivID(this.iCivID);
      }

      this.lValues.add(new Graph_Vertical_Data_Value_PopulationAllAroundTheWorld(nNumOfUnits, this.iCivID));
   }

   public final void buildArmyPerCapitaData() {
      this.lValues.clear();
      int nPopulation = 0;
      int nNumOfUnits = CFG.game.getCiv(this.iCivID).getNumOfUnits();

      for (int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); i++) {
         nPopulation += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(this.iCivID);
      }

      for (int var4 = 0; var4 < CFG.game.getCiv(this.iCivID).getArmyInAnotherProvinceSize(); var4++) {
         nNumOfUnits += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getArmyInAnotherProvince(var4)).getArmyCivID(this.iCivID);
      }

      int var5;
      this.lValues.add(new Graph_Vertical_Data_Value_ArmyPerCapita((int)(nNumOfUnits * 100.0F / (var5 = nPopulation + nNumOfUnits) * 100.0F), this.iCivID));
   }

   public final void buildTechnologyLevelsData() {
      this.lValues.clear();
      this.lValues.add(new Graph_Vertical_Data_Value_TechnologyLevels((int)(CFG.game.getCiv(this.iCivID).getTechnologyLevel() * 100.0F), this.iCivID));
   }

   public final void buildPopulationByProvincesData() {
      this.lValues.clear();
      this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getProvince(this.iCivID).getPopulationData().getPopulation(), this.iCivID));
      this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
   }

   public final void buildEconomyByProvincesData() {
      this.lValues.clear();
      this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getProvince(this.iCivID).getEconomy(), this.iCivID));
      this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
   }

   public final void buildConqueredProvincesData() {
      this.lValues.clear();
      this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getCiv(this.iCivID).civGameData.iNumOfConqueredProvinces, this.iCivID));
   }

   public final void buildConstructedBuildingsData() {
      this.lValues.clear();
      this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getCiv(this.iCivID).civGameData.iNumOfBuildingsConstructed, this.iCivID));
   }

   public final void buildArmyByProvincesData() {
      this.lValues.clear();
      int nArmySize = 0;

      for (int i = 0; i < CFG.game.getProvince(this.iCivID).getCivsSize(); i++) {
         nArmySize += CFG.game.getProvince(this.iCivID).getArmy(i);
      }

      this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(nArmySize, this.iCivID));
      this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
   }

   public final void buildTechnologyLevelsByProvincesData() {
      this.lValues.clear();
      this.lValues
         .add(new Graph_Vertical_Data_Value_PopulationByProvinces((int)(CFG.game.getProvince(this.iCivID).getDevelopmentLevel() * 100.0F), this.iCivID));
      this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
   }

   public final void buildEconomyData() {
      this.lValues.clear();
      int nEconomy = 0;

      for (int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); i++) {
         nEconomy += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getEconomy();
      }

      this.lValues.add(new Graph_Vertical_Data_Value_Population(nEconomy, this.iCivID));
   }

   public final void buildPopulationOfCivByNationalitiesData(int nCivID) {
      this.lValues.clear();
      int nPopulation = 0;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         nPopulation += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(this.iCivID);
      }

      this.lValues.add(new Graph_Vertical_Data_Value_Population(nPopulation, this.iCivID));
   }

   public final int getCivID() {
      return this.iCivID;
   }

   public final int getValue() {
      int tOut = 0;

      for (int i = 0; i < this.lValues.size(); i++) {
         tOut += this.lValues.get(i).getValue();
      }

      return tOut;
   }

   public final boolean getInView() {
      return this.inView;
   }

   public final void setInView(boolean inView) {
      this.inView = inView;
   }

   public final void resetAnimation() {
      this.lTime = 0L;
   }

   public final int getValuesSize() {
      return this.lValues.size();
   }

   public final int getValue(int i) {
      return this.lValues.get(i).getValue();
   }

   public final int getValueDataTypeID(int i) {
      return this.lValues.get(i).getDataTypeID();
   }
}
