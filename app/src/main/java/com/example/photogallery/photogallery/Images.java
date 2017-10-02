package com.example.photogallery.photogallery;

/**
 * Created by bafeng on 10/1/17.
 */

//to hold photo instance
public class Images {
    private String _title;
    private String _url;
    public Images(String title, String url){
        this._title = title;
        this._url = url;
    }
    public String getTitle(){
        return this._title;
    }
    public String getURL(){
        return this._url;
    }
}
