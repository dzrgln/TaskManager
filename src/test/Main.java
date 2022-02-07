package test;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utilites.InMemoryTaskManager;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        //Create Tasks.Epic and two subtasks
        inMemoryTaskManager.addAnyTask(new Epic("Cooking", "Crate dinner"));
        inMemoryTaskManager.addAnyTask(new SubTask("Shopping", "buy potato", "NEW", 1));
        inMemoryTaskManager.addAnyTask(new SubTask("Clear potato", "", "IN_PROGRESS", 1));
        //Create Tasks.Epic and one subtask
        inMemoryTaskManager.addAnyTask(new Epic("Write Diploma", ""));
        inMemoryTaskManager.addAnyTask(new SubTask("Do literature review ", "To find 50 sources"
                ,"NEW", 4));
        //Create two tasks
        inMemoryTaskManager.addAnyTask(new Task( "Drink juice", "", "NEW"));
        inMemoryTaskManager.addAnyTask(new Task( "Eat meet", "", "NEW"));
        inMemoryTaskManager.addAnyTask(new Task( "Eat meet1", "", "NEW"));
        inMemoryTaskManager.addAnyTask(new Task( "Eat meet2", "", "NEW"));
        inMemoryTaskManager.addAnyTask(new Task( "Eat meet3", "", "NEW"));
        inMemoryTaskManager.addAnyTask(new Task( "Eat meet4", "", "NEW"));
        System.out.println("Tasks have created");
//        //Print tasks
//        System.out.println("-Tasks: " + Arrays.toString(inMemoryTaskManager.getListOfTasks().toArray()));
//        System.out.println("-Epics: " + Arrays.toString(inMemoryTaskManager.getListOfEpics().toArray()));
//        System.out.println("-Subtasks: " + Arrays.toString(inMemoryTaskManager.getListOfSubTasks().toArray()));
//        //Update tasks
//        inMemoryTaskManager.updateAnyTask(2, new SubTask("Shopping", "buy potato", "DONE"
//                , 1));
//        inMemoryTaskManager.updateAnyTask(3, new SubTask("Clear potato", "", "DONE", 1));
//        inMemoryTaskManager.updateAnyTask(6, new Task( "Drink juice", "", "DONE"));
//        System.out.println("Tasks have updated");
//        //Print tasks
//        System.out.println("-Tasks: " + Arrays.toString(inMemoryTaskManager.getListOfTasks().toArray()));
//        System.out.println("-Epics: " + Arrays.toString(inMemoryTaskManager.getListOfEpics().toArray()));
//        System.out.println("-Subtasks: " + Arrays.toString(inMemoryTaskManager.getListOfSubTasks().toArray()));
//        //Delete tasks
//        inMemoryTaskManager.deleteAnyTask(1);
//        inMemoryTaskManager.deleteAnyTask(6);
//        System.out.println("Tasks have deleted");
//        //Print tasks
//        System.out.println("-Tasks: " + Arrays.toString(inMemoryTaskManager.getListOfTasks().toArray()));
//        System.out.println("-Epics: " + Arrays.toString(inMemoryTaskManager.getListOfEpics().toArray()));
//        System.out.println("-Subtasks: " + Arrays.toString(inMemoryTaskManager.getListOfSubTasks().toArray()));

        //Request tasks and print history
        System.out.println("Task 1: " + inMemoryTaskManager.getAnyTask(1));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(2));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(3));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(4));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(5));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(6));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(7));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(8));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(9));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(10));
        System.out.println("Task 2: " + inMemoryTaskManager.getAnyTask(11));
        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history().size());
    }
}
