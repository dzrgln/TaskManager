package utilites;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> lastTasks = new ArrayList<>();

    public List<Task> getHistoryList() {
        return lastTasks;
    }

    @Override
    public void add(Task task) {
        lastTasks.add(task);
        updateHistory();
    }

    private void updateHistory() {
        if(lastTasks.size() > 10){
            lastTasks.remove(0);
        }
    }

    @Override
    public void deleteAnyTask(Task task) {
        lastTasks.remove(task);
    }

    @Override
    public void deleteAllHistory() {
        lastTasks.clear();

    }
}
