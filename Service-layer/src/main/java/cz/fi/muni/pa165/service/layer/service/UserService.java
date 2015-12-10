package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.entity.User;
import java.util.List;

/**
 *
 * @author JaroslavDavidek
 */
public interface UserService {

    void registerUser(User user, String unencryptedPassword);  
    boolean authenticate(User user, String password);
    boolean isAdmin(User user);
    List<User> getAllUsers();
    User findUserById(Long userId);
    User findUserByEmail(String email);
}
