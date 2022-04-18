package utilites;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {

    private final File file;

    public FileBackedTasksManager(File file) {
        this.file = file;
    }

    public static FileBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);
        for (String string : readTasksFormFile(file)) {
            fillInMaps(string);
        }
        managerHistory.deleteAllHistory();
        for (Integer id : readHistoryFormFile(file)) {
            managerHistory = new InMemoryHistoryManager();
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
        return listOfId;
    }

    private void saveInFile() throws ManagerSaveException {
        String separator = FileSystems.getDefault().getSeparator();
        try (FileWriter writer = new FileWriter("resources" + separator + "tasks.txt")) {
            writer.write("id,type,name,status,description,epic, startTime, Duration\n");
            for (Task task : sortedTask) {
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
        switch (string.split(",")[1]) {
            case "Task":
                Task task = Task.formString(string);
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
            saveInFile();
        } catch (ManagerSaveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Task getAnyTask(int id) {
        Task task = super.getAnyTask(id);
        try {
            saveInFile();
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
            saveInFile();
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
            saveInFile();
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
            saveInFile();
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
