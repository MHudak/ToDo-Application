package nice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import nice.models.TaskDao;
import nice.models.Task;
import nice.models.UserDao;
import nice.models.User;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Bean
	CommandLineRunner init(UserDao userDao, TaskDao taskDao) {
		return (evt) -> {
			User user1 = userDao.save(new User("niceUser1"));
			User user2 = userDao.save(new User("niceUser2"));
			userDao.save(new User("niceUser3"));
			taskDao.save(new Task("niceTask1"));
			taskDao.save(new Task("niceTask2", user2));
			taskDao.save(new Task("niceTask3", user1, Task.Status.IN_PROGRESS));
		};
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    } 
}