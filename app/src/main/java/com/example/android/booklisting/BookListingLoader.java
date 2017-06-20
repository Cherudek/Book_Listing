package com.example.android.booklisting;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by Gregorio on 17/06/2017.
 */

public class BookListingLoader extends AsyncTaskLoader<List<Book>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = BookListingLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link BookListingLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public BookListingLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG, "TEST: Background Tread loaded");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Book> loadInBackground() {
        Log.v(LOG_TAG, "TEST: Background Tread started");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<Book> books = QueryUtils.fetchBooksData(mUrl);
        Log.v(LOG_TAG, "TEST: Extracting List of Earthquakes");

        return books;
    }
}

