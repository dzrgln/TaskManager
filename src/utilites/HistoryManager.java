package utilites;

import tasks.*;

import java.util.List;
import java.util.Map;

public interface HistoryManager {

     List<Task> getHistoryList();

     void add(Task task);

     void deleteAnyTask(Task task);

     void deleteAllHistory();
}
