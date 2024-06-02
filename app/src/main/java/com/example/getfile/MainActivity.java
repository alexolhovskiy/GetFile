package com.example.getfile;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Image>arrayList=new ArrayList<>();
    private final ActivityResultLauncher<String> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.RequestPermission(),
//            new ActivityResultCallback<Boolean>() {
//                @Override
//                public void onActivityResult(Boolean result) {
//                    if(result){
//                        getImage();
//                    }
//                }
//            });
            result -> {
                if(result){
                    getImages();
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else{
            if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                activityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }else{
                getImages();
            }
        }
    }

    private void getImages(){
        arrayList.clear();
        String filePath="/storage/emulated/0/Pictures";//"/storage/emulated/0/DCIM/Camera";//
        File file=new File(filePath);
        File[]files=file.listFiles();
        Toast.makeText(MainActivity.this,"Open"+filePath,Toast.LENGTH_LONG).show();
        if(files!=null){
            for(File file1:files){
                Toast.makeText(MainActivity.this,"Open file"+file1.getPath(),Toast.LENGTH_LONG).show();
                if(file1.getPath().endsWith(".png")||file1.getPath().endsWith(".jpg")){
                    arrayList.add(new Image(file1.getName(), file1.getPath(),file1.length()));
                }
            }
        }else{
            Toast.makeText(MainActivity.this,"Files are null",Toast.LENGTH_LONG).show();
        }

        Toast.makeText(MainActivity.this,"All Files"+arrayList.size(),Toast.LENGTH_LONG).show();


        ImageAdapter adapter=new ImageAdapter(MainActivity.this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, String path) {
                startActivities(new Intent[]{new Intent(MainActivity.this, ImageViewerActivity.class).putExtra("Image", path)});
            }
        });
    }
}