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

import java.io.Serializable;

/**
 * How you want to obtain payment
 *
 * You cannot set this value to Sale on SetExpressCheckout request and then
 * change this value to Authorization on the final PayPal Express Checkout API
 * DoExpressCheckoutPayment request.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>.
 */
public enum PaymentAction implements Serializable {

    /**
     * Indicates that this payment is a basic authorization subject to
     * settlement with PayPal Authorization & Capture.
     */
    AUTHORIZATION("Authorization"),

    /**
     * Indicates that this payment is is an order authorization subject to
     * settlement with PayPal Authorization & Capture.
     */
    ORDER("Order"),

    /**
     * Indicates that this is a final sale for which you are requesting payment.
     */
    SALE("Sale");

    private String value;

    private PaymentAction(String value) {
        this.value = value;
    }

    /**
     *
     * @return  string value for nvp request
     */
    public String getValue() {
        return value;
    }
}
