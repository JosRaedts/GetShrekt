package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.cartproduct.Cartproduct;
import com.photoshop.models.student.Student;
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
                item.setAmount("1.00");
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

            Map<String, String> response = setEC.getNVPResponse();

            return "redirect: " + pp.getRedirectUrl(setEC);
        }
        return null;
    }
    
    @RequestMapping(value="/confirm", method=RequestMethod.GET)
    public String confirm(@RequestParam("token") String token)
    {
        Profile user = new BaseProfile.Builder("info_api1.photoshop.nl", "AL6B27VXTC3LHTLP").signature("AiPC9BjkCyDFQXbSkoZcgqH3hpacAu8Jct0bClyKsrPt4W5HFgqgaI6U").build();
        PayPal pp = new PayPal(user, PayPal.Environment.SANDBOX);
        GetExpressCheckoutDetails ecd = new GetExpressCheckoutDetails(token);
        pp.setResponse(ecd);
        System.out.println(ecd.getNVPRequest());
        System.out.println(ecd.getNVPResponse());
        return "";
    }
}
