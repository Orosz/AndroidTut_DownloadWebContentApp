package com.orosz.myapp.downloadwebcontentapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {

        Log.i("URL", urls[0]);
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {

            //get link
            url = new URL(urls[0]);
            //open "browser"
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();
            char current;

            while (data != -1) {

                current = (char) data;
                result += current;

                data = reader.read();
            }

            Log.i("ResultBack", result);

            return result;


        } catch (Exception e) {
            e.printStackTrace();

            return "Failed! Check Link!";
        }
    }
}
