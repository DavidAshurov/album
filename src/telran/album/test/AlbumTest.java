package telran.album.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.album.dao.Album;
import telran.album.dao.AlbumImpl;
import telran.album.model.Photo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {
    private final int capacity = 5;
    private final Comparator<Photo> comparator = (p1,p2) -> {
        int res = Integer.compare(p1.getAlbumID(),p2.getAlbumID());
        return res != 0 ? res : Integer.compare(p1.getPhotoID(),p2.getPhotoID());
    };
    private Album album;
    private Photo[] photos;
    @BeforeEach
    void setUp() {
        album = new AlbumImpl(capacity);
        photos= new Photo[capacity];
        photos[0] = new Photo(1,10,"Summer2022","a.com", LocalDateTime.of(2022, Month.JULY,1,18,45));
        photos[1] = new Photo(1,11,"Winter2023","ab.com", LocalDateTime.of(2023, Month.FEBRUARY,27,9,45));
        photos[2] = new Photo(1,12,"Autumn2024","abc.com", LocalDateTime.of(2024, Month.OCTOBER,16,18,45));
        photos[3] = new Photo(2,10,"Spring2024","abcd.com", LocalDateTime.of(2024, Month.APRIL,13,12,45));
        photos[4] = new Photo(2,11,"Family","abcdf.com", LocalDateTime.now());
        for (int i = 0; i < photos.length - 1; i++) {
            album.addPhoto(photos[i]);
        }
    }

    @Test
    void testAddPhoto() {
        assertFalse(album.addPhoto(null));
        assertFalse(album.addPhoto(photos[2]));
        assertTrue(album.addPhoto(photos[4]));
        assertEquals(capacity,album.size());
        assertFalse(album.addPhoto( new Photo(3,11,"Family","abcdf.com", LocalDateTime.now())));
    }
    @Test
    void testRemovePhoto() {
        assertFalse(album.removePhoto(19,1));
        assertTrue(album.removePhoto(10,2));
        assertEquals(capacity - 2,album.size());
        assertFalse(album.removePhoto(10,2));
    }

    @Test
    void testUpdatePhoto() {
        assertFalse(album.updatePhoto(19,1,"abc.com"));
        assertTrue(album.updatePhoto(10,1,"abc.com"));
        assertEquals("abc.com",album.getPhotoFromAlbum(10,1).getURL());
    }

    @Test
    void testGetPhotoFromAlbum() {
        assertNull(album.getPhotoFromAlbum(19,1));
        assertEquals(photos[1],album.getPhotoFromAlbum(11,1));
    }

    @Test
    void testGetAllPhotoFromAlbum() {
        Photo[] expected = Arrays.copyOfRange(photos,0,3);
        Photo[] actual = album.getAllPhotoFromAlbum(1);
        Arrays.sort(actual,comparator);
        assertArrayEquals(expected,actual);
        assertNull(album.getAllPhotoFromAlbum(3));
    }

    @Test
    void testGetPhotoBetweenDate() {
        Photo[] expected = Arrays.copyOfRange(photos,1,4);
        Photo[] actual = album.getPhotoBetweenDate(LocalDate.of(2023,Month.JANUARY,1),LocalDate.of(2024,Month.OCTOBER,18));
        Arrays.sort(actual,comparator);
        Arrays.sort(expected,comparator);
        assertArrayEquals(expected,actual);
        assertNull(album.getPhotoBetweenDate(LocalDate.of(2000,Month.JANUARY,1),LocalDate.of(2010,Month.OCTOBER,18)));
    }

    @Test
    void testSize() {
        assertEquals(capacity - 1, album.size());
    }
}