package tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subTasks = new ArrayList<>();

    public Epic(String name, String description) {
        this.name = name;
        this.description = description;
        changeStatus();
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    @Override
    public String getStatus() {
        changeStatus();
        return super.getStatus();
    }

    @Override
    public void setStatus(String status) {
        System.out.println("Статус эпика обновлен в зависимости от статусов их подзадач");
        changeStatus();
    }

    public void changeStatus() {
        boolean isNewSubTask = true;
        boolean isInProgressSubTask = false;
        boolean isDoneSubTask = true;

        //Check existing of subtasks
        if (subTasks.isEmpty()) {
            this.status = StageOfTask.NEW;
        }
        //Check status of subtasks
        for (SubTask subTask : subTasks) {
            if (!subTask.getStatus().equals("NEW")) {
                isNewSubTask = false;
            }
            if (!subTask.getStatus().equals("DONE")) {
                isDoneSubTask = false;
            }
            if (!isDoneSubTask && !isNewSubTask) {
                isInProgressSubTask = true;
            }
        }
        //Change current status
        if (isNewSubTask) {
            this.status = StageOfTask.NEW;
        }
        if (isDoneSubTask) {
            this.status = StageOfTask.DONE;
        }
        if (isInProgressSubTask) {
            this.status = StageOfTask.IN_PROGRESS;
        }
    }
}

