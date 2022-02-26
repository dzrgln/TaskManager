package utilites;

import tasks.*;

import java.util.List;
import java.util.Map;

public interface HistoryManager {

     List<Task> getHistoryList();

     void add(Task task);

     void remove(Task task);

     void deleteAllHistory();

}
