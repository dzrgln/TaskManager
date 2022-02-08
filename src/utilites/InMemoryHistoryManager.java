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

    @Override
    public void updateHistory() {
        if(lastTasks.size() > 10){
            lastTasks.remove(0);
        }
    }

    @Override
    public void deleteTask(int id, Map<Integer, Task> map) {
        lastTasks.remove(map.get(id));
    }

    @Override
    public void deleteEpic(int id, Map<Integer, Epic> map) {
        lastTasks.remove(map.get(id));
    }

    @Override
    public void deleteSubTask(int id, Map<Integer, SubTask> map) {
        lastTasks.remove(map.get(id));
    }

    @Override
    public void deleteAllHistory() {
        lastTasks.clear();

    }
}
