package nice.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by dan on 7/6/16.
 */

@Transactional
public interface TaskDao extends CrudRepository<Task, Long>{

    Task findByTaskName(String taskName);
}
