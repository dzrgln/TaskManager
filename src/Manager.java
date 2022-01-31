import Tasks.Epic;
import Tasks.SubTask;
import Tasks.Task;

import java.util.*;

public class Manager {
    private static int id = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, SubTask> subTasks = new HashMap<>();

    List<Task> getListOfAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks.values());
        allTasks.addAll(epics.values());
        allTasks.addAll(subTasks.values());
        return allTasks;
    }

    List<Task> getListOfTasks() {
        return new ArrayList<>(tasks.values());
    }

    List<Epic> getListOfEpics() {
        return new ArrayList<>(epics.values());
    }

    List<SubTask> getListOfSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    void deleteAllTasks() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }

    Task getTask(int id) {
        Task requiredTask = null;
        if (tasks.containsKey(id)) {
            requiredTask = tasks.get(id);
        }
        if (epics.containsKey(id)) {
            requiredTask = epics.get(id);
        }
        if (subTasks.containsKey(id)) {
            requiredTask = subTasks.get(id);
        }
        return requiredTask;
    }

    void createAnyTask(Task task) {
        if (task instanceof Epic) {
            createEpic(task);
        } else if (task instanceof SubTask) {
            createSubTask(task);
        } else {
            createTask(task);
        }
    }

    private void createTask(Task task) {
        int idTask = id++;
        tasks.put(idTask, task);
        task.setId(idTask);
    }

    private void createEpic(Task task) {
        int idTask = id++;
        epics.put(idTask, (Epic) task);
        task.setId(idTask);
        epics.get(idTask).setStatus(null);
    }

    private void createSubTask(Task task) {
        int idTask = id++;
        subTasks.put(idTask, (SubTask) task);
        task.setId(idTask);
        for (Epic epic : epics.values()) {
            if (epic.getId() == ((SubTask) task).getIdOfEpic()) {
                epic.getSubTasks().add((SubTask) task);
                epic.setStatus(null);
            }
        }
    }

    void updateAnyTask(int id, Task task) {
        if (epics.containsKey(id)) {
            updateEpic(id, task);
        } else if (tasks.containsKey(id)) {
            updateTask(id, task);
        } else if (subTasks.containsKey(id)) {
            updateSubtask(id, task);
        } else {
            System.out.println("Указанная задача отсутствует в планировщике задач");
        }
    }

    private void updateEpic(int id, Task task) {
        for (Epic epic : epics.values()) {
            if (epic.getId() == id) {
                epic.setName(task.getName());
                epic.setDescription(task.getDescription());
            }
        }
    }

    private void updateSubtask(int id, Task task) {
        for (SubTask subTask : subTasks.values()) {
            if (subTask.getId() == id) {
                subTask.setName(task.getName());
                subTask.setDescription(task.getDescription());
                subTask.setIdOfEpic(((SubTask) task).getIdOfEpic());
                subTask.setStatus(task.getStatus());
                for (Epic epic : epics.values()) {
                    if (epic.getId() == subTask.getIdOfEpic()) {
                        epic.setStatus(null);
                    }
                }
            }
        }
    }

    private void updateTask(int id, Task task) {
        for (Task task1 : tasks.values()) {
            if (task1.getId() == id) {
                task1.setName(task.getName());
                task1.setDescription(task.getDescription());
                task1.setStatus(task.getStatus());
            }
        }
    }

    void deleteAnyTask(int id) {
        if (epics.containsKey(id)) {
            deleteEpic(id);
        } else if (subTasks.containsKey(id)) {
            deleteSubTask(id);
        } else if (tasks.containsKey(id)) {
            deleteTask(id);
        } else {
            System.out.println("Задачи с указанным id не существует");
        }
    }

    private void deleteEpic(int id) {
        List<SubTask> subTasksForThisEpic = getSubtasksForEpic(id);
        for (SubTask subTask1 : subTasksForThisEpic) {
            subTasks.remove(subTask1.getId());
        }
        epics.remove(id);
    }

    private void deleteSubTask(int id) {
        subTasks.remove(id);
    }

    private void deleteTask(int id) {
        tasks.remove(id);
    }

    List<SubTask> getSubtasksForEpic(int id) {
        List<SubTask> subTasksForEpic = null;
        if (epics.containsKey(id)) {
            subTasksForEpic = epics.get(id).getSubTasks();
        } else {
            System.out.println("Эпик с указанным id отсутствует");
        }
        return subTasksForEpic;
    }
}
