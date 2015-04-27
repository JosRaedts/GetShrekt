/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import com.photoshop.controllers.ProductController;
import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.product.Product;
import com.photoshop.models.product.ProductDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bart
 */
public class ProductPrijs {

    Photographer photographer;
    List<Product> photos;

    public ProductPrijs() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        PhotographerDao photographerDao = new PhotographerDao();
        ProductDao productDao = new ProductDao();
        photos = new ArrayList();
        this.photographer = photographerDao.getById(2);
        this.photos = productDao.getPriceList(photographer.getId());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void SetupTest() {
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
        
        assertNotNull(null, photos);
    }

    @Test
    public void ProductSetpriceTestPositief() {
        //Set price Foto op fotopapier klein:
        photos.get(0).setPrice(6.0);
        //Foto op mok:
        photos.get(1).setPrice(7.0);
        //Foto op canvas:
        photos.get(2).setPrice(10.0);
        //Foto op t-shirt:
        photos.get(3).setPrice(2.0);
        //Foto op muismat:
        photos.get(4).setPrice(5.0);
        //Foto op fotopapier groot:
        photos.get(5).setPrice(8.0);
        //Foto op iPhone 5 hoesje:
        photos.get(6).setPrice(10.0);
        //Foto op schoudertas:
        photos.get(7).setPrice(25.0);
        
        for (Product temp : photos) {
            if (temp.getName().equals("Foto op fotopapier klein")) {
                assertEquals("Prijs Foto op fotopapier klein is fout", (Double) 6.0, photos.get(0).getPrice());
            }
            if (temp.getName().equals("Foto op mok")) {
                assertEquals("Prijs Foto op mok is fout", (Double) 7.0, photos.get(1).getPrice());
            }
            if (temp.getName().equals("Foto op canvas")) {
                assertEquals("Prijs Foto op canvas is fout", (Double) 10.0, photos.get(2).getPrice());
            }
            if (temp.getName().equals("Foto op t-shirt")) {
                assertEquals("Prijs Foto op t-shirt is fout", (Double) 2.0, photos.get(3).getPrice());
            }
            if (temp.getName().equals("Foto op muismat")) {
                assertEquals("Prijs Foto op muismat is fout", (Double) 5.0, photos.get(4).getPrice());
            }
            if (temp.getName().equals("Foto op fotopapier groot")) {
                assertEquals("Prijs Foto op fotopapier groot is fout", (Double) 8.0, photos.get(5).getPrice());
            }
            if (temp.getName().equals("Foto op iPhone 5 hoesje")) {
                assertEquals("Prijs Foto op iPhone 5 hoesje is fout", (Double) 10.0, photos.get(6).getPrice());
            }
            if (temp.getName().equals("Foto op schoudertas")) {
                assertEquals("Prijs Foto op schoudertas is fout", (Double) 25.0, photos.get(7).getPrice());
            }
        }
        
    }
    
    @Test
    public void ProductSetpriceTestNegatief() {
        //Set price Foto op fotopapier klein:
        photos.get(0).setPrice(-6.0);
        //Foto op mok:
        photos.get(1).setPrice(-7.0);
        //Foto op canvas:
        photos.get(2).setPrice(-10.0);
        //Foto op t-shirt:
        photos.get(3).setPrice(-2.0);
        //Foto op muismat:
        photos.get(4).setPrice(-5.0);
        //Foto op fotopapier groot:
        photos.get(5).setPrice(-8.0);
        //Foto op iPhone 5 hoesje:
        photos.get(6).setPrice(-10.0);
        //Foto op schoudertas:
        photos.get(7).setPrice(-25.0);
        
        for (Product temp : photos) {
            if (temp.getName().equals("Foto op fotopapier klein")) {
                assertEquals("Prijs Foto op fotopapier klein is fout", (Double) (6.0), photos.get(0).getPrice());
            }
            if (temp.getName().equals("Foto op mok")) {
                assertEquals("Prijs Foto op mok is fout", (Double) (7.0), photos.get(1).getPrice());
            }
            if (temp.getName().equals("Foto op canvas")) {
                assertEquals("Prijs Foto op canvas is fout", (Double) (10.0), photos.get(2).getPrice());
            }
            if (temp.getName().equals("Foto op t-shirt")) {
                assertEquals("Prijs Foto op t-shirt is fout", (Double) (2.0), photos.get(3).getPrice());
            }
            if (temp.getName().equals("Foto op muismat")) {
                assertEquals("Prijs Foto op muismat is fout", (Double) (5.0), photos.get(4).getPrice());
            }
            if (temp.getName().equals("Foto op fotopapier groot")) {
                assertEquals("Prijs Foto op fotopapier groot is fout", (Double) (8.0), photos.get(5).getPrice());
            }
            if (temp.getName().equals("Foto op iPhone 5 hoesje")) {
                assertEquals("Prijs Foto op iPhone 5 hoesje is fout", (Double) (10.0), photos.get(6).getPrice());
            }
            if (temp.getName().equals("Foto op schoudertas")) {
                assertEquals("Prijs Foto op schoudertas is fout", (Double) (25.0), photos.get(7).getPrice());
            }
        }
    }
}

