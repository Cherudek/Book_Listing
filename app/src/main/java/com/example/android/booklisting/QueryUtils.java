package com.example.android.booklisting;
/**
 * Created by Gregorio on 09/06/2017.
 */


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.booklisting.BookListingActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving books data from USGS.
 */
public final class QueryUtils {


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }


    /**
     * Query the Google Book data  set and return a list of {@link Book} objects.
     */
    public static List<Book> fetchBooksData(String requestUrl) {
        Log.v(LOG_TAG, "TEST: Fetch Earthquake Data");
        // Create URL object
        URL url = createUrl(requestUrl);

        //2 seconds delay added before fetching data to show progress bar
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Book}s
        List<Book> books = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return books;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "TEST: Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "TEST: Problem retrieving the books JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Return a list of {@link Book} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Book> extractFeatureFromJson(String bookJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding books to
        List<Book> books = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(bookJSON);

            // Extract the JSONArray associated with the key called "items",
            // which represents a list of features (or books).
            JSONArray bookItemArray = baseJsonResponse.getJSONArray("items");


            // For each book in the bookArray, create an {@link Book} object
            for (int i = 0; i < bookItemArray.length(); i++) {

                // Get a single book at position i within the list of book
                JSONObject currentBook = bookItemArray.getJSONObject(i);


                // For a given book, extract the JSONObject associated with the
                // key called "volumeInfo", which represents a list of all properties
                // for that book.
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                // Extract the value for the key called "mag"
                String title = volumeInfo.getString("title");
                Log.v(LOG_TAG, "TEST: the Title of the book is: " + title);


                String publisher;
                //conditional whether a publisher exist or not
                if (volumeInfo.has("publisher")) {
                    // Extract the value for the key called "publisher"
                    publisher = volumeInfo.getString("publisher");
                } else {
                    publisher = "N.A.";
                }


                String publishedDate;
                //conditional whether a publisher exist or not
                if (volumeInfo.has("publishedDate")) {
                    // Extract the value for the key called "publishDate"
                    publishedDate = volumeInfo.getString("publishedDate");
                } else {
                    publishedDate = "N.A.";
                }


                String url;
                if (volumeInfo.has("canonicalVolumeLink")) {
                    // Extract the value for the key called "canonicalVolumeLink"
                    url = volumeInfo.getString("canonicalVolumeLink");
                } else {
                    url = "N.A.";
                }


                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");


                String smallThumb;
                if (imageLinks.has("smallThumbnail")) {
                // Extract the value for the key called "imageLinks"
                    smallThumb = imageLinks.getString("smallThumbnail");
                } else {
                    smallThumb = "N.A.";
                }

                String author;
                if (volumeInfo.has("authors")) {
                    JSONArray authorsArray = volumeInfo.getJSONArray("authors");

                    author = authorsArray.getString(0);
                } else {
                    author = "N.A.";
                }


                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response.
                Book book = new Book(smallThumb, author, title, publishedDate, publisher, url);

                // Add the new {@link Earthquake} to the list of books.
                books.add(book);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("TEST: QueryUtils", "Problem parsing the book JSON results", e);
        }

        // Return the list of books
        return books;
    }

}

