import Tasks.Epic;
import Tasks.SubTask;
import Tasks.Task;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        //Create Tasks.Epic and two subtasks
        manager.createAnyTask(new Epic("Cooking", "Crate dinner"));
        manager.createAnyTask(new SubTask("Shopping", "buy potato", "NEW", 1));
        manager.createAnyTask(new SubTask("Clear potato", "", "IN_PROGRESS", 1));
        //Create Tasks.Epic and one subtask
        manager.createAnyTask(new Epic("Write Diploma", ""));
        manager.createAnyTask(new SubTask("Do literature review ", "To find 50 sources", "NEW"
                , 4));
        //Create two tasks
        manager.createAnyTask(new Task("Drink juice", "", "NEW"));
        manager.createAnyTask(new Task("Eat meet", "", "NEW"));
        System.out.println("Tasks have created");
        //Print tasks
        System.out.println("-Tasks: " + Arrays.toString(manager.getListOfTasks().toArray()));
        System.out.println("-Epics: " + Arrays.toString(manager.getListOfEpics().toArray()));
        System.out.println("-Subtasks: " + Arrays.toString(manager.getListOfSubTasks().toArray()));
        //Update tasks
        manager.updateAnyTask(2, new SubTask("Shopping", "buy potato", "DONE"
                , 1));
        manager.updateAnyTask(3, new SubTask("Clear potato", "", "DONE", 1));
        manager.updateAnyTask(6, new Task("Drink juice", "", "DONE"));
        System.out.println("Tasks have updated");
        //Print tasks
        System.out.println("-Tasks: " + Arrays.toString(manager.getListOfTasks().toArray()));
        System.out.println("-Epics: " + Arrays.toString(manager.getListOfEpics().toArray()));
        System.out.println("-Subtasks: " + Arrays.toString(manager.getListOfSubTasks().toArray()));
        //Delete tasks
        manager.deleteAnyTask(1);
        manager.deleteAnyTask(6);
        System.out.println("Tasks have deleted");
        //Print tasks
        System.out.println("-Tasks: " + Arrays.toString(manager.getListOfTasks().toArray()));
        System.out.println("-Epics: " + Arrays.toString(manager.getListOfEpics().toArray()));
        System.out.println("-Subtasks: " + Arrays.toString(manager.getListOfSubTasks().toArray()));
    }
}
