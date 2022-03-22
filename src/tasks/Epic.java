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

    public Epic(Integer id, String name, String description, String status) {
        super(id, name, description, status);
    }


    public static Epic formString(String str){
        String[] parametersOfTask = str.split(",");
        return new Epic(Integer.parseInt(parametersOfTask[0]), parametersOfTask[2],
                parametersOfTask[4], parametersOfTask[3]);
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    @Override
    public StageOfTask getStatus() {
        changeStatus();
        return super.getStatus();
    }

    @Override
    public void setStatus(StageOfTask status) {
        System.out.println("Статус эпика обновлен в зависимости от статусов их подзадач");
        changeStatus();
    }

    @Override
    public String toString() {
        changeStatus();
        return id + "," + TypesOfTask.Epic + "," + name + "," + status + "," + description;
    }

    private void changeStatus() {
        boolean isNewSubTask = true;
        boolean isInProgressSubTask = false;
        boolean isDoneSubTask = true;

        //Check existing of subtasks
        if (subTasks.isEmpty()) {
            this.status = StageOfTask.NEW;
        }
        //Check status of subtasks
        for (SubTask subTask : subTasks) {
            if (!subTask.getStatus().equals(StageOfTask.NEW)) {
                isNewSubTask = false;
            }
            if (!subTask.getStatus().equals(StageOfTask.DONE)) {
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

