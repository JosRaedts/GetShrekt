package com.photoshop.models.photo;

import com.photoshop.models.Database;
import com.photoshop.models.photo.Photo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class PhotoDao extends Database {
    public PhotoDao()
    {
        super();
    }
    
    public List<Photo> getList()
    {
        List<Photo> photos = new ArrayList();
        try {
            String querystring = "SELECT * FROM photos";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                photos.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photos;
    }
    
    public Photo getById(int id)
    {
        Photo photographer = null;
        try {
            String querystring = "SELECT * FROM photos WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                photographer = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photographer;
    }
    
    public boolean idExists(int id)
    {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM photos WHERE id = ?";
            PreparedStatement stat;
            stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
               exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }
    
    /*public boolean save(Photo photo)
    {
        try {
            String querystring = null;
            boolean exists = idExists(photo.getId());
            if(exists)
            {
                querystring = "UPDATE photos SET name = ?, username = ?, password = ? WHERE id = ?";                                
            }
            else
            {
                querystring = "INSERT INTO photographers(name, username, password) VALUES(?, ?, ?)";
            }
            
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setString(2, photographer.getUsername());
            stat.setString(3, photographer.getPassword());
            if(exists)
            {
                stat.setInt(4, photographer.getId());
            }
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PhotographerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }*/
    
    public void delete(Photo photo)
    {
        try {
            String querystring = "UPDATE photos SET active=0 WHERE id=?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photo.getId());
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Photo build(ResultSet rs)
    {
        Photo photo = null;
        try {            
            photo = new Photo(this);
            photo.setId(rs.getInt("id"));
            photo.setHeight(rs.getInt("height"));
            photo.setWidth(rs.getInt("width"));
            photo.setLowResURL(rs.getString("lowresURL"));
            photo.setHighResURL(rs.getString("highresURL"));
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photo;
    }
}
