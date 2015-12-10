package cz.fi.muni.pa165.api.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.UserAuthenticateDTO;
import cz.fi.muni.pa165.api.layer.dto.UserDTO;
import java.util.Collection;

/**
 *
 * @author JaroslavDavidek
 */
public interface UserFacade {  
    void registerUser(UserDTO user, String unencryptedPassword);  
    boolean authenticate(UserAuthenticateDTO user);
    boolean isAdmin(UserDTO user);
    UserDTO findUserById(Long userId);
    UserDTO findUserByEmail(String email);
    Collection<UserDTO> getAllUsers();
}
