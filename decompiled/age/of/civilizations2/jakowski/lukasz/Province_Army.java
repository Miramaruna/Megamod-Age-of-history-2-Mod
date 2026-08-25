package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Province_Army implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iCivID;
   public int iArmy;
   public int iArmyWidth;

   public Province_Army(int nCivID, int nArmy, int nProvinceID) {
      this.iCivID = nCivID;
      this.setArmy(nArmy, nProvinceID);
   }

   public final void updateArmyWidth_Just(int nProvinceID) {
      try {
         CFG.glyphLayout.setText(CFG.fontArmy, "" + this.iArmy);
         this.iArmyWidth = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var3) {
         CFG.game.addLoadArmiesWidth_ErrorIDs(nProvinceID);
         this.iArmyWidth = 1;
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (NullPointerException var4) {
         CFG.game.addLoadArmiesWidth_ErrorIDs(nProvinceID);
         this.iArmyWidth = 1;
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      } catch (IllegalStateException var5) {
         CFG.game.addLoadArmiesWidth_ErrorIDs(nProvinceID);
         this.iArmyWidth = 1;
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      } catch (IllegalArgumentException var6) {
         CFG.game.addLoadArmiesWidth_ErrorIDs(nProvinceID);
         this.iArmyWidth = 1;
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }
   }

   public final void updateArmyWidth(int nValue) {
      try {
         CFG.glyphLayout.setText(CFG.fontArmy, "" + nValue);
         this.iArmyWidth = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var3) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (NullPointerException var4) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      } catch (IllegalArgumentException var5) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      } catch (IllegalStateException var6) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }
   }

   public final void updateArmyWidth(String nValue) {
      try {
         CFG.glyphLayout.setText(CFG.fontArmy, "" + nValue);
         this.iArmyWidth = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var3) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (NullPointerException var4) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      } catch (IllegalArgumentException var5) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      } catch (IllegalStateException var6) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }
   }

   public final void updateArmyWidth(float nValue) {
      try {
         CFG.glyphLayout.setText(CFG.fontArmy, "" + nValue);
         this.iArmyWidth = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var3) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (NullPointerException var4) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      } catch (IllegalArgumentException var5) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      } catch (IllegalStateException var6) {
         this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }
   }

   public final int getCivID() {
      return this.iCivID;
   }

   public final void setCivID(int nCivID) {
      this.iCivID = nCivID;
   }

   public final int getArmy() {
      return this.iArmy;
   }

   public final void setArmy(int nArmy, int nProvinceID) {
      this.iArmy = Math.max(0, nArmy);
      this.updateArmyWidth_Just(nProvinceID);
   }

   public int getArmyWidth() {
      return this.iArmyWidth;
   }
}
