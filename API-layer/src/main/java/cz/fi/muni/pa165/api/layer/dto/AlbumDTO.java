package cz.fi.muni.pa165.api.layer.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AlbumDTO {
    
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 128)
    private String title;
    
    @Valid
    private Date releaseDate;
    
    private String commentary;
    private Byte[] cover;

    @Valid
    private MusicianDTO musician;
    
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

    public MusicianDTO getMusician() {
        return musician;
    }

    public void setMusician(MusicianDTO musician) {
        this.musician = musician;
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
        final AlbumDTO other = (AlbumDTO) obj;
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
}
