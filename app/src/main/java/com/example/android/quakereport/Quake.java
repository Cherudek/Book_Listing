package com.example.android.quakereport;

/**
 * Created by Gregorio on 06/06/2017.
 */

public class Quake {


    // 3 variable instances
    private String mMagnitude;
    private String mPlace;
    private long mTimeInMilliseconds;



    // 1 Constructor with 3 Strings inputs
    public Quake(String mMagnitude, String mPlace, long timeInMilliseconds) {
        this.mMagnitude = mMagnitude;
        this.mPlace = mPlace;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    //Getter Method for the Date
    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    //Getter Method for the Magnitude
    public String getmMagnitude() {
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
                ", mDate=" + mTimeInMilliseconds +
                '}';
    }
}
