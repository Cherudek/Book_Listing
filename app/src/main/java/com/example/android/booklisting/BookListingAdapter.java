package com.example.android.booklisting;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.graphics.drawable.GradientDrawable;

import com.squareup.picasso.Picasso;

/**
 * Created by Gregorio on 06/06/2017.
 */

public class BookListingAdapter extends ArrayAdapter<Book> {



    private static final String LOG_TAG = BookListingAdapter.class.getSimpleName();


    //Constructor for our customized Book Class
    public BookListingAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the book at the given position in the list of books
        Book currentBook = getItem(position);

        //original author String from the Book object and store that in a variable.
        String author = currentBook.getmAuthor();

        // Find the ImageView with view ID image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        // url string for the thumbnail image
        String imageUrl = currentBook.getmCover();

        // set the book cover image in the ImageView
        Picasso.with(getContext()).load(imageUrl).into(imageView);

        //original title String from the Book object and store that in a variable.
        String title = currentBook.getmTitle();
        Log.i(LOG_TAG, "TEST: the current book title is" + title);

        //original date String from the Book object and store that in a variable.
        String date = currentBook.getmDate();

        //original publisher String from the Book object and store that in a variable.
        String publisher = currentBook.getmPublisher();

        // url link string from the Book object and store it in a variable
        String url = currentBook.getmWeblink();


        // Find the TextView with view ID author
        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        // Display the author of the current book in that TextView
        authorView.setText(author);


        // Find the TextView with view ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        // Display the title of the current book in that TextView
        titleView.setText(title);


        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        // Display the date of the current book in that TextView
        dateView.setText(date);

        // Find the TextView with view ID publisher
        TextView timeView = (TextView) listItemView.findViewById(R.id.publisher);

        // Display the time of the current earthquake in that TextView
        timeView.setText(publisher);


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
