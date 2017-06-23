package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

/**
 * Created by Gregorio on 22/06/2017.
 */

public class LoadImage extends AsyncTask<Book, Void, Bitmap> {

    private ImageView imageView;
    private String smallThumb;

    public LoadImage(ImageView imageView, String smallThumb) {
        this.imageView = imageView;
        this.smallThumb = imageView.getTag().toString();
    }

    @Override
    protected Bitmap doInBackground(Book... params) {
        Bitmap bitmap = null;
        File file = new File(
                Environment.getExternalStorageDirectory().getAbsolutePath() + smallThumb);

        if (file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (!imageView.getTag().toString().equals(smallThumb)) {
               /* The path is not same. This means that this
                  image view is handled by some other async task.
                  We don't do anything and return. */
            return;
        }

        if (result != null && imageView != null) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(result);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}