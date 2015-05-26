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

import paypalnvp.util.Validator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Payment Details Type Fields. For simple paymets use constructor with amount
 * field. If you want to set tax, or more options, use Constructor that takes
 * PaymentItem array.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class Payment implements RequestFields {

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** items that belong to this payment, empty if no items are added */
    private List<Map<String, String>> items;

    /** ebay items that belong to this payment, empty if no items are added */
    private List<Map<String, String>> ebayItems;

    /* same for all consturctors */
    { 
		nvpRequest	= new HashMap<String, String>();
        items		= new LinkedList<Map<String, String>>();
        ebayItems	= new LinkedList<Map<String, String>>();
	}

    /**
	 * You are adviced to use Payment(PaymentItem[] itmes) contructor, where 
	 * you can specify all items individually, add individual descriptions, 
	 * recurring payments etc.
     *
     * @param amount    Limitations: Must not exceed $10,000 USD in any
     *                  currency. No currency symbol. Must have two decimal
     *                  places, decimal separator must be a period (.), and no
     *                  thousands separator.
     * @throws IllegalArgumentException
     */
    public Payment(String amount) throws IllegalArgumentException {


        /* values for this request */
        nvpRequest.put("AMT", amount);
    }

    /**
     * Create Payment from supplied items. Amount is calculated automatically.
     * 
     * @param items
     * @throws IllegalArgumentException
     */
    public Payment(PaymentItem[] items) throws IllegalArgumentException {

        /* check items */
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("You have to supply items.");
        }

        /* iterate supplied array */
        int x = 0;  // this is only for exception message
        for (PaymentItem item : items) {
            /* item cannot be null */
            if (item == null) {
                throw new IllegalArgumentException("Itme at index " + x
                        + " is not set.");
            }
            this.items.add(new HashMap<String, String>(item.getNVPRequest()));
            x++;
        }
    }

    /**
     * Create Payment from supplied ebay items. Amount has to be set, because 
     * ebay items do not have amount field.
     * 
     * Set amount to 0 if the transaction does not include a one-time
     * purchase; for example, when you set up a billing agreement for a
     * recurring payment that is not immediately charged.
     *
     * @param amount    Set this field to 0 if the transaction does not include
     *                  a one-time purchase; for example, when you set up a
     *                  billing agreement for a recurring payment that is not
     *                  immediately charged.
     *                  Limitations: Must not exceed $10,000 USD in any
     *                  currency. No currency symbol. Must have two decimal
     *                  places, decimal separator must be a period (.), and no
     *                  thousands separator.
     * 
     * @param amount
     * @param items
     * @throws IllegalArgumentException
     */
    public Payment(String amount, EbayPaymentItem[] items)
            throws IllegalArgumentException {

        /* set amount */
        this(amount);

        /* check items */
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("You have to supply items.");
        }

        /* iterate supplied array */
        int x = 0;  // this is only for exception message
        for (EbayPaymentItem item : items) {
            /* item cannot be null */
            if (item == null) {
                throw new IllegalArgumentException("Itme at index " + x
                        + " is not set.");
            }
            this.ebayItems.add(
                    new HashMap<String, String>(item.getNVPRequest()));
            x++;
        }
    }

    /**
     * A three-character currency code. Default: USD 
     * 
     * @param currency
     */
    public void setCurrency(Currency currency) {
        nvpRequest.put("CURRENCYCODE", currency.toString());
    }

    /** 
     * Total shipping costs for this order. Note: Character length and 
     * limitations: Must not exceed $10,000 USD in any currency. No currency 
     * symbol. Regardless of currency, decimal separator must be a period (.) 
     * Equivalent to nine characters maximum for USD.
     *
     * @param amount
     * @throws IllegalArgumentException
     */
    public void setShippingAmount(String amount)
            throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount +
                    " is not valid. Amount has to have exactly two decimal "
                    + "places seaprated by \".\" - example: \"50.00\"");
        }
        nvpRequest.put("SHIPPINGAMT", amount);
    }

    /**
     * Total shipping insurance costs for this order. The value must be a 
     * non-negative currency amount or null if insurance options are offered. 
     * Note: Character length and limitations: Must not exceed $10,000 USD in 
     * any currency. No currency symbol. Regardless of currency, decimal 
     * separator must be a period (.). Equivalent to nine characters maximum 
     * for USD.
     * 
     * @param amount
     * @throws IllegalArgumentException
     */
    public void setInsuranceAmount(String amount) 
            throws IllegalArgumentException {

        if (amount == null) {
            nvpRequest.put("INSURANCEAMT", "null");
            return;
        }

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount +
                    " is not valid. Amount has to have exactly two decimal "
                    + "places seaprated by \".\" - example: \"50.00\"");
        }
        nvpRequest.put("INSURANCEAMT", amount);
    }

    /**
     * Total shipping insurance costs for this order. The value must be a
     * non-negative currency amount or null if insurance options are offered.
     * Note: Character length and limitations: Must not exceed $10,000 USD in
     * any currency. No currency symbol. Regardless of currency, decimal
     * separator must be a period (.). Equivalent to nine characters maximum
     * for USD.
     *
     * Insurance option displays drop-down on the PayPal Review page.
     *
     * @param amount
     * @param insuranceOption   If true, the Insurance drop-down on the PayPal
     *                          Review page displays the string ‘Yes’ and the
     *                          insurance amount.
     * @throws IllegalArgumentException
     */
    public void setInsuranceAmount(String amount, boolean insuranceOption)
            throws IllegalArgumentException {

        /* amount */
        setInsuranceAmount(amount);
        /* option */
        if (insuranceOption) {
            nvpRequest.put("INSURANCEOPTIONOFFERED", "true");
        } else {
            nvpRequest.put("INSURANCEOPTIONOFFERED", "false");
        }
    }

    /**
     * Shipping discount for this order, specified as a negative number. Note:
     * Character length and limitations: Must not exceed $10,000 USD in any
     * currency. No currency symbol. Regardless of currency, decimal separator
     * must be a period (.). Equivalent to nine characters maximum for USD.
     * 
     * @param discount
     * @throws IllegalArgumentException
     */
    public void setShippingDiscount(String discount) 
            throws IllegalArgumentException {

        /* amount is number with exactly two decimal places */
        if (!Validator.isValidAmount(discount)) {
            throw new IllegalArgumentException("Amount " + discount +
                    " is not valid. Amount has to have exactly two decimal "
                    + "places seaprated by \".\" - example: \"50.00\"");
        }
        nvpRequest.put("SHIPPINGDISCOUNT", discount);
    }

    /**
     * Total handling costs for this order. Note: Character length and
     * limitations: Must not exceed $10,000 USD in any currency. No currency
     * symbol. Regardless of currency, decimal separator must be a period (.).
     * Equivalent to nine characters maximum for USD.
     *
     * @param amount
     * @throws IllegalArgumentException
     */
    public void setHandlingAmount(String amount) 
            throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount +
                    " is not valid. Amount has to have exactly two decimal "
                    + "places seaprated by \".\" - example: \"50.00\"");
        }
        nvpRequest.put("HANDLINGAMT", amount);
    }

    /**
     * Description of items the customer is purchasing. Character length and
     * limitations: 127 single-byte alphanumeric characters
     * 
     * @param description
     * @throws IllegalArgumentException
     */
    public void setDescription(String description) 
            throws IllegalArgumentException {

        if (description.length() > 127) {
            throw new IllegalArgumentException("Description cannot exceed 127 " 
                    + "characters");
        }
        nvpRequest.put("DESC", description);
    }

    /**
     * A free-form field for your own use. Character length and limitations:
     * 256 single-byte alphanumeric characters
     *
     * @param field
     * @throws IllegalArgumentException
     */
    public void setCustomField(String field) throws IllegalArgumentException {

        if (field.length() > 256) {
            throw new IllegalArgumentException("Field cannot exceed 256 "
                    + "characters");
        }
        nvpRequest.put("CUSTOM", field);
    }

    /**
     * Your own invoice or tracking number. Character length and limitations:
     * 127 single-byte alphanumeric characters
     *
     * @param invoiceNumber
     * @throws IllegalArgumentException
     */
    public void setInvoiceNumber(String invoiceNumber)
            throws IllegalArgumentException {

        if (invoiceNumber.length() > 127) {
            throw new IllegalArgumentException("Invoice number cannot exceed "
                    + "127 characters");
        }
        nvpRequest.put("INVNUM", invoiceNumber);
    }

    /**
     * An identification code for use by third-party applications to identify
     * transactions. Character length and limitations: 32 single-byte
     * alphanumeric characters
     *
     * @param source
     * @throws IllegalArgumentException
     */
    public void setButtonSource(String source) throws IllegalArgumentException {

        if (source.length() > 127) {
            throw new IllegalArgumentException("Source cannot exceed 127 "
                    + "characters");
        }
        nvpRequest.put("BUTTONSOURCE", source);
    }

    /**
     * Your URL for receiving Instant Payment Notification (IPN) about this
     * transaction. If you do not specify this value in the request, the
     * notification URL from your Merchant Profile is used, if one exists.
     * Important: The notify URL only applies to 
     * <code>DoExpressCheckoutPayment</code>. This value is ignored when set in
     * <code>SetExpressCheckout</code> or <code>GetExpressCheckoutDetails</code>
     * . Character length and limitations: 2,048 single-byte alphanumeric
     * characters
     *
     * @param url
     * @throws IllegalArgumentException
     */
    public void setNotifyUrl(String url) throws IllegalArgumentException {

        if (url.length() > 2048) {
            throw new IllegalArgumentException("Url cannot exceed 2048 "
                    + "characters");
        }
        nvpRequest.put("NOTIFYURL", url);
    }

    /**
     * Note to the seller. Character length and limitations: 255 single-byte
     * characters
     * 
     * @param note
     * @throws IllegalArgumentException
     */
    public void setNote(String note) throws IllegalArgumentException {

        if (note.length() > 255) {
            throw new IllegalArgumentException("Note cannot exceed 255 "
                    + "characters");
        }
        nvpRequest.put("NOTETEXT", note);
    }

    /**
     * Transaction identification number of the transaction that was created.
     *
     * @param transactionId
     */
    public void setTransactionId(String transactionId) {
        nvpRequest.put("TRANSACTIONID", transactionId);
    }

    /**
     * The payment method type. Specify the value 
     * <code>InstantPaymentOnly.</code>
     *
     * @param method
     */
    public void setAllowedPaymentMethod(String method) {
        nvpRequest.put("ALLOWEDPAYMENTMETHOD", method);
    }

    public Map<String, String> getNVPRequest() {

		/* hash map holding response */
		HashMap<String, String> nvp = new HashMap<String, String>(nvpRequest);

		int itemAmt = 0;
		int itemTax = 0;

        /* items */
		for (int i =0; i < items.size(); i++) {
			for (Map.Entry<String, String> entry : items.get(i).entrySet()) {

				/* KEYn VALUE */
				nvp.put(entry.getKey() + i, entry.getValue());

				/* item amount */
				if (entry.getKey().equals("L_AMT")) {
					/* remove decimal point and parse to int */
					itemAmt += Integer.parseInt(
							entry.getValue().replace(".", ""));
				}

				/* tax amount */
				if (entry.getKey().equals("L_TAXAMT")) {
					/* remove decimal point and parse to int */
					itemTax += Integer.parseInt(
							entry.getValue().replace(".", ""));
				}
			}
		}

        /* ebay items */
		for (int i =0; i < ebayItems.size(); i++) {
			for (Map.Entry<String, String> entry
					: ebayItems.get(i).entrySet()) {

				/* KEYn VALUE */
				nvp.put(entry.getKey() + i, entry.getValue());
			}
		}

		/* format to two decimal places */
		DecimalFormat currency = new DecimalFormat("#0.00");

		/* set ITEMAMT */
		if (itemAmt > 0) {
			/* convert back to two decimals*/
			nvp.put("ITEMAMT", currency.format(itemAmt / 100d));
		}

		/* set TAXAMT */
		if (itemTax > 0) {
			/* convert back to two decimals*/
			nvp.put("TAXAMT", currency.format(itemTax / 100d));
		}

		/* set AMT if not set */
		if (!nvp.containsKey("AMT")) {

			/* calculate total - tax, shipping etc. */
			int total = itemAmt + itemTax;

			if (nvp.containsKey("HANDLINGAMT")) {
				total += Integer.parseInt(nvp.get("HANDLINGAMT").replace(".", ""));
			}
			if (nvp.containsKey("SHIPPINGAMT")) {
				total += Integer.parseInt(nvp.get("SHIPPINGAMT").replace(".", ""));
			}

			/* convert bakc to two decimals */
			nvp.put("AMT", currency.format(total / 100d));
		}

		/* handling or shipping amount is set but item amount is not set */
		if ((nvp.containsKey("HANDLINGAMT") || nvp.containsKey("SHIPPINGAMT"))
				&& !nvp.containsKey("ITEMAMT")) {

			/* set the amount for itemamt - because itemamt is required */
			/* when handling amount is set */
			nvp.put("ITEMAMT", nvp.get("AMT"));
		}

		/* return nvp request */
        nvp.put("AMT", nvp.get("AMT").replace(",", "."));
        nvp.put("ITEMAMT", nvp.get("ITEMAMT").replace(",", "."));
		return nvp;
    }

    @Override
    public String toString() {
        
        return "instance of PaymentDetails class with the values: nvpRequest: " 
                + nvpRequest.toString(); 
    }
}
