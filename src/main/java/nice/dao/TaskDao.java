package nice.dao;

import nice.models.task.Status;
import nice.models.task.Task;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TaskDao extends CrudRepository<Task, Long> {

    public Iterable<Task> findByStatus(Status status);

}
