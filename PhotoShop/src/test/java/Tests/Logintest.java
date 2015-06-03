/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import com.photoshop.models.admin.Admin;
import com.photoshop.models.admin.AdminDao;
import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bart
 */
public class Logintest {
    
    PhotographerDao photographerDao;
    StudentDao studentDao;
    AdminDao admindao;
    
    public Logintest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        photographerDao = new PhotographerDao();
        studentDao = new StudentDao();
        admindao = new AdminDao();   
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void Testfakelogin()
    {
        String name;
        String password;
        Photographer photographer;
        Admin admin;
        Student student;
        
        name = "test";
        password = "test";
        photographer = photographerDao.authenticate(name, password);
        assertNull(photographer);
        
        name = "test2";
        password = "test2";
        admin = admindao.authenticate(name, password);
        assertNull(admin);
             
        name = "test3";
        password = "test3";
        student = this.studentDao.authenticate(name, password);
        assertNull(student);
        
        name = "henk";
        password = "henk";
        photographer = photographerDao.authenticate(name, password);
        assertNull(photographer);
        
        String name2 = "klaas";
        String password2 = "klaas";
        admin = admindao.authenticate(name2, password2);
        assertNull(admin);
             
        String name3 = "piet";
        String password3 = "piet";
        student = this.studentDao.authenticate(name3, password3);
        assertNull(student);
    }
    
    @Test
    public void Testreallogin()
    {
        String name;
        String password;
        Photographer photographer;
        Admin admin;
        Student student;
        
        name = "photo";
        password = "photo";
        photographer = photographerDao.authenticate(name, password);
        assertNotNull(photographer);
        
        name = "admin";
        password = "admin";
        admin = admindao.authenticate(name, password);
        assertNotNull(admin);
             
        name = "student";
        password = "student";
        student = this.studentDao.authenticate(name, password);
        assertNotNull(student);
    }

}
