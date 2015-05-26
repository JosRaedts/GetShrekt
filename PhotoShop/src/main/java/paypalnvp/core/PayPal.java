/*
 *  Copyright (C) 2010 Pete Reisinger <p.reisinger@gmail.com>.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package paypalnvp.core;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import paypalnvp.request.Request;
import paypalnvp.request.SetExpressCheckout;
import paypalnvp.profile.Profile;

/**
 * Instance of this class is used for sending requests and returning responses
 * from paypal.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class PayPal implements Serializable {

    /** 
     * indicates if server for use with api signature (or api certificate if 
     * false) should be used 
     */
    private final boolean apiSignature;

    /** version */
    private static final String VERSION = "61.0";

    /** sends request and returns response */
    private final Transport transport;

    /** class holding profile details */
    private final Profile profile;

    /** environment - test, live etc. */
    private final Environment environment;

    /**
     * paypal environment - live, sandbox or beta sandbox
     */
    public enum Environment {

        /** live environment */
        LIVE(""),
        /** test environment */
        SANDBOX("sandbox."),
        /** beta test environment */
        BETA_SANDBOX("beta-sandbox.");

        /** string represnetation of the environment/part of the url */
        private final String environment;

        private Environment(String environment) {
            this.environment = environment;
        }

        /**
         * Return url where you send request, this changes according to the
         * environment set.
         *
         * @return - url string where to send request
         */
        private String getEnvironmentPartUrl() {
            return environment;
        }
    }

    /* same for all constructors */
    {transport = new HttpPost();}

    /**
     * Returns new instance of PayPal for use with api signatures.
     * 
     * @param profile
     * @param environment
     */
    public PayPal(Profile profile, Environment environment) {

        this.profile        = profile;
        this.environment    = environment;
        this.apiSignature   = true;
    }

    /**
     * Returns new instance of PayPal.
     *
     * @param profile
     * @param environment
     * @param apiSignature -    specify if you want to use server for api
     *                          signature, or api certificate
     */
    public PayPal(Profile profile, Environment environment,
            boolean apiSignature) {

        this.profile        = profile;
        this.environment    = environment;
        this.apiSignature   = apiSignature;
    }

    /**
     * Sets response from PayPal. Calls setNVPResponse on supplied request
     * argument and sets response Map from PayPal.
     *
     * @param request
     */
    public void setResponse(Request request) {

        StringBuffer nvpString  = new StringBuffer();
        /* character encoding for the nvp string */
        String encoding         = "UTF-8";

        /* create nvp string */
        try {
            /* profile part */
            for(Map.Entry<String, String> e : profile.getNVPMap().entrySet()) {
                nvpString.append(e.getKey() + "="
                        + URLEncoder.encode(e.getValue(), encoding));
                nvpString.append("&");
            }
            /* request part */
            for(Map.Entry<String, String> e : request.getNVPRequest().entrySet()) {
                nvpString.append(e.getKey() + "="
                        + URLEncoder.encode(e.getValue(), encoding));
                nvpString.append("&");
            }
            /* the rest */
            nvpString.append("VERSION=" + URLEncoder.encode(VERSION, encoding));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SetExpressCheckout.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* create end point url */
        StringBuffer endpointUrl = new StringBuffer();
        if (apiSignature) {
            endpointUrl.append("https://api-3t.");
        } else {
            endpointUrl.append("https://api." );
        }
        endpointUrl.append(environment.getEnvironmentPartUrl());
        endpointUrl.append("paypal.com/nvp");

        /* send request and save response */
        String response = null;
        try {
            response = transport.getResponse(endpointUrl.toString(),
                    nvpString.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(PayPal.class.getName()).log(Level.SEVERE, null,
                    ex);
        }

        if (response != null) {

            /* map holding response */
            Map<String, String> responseMap = new HashMap<String, String>();

            /* add response to the Map */
            try {
                String[] pairs = response.split("&");       // split nvp
                for (String pair : pairs) {
                    String[] nvp = pair.split("=");         // split key value
                    responseMap.put(nvp[0], URLDecoder.decode(nvp[1], encoding));
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SetExpressCheckout.class.getName()).log(
                        Level.SEVERE, null, ex);
            }

            /* set response */
            request.setNVPResponse(responseMap);
        }
    }

    /**
     * Returns paypal url, where profile should be redirected. If Request has
     * not been sent, or response has not been successfull, null is returned.
     * 
     * @return - url where to redirect profile
     */
    public String getRedirectUrl(Request request) {

        /* response */
        Map<String, String> response = request.getNVPResponse();

        /* nvpResponse is not set */
        if (response == null) {
            return null;
        }

        String ack      = response.get("ACK");
        String token    = response.get("TOKEN");

        /* ack is not successfull or token is not set */
        if ((ack == null || !ack.equals("Success")) ||
                (token == null || token.equals(""))) {

            return null;
        }

        /* return redirect url */
        return "https://www." + environment.getEnvironmentPartUrl()
                + "paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
                + token;
    }

    @Override
    public String toString() {

		return "instance of PayPalNVP class with values: VERSION: " + VERSION
                + ", User profile: " + profile.toString() + ", Transpor transport: "
                + transport.toString() + ", Environment environment: "
                + environment.toString();
    }
}
