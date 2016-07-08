package nice.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by dan on 7/6/16.
 */

@Transactional
public interface TaskDao extends CrudRepository<Task, Long>{

    Task findByTaskName(String taskName);
    Set<Task> findByTaskStatus(Task.Status taskStatus);
    Set<Task> findByTaskStatusNot(Task.Status taskStatus);
}
