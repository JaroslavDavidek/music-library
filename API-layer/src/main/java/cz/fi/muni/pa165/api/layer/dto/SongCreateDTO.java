package cz.fi.muni.pa165.api.layer.dto;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JaroslavDavidek
 */
public class SongCreateDTO {
    
    @NotNull
    @Size(min = 1, max = 128)
    private String title;
    
    @NotNull
    private Long albumId;
    
    @NotNull
    private Long musicianId;
    
    @NotNull
    private Long genreId;
    
    @Min(0)
    private int bitrate;
    
    @Min(0)
    private int albumPosition;
    
    @Size(max = 512)
    private String commentary;
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String titleToSet) {
        this.title = titleToSet;
    }
       
    public Long getAlbumId() {
        return this.albumId;
    }
    
    public void setAlbumId(Long albumIdToSet) {
        this.albumId = albumIdToSet;
    }
    
    public Long getGenreId() {
        return this.genreId;
    }
    
    public void setGenreId(Long genreIdToSet) {
        this.genreId = genreIdToSet;
    }
    
    public Long getMusicianId() {
        return this.musicianId;
    }
    
    public void setMusicianId(Long musicianIdToSet) {
        this.musicianId = musicianIdToSet;
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
             
        if(this.albumId != null){
            hashCode += albumId.hashCode() * primeTwo;
        }
        
        if(this.genreId != null){
            hashCode += genreId.hashCode() * primeTwo;
        }
        if(this.musicianId != null){
            hashCode += musicianId.hashCode() * primeTwo;
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this){
            return true;
        }
        if (object == null || !(object instanceof SongCreateDTO)){
            return false;
        }       
        SongCreateDTO other = (SongCreateDTO) object;       
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
        if(this.getAlbumId() != null ? !this.getAlbumId().equals(other.getAlbumId()) : other.getAlbumId() != null){
            return false;
        }
        if(this.getGenreId() != null ? !this.getGenreId().equals(other.getGenreId()) : other.getGenreId() != null){
            return false;
        }
        return !(this.getMusicianId() != null ? !this.getMusicianId().equals(other.getMusicianId()) : other.getMusicianId() != null);
    }

    @Override
    public String toString() {
        return title + " from " + albumId + " by " + musicianId + " @" + bitrate + " Kbps";
    }
}
