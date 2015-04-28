package com.photoshop.models.photo;

import com.mysql.jdbc.Statement;
import com.photoshop.models.Database;
import com.photoshop.models.school.School;
import com.photoshop.models.school.SchoolDao;
import com.photoshop.models.schoolClass.SchoolClass;
import com.photoshop.models.schoolClass.SchoolClassDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public List<Photo> getUnlinkedList(int id)
    {
        List<Photo> photos = new ArrayList();
        try {
            String querystring = "SELECT * FROM `photos` WHERE id NOT IN (SELECT photoID FROM student_photos) "
                    + "AND id NOT IN (SELECT photoID FROM schoolclass_photos) "
                    + "AND id NOT IN (SELECT photoID FROM school_photos) AND photographerID = ? ";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
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
                querystring = "INSERT INTO photos(height, width, thumbnailURL, lowresURL, highresURL, photographerID, active, date) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
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
    
    public void active(int id) 
    {
        try {
            String querystring = "SELECT id, active FROM photos WHERE id=?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            if(rs.getInt("active") == 1){
                querystring = "UPDATE photos SET active=0 WHERE id=?";
                stat = conn.prepareStatement(querystring);
                stat.setInt(1, id);
                stat.execute();
            }
            else{
                querystring = "UPDATE photos SET active=1 WHERE id=?";
                stat = conn.prepareStatement(querystring);
                stat.setInt(1, id);
                stat.execute();
            }
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
            photo.setThumbnailURL(rs.getString("thumbnailURL"));
            photo.setLowResURL(rs.getString("lowresURL"));
            photo.setHighResURL(rs.getString("highresURL"));
            photo.setPhotographerID(rs.getInt("photographerID"));
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
            String querystring = "SELECT p.id AS id, p.height AS height, p.width AS width, p.thumbnailURL AS thumbnailURL, p.lowresURL AS lowresURL, p.highresURL AS highresURL, p.photographerID AS photographerID, p.active AS active, p.date AS date, sp.photoID, sp.studentID FROM photos p, student_photos sp WHERE p.id = sp.photoID AND sp.studentID = ? ORDER BY date";
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
            String querystring = "SELECT id, height, width, thumbnailURL, lowresURL, highresURL, photographerID, active, date FROM photos WHERE photographerID = ? ORDER BY date";
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
    
     public List<Photo> getClassPhotosByStudentclass(int classid){
        ArrayList<Photo> photos = new ArrayList<Photo>();
        try {
            String querystring = "SELECT * FROM photos p,schoolclass_photos scp WHERE p.id = scp.id and schoolclassID = ? ORDER BY date";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, classid);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                photos.add(build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photos;
    }
     
    public List<Photo> getSchoolPhotos(int schoolid){
        ArrayList<Photo> photos = new ArrayList<Photo>();
        try {
            String querystring = "SELECT * FROM photos p,school_photos scp WHERE p.id = scp.photoID and scp.schoolID = ? ORDER BY date";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, schoolid);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                photos.add(build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photos;
    }

    @Autowired
    private StudentDao studentDao;

    public List<Student> getStudents(Photo photo)
    {
        ArrayList<Student> students = new ArrayList();
        try {
            String querystring = "SELECT students.* FROM students, student_photos sp WHERE photoID=? AND students.id=sp.studentID";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photo.getId());
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                System.out.println("Student found");
                students.add(studentDao.build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    @Autowired
    private SchoolClassDao schoolClassDao;

    public List<SchoolClass> getSchoolClasses(Photo photo)
    {
        ArrayList<SchoolClass> schoolClasses = new ArrayList<SchoolClass>();
        try {
            String querystring = "SELECT schoolclasses.* FROM schoolclasses, schoolclass_photos sp WHERE photoID=? AND schoolclasses.id=sp.schoolclassID";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photo.getId());
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                schoolClasses.add(schoolClassDao.build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schoolClasses;
    }

    @Autowired
    private SchoolDao schoolDao;

    public List<School> getSchools(Photo photo)
    {
        ArrayList<School> schools = new ArrayList<School>();
        try {
            String querystring = "SELECT schools.* FROM schools, school_photos sp WHERE photoID=? AND schools.id=sp.schoolID";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photo.getId());
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                schools.add(schoolDao.build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schools;
    }

    public void saveStudents(Photo photo, List<Student> students)
    {
        //DELETE ALL CONNECTIONS FIRST TO THIS PHOTO
        try {
            String querystring = "DELETE FROM student_photos WHERE photoID=?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photo.getId());
            stat.execute();
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        // SAVE NEW CONNECTIONS
        try {
            for(Student s : students) {
                String querystring = "INSERT INTO student_photos(photoID, studentID) VALUES(?, ?)";
                PreparedStatement stat = conn.prepareStatement(querystring);
                stat.setInt(1, photo.getId());
                stat.setInt(2, s.getId());
                stat.execute();
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveSchoolClasses(Photo photo, List<SchoolClass> schoolClasses)
    {
        //DELETE ALL CONNECTIONS FIRST TO THIS PHOTO
        try {
            String querystring = "DELETE FROM schoolclass_photos WHERE photoID=?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photo.getId());
            stat.execute();
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        // SAVE NEW CONNECTIONS
        try {
            for(SchoolClass sc : schoolClasses) {
                String querystring = "INSERT INTO schoolclass_photos(photoID, schoolclassID) VALUES(?, ?)";
                PreparedStatement stat = conn.prepareStatement(querystring);
                stat.setInt(1, photo.getId());
                stat.setInt(2, sc.getId());
                stat.execute();
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveSchools(Photo photo, List<School> schools)
    {
        //DELETE ALL CONNECTIONS FIRST TO THIS PHOTO
        try {
            String querystring = "DELETE FROM school_photos WHERE photoID=?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photo.getId());
            stat.execute();
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        // SAVE NEW CONNECTIONS
        try {
            for(School s : schools) {
                String querystring = "INSERT INTO school_photos(photoID, schoolID) VALUES(?, ?)";
                PreparedStatement stat = conn.prepareStatement(querystring);
                stat.setInt(1, photo.getId());
                stat.setInt(2, s.getId());
                stat.execute();
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
