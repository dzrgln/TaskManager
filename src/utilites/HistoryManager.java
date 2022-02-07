package utilites;

import tasks.*;

import java.util.List;
import java.util.Map;

public interface HistoryManager {

     List<Task> getHistoryList();

     void add(Task task);

     void updateHistory();

     void deleteTask(int id, Map<Integer, Task> map);

     void deleteEpic(int id, Map<Integer, Epic> map);

     void deleteSubTask(int id, Map<Integer, SubTask> map);
}
