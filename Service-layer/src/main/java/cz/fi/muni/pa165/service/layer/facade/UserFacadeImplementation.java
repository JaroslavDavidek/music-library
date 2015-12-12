package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.UserAuthenticateDTO;
import cz.fi.muni.pa165.api.layer.dto.UserDTO;
import cz.fi.muni.pa165.api.layer.facade.UserFacade;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.layer.service.MappingServiceImplementation;
import cz.fi.muni.pa165.service.layer.service.UserService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JaroslavDavidek
 */
@Service
@Transactional
public class UserFacadeImplementation implements UserFacade {
        
    @Autowired
    private UserService userService;

    @Autowired
    private MappingServiceImplementation mappingService;


    @Override
    public void registerUser(UserDTO userDTO, String unencryptedPassword) {
        User userEntity = mappingService.mapTo(userDTO, User.class);
        userService.registerUser(userEntity, unencryptedPassword);
        userDTO.setId(userEntity.getId());
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO user) {
        return userService.authenticate(userService.findUserById(user.getUserId()), user.getPassword());
    }

    @Override
    public boolean isAdmin(UserDTO user) {
        return userService.isAdmin(mappingService.mapToEnforceID(user, User.class));
    }

    @Override
    public UserDTO findUserById(Long userId) {
        User user = userService.findUserById(userId);
        return (user == null) ? null : mappingService.mapToEnforceID(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userService.findUserByEmail(email);
        return (user == null) ? null : mappingService.mapToEnforceID(user, UserDTO.class);
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        return mappingService.mapToCollectionEnforceID(userService.getAllUsers(), UserDTO.class);
    }
    
    
}
