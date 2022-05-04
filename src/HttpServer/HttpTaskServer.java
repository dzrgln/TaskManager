package HttpServer;

import HttpServer.handlers.HistoryHandler;
import HttpServer.handlers.TasksHandler;
import com.sun.net.httpserver.HttpServer;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utilites.Managers;
import utilites.TaskManager;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpTaskServer {
    private static final int PORT = 8081;
    private static TaskManager manager = null;

    public static void main(String[] args) throws IOException {
        manager = Managers.getDefault();
        manager.addAnyTask(new Task("OldTask", "sd", "DONE", "2021-03-01 12:00", 40));
        manager.addAnyTask(new Epic("Epic2", "sd"));
        manager.addAnyTask(new SubTask("Ddddd", "sd", "DONE", 2, "2022-03-01 12:00", 40));
        System.out.println(manager.getAnyTask(2));
        System.out.println(manager.getTask(1));
        System.out.println(manager.getAnyTask(3));
        HttpServer httpServer = HttpServer.create();
        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/tasks", new TasksHandler(manager));
        httpServer.createContext("/history", new HistoryHandler(manager));
        httpServer.start();

        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
    }

    public HttpTaskServer(TaskManager manager) throws IOException {
        this.manager = manager;
    }

}
