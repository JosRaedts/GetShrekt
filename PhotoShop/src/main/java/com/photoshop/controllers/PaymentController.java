package com.photoshop.controllers;

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

        PaymentItem item2 = new PaymentItem();
        item2.setQuantity(1);
        item2.setAmount("30.00");
        item2.setDescription("teest2");



        PaymentItem[] items = {item1, item2};

        /* create payment (now you can create payment from the items) */
        Payment payment = new Payment(items);
        payment.setCurrency(Currency.GBP);
        System.out.println(payment.getNVPRequest());

        /* create set express checkout - the first paypal request */
        SetExpressCheckout setEC = new SetExpressCheckout(payment, "https://www.returnurl.com", "https://www.cancelurl.com");
        System.out.println(setEC.getNVPRequest());
        System.out.println(setEC.getNVPResponse());
        /* send request and set response */
        pp.setResponse(setEC);
        System.out.println(pp.getRedirectUrl(setEC));

        Map<String, String> response = setEC.getNVPResponse();

        System.out.println(response);

        return "";
    }
}
