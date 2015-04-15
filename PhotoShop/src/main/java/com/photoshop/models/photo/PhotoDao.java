package com.photoshop.models.photo;

import com.mysql.jdbc.Statement;
import com.photoshop.models.Database;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jos
 */

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
    
    public boolean save(Photo photo)
    {
        try {
            PreparedStatement stat;
            String querystring = null;
            boolean exists = idExists(photo.getId());
            if(exists)
            {
                querystring = "UPDATE photos SET height = ?, width = ?, thumbnailURL = ?, lowresURL = ?, highresURL = ?, photographerID = ?, active = ?, date = ? WHERE id = ?";
                stat = conn.prepareStatement(querystring);
            }
            else
            {
                querystring = "INSERT INTO photos(height, width, thumbnailURL = ?, lowresURL, highresURL, photographerID, active, date) VALUES(?, ?, ?, ?, ?, ?, ?)";
                stat = conn.prepareStatement(querystring, Statement.RETURN_GENERATED_KEYS);
            }

            stat.setInt(1, photo.getHeight());
            stat.setInt(2, photo.getWidth());
            stat.setString(3, photo.getThumbnailURL());
            stat.setString(4, photo.getLowResURL());
            stat.setString(5, photo.getHighResURL());
            stat.setInt(6, photo.getPhotographerID());
            if(photo.getActive()){
                stat.setInt(7, 1);
            }
            else{
                stat.setInt(7, 0);
            }
            stat.setDate(8, (Date)photo.getDate());
            if(exists)
            {
                stat.setInt(9, photo.getId());
            }
            stat.execute();
            if(!exists)
            {
                ResultSet rs = stat.getGeneratedKeys();
                rs.next();
                photo.setId(rs.getInt(1));
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
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
            photo.setThumbnailURL("thumnailURL");
            photo.setLowResURL(rs.getString("lowresURL"));
            photo.setHighResURL(rs.getString("highresURL"));
            if(rs.getInt("active") == 1){
                photo.setActive(true);
            }
            else{
                photo.setActive(false);
            }            
            photo.setDate(rs.getDate("date"));
        } catch (SQLException ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photo;
    }

    public List<Photo> getPhotosByStudent(int id){
        ArrayList<Photo> photos = new ArrayList<Photo>();
        try {
            String querystring = "SELECT p.id AS id, p.height AS height, p.width AS width, p.lowresURL AS lowresURL, p.highresURL AS highresURL, p.photographerID AS photographerID, p.active AS active, p.date AS date, sp.photoID, sp.studentID FROM photos p, student_photos sp WHERE p.id = sp.photoID AND sp.studentID = ? ORDER BY date";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                photos.add(build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photos;
    }
    
    public List<Photo> getPhotosByPhotographer(int id){
        ArrayList<Photo> photos = new ArrayList<Photo>();
        try {
            String querystring = "SELECT id, height, width, lowresURL, highresURL, photographerID, active, date FROM photos WHERE photographerID = ? ORDER BY date";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                photos.add(build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photos;
    }
}
