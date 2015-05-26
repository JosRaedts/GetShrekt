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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import paypalnvp.fields.Address;
import paypalnvp.fields.BillingPeriodDetails;
import paypalnvp.fields.CreditCard;
import paypalnvp.fields.PayerInformation;
import paypalnvp.fields.ShipToAddress;
import paypalnvp.util.FormatFields;
import paypalnvp.util.Validator;

/**
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class UpdateRecurringPaymentsProfile implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "UpdateRecurringPaymentsProfile";

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** map that holds name value pair response values */
    private Map<String, String> nvpResponse;

    /**
     *
     * @param profileId Recurring payments profile ID returned in the
     *                  CreateRecurringPaymentsProfile response. Character
     *                  length and limitations: 14 single-byte alphanumeric
     *                  characters. 19 character profile IDs are supported for
     *                  compatibility with previous versions of the PayPal API.
     * @throws IllegalArgumentException
     */
    public UpdateRecurringPaymentsProfile(String profileId)
            throws IllegalArgumentException {

        if (profileId.length() != 14 || profileId.length() != 19) {
            throw new IllegalArgumentException("profileId has to be 14 or 19 "
                    + "characters long");
        }

        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();
        nvpRequest.put("METHOD", METHOD_NAME);
        nvpRequest.put("PROFILEID", profileId);
    }

    /**
     *
     * @param note  The reason for the update to the recurring payments
     *              profile. This message will be included in the email
     *              notification to the buyer for the recurring payments
     *              profile update. This note can also be seen by both you and
     *              the buyer on the Status History page of the PayPal account.
     */
    public void setNote(String note) {
        nvpRequest.put("NOTE", note);
    }

    /**
     *
     * @param description   Description of the recurring payment. Character
     *                      length and limitations: 127 single-byte
     *                      alphanumeric characters.
     */
    public void setDescription(String description)
            throws IllegalArgumentException {

        if (description.length() > 127) {
            throw new IllegalArgumentException("Description cannot be longer "
                    + "than 127 characters.");
        }
        nvpRequest.put("DESC", description);
    }

    /**
     *
     * @param name  Full name of the person receiving the product or service
     *              paid for by the recurring payment. If not present, the name
     *              in the buyer's PayPal account is used. Character length and
     *              limitations: 32 single-byte characters.
     */
    public void setSubscriberName(String name) {

        if (name.length() > 32) {
            throw new IllegalArgumentException("Name cannot be longer "
                    + "than 32 characters.");
        }
        nvpRequest.put("SUBSCRIBERNAME", name);
    }

    /**
     *
     * @param reference The merchant’s own unique reference or invoice number.
     *                  Character length and limitations: 127 single-byte
     *                  alphanumeric characters.
     * @throws IllegalArgumentException
     */
    public void setProfileReference(String reference)
            throws IllegalArgumentException {

        if (reference.length() > 127) {
            throw new IllegalArgumentException("Reference cannot be longer "
                    + "than 127 characters.");
        }
        nvpRequest.put("PROFILEREFERENCE", reference);
    }

    /**
     *
     * @param cycles    The number of additional billing cycles to add to this
     *                  profile.
     */
    public void setAdditionalBillingCycles(int cycles) {
        nvpRequest.put("ADDITIONALBILLINGCYCLES", Integer.toString(cycles));
    }

    /**
     *
     * @param amount    Billing amount for each cycle in the subscription
     *                  period, not including shipping and tax amounts.
     *                  Note:
     *                  For recurring payments with Express Checkout, the
     *                  payment amount can be increased by no more than 20%
     *                  every 180 days (starting when the profile is created).
     *                  Character length and limitations: Does not exceed
     *                  $10,000 USD in any currency. No currency symbol.
     *                  Regardless of currency, decimal separator is a
     *                  period (.). Equivalent to nine characters maximum
     *                  for USD.
     * @throws IllegalArgumentException
     */
    public void setAmount(String amount) throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount - " + amount + ", is "
                    + "not valid argument");
        }
        nvpRequest.put("AMT", amount);
    }

    /**
     *
     * @param amount    Shipping amount for each billing cycle during the
     *                  regular payment period.
     *                  Note:
     *                  All amounts in the request must have the same currency.
     *                  Character length and limitations: Does not exceed
     *                  $10,000 USD in any currency. No currency symbol.
     *                  Regardless of currency, decimal separator is a
     *                  period (.). Equivalent to nine characters maximum
     *                  for USD.
     * @throws IllegalArgumentException
     */
    public void setShippingAmount(String amount)
            throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount - " + amount + ", is "
                    + "not valid argument");
        }
        nvpRequest.put("SHIPPINGAMT", amount);
    }

    /**
     *
     * @param amount    Tax amount for each billing cycle during the regular
     *                  payment period.
     *                  Note:
     *                  All amounts in the request must have the same currency.
     *                  Character length and limitations: Does not exceed
     *                  $10,000 USD in any currency. No currency symbol.
     *                  Regardless of currency, decimal separator is a
     *                  period (.). Equivalent to nine characters maximum
     *                  for USD.
     * @throws IllegalArgumentException
     */
    public void setTaxAmount(String amount) throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount - " + amount + ", is "
                    + "not valid argument");
        }
        nvpRequest.put("TAXAMT", amount);
    }

    /**
     *
     * @param amount    The current past due or outstanding amount for this
     *                  profile. You can only decrease the outstanding
     *                  amount-it cannot be increased. Character length and
     *                  limitations: Does not exceed $10,000 USD in any
     *                  currency. No currency symbol. Regardless of currency,
     *                  decimal separator is a period (.). Equivalent to nine
     *                  characters maximum for USD.
     * @throws IllegalArgumentException
     */
    public void setOutstandingAmount(String amount)
            throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount - " + amount + ", is "
                    + "not valid argument");
        }
        nvpRequest.put("OUTSTANDINGAMT", amount);
    }

    /**
     *
     * @param autoBilling   This field indicates whether you would like PayPal
     *                      to automatically bill the outstanding balance
     *                      amount in the next billing cycle.
     */
    public void setAutoBillingOutstandingAmount(boolean autoBilling) {

        String value = (autoBilling) ? "AddToNextBilling" : "NoAutoBill";
        nvpRequest.put("AUTOBILLOUTAMT", value);
    }

    /**
     *
     * @param maxFailedPayments     The number of failed payments allowed
     *                              before the profile is automatically
     *                              suspended. The specified value cannot be
     *                              less than the current number of failed
     *                              payments for this profile.
     */
    public void setMaxFailedPayments(int maxFailedPayments) {
        nvpRequest.put("MAXFAILEDPAYMENTS", Integer.toString(maxFailedPayments));
    }

    /**
     *
     * @param startDate     The date when billing for this profile begins.
     *                      The profile may take up to 24 hours for activation.
     */
    public void setProfileStartDate(Date startDate) {

        String date = FormatFields.getDateTimeField(startDate);
        nvpRequest.put("PROFILESTARTDATE", date);
    }

    /**
     *
     * @param address
     */
    public void setShipToAddress(ShipToAddress address) {
		nvpRequest.putAll(new HashMap<String, String>(address.getNVPRequest()));
    }

    /**
     *
     * @param period
     */
    public void setBillingPeriodDetails(BillingPeriodDetails period) {
		nvpRequest.putAll(new HashMap<String, String>(period.getNVPRequest()));
    }

    /**
     *
     * @param card
     */
    public void setCreditCard(CreditCard card) {
		nvpRequest.putAll(new HashMap<String, String>(card.getNVPRequest()));
    }

    /**
     *
     * @param payer
     */
    public void setPayerInformation(PayerInformation payer) {
		nvpRequest.putAll(new HashMap<String, String>(payer.getNVPRequest()));
    }

    /**
     *
     * @param address
     */
    public void setAddress(Address address) {
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
        str.append("UpdateRecurringPaymentsProfile class with the values: ");
        str.append("nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
