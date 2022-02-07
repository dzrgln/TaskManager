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

    default void addTask(Task task) {
    }

    default void addEpic(Task task) {
    }

    default void addSubTask(Task task) {
    }

    void updateAnyTask(int id, Task task);

    default void updateEpic(int id, Task task) {
    }

    default void updateSubtask(int id, Task task) {
    }

    default void updateTask(int id, Task task) {
    }

    void deleteAnyTask(int id);

    default void deleteEpic(int id) {
    }

    default void deleteSubTask(int id) {
    }

    default void deleteTask(int id) {

    }

    List<SubTask> getSubtasksForEpic(int id);

    List<Task> history();


}
