package utilites;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import java.util.List;

public interface TaskManager {
    List<Task> getListOfAllTasks();

    List<Task> getListOfTasks();

    List<Epic> getListOfEpics();

    List<SubTask> getListOfSubTasks();

    void deleteAllTasks();

    abstract Task getAnyTask(int id);

    abstract Task getTask(int id);

    abstract Epic getEpic(int id);

    abstract SubTask getSubTask(int id);

    abstract void addAnyTask(Task task);

    abstract void addTask(Task task);

    abstract void addEpic(Task task);

    abstract void addSubTask(Task task);

    abstract void updateAnyTask(int id, Task task);

    abstract void updateEpic(int id, Task task);

    abstract void updateSubtask(int id, Task task);

    abstract void updateTask(int id, Task task);

    abstract void deleteAnyTask(int id);

    abstract void deleteEpic(int id);

    abstract void deleteSubTask(int id);

    abstract void deleteTask(int id);

    abstract List<SubTask> getSubtasksForEpic(int id);

    abstract List<Task> history();
}
