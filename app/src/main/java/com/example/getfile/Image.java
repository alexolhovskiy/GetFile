package com.example.getfile;

public class Image {
    String title;
    String path;
    long size;

    public Image(String title,String path,long size){
        this.title=title;
        this.path=path;
        this.size=size;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }
}
