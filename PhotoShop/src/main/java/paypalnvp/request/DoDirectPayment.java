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

import java.util.HashMap;
import java.util.Map;
import paypalnvp.fields.Address;
import paypalnvp.fields.CreditCard;
import paypalnvp.fields.PayerInformation;
import paypalnvp.fields.Payment;
import paypalnvp.fields.PaymentAction;
import paypalnvp.fields.Secure3D;
import paypalnvp.fields.ShipToAddress;

/**
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>.
 */
public final class DoDirectPayment implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "DoDirectPayment";

    /** name value pair request */
    private final Map<String, String> nvpRequest;

    /** name value pair response */
    private Map<String, String> nvpResponse;

    /**
     *
     * @param ipAddress     IP address of the payer’s browser.
     *                      Note:
     *                      PayPal records this IP addresses as a means to
     *                      detect possible fraud. Character length and
     *                      limitations: 15 single-byte characters, including
     *                      periods, for example: 255.255.255.255.
     * @throws IllegalArgumentException
     */
    public DoDirectPayment(String ipAddress, Payment payment,
            CreditCard creditCard, PayerInformation payer, Address address)
            throws IllegalArgumentException {

        // TODO - add validation for IP address and validate it

        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();

        nvpRequest.put("METHOD", METHOD_NAME);
        nvpRequest.put("IPADDRESS", ipAddress);

        nvpRequest.putAll(new HashMap<String, String>(creditCard.getNVPRequest()));
        nvpRequest.putAll(new HashMap<String, String>(payment.getNVPRequest()));
        nvpRequest.putAll(new HashMap<String, String>(payer.getNVPRequest()));
        nvpRequest.putAll(new HashMap<String, String>(address.getNVPRequest()));
    }

    /**
     * How you want to obtain payment
     * Default: Sale
     * Note: Order is not allowed for Direct Payment.
     *
     * @param action    Authorization indicates that this payment is a basic
     *                  authorization subject to settlement with PayPal
     *                  Authorization & Capture.
     *                  Sale indicates that this is a final sale for which you
     *                  are requesting payment.
     */
    public void setPaymentAction(PaymentAction action) 
            throws IllegalArgumentException {

        if (action.equals(PaymentAction.ORDER)) {
            throw new IllegalArgumentException("Order is not allowed for "
                    + "Direct Payment");
        }
        nvpRequest.put("PAYMENTACTION", action.getValue());
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
     *
     * @param address Ship To Address Fields
     */
    public void setShipToAddress(ShipToAddress address) {
        nvpRequest.putAll(new HashMap<String, String>(address.getNVPRequest()));
    }

    /**
     *
     * @param secure3D  3D Secure Request Fields (U.K. Merchants Only)
     */
    public void setSecure3D(Secure3D secure3D) {
        nvpRequest.putAll(new HashMap<String, String>(secure3D.getNVPRequest()));
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

        StringBuffer str = new StringBuffer("instance of ");
        str.append("DoDirectPayment class with the values: ");
        str.append("nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
