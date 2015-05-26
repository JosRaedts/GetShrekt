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

/**
 * Obtain information about a recurring payments profile.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>.
 */
public final class GetRecurringPaymentsProfileDetails implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "GetRecurringPaymentsProfileDetails";

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** map that holds name value pair response values */
    private Map<String, String> nvpResponse;

    /**
     *
     * @param profileId Recurring payments profile ID returned in the
     *                  CreateRecurringPaymentsProfile  response. Character
     *                  length and limitations: 14 single-byte alphanumeric
     *                  characters. 19 character profile IDs are supported for
     *                  compatibility with previous versions of the PayPal API.
     * @throws IllegalArgumentException
     */
    public GetRecurringPaymentsProfileDetails(String profileId)
            throws IllegalArgumentException {

        if (profileId.length() != 14 || profileId.length() != 19) {
            throw new IllegalArgumentException("profileId has to be 14 or 19 "
                    + "characters long");
        }

        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();

        nvpRequest.put("METHOD", METHOD_NAME);
        nvpRequest.put("PROFILEID", profileId);
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

        StringBuffer str =
                new StringBuffer("instance of GetRecurringPaymentsProfileDetails");
        str.append("class with the vlues: nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
