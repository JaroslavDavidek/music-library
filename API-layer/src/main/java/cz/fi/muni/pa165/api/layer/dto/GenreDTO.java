package cz.fi.muni.pa165.api.layer.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GenreDTO {
    
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 128)
    private String title;
    
    private int yearOfOrigin;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getYearOfOrigin(){
        return this.yearOfOrigin;
    }
    
    public void setYearOfOrigin(int year){
        this.yearOfOrigin = year;
    }
    
    @Override
    public int hashCode(){
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
        if (object == null || !(object instanceof GenreDTO)){
            return false;
        }       
        GenreDTO other = (GenreDTO) object;       
        if (this.getTitle() != null ? !Objects.equals(this.getTitle(), other.getTitle()) : other.getTitle() != null){
            return false;
        }
        return this.getYearOfOrigin() == other.getYearOfOrigin();        
    }
}
