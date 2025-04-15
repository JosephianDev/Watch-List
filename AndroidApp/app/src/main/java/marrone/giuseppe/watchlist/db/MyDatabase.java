package marrone.giuseppe.watchlist.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import marrone.giuseppe.watchlist.model.Film;

@Database(entities = {Film.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MyDatabase extends RoomDatabase {
    public abstract DaoAccess getDaoAccess();
}
