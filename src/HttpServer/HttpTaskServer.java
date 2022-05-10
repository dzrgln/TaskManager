package HttpServer;

import HttpServer.handlers.HistoryHandler;
import HttpServer.handlers.TasksHandler;
import com.sun.net.httpserver.HttpServer;
import utilites.Managers;
import utilites.TaskManager;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpTaskServer {
    private final int port;

    public HttpTaskServer(int port) {
        this.port = port;
    }

    public void start() throws IOException, InterruptedException {
        TaskManager manager = Managers.getDefault();
        HttpServer httpServer = HttpServer.create();
        httpServer.bind(new InetSocketAddress(port), 0);
        httpServer.createContext("/tasks", new TasksHandler(manager));
        httpServer.createContext("/history", new HistoryHandler(manager));
        httpServer.start();
    }
}
