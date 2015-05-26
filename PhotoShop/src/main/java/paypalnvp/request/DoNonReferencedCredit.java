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
import paypalnvp.fields.Currency;
import paypalnvp.fields.PayerInformation;
import paypalnvp.fields.PayerName;
import paypalnvp.util.Validator;

/**
 * Issue a credit to a card not referenced by the original transaction.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class DoNonReferencedCredit implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "DoNonReferencedCredit";

    /** name value pair request */
    private final Map<String, String> nvpRequest;

    /** name value pair response */
    private Map<String, String> nvpResponse;

    /**
     *
     * @param amount    Total of order, including shipping, handling, and tax.
     *                  Limitations: Must not exceed $10,000 USD in any
     *                  currency. No currency symbol. Must have two decimal
     *                  places, decimal separator must be a period (.).
     *                  Amount = NetAmount + ShippingAmount + TaxAmount
     * @param card
     * @param payer
     * @param address
     * @throws IllegalArgumentException
     */
    public DoNonReferencedCredit(String amount, CreditCard card, 
            PayerInformation payer, Address address)
            throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount
                    + " is not valid");
        }

        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();

        nvpRequest.put("METHOD", METHOD_NAME);
        nvpRequest.put("AMT", amount);
        nvpRequest.putAll(new HashMap<String, String>(card.getNVPRequest()));
    }

    /**
     *
     * @param amount    Total amount of all items in this transaction.
     *                  Limitations: Must not exceed $10,000 USD in any
     *                  currency. No currency symbol. Must have two decimal
     *                  places, decimal separator must be a period (.). The
     *                  only valid currencies are AUD, CAD, EUR, GBP, JPY, and
     *                  USD.
     * @throws IllegalArgumentException
     */
    public void setNetAmount(String amount) throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount
                    + " is not valid");
        }
        nvpRequest.put("NETAMT", amount);
    }

    /**
     *
     * @param amount    Sum of tax for all items in this order.
     *                  Limitations: The value must be zero or greater and
     *                  cannot exceed $10,000 USD in any currency. No currency
     *                  symbol. Must have two decimal places, decimal separator
     *                  must be a period (.). The only valid currencies are
     *                  AUD, CAD, EUR, GBP, JPY, and USD.
     * @throws IllegalArgumentException
     */
    public void setTaxAmount(String amount) throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount
                    + " is not valid");
        }
        nvpRequest.put("TAXAMT", amount);
    }

    /**
     *
     * @param amount    Total shipping costs in this transaction.
     *                  Limitations: Value must be zero or greater and cannot
     *                  exceed $10,000 USD in any currency. No currency symbol.
     *                  Must have two decimal places, decimal separator must be
     *                  a period (.). The only valid currencies are AUD, CAD,
     *                  EUR, GBP, JPY, and USD.
     * @throws IllegalArgumentException
     */
    public void setShippingAmount(String amount) throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount
                    + " is not valid");
        }
        nvpRequest.put("SHIPPINGAMT", amount);
    }

    /**
     *
     * @param note  Field used by merchant to record why this credit was issued
     *              to a buyer. Similar to a "memo" field.
     */
    public void setNote(String note) {
        nvpRequest.put("NOTE", note);
    }

    /**
     *
     * @param currency  Currency code. Default: USD. The only valid currencies
     *                  are AUD, CAD, EUR, GBP, JPY, and USD.
     * @throws IllegalArgumentException
     */
    public void setCurrency(Currency currency) throws IllegalArgumentException {
        if (!currency.equals(Currency.AUD) && !currency.equals(Currency.CAD)
            && !currency.equals(Currency.EUR) && !currency.equals(Currency.GBP) 
            && !currency.equals(Currency.JPY) && !currency.equals(Currency.USD)) {

            throw new IllegalArgumentException("Currency can be only AUD, "
                    + "CAD, EUR, GBP, JPY, and USD, you used - "
                    + currency.toString());
        }
        nvpRequest.put("CURRENCYCODE", currency.toString());
    }

    /**
     *
     * @param payerName
     */
    public void setPayerName(PayerName payerName) {
        nvpRequest.putAll(new HashMap<String, String>(payerName.getNVPRequest()));
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
        str.append("DoNonReferencedCredit class with the values: ");
        str.append("nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
