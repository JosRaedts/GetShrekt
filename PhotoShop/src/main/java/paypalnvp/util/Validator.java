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

package paypalnvp.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates common paypal fields. The class is used directly by paypal classes
 * so you can use it for validation before setting certain values in paypal
 * requests or fields.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class Validator implements Serializable {

    /** pattern accepted by paypal */
    private static final Pattern amountPattern;

    /** email pattern for validation */
    private static final Pattern emailPattern;

    /** create instance of amountPattern only once */
    static {
        amountPattern   = Pattern.compile("^(\\d*\\.\\d{2}|0{1})$");
        emailPattern    = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)$");
    }

    /**
     * Validates string if it is amount supported by paypal. Amount has to be
     * exactly two decimal places, decimal point is ".", or 0.
	 * You can set 0 for for example Payment when you set up recurring payment.
     *
     * @param   amount string representing the amount
     * @return  true if amount is valid, false otherwise
     */
    public static boolean isValidAmount(String amount) {

		if (amount == null) {
			return false;
		}

        /* amount is zero or number with exactly two decimal places */
        Matcher matcher = amountPattern.matcher(amount);
        if (!matcher.find()) {
            return false;
        }

        return true;
    }

    /**
     * Validates email address. Does not validate length of the email, this is 
     * because it varies between paypal requests.
     * 
     * @param email
     * @return true if email is valid, otherwise false
     */
    public static boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }

        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        }

        return true;
    }

    /**
     * Uses Luhn algorithm to validate numbers. Luhn algorithm is also known as
     * modulus 10 or mod 10 algorithm.
     *
     * In this case, this is used to validate credit card numbers.
     *
     * @param number    number to be validated (credit card number)
     * @return
     */
    public static boolean isValidLuhn(String number) {

        int sum = 0;

		boolean alternate = false;
		for (int i = number.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(number.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}

		return (sum % 10 == 0);
    }
}
