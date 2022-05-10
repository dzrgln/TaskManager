package httpserver.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import httpserver.serializers.EpicSerializer;
import httpserver.serializers.SubtaskSerializer;
import httpserver.serializers.TaskSerializer;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TypesOfTask;
import utilites.TaskManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;

public class TasksHandler implements HttpHandler {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Task.class, new TaskSerializer())
            .registerTypeAdapter(SubTask.class, new SubtaskSerializer())
            .registerTypeAdapter(Epic.class, new EpicSerializer())
            .create();
    private final TaskManager manager;

    public TasksHandler(TaskManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Началась обработка запроса /tasks от клиента");
        String[] path = exchange.getRequestURI().getPath().split("/");
        String query = exchange.getRequestURI().getQuery();
        String response = null;
        switch (exchange.getRequestMethod()) {
            case "POST":
                exchange.sendResponseHeaders(201, 0);
                InputStream inputStream = exchange.getRequestBody();
                String body = new String(inputStream.readAllBytes());
                addTask(returnTypeOfTaskFromPath(path), body);
                break;
            case "PUT":
                exchange.sendResponseHeaders(201, 0);
                inputStream = exchange.getRequestBody();
                body = new String(inputStream.readAllBytes());
                updateTask(returnTypeOfTaskFromPath(path), body);
                break;
            case "GET":
                exchange.sendResponseHeaders(201, 0);
                switch (path.length) {
                    case 2:
                        response = getPrioritizedTasks();
                        break;
                    case 3:
                        if (query == null) {
                            response = getListsOfTasks(returnTypeOfTaskFromPath(path));
                        } else {
                            response = getTask(returnTypeOfTaskFromPath(path), returnIdFromQuery(query));
                        }
                        break;
                    case 4:
                        response = getListOfSubtasksForEpic(returnIdFromQuery(query));
                        break;
                }
                break;
            case "DELETE":
                switch (path.length) {
                    case 2:
                        exchange.sendResponseHeaders(201, 0);
                        manager.deleteAllTasks();
                        response = "Все задачи успешно удалены";
                        break;
                    case 3:
                        removeTask(returnTypeOfTaskFromPath(path), returnIdFromQuery(query));
                        response = "Задача с ID: " + returnIdFromQuery(query) + " успешно удалена";
                }
        }
        try (OutputStream os = exchange.getResponseBody()) {
            assert response != null;
            os.write(response.getBytes());
        }
    }

    private String getTask(TypesOfTask task, int id) {
        String gsonTask = null;
        switch (task) {
            case TASK:
                gsonTask = gson.toJson(manager.getTask(id));
                break;
            case EPIC:
                gsonTask = gson.toJson(manager.getEpic(id));
                break;
            case SUBTASK:
                gsonTask = gson.toJson(manager.getSubTask(id));
                break;
        }
        return gsonTask;
    }

    private void addTask(TypesOfTask task, String body) {
        String newBody = body.substring(1, body.length() - 1);
        String[] parametersOfTask = newBody.split(",");
        switch (task) {
            case TASK:
                Duration duration = Duration.parse(parametersOfTask[4]);
                manager.addTask(new Task(parametersOfTask[0],
                        parametersOfTask[1],
                        parametersOfTask[2],
                        parametersOfTask[3],
                        (int) (duration.toMinutesPart() + duration.toHours() * 60)));
                break;
            case EPIC:
                manager.addEpic(new Epic(parametersOfTask[0], parametersOfTask[1]));
                break;
            case SUBTASK:
                duration = Duration.parse(parametersOfTask[5]);
                manager.addSubTask(new SubTask(parametersOfTask[0],
                        parametersOfTask[1],
                        parametersOfTask[2],
                        Integer.parseInt(parametersOfTask[3]),
                        parametersOfTask[4],
                        (int) (duration.toMinutesPart() + duration.toHours() * 60)));
                break;
        }
    }

    private void updateTask(TypesOfTask task, String body) {
        String newBody = body.substring(1, body.length() - 1);
        String[] parametersOfTask = newBody.split(",");
        switch (task) {
            case TASK:
                Duration duration = Duration.parse(parametersOfTask[5]);
                manager.updateTask(Integer.parseInt(parametersOfTask[0]),
                        new Task(parametersOfTask[1],
                                parametersOfTask[2],
                                parametersOfTask[3],
                                parametersOfTask[4],
                                (int) (duration.toMinutesPart() + duration.toHours() * 60)));
                break;
            case EPIC:
                manager.updateEpic(Integer.parseInt(parametersOfTask[0]),
                        new Epic(parametersOfTask[1], parametersOfTask[2]));
                break;
            case SUBTASK:
                duration = Duration.parse(parametersOfTask[6]);
                manager.updateSubtask(Integer.parseInt(parametersOfTask[0]),
                        new SubTask(parametersOfTask[1],
                                parametersOfTask[2],
                                parametersOfTask[3],
                                Integer.parseInt(parametersOfTask[4]),
                                parametersOfTask[5],
                                (int) (duration.toMinutesPart() + duration.toHours() * 60)));
                break;
        }
    }

    private void removeTask(TypesOfTask task, int id) {
        switch (task) {
            case TASK:
                manager.deleteTask(id);
                break;
            case EPIC:
                manager.deleteEpic(id);
                break;
            case SUBTASK:
                manager.deleteSubTask(id);
                break;
        }
    }

    private String getPrioritizedTasks() {
        return gson.toJson(manager.getPrioritizedTasks());
    }

    private String getListsOfTasks(TypesOfTask task) {

        String gsonList = null;
        switch (task) {
            case TASK:
                gsonList = gson.toJson(manager.getListOfTasks());
                break;
            case EPIC:
                gsonList = gson.toJson(manager.getListOfEpics());
                break;
            case SUBTASK:
                gsonList = gson.toJson(manager.getListOfSubTasks());
                break;
        }
        return gsonList;
    }

    private String getListOfSubtasksForEpic(int id){
        return gson.toJson(manager.getEpic(id).getSubTasks());
    }

    private int returnIdFromQuery(String query) {
        String[] strings = query.split("=");
        return Integer.parseInt(strings[1]);
    }

    private TypesOfTask returnTypeOfTaskFromPath(String[] path) {
        return TypesOfTask.valueOf(path[2].toUpperCase());
    }

}

