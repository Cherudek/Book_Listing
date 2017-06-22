package com.example.android.booklisting;

/**
 * Created by Gregorio on 06/06/2017.
 */

public class Book {


    // 6 variable instances
    private String mTitle;
    private String mAuthor;
    private String mCover;
    private String mWeblink;
    private String mDate;
    private String mPublisher;

    // 1 Constructor with 5 Strings and 1 integer
    public Book(String mTitle, String mAuthor, String mCover, String mWeblink, String mDate, String mPublisher) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mCover = mCover;
        this.mWeblink = mWeblink;
        this.mDate = mDate;
        this.mPublisher = mPublisher;

    }

    public String getmDate() {
        return mDate;
    }

    public String getmPublisher() {
        return mPublisher;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmCover() {
        return mCover;
    }

    public String getmWeblink() {
        return mWeblink;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mCover=" + mCover +
                ", mWeblink='" + mWeblink + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mPublisher='" + mPublisher + '\'' +
                '}';
    }
}
