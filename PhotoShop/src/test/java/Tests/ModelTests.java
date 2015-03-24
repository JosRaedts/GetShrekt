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
import com.photoshop.models.school.School;
import com.photoshop.models.school.SchoolDao;
import com.photoshop.models.schoolClass.SchoolClass;
import com.photoshop.models.schoolClass.SchoolClassDao;
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
 * @author Willem
 */
public class ModelTests {
    
    Student student;
    Photographer photographer;
    Admin admin;
    School school;
    SchoolClass schoolClass;
            
    
    public ModelTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        StudentDao studentDao = new StudentDao();
        AdminDao adminDao = new AdminDao();
        PhotographerDao photographerDao = new PhotographerDao();
        SchoolDao schoolDao = new SchoolDao();
        SchoolClassDao schoolClassDao = new SchoolClassDao();
        
        this.student = studentDao.getById(2);
        this.photographer = photographerDao.getById(2);
        this.admin = adminDao.getById(1);
        this.school = schoolDao.getById(1);
        this.schoolClass = schoolClassDao.getById(1);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testStudent() {
        assertEquals("Student name should be Willem de Kok", "Willem de Kok", student.getName());
        assertEquals("Student adress should be Orion 31", "Orion 31", student.getAddress());
        assertEquals("Student city should be Hapert", "Hapert", student.getCity());
        assertEquals("Student ID should be 2", 2, student.getId());
        assertEquals("Student ClassID should be 1", 1, student.getSchoolclass_id());
        assertEquals("Student StudentID should be 297166", 297166, student.getStudentnr());
        assertEquals("Student Username should be Terrorjoekel", "Terrorjoekel", student.getUsername());
        assertEquals("Student Zipcode should be 5527CR", "5527CR", student.getZipcode());
        
        student.setAddress("Orion 32");
        student.setCity("Eindhoven");
        student.setName("Willem");
        student.setSchoolclass_id(2);
        student.setStudentnr(2971662);
        student.setUsername("Wilhelmus Theodorus Maria de Kok");
        student.setZipcode("1234AB");
        
        assertEquals("Student name should be Willem", "Willem", student.getName());
        assertEquals("Student adress should be Orion 32", "Orion 32", student.getAddress());
        assertEquals("Student city should be Eindhoven", "Eindhoven", student.getCity());
        assertEquals("Student ID should be 2", 2, student.getId());
        assertEquals("Student ClassID should be 2", 2, student.getSchoolclass_id());
        assertEquals("Student StudentID should be 2971662", 2971662, student.getStudentnr());
        assertEquals("Student Username should be Wilhelmus Theodorus Maria de Kok", "Wilhelmus Theodorus Maria de Kok", student.getUsername());
        assertEquals("Student Zipcode should be 1234AB", "1234AB", student.getZipcode());
        
        student.setAddress("Orion 31");
        student.setCity("Hapert");
        student.setName("Willem de Kok");
        student.setSchoolclass_id(1);
        student.setStudentnr(297166);
        student.setUsername("Terrorjoekel");
        student.setZipcode("5527CR");
        
        assertEquals("Student name should be Willem de Kok", "Willem de Kok", student.getName());
        assertEquals("Student adress should be Orion 31", "Orion 31", student.getAddress());
        assertEquals("Student city should be Hapert", "Hapert", student.getCity());
        assertEquals("Student ID should be 2", 2, student.getId());
        assertEquals("Student ClassID should be 1", 1, student.getSchoolclass_id());
        assertEquals("Student StudentID should be 297166", 297166, student.getStudentnr());
        assertEquals("Student Username should be Terrorjoekel", "Terrorjoekel", student.getUsername());
        assertEquals("Student Zipcode should be 5527CR", "5527CR", student.getZipcode());
    }
    
    @Test
    public void testPhotographer() {
        assertEquals("Photographer name should be Willempie", "Willempie", photographer.getName());
        assertEquals("Photographer usernamename should be Willempie", "Willempie", photographer.getUsername());
        
        photographer.setName("temp");
        photographer.setUsername("Willems");
        
        assertEquals("Photographer name should be temp", "temp", photographer.getName());
        assertEquals("Photographer usernamename should be Willems", "Willems", photographer.getUsername());
        
        photographer.setName("Willempie");
        photographer.setUsername("Willempie");
        
        assertEquals("Photographer name should be Willempie", "Willempie", photographer.getName());
        assertEquals("Photographer usernamename should be Willempie", "Willempie", photographer.getUsername());
    }
    
    @Test
    public void testAdmin() {
        assertEquals("Admin name should be admin", "admin", admin.getName());
        assertEquals("Admin usernamename should be Admin", "Admin", admin.getUsername());
        
        admin.setName("administrator");
        admin.setUsername("administrator");
                
        assertEquals("Admin name should be administrator", "administrator", admin.getName());
        assertEquals("Admin usernamename should be administrator", "administrator", admin.getUsername());
        
        admin.setName("admin");
        admin.setUsername("Admin");
        
        assertEquals("Admin name should be admin", "admin", admin.getName());
        assertEquals("Admin usernamename should be Admin", "Admin", admin.getUsername());
    }
    
    @Test
    public void testSchool() {
        assertEquals("School Address should be Rachelsmolen 1", "Rachelsmolen 1", school.getAddress());
        assertEquals("School city should be Eindhoven", "Eindhoven", school.getCity());
        assertEquals("School code should be fon", "fon", school.getCode());
        assertEquals("School ID should be 1", 1, school.getId());
        assertEquals("School name should be Fontys Hogeschool ICT Nerdjes", "Fontys Hogeschool ICT Nerdjes", school.getName());
        assertEquals("School zipcode should be 5612MA", "5612MA", school.getZipcode());
        
        school.setAddress("Blaze it 32");
        school.setCity("Tilburg");
        school.setCode("r3kt");
        school.setName("testschool");
        school.setZipcode("1235AC");
        
        assertEquals("School Address should be Blaze it 32", "Blaze it 32", school.getAddress());
        assertEquals("School city should be Tilburg", "Tilburg", school.getCity());
        assertEquals("School code should be r3kt", "r3kt", school.getCode());
        assertEquals("School ID should be 1", 1, school.getId());
        assertEquals("School name should be testschool", "testschool", school.getName());
        assertEquals("School zipcode should be 1235AC", "1235AC", school.getZipcode());
        
        school.setAddress("Rachelsmolen 1");
        school.setCity("Eindhoven");
        school.setCode("fon");
        school.setName("Fontys Hogeschool ICT Nerdjes");
        school.setZipcode("5612MA");
        
        assertEquals("School Address should be Rachelsmolen 1", "Rachelsmolen 1", school.getAddress());
        assertEquals("School city should be Eindhoven", "Eindhoven", school.getCity());
        assertEquals("School code should be fon", "fon", school.getCode());
        assertEquals("School ID should be 1", 1, school.getId());
        assertEquals("School name should be Fontys Hogeschool ICT Nerdjes", "Fontys Hogeschool ICT Nerdjes", school.getName());
        assertEquals("School zipcode should be 5612MA", "5612MA", school.getZipcode());
    }
    
    @Test
    public void testSchoolClass() {
        assertEquals("SchoolClass ID should be 1", 1, schoolClass.getId());
        assertEquals("SchoolClass name should be SE42", "SE42", schoolClass.getName());
        
        schoolClass.setName("test");
        
        assertEquals("SchoolClass name should be test", "test", schoolClass.getName());
        
        schoolClass.setName("SE42");
    }
}
