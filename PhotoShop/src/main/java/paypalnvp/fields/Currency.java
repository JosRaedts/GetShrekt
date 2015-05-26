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
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>.
 */
public enum Currency implements Serializable {

        /** Australian Dollar */
        AUD,

        /**
         * Brazilian Real
         *
         * This currency is supported as a payment currency and a currency balance for
         * in-country PayPal accounts only.
         */
        BRL,

        /** Canadian Dollar */
        CAD,

        /** Czech Koruna */
        CZK,

        /** Danish Krone */
        DKK,

        /** Euro */
        EUR,

        /** Hong Kong Dollar */
        HKD,

        /** Hungarian Forint */
        HUF,

        /** Israeli New Sheqel */
        ILS,

        /** Japanese Yen */
        JPY,

        /**
		 * Malaysian Ringgit
         *
         * This currency is supported as a payment currency and a currency balance for
         * in-country PayPal accounts only.
         */
        MYR,

        /** Mexican Peso */
        MXN,

        /** Norwegian Krone */
        NOK,

        /** New Zealand Dollar */
        NZD,

        /** Philippine Peso */
        PHP,

        /** Polish Zloty */
        PLN,

        /** Pound Sterling */
        GBP,

        /** Singapore Dollar */
        SGD,

        /** Swedish Krona */
        SEK,

        /** Swiss Franc */
        CHF,

        /** Taiwan New Dollar */
        TWD,

        /** Thai Baht */
        THB,

        /** U.S. Dollar */
        USD;
}
