package utilites;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

public class Managers {

    public static TaskManager getDefault() throws IOException, InterruptedException {
       return new HTTPTaskManager("http://localhost:8079/register");
    }

    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }

}
