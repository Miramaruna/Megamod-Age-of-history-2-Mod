package age.of.civilizations2.jakowski.lukasz;

public class ScriptMenu {
   public static void run() {
      CFG.game.getProvince(CFG.oR.nextInt(CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()))).setTrueOwnerOfProvince(25);

      for (int i = 0; i < CFG.game.getCivsSize(); i++) {
         CFG.game.getCiv(i).setCivName(CFG.game.getCiv(CFG.oR.nextInt(0, CFG.game.getCivsSize())).getCivName());
         CFG.game.getCiv(i).setIdeologyID(CFG.oR.nextInt(0, CFG.ideologiesManager.getIdeologiesSize()));
         CFG.game.getCiv(i).setMoney(CFG.oR.nextInt(0, 1000000));
      }
   }
}
