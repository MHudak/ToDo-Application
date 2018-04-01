package nice;

import nice.dao.TaskDao;
import nice.dao.UserDao;
import nice.models.task.Status;
import nice.models.task.Task;
import nice.models.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Bean
	CommandLineRunner init(UserDao userDao, TaskDao taskDao) {
		return (evt) -> {
			userDao.save(new User("niceUser1"));
			userDao.save(new User("niceUser2"));
			userDao.save(new User("niceUser3"));
			Task t = new Task("Task1");
			t.setStatus(Status.COMPLETE);
			taskDao.save(t);
			Task t2 = new Task("Task2");
			t2.setStatus(Status.IN_PROGRESS);
			taskDao.save(t2);
		};
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    } 
}