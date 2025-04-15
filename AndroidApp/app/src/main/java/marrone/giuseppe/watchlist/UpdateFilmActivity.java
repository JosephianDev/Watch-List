package marrone.giuseppe.watchlist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import marrone.giuseppe.watchlist.db.DbRepository;
import marrone.giuseppe.watchlist.model.Date;
import marrone.giuseppe.watchlist.model.Film;

public class UpdateFilmActivity extends AppCompatActivity {

    private EditText edtFilmTitle;
    private DatePicker dtpFilmReleased;
    private NumberPicker nmpFilmRuntime;
    private CheckBox[] chbFilmGenre;
    private Spinner spnFilmCountry;
    String[] countries = {"Italy", "United States", "China", "Japan", "United Kingdom", "France", "Germany", "India", "Brazil", "Russia", "Canada", "Australia", "South Korea", "Mexico", "Indonesia", "Turkey", "Switzerland", "South Africa", "Argentina", "Spain"};
    private Button btnSubmitNewFilm;
    private DbRepository dbRepository;

    private Film selectedFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_film);

        Context ctx=this;

        selectedFilm=(Film)getIntent().getExtras().getSerializable("selected_film");

        dbRepository=new DbRepository(this);

        edtFilmTitle=findViewById(R.id.edt_insert_film_title);
        dtpFilmReleased=findViewById(R.id.dtp_insert_film_release_date);
        nmpFilmRuntime=findViewById(R.id.nmp_insert_film_runtime);

        chbFilmGenre=new CheckBox[8];
        chbFilmGenre[0]=findViewById(R.id.chb_genre_action);
        chbFilmGenre[1]=findViewById(R.id.chb_genre_adventure);
        chbFilmGenre[2]=findViewById(R.id.chb_genre_comedy);
        chbFilmGenre[3]=findViewById(R.id.chb_genre_drama);
        chbFilmGenre[4]=findViewById(R.id.chb_genre_science_fiction);
        chbFilmGenre[5]=findViewById(R.id.chb_genre_horror);
        chbFilmGenre[6]=findViewById(R.id.chb_genre_romance);
        chbFilmGenre[7]=findViewById(R.id.chb_genre_thriller);

        spnFilmCountry=findViewById(R.id.spn_insert_film_country);
        btnSubmitNewFilm=findViewById(R.id.btn_submit_new_film);

        nmpFilmRuntime.setMinValue(40);// da 40 minuti
        nmpFilmRuntime.setMaxValue(360);// a 6 ore (anche se i film non hanno limiti di orario)

        ArrayAdapter<String> aa = new ArrayAdapter<>(ctx,android.R.layout.simple_spinner_item,countries);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFilmCountry.setAdapter(aa);

        if(selectedFilm !=null) {
            edtFilmTitle.setText(selectedFilm.getTitle());
            nmpFilmRuntime.setValue(selectedFilm.getRuntime());

            String[] genres = selectedFilm.getGenre().split("\n");
            for (CheckBox checkBox : chbFilmGenre)
                for (String genre : genres)
                    if(checkBox.getText().toString().equals(genre)) checkBox.setChecked(true);
            spnFilmCountry.setSelection(aa.getPosition(selectedFilm.getCountry()));
        }

        btnSubmitNewFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filmTitle=edtFilmTitle.getText().toString();
                Date filmReleased=new Date(dtpFilmReleased.getDayOfMonth(),dtpFilmReleased.getMonth(),dtpFilmReleased.getYear());
                int filmRuntime=nmpFilmRuntime.getValue();
                String filmGenre="";
                for (CheckBox checkBox : chbFilmGenre)
                    if (checkBox.isChecked()) filmGenre += " " + checkBox.getText().toString();
                String filmCountry=spnFilmCountry.getSelectedItem().toString();

                Film updatedFilm=new Film(filmTitle,filmReleased,filmRuntime,filmGenre,filmCountry);

                dbRepository.updateFilm(updatedFilm);

                Toast.makeText(ctx, ctx.getResources().getString(R.string.msg_film_update_successful), Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}