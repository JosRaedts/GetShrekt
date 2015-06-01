package com.photoshop.controllers;

import com.photoshop.models.address.Address;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import paypalnvp.core.PayPal;
import paypalnvp.fields.Currency;
import paypalnvp.fields.Payment;
import paypalnvp.fields.PaymentItem;
import paypalnvp.profile.BaseProfile;
import paypalnvp.profile.Profile;
import paypalnvp.request.SetExpressCheckout;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import paypalnvp.request.GetExpressCheckoutDetails;

/**
 * Created by Bram on 25-5-2015.
 */

@RequestMapping("/payment")
@Controller
public class PaymentController {

    @RequestMapping(method = RequestMethod.GET)
    public String pay()
    {
        Profile user = new BaseProfile.Builder("info_api1.photoshop.nl", "AL6B27VXTC3LHTLP").signature("AiPC9BjkCyDFQXbSkoZcgqH3hpacAu8Jct0bClyKsrPt4W5HFgqgaI6U").build();
        PayPal pp = new PayPal(user, PayPal.Environment.SANDBOX);
        /* create items (items from a shopping basket) */
        PaymentItem item1 = new PaymentItem();
        item1.setQuantity(1);
        item1.setAmount("20.00");
        item1.setDescription("teest");
        //item1.setItemNumber(null);

        PaymentItem item2 = new PaymentItem();
        item2.setQuantity(1);
        item2.setAmount("30.00");
        item2.setDescription("teest2");
        //item2.setItemNumber(null);

        PaymentItem[] items = {item1, item2};

        /* create payment (now you can create payment from the items) */
        Payment payment = new Payment(items);
        payment.setCurrency(Currency.EUR);

        /* create set express checkout - the first paypal request */
        SetExpressCheckout setEC = new SetExpressCheckout(payment, "http://localhost:8080/PhotoShop/payment/confirm", "https://www.cancelurl.com");
        /* send request and set response */
        pp.setResponse(setEC);

        Map<String, String> response = setEC.getNVPResponse();

        return "redirect: "+pp.getRedirectUrl(setEC);
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
