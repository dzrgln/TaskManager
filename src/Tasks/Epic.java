package Tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subTasks = new ArrayList<>();

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public Epic(String name, String description) {
        this.name = name;
        this.description = description;
        this.setStatus(null);
    }

    @Override
    public void setStatus(String status) {
        boolean isNewSubTask = true;
        boolean isInProgressSubTask = false;
        boolean isDoneSubTask = true;

        //Check existing of subtasks
        if (subTasks.isEmpty()) {
            this.status = "NEW";
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
            this.status = "NEW";
        }
        if (isDoneSubTask) {
            this.status = "DONE";
        }
        if (isInProgressSubTask) {
            this.status = "IN_PROGRESS";
        }
    }

//    public void changeStatus() {
//        boolean isNewSubTask = true;
//        boolean isInProgressSubTask = false;
//        boolean isDoneSubTask = true;
//
//        //Check existing of subtasks
//        if (subTasks.isEmpty()) {
//            this.status = "NEW";
//        }
//        //Check status of subtasks
//        for (SubTask subTask : subTasks) {
//            if (!subTask.getStatus().equals("NEW")) {
//                isNewSubTask = false;
//            }
//            if (!subTask.getStatus().equals("DONE")) {
//                isDoneSubTask = false;
//            }
//            if (!isDoneSubTask && !isNewSubTask) {
//                isInProgressSubTask = true;
//            }
//        }
//        //Change current status
//        if (isNewSubTask) {
//            this.status = "NEW";
//        }
//        if (isDoneSubTask) {
//            this.status = "DONE";
//        }
//        if (isInProgressSubTask) {
//            this.status = "IN_PROGRESS";
//        }
//    }
}

