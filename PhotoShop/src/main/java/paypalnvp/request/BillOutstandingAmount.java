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
import paypalnvp.util.Validator;

/**
 * BillOutstandingAmount Request Message
 *
 * Bill the buyer for the outstanding balance associated with a recurring
 * payments profile.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class BillOutstandingAmount implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "BillOutstandingAmount";

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** map that holds name value pair response values */
    private Map<String, String> nvpResponse;

    /**
     * Recurring payments profile ID returned in the 
     * CreateRecurringPaymentsProfile  response. Character length and 
     * limitations: 14 single-byte alphanumeric characters. 19 character 
     * profile IDs are supported for compatibility with previous versions of the 
     * PayPal API.
     * 
     * The profile must have a status of either Active  or Suspended.
     * 
     * @param profileId
     * @throws IllegalArgumentException
     */
    public BillOutstandingAmount(String profileId)
            throws IllegalArgumentException {

        /* validation */
        if (profileId.length() != 14 || profileId.length() != 19) {
            throw new IllegalArgumentException("profileId can be 14 or 19 " +
                    "characters long.");
        }

        /* instance variables */
        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();
        nvpRequest.put("METHOD", METHOD_NAME);

        nvpRequest.put("PROFILEID", profileId);
    }

    /**
     * The amount to bill. The amount must be less than or equal to the current 
     * outstanding balance of the profile. If no value is specified, PayPal 
     * will attempt to bill the entire outstanding balance amount.
     * 
     * @param amount    Set this field to 0 if the transaction does not include
     *                  a one-time purchase; for example, when you set up a
     *                  billing agreement for a recurring payment that is not
     *                  immediately charged.
     *                  Limitations: Must not exceed $10,000 USD in any
     *                  currency. No currency symbol. Must have two decimal
     *                  places, decimal separator must be a period (.), and no
     *                  thousands separator.
     * @throws IllegalArgumentException
     */
    public void setAmount(String amount) throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount +
                    " is not valid. Amount has to have exactly two decimal "
                    + "places seaprated by \".\" - example: \"50.00\"");
        }

        /* values for this request */
        nvpRequest.put("AMT", amount);
    }

    /**
     * The reason for the non-scheduled payment. For profiles created using 
     * Express Checkout, this message will be included in the email 
     * notification to the buyer for the non-scheduled payment transaction, and 
     * can also be seen by both you and the buyer on the Status History page of 
     * the PayPal account. 
     * 
     * @param note
     */
    public void setNote(String note) {

        nvpRequest.put("NOTE", note);
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
        str.append("BillOutstandingAmount class with the vlues: nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
