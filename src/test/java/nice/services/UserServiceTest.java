package nice.services;

import nice.models.Task;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nice.models.User;
import nice.models.UserDao;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceTest {

	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserService userService = new UserService();

	private long testUserId = 1L;
	private String testUserName = "testUserName1";
	private User testUser;
	private String testTaskName = "testTaskName1";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		testUser = new User(testUserName);
		testUser.setId(testUserId);
		Set<Task> tasks = new HashSet<>();
		tasks.add(new Task(testTaskName));
		testUser.setAssignedTasks(tasks);
	}

	@Test
	public void exampleTest() {
		assertEquals(1, 1);
	}

	@Test
	public void createUserTest() {
		Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(testUser);

		User user = userService.createUser(testUserName);

		Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any(User.class));
		assertNotNull(user);
		assertNotNull(user.getId());
		assertEquals(testUserName, user.getUserName());
	}

	@Test
	public void updateUserTest() {
		String updatedUserName = "updatedUserName";
		User updatedUser = new User(updatedUserName);
		updatedUser.setId(testUserId);

		Mockito.when(userDao.findOne(Mockito.anyLong())).thenReturn(testUser);
		Mockito.when(userDao.save(updatedUser)).thenReturn(updatedUser);

		User result = userService.updateUser(testUserId, updatedUserName);

		Mockito.verify(userDao, Mockito.times(1)).findOne(testUserId);
		Mockito.verify(userDao, Mockito.times(1)).save(updatedUser);

		assertNotNull(result);
		assertEquals(testUserId, result.getId());
		assertEquals(updatedUserName, result.getUserName());
	}

	@Test
	public void deleteUserTest() {
		String userToDelete = testUserName;
		Mockito.when(userDao.findOne(Mockito.anyLong())).thenReturn(testUser);

		userService.deleteUserByIdOrUserName(userToDelete);

		Mockito.verify(userDao, Mockito.times(1)).delete(testUserId);
	}

	@Test
	public void listUserTasksTest() {
		Mockito.when(userDao.findOne(Mockito.anyLong())).thenReturn(testUser);

		Set<Task> tasks = userService.getTasksForUserId(testUserId);

		assertNotNull(tasks);
		assertEquals(1, tasks.size());
	}
}