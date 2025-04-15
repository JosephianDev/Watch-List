package marrone.giuseppe.watchlist.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import marrone.giuseppe.watchlist.model.Film;

@Dao
public interface DaoAccess {

    @Insert
    void insertFilm(Film film);
    @Update
    void updateFilm(Film film);
    @Delete
    void deleteFilm(Film film);
    @Query("DELETE FROM Film")
    void deleteAllFilms();
    @Query("SELECT * FROM Film ORDER BY id ASC")
    LiveData<List<Film>> readAllFilms();
    @Query("SELECT * FROM Film WHERE id =:idFilm")
    LiveData<Film> readFilm(int idFilm);
    @Query("SELECT count(id) FROM Film")
    LiveData<Integer> readFilmsCount();
}
