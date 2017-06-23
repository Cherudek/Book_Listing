package com.example.android.booklisting;

/**
 * Created by Gregorio on 06/06/2017.
 */

public class Book {


    // 6 variable instances
    private String mTitle;
    private String mAuthor;
    private String mImageUrl;
    private String mWebUrl;
    private String mDate;
    private String mPublisher;

    // 1 Constructor with 5 Strings and 1 integer
    public Book(String mImageUrl, String mAuthor, String mTitle, String mDate, String mPublisher, String mWebUrl) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mImageUrl = mImageUrl;
        this.mWebUrl = mWebUrl;
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
        return mImageUrl;
    }

    public String getmWeblink() {
        return mWebUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mCover=" + mImageUrl +
                ", mWeblink='" + mWebUrl + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mPublisher='" + mPublisher + '\'' +
                '}';
    }
}
