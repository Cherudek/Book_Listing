package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gregorio on 06/06/2017.
 */

public class QuakeAdapter extends ArrayAdapter<Quake> {

    private static final String LOG_TAG = QuakeAdapter.class.getSimpleName();


    //Constructor for our customized Quake Class
    public QuakeAdapter(Activity context, ArrayList<Quake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Quake} object located at this position in the list
        Quake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID list item 1
        TextView magTextView = (TextView) listItemView.findViewById(R.id.list_item_1);

        // Get the version name from the current Quake object and
        // set this text on the Magnitude TextView
        magTextView.setText(currentEarthquake.getmMagnitude());

        // Find the TextView in the list_item.xml layout with the ID list item 2
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.list_item_2);

        // Get the version name from the current Quake object and
        // set this text on the Location TextView
        placeTextView.setText(currentEarthquake.getmPlace());

        // Find the TextView in the list_item.xml layout with the ID list item 3
        TextView dateTextVIew = (TextView) listItemView.findViewById(R.id.list_item_3);

        // Get the version name from the current Quake object and
        // set this text on the Date TextView
        dateTextVIew.setText(currentEarthquake.getmDate());

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
