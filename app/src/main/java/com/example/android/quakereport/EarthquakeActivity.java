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
package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        //Creating an ArrayList with 10 items
        final ArrayList<Quake> quakes = new ArrayList<Quake>();
        quakes.add(new Quake("7.2", "San Francisco", "Feb 2 2016"));
        quakes.add(new Quake("6.1", "London", "Jul 20, 2015"));
        quakes.add(new Quake("3.9", "Tokyo", "Nov 10, 2014"));
        quakes.add(new Quake("5.4", "Mexico", "Jul 31, 2014"));
        quakes.add(new Quake("2.8", "Moscow", "Jan 31, 2013"));
        quakes.add(new Quake("4.9", "Rio De Janeiro", "Aug 19, 2012"));
        quakes.add(new Quake("1.6", "Paris", "Oct 30, 2011"));


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        QuakeAdapter adapter = new QuakeAdapter(this, quakes);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
