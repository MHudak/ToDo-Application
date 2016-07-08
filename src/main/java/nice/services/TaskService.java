package nice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nice.models.Task;
import nice.models.TaskDao;

import java.util.Set;


@Service
public class TaskService {

	@Autowired
    private TaskDao taskDao;

	@Transactional
    public Iterable<Task> findAll() {
    	return taskDao.findAll();
    }

    @Transactional
	public Task findByIdOrTaskName(String idOrTaskName) {
		Task task;

        try {
            Long id = new Long(idOrTaskName);
            task = taskDao.findOne(id);
        } catch (Exception e) {
            task = null;
        }

        if (task == null) {
            task = taskDao.findByTaskName(idOrTaskName);
        }

        return task;
	}

	@Transactional
	public Task createTask(String taskName) {
		Task task = new Task(taskName);
        return taskDao.save(task);
	}

	@Transactional
	public Task updateTask(Long id, String taskName) {
		try {
            Task task = taskDao.findOne(id);
            task.setTaskName(taskName);
            return taskDao.save(task);
        } catch (Exception e) {
            return null;
        }
	}

    @Transactional
    public Set<Task> findAllByStatus(Task.Status status) {
        return taskDao.findByTaskStatus(status);
    }

    @Transactional
    public Set<Task> findAllByStatusNotEqualTo(Task.Status status) {
        return taskDao.findByTaskStatusNot(status);
    }

}