package test;

import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utilites.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class TaskManagerTest <T extends TaskManager> {

    protected T manager;



    protected void addTask1() {
        manager.addTask(new Task("NameTask", "Description", "NEW",
                "2020-03-20 12:02", 40));
    }

    protected void addTask2() {
        manager.addTask(new Task("NameTask", "Description", "NEW",
                "2020-03-20 10:02", 40));
    }

    protected void addEpic() {
        manager.addEpic(new Epic("NameEpic", "Description"));
    }

    protected void addSubtask() {
        manager.addSubTask(new SubTask("NameSubtask", "Description", "NEW",
                2, "2020-03-20 11:02", 40));
    }

    //GetListOfTasks
    @Test
    public void testGetList1ShouldEmptyListWhenNoAnyTasks() {
        assertEquals(0, manager.getListOfTasks().size(), "Список не пуст");
    }

    @Test
    public void testGetList2ShouldEmptyListWhenThereIsTask() {
        addTask1();
        assertEquals(1, manager.getListOfTasks().size(), "В списке не одна задача");
    }

    //GetListOfEpics
    @Test
    public void testGetList3ShouldEmptyListWhenNoAnyEpics() {
        assertEquals(0, manager.getListOfEpics().size(), "Список не пуст");
    }

    @Test
    public void testGetList4ShouldEmptyListWhenThereIsEpic() {
        addEpic();
        assertEquals(1, manager.getListOfEpics().size(), "В списке не одна задача");
    }

    //GetListOfSubTasks
    @Test
    public void testGetList5ShouldEmptyListWhenNoAnySubTasks() {
        assertEquals(0, manager.getListOfSubTasks().size(), "Список не пуст");
    }

    @Test
    public void testGetList6ShouldEmptyListWhenThereIsSubtask() {
        addSubtask();
        assertEquals(1, manager.getListOfSubTasks().size(), "В списке не одна задача");
    }

    @Test
    public void testGet1ShouldThrowExceptionWhenThereIsNoAnyTask() {
        final NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.getTask(1);
                },
                "Исключение не выброшено"
        );
    }

    @Test
    public void testGet2ShouldThrowExceptionWhenThereIsNoAnyEpic() {
        final NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.getEpic(1);
                },
                "Исключение не выброшено"
        );
    }

    @Test
    public void testGet3ShouldThrowExceptionWhenThereIsNoAnySubtask() {
        final NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.getSubTask(1);
                },
                "Исключение не выброшено"
        );
    }

    @Test
    public void testGet4ShouldReturnTaskWhenAddTask() {
        addTask1();
        Task controlTask = new Task(1, "NameTask", "Description", "NEW");
        assertEquals(controlTask, manager.getTask(1), "В менеджере нет переданной задача");
    }

    @Test
    public void testGet5ShouldReturnEpicWhenAddEpic() {
        addEpic();
        Epic controlTask = new Epic(1, "NameEpic", "Description");
        assertEquals(controlTask, manager.getEpic(1), "В менеджере нет переданной задача");
    }

    @Test
    public void testGet6ShouldReturnSubtaskWhenAddSubtask() {
        addEpic();
        addSubtask();
        SubTask controlTask = new SubTask(2, "NameSubtask", "Description", "NEW", 1);
        assertEquals(controlTask, manager.getSubTask(2), "В менеджере нет переданной задача");
    }


    @Test
    public void testGet7ShouldThrowExceptionWhenRequiredTaskWrongID() {
        addTask1();
        final NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.getTask(100);
                },
                "Исключение не выброшено"
        );
    }

    @Test
    public void testGet8ShouldThrowExceptionWhenRequiredEpicWrongID() {
        addEpic();
        final NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.getEpic(100);
                },
                "Исключение не выброшено"
        );
    }

    @Test
    public void testGet9ShouldThrowExceptionWhenRequiredSubTaskWrongID() {
        addSubtask();
        final NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.getSubTask(100);
                },
                "Исключение не выброшено"
        );
    }

    @Test
    public void testAdd1ShouldAddTask() {
        manager.addTask(new Task("Task", "TaskDescr", "NEW",
                "2020-03-20 12:02", 40));
        Task controlTask = new Task(1, "Task", "TaskDescr", "NEW",
                "2020-03-20 12:02", 40);
        assertEquals(controlTask, manager.getAnyTask(1));
    }

    @Test
    public void testAdd2ShouldAddEpicWithoutSubtaskAndCheckSubtasksList() {
        manager.addEpic(new Epic("Epic", "TaskDescr"));
        Epic controlTask = new Epic(1, "Epic", "TaskDescr");
        assertEquals(controlTask, manager.getAnyTask(1));
        assertEquals(0, manager.getEpic(1).getSubTasks().size());
    }

    @Test
    public void testAdd3ShouldAddSubtask() {
        manager.addSubTask(new SubTask("SubTask", "TaskDescr", "NEW", 2,
                "2020-03-20 12:02", 40));
        SubTask controlTask = new SubTask(1, "SubTask", "TaskDescr", "NEW", 2,
                "2020-03-20 12:02", 40);
        assertEquals(controlTask, manager.getAnyTask(1));
    }

    @Test
    public void testAdd4ShouldAddEpicWithSubtaskAndCheckSubtasksList() {
        manager.addEpic(new Epic("Epic", "TaskDescr"));
        manager.addSubTask(new SubTask("SubTask", "TaskDescr", "NEW", 1,
                "2020-03-20 12:02", 40));
        SubTask controlTask = new SubTask(2, "SubTask", "TaskDescr", "NEW", 2,
                "2020-03-20 12:02", 40);
        assertEquals(1, manager.getEpic(1).getSubTasks().size());
        assertEquals(controlTask, manager.getEpic(1).getSubTasks().get(0));
    }

    @Test
    public void testUpdate1ShouldUpdateTask() {
        addTask1();
        manager.updateTask(1, new Task("NewTask", "Description", "NEW",
                "2020-03-20 12:02", 42));
        Task controlTask = new Task(1, "NewTask", "Description", "NEW",
                "2020-03-20 12:02", 42);
        assertEquals(controlTask, manager.getTask(1));
    }

    @Test
    public void testUpdate2ShouldUpdateSubtask() {
        addSubtask();
        manager.updateSubtask(1, new SubTask("NameSubtask", "Description", "NEW",
                2, "2020-03-20 13:10", 40));
        SubTask controlTask = new SubTask(1,"NameSubtask", "Description", "NEW",
                2, "2020-03-20 13:10", 40);
        assertEquals(controlTask, manager.getSubTask(1));
    }

    @Test
    public void testUpdate3ShouldUpdateEpic() {
        addEpic();
        manager.updateEpic(1, new Epic(1, "New name", "Descr"));
        Epic controlTask = new Epic(1, "New name", "Descr" );
        assertEquals(controlTask, manager.getEpic(1));
    }

    @Test
    public void testUpdate4WhenIdDoesntExistThrowException() {
        addTask1();
        addSubtask();
        addEpic();
        final NullPointerException exceptionTask = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.updateTask(100, new Task("NewTask", "Description", "NEW",
                            "2020-03-20 12:02", 42));
                },
                "Исключение не выброшено"
        );
        final NullPointerException exceptionSubtask = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.updateSubtask(100, new SubTask(100,"NameSubtask", "Description", "NEW",
                            2, "2020-03-20 13:10", 40));
                },
                "Исключение не выброшено"
        );
        final NullPointerException exceptionEpic = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.updateEpic(100, new Epic("NewTask", "Description"));
                },
                "Исключение не выброшено"
        );
    }

    @Test
    public void testDelete1ShouldDeleteTask(){
        addTask1();
        manager.deleteTask(1);
        assertEquals(0, manager.getListOfTasks().size(), "Лист задач не пуст");
    }

    @Test
    public void testDelete2ShouldDeleteSubTask(){
        addSubtask();
        manager.deleteSubTask(1);
        assertEquals(0, manager.getListOfSubTasks().size(), "Лист задач не пуст");
    }

    @Test
    public void testDelete3ShouldDeleteEpic(){
        addEpic();
        manager.deleteEpic(1);
        assertEquals(0, manager.getListOfEpics().size(), "Лист задач не пуст");
    }


    @Test
    public void testDelete4WhenIdDoesntExistThrowException() {
        addTask1();
        addSubtask();
        addEpic();
        final NullPointerException exceptionTask = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.deleteTask(100);
                },
                "Исключение не выброшено"
        );
        final NullPointerException exceptionSubtask = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.deleteSubTask(10);
                },
                "Исключение не выброшено"
        );
        final NullPointerException exceptionEpic = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.deleteEpic(15);
                },
                "Исключение не выброшено"
        );
    }

    @Test
    public void testGettingSubtasksForEpic1ShouldReturnNotEmptyList(){
        addSubtask();
        addEpic();
        assertEquals(1, manager.getSubtasksForEpic(2).size(), "В листе не один элемент");
    }

    @Test
    public void testGettingSubtasksForEpic2ShouldReturnEmptyList(){
        addEpic();
        assertEquals(0, manager.getSubtasksForEpic(1).size(), "В листе не один элемент");
    }

    @Test
    public void estGettingSubtasksForEpic3WhenIdDoesntExistThrowException() {
        addSubtask();
        addEpic();
        final NullPointerException exceptionTask = assertThrows(
                NullPointerException.class,
                () -> {
                    manager.getSubtasksForEpic(100);
                },
                "Исключение не выброшено"
        );
    }


}