import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        //Create Epic and two subtasks
        manager.createTask(new Epic("Cooking", "Crate dinner"));
        manager.createTask(new SubTask("Shopping", "buy potato", "NEW", "Cooking"));
        manager.createTask(new SubTask("Clear potato", "", "IN_PROGRESS", "Cooking"));
        //Create Epic and one subtask
        manager.createTask(new Epic("Write Diploma", ""));
        manager.createTask(new SubTask("Do literature review ", "To find 50 sources", "NEW"
                , "Write Diploma"));
        //Create two tasks
        manager.createTask(new Task("Drink juice", "", "NEW"));
        manager.createTask(new Task("Eat meet", "", "NEW"));
        System.out.println("Tasks have created");
        //Print tasks
        System.out.println("-Tasks: " + Arrays.toString(manager.getListOfTasks().toArray()));
        System.out.println("-Epics: " + Arrays.toString(manager.getListOfEpics().toArray()));
        System.out.println("-Subtasks: " + Arrays.toString(manager.getListOfSubTasks().toArray()));
        //Update tasks
        manager.updateTask(2, new SubTask("Shopping", "buy potato", "DONE"
                , "Cooking"));
        manager.updateTask(3, new SubTask("Clear potato", "", "DONE", "Cooking"));
        manager.updateTask(6, new Task("Drink juice", "", "DONE"));
        System.out.println("Tasks have updated");
        //Print tasks
        System.out.println("-Tasks: " + Arrays.toString(manager.getListOfTasks().toArray()));
        System.out.println("-Epics: " + Arrays.toString(manager.getListOfEpics().toArray()));
        System.out.println("-Subtasks: " + Arrays.toString(manager.getListOfSubTasks().toArray()));
        //Delete tasks
        manager.deleteTask(1);
        manager.deleteTask(6);
        System.out.println("Tasks have deleted");
        //Print tasks
        System.out.println("-Tasks: " + Arrays.toString(manager.getListOfTasks().toArray()));
        System.out.println("-Epics: " + Arrays.toString(manager.getListOfEpics().toArray()));
        System.out.println("-Subtasks: " + Arrays.toString(manager.getListOfSubTasks().toArray()));
    }
}
