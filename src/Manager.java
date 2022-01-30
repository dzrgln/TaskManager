import java.util.*;

public class Manager {
    private static int id = 1;
    private List<Map<Integer, Task>> listOfTasks = new ArrayList<>();
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, SubTask> subTasks = new HashMap<>();

    List<Task> getListOfAllTasks() {
        List<Task> allTasks = new ArrayList<>();

        for (Task task : tasks.values()) {
            allTasks.add(task);
        }
        for (Epic epic : epics.values()) {
            allTasks.add(epic);
        }
        for (SubTask subTask : subTasks.values()) {
            allTasks.add(subTask);
        }
        return allTasks;
    }

    List<Task> getListOfTasks() {
        List<Task> allTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            allTasks.add(task);
        }
        return allTasks;
    }

    List<Epic> getListOfEpics() {
        List<Epic> allTasks = new ArrayList<>();
        for (Epic epic : epics.values()) {
            allTasks.add(epic);
        }
        return allTasks;
    }

    List<SubTask> getListOfSubTasks() {
        List<SubTask> allTasks = new ArrayList<>();
        for (SubTask subTask : subTasks.values()) {
            allTasks.add(subTask);
        }
        return allTasks;
    }

    void deleteAllTasks() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }

    Task getTask(int id) {
        Task requiredTask = null;
        List<Task> allTasks = getListOfAllTasks();
        for (Task task : allTasks) {
            if (task.getId() == id) {
                requiredTask = task;
            }
        }
        return requiredTask;
    }

    void createTask(Task task) {
        while (true) {
            if (task instanceof Epic) {
                int idTask = id++;
                epics.put(idTask, (Epic) task);
                task.setId(idTask);
                break;
            }
            if (task instanceof SubTask) {
                int idTask = id++;
                subTasks.put(idTask, (SubTask) task);
                task.setId(idTask);
                for (Epic epic : epics.values()) {
                    if (epic.name.equals(((SubTask) task).getNameOfEpic())) {
                        epic.getSubTasks().add((SubTask) task);
                    }
                    epic.changeStatus();
                }
                break;
            }
            if (task instanceof Task) {
                int idTask = id++;
                tasks.put(idTask, task);
                task.setId(idTask);
                break;
            }
        }
    }

    void updateTask(int id, Task task) {
        while (true) {
            if (task instanceof Epic) {
                updateTaskEpic(id, task);
                break;
            }
            if (task instanceof SubTask) {
                updateTaskSubtask(id, task);
                break;
            }
            if (task instanceof Task) {
                updateTaskTask(id, task);
                break;
            }
        }
    }

    void updateTaskEpic(int id, Task task) {
        for (Epic epic : epics.values()) {
            if (epic.getId() == id) {
                epic.setName(task.name);
                epic.setDescription(task.description);
            }
        }
    }

    void updateTaskSubtask(int id, Task task) {
        for (SubTask subTask : subTasks.values()) {
            if (subTask.getId() == id) {
                subTask.setName(task.name);
                subTask.setDescription(task.description);
                subTask.setNameOfEpic(((SubTask) task).getNameOfEpic());
                subTask.setStatus(task.getStatus());
                for (Epic epic : epics.values()) {
                    if (epic.name.equals(subTask.getNameOfEpic())) {
                        epic.changeStatus();
                    }
                }
            }
        }
    }

    void updateTaskTask(int id, Task task) {
        for (Task task1 : tasks.values()) {
            if (task1.getId() == id) {
                task1.setName(task.name);
                task1.setDescription(task.description);
                task1.setStatus(task.status);
            }
        }
    }

    void deleteTask(int id) {
        List<Task> allTasks = getListOfAllTasks();
        for (Task task : allTasks) {
            if (task.id == id) {
                if (task instanceof Task) {
                    tasks.remove(id);
                }
                if (task instanceof SubTask) {
                    subTasks.remove(id);
                }
                if (task instanceof Epic) {
//                    System.out.println(Arrays.toString(getSubtasksForEpic(id).toArray()));
                    List<SubTask> subTasksForThisEpic = getSubtasksForEpic(id);
                    for (SubTask subTask1 : subTasksForThisEpic) {
                        subTasks.remove(subTask1.getId());
                    }
                    epics.remove(id);
                }
            }
        }
    }

    List<SubTask> getSubtasksForEpic(int id) {
        List<SubTask> subTasksForEpic = null;
        for (Epic epic1 : epics.values()) {
            if (epic1.getId() == id) {
                subTasksForEpic = epic1.getSubTasks();
            }
        }
        return subTasksForEpic;
    }
}
