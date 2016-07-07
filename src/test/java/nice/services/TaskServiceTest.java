package nice.services;

import nice.models.Task;
import nice.models.TaskDao;
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

	private long testTaskId = 1L;
	private String testTaskName = "testTaskName1";
	private Task testTask;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		testTask = new Task(testTaskName);
		testTask.setId(testTaskId);
	}

	@Test
	public void exampleTest() {
		assertEquals(1, 1);
	}

	@Test
	public void createTaskTest() {
		Mockito.when(taskDao.save(Mockito.any(Task.class))).thenReturn(testTask);

		Task task = taskService.createTask(testTaskName);

		Mockito.verify(taskDao, Mockito.times(1)).save(Mockito.any(Task.class));
		assertNotNull(task);
		assertNotNull(task.getId());
		assertEquals(testTaskName, task.getTaskName());
	}

	@Test
	public void updateTaskTest() {
		String updatedTaskName = "updatedTaskName";
		Task updatedTask = new Task(updatedTaskName);
		updatedTask.setId(testTaskId);

		Mockito.when(taskDao.findOne(Mockito.anyLong())).thenReturn(testTask);
		Mockito.when(taskDao.save(updatedTask)).thenReturn(updatedTask);

		Task result = taskService.updateTask(testTaskId, updatedTaskName);

		Mockito.verify(taskDao, Mockito.times(1)).findOne(testTaskId);
		Mockito.verify(taskDao, Mockito.times(1)).save(updatedTask);

		assertNotNull(result);
		assertEquals(testTaskId, result.getId());
		assertEquals(updatedTaskName, result.getTaskName());
	}
}