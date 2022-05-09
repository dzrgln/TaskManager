package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.StageOfTask;
import tasks.SubTask;
import utilites.InMemoryTaskManager;
import utilites.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {

    public static TaskManager manager;

    @BeforeEach
    public void createManager() {
        manager = new InMemoryTaskManager();
        manager.addEpic(new Epic("Eat", "Eating"));
    }

    @AfterEach
    private void clearManager(){
        manager.deleteAllTasks();
    }

    @Test
    public void test1ShouldEmptySubtaskList() {
        assertEquals(0, manager.getEpic(1).getSubTasks().size(), "Список не пустой");
    }

    @Test
    public void test2ShouldNotEmptySubtaskList() {
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.NEW.name(), 1
                , "2021-01-20 12:00", 10));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.NEW.name(), 1
                , "2021-01-20 12:00", 20));
        assertEquals(2, manager.getEpic(1).getSubTasks().size(), "Размер списка не равен 2");
    }

    @Test
    public void test3ShouldEpicStatusIsNEW_WhenSubtasksStatusIsNEW() {
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.NEW.name(), 1
                , "2021-01-20 12:00", 10));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.NEW.name(), 1
                , "2021-01-20 12:00", 20));
        assertEquals(StageOfTask.NEW, manager.getEpic(1).getStatus(), "Ожидался статут NEW");
    }

    @Test
    public void test4ShouldEpicStatusIsDONE_WhenSubtasksStatusIsDONE() {
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.DONE.name(), 1
                , "2021-01-20 12:00", 10));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.DONE.name(), 1
                , "2021-01-20 12:00", 20));
        assertEquals(StageOfTask.DONE, manager.getEpic(1).getStatus(), "Ожидался статут DONE");
    }

    @Test
    public void test5ShouldEpicStatusIsIN_PROGRESS_WhenSubtasksStatusIsDONENEW() {
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.NEW.name(), 1
                , "2021-01-20 12:00", 10));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.DONE.name(), 1
                , "2021-01-20 12:00", 20));
        assertEquals(StageOfTask.IN_PROGRESS, manager.getEpic(1).getStatus(), "Ожидался статут IN_PROGRESS");
    }

    @Test
    public void tes6ShouldEpicStatusIsIN_PROGRESS_WhenSubtasksStatusIsIN_PROGRESS() {
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.IN_PROGRESS.name(), 1
                , "2021-01-20 12:00", 10));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.IN_PROGRESS.name(), 1
                , "2021-01-20 12:00", 20));
        assertEquals(StageOfTask.IN_PROGRESS, manager.getEpic(1).getStatus(), "Ожидался статут IN_PROGRESS");
    }

}