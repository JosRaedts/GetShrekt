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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import paypalnvp.util.Validator;

/**
 * AddressVerify request.
 * Confirms whether a postal address and postal code match those of the
 * specified PayPal account holder.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class AddressVerify implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "AddressVerify";

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** map that holds name value pair response values */
    private Map<String, String> nvpResponse;

    /**
     * 
     * @param email     Email address of a PayPal member to verify. Must be 
     *                  valid email with maximum 255 single-byte characters
     * @param street    First line of the billing or shipping postal address to 
     *                  verify. The value of Street must match the first three 
     *                  single-byte characters of a postal address on file for 
     *                  the PayPal member. Maximum string length: 35 
     *                  single-byte characters. Alphanumeric plus - , . ' # \
     * @param zip       Postal code to verify. To pass verification, the value 
     *                  of Zip must match the first five single-byte characters 
     *                  of the postal code of the verified postal address for 
     *                  the verified PayPal member. Maximum string length: 16 
     *                  single-byte characters.
     * @throws IllegalArgumentException
     */
    public AddressVerify(String email, String street, String zip)
            throws IllegalArgumentException {

        /* validation */
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException("Email is not valid");
        }

        if (email.length() > 255) {
            throw new IllegalArgumentException("Email can by max 255 " +
                    "characters long");
        }

        /* street */
        if (street == null) {
            throw new IllegalArgumentException("Street cannot be null");
        }
        if (street.length() > 35) {
            throw new IllegalArgumentException("Street cannot be longer than "
                    + "35 characters");
        }
        String streetRegex = "^[0-9a-zA-Z\\s\\-,\\.'#\\\\]{1,35}$";
        Matcher streetMatcher = Pattern.compile(streetRegex).matcher(street);
        if (!streetMatcher.matches()) {
            throw new IllegalArgumentException("Street is not valid");
        }

        /* zip */
        if (zip == null) {
            throw new IllegalArgumentException("Zip cannot be null");
        }
        if (zip.length() > 16) {
            throw new IllegalArgumentException("Zip cannot be longer than 16 "
                    + "characters");
        }

        /* instance variables */
        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();
        nvpRequest.put("METHOD", METHOD_NAME);

        nvpRequest.put("EMAIL", email);
        nvpRequest.put("STREET", street);
        nvpRequest.put("ZIP", zip);
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

        StringBuffer str = new StringBuffer("instance of AddressVerify");
        str.append("class with the vlues: nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
