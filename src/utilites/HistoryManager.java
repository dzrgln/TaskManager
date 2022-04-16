package utilites;

import tasks.Task;

import java.util.List;

public interface HistoryManager {

     List<Task> getHistoryList();

     void add(Task task);

     void remove(Task task);

     void deleteAllHistory();

}
