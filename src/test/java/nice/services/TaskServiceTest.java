package nice.services;

import nice.dao.TaskDao;
import nice.models.task.Status;
import nice.models.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskServiceTest {

    @Mock
    private TaskDao taskDao;

    @InjectMocks
    private TaskService taskService = new TaskService();

    private long testTaskId = 1l;
    private String testTaskName = "Test Task Name";
    private Status testTaskStatus = Status.COMPLETE;
    private Task testTask;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testTask = new Task();
        testTask.setName(testTaskName);
        testTask.setId(testTaskId);
        testTask.setStatus(testTaskStatus);
    }

    @Test
    public void createTaskTest() {
        Mockito.when(taskDao.save(Mockito.any(Task.class))).thenReturn(testTask);

        Task task = taskService.createTask(testTask);

        Mockito.verify(taskDao, Mockito.times(1)).save(Mockito.any(Task.class));
        assertNotNull(task);
        assertEquals(testTaskId, task.getId());
        assertEquals(testTaskName, task.getName());
    }

    @Test
    public void updateTaskTest() {
        String updatedTaskName = "updatedTaskName";
        Task updatedTask = new Task(updatedTaskName);
        updatedTask.setId(testTaskId);

        Mockito.when(taskDao.findOne(Mockito.anyLong())).thenReturn(testTask);
        Mockito.when(taskDao.save(updatedTask)).thenReturn(updatedTask);

        Task result = taskService.updateTask(updatedTask);

        Mockito.verify(taskDao, Mockito.times(1)).save(updatedTask);
        Mockito.verify(taskDao, Mockito.times(1)).findOne(testTaskId);

        assertNotNull(result);
        assertEquals(updatedTask.getId(), result.getId());
        assertEquals(updatedTaskName, result.getName());
    }

    @Test
    public void listTaskTest() {

        List<Task> taskList = new ArrayList<>();
        taskList.add(testTask);
        Task testTask2 = new Task("Task2");
        taskList.add(testTask2);

        Mockito.when(taskDao.findAll()).thenReturn(taskList);

        Iterable<Task> tasks = taskService.getAllTasks();
        tasks.forEach(task -> assertTrue(taskList.contains(task)));

        Mockito.verify(taskDao, Mockito.times(1)).findAll();
    }

    @Test
    public void listTasksByStatus() {

        Status status = Status.IN_PROGRESS;

        List<Task> taskList = new ArrayList<>();
        taskList.add(testTask);

        Mockito.when(taskDao.findByStatus(status)).thenReturn(taskList);

        Iterable<Task> tasks = taskService.findByStatus(testTaskStatus);
        tasks.forEach(task -> assertTrue(taskList.contains(task)));

        Mockito.verify(taskDao, Mockito.times(1)).findByStatus(testTaskStatus);
    }
}
