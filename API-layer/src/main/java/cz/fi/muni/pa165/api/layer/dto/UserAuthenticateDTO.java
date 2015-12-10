package cz.fi.muni.pa165.api.layer.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JaroslavDavidek
 */
public class UserAuthenticateDTO
{
    private Long userId;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String login;
    
    @NotNull
    @Size(min = 5, max = 50)
    private String password;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    
    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
