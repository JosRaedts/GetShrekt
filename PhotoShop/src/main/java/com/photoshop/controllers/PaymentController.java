package com.photoshop.controllers;

import com.photoshop.misc.Factuurgenerator;
import com.photoshop.misc.Indexkaartgenerator;
import com.photoshop.models.UserType;
import com.photoshop.models.address.Address;
import com.photoshop.models.cartproduct.Cartproduct;
import com.photoshop.models.cartproduct.CartproductDao;
import com.photoshop.models.order.Order;
import com.photoshop.models.order.OrderEnum;
import com.photoshop.models.orderrow.OrderRow;
import com.photoshop.models.orderrow.OrderRowDao;
import com.photoshop.models.photo.Photo;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import paypalnvp.core.PayPal;
import paypalnvp.fields.Currency;
import paypalnvp.fields.Payment;
import paypalnvp.fields.PaymentItem;
import paypalnvp.profile.BaseProfile;
import paypalnvp.profile.Profile;
import paypalnvp.request.GetExpressCheckoutDetails;
import paypalnvp.request.SetExpressCheckout;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Bram on 25-5-2015.
 */

@RequestMapping("/payment")
@Controller
public class PaymentController extends AbstractController{

    @RequestMapping(method = RequestMethod.GET)
    public String pay(HttpServletRequest request)
    {
        if (this.authenticate(UserType.STUDENT)) {
            Student student = (Student) this.getUser();

            Profile user = new BaseProfile.Builder("info_api1.photoshop.nl", "AL6B27VXTC3LHTLP").signature("AiPC9BjkCyDFQXbSkoZcgqH3hpacAu8Jct0bClyKsrPt4W5HFgqgaI6U").build();
            PayPal pp = new PayPal(user, PayPal.Environment.SANDBOX);
        /* create items (items from a shopping basket) */

            List<Cartproduct> cartproducts = student.getCartProducts();

            System.out.println(cartproducts.size()+"pizza");
            PaymentItem[] items = new PaymentItem[cartproducts.size()];
            int i = 0;
            for(Cartproduct cp : cartproducts)
            {
                System.out.println("test");
                PaymentItem item = new PaymentItem();
                item.setQuantity(cp.getAmount());
                item.setItemNumber(String.valueOf(cp.getId()));
                DecimalFormat df = new DecimalFormat("##.##");
                float price = (float) cp.getPrice();
                item.setAmount(String.format("%.2f", price).replace(",", "."));
                item.setDescription(cp.getContent());
                items[i] = item;
                i++;
            }

            System.out.println(items);
        /* create payment (now you can create payment from the items) */
            Payment payment = new Payment(items);
            payment.setCurrency(Currency.EUR);

        /* create set express checkout - the first paypal request */
            SetExpressCheckout setEC = new SetExpressCheckout(payment, "http://localhost:8080"+request.getContextPath()+"/payment/confirm", "https://www.cancelurl.com");
            
            
            /* send request and set response */
            pp.setResponse(setEC);
            System.out.println(setEC.getNVPRequest());
            System.out.println(setEC.getNVPResponse());
            
            Map<String, String> response = setEC.getNVPResponse();

            return "redirect: " + pp.getRedirectUrl(setEC);
        }
        return null;
    }
    
    @Autowired
    private CartproductDao cartproductDao;
    @Autowired
    private PhotoDao photodao;
    @Autowired
    private OrderRowDao orderrowdao;
    @Autowired
    private Environment env;
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value="/confirm", method=RequestMethod.GET)
    public String confirm(@RequestParam("token") String token, HttpServletRequest request, Locale locale)
    {
        Profile user = new BaseProfile.Builder("info_api1.photoshop.nl", "AL6B27VXTC3LHTLP").signature("AiPC9BjkCyDFQXbSkoZcgqH3hpacAu8Jct0bClyKsrPt4W5HFgqgaI6U").build();
        PayPal pp = new PayPal(user, PayPal.Environment.SANDBOX);
        GetExpressCheckoutDetails ecd = new GetExpressCheckoutDetails(token);
        pp.setResponse(ecd);
        System.out.println(ecd.getNVPRequest());
        System.out.println();
        
        Order order = new Order();
        order.setDatum(new Timestamp(Calendar.getInstance().getTime().getTime()));
        order.setStatus(OrderEnum.BETAALD);
        order.setStudent((Student) this.getUser());
        order.setInvoiceaddress((Address) request.getSession().getAttribute("invoiceaddress"));
        order.setShippingaddress((Address) request.getSession().getAttribute("shippingaddress"));
        order.setFactuur("");
        order.setIndexkaart("");
        order.save();

        String orderdirstring = env.getProperty("uploadDir") + "orders/" + order.getId();
        File orderdir = new File(orderdirstring);
        if (!orderdir.exists()) {
            orderdir.mkdir();
        }
        
        List<OrderRow> orderrows = new ArrayList();
        for(int i = 0; i < 100; i++)
        {
            Map<String, String> values = ecd.getNVPResponse();
            try
            {
                int id = Integer.parseInt(values.get("L_NUMBER"+i));
                int amount = Integer.parseInt(values.get("L_QTY"+i));
                Cartproduct cp = cartproductDao.getById(id);
                OrderRow orderrow = new OrderRow();
                orderrow.setAantal(amount);
                orderrow.setProductprice(cp.getPrice());
                System.out.println(cp.getPhotoID());
                Photo photo = photodao.getById(cp.getPhotoID());
                orderrow.setPhotographer_id(photo.getPhotographerID());
                orderrow.setProduct_id(cp.getProductId());
                orderrow.setPhoto_id(cp.getPhotoID());
                orderrow.setOrder_id(order.getId());
                orderrow.setImagedata_id(cp.getImageId());;

                //Files.move(, , StandardCopyOption.REPLACE_EXISTING);
                orderrowdao.save(orderrow);

                String orderrowdirstring = orderdirstring+"/"+orderrow.getId();
                File orderrowdir = new File(orderrowdirstring);
                if (!orderrowdir.exists()) {
                    orderrowdir.mkdir();
                }

                String cartdir = env.getProperty("uploadDir") + "cart/" + cp.getId();
                //Files.move(Paths.get(cartdir+"/*"), Paths.get(orderrowdirstring+"/*"));

                Path sourceDir = Paths.get(cartdir);
                Path destinationDir = Paths.get(orderrowdirstring);

                try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceDir)) {
                    for (Path path : directoryStream) {
                        System.out.println("copying " + path.toString());
                        Path d2 = destinationDir.resolve(path.getFileName());
                        System.out.println("destination File=" + d2);
                        Files.move(path, d2, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
            catch(NumberFormatException nbe)
            {
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   
        Factuurgenerator factuurgenerator = new Factuurgenerator(order, env, messageSource, locale);
        order.setFactuur(factuurgenerator.getFilename());
        Indexkaartgenerator indexkaartgenerator = new Indexkaartgenerator(order, env, photodao, messageSource, locale);
        order.setIndexkaart(indexkaartgenerator.getFilename());
        order.save();
        
        Student student = (Student)this.getUser();
        cartproductDao.clearCart(student.getId());
        
        return "redirect:../order/success";
    }
}
