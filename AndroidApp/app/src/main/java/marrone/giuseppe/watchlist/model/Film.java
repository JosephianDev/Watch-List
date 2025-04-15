package marrone.giuseppe.watchlist.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Film implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    protected String title;
    private Date released;
    private int runtime;
    private String genre;
    private String country;

    //titolo
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    //anno
    public void setYear(int year) {
        released.setYear(year);
    }
    public int getYear() {
        return released.getYear();
    }

    //data di pubblicazione
    public void setReleaseDate(Date releaseDate) {
        released = releaseDate;
    }
    public Date getReleaseDate() {
        return released;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    //durata
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public int getRuntime() {
        return runtime;
    }

    //generi
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    //paese
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return country;
    }


    @NonNull
    @Override
    public String toString() {
        return "Title: "+getTitle()+"\n"
                + "Year: "+getYear()+"\n"
                + "Released: "+getReleaseDate().toString()+"\n"
                + "Runtime: "+getRuntime()+" min.\n"
                + "Genres: "+getGenre()+"\n"
                + "Country: "+getCountry()+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && runtime == film.runtime && title.equals(film.title)
                && Objects.equals(released, film.released) && Objects.equals(genre, film.genre)
                && Objects.equals(country, film.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, released, runtime, genre, country);
    }

    //costruttore
    public Film(Film mov){
        setId(mov.getId());
        setTitle(mov.getTitle());
        setReleaseDate(mov.getReleaseDate());
        setRuntime(mov.getRuntime());
        setGenre(mov.getGenre());
        setCountry(mov.getCountry());
    }

    public Film(@NonNull String title, Date released, int runtime, String genre, String country) {
        this.title = title;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.country = country;
    }
}
