package tasks;

public enum StageOfTask {
    NEW("NEW"), IN_PROGRESS("IN_PROGRESS"), DONE("DONE");

    private String name;

    public String getName() {
        return name;
    }

    StageOfTask(String name) {
        this.name = name;
    }
}
