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
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>.
 */
public final class DoReauthorization implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "DoReauthorization";

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** map that holds name value pair response values */
    private Map<String, String> nvpResponse;

	/**
	 *
	 * @param authorizationId	The value of a previously authorized
	 * 							transaction identification number returned by
	 * 							PayPal. Character length and limits: 19
	 * 							single-byte characters maximum.
	 * @param amount			Amount to reauthorize.
	 * 							Limitations: Value is a positive number which
	 * 							cannot exceed $10,000 USD in any currency. No
	 * 							currency symbol. Must have two decimal places,
	 * 							decimal separator must be a period (.).
	 * @throws IllegalArgumentException
	 */
	public DoReauthorization(String authorizationId, String amount)
			throws IllegalArgumentException {

		/* validate */
		if (authorizationId == null || authorizationId.length() > 19) {
            throw new IllegalArgumentException("AuthorizationId cannot be " 
					+ "longer than 19 characters");
		}
        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount is not valid. Amount "
					+ "has to have exactly two decimal "
                    + "places seaprated by \".\" - example: \"50.00\"");
        }

		/* set instance variables */
        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();

        nvpRequest.put("METHOD", METHOD_NAME);
        nvpRequest.put("AUTHORIZATIONID", authorizationId);
        nvpRequest.put("AMT", amount);
	}

	/**
	 * Currency
	 *
	 * @param currency
	 */
	public void setCurrency(Currency currency) {
        nvpRequest.put("CURRENCYCODE", currency.toString());
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

        StringBuffer str = new StringBuffer("instance of DoReauthorization ");
        str.append("class with the vlues: nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
