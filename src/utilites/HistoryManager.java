package utilites;

import tasks.*;

import java.util.List;
import java.util.Map;

public interface HistoryManager {

     List<Task> getHistoryList();

     abstract void add(Task task);

     abstract void updateHistory();

     abstract void deleteTask(int id, Map<Integer, Task> map);

     abstract void deleteEpic(int id, Map<Integer, Epic> map);

     abstract void deleteSubTask(int id, Map<Integer, SubTask> map);

     abstract void deleteAllHistory();
}
