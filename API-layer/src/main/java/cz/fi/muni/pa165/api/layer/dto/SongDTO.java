package cz.fi.muni.pa165.api.layer.dto;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JaroslavDavidek
 */
public class SongDTO {
    
    private Long id;

    @NotNull
    @Size(min = 1, max = 128)
    private String title;
    
    @Valid
    private AlbumDTO album;
    
    @Valid
    private GenreDTO genre;
    
    @Valid
    private MusicianDTO musician;
    
    @Min(0)
    private int bitrate;
    
    @Min(0)
    private int albumPosition;
    
    @Size(max = 512)
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
       
    public AlbumDTO getAlbum() {
        return this.album;
    }
    
    public void setAlbum(AlbumDTO albumToSet) {
        this.album = albumToSet;
    }
    
    public GenreDTO getGenre() {
        return this.genre;
    }
    
    public void setGenre(GenreDTO genreToSet) {
        this.genre = genreToSet;
    }
    
    public MusicianDTO getMusician() {
        return this.musician;
    }
    
    public void setMusician(MusicianDTO musicianToSet) {
        this.musician = musicianToSet;
    } 
    
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
        this.commentary = commentaryToSet;
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
             
        if(this.album != null){
            hashCode += album.hashCode() * primeTwo;
        }
        
        if(this.genre != null){
            hashCode += genre.hashCode() * primeTwo;
        }
        if(this.musician != null){
            hashCode += musician.hashCode() * primeTwo;
        }

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
        if(this.getAlbum() != null ? !this.getAlbum().equals(other.getAlbum()) : other.getAlbum() != null){
            return false;
        }
        if(this.getGenre() != null ? !this.getGenre().equals(other.getGenre()) : other.getGenre() != null){
            return false;
        }
        return !(this.getMusician() != null ? !this.getMusician().equals(other.getMusician()) : other.getMusician() != null);
    }

    @Override
    public String toString() {
        return title + " from " + album.toString() + " by " + musician.toString() + " @" + bitrate + " Kbps";
    }
    
}