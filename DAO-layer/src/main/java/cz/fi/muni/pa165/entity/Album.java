package cz.fi.muni.pa165.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    @OneToOne
    @NotNull
    private Musician musician;
    
    @OneToMany(mappedBy = "album", cascade = CascadeType.REMOVE)
    private List<Song> songs = new ArrayList<>();
    
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

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public Collection<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    
    public void addSong(Song song) {
        this.songs.add(song);
    }
    
    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(getTitle());
        hash = 17 * hash + Objects.hashCode(getReleaseDate());
        hash = 17 * hash + Objects.hashCode(getMusician());
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
        if (!Objects.equals(this.getTitle(), other.getTitle())) {
            return false;
        }
        if (!Objects.equals(this.getReleaseDate(), other.getReleaseDate())) {
            return false;
        }
        if (!Objects.equals(this.getMusician(), other.getMusician())) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Album{" + "title=" + title + ", releaseDate=" + releaseDate + '}';
    }
    
}
