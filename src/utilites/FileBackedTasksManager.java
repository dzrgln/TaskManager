package utilites;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {

    public static void main(String[] args) {
        String s = FileSystems.getDefault().getSeparator();
        InMemoryTaskManager fileBackedTasksManager = new FileBackedTasksManager("D:" + s +
                "dev" + s + "sprint 2" + s + "java-sprint2-hw" + s + "resources" + s + "tasks.txt");

        //Create epic and three subtasks
        // id: 1
        fileBackedTasksManager.addAnyTask(new Epic("Cooking", "Crate dinner"));
        // id: 2
        fileBackedTasksManager.addAnyTask(new SubTask("Shopping", "buy potato", "NEW", 1));
        // id: 3
        fileBackedTasksManager.addAnyTask(new SubTask("Clear potato", "with knife", "IN_PROGRESS", 1));
        // id: 4
        fileBackedTasksManager.addAnyTask(new SubTask("Clear carrot", "with knife", "IN_PROGRESS", 1));
        //Create Tasks.Epic
        // id: 5
        fileBackedTasksManager.addAnyTask(new Epic("Write Diploma", "for order"));
        //Create two tasks
        // id: 6
        fileBackedTasksManager.addAnyTask(new Task("Drink juice", "by juice", "NEW"));
        // id: 7
        fileBackedTasksManager.addAnyTask(new Task("Eat meet", "coo meat", "NEW"));

        System.out.println("-------------Task : " + fileBackedTasksManager.getAnyTask(1));
        System.out.println("History of requests:");
        System.out.println(fileBackedTasksManager.history());

        System.out.println("-------------Task : " + fileBackedTasksManager.getAnyTask(4));
        System.out.println("History of requests:");
        System.out.println(fileBackedTasksManager.history());

        System.out.println("-------------Task : " + fileBackedTasksManager.getAnyTask(2));
        System.out.println("History of requests:");
        System.out.println(fileBackedTasksManager.history());

        System.out.println("-------------Task : " + fileBackedTasksManager.getAnyTask(1));
        System.out.println("History of requests:");
        System.out.println(fileBackedTasksManager.history());

//        InMemoryTaskManager fileBackedTasksManager1 = new FileBackedTasksManager("D:" + s + "dev" + s + "sprint 2" +
//                s + "java-sprint2-hw" + s + "resources" + s + "tasks.txt");
        InMemoryTaskManager fileBackedTasksManager1 = FileBackedTasksManager.loadFromFile("D:" + s +
                "dev" + s + "sprint 2" + s + "java-sprint2-hw" + s + "resources" + s + "tasks.txt");
        System.out.println("History of requests after closing:");
        System.out.println(fileBackedTasksManager1.history());

        System.out.println("Request of some task:");
        System.out.println(fileBackedTasksManager1.getAnyTask(1));
        System.out.println(fileBackedTasksManager1.getAnyTask(4));

    }
    private String path;

    public String getPath() {
        return path;
    }

    public FileBackedTasksManager(String path) {
        this.path = path;
    }

    public static FileBackedTasksManager loadFromFile(String path) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(path);
        for (String string : readTasksFormFile(new File(path))) {
            fillInMaps(string);
        }
        managerHistory.deleteAllHistory();
        for (Integer id : readHistoryFormFile(new File(path))) {
            if (tasks.containsKey(id)) {
                managerHistory.add(tasks.get(id));
            } else if (epics.containsKey(id)) {
                managerHistory.add(epics.get(id));
            } else {
                managerHistory.add(subTasks.get(id));
            }
        }
        return fileBackedTasksManager;
    }

    public static List<String> readTasksFormFile(File file) {
        List<String> strings = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            BufferedReader br = new BufferedReader(reader);
            while (br.ready()) {
                String line = br.readLine();
                if (!line.isEmpty()) {
                    strings.add(line);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла.");
        }
        return strings;
    }

    public static List<Integer> readHistoryFormFile(File file) {
        List<Integer> listOfId = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            BufferedReader br = new BufferedReader(reader);
            boolean isHistory = false;
            while (br.ready()) {
                String line = br.readLine();
                if (isHistory) {
                    for (String id : line.split(",")) {
                        listOfId.add(Integer.parseInt(id));
                    }
                    break;
                }
                if (line.isEmpty()) {
                    isHistory = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла.");
        }
        System.out.println("List of id - " + listOfId);
        return listOfId;
    }

    private void save() throws ManagerSaveException {
        String separator = FileSystems.getDefault().getSeparator();
        try (FileWriter writer = new FileWriter("D:" + separator + "dev" + separator
                + "sprint 2" + separator + "java-sprint2-hw" + separator + "resources" + separator + "tasks.txt")) {
            writer.write("id,type,name,status,description,epic\n");
            for (Task task : getListOfAllTasks()) {
                writer.write(task.toString() + "\n");
            }
            writer.write("\n");
            for (Task task : history()) {
                writer.write(task.getId() + ",");
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Произошла ошибка во время записи файла.");
        }
    }

    private static void fillInMaps(String string) {
        Task task = null;
        switch (string.split(",")[1]) {
            case "Task":
                task = Task.formString(string);
                tasks.put(task.getId(), task);
                break;
            case "Epic":
                task = Epic.formString(string);
                epics.put(task.getId(), (Epic) task);
                break;
            case "SubTask":
                task = SubTask.formString(string);
                subTasks.put(task.getId(), (SubTask) task);
                epics.get(((SubTask) task).getIdOfEpic()).getSubTasks().add((SubTask) task);
                break;
        }
    }

    public List<Task> getListOfAllTasks() {
        return super.getListOfAllTasks();
    }

    @Override
    public List<Task> getListOfTasks() {
        return super.getListOfTasks();
    }

    @Override
    public List<Epic> getListOfEpics() {
        return super.getListOfEpics();
    }

    @Override
    public List<SubTask> getListOfSubTasks() {
        return super.getListOfSubTasks();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        try {
            save();
        } catch (ManagerSaveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Task getAnyTask(int id) {
        Task task = super.getAnyTask(id);
        try {
            save();
        } catch (ManagerSaveException e) {
            System.out.println(e.getMessage());
        }
        return task;
    }

    @Override
    public Task getTask(int id) {
        return super.getTask(id);
    }

    @Override
    public Epic getEpic(int id) {
        return super.getEpic(id);
    }

    @Override
    public SubTask getSubTask(int id) {
        return super.getSubTask(id);
    }

    @Override
    public void addAnyTask(Task task) {
        super.addAnyTask(task);
        try {
            save();
        } catch (ManagerSaveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
    }

    @Override
    public void addEpic(Task task) {
        super.addEpic(task);
    }

    @Override
    public void addSubTask(Task task) {
        super.addSubTask(task);
    }

    @Override
    public void updateAnyTask(int id, Task task) {
        super.updateAnyTask(id, task);
        try {
            save();
        } catch (ManagerSaveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEpic(int id, Task task) {
        super.updateEpic(id, task);
    }

    @Override
    public void updateSubtask(int id, Task task) {
        super.updateSubtask(id, task);
    }

    @Override
    public void updateTask(int id, Task task) {
        super.updateTask(id, task);
    }

    @Override
    public void deleteAnyTask(int id) {
        super.deleteAnyTask(id);
        try {
            save();
        } catch (ManagerSaveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEpic(int id) {
        super.deleteEpic(id);
    }

    @Override
    public void deleteSubTask(int id) {
        super.deleteSubTask(id);
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
    }

    @Override
    public List<SubTask> getSubtasksForEpic(int id) {
        return super.getSubtasksForEpic(id);
    }

    @Override
    public List<Task> history() {
        return super.history();
    }
}
