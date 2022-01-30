
public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected String status;

    public Task(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        while (true) {
            if (status.equals(StageOfTask.DONE.getName()) || status.equals(StageOfTask.NEW.getName())
                    || status.equals(StageOfTask.IN_PROGRESS.getName())) {
                this.status = status;
                break;
            } else {
                System.out.println("Некорректный ввод для задачи " + this.name
                        + ". Допустимые значения для статуса: DONE, NEW, IN_PROGRESS");
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
