package tasks;

public class SubTask extends Task {
    private int idOfEpic;

    public SubTask(String name, String description, String status, int idOfEpic) {
        super(name, description, status);
        this.idOfEpic = idOfEpic;
    }
    public SubTask(Integer id, String name, String description, String status, int idOfEpic) {
        super(id, name, description, status);
        this.idOfEpic = idOfEpic;
    }

    public static SubTask formString(String str){
        String[] parametersOfTask = str.split(",");
        return new SubTask(Integer.parseInt(parametersOfTask[0]), parametersOfTask[2],
                parametersOfTask[4], parametersOfTask[3], Integer.parseInt(parametersOfTask[5]));
    }

    public int getIdOfEpic() {
        return idOfEpic;
    }

    public void setIdOfEpic(int idOfEpic) {
        this.idOfEpic = idOfEpic;
    }

    @Override
    public String toString() {
        return id + "," + TypesOfTask.SubTask + "," + name + "," + status + "," + description+ "," + idOfEpic;
    }
}
