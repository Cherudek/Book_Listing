package com.example.android.quakereport;

/**
 * Created by Gregorio on 06/06/2017.
 */

public class Quake {

    // 3 variable instances
    private String mMagnitude;
    private String mPlace;
    private String mDate;

    // 1 Constructor with 3 Strings inputs
    public Quake(String mMagnitude, String mPlace, String mDate) {
        this.mMagnitude = mMagnitude;
        this.mPlace = mPlace;
        this.mDate = mDate;
    }

    //Getter Method for the Date
    public String getmDate() {
        return mDate;
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
                ", mDate=" + mDate +
                '}';
    }
}
