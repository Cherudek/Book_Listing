package com.example.android.quakereport;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by Gregorio on 17/06/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Quake>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public EarthquakeLoader(Context context, String url) {
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
    public List<Quake> loadInBackground() {
        Log.v(LOG_TAG, "TEST: Background Tread started");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Quake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        Log.v(LOG_TAG, "TEST: Extracting List of Earthquakes");

        return earthquakes;
    }
}

