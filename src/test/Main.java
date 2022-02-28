package test;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utilites.InMemoryTaskManager;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        //Create epic and three subtasks
        // id: 1
        inMemoryTaskManager.addAnyTask(new Epic("Cooking", "Crate dinner"));
        // id: 2
        inMemoryTaskManager.addAnyTask(new SubTask("Shopping", "buy potato", "NEW", 1));
        // id: 3
        inMemoryTaskManager.addAnyTask(new SubTask("Clear potato", "", "IN_PROGRESS", 1));
        // id: 4
        inMemoryTaskManager.addAnyTask(new SubTask("Clear carrot", "", "IN_PROGRESS", 1));
        //Create Tasks.Epic
        // id: 5
        inMemoryTaskManager.addAnyTask(new Epic("Write Diploma", ""));
        //Create two tasks
        // id: 6
        inMemoryTaskManager.addAnyTask(new Task("Drink juice", "", "NEW"));
        // id: 7
        inMemoryTaskManager.addAnyTask(new Task("Eat meet", "", "NEW"));


        System.out.println("History of requests before getting of tasks:");
        System.out.println(inMemoryTaskManager.history());
        System.out.println("______________________________________________");
        //Request tasks and print history

        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(1));
        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history());

        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(1));
        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history());

        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(1));
        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history());

        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(7));
        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history());

        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(2));

        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history());

        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(1));
        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history());

        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(2));
        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history());
        inMemoryTaskManager.getAnyTask(3);
        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(3));
        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history());

        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(5));
        System.out.println("History of requests:");
        System.out.println(inMemoryTaskManager.history());

        //Removing tasks
        inMemoryTaskManager.deleteAnyTask(6);
        System.out.println("History of requests after removing id 6:");
        System.out.println(inMemoryTaskManager.history());

        inMemoryTaskManager.deleteAnyTask(1);
        System.out.println("History of requests after removing id 1:");
        System.out.println(inMemoryTaskManager.history());
    }
}
