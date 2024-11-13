package telran.album.dao;

import telran.album.model.Photo;

import java.time.LocalDate;
import java.util.Arrays;

public class AlbumImpl implements Album {
    private Photo[] photos;
    private int size;

    public AlbumImpl(int capacity) {
        this.photos = new Photo[capacity];
    }

    @Override
    public boolean addPhoto(Photo photo) {
        if (photo == null || getPhotoFromAlbum(photo.getPhotoID(), photo.getAlbumID()) != null || size == photos.length) {
            return false;
        }
        photos[size++] = photo;
        return true;
    }

    @Override
    public boolean removePhoto(int photoID, int albumID) {
        for (int i = 0; i < size; i++) {
            if (photos[i].getPhotoID() == photoID && photos[i].getAlbumID() == albumID) {
                System.arraycopy(photos, i + 1, photos, i, size - i - 1);
                photos[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePhoto(int photoID, int albumID, String URL) {
        for (int i = 0; i < size; i++) {
            if (photos[i].getPhotoID() == photoID && photos[i].getAlbumID() == albumID) {
                photos[i].setURL(URL);
                return true;
            }
        }
        return false;
    }

    @Override
    public Photo getPhotoFromAlbum(int photoID, int albumID) {
        for (int i = 0; i < size; i++) {
            if (photos[i].getPhotoID() == photoID && photos[i].getAlbumID() == albumID) {
                return photos[i];
            }
        }
        return null;
    }

    @Override
    public Photo[] getAllPhotoFromAlbum(int albumID) {
        Photo[] res = new Photo[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumID() == albumID) {
                res[j++] = photos[i];
            }
        }
        return j != 0 ? Arrays.copyOf(res, j) : null;
    }

    @Override
    public Photo[] getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
        Photo[] res = new Photo[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (photos[i].getDate().isAfter(dateFrom.atStartOfDay()) && photos[i].getDate().isBefore(dateTo.atStartOfDay())) {
                res[j++] = photos[i];
            }
        }
        return j != 0 ? Arrays.copyOf(res,j) : null;
    }

    @Override
    public int size() {
        return size;
    }
}
