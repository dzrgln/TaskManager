package tasks;

import java.util.Comparator;

public class TasksComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        if(t1.getClass().toString().equals("Task") && t2.getClass().toString().equals("Epic") ||
                t1.getClass().toString().equals("Task") && t2.getClass().toString().equals("Task") ||
                t1.getClass().toString().equals("Epic") && t2.getClass().toString().equals("Epic") ||
                t1.getClass().toString().equals("Epic") && t2.getClass().toString().equals("Task")) {
            return 0;
        } else if (t1.getClass().toString().equals("Task") && t2.getClass().toString().equals("SubTask") ||
                t1.getClass().toString().equals("Epic") && t2.getClass().toString().equals("SubTask")){
            return -1;
        } else {
            return 1;
        }
    }
}
