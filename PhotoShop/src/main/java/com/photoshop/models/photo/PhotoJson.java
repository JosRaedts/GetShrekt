package com.photoshop.models.photo;

/**
 * Created by Bram on 12-4-2015.
 */
public class PhotoJson {

    public String url;
    public String thumbnail_url;
    public String name;
    public String type;
    public String size;
    public String delete_url;
    public String delete_type;

    public PhotoJson(String url, String thumbnail_url, String name, String type, String size, String delete_url, String delete_type)
    {
        this.url=url;
        this.thumbnail_url=thumbnail_url;
        this.type=type;
        this.size=size;
        this.delete_url=delete_url;
        this.delete_type=delete_type;
    }
}
