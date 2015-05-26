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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts fields to the paypal required format.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>.
 */
public final class FormatFields implements Serializable {

    /** PayPal's date/time format */
    private static final SimpleDateFormat dateTimeFormat;

    /** Credit card date format - MMYYYY */
    private static final SimpleDateFormat cardDateFormat;

    /** instantiates SimpleDateFormat only once */
    static {
        dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        cardDateFormat = new SimpleDateFormat("MMyyyy");
    }

    /**
     * This method is used inside main classes, if any classes needs date
     * argument you can use Java Date. In short - you will not need to use this
     * method.
     *
     * Paypal needs  Coordinated Universal Time (UTC/GMT), using ISO 8601
     * format, and of type ns:dateTime for Date/Time formats An example 
     * date/time stamp is 2006-08-24T05:38:48Z
     * 
     * @param dateTime
     * @return  Coordinated Universal Time (UTC/GMT), using ISO 8601 format, 
     *          and of type ns:dateTime
     */
    public static String getDateTimeField(Date dateTime) {
        return dateTimeFormat.format(dateTime);
    }

    /**
     * This method is used inside main classes, if any classes needs date
     * argument you can use Java Date. In short - you will not need to use this
     * method.
     *
     * Paypal cards needs date in MMYYYY format
     *
     * @param date
     * @return  String in MMYYYY format
     */
    public static String getCardDateField(Date date) {
        return cardDateFormat.format(date);
    }

	/**
	 * Returns formated amount. For example 24.7 will become "24.70".
	 * Returned amount can be used for setting amounts in PayPal requests.
	 * 
	 * @param amount
	 * @return
	 */
	public static String getAmountField(float amount) {

		if (amount < 0) {
			return "0.00";
		}
		return String.format("%.2f", amount);
	}

	/**
	 * Returns formated amount. For example 24.7 will become "24.70".
	 * Returned amount can be used for setting amounts in PayPal requests.
	 * 
	 * @param amount
	 * @return
	 */
	public static String getAmountField(int amount) {

		if (amount < 0) {
			return "0.00";
		}
		return String.format("%d.00", amount);
	}
}
