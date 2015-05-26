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

package paypalnvp.fields;

import java.util.HashMap;
import java.util.Map;

/**
 * Schedule Details Fields
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class ScheduleDetails implements RequestFields {

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /**
     *
     * @param description   Description of the recurring payment.
     *                      Note:
     *                      This field must match the corresponding billing
     *                      agreement description included in the
     *                      SetExpressCheckout request.
     *                      Character length and limitations: 127 single-byte
     *                      alphanumeric characters
     * @throws IllegalArgumentException
     */
    public ScheduleDetails(String description)
            throws IllegalArgumentException {

        if (description.length() > 127) {
            throw new IllegalArgumentException("Description cannot be longer "
                    + "than 127 characters");
        }

        nvpRequest = new HashMap<String, String>();
        nvpRequest.put("DESC", description);
    }

    /**
     *
     * @param number    The number of scheduled payments that can fail before
     *                  the profile is automatically suspended. An IPN message
     *                  is sent to the merchant when the specified number of
     *                  failed payments is reached.
     */
    public void setMaxFailedPayments(int number) {
        nvpRequest.put("MAXFAILEDPAYMENTS", Integer.toString(number));
    }

    /**
     * This field indicates whether you would like PayPal to automatically bill
     * the outstanding balance amount in the next billing cycle. The
     * outstanding balance is the total amount of any previously failed
     * scheduled payments that have yet to be successfully paid.
     *
     * @param autoBill  if set to true, AddToNextBilling value is set,
     *                  otherwise NoAutoBill is used
     */
    public void setAutoBillAmount(boolean autoBill) {

        String value = (autoBill) ? "AddToNextBilling" : "NoAutoBill";
        nvpRequest.put("AUTOBILLAMT", value);
    }

    public Map<String, String> getNVPRequest() {
        return new HashMap<String, String>(nvpRequest);
    }

    @Override
    public String toString() {

        return "instance of ScheduleDetails class with the values: nvpRequest: "
                + nvpRequest.toString();
    }
}
