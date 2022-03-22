package utilites;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TasksComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private static int id = 1;
    protected final Map<Integer, Task> tasks = new HashMap<>();
    protected final Map<Integer, Epic> epics = new HashMap<>();
    protected final Map<Integer, SubTask> subTasks = new HashMap<>();
    protected static final HistoryManager managerHistory = Managers.getDefaultHistory();
    private final TasksComparator comparator = new TasksComparator();

    @Override
    public List<Task> getListOfAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks.values());
        allTasks.addAll(epics.values());
        allTasks.addAll(subTasks.values());
        allTasks.sort(comparator);
        return allTasks;
    }


    @Override
    public List<Task> getListOfTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getListOfEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<SubTask> getListOfSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
        managerHistory.deleteAllHistory();
    }

    @Override
    public Task getAnyTask(int id) {
        Task requiredTask = null;
        if (epics.containsKey(id)) {
            requiredTask = getEpic(id);
        }
        if (subTasks.containsKey(id)) {
            requiredTask = getSubTask(id);
        }
        if (tasks.containsKey(id)) {
            requiredTask = getTask(id);
        }

        return requiredTask;
    }

    @Override
    public Task getTask(int id) {
        managerHistory.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(int id) {
        managerHistory.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public SubTask getSubTask(int id) {
        managerHistory.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public void addAnyTask(Task task) {

        if (task instanceof Epic) {
            addEpic(task);
        } else if (task instanceof SubTask) {
            addSubTask(task);
        } else {
            addTask(task);
        }
    }

    @Override
    public void addTask(Task task) {
        int idTask = id++;
        tasks.put(idTask, task);
        task.setId(idTask);
    }

    @Override
    public void addEpic(Task task) {
        int idTask = id++;
        epics.put(idTask, (Epic) task);
        task.setId(idTask);
        if (!((Epic) task).getSubTasks().isEmpty()) {
            for (SubTask subTask : ((Epic) task).getSubTasks()) {
                subTask.setIdOfEpic(idTask);
                addSubTask(subTask);
            }
        }
    }

    @Override
    public void addSubTask(Task task) {
        int idTask = id++;
        subTasks.put(idTask, (SubTask) task);
        task.setId(idTask);
        if (epics.containsKey(((SubTask) task).getIdOfEpic())) {
            epics.get(subTasks.get(idTask).getIdOfEpic()).getSubTasks().add((SubTask) task);
        } else {
            Epic newEpic = new Epic("unknown Epic", "");
            addEpic(newEpic);
            newEpic.getSubTasks().add((SubTask) task);
        }
    }

    @Override
    public void updateAnyTask(int id, Task task) {
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

    @Override
    public void updateEpic(int id, Task task) {
        epics.get(id).setName(task.getName());
        epics.get(id).setDescription(task.getDescription());
    }

    @Override
    public void updateSubtask(int id, Task task) {
        subTasks.get(id).setName(task.getName());
        subTasks.get(id).setDescription(task.getDescription());
        subTasks.get(id).setIdOfEpic(((SubTask) task).getIdOfEpic());
        subTasks.get(id).setStatus(task.getStatus());
    }

    @Override
    public void updateTask(int id, Task task) {
        tasks.get(id).setName(task.getName());
        tasks.get(id).setDescription(task.getDescription());
        tasks.get(id).setStatus(task.getStatus());
    }


    @Override
    public void deleteAnyTask(int id) {
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

    @Override
    public void deleteEpic(int id) {
        try {
            managerHistory.remove(epics.get(id));
            for (SubTask subTask1 : getSubtasksForEpic(id)) {
                managerHistory.remove(subTask1);
                subTasks.remove(subTask1.getId());
                epics.remove(id);
            }
        } catch (NullPointerException e) {
            System.out.println("Задачи с таким id не существует");
        }
    }

    @Override
    public void deleteSubTask(int id) {
        try {
            managerHistory.remove(subTasks.get(id));
            //removing subtask from its epic
            epics.get(subTasks.get(id).getIdOfEpic()).getSubTasks().remove(subTasks.get(id));
            subTasks.remove(id);
        } catch (NullPointerException e) {
            System.out.println("Задачи с таким id не существует");
        }
    }

    @Override
    public void deleteTask(int id) {
        try {
            managerHistory.remove(tasks.get(id));
            tasks.remove(id);
        } catch (NullPointerException e) {
            System.out.println("Задачи с таким id не существует");
        }
    }

    @Override
    public List<SubTask> getSubtasksForEpic(int id) {
        List<SubTask> subTasksForEpic = null;
        if (epics.containsKey(id)) {
            subTasksForEpic = epics.get(id).getSubTasks();
        } else {
            System.out.println("Эпик с указанным id отсутствует");
        }
        return subTasksForEpic;
    }

    @Override
    public List<Task> history() {
        return managerHistory.getHistoryList();
    }

    public void deleteAllHistory(){
        managerHistory.deleteAllHistory();
    }

}