package cz.fi.muni.pa165.api.layer.dto;

import java.sql.Date;

/**
 *
 * @author Jergus Fasanek
 */
public class MusicianSearchDTO {
    
    private Long musicianId;
    
    private String realName;
    
    private String artistName;
    
    private Date dateOfBirth;
    
    private int fromYear;
    
    private int toYear;
    
    private boolean sortAscending;

    /**
     * @return the musicianId
     */
    public Long getMusicianId() {
        return musicianId;
    }

    /**
     * @param musicianId the musicianId to set
     */
    public void setMusicianId(Long musicianId) {
        this.musicianId = musicianId;
    }

    /**
     * @return the realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName the realName to set
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return the artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artistName the artistName to set
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the fromYear
     */
    public int getFromYear() {
        return fromYear;
    }

    /**
     * @param fromYear the fromYear to set
     */
    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    /**
     * @return the toYear
     */
    public int getToYear() {
        return toYear;
    }

    /**
     * @param toYear the toYear to set
     */
    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    /**
     * @return the sortAscending
     */
    public boolean isSortAscending() {
        return sortAscending;
    }

    /**
     * @param sortAscending the sortAscending to set
     */
    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }
}
