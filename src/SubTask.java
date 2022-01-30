public class SubTask extends Task {
    private String nameOfEpic;

    public String getNameOfEpic() {
        return nameOfEpic;
    }

    public void setNameOfEpic(String nameOfEpic) {
        this.nameOfEpic = nameOfEpic;
    }

    public SubTask(String name, String description, String status, String nameOfEpic) {
        super(name, description, status);
        this.nameOfEpic = nameOfEpic;
    }

    @Override
    public String
    toString() {
        return "SubTask{" +
                "epic='" + nameOfEpic + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
