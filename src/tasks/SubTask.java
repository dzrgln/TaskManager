package tasks;

import java.time.Duration;

public class SubTask extends Task {
    private int idOfEpic;

    public SubTask(String name, String description, String status, int idOfEpic, String startTime,
                   int duration) {
        super(name, description, status, startTime, duration);
        this.idOfEpic = idOfEpic;
    }
    public SubTask(String name, String description, String status, int idOfEpic) {
        super(name, description, status);
        this.idOfEpic = idOfEpic;
    }
    public SubTask(int id, String name, String description, String status, int idOfEpic) {
        super(id, name, description, status);
        this.idOfEpic = idOfEpic;
    }
    public SubTask(Integer id, String name, String description, String status, int idOfEpic, String startTime,
                   int duration) {
        super(id, name, description, status, startTime, duration);
        this.idOfEpic = idOfEpic;
    }

    public static SubTask formString(String str){
        String[] parametersOfTask = str.split(",");
        Duration duration = Duration.parse(parametersOfTask[7]);
        return new SubTask(Integer.parseInt(parametersOfTask[0]), parametersOfTask[2],
                parametersOfTask[4], parametersOfTask[3], Integer.parseInt(parametersOfTask[5]),
                parametersOfTask[6],  (int) (duration.toMinutesPart() + duration.toHours()*60));
    }

    public int getIdOfEpic() {
        return idOfEpic;
    }

    public void setIdOfEpic(int idOfEpic) {
        this.idOfEpic = idOfEpic;
    }

    @Override
    public String toString() {
        return id + "," + TypesOfTask.SUBTASK + "," + name + "," + status + "," + description+ "," + idOfEpic + ","
                + startTime.format(formatterForTime)  + "," + duration;
    }
}
