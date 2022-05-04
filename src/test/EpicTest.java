package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.StageOfTask;
import tasks.SubTask;
import utilites.Managers;
import utilites.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {

    public static TaskManager manager;
    @BeforeAll
    public static void createManager() {
        manager = Managers.getDefault();
        manager.addEpic(new Epic("Eat", "Eating"));
    }

    @Test
    public void test1ShouldEmptySubtaskList() {
        assertEquals(0, manager.getEpic(1).getSubTasks().size(), "Список не пустой");
    }

    @Test
    public void test2ShouldNotEmptySubtaskList(){
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.NEW.name(), 1));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.NEW.name(), 1));
        assertEquals(2, manager.getEpic(1).getSubTasks().size(), "Размер списка не равен 2");
    }

    @Test
    public void test3ShouldEpicStatusIsNEW_WhenSubtasksStatusIsNEW(){
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.NEW.name(), 1));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.NEW.name(), 1));
        assertEquals(StageOfTask.NEW, manager.getEpic(1).getStatus(), "Ожидался статут NEW");
    }

    @Test
    public void test4ShouldEpicStatusIsDONE_WhenSubtasksStatusIsDONE(){
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.DONE.name(), 1));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.DONE.name(), 1));
        assertEquals(StageOfTask.DONE, manager.getEpic(1).getStatus(), "Ожидался статут DONE");
    }

    @Test
    public void test5ShouldEpicStatusIsIN_PROGRESS_WhenSubtasksStatusIsDONENEW(){
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.NEW.name(), 1));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.DONE.name(), 1));
        assertEquals(StageOfTask.IN_PROGRESS, manager.getEpic(1).getStatus(), "Ожидался статут IN_PROGRESS");
    }

    @Test
    public void tes6ShouldEpicStatusIsIN_PROGRESS_WhenSubtasksStatusIsIN_PROGRESS(){
        manager.addSubTask(new SubTask("Cook", "Cooking", StageOfTask.IN_PROGRESS.name(), 1));
        manager.addSubTask(new SubTask("Drink", "Drinking", StageOfTask.IN_PROGRESS.name(), 1));
        assertEquals(StageOfTask.IN_PROGRESS, manager.getEpic(1).getStatus(), "Ожидался статут IN_PROGRESS");
    }

}