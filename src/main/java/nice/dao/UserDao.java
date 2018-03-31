package nice.dao;

import javax.transaction.Transactional;

import nice.models.user.User;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

  User findByUserName(String userName);

}