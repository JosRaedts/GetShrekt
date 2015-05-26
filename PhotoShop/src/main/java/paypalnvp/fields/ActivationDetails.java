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
import paypalnvp.util.Validator;

/**
 * Activation Details Fields
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class ActivationDetails implements RequestFields {

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    public ActivationDetails() {
        nvpRequest = new HashMap<String, String>();
    }

    /**
     * Initial non-recurring payment amount due immediately upon profile
     * creation. Use an initial amount for enrolment or set-up fees.
     * Note:
     * All amounts included in the request must have the same currency.
     * Character length and limitations: Does not exceed $10,000 USD in any
     * currency. No currency symbol. Regardless of currency, decimal separator
     * is a period (.). Equivalent to nine characters maximum for USD.
     *
     * @param amount
     * @throws IllegalArgumentException
     */
    public void setInitialAmount(String amount)
            throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount has to have exactly two "
					+ "decimal places seaprated by \".\" - example: \"50.00\"");
        }

        nvpRequest.put("INITAMT", amount);
    }

    /**
     * By default, PayPal will suspend the pending profile in the event that
     * the initial payment amount fails. You can override this default
     * behaviour by setting this field to to true 'continueOnFailure', which
     * indicates that if the initial payment amount fails, PayPal should add
     * the failed payment amount to the outstanding balance for this recurring
     * payment profile. When the continueOnFailure is set to true, a success
     * code will be returned to the merchant in the
     * CreateRecurringPaymentsProfile response and the recurring payments
     * profile will be activated for scheduled billing immediately. You should
     * check your IPN messages or PayPal account for updates of the payment
     * status. If this field is not set to true, PayPal will create the
     * recurring payment profile, but will place it into a pending status until
     * the initial payment is completed. If the initial payment clears, PayPal
     * will notify you by IPN that the pending profile has been activated. If
     * the payment fails, PayPal will notify you by IPN that the pending
     * profile has been cancelled.
     *
     * @param action
     */
    public void setFailedInitialAmountAction(boolean continueOnFailure) {

        String value = (continueOnFailure) ? "ContinueOnFailure" : "CancelOnFailure";
        nvpRequest.put("FAILEDINITAMTACTION", value);
    }

    public Map<String, String> getNVPRequest() {
        return new HashMap<String, String>(nvpRequest);
    }

    @Override
    public String toString() {

        return "instance of ActivationDetails class with the values: "
                + "nvpRequest: " + nvpRequest.toString();
    }
}
