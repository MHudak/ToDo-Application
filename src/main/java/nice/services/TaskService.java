package nice.services;

import nice.dao.TaskDao;
import nice.models.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Transactional
    public Task createTask(Task task) {
        return taskDao.save(task);
    }

    @Transactional
    public Iterable<Task> getAllTasks() {
        return taskDao.findAll();
    }

    @Transactional
    public Task updateTask(Task task) {
        Task t = taskDao.findOne(task.getId());
        t.setName(task.getName());
        t.setStatus(task.getStatus());
        t.setAssignedUser(task.getAssignedUser());
        t.setDescription(task.getDescription());
        return taskDao.save(task);
    }

}
