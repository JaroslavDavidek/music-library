package cz.fi.muni.pa165.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JaroslavDavidek
 */
@Entity
public class Song {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    
    @ManyToOne
    @NotNull
    private Album album;
    /*
    @ManyToOne
    @NotNull
    private Genre genre;
    
    @ManyToOne
    @NotNull
    private Musician musician;
    */
    private int bitrate;
    
    private int albumPosition;
    
    private String commentary;

    public Song() {
    }
    
    public Song(Long id) {
	this.id=id;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String titleToSet) {
        this.title = titleToSet;
    }
    
    public Album getAlbum() {
        return this.album;
    }
    
    public void setAlbum(Album albumToSet) {
        this.album = albumToSet;
    }
    /*
    public Genre getGenre() {
        return this.genre;
    }
    
    public void setGenre(Genre genreToSet) {
        this.genre = genreToSet;
    }
    
    public Musician getMusician() {
        return this.musician;
    }
    
    public void setMusician(Musician musicianToSet) {
        this.musician = musicianToSet;
    }
    */
    public int getBitrate() {
        return this.bitrate;
    }
    
    public void setBitrate(int bitrateToSet) {
        this.bitrate = bitrateToSet;
    }
    
    public int getAlbumPosition() {
        return this.albumPosition;
    }
    
    public void setAlbumPosition(int albumPositionToSet) {
        this.albumPosition = albumPositionToSet;
    }
    
    public String getCommentary() {
        return this.commentary;
    }
    
    public void setCommentary(String commentaryToSet) {
        this.title = commentaryToSet;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        final int primeOne = 89;
        final int primeTwo = 43;   
        
        hashCode = Objects.hash(title, bitrate, albumPosition, commentary);
        hashCode *=  primeOne;
        hashCode += album.hashCode() * primeTwo;
        //hashCode += genre.hashCode() * primeTwo;
        //hashCode += musician.hashCode() * primeTwo;
        
        return hashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this){
            return true;
        }
        if (!(object instanceof Song)){
            return false;
        } 
        
        Song other = (Song) object;
        return Objects.equals(this.getTitle(), other.getTitle())
            && Objects.equals(this.getBitrate(), other.getBitrate())
            && Objects.equals(this.getAlbumPosition(), other.getAlbumPosition())
            && Objects.equals(this.getCommentary(), other.getCommentary())
            && this.getAlbum().equals(other.getAlbum());
            //&& this.getGenre().equals(other.getGenre())
            //&& this.getMusician().equals(other.getMusician());
    }

    @Override
    public String toString() {
        return title + " " + album.getTitle() + " " + /*musician.toString() +*/ " @" + bitrate + " Kbps";
    }
    
}
