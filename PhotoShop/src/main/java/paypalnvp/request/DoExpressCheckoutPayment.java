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

package paypalnvp.request;

import paypalnvp.fields.Address;
import paypalnvp.fields.Payment;
import paypalnvp.fields.PaymentAction;
import paypalnvp.fields.UserSelectedOptions;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>.
 */
public final class DoExpressCheckoutPayment implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "DoExpressCheckoutPayment";

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** map that holds name value pair response values */
    private Map<String, String> nvpResponse;

    /**
     * 
	 * @param payment		Should be the same as for SetExpressCheckout
     * @param token         PayPal token
     * @param paymentAction How you want to obtain payment
     * @param payerId       Unique PayPal customer account identification 
     *                      number as returned by GetExpressCheckoutDetails 
     *                      response
     * @throws IllegalArgumentException
     */
    public DoExpressCheckoutPayment(Payment payment, String token,
            PaymentAction paymentAction, String payerId)
            throws IllegalArgumentException {
        if (token.length() != 20) {
            throw new IllegalArgumentException("Invalid token argument");
        }
        if (payerId.length() != 13) {
            throw new IllegalArgumentException("Invalid payer id");
        }

        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();

        nvpRequest.put("METHOD", METHOD_NAME);
		/* insert payment values */
		HashMap<String, String> nvp =
				new HashMap<String, String>(payment.getNVPRequest());
        nvpRequest.putAll(nvp);
        nvpRequest.put("TOKEN", token);
        nvpRequest.put("PAYMENTACTION", paymentAction.getValue());
        nvpRequest.put("PAYERID", payerId);
    }

	/**
	 * Flag to indicate whether you want the results returned by Fraud 
	 * Management Filters. By default this is false. 
	 * 
	 * @param fmf	true: receive FMF details
	 *				false: do not receive FMF details (default)
	 */
	public void setReturnFMF(boolean fmf) {

		int x = (fmf) ? 1 : 0;
        nvpRequest.put("RETURNFMFDETAILS", Integer.toString(x));
	}

	/**
	 * Sets user selected options
	 * 
	 * @param userOptions
	 */
	public void setUserSelectedOptions(UserSelectedOptions userOptions) {

		HashMap<String, String> nvp = 
				new HashMap<String, String>(userOptions.getNVPRequest());
		nvpRequest.putAll(nvp);
	}

	/**
	 * Sets address fields
	 *
	 * @param address
	 */
	public void setAddress(Address address) {

		HashMap<String, String> nvp =
				new HashMap<String, String>(address.getNVPRequest());
		nvpRequest.putAll(nvp);
	}

    public Map<String, String> getNVPRequest() {
        return new HashMap(nvpRequest);
    }

    public void setNVPResponse(Map<String, String> nvpResponse) {
        this.nvpResponse = new HashMap<String, String>(nvpResponse);
    }

    public Map<String, String> getNVPResponse() {
        return new HashMap<String, String>(nvpResponse);
    }

    @Override
    public String toString() {

        StringBuffer str = new StringBuffer("instance of SetExpressCheckout ");
        str.append("class with the vlues: nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
