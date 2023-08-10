package sg.edu.rp.c346.id22027176.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Spinner spnrFilterRating;
    Button btnAdd;
    ListView lvMovies;
    ArrayList<Movies> moviesArrayList;
    ArrayAdapter ratingsAdapter;
    CustomAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnrFilterRating = findViewById(R.id.spinnerFilterRating);
        btnAdd = findViewById(R.id.btnAdd);
        lvMovies = findViewById(R.id.lvMovies);

        moviesArrayList = new ArrayList<Movies>();
        moviesAdapter = new CustomAdapter(this, R.layout.row, moviesArrayList);
        lvMovies.setAdapter(moviesAdapter);

        ratingsAdapter = ArrayAdapter.createFromResource(this, R.array.filter_ratings_array, android.R.layout.simple_spinner_item);
        ratingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrFilterRating.setAdapter(ratingsAdapter);

        final String[] rating = new String[1];
        spnrFilterRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String ratingSelected = spnrFilterRating.getSelectedItem().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                moviesArrayList.clear();
                if(!ratingSelected.equals("All")){
                    moviesArrayList.addAll(dbh.getMovies("rating", "=\""+ratingSelected+"\""));
                }
                else{
                    moviesArrayList.addAll(dbh.getMovies());
                }
                moviesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnAdd.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(i);
        });

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies selectedMovie = moviesArrayList.get(position);
                Intent i = new Intent(MainActivity.this, ModifyActivity.class);
                i.putExtra("movie", selectedMovie);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(MainActivity.this);
        moviesArrayList.clear();
        moviesArrayList.addAll(dbh.getMovies());
        moviesAdapter.notifyDataSetChanged();
    }
}