package HttpServer.handlers;

import HttpServer.Serializers.EpicSerializer;
import HttpServer.Serializers.SubtaskSerializer;
import HttpServer.Serializers.TaskSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utilites.TaskManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

public class HistoryHandler implements HttpHandler {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Task.class, new TaskSerializer())
            .registerTypeAdapter(SubTask.class, new SubtaskSerializer())
            .registerTypeAdapter(Epic.class, new EpicSerializer())
            .create();
    private final TaskManager manager;

    public HistoryHandler(TaskManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Началась обработка запроса /history от клиента");

        String[] path = exchange.getRequestURI().getPath().split("/");
        String query = exchange.getRequestURI().getQuery();
        String response = null;
        switch (exchange.getRequestMethod()) {
            case "POST":
                exchange.sendResponseHeaders(201, 0);
                InputStream inputStream = exchange.getRequestBody();
                String body = new String(inputStream.readAllBytes());
                System.out.println(exchange.getRequestURI().getQuery());
                System.out.println(body);
                Stream.of(path).forEach(System.out::println);
                System.out.println(path.length);
                break;
            case "GET":
                exchange.sendResponseHeaders(201, 0);
                response = getHistory();
                break;
            case "DELETE":
                exchange.sendResponseHeaders(201, 0);
                manager.deleteAllHistory();
                response = "История успешно удалена";
                break;
        }
        try (OutputStream os = exchange.getResponseBody()) {
            assert response != null;
            os.write(response.getBytes());
        }
    }

    private String getHistory() {
        return gson.toJson(manager.history());
    }

}

