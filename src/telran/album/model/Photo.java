package telran.album.model;

import java.time.LocalDateTime;

public class Photo {
    private int albumID;
    private int photoID;
    private String title;
    private String URL;
    private LocalDateTime date;

    public Photo(int albumID, int photoID, String title, String URL, LocalDateTime date) {
        this.albumID = albumID;
        this.photoID = photoID;
        this.title = title;
        this.URL = URL;
        this.date = date;
    }

    public int getAlbumID() {
        return albumID;
    }

    public int getPhotoID() {
        return photoID;
    }

    public String getTitle() {
        return title;
    }

    public String getURL() {
        return URL;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "albumID=" + albumID +
                ", photoID=" + photoID +
                ", title='" + title + '\'' +
                ", URL='" + URL + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo photo)) return false;

        return albumID == photo.albumID && photoID == photo.photoID;
    }

    @Override
    public int hashCode() {
        int result = albumID;
        result = 31 * result + photoID;
        return result;
    }
}
