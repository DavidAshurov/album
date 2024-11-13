package telran.album.dao;

import telran.album.model.Photo;

import java.time.LocalDate;

public interface Album {
    boolean addPhoto(Photo photo);
    boolean removePhoto(int photoID, int albumID);
    boolean updatePhoto(int photoID, int albumID, String URL);
    Photo getPhotoFromAlbum(int photoID, int albumID);
    Photo[] getAllPhotoFromAlbum(int albumID);
    Photo[] getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo);
    int size();

}
