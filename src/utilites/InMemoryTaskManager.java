package utilites;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TasksComparator;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private static int id = 1;
    protected static final Map<Integer, Task> tasks = new HashMap<>();
    protected static final Map<Integer, Epic> epics = new HashMap<>();
    protected static final Map<Integer, SubTask> subTasks = new HashMap<>();
    protected static HistoryManager managerHistory = Managers.getDefaultHistory();
    protected static final TreeSet<Task> sortedTask = new TreeSet<>(new TasksComparator());
    protected final TasksComparator comparator = new TasksComparator();

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
    public TreeSet<Task> getPrioritizedTasks() {
        return sortedTask;
    }

    public void validateStartTime(Task newTask) throws IllegalArgumentException {
        List<Task> listForValidation = new ArrayList<>();
        listForValidation.addAll(tasks.values());
        listForValidation.addAll(subTasks.values());
        listForValidation.remove(newTask);
        for (Task task : listForValidation) {
            if (newTask.getStartTime().isBefore(task.getStartTime()) &&
                    newTask.getEndTime().isAfter(task.getStartTime()) ||
                    newTask.getStartTime().isAfter(task.getStartTime()) &&
                            newTask.getEndTime().isBefore(task.getEndTime()) ||
                    newTask.getStartTime().isEqual(task.getStartTime())) {
                throw new IllegalArgumentException("Время начала задачи пересекается с существующей задачей");
            }
        }

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
        sortedTask.clear();
        managerHistory.deleteAllHistory();
        id = 1;
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
    public Task getTask(int id) throws NullPointerException {
        if (!tasks.containsKey(id)) {
            throw new NullPointerException("Задачи с таким Id не существует");
        } else {
            managerHistory.add(tasks.get(id));
        }
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(int id) throws NullPointerException {
        if (!epics.containsKey(id)) {
            throw new NullPointerException("Задачи с таким Id не существует");
        } else {
            managerHistory.add(epics.get(id));
        }
        return epics.get(id);
    }

    @Override
    public SubTask getSubTask(int id) throws NullPointerException {
        if (!subTasks.containsKey(id)) {
            throw new NullPointerException("Задачи с таким Id не существует");
        } else {
            managerHistory.add(subTasks.get(id));
        }
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
        try {
            validateStartTime(task);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        int idTask = id++;
        tasks.put(idTask, task);
        sortedTask.add(task);
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
        ((Epic) task).updateTimeParameters();
        sortedTask.add(task);
    }

    @Override
    public void addSubTask(Task task) {
        try {
            validateStartTime(task);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        int idTask = id++;
        subTasks.put(idTask, (SubTask) task);
        task.setId(idTask);
        if (epics.containsKey(((SubTask) task).getIdOfEpic())) {
            epics.get(subTasks.get(idTask).getIdOfEpic()).getSubTasks().add((SubTask) task);
            epics.get(subTasks.get(idTask).getIdOfEpic()).updateTimeParameters();
        } else {
            Epic newEpic = new Epic("unknown Epic", "");
            addEpic(newEpic);
            newEpic.getSubTasks().add((SubTask) task);
        }
        sortedTask.add(task);
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
        if (epics.containsKey(1)) {
            epics.get(id).setName(task.getName());
            epics.get(id).setDescription(task.getDescription());
        } else {
            throw new NullPointerException("Задачи с таким ID не существует");
        }
    }

    @Override
    public void updateSubtask(int id, Task task) {
        if (subTasks.containsKey(1)) {
            subTasks.get(id).setDescription(task.getDescription());
            subTasks.get(id).setIdOfEpic(((SubTask) task).getIdOfEpic());
            subTasks.get(id).setStatus(task.getStatus());
            subTasks.get(id).setStartTime(task.getStartTime());
            subTasks.get(id).setDuration(task.getDuration());
        } else {
            throw new NullPointerException("Задачи с таким ID не существует");
        }
    }

    @Override
    public void updateTask(int id, Task task) {
        if (tasks.containsKey(1)) {
            tasks.get(id).setName(task.getName());
            tasks.get(id).setDescription(task.getDescription());
            tasks.get(id).setStatus(task.getStatus());
            tasks.get(id).setStartTime(task.getStartTime());
            tasks.get(id).setDuration(task.getDuration());
        } else {
            throw new NullPointerException("Задачи с таким ID не существует");
        }
    }


    @Override
    public void deleteAnyTask(int id) {
        if (tasks.containsKey(id)) {
            deleteEpic(id);
        } else if (subTasks.containsKey(id)) {
            deleteSubTask(id);
        } else if (epics.containsKey(id)) {
            deleteTask(id);
        } else {
            System.out.println("Задачи с указанным id не существует");
        }
    }

    @Override
    public void deleteEpic(int id) {
        if (epics.containsKey(id)) {
            managerHistory.remove(epics.get(id));
            for (SubTask subTask1 : getSubtasksForEpic(id)) {
                managerHistory.remove(subTask1);
                subTasks.remove(subTask1.getId());
            }
            epics.remove(id);
        } else {
            throw new NullPointerException("Задачи с таким ID: " + id + " не существует");
        }
    }

    @Override
    public void deleteSubTask(int id) {
        if (subTasks.containsKey(id)) {
            managerHistory.remove(subTasks.get(id));
            //removing subtask from its epic
            epics.get(subTasks.get(id).getIdOfEpic()).getSubTasks().remove(subTasks.get(id));
            subTasks.remove(id);
        } else {
            throw new NullPointerException("Задачи с таким ID не существует");
        }
    }

    @Override
    public void deleteTask(int id) {
        if (tasks.containsKey(id)) {
            managerHistory.remove(tasks.get(id));
            tasks.remove(id);
        } else {
            throw new NullPointerException("Задачи с таким ID не существует");
        }
    }

    @Override
    public List<SubTask> getSubtasksForEpic(int id) {
        List<SubTask> subTasksForEpic = null;
        if (epics.containsKey(id)) {
            subTasksForEpic = epics.get(id).getSubTasks();
        } else {
            throw new NullPointerException(("Эпик с таким ID отсутствует"));
        }
        return subTasksForEpic;
    }

    @Override
    public List<Task> history() {
        return managerHistory.getHistoryList();
    }

    @Override
    public void deleteAllHistory() {
        managerHistory.deleteAllHistory();
    }

}