package com.photoshop.controllers;

import com.photoshop.misc.Factuurgenerator;
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
import com.photoshop.models.product.Product;
import com.photoshop.models.product.ProductDao;
import com.photoshop.models.student.Student;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Bram on 25-5-2015.
 */

@RequestMapping("/payment")
@Controller
public class PaymentController extends AbstractController{

    @RequestMapping(method = RequestMethod.GET)
    public String pay()
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
                item.setAmount(Double.toString(cp.getPrice()));
                item.setDescription(cp.getContent());
                items[i] = item;
                i++;
            }

            System.out.println(items);
        /* create payment (now you can create payment from the items) */
            Payment payment = new Payment(items);
            payment.setCurrency(Currency.EUR);

        /* create set express checkout - the first paypal request */
            SetExpressCheckout setEC = new SetExpressCheckout(payment, "http://localhost:8080/PhotoShop/payment/confirm", "https://www.cancelurl.com");
            
            
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
    private Enviroment env;
    
    @RequestMapping(value="/confirm", method=RequestMethod.GET)
    public String confirm(@RequestParam("token") String token, HttpServletRequest request)
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
        order.save();
        
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
                orderrow.setImagedata_id(0);
                orderrowdao.save(orderrow); 
            }
            catch(NumberFormatException nbe)
            {
                
            }
        }
   
        Factuurgenerator factuurgenerator = new Factuurgenerator(order, env);
        
        return "";
    }
}
