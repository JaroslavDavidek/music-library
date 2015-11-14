package cz.fi.muni.pa165.api.layer.dto;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JaroslavDavidek
 */
public class SongCreateDTO {
    
    // region Song properties
    
    @NotNull
    @Size(min = 1, max = 128)
    private String title;
    
    @Min(0)
    private int bitrate;
    
    @Min(0)
    private int albumPosition;
    
    private String commentary;
    
    // endregion Song properties
    
    
    // region Album atributte properties
    
    @NotNull
    @Size(min = 1, max = 128)
    private String albumTitle;
    
    @NotNull
    private Date albumReleaseDate;
    
    private String albumCommentary;
    
    private Byte[] albumCover;
    
    // endregion Album atributte properties
      
    
    // region Genre atributte properties
    
    @NotNull
    @Size(min = 1, max = 128)
    private String genreTitle;
    
    @Min(0)
    private int genreYearOfOrigin;
    
    // endregion Genre atributte properties
    
    // region Musician atributte properties
    
    @NotNull
    @Size(min = 2, max = 128)
    private String musicianRealName;
    
    private String musicianArtistName;
    
    private Date musicianDateOfBirth;
    
    // endregion Musician atributte properties
    
    
    
    // region Song getters/setters
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String titleToSet) {
        this.title = titleToSet;
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
        this.title = commentaryToSet;
    }
    
    // endregion Song getters/setters
    
    
    // region Album getters/setters
    
    public String getAlbumTitle() {
        return this.title;
    }

    public void setAlbumTitle(String title) {
        this.title = title;
    }

    public Date getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public void setAlbumReleaseDate(Date releaseDate) {
        this.albumReleaseDate = releaseDate;
    }

    public String getAlbumCommentary() {
        return this.albumCommentary;
    }

    public void setAlbumCommentary(String commentary) {
        this.albumCommentary = commentary;
    }

    public Byte[] getAlbumCover() {
        return this.albumCover;
    }

    public void setAlbumCover(Byte[] cover) {
        this.albumCover = cover;
    }
    
    // endregion Album getters/setters
    
    
    // region Genre getters/setters
    
    public String getGenreTitle() {
        return this.genreTitle;
    }
    
    public void setGenreTitle(String titleToSet) {
        this.genreTitle = titleToSet;
    }

    public int getGenreYearOfOrigin() {
        return this.genreYearOfOrigin;
    }
    
    public void setGenreYearOfOrigin(int yearOfOriginToSet) {
        this.genreYearOfOrigin = yearOfOriginToSet;
    }
    
    // endregion Genre getters/setters
    
    
    // region Musician getters/setters
    
    /**
     * @return the realName
     */
    public String getRealName() {
        return this.musicianRealName;
    }

    /**
     * @param realName the realName to set
     */
    public void setRealName(String realName) {
        this.musicianRealName = realName;
    }

    /**
     * @return the artistName
     */
    public String getArtistName() {
        return this.musicianArtistName;
    }

    /**
     * @param artistName the artistName to set
     */
    public void setArtistName(String artistName) {
        this.musicianArtistName = artistName;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return this.musicianDateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.musicianDateOfBirth = dateOfBirth;
    }
    
    // endregion Musician getters/setters
    
    // region common overriden methods
    
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
        return this.title + " from " + this.albumTitle + " by " + this.musicianArtistName + " @" + this.bitrate + " Kbps";
    }
    
    // endregion common overriden methods
}
