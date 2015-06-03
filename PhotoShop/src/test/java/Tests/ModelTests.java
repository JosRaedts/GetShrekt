/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import com.photoshop.misc.ImageManager;
import com.photoshop.models.address.Address;
import com.photoshop.models.admin.Admin;
import com.photoshop.models.admin.AdminDao;
import com.photoshop.models.cartproduct.Cartproduct;
import com.photoshop.models.cartproduct.CartproductDao;
import com.photoshop.models.order.Order;
import com.photoshop.models.order.OrderDao;
import com.photoshop.models.order.OrderEnum;
import com.photoshop.models.photo.Photo;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.product.Product;
import com.photoshop.models.product.ProductDao;
import com.photoshop.models.school.School;
import com.photoshop.models.school.SchoolDao;
import com.photoshop.models.schoolClass.SchoolClass;
import com.photoshop.models.schoolClass.SchoolClassDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wilhelmus Theodorus Maria de Kok & Jos Raedts
 */
public class ModelTests {
    
    Student student;
    Photographer photographer;
    Admin admin;
    School school;
    SchoolClass schoolClass;
    Photo photo;
    Product product;
    Order order;
    Cartproduct cartproduct;
    
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
        PhotoDao photoDao = new PhotoDao();
        ProductDao productDao = new ProductDao();
        OrderDao orderDao = new OrderDao();
        CartproductDao cartproductdao = new CartproductDao();
        
        this.student = studentDao.getById(2);
        this.photographer = photographerDao.getById(2);
        this.admin = adminDao.getById(1);
        this.school = schoolDao.getById(1);
        this.schoolClass = schoolClassDao.getById(1);
        this.photo = photoDao.getById(1);
        this.product = productDao.getById(1);
        this.order = orderDao.getById(12);
        this.cartproduct = cartproductdao.getById(4);
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
    
    @Test
    public void testResize()
    {
        ImageManager IM = new ImageManager();
        Image testpicture = Toolkit.getDefaultToolkit().createImage("C:\\Users\\pc\\Pictures\\doge.jpg");
        System.out.println(testpicture.getHeight(null));
        System.out.println(testpicture.getWidth(null));
        BufferedImage bufftestpicture = IM.resize(testpicture, 100, 100);
        System.out.println(bufftestpicture.getHeight());
        System.out.println(bufftestpicture.getWidth());
    }
    
    @Test
    public void testPhoto()
    {
        assertTrue("Photo should be active", photo.getActive());
        assertEquals("Photo ID should be 1", 1, photo.getId());
        assertEquals("Photo height must be 480", 480, photo.getHeight());
        assertEquals("Photo width must be 640", 640, photo.getWidth());
        assertEquals("ThumbnailURL should be thumb-1429614285504-schoolclass-1-1.jpg", "thumb-1429614285504-schoolclass-1-1.jpg", photo.getThumbnailURL());
        assertEquals("LowResURL should be lowres-1429614285504-schoolclass-1-1.jpg", "lowres-1429614285504-schoolclass-1-1.jpg", photo.getLowResURL());
        assertEquals("HighResURL should be 1429614285504-schoolclass-1-1.jpg", "1429614285504-schoolclass-1-1.jpg", photo.getHighResURL());
        assertEquals("photographerid should be 4", 4, photo.getPhotographerID());
        
        photo.setId(25);
        photo.setHeight(25);
        photo.setWidth(256);
        photo.setThumbnailURL("testphoto");
        photo.setLowResURL("1235AC");
        photo.setHighResURL("56789");
        photo.setActive(false);
        photo.setPhotographerID(12);
        
        assertFalse("Photo should be inactive", photo.getActive());
        assertEquals("Photo ID should be 25", 25, photo.getId());
        assertEquals("Photo height must be 25", 25, photo.getHeight());
        assertEquals("Photo width must be 256", 256, photo.getWidth());
        assertEquals("ThumbnailURL should be testphoto", "testphoto", photo.getThumbnailURL());
        assertEquals("LowResURL should be 1235AC", "1235AC", photo.getLowResURL());
        assertEquals("HighResURL should be 56789", "56789", photo.getHighResURL());
        assertEquals("photographerid should be 12", 12, photo.getPhotographerID());
                     
        photo.setId(1);
        photo.setHeight(480);
        photo.setWidth(640);
        photo.setThumbnailURL("thumb-1429614285504-schoolclass-1-1.jpg");
        photo.setLowResURL("lowres-1429614285504-schoolclass-1-1.jpg");
        photo.setHighResURL("1429614285504-schoolclass-1-1.jpg");
        photo.setActive(true);
        photo.setPhotographerID(4);
        
        assertTrue("Photo should be active", photo.getActive());
        assertEquals("Photo ID should be 1", 1, photo.getId());
        assertEquals("Photo height must be 480", 480, photo.getHeight());
        assertEquals("Photo width must be 640", 640, photo.getWidth());
        assertEquals("ThumbnailURL should be thumb-1429614285504-schoolclass-1-1.jpg", "thumb-1429614285504-schoolclass-1-1.jpg", photo.getThumbnailURL());
        assertEquals("LowResURL should be lowres-1429614285504-schoolclass-1-1.jpg", "lowres-1429614285504-schoolclass-1-1.jpg", photo.getLowResURL());
        assertEquals("HighResURL should be 1429614285504-schoolclass-1-1.jpg", "1429614285504-schoolclass-1-1.jpg", photo.getHighResURL());
        assertEquals("photographerid should be 4", 4, photo.getPhotographerID());
    }
    
    @Test
    public void testProduct(){
        assertEquals("Productid should be 1", 1, product.getId());
        assertEquals("Productname should be Foto op fotopapier klein", "Foto op fotopapier klein", product.getName());
        assertEquals("Height should be 10", 10, product.getHeight());
        assertEquals("Width should be 15", 15, product.getWidth());
        assertEquals("imageURL should be photo.png", "photo.png", product.getImageURL());
        assertTrue("product should be active", product.getActive());
        
        product.setId(2);
        product.setName("Foto");
        product.setHeight(2);
        product.setWidth(2);
        product.setImageURL("Foto'tje");
        product.setActive(false);
        product.setPrice(new Double(300.0));
        
        assertEquals("Productid should be 2", 2, product.getId());
        assertEquals("Productname should be Foto", "Foto", product.getName());
        assertEquals("Height should be 2", 2, product.getHeight());
        assertEquals("Width should be 2", 2, product.getWidth());
        assertEquals("imageURL should be Foto'tje", "Foto'tje", product.getImageURL());
        assertFalse("product should be inactive", product.getActive());
        assertEquals("price should be 300.0", new Double(300.0), product.getPrice());
        
        product.setId(1);
        product.setName("Foto op fotopapier klein");
        product.setHeight(10);
        product.setWidth(15);
        product.setImageURL("photo.png");
        product.setActive(true);
        product.setPrice(new Double(2.95));
        
        assertEquals("Productid should be 1", 1, product.getId());
        assertEquals("Productname should be Foto op fotopapier klein", "Foto op fotopapier klein", product.getName());
        assertEquals("Height should be 10", 10, product.getHeight());
        assertEquals("Width should be 15", 15, product.getWidth());
        assertEquals("imageURL should be photo.png", "photo.png", product.getImageURL());
        assertTrue("product should be active", product.getActive());
        assertEquals("price should be 2.95", new Double(2.95), product.getPrice());
    }
    
    @Test
    public void orderTest(){
        assertEquals("Orderid should be 1", 12, order.getId());
        assertEquals("Studentname should be Casper Schobers", "Casper Schobers", order.getStudent().getName());
        assertEquals("Status should be BETAALD", "2", order.getStatus().toString());
        assertEquals("Factuur should be Factuur 12", "Factuur 12", order.getFactuur());
        assertEquals("Indexkaart should be Indexkaart 12", "Indexkaart 12", order.getIndexkaart());
        
        order.setId(4);
        order.setStatus(OrderEnum.NIET_BETAALD);
        order.setShippingaddress(new Address("Henk", "Pinda", "5975SE", "Sevenum", "28343241"));
        order.setInvoiceaddress(new Address("Henk", "de Pinda", "5975SE", "Sevenum", "28343241"));
        order.setFactuur("Factuur 3");
        order.setIndexkaart("Indexkaart 3");
        
        assertEquals("Orderid should be 4", 4, order.getId());
        assertEquals("Status should be NIET_BETAALD", "1", order.getStatus().toString());
        assertEquals("Shipping address should be Pinda", "Pinda", order.getShippingaddress().getAdres());
        assertEquals("Invoice address should be de Pinda", "de Pinda", order.getInvoiceaddress().getAdres());
        assertEquals("Factuur should be Factuur 4", "Factuur 3", order.getFactuur());
        assertEquals("Indexkaart should be Indexkaart 4", "Indexkaart 3", order.getIndexkaart());
        
        order.setId(12);
        order.setStatus(OrderEnum.NIET_BETAALD);
        order.setShippingaddress(null);
        order.setInvoiceaddress(null);
        order.setFactuur("Factuur 4");
        order.setIndexkaart("Indexkaart 4");
        
        assertEquals("Orderid should be 1", 12, order.getId());
        assertEquals("Studentname should be Casper Schobers", "Casper Schobers", order.getStudent().getName());
        assertEquals("Status should be NIET_BETAALD", "1", order.getStatus().toString());
        assertEquals("Factuur should be Factuur 4", "Factuur 4", order.getFactuur());
        assertEquals("Indexkaart should be Indexkaart 4", "Indexkaart 4", order.getIndexkaart());
    }
    
    @Test
    public void testCartproduct(){
        assertEquals("cartproductid should be 4", 4, cartproduct.getId());
        assertEquals("content should be Foto op t-shirt", "Foto op t-shirt", cartproduct.getContent());
        assertEquals("amount should be 1", 1, cartproduct.getAmount());
        assertEquals("studentid should be 5", 5, cartproduct.getStudentID());
        assertEquals("photoid should be 3", 3, cartproduct.getPhotoID());
        assertEquals("productid should be 5", 5, cartproduct.getProductId());
        assertEquals("imageid should be 4", 4, cartproduct.getImageId());
        
        cartproduct.setId(60);
        cartproduct.setContent("dat pad");
        cartproduct.setAmount(25);
        cartproduct.setStudentID(25);
        cartproduct.setPhotoID(58);
        cartproduct.setProductId(25);
        cartproduct.setImageId(25);
        
        assertEquals("cartproductid should be 30", 60, cartproduct.getId());
        assertEquals("content should be dat pad", "dat pad", cartproduct.getContent());
        assertEquals("amount should be 3", 25, cartproduct.getAmount());
        assertEquals("studentid should be 4", 25, cartproduct.getStudentID());
        assertEquals("photoid should be 51", 58, cartproduct.getPhotoID());
        assertEquals("productid should be 2", 25, cartproduct.getProductId());
        assertEquals("imageid should be 1", 25, cartproduct.getImageId());
        
        cartproduct.setId(30);
        cartproduct.setContent("Foto op iPhone 5 hoesje");
        cartproduct.setAmount(3);
        cartproduct.setStudentID(4);
        cartproduct.setPhotoID(51);
        cartproduct.setProductId(2);
        cartproduct.setImageId(1);
        
        assertEquals("cartproductid should be 30", 30, cartproduct.getId());
        assertEquals("content should be Foto op iPhone 5 hoesje", "Foto op iPhone 5 hoesje", cartproduct.getContent());
        assertEquals("amount should be 3", 3, cartproduct.getAmount());
        assertEquals("studentid should be 4", 4, cartproduct.getStudentID());
        assertEquals("photoid should be 51", 51, cartproduct.getPhotoID());
        assertEquals("productid should be 2", 2, cartproduct.getProductId());
        assertEquals("imageid should be 1", 1, cartproduct.getImageId());
    }
}
