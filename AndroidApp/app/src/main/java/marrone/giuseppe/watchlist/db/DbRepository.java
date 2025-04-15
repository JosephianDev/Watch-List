package marrone.giuseppe.watchlist.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

import marrone.giuseppe.watchlist.model.Film;

public class DbRepository {

    private final String DB_NAME="mydb";

    private MyDatabase myDatabase;

    public DbRepository(Context context) {
        this.myDatabase = Room.databaseBuilder(context, MyDatabase.class, DB_NAME).build();
    }

    public void insertFilm(Film film) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                myDatabase.getDaoAccess().insertFilm(film);
                return null;
            }
        }.execute();
    }

    public void updateFilm(Film film) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                myDatabase.getDaoAccess().updateFilm(film);
                return null;
            }
        }.execute();
    }

    public void deleteFilm(Film film) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                myDatabase.getDaoAccess().deleteFilm(film);
                return null;
            }
        }.execute();
    }

    public void deleteAllFilms() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                myDatabase.getDaoAccess().deleteAllFilms();
                return null;
            }
        }.execute();
    }

    public LiveData<List<Film>> readAllFilms() {
        return myDatabase.getDaoAccess().readAllFilms();
    }

    public LiveData<Film> readFilm(int idFilm) {
        return myDatabase.getDaoAccess().readFilm(idFilm);
    }

    public LiveData<Integer> readFilmsCount() {
        return myDatabase.getDaoAccess().readFilmsCount();
    }

}
