/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.content.Loader;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookListingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = BookListingActivity.class.getName();
    /**
     * Constant value for the book loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOKS_LOADER_ID = 1;
    /**
     * URL to query the Google Book Api dataset for book information
     */
    private static final String GOOGLE_BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?maxResults=30&q=";

    //Variable for the SearchView
    private SearchView searchView;

    //Variable for the user input
    private String mQuery;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;
    /**
     * ImageView that is displayed when the list is empty
     */
    private ImageView mEmptyStateImageView;
    /**
     * Progress Bar that is displayed when the data is loading
     */
    private ProgressBar mProgressBarView;
    /**
     * Adapter for the list of books
     */
    private BookListingAdapter mAdapter;

    //ListView that is displayed when the list is empty
    private ListView bookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_listing_activity);


        // Find a reference to the {@link ListView} in the layout
        bookListView = (ListView) findViewById(R.id.list);

        // Find a reference to the {@link SearchView} in the layout
        searchView = (SearchView) findViewById(R.id.search_view);

        // Find a reference to the {@link loading_spinner} in the layout
        mProgressBarView = (ProgressBar) findViewById(R.id.loading_spinner);

        // Find a reference to the {@link ListView} in the layout
        final ListView bookListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_text_view);
        bookListView.setEmptyView(mEmptyStateTextView);

        mEmptyStateImageView = (ImageView) findViewById(R.id.empty_image_view);
        bookListView.setEmptyView(mEmptyStateImageView);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookListingAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected book.
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Find the current book that was clicked on
                Book currentBook = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri booklistingUri = Uri.parse(currentBook.getmWeblink());

                // Create a new intent to view the book URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, booklistingUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


        //Set an OnQueryTextListener to the search button
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String newText) {
                //Get the query given by the user
                mQuery = searchView.getQuery().toString();
                mQuery = mQuery.replace(" ", "+");
                //Restart the Loader upon the search query(execute the search)
                getLoaderManager().restartLoader(BOOKS_LOADER_ID, null, BookListingActivity.this);
                return true;
            }
        });



        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOKS_LOADER_ID, null, this);
            Log.v(LOG_TAG, "TEST: Calling the LoaderCallBack");

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet);
            Log.i(LOG_TAG, "TEST: No Internet Connectivity");

        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG, "TEST: New Loader initialised for the url provided");

        mProgressBarView.setVisibility(View.VISIBLE);
        bookListView.setVisibility(View.INVISIBLE);
        mEmptyStateTextView.setVisibility(View.GONE);
        mEmptyStateImageView.setVisibility(View.GONE);

        String requestUrl = "";
        if (mQuery != null && mQuery != "") {
            requestUrl = GOOGLE_BOOKS_REQUEST_URL + mQuery;
        } else {
            String defaultQuery = "android";
            requestUrl = GOOGLE_BOOKS_REQUEST_URL + defaultQuery;
        }

        // Create a new loader for the given URL
        return new BookListingLoader(this, requestUrl);
    }


    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> bookListings) {
        Log.v(LOG_TAG, "TEST: Loader Cleared");

        // Hide loading indicator because the data has been loaded
        mProgressBarView.setVisibility(View.GONE);

        // Clear the adapter of previous books data
        mAdapter.clear();

        // If there is a valid list of {@link book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (bookListings != null && !bookListings.isEmpty()) {
            mAdapter.addAll(bookListings);
        } else {
            // Set empty state image to display a Crocodile Chilling"
            mEmptyStateImageView.setImageResource(R.drawable.relaxs);
            mEmptyStateTextView.setText(R.string.empty_state);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.v(LOG_TAG, "TEST: Loader cleared of existing data");

        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}

