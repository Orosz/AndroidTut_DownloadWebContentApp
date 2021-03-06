package com.orosz.myapp.downloadwebcontentapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... urls) {

        URL url = null;
        try {

            url = new URL(urls[0]);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.connect();

            InputStream inputStream = connection.getInputStream();

            Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

            return myBitmap;


        } catch (MalformedURLException e) {

            e.printStackTrace();

        }catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }
}
