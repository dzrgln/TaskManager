package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import utilites.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryHistoryManagerTest extends TaskManagerTest{
    @BeforeEach
    protected void createManager() {
        manager = new InMemoryTaskManager();
    }
    @AfterEach
    protected void clearManager(){
        manager.deleteAllTasks();
    }

    @Test
    public void testAdd1TaskShouldAddInHistory(){
        addTask1();
        manager.getTask(1);
        assertEquals(1, manager.history().size(), "В истории просмотров не одна задача");
    }

    @Test
    public void testAdd2ShouldBeOneTaskAfterDubleRequire(){
        addTask1();
        manager.getTask(1);
        manager.getTask(1);
        assertEquals(1, manager.history().size(), "В истории просмотров не одна задача");
    }

    @Test
    public void testAdd3ShouldBeEmptyListOfHistory(){
        addTask1();
        assertEquals(0, manager.history().size(), "В истории просмотров не ноль задач");
    }

    @Test
    public void testDelete1ShouldDeleteTask(){
        addTask1();
        addTask2();
        manager.getTask(1);
        manager.getTask(2);
        assertEquals(2, manager.history().size(), "В истории просмотров не две задачи");
        manager.deleteTask(1);
        assertEquals(1, manager.history().size(), "В истории просмотров не одна задача");
    }

    @Test
    public void testDelete2ShouldDeleteAllTasks(){
        addTask1();
        addTask2();
        manager.getTask(1);
        manager.getTask(2);
        assertEquals(2, manager.history().size(), "В истории просмотров не две задачи");
        manager.deleteAllHistory();
        assertEquals(0, manager.history().size(), "В истории просмотров не ноль задач");
    }

    public void fillInHistory(){
        addTask1();
        addTask2();
        manager.addTask(new Task("NameTask3", "Desck", "NEW",
                "2020-04-20 10:02", 40));
        manager.getTask(1);
        manager.getTask(2);
        manager.getTask(3);
    }

    @Test
    public void testRemoveNode1ShouldDeleteFirstNode(){
        fillInHistory();
        assertEquals(3, manager.history().size(), "В истории просмотров не три задачи");
        manager.deleteTask(1);
        assertEquals(2, manager.history().size(), "В истории просмотров не две задачи");
    }

    @Test
    public void testRemoveNode2ShouldDeleteLastNode(){
        fillInHistory();
        assertEquals(3, manager.history().size(), "В истории просмотров не три задачи");
        manager.deleteTask(3);
        assertEquals(2, manager.history().size(), "В истории просмотров не две задачи");
    }
    @Test
    public void testRemoveNode3ShouldDeleteMiddleNode(){
        fillInHistory();
        assertEquals(3, manager.history().size(), "В истории просмотров не три задачи");
        manager.deleteTask(2);
        assertEquals(2, manager.history().size(), "В истории просмотров не две задачи");
    }
    @Test
    public void testRemoveNode4ShouldDeleteSingleNode(){
        addTask1();
        manager.getTask(1);
        assertEquals(1, manager.history().size(), "В истории просмотров не одна задачи");
        manager.deleteTask(1);
        assertEquals(0, manager.history().size(), "В истории просмотров не ноль задач");
    }
}