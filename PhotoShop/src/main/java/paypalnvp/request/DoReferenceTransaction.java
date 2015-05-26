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

import paypalnvp.fields.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Process a payment from a buyer’s account, which is identified by a previous
 * transaction.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class DoReferenceTransaction implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "DoReferenceTransaction";

    /** name value pair request */
    private final Map<String, String> nvpRequest;

    /** name value pair response */
    private Map<String, String> nvpResponse;

    /**
     *
     * @param referenceId   A transaction ID from a previous purchase, such as
     *                      a credit card charge using the DoDirectPayment API,
     *                      or a billing agreement ID.
     * @param payment
     * @param card
     * @param payer
     * @param address
     */
    public DoReferenceTransaction(String referenceId, Payment payment,
            CreditCard card, PayerInformation payer, Address address) {

        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();

        nvpRequest.put("METHOD", METHOD_NAME);
        nvpRequest.put("REFERENCEID", referenceId);
        nvpRequest.putAll(new HashMap<String, String>(payment.getNVPRequest()));
        nvpRequest.putAll(new HashMap<String, String>(card.getNVPRequest()));
        nvpRequest.putAll(new HashMap<String, String>(payer.getNVPRequest()));
        nvpRequest.putAll(new HashMap<String, String>(address.getNVPRequest()));
    }

    /**
     * How you want to obtain payment
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
            throw new IllegalArgumentException("Order is not allowed");
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
	 * The soft descriptor is a per transaction description of the payment that
	 * is passed to the consumer's credit card statement. If a value for the
	 * soft descriptor field is provided, the full descriptor displayed on the
	 * customer's statement has the following format:
	 * &lt;PP * | PAYPAL *&gt;&lt;Merchant descriptor as set in the Payment
     * Receiving Preferences&gt;&lt;1 space&gt;&lt;soft descriptor&gt;
	 *
	 * The soft descriptor does not include the phone number, which can be
	 * toggled between the merchant's customer service number and PayPal's
	 * customer service number.
	 *
	 *
	 *
	 * @param softDescriptor 	can contain only the following characters:
	 * 							Alphanumeric characters, - (dash),
	 *							* (asterisk), . (period), {space}.
	 * 							The maximum length of the total soft descriptor
	 * 							is 22 characters. Of this, either 4 or 8
	 * 							characters are used by the PayPal prefix
	 * 							shown in the data format. Thus, the maximum
	 * 							length of the soft descriptor passed in the API
	 * 							request is:22 - len(&lt;PP * | PAYPAL *&gt;) -
	 * 							len(&lt;Descriptor set in Payment Receiving
	 * 							Preferences&gt; + 1) For example, assume the
	 * 							following conditions:
	 * 							<ul>
	 * 								<li>The PayPal prefix toggle is set to
	 * 								PAYPAL * in PayPal’s admin tools.</li>
	 * 								<li>The merchant descriptor set in the
	 * 								Payment Receiving Preferences is set to
	 * 								EBAY.</li>
	 * 								<li>The soft descriptor is passed in as
	 * 								JanesFlowerGifts LLC.</li>
	 * 							</ul>
	 * 							The resulting descriptor string on the credit
	 * 							card would be: PAYPAL *EBAY JanesFlow
	 * @throws IllegalArgumentException
	 */
	public void setSoftDescriptor(String softDescriptor)
			throws IllegalArgumentException {

        nvpRequest.put("SOFTDESCRIPTOR", softDescriptor);
	}

    /**
     *
     * @param address Ship To Address Fields
     */
    public void setShipToAddress(ShipToAddress address) {
        nvpRequest.putAll(new HashMap<String, String>(address.getNVPRequest()));
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
        str.append("DoReferenceTransaction class with the values: ");
        str.append("nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
