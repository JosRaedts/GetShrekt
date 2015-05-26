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
 * Payment Details Item Type Fields. You have to set amount for at leas one
 * item. Otherwise the payment will be rejected by paypal, because
 * order will be 0.00
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class PaymentItem implements RequestFields {

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    public PaymentItem() {
        nvpRequest = new HashMap<String, String>();
    }

    /**
     * Item name. Character length and limitations: 127 single-byte characters
     * 
     * @param name
     * @throws IllegalArgumentException
     */
    public void setName(String name) throws IllegalArgumentException {

        if (name.length() > 127) {
            throw new IllegalArgumentException("Name cannot exceed 127 "
                    + "characters");
        }
        nvpRequest.put("L_NAME", name);
    }

    /**
     * Item description. Character length and limitations: 127 single-byte
     * characters
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
        nvpRequest.put("L_DESC", description);
    }

    /**
     * Cost of item.
     * Set amount to 0 if the transaction does not include a one-time
     * purchase; for example, when you set up a billing agreement for a
     * recurring payment that is not immediately charged.
     *
     * @param amount	Character length and limitations: Must not exceed
	 * 					$10,000 USD in any currency. No currency symbol.
	 * 					Regardless of currency, decimal separator must be a
	 * 					period (.). Equivalent to nine characters maximum for
	 * 					USD.
     * @throws IllegalArgumentException
     */
    public void setAmount(String amount) throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount +
                    " is not valid. Amount has to have exactly two decimal "
                    + "places seaprated by \".\" - example: \"50.00\"");
        }
        nvpRequest.put("L_AMT", amount);
    }

    /**
     * Item number. Character length and limitations: 127 single-byte characters
     *
     * @param itemNumber
     * @throws IllegalArgumentException
     */
    public void setItemNumber(String itemNumber)
            throws IllegalArgumentException {

        if (itemNumber.length() > 127) {
            throw new IllegalArgumentException("Item number cannot exceed 127 "
                    + "characters");
        }
        nvpRequest.put("L_NUMBER", itemNumber);
    }

    /**
     * Item quantity. Character length and limitations: Any positive integer
     *
     * @param quantity
     * @throws IllegalArgumentException
     */
    public void setQuantity(int quantity) throws IllegalArgumentException {

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity has to be positive "
                    + "integer");
        }
        nvpRequest.put("L_QTY", Integer.toString(quantity));
    }

    /**
     * Item sales tax. Note: Character length and limitations: Must not exceed
     * $10,000 USD in any currency. No currency symbol. Regardless of currency,
     * decimal separator must be a period (.). Equivalent to nine characters
     * maximum for USD.
     *
     * @param amount
     * @throws IllegalArgumentException
     */
    public void setTaxAmount(String amount)
            throws IllegalArgumentException {

        if (!Validator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount " + amount +
                    " is not valid. Amount has to have exactly two decimal "
                    + "places seaprated by \".\" - example: \"50.00\"");
        }
        nvpRequest.put("L_TAXAMT", amount);
    }

    /**
     * Item weight corresponds to the weight of the item. You can pass this
     * data to the shipping carrier as is without having to make an additional
     * database query.
     *
     * @param value any positive integer
     * @param unit
     * @throws IllegalArgumentException
     */
    public void setWeight(int value, String unit)
            throws IllegalArgumentException {

        if (value < 0) {
            throw new IllegalArgumentException("Value has to be positive "
                    + "integer");
        }
        nvpRequest.put("L_ITEMWEIGHTVALUE", Integer.toString(value));
        nvpRequest.put("L_ITEMWEGHTUNIT", unit);
    }

    /**
     * Item length corresponds to the length of the item. You can pass this
     * data to the shipping carrier as is without having to make an additional
     * database query.
     *
     * @param value any positive integer
     * @param unit
     * @throws IllegalArgumentException
     */
    public void setLength(int value, String unit)
            throws IllegalArgumentException {

        if (value < 0) {
            throw new IllegalArgumentException("Value has to be positive "
                    + "integer");
        }
        nvpRequest.put("L_ITEMLENGTHVALUE", Integer.toString(value));
        nvpRequest.put("L_ITEMLENGTHUNIT", unit);
    }

    /**
     * Item width corresponds to the width of the item. You can pass this data
     * to the shipping carrier as is without having to make an additional
     * database query.
     *
     * @param value any positive integer
     * @param unit
     * @throws IllegalArgumentException
     */
    public void setWidth(int value, String unit)
            throws IllegalArgumentException {

        if (value < 0) {
            throw new IllegalArgumentException("Value has to be positive "
                    + "integer");
        }
        nvpRequest.put("L_ITEMWIDTHVALUE", Integer.toString(value));
        nvpRequest.put("L_ITEMWIDTHUNIT", unit);
    }

    /**
     * Item height corresponds to the height of the item. You can pass this
     * data to the shipping carrier as is without having to make an additional
     * database query. Character length and limitations: Any positive integer
     *
     * @param value any positive integer
     * @param unit
     * @throws IllegalArgumentException
     */
    public void setHeight(int value, String unit)
            throws IllegalArgumentException {

        if (value < 0) {
            throw new IllegalArgumentException("Value has to be positive "
                    + "integer");
        }
        nvpRequest.put("L_ITEMHEIGHTVALUE", Integer.toString(value));
        nvpRequest.put("L_ITEMHEIGHTUNIT", unit);
    }

    public Map<String, String> getNVPRequest() {
        return new HashMap<String, String>(nvpRequest);
    }

    @Override
    public String toString() {

        return "instance of PaymentDetailsItem class with the values: "
                + "nvpRequest: " + nvpRequest.toString();
    }
}
