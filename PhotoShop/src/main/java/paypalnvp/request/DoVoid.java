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
 * Void an order or an authorization.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class DoVoid implements Request {

    /** Method value of this request */
    private static final String METHOD_NAME = "DoVoid";

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** map that holds name value pair response values */
    private Map<String, String> nvpResponse;

	/**
	 * 
	 * @param authorizationId 	The value of the original authorization 
	 * 							identification number returned by a PayPal 
	 * 							product. 
	 * 							IMPORTANT: If you are voiding a transaction 
	 * 							that has been reauthorized, use the ID from the 
	 * 							original authorization, and not the 
	 * 							reauthorization. 
	 * 							Character length and limits: 19 single-byte 
	 * 							characters.
	 * @throws IllegalArgumentException
	 */
	public DoVoid(String authorizationId) throws IllegalArgumentException {

        /* validation */
		if (authorizationId == null || authorizationId.length() > 19) {
            throw new IllegalArgumentException("Authorization id can be "
					+ "maximum 19 characters long.");
		}

        /* instance variables */
        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();
        nvpRequest.put("METHOD", METHOD_NAME);
        nvpRequest.put("AUTHORIZATIONID", authorizationId);
	}

	/**
	 * An informational note about this settlement that is displayed to the
	 * payer in email and in his transaction history.
	 *
	 * @param note Character length and limits: 255 single-byte characters.
	 * @throws IllegalArgumentException
	 */
	public void setNote(String note) throws IllegalArgumentException {

		if (note == null || note.length() > 255) {
			throw new IllegalArgumentException("Note can be maximum 255 "
					+ "characters long.");
		}
        nvpRequest.put("NOTE", note);
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

        StringBuffer str = new StringBuffer("instance of DoVoid");
        str.append("class with the vlues: nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
