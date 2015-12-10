package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.User;
import java.util.List;

/**
 *
 * @author JaroslavDavidek
 */
public interface UserDao {
    public boolean create(User user);
    public User findById(Long id);
    public User findUserByEmail(String email);
    public  List<User> findAll();
}
