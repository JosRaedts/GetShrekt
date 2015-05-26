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
import paypalnvp.fields.Currency;
import paypalnvp.util.Validator;

/**
 * Capture an authorized payment.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class DoCapture implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "DoCapture";

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** map that holds name value pair response values */
    private Map<String, String> nvpResponse;

	/**
	 * Returns DoCapture instance if arguments are valid, otherwise an
	 * exception is thrown.
	 *
	 * @param authorizationId	The authorization identification number of the
	 * 							payment you want to capture. This is the
	 * 							transaction id returned from
	 * 							DoExpressCheckoutPayment or DoDirectPayment.
	 * 							Character length and limits: 19 single-byte
	 * 							characters maximum.
	 * @param amount 			Amount to capture.
	 * 							Limitations: Value is a positive number which
	 * 							cannot exceed $10,000 USD in any currency. No
	 * 							currency symbol. Must have two decimal places,
	 * 							decimal separator must be a period (.).
	 * @param completeType		Complete indicates that this the last capture
	 * 							you intend to make. The value NotComplete
	 * 							indicates that you intend to make additional
	 * 							captures.
	 * 							NOTE: If Complete, any remaining amount of the
	 * 							original authorized transaction is
	 * 							automatically voided and all remaining open
	 * 							authorizations are voided.
	 * @throws IllegalArgumentException
	 */
	public DoCapture(String authorizationId, String amount, 
			boolean completeType) throws IllegalArgumentException {

        /* validation */
		if (authorizationId == null || authorizationId.length() > 19) {
            throw new IllegalArgumentException("Authorization id can be "
					+ "maximum 19 characters long.");
		}
        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount is not valid");
        }
		String complete = (completeType) ? "Complete" : "NotComplete";

        /* instance variables */
        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();
        nvpRequest.put("METHOD", METHOD_NAME);
        nvpRequest.put("AUTHORIZATIONID", authorizationId);
        nvpRequest.put("AMT", amount);
        nvpRequest.put("COMPLETETYPE", complete);
	}

	/**
	 * Sets currency code.
	 * 
	 * @param currency Default is USD.
	 */
	public void setCurrency(Currency currency) {
        nvpRequest.put("CURRENCYCODE", currency.toString());
	}

	/**
	 * Your invoice number or other identification number that is displayed to
	 * the merchant and customer in his transaction history.
	 *
	 * NOTE: This value on DoCapture will overwrite a value previously set on
	 * DoAuthorization.
	 *
	 * NOTE: The value is recorded only if the authorization you are capturing
	 * is an order authorization, not a basic authorization.
	 *
	 * @param invoiceNumber Character length and limits: 127 single-byte
	 * 						alphanumeric characters.
	 * @throws IllegalArgumentException
	 */
	public void setInvoicNumber(String invoiceNumber)
			throws IllegalArgumentException {

		if (invoiceNumber == null || invoiceNumber.length() > 127) {
			throw new IllegalArgumentException("Invoice number can be maximum "
					+ "127 characters long.");
		}
        nvpRequest.put("INVNUM", invoiceNumber);
	}

	/**
	 * An informational note about this settlement that is displayed to the
	 * payer in email and in his transaction history.
	 *
	 * @param note Character length and limits: 255 single-byte characters.
	 * @throws IllegalArgumentException
	 */
	public void setNote(String note) throws IllegalArgumentException {

		if (note == null || note.length() > 255) {
			throw new IllegalArgumentException("Note can be maximum 255 "
					+ "characters long.");
		}
        nvpRequest.put("NOTE", note);
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

        StringBuffer str = new StringBuffer("instance of DoCapture");
        str.append("class with the vlues: nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
