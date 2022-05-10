package test;

import httpserver.kvserver.KVServer;
import tasks.Task;
import utilites.HTTPTaskManager;
import utilites.TaskManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        KVServer server = new KVServer();
        server.start();
        TaskManager manager = new HTTPTaskManager("http://localhost:8079/register");
//        TaskManager manager = HTTPTaskManager.load("http://localhost:8079/register");
        manager.addAnyTask(new Task("Name", "Desc", "NEW"
                , "2021-10-01 12:20", 40));
        manager.addAnyTask(new Task("Name2", "Desc2", "NEW"
                , "2022-10-01 12:20", 40));
        manager.getAnyTask(1);
        manager.getAnyTask(2);




        TaskManager manager11 = HTTPTaskManager.load("http://localhost:8079/register");
        System.out.println("Task" + manager11.getTask(1));


    }

}


//    public static void main(String[] args) {

//        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
//
//        //Create epic and three subtasks
//        // id: 1
//        inMemoryTaskManager.addAnyTask(new Epic("Cooking", "Crate dinner"));
//        // id: 2
//        inMemoryTaskManager.addAnyTask(new SubTask("Shopping", "buy potato", "NEW", 1));
//        // id: 3
//        inMemoryTaskManager.addAnyTask(new SubTask("Clear potato", "with knife", "IN_PROGRESS", 1));
//        // id: 4
//        inMemoryTaskManager.addAnyTask(new SubTask("Clear carrot", "with knife", "IN_PROGRESS", 1));
//        //Create Tasks.Epic
//        // id: 5
//        inMemoryTaskManager.addAnyTask(new Epic("Write Diploma", "for order"));
//        //Create two tasks
//        // id: 6
//        inMemoryTaskManager.addAnyTask(new Task("Drink juice", "by juice", "NEW"));
//        // id: 7
//                System.out.println("History of requests before getting of tasks:");
//        System.out.println(inMemoryTaskManager.history());
//        System.out.println("______________________________________________");
//        //Request tasks and print history
//
//        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(1));
//        System.out.println("History of requests:");
//        System.out.println(inMemoryTaskManager.history());
//
//        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(1));
//        System.out.println("History of requests:");
//        System.out.println(inMemoryTaskManager.history());
//
//        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(1));
//        System.out.println("History of requests:");
//        System.out.println(inMemoryTaskManager.history());
//
//
//        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(7));
//        System.out.println("History of requests:");
//        System.out.println(inMemoryTaskManager.history());
//
//        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(2));
//
//        System.out.println("History of requests:");
//        System.out.println(inMemoryTaskManager.history());
//
//        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(1));
//        System.out.println("History of requests:");
//        System.out.println(inMemoryTaskManager.history());
//
//        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(2));
//        System.out.println("History of requests:");
//        System.out.println(inMemoryTaskManager.history());
//        inMemoryTaskManager.getAnyTask(3);
//        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(3));
//        System.out.println("History of requests:");
//        System.out.println(inMemoryTaskManager.history());
//
//        System.out.println("-------------Task : " + inMemoryTaskManager.getAnyTask(5));
//        System.out.println("History of requests:");
//        System.out.println(inMemoryTaskManager.history());
//
//        //Removing tasks
//        inMemoryTaskManager.deleteAnyTask(6);
//        System.out.println("History of requests after removing id 6:");
//        System.out.println(inMemoryTaskManager.history());
//
//        inMemoryTaskManager.deleteAnyTask(1);
//        System.out.println("History of requests after removing id 1:");
//        System.out.println(inMemoryTaskManager.history());
//
//        inMemoryTaskManager.deleteAllHistory();
//        System.out.println("History of requests after removing all history:");
//        System.out.println(inMemoryTaskManager.history());
//    }

