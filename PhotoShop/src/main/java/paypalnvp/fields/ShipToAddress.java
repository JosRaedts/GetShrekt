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

/**
 * Shipping address
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class ShipToAddress implements RequestFields {

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /**
     * 
     * @param name      Person's name associated with this shipping address.
     *                  Character length and limitations: 32 single-byte 
     *                  characters.
     * @param street    First street address. Character length and 
     *                  limitations: 100 single-byte characters.
     * @param city      Name of city. Character length and limitations: 40 
     *                  single-byte characters.
     * @param state     State or province. Character length and limitations: 40 
     *                  single-byte character.
     * @param country
     * @throws IllegalArgumentException
     */
    public ShipToAddress(String name, String street, String city, String state,
            Country country) throws IllegalArgumentException {

        if (name.length() > 32 || street.length() > 100 || city.length() > 40 || 
                state.length() > 40) {

            throw new IllegalArgumentException();
        }

        nvpRequest = new HashMap<String, String>();

        nvpRequest.put("SHIPTONAME", name);
        nvpRequest.put("SHIPTOSTREET", street);
        nvpRequest.put("SHIPTOCITY", city);
        nvpRequest.put("SHIPTOSTATE", state);
        nvpRequest.put("SHIPTOCOUNTRY", country.toString());

    }

    /**
     * Second street address. Character length and limitations: 100 single-byte
     * characters.
     *
     * @param street
     * @throws IllegalArgumentException
     */
    public void setStreet2(String street) throws IllegalArgumentException {

        if (street.length() > 100) {
            throw new IllegalArgumentException("Street can be maximum 100 "
                    + "characters");
        }
        nvpRequest.put("SHIPTOSTREET2", street);
    }

    /**
     * U.S. ZIP code or other country-specific postal code. Required if using a
     * U.S. shipping address; may be required for other countries.  Character
     * length and limitations: 20 single-byte characters.
     *
     * @param street
     * @throws IllegalArgumentException
     */
    public void setZIP(String zip) throws IllegalArgumentException {

        if (zip.length() > 20) {
            throw new IllegalArgumentException("Zip code can be maximum 20 "
                    + "characters");
        }
        nvpRequest.put("SHIPTOZIP", zip);
    }

    /**
     * Phone number. Character length and limit: 20 single-byte characters.
     *
     * @param phoneNumber
     * @throws IllegalArgumentException
     */
    public void setPhoneNumber(String phoneNumber)
            throws IllegalArgumentException {

        if (phoneNumber.length() > 20) {
            throw new IllegalArgumentException("Phone number can be maximum 20 "
                    + "characters");
        }
        nvpRequest.put("SHIPTOPHONENUM", phoneNumber);
    }

    public Map<String, String> getNVPRequest() {
        return new HashMap<String, String>(nvpRequest);
    }

    @Override
    public String toString() {
        
        return "instance of ShipToAddress class with the values: nvpRequest: "
                + nvpRequest.toString(); 
    }
}
