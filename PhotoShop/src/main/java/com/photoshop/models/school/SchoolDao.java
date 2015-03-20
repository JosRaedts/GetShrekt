/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.school;

import com.photoshop.models.Database;
import com.photoshop.models.schoolClass.SchoolClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Casper
 */
@Component
public class SchoolDao extends Database {

    public SchoolDao() {
        super();
    }

    public List<School> getList() {
        List<School> school = new ArrayList();
        try {
            String querystring = "SELECT * FROM schools";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                school.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return school;
    }

    public School getSchoolBySchoolClass(int id) {
        School school = new School();
        try {
            String querystring = "SELECT * FROM schools, schoolclasses WHERE schoolclasses.school_id = ? AND schools.id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            stat.setInt(2, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {

                school = build(rs);
            }
        } catch (Exception ex) {
            Logger.getLogger(SchoolDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return school;
    }

    public School getById(int id) {
        School school = null;
        try {
            String querystring = "SELECT * FROM schools WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                school = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return school;
    }

    public boolean idExists(int id) {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM schools WHERE id = ?";
            PreparedStatement stat;
            stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SchoolDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    public void save(School school) {
        try {
            String querystring = null;
            boolean exists = idExists(school.getId());
            if (exists) {
                querystring = "UPDATE schools SET name = ?, address = ?, city = ?, zipcode = ?, code = ? WHERE id = ?";
            } else {
                querystring = "INSERT INTO schools(name, address, city, zipcode, code) VALUES(?, ?, ?, ?, ?)";
            }

            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setString(1, school.getName());
            stat.setString(2, school.getAddress());
            stat.setString(3, school.getCity());
            stat.setString(4, school.getZipcode());
            stat.setString(5, school.getCode());
            if (exists) {
                stat.setInt(6, school.getId());
            }
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SchoolDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(School school) {
        try {
            String querystring = "DELETE FROM schools WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, school.getId());
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SchoolDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private School build(ResultSet rs) {
        School school = null;
        try {
            school = new School(this);
            school.setId(rs.getInt("id"));
            school.setName(rs.getString("name"));
            school.setAddress(rs.getString("address"));
            school.setCity(rs.getString("city"));
            school.setZipcode(rs.getString("zipcode"));
            school.setCode(rs.getString("code"));
        } catch (SQLException ex) {
            Logger.getLogger(SchoolDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return school;
    }
}
