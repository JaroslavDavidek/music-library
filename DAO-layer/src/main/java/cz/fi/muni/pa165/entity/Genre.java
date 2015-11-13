package cz.fi.muni.pa165.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JaroslavDavidek
 */
@Entity
public class Genre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;
    
    private int yearOfOrigin;
    
    public Genre(){
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String titleToSet) {
        this.title = titleToSet;
    }

    public int getYearOfOrigin() {
        return this.yearOfOrigin;
    }
    
    public void setYearOfOrigin(int yearOfOriginToSet) {
        this.yearOfOrigin = yearOfOriginToSet;
    }
    
    @Override
    public int hashCode() {
        final int primeOne = 89;
        final int primeTwo = 43;   
        
        int hashCode = Objects.hash(yearOfOrigin) * primeOne;
        
        // title is never null
        hashCode += this.title.hashCode() * primeTwo;
        return hashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this){
            return true;
        }
        if (object == null || !(object instanceof Genre)){
            return false;
        }       
        Genre other = (Genre) object;       
        if (this.getTitle() != null ? !Objects.equals(this.getTitle(), other.getTitle()) : other.getTitle() != null){
            return false;
        }
        return this.getYearOfOrigin() == other.getYearOfOrigin();        
    }

    @Override
    public String toString() {
        return this.title + " originated in " + this.yearOfOrigin;
    }
    
}
