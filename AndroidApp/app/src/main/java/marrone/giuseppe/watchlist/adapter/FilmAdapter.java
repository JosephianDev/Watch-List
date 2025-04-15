package marrone.giuseppe.watchlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import marrone.giuseppe.watchlist.R;
import marrone.giuseppe.watchlist.model.Film;

public class FilmAdapter extends ArrayAdapter<Film> {

    private Context context;
    private int layout;
    private List<Film> films;

    private View view;
    private ViewHolder viewHolder;

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView txtId;
        TextView txtTitle;
        TextView txtReleased;
        TextView txtRuntime;
        TextView txtGenre;
        TextView txtCountry;
    }

    public FilmAdapter(@NonNull Context context, int resource, @NonNull List<Film> objects) {
        super(context, resource, objects);
        this.context=context;
        this.films=objects;
        this.layout=resource;
    }

    @Nullable
    @Override
    public Film getItem(int position) {
        return films.get(position);
    }

    @Override
    public int getPosition(@Nullable Film item) {
        int position=0;
        for (Film film : films) {
            if(item.equals(film)) {
                break;
            }
            position++;
        }
        return position;
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(layout, null);
            viewHolder=new ViewHolder();
            viewHolder.txtId=convertView.findViewById(R.id.txt_film_id);
            viewHolder.txtTitle=convertView.findViewById(R.id.txt_film_title);
            viewHolder.txtReleased=convertView.findViewById(R.id.txt_film_released);
            viewHolder.txtRuntime=convertView.findViewById(R.id.txt_film_runtime);
            viewHolder.txtGenre=convertView.findViewById(R.id.txt_film_genre);
            viewHolder.txtCountry=convertView.findViewById(R.id.txt_film_country);

            convertView.setEnabled(true);
            convertView.setTag(viewHolder);
            this.view=convertView;
        }
        else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.txtId.setText(String.valueOf(films.get(position).getId()));
        viewHolder.txtTitle.setText(films.get(position).getTitle());
        viewHolder.txtReleased.setText(films.get(position).getReleased().toString());
        viewHolder.txtRuntime.setText(String.valueOf(films.get(position).getRuntime()));
        viewHolder.txtGenre.setText(films.get(position).getGenre());
        viewHolder.txtCountry.setText(films.get(position).getCountry());
        return convertView;
    }
}
