package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Civ_TasksManager_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Civ_Task> civTasks = new ArrayList<>();
   public int iTasksSize = 0;

   Civ_TasksManager_GameData() {
   }

   public final void addNewTask(Civ_Task nTask) {
      for (int i = this.civTasks.size() - 1; i >= 0; i--) {
         switch (this.civTasks.get(i).taskType) {
            default:
               if (this.civTasks.get(i).iProvinceID == nTask.iProvinceID) {
                  return;
               }
         }
      }

      this.civTasks.add(nTask);
      this.iTasksSize = this.civTasks.size();
   }

   public final void runTasks() {
      int i = 0;

      while (i < this.iTasksSize) {
         i++;
      }
   }
}
