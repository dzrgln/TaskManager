package test;


import HttpServer.KVServer.KVServer;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import utilites.FileBackedTasksManager;
import utilites.HTTPTaskManager;


import java.io.IOException;


public class HttpTaskManagerTest extends FileBackedTaskManagerTest {
    private KVServer server;
    @BeforeEach

    private void createManager() throws IOException, InterruptedException {

        server = new KVServer();

        server.start();

        manager = new HTTPTaskManager("http://localhost:8079/register");
    }


    @AfterEach

    private void clearManager() {

        server.stop();

        manager.deleteAllTasks();

    }

    @Test

    @Override

    public void testFileBacked1ShouldRestoreListOfTasksFromFile() {

        super.testFileBacked1ShouldRestoreListOfTasksFromFile();

    }

    @Test

    @Override

    public void testFileBacked2ShouldRestoreEmptyListOfTasksFromFile() {

        super.testFileBacked2ShouldRestoreEmptyListOfTasksFromFile();

    }

    @Test

    @Override

    public void testFileBackedHistory1ShouldBeEmptyHistory() {

        super.testFileBackedHistory1ShouldBeEmptyHistory();

    }

    @Test

    @Override

    public void testFileBackedHistory2ShouldBeHistoryWithTwoTasks() {

        super.testFileBackedHistory2ShouldBeHistoryWithTwoTasks();

    }

    @Test

    @Override

    public void testFileBackedHistory3ShouldBeHistoryWithEpic() {

        super.testFileBackedHistory3ShouldBeHistoryWithEpic();

    }

    @Test

    @Override

    public void testGetList1ShouldEmptyListWhenNoAnyTasks() {

        super.testGetList1ShouldEmptyListWhenNoAnyTasks();

    }

    @Test

    @Override

    public void testGetList2ShouldEmptyListWhenThereIsTask() {

        super.testGetList2ShouldEmptyListWhenThereIsTask();

    }

    @Test

    @Override

    public void testGetList3ShouldEmptyListWhenNoAnyEpics() {

        super.testGetList3ShouldEmptyListWhenNoAnyEpics();

    }

    @Test

    @Override

    public void testGetList4ShouldEmptyListWhenThereIsEpic() {

        super.testGetList4ShouldEmptyListWhenThereIsEpic();

    }

    @Test

    @Override

    public void testGetList5ShouldEmptyListWhenNoAnySubTasks() {

        super.testGetList5ShouldEmptyListWhenNoAnySubTasks();

    }

    @Test

    @Override

    public void testGetList6ShouldEmptyListWhenThereIsSubtask() {

        super.testGetList6ShouldEmptyListWhenThereIsSubtask();

    }

    @Test

    @Override

    public void testGet1ShouldThrowExceptionWhenThereIsNoAnyTask() {

        super.testGet1ShouldThrowExceptionWhenThereIsNoAnyTask();

    }

    @Test

    @Override

    public void testGet2ShouldThrowExceptionWhenThereIsNoAnyEpic() {

        super.testGet2ShouldThrowExceptionWhenThereIsNoAnyEpic();

    }

    @Test

    @Override

    public void testGet3ShouldThrowExceptionWhenThereIsNoAnySubtask() {

        super.testGet3ShouldThrowExceptionWhenThereIsNoAnySubtask();

    }

    @Test

    @Override

    public void testGet4ShouldReturnTaskWhenAddTask() {

        super.testGet4ShouldReturnTaskWhenAddTask();

    }

    @Test

    @Override

    public void testGet5ShouldReturnEpicWhenAddEpic() {

        super.testGet5ShouldReturnEpicWhenAddEpic();

    }

    @Test

    @Override

    public void testGet6ShouldReturnSubtaskWhenAddSubtask() {

        super.testGet6ShouldReturnSubtaskWhenAddSubtask();

    }

    @Test

    @Override

    public void testGet7ShouldThrowExceptionWhenRequiredTaskWrongID() {

        super.testGet7ShouldThrowExceptionWhenRequiredTaskWrongID();

    }

    @Test

    @Override

    public void testGet8ShouldThrowExceptionWhenRequiredEpicWrongID() {

        super.testGet8ShouldThrowExceptionWhenRequiredEpicWrongID();

    }

    @Test

    @Override

    public void testGet9ShouldThrowExceptionWhenRequiredSubTaskWrongID() {

        super.testGet9ShouldThrowExceptionWhenRequiredSubTaskWrongID();

    }

    @Test

    @Override

    public void testAdd1ShouldAddTask() {

        super.testAdd1ShouldAddTask();

    }

    @Test

    @Override

    public void testAdd2ShouldAddEpicWithoutSubtaskAndCheckSubtasksList() {

        super.testAdd2ShouldAddEpicWithoutSubtaskAndCheckSubtasksList();

    }

    @Test

    @Override

    public void testAdd3ShouldAddSubtask() {

        super.testAdd3ShouldAddSubtask();

    }

    @Test

    @Override

    public void testAdd4ShouldAddEpicWithSubtaskAndCheckSubtasksList() {

        super.testAdd4ShouldAddEpicWithSubtaskAndCheckSubtasksList();

    }

    @Test

    @Override

    public void testUpdate1ShouldUpdateTask() {

        super.testUpdate1ShouldUpdateTask();

    }

    @Test

    @Override

    public void testUpdate2ShouldUpdateSubtask() {

        super.testUpdate2ShouldUpdateSubtask();

    }

    @Test

    @Override

    public void testUpdate3ShouldUpdateEpic() {

        super.testUpdate3ShouldUpdateEpic();

    }

    @Test

    @Override

    public void testUpdate4WhenIdDoesntExistThrowException() {

        super.testUpdate4WhenIdDoesntExistThrowException();

    }

    @Test

    @Override

    public void testDelete1ShouldDeleteTask() {

        super.testDelete1ShouldDeleteTask();

    }

    @Test

    @Override

    public void testDelete2ShouldDeleteSubTask() {

        super.testDelete2ShouldDeleteSubTask();

    }

    @Test

    @Override

    public void testDelete3ShouldDeleteEpic() {

        super.testDelete3ShouldDeleteEpic();

    }

    @Test

    @Override

    public void testDelete4WhenIdDoesntExistThrowException() {

        super.testDelete4WhenIdDoesntExistThrowException();

    }

    @Test

    @Override

    public void testGettingSubtasksForEpic1ShouldReturnNotEmptyList() {

        super.testGettingSubtasksForEpic1ShouldReturnNotEmptyList();

    }

    @Test

    @Override

    public void testGettingSubtasksForEpic2ShouldReturnEmptyList() {

        super.testGettingSubtasksForEpic2ShouldReturnEmptyList();

    }

    @Test

    @Override

    public void estGettingSubtasksForEpic3WhenIdDoesntExistThrowException() {

        super.estGettingSubtasksForEpic3WhenIdDoesntExistThrowException();

    }

}