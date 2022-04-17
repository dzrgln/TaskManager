package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilites.InMemoryTaskManager;

class InMemoryTaskManagerTest extends TaskManagerTest <InMemoryTaskManager>{

    @BeforeEach
    private void createManager() {
       manager = new InMemoryTaskManager();
    }

    @AfterEach
    private void clearManager(){
        manager.deleteAllTasks();
    }

    @Test
    @Override
    public void testGetList1_shouldEmptyListWhenNoAnyTasks() {
        super.testGetList1_shouldEmptyListWhenNoAnyTasks();
    }

    @Test
    @Override
    public void testGetList2_shouldEmptyListWhenThereIsTask() {
        super.testGetList2_shouldEmptyListWhenThereIsTask();
    }
    @Test
    @Override
    public void testGetList3_shouldEmptyListWhenNoAnyEpics() {
        super.testGetList3_shouldEmptyListWhenNoAnyEpics();
    }
    @Test
    @Override
    public void testGetList4_shouldEmptyListWhenThereIsEpic() {
        super.testGetList4_shouldEmptyListWhenThereIsEpic();
    }
    @Test
    @Override
    public void testGetList5_shouldEmptyListWhenNoAnySubTasks() {
        super.testGetList5_shouldEmptyListWhenNoAnySubTasks();
    }
    @Test
    @Override
    public void testGetList6_shouldEmptyListWhenThereIsSubtask() {
        super.testGetList6_shouldEmptyListWhenThereIsSubtask();
    }
    @Test
    @Override
    public void testGet1_shouldThrowExceptionWhenThereIsNoAnyTask() {
        super.testGet1_shouldThrowExceptionWhenThereIsNoAnyTask();
    }
    @Test
    @Override
    public void testGet2_shouldThrowExceptionWhenThereIsNoAnyEpic() {
        super.testGet2_shouldThrowExceptionWhenThereIsNoAnyEpic();
    }
    @Test
    @Override
    public void testGet3_shouldThrowExceptionWhenThereIsNoAnySubtask() {
        super.testGet3_shouldThrowExceptionWhenThereIsNoAnySubtask();
    }
    @Test
    @Override
    public void testGet4_shouldReturnTaskWhenAddTask() {
        super.testGet4_shouldReturnTaskWhenAddTask();
    }
    @Test
    @Override
    public void testGet5_shouldReturnEpicWhenAddEpic() {
        super.testGet5_shouldReturnEpicWhenAddEpic();
    }
    @Test
    @Override
    public void testGet6_shouldReturnSubtaskWhenAddSubtask() {
        super.testGet6_shouldReturnSubtaskWhenAddSubtask();
    }
    @Test
    @Override
    public void testGet7_shouldThrowExceptionWhenRequiredTaskWrongID() {
        super.testGet7_shouldThrowExceptionWhenRequiredTaskWrongID();
    }
    @Test
    @Override
    public void testGet8_shouldThrowExceptionWhenRequiredEpicWrongID() {
        super.testGet8_shouldThrowExceptionWhenRequiredEpicWrongID();
    }
    @Test
    @Override
    public void testGet9_shouldThrowExceptionWhenRequiredSubTaskWrongID() {
        super.testGet9_shouldThrowExceptionWhenRequiredSubTaskWrongID();
    }
    @Test
    @Override
    public void testAdd1_shouldAddTask() {
        super.testAdd1_shouldAddTask();
    }
    @Test
    @Override
    public void testAdd2_shouldAddEpicWithoutSubtaskAndCheckSubtasksList() {
        super.testAdd2_shouldAddEpicWithoutSubtaskAndCheckSubtasksList();
    }
    @Test
    @Override
    public void testAdd3_shouldAddSubtask() {
        super.testAdd3_shouldAddSubtask();
    }
    @Test
    @Override
    public void testAdd4_shouldAddEpicWithSubtaskAndCheckSubtasksList() {
        super.testAdd4_shouldAddEpicWithSubtaskAndCheckSubtasksList();
    }
    @Test
    @Override
    public void testUpdate1_shouldUpdateTask() {
        super.testUpdate1_shouldUpdateTask();
    }
    @Test
    @Override
    public void testUpdate2_shouldUpdateSubtask() {
        super.testUpdate2_shouldUpdateSubtask();
    }
    @Test
    @Override
    public void testUpdate3_shouldUpdateEpic() {
        super.testUpdate3_shouldUpdateEpic();
    }
    @Test
    @Override
    public void testUpdate4_whenIdDoesntExistThrowException() {
        super.testUpdate4_whenIdDoesntExistThrowException();
    }
    @Test
    @Override
    public void testDelete1_shouldDeleteTask() {
        super.testDelete1_shouldDeleteTask();
    }
    @Test
    @Override
    public void testDelete2_shouldDeleteSubTask() {
        super.testDelete2_shouldDeleteSubTask();
    }
    @Test
    @Override
    public void testDelete3_shouldDeleteEpic() {
        super.testDelete3_shouldDeleteEpic();
    }
    @Test
    @Override
    public void testDelete4_whenIdDoesntExistThrowException() {
        super.testDelete4_whenIdDoesntExistThrowException();
    }
    @Test
    @Override
    public void testGettingSubtasksForEpic1_shouldReturnNotEmptyList() {
        super.testGettingSubtasksForEpic1_shouldReturnNotEmptyList();
    }
    @Test
    @Override
    public void testGettingSubtasksForEpic2_shouldReturnEmptyList() {
        super.testGettingSubtasksForEpic2_shouldReturnEmptyList();
    }

}