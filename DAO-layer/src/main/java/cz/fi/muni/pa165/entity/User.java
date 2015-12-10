package cz.fi.muni.pa165.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author JaroslavDavidek
 */
@Entity
@Table(name="Users")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String passwordHash;

    @Column(nullable=false,unique=true)
    @Pattern(regexp=".+@.+\\....?")   
    @NotNull
    private String email;
    
    @NotNull
    private String login;

    private boolean isAdmin;

    public User() {
    }
    
    public User(Long id) {
	this.id=id;
    }
    
    public Long getId() {
            return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    @Override
    public int hashCode() {
        final int primeOne = 89;
        final int primeTwo = 43;  
        
        int hashCode = Objects.hash(isAdmin);
       
        if(this.email != null){
            hashCode += Objects.hash(email) * primeOne;
        }
        
        if(this.login != null){
            hashCode += Objects.hash(login) * primeTwo;
        }
        
        return hashCode;
    }

	@Override
	public boolean equals(Object object) {
            if (object == this){
            return true;
            }
            if (object == null || !(object instanceof User)){
                return false;
            }       
            User other = (User) object;       
            if (this.getLogin() != null ? !Objects.equals(this.getLogin(), other.getLogin()) : other.getLogin() != null){
                return false;
            }
            return !(this.getEmail() != null ? !this.getEmail().equals(other.getEmail()) : other.getEmail() != null);
	}

    @Override
    public String toString() {
        return "User [" + id +"] admin: " + isAdmin + " login: " + this.login + " passwordHash: " + passwordHash;
    }
}
