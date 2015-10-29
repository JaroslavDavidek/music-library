package cz.fi.muni.pa165.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genre {
    @Id
    private Long id;
    
    private String title;
    
    public Genre(){}

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
}
