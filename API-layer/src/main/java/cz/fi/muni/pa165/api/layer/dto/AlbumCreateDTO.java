/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.layer.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 *
 * @author Peter Franek
 */
public class AlbumCreateDTO {
    
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 128)
    private String title;
    
    @Past
    private Date releaseDate;
    
    private String commentary;
    private Byte[] cover;

    @NotNull
    private long musicianId;
    
    private List<SongDTO> songs = new ArrayList<>();

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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

    public long getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(long musicianId) {
        this.musicianId = musicianId;
    }

    public List<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDTO> songs) {
        this.songs = songs;
    }
    
    public void addSong(SongDTO song) {
        this.songs.add(song);
    }
    
    public void removeSong(SongDTO song) {
        this.songs.remove(song);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(getTitle());
        hash = 17 * hash + Objects.hashCode(getReleaseDate());
        hash = 17 * hash + Objects.hashCode(getMusicianId());
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
        final AlbumCreateDTO other = (AlbumCreateDTO) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (this.musicianId != other.musicianId) {
            return false;
        }
        return true;
    }

    
}
