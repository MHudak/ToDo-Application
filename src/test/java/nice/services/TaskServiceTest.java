package nice.services;

import nice.dao.TaskDao;
import nice.models.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
}
