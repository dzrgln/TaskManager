package tasks;

import java.util.Objects;

public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected StageOfTask status;
    protected boolean isNew = true;

    public Task(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = StageOfTask.valueOf(status);
    }

    public Task(Integer id, String name, String description, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = StageOfTask.valueOf(status);
    }

    public Task() {
    }

    public static Task formString(String str){
        String[] parametersOfTask = str.split(",");
        return new Task(Integer.parseInt(parametersOfTask[0]), parametersOfTask[2],
                parametersOfTask[4], parametersOfTask[3]);
    }

    public void setId(int id) {
        if (isNew) {
            this.id = id;
            isNew = false;
        } else {
            System.out.println("Изменение идентификатора невозможно");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(StageOfTask status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public StageOfTask getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task otherTask = (Task) obj;
        return id == otherTask.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + "," + TypesOfTask.Task + "," + name + "," + status + "," + description;
    }
}
