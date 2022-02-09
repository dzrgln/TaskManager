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

    Task getAnyTask(int id);

    Task getTask(int id);

    Epic getEpic(int id);

    SubTask getSubTask(int id);

    void addAnyTask(Task task);

    void addTask(Task task);

    void addEpic(Task task);

    void addSubTask(Task task);

    void updateAnyTask(int id, Task task);

    void updateEpic(int id, Task task);

    void updateSubtask(int id, Task task);

    void updateTask(int id, Task task);

    void deleteAnyTask(int id);

    void deleteEpic(int id);

    void deleteSubTask(int id);

    void deleteTask(int id);

    List<SubTask> getSubtasksForEpic(int id);

    List<Task> history();
}
