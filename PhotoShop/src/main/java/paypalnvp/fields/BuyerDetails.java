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

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class BuyerDetails implements RequestFields {

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /**
     * The unique identifier provided by eBay for this buyer. The value may
     * or may not be the same as the username. In the case of eBay, it is
     * different. Character length and limitations: 255 single-byte
     * characters
     *
     * @param buyerUserName
     * @throws IllegalArgumentException
     */
    public BuyerDetails(String buyerUserName) throws IllegalArgumentException {

        if (buyerUserName.length() > 255) {
            throw new IllegalArgumentException("Buyer user name cannot exceed "
                    + "255 characters");
        }
        nvpRequest = new HashMap<String, String>();
        nvpRequest.put("BUYERUSERNAME", buyerUserName);
    }

    public Map<String, String> getNVPRequest() {
        return new HashMap<String, String>(nvpRequest);
    }

    @Override
    public String toString() {

        return "instance of BuyerDetails class with the values: "
                + "nvpRequest: " + nvpRequest.toString();
    }
}
