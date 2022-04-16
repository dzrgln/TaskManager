package tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected StageOfTask status;
    protected Duration duration;
    protected LocalDateTime startTime;
    protected DateTimeFormatter formatterForTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected boolean isNew = true;

    public Task(String name, String description, String status, String startTime,
                int duration) {
        this.name = name;
        this.description = description;
        this.status = StageOfTask.valueOf(status);
        this.startTime = LocalDateTime.parse(startTime, formatterForTime);
        this.duration = Duration.ofMinutes(duration);
    }

    public Task(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = StageOfTask.valueOf(status);
    }

    public Task(Integer id, String name, String description, String status, String startTime,
                int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = StageOfTask.valueOf(status);
        this.startTime = LocalDateTime.parse(startTime, formatterForTime);
        this.duration = Duration.ofMinutes(duration);
    }

    public Task(Integer id, String name, String description, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = StageOfTask.valueOf(status);
    }

    public Task() {
    }

    public static Task formString(String str) {
        String[] parametersOfTask = str.split(",");
        Duration duration = Duration.parse(parametersOfTask[6]);
        return new Task(Integer.parseInt(parametersOfTask[0]), parametersOfTask[2],
                parametersOfTask[4], parametersOfTask[3], parametersOfTask[5],
                (int) (duration.toMinutesPart() + duration.toHours()*60));
    }

    public void setId(int id) {
        if (isNew) {
            this.id = id;
            isNew = false;
        } else {
            System.out.println("Изменение идентификатора невозможно");
        }
    }

    public void setDuration(Integer duration) {
        this.duration = Duration.ofMinutes(duration);
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime, formatterForTime);

    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
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

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return startTime.plus(duration);
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
        return id + "," + TypesOfTask.Task + "," + name + "," + status + "," + description + ","
                + startTime.format(formatterForTime) + "," + duration;
    }
}
