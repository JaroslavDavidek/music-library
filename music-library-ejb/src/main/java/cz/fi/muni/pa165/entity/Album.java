package cz.fi.muni.pa165.entity;

import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Album {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false, unique=true)
    private String title;
    
    @NotNull
    @Column(nullable=false)
    private Date releaseDate;
    private String commentary;
    private Byte[] cover;

    public Album() {
    }

    public Long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Byte[] getCover() {
        return cover;
    }

    public void setCover(Byte[] cover) {
        this.cover = cover;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.title);
        hash = 89 * hash + Objects.hashCode(this.releaseDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album{" + "title=" + title + ", releaseDate=" + releaseDate + '}';
    }
    
}
