package sg.edu.rp.c346.id22027176.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {

    EditText id, title, genre, year;
    Spinner spnrRating;
    Button btnEdit, btnDel, btnCancel;
    ArrayAdapter ratingsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        id = findViewById(R.id.editTextID);
        title = findViewById(R.id.editTextTitle);
        genre = findViewById(R.id.editTextGenre);
        year = findViewById(R.id.editTextYear);
        spnrRating = findViewById(R.id.spinnerRating);
        btnEdit = findViewById(R.id.btnEdit);
        btnDel = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        Movies movie = (Movies) i.getSerializableExtra("movie");

        ratingsAdapter = ArrayAdapter.createFromResource(this, R.array.ratings_array, android.R.layout.simple_spinner_item);
        ratingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrRating.setAdapter(ratingsAdapter);

        id.setText(String.valueOf(movie.getId()));
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());
        year.setText(String.valueOf(movie.getYear()));
        spnrRating.setSelection(ratingsAdapter.getPosition(movie.getRating()));

        final String[] rating = new String[1];
        spnrRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                rating[0] = spnrRating.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie.setTitle(title.getText().toString());
                movie.setGenre(genre.getText().toString());
                movie.setYear(Integer.valueOf(year.getText().toString()));
                movie.setRating(rating[0]);
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.updateMovie(movie);
                Toast.makeText(ModifyActivity.this, "Changes saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyActivity.this);
                builder.setTitle("Danger");
                builder.setMessage("Are you sure you want to delete the movie \"" + movie.getTitle() + "\"?");
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ModifyActivity.this);
                        dbh.deleteMovie(movie.getId());
                        Toast.makeText(ModifyActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNeutralButton("CANCEL", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyActivity.this);
                builder.setTitle("Danger");
                builder.setMessage("Are you sure you want to discard the changes?");
                builder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("DO NOT DISCARD", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}