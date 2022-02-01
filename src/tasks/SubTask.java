package tasks;

public class SubTask extends Task {
    private int idOfEpic;

    public SubTask(String name, String description, String status, int idOfEpic) {
        super(name, description, status);
        this.idOfEpic = idOfEpic;
    }

    public int getIdOfEpic() {
        return idOfEpic;
    }

    public void setIdOfEpic(int idOfEpic) {
        this.idOfEpic = idOfEpic;
    }

    @Override
    public String toString() {
        return "Tasks.SubTask{" +
                "epic='" + idOfEpic + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
