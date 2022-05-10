package utilites;

import HttpClient.KVTaskClient;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HTTPTaskManager extends FileBackedTasksManager {
    public static KVTaskClient client;

    public HTTPTaskManager(String URL) throws IOException, InterruptedException {
        super(URL);
        client = new KVTaskClient(super.name);

    }

    @Override
    public void save() {
        StringBuilder builder = new StringBuilder();
        builder.append("id,type,name,status,description,epic,startTime,Duration\n");
        for (Task task : getListOfAllTasks()) {
            builder.append(task.toString()).append("\n");
        }

        String history = history().stream()
                .map(Task::getId)
                .map(Object::toString)
                .collect(Collectors.joining(","));
        builder.append('\n').append(history);
        try {
            client.put("manager", builder.toString());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TaskManager load(String URL) {
        String key = "manager";
        TaskManager manager = null;
        String json = null;
        try {
            manager = new HTTPTaskManager(URL);

            json = client.load(key);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        readTaskFromJson(json).forEach(HTTPTaskManager::fillInMaps);
        managerHistory.deleteAllHistory();
        for (Integer id : readHistoryFromJson(json)) {
            managerHistory = new InMemoryHistoryManager();
            if (tasks.containsKey(id)) {
                managerHistory.add(tasks.get(id));
            } else if (epics.containsKey(id)) {
                managerHistory.add(epics.get(id));
            } else {
                managerHistory.add(subTasks.get(id));
            }
        }
        return manager;
    }

    protected static void fillInMaps(String string) {
        switch (string.split(",")[1]) {
            case "Task":
                Task task = Task.formString(string);
                tasks.put(task.getId(), task);
                break;
            case "Epic":
                task = Epic.formString(string);
                epics.put(task.getId(), (Epic) task);
                break;
            case "SubTask":
                task = SubTask.formString(string);
                subTasks.put(task.getId(), (SubTask) task);
                epics.get(((SubTask) task).getIdOfEpic()).getSubTasks().add((SubTask) task);
                break;
        }
    }

    public static List<String> readTaskFromJson(String json) {
        List<String> tasks = new ArrayList<>(List.of(json.split("\n")));
        tasks.remove(0);
        tasks.remove(tasks.size()-1);
        tasks.remove(tasks.size()-1);
        return tasks;
    }

    public static List<Integer> readHistoryFromJson(String json){
        List<String> strings = List.of(json.split("\n"));
        return Stream.of(strings.get(strings.size()-1).split(","))
                .map(Integer::parseInt).collect(Collectors.toList());
    }


    @Override
    public List<Task> getListOfAllTasks() {
        return super.getListOfAllTasks();
    }

    @Override
    public TreeSet<Task> getPrioritizedTasks() {
        return super.getPrioritizedTasks();
    }

    @Override
    public void validateStartTime(Task newTask) throws IllegalArgumentException {
        super.validateStartTime(newTask);
    }

    @Override
    public List<Task> getListOfTasks() {
        return super.getListOfTasks();
    }

    @Override
    public List<Epic> getListOfEpics() {
        return super.getListOfEpics();
    }

    @Override
    public List<SubTask> getListOfSubTasks() {
        return super.getListOfSubTasks();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
    }

    @Override
    public Task getAnyTask(int id) {
        return super.getAnyTask(id);
    }

    @Override
    public Task getTask(int id) {
        return super.getTask(id);
    }

    @Override
    public Epic getEpic(int id) {
        return super.getEpic(id);
    }

    @Override
    public SubTask getSubTask(int id) {
        return super.getSubTask(id);
    }

    @Override
    public void addAnyTask(Task task) {
        super.addAnyTask(task);
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
    }

    @Override
    public void addEpic(Task task) {
        super.addEpic(task);
    }

    @Override
    public void addSubTask(Task task) {
        super.addSubTask(task);
    }

    @Override
    public void updateAnyTask(int id, Task task) {
        super.updateAnyTask(id, task);
    }

    @Override
    public void updateEpic(int id, Task task) {
        super.updateEpic(id, task);
    }

    @Override
    public void updateSubtask(int id, Task task) {
        super.updateSubtask(id, task);
    }

    @Override
    public void updateTask(int id, Task task) {
        super.updateTask(id, task);
    }

    @Override
    public void deleteAnyTask(int id) {
        super.deleteAnyTask(id);
    }

    @Override
    public void deleteEpic(int id) {
        super.deleteEpic(id);
    }

    @Override
    public void deleteSubTask(int id) {
        super.deleteSubTask(id);
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
    }

    @Override
    public List<SubTask> getSubtasksForEpic(int id) {
        return super.getSubtasksForEpic(id);
    }

    @Override
    public List<Task> history() {
        return super.history();
    }

    @Override
    public void deleteAllHistory() {
        super.deleteAllHistory();
    }

}
