package com.example.photogallery.photogallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/// Activity to show the large image
public class FullImageActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_over_image);

        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        String title = intent.getExtras().getString("title");
        if (title == ""){
            title = "no title...";
        }

        TextView textView = (TextView)findViewById(R.id.full_image_text_title) ;
        textView.setText(title);
        ImageView imageView = (ImageView)findViewById(R.id.full_image_single);
        Glide.with(this).load(url).into(imageView);
    }

    public void clickEvent(View v)
    {
        onBackPressed();

    }


}