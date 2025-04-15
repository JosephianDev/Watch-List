package marrone.giuseppe.watchlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import marrone.giuseppe.watchlist.adapter.FilmAdapter;
import marrone.giuseppe.watchlist.db.DbRepository;
import marrone.giuseppe.watchlist.model.Date;
import marrone.giuseppe.watchlist.model.Film;

public class MainActivity extends AppCompatActivity {

    private Button btnAddNewFilm;
    private ListView lstFilms;
    private DbRepository dbRepository;
    private FilmAdapter filmAdapter;
    private boolean isDatabasePopulated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context ctx = this;

        btnAddNewFilm=findViewById(R.id.btn_add_film);
        lstFilms=findViewById(R.id.lst_films);

        filmAdapter=new FilmAdapter(this, R.layout.film_list_layout, new ArrayList<>());
        lstFilms.setAdapter(filmAdapter);
        dbRepository=new DbRepository(this);
/*
        dbRepository.readFilmsCount().observe(getLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer==0) {
                    if (!isDatabasePopulated) {
                        populateDb();
                        isDatabasePopulated = true;
                    }
                }
            }
        });
*/
        lstFilms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alertDialog=new AlertDialog.Builder(ctx).create();
                alertDialog.setTitle("Film selezionato");
                alertDialog.setMessage(((Film)lstFilms.getItemAtPosition(position)).getTitle());
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.msg_button_delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Film selectedFilm=(Film)lstFilms.getItemAtPosition(position);
                        dbRepository.deleteFilm(selectedFilm);
                        Toast.makeText(ctx, ctx.getResources().getString(R.string.msg_film_deleted_successful), Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.msg_button_update), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Film selectedFilm=(Film)lstFilms.getItemAtPosition(position);
                        Intent intent=new Intent(ctx, UpdateFilmActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("selected_film", selectedFilm);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.msg_button_close), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        btnAddNewFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AddNewFilmActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        dbRepository.readAllFilms().observe(getLifecycleOwner(), new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                filmAdapter.setFilms(films);
            }
        });

    }

    private LifecycleOwner getLifecycleOwner() {
        Context context=this;
        return (LifecycleOwner) context;
    }

    private void populateDb() {
        Film a = new Film("Quo vado?",new Date(1,1,2016),86,"Comedy","Italy");
        Film b = new Film("Titanic", new Date(19,12,1997),194,"Drama\nRomance","United States, Mexico");

        dbRepository.insertFilm(a);
        dbRepository.insertFilm(b);

    }
}