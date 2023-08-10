package sg.edu.rp.c346.id22027176.mymovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movies> contactList;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        contactList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.textViewMovieTitle);
        TextView tvGenre = rowView.findViewById(R.id.textViewMovieGenre);
        TextView tvYear = rowView.findViewById(R.id.textViewMovieYear);
        ImageView ivRating = rowView.findViewById(R.id.imageViewRating);

        Movies currentItem = contactList.get(position);
        tvTitle.setText(currentItem.getTitle());
        tvGenre.setText(currentItem.getGenre());
        tvYear.setText(currentItem.getYear() + "");

        String imageUrl = "";
        switch (currentItem.getRating()){
            case "U":
                imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16277-28797ce.jpg?quality=90&webp=true&fit=584,471"; //is this public domain? hope im not getting in trouble. theres a copyright logo, dunno those laws
                Picasso.with(parent_context).load(imageUrl).into(ivRating);
                break;
            case "PG":
                imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16278-28797ce.jpg?quality=90&webp=true&fit=584,471";
                Picasso.with(parent_context).load(imageUrl).into(ivRating);
                break;
            case "12A":
                imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16279-8d5bdb7.jpg?quality=90&webp=true&fit=490,490";
                Picasso.with(parent_context).load(imageUrl).into(ivRating);
                break;
            case "12":
                imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16280-8d5bdb7.jpg?quality=90&webp=true&fit=320,320";
                Picasso.with(parent_context).load(imageUrl).into(ivRating);
                break;
            case "15":
                imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16281-8d5bdb7.jpg?quality=90&webp=true&fit=490,490";
                Picasso.with(parent_context).load(imageUrl).into(ivRating);
                break;
            case "18":
                imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16282-05127b2.jpg?quality=90&webp=true&fit=300,300";
                Picasso.with(parent_context).load(imageUrl).into(ivRating);
                break;
            case "R18":
                imageUrl = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16283-05127b2.jpg?quality=90&webp=true&fit=515,424";
                Picasso.with(parent_context).load(imageUrl).into(ivRating);
                break;
        }

        return rowView;
    }
}
