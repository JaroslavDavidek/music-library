package cz.fi.muni.pa165.api.layer.dto;

import java.util.Objects;

/**
 *
 * @author JaroslavDavidek
 */
public class SongDTO {
    
    private Long id;

    private String title;
    
    //private AlbumDTO album;
    
    //private GenreDTO genre;
    
    //private MusicianDTO musician;
    
    private int bitrate;
    
    private int albumPosition;
    
    private String commentary;
    
    public Long getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String titleToSet) {
        this.title = titleToSet;
    }
    
    /*
    public Album getAlbum() {
        return this.album;
    }
    
    public void setAlbum(Album albumToSet) {
        this.album = albumToSet;
    }
    
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
        final int primeOne = 89;
        final int primeTwo = 43;   
        
        int hashCode = Objects.hash(bitrate, albumPosition);
        hashCode *=  primeOne;
        
        if(this.title != null){
            hashCode += Objects.hash(title) * primeTwo;
        }
        
        if(this.commentary != null){
            hashCode += Objects.hash(commentary) * primeTwo;
        }
        
        /*
        if(this.album != null){
            hashCode += album.hashCode() * primeTwo;
        }
        
        if(this.genre != null){
            hashCode += genre.hashCode() * primeTwo;
        }
        if(this.musician != null){
            hashCode += musician.hashCode() * primeTwo;
        }
        */

        return hashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this){
            return true;
        }
        if (object == null || !(object instanceof SongDTO)){
            return false;
        }       
        SongDTO other = (SongDTO) object;       
        if (this.getTitle() != null ? !Objects.equals(this.getTitle(), other.getTitle()) : other.getTitle() != null){
            return false;
        }
        if(this.getBitrate() != other.getBitrate()){
            return false;
        }
        if(this.getAlbumPosition() != other.getAlbumPosition()){
            return false;
        }
        if(this.getCommentary() != null ? !Objects.equals(this.getCommentary(), other.getCommentary()) : other.getCommentary() != null){
            return false;
        }
        
        // to be commented out
        return true;
        
        /*
        if(this.getAlbum() != null ? !this.getAlbum().equals(other.getAlbum()) : other.getAlbum() != null){
            return false;
        }
        if(this.getGenre() != null ? !this.getGenre().equals(other.getGenre()) : other.getGenre() != null){
            return false;
        }
        return !(this.getMusician() != null ? !this.getMusician().equals(other.getMusician()) : other.getMusician() != null);
        */
    }

    @Override
    public String toString() {
        return title /* + " from " + album.getTitle() + " by " + musician.toString() */ + " @" + bitrate + " Kbps";
    }
    
}
