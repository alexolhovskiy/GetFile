package com.example.getfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ImageView imageView=findViewById(R.id.imageView);
        Intent intent=getIntent();
        if(intent!=null){
            Glide.with(ImageViewerActivity.this).load(intent.getStringExtra("Image")).placeholder(R.drawable.ic_baseline_broken_image_24).into(imageView);
        }
    }
}