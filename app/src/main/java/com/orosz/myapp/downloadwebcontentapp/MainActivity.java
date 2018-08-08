package com.orosz.myapp.downloadwebcontentapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText linkEditText;

    TextView siteContentTextView;
    ImageView picContentImageView;

    String link;

    public void resetParams() {

        siteContentTextView.setText("");
        siteContentTextView.setVisibility(View.INVISIBLE);
        picContentImageView.setImageResource(0);
        picContentImageView.setVisibility(View.INVISIBLE);
    }

    public void pictureContentDownload(View view) {

        resetParams();

        link = String.valueOf(linkEditText.getText());

        ImageDownloadTask task = new ImageDownloadTask();
        Bitmap myImage;

        try {

            myImage = task.execute(link).get();
            picContentImageView.setImageBitmap(myImage);

            picContentImageView.setVisibility(View.VISIBLE);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void siteContentDownload(View view) {

        resetParams();

        link = String.valueOf(linkEditText.getText());

        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute(link).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("Result", result);

        siteContentTextView.setText(result);
        siteContentTextView.setMovementMethod(new ScrollingMovementMethod());
        siteContentTextView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkEditText = (EditText)findViewById(R.id.linkEditText);
        siteContentTextView = (TextView)findViewById(R.id.contentTextView);
        picContentImageView = (ImageView)findViewById(R.id.picImageView);


    }
}
