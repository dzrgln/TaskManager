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
    }

    @Override
    public void updateHistory() {
        if(lastTasks.size() > 10){
            lastTasks.remove(0);
        }
    }

    @Override
    public void deleteTask(int id, Map<Integer, Task> map) {
        map.remove(id);
    }

    @Override
    public void deleteEpic(int id, Map<Integer, Epic> map) {
        map.remove(id);
    }

    @Override
    public void deleteSubTask(int id, Map<Integer, SubTask> map) {
        map.remove(id);
    }


}
