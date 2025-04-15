package marrone.giuseppe.watchlist.db;

import androidx.room.TypeConverter;

import marrone.giuseppe.watchlist.model.Date;

public class Converters {
    @TypeConverter
    public static Date toDate(Long dateLong){
        return dateLong == null ? null: new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null ? null : date.getTime();
    }
}
