package nice.services;

import nice.dao.TaskDao;
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
    private Task testTask;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testTask = new Task();
        testTask.setName(testTaskName);
        testTask.setId(testTaskId);
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
    public void listTaskTest() {

        List<Task> taskList = new ArrayList<>();
        taskList.add(testTask);
        Task testTask2 = new Task("Task2");
        taskList.add(testTask2);

        Mockito.when(taskDao.findAll()).thenReturn(taskList);

        Iterable<Task> tasks = taskService.getAllTasks();
        tasks.forEach(user -> assertTrue(taskList.contains(user)));

        Mockito.verify(taskDao, Mockito.times(1)).findAll();
    }
}
