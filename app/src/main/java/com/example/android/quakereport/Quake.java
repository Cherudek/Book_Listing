package com.example.android.quakereport;

/**
 * Created by Gregorio on 06/06/2017.
 */

public class Quake {


    // 3 variable instances
    private Double mMagnitude;
    private String mPlace;
    private long mTimeInMilliseconds;
    private String mWeblink;


    // 1 Constructor with 1 String 2 doubles and 1 lonf inputs
    public Quake(Double mMagnitude, String mPlace, long timeInMilliseconds, String mWeblink) {
        this.mMagnitude = mMagnitude;
        this.mPlace = mPlace;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mWeblink = mWeblink;

    }

    //Getter Method for the URL of the web pahe
    public String getmWeblink() {
        return mWeblink;
    }

    //Getter Method for the Date
    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    //Getter Method for the Magnitude
    public Double getmMagnitude() {
        return mMagnitude;
    }

    //Getter Method for the Place
    public String getmPlace() {
        return mPlace;
    }

    @Override
    public String toString() {
        return "Quake{" +
                "mMagnitude=" + mMagnitude +
                ", mPlace='" + mPlace + '\'' +
                ", mTimeInMilliseconds=" + mTimeInMilliseconds +
                ", mWeblink='" + mWeblink + '\'' +
                '}';
    }
}
