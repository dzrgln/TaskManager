package tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<SubTask> subTasks = new ArrayList<>();
    private LocalDateTime endTime;

    public Epic(String name, String description) {
        this.name = name;
        this.description = description;
        changeStatus();
    }
    public Epic(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        changeStatus();
    }

    public Epic(Integer id, String name, String description, String status) {
        super(id, name, description, status);
    }


    public static Epic formString(String str) {
        String[] parametersOfTask = str.split(",");
        return new Epic(Integer.parseInt(parametersOfTask[0]), parametersOfTask[2],
                parametersOfTask[4], parametersOfTask[3]);
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    private void updateStartTime(){
        for (SubTask subTask : subTasks) {
            if (startTime == null) {
                startTime = subTask.getStartTime();
            } else if (subTask.getStartTime().isBefore(startTime)) {
                startTime = subTask.getStartTime();
            }
        }
    }

    public LocalDateTime getStartTime() {
        updateStartTime();
        return startTime;
    }

    private void updateDuration(){
        if(this.startTime != null && this.endTime != null){
            this.duration = Duration.between(this.startTime, this.endTime);
        }
    }

    public Duration getDuration(){
        updateDuration();
        return duration;
    }

    private void updateEndTime(){
        for (SubTask subTask : subTasks) {
            if (endTime == null) {
                endTime = subTask.getEndTime();
            } else if (subTask.getStartTime().isAfter(endTime)) {
                endTime = subTask.getEndTime();
            }
        }
    }

    public LocalDateTime getEndTime() {
        updateEndTime();
        return endTime;
    }

    public void updateTimeParameters(){
        updateStartTime();
        updateEndTime();
        updateDuration();
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
        return id + "," + TypesOfTask.Epic + "," + name + "," + status + "," + description + ","
                + startTime  + "," + duration;
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

