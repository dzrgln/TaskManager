package utilites;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import java.util.*;

public class Manager {
    private static int id = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, SubTask> subTasks = new HashMap<>();

    public List<Task> getListOfAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks.values());
        allTasks.addAll(epics.values());
        allTasks.addAll(subTasks.values());
        return allTasks;
    }

    public List<Task> getListOfTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getListOfEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<SubTask> getListOfSubTasks() {
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

    public void addAnyTask(Task task) {
        if (task instanceof Epic) {
            addEpic(task);
        } else if (task instanceof SubTask) {
            addSubTask(task);
        } else {
            addTask(task);
        }
    }

    private void addTask(Task task) {
        int idTask = id++;
        tasks.put(idTask, task);
        task.setId(idTask);
    }

    private void addEpic(Task task) {
        int idTask = id++;
        epics.put(idTask, (Epic) task);
        task.setId(idTask);
        epics.get(idTask).changeStatus();
        if(!((Epic) task).getSubTasks().isEmpty()){
            for(SubTask subTask: ((Epic) task).getSubTasks()){
                subTask.setIdOfEpic(idTask);
                addSubTask(subTask);
            }
        }
    }

    private void addSubTask(Task task) {
        int idTask = id++;
        subTasks.put(idTask, (SubTask) task);
        task.setId(idTask);
        if (epics.containsKey(((SubTask) task).getIdOfEpic())) {
            epics.get(subTasks.get(idTask).getIdOfEpic()).getSubTasks().add((SubTask) task);
            epics.get(subTasks.get(idTask).getIdOfEpic()).changeStatus();
        } else {
            Epic newEpic = new Epic("unknown Epic", "");
            addEpic(newEpic);
            newEpic.getSubTasks().add((SubTask) task);
        }
    }

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

    private void updateEpic(int id, Task task) {
        epics.get(id).setName(task.getName());
        epics.get(id).setDescription(task.getDescription());
    }


    private void updateSubtask(int id, Task task) {
        subTasks.get(id).setName(task.getName());
        subTasks.get(id).setDescription(task.getDescription());
        subTasks.get(id).setIdOfEpic(((SubTask) task).getIdOfEpic());
        subTasks.get(id).setStatus(task.getStatus());
        for (Epic epic : epics.values()) {
            if (epic.getId() == subTasks.get(id).getIdOfEpic()) {
                epic.changeStatus();
            }
        }
    }

    private void updateTask(int id, Task task) {
        tasks.get(id).setName(task.getName());
        tasks.get(id).setDescription(task.getDescription());
        tasks.get(id).setStatus(task.getStatus());
    }

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

    private void deleteEpic(int id) {
        List<SubTask> subTasksForThisEpic = getSubtasksForEpic(id);
        for (SubTask subTask1 : subTasksForThisEpic) {
            subTasks.remove(subTask1.getId());
        }
        epics.remove(id);
    }

    private void deleteSubTask(int id) {
        subTasks.remove(id);
        //removing subtask from its epic and updating epic's status
        epics.get(subTasks.get(id).getIdOfEpic()).getSubTasks().remove(subTasks.get(id));
        epics.get(subTasks.get(id).getIdOfEpic()).changeStatus();
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
