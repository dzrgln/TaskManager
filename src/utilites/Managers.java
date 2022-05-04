package utilites;

import java.io.File;
import java.nio.file.FileSystems;

public class Managers {

    public static TaskManager getDefault(){
        String s = FileSystems.getDefault().getSeparator();
       return new FileBackedTasksManager(new File("resources" + s + "tasks.txt"));
    }

    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }

}
