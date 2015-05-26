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
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class ManagePendingTransactionStatus implements Request {

	/** The operation you want to perform on the transaction */
	public enum Action {
		/** accepts the payment */
		ACCEPT,

		/** rejects the payment */
		DENY;
	}

    /** Method value of this request */
    private static final String METHOD_NAME = "ManagePendingTransactionStatus";

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    /** map that holds name value pair response values */
    private Map<String, String> nvpResponse;

	/**
	 * Returns instance of ManagePendingTransaction class. If arguments are not
	 * valid, exception is thrown.
	 *
	 * @param transactionId The transaction ID of the payment transaction.
	 * @param action		The operation you want to perform on the transaction
	 * @throws IllegalArgumentException
	 */
	public ManagePendingTransactionStatus(String transactionId, Action action)
			throws IllegalArgumentException {

		if (transactionId == null || transactionId.length() > 17) {
			throw new IllegalArgumentException("Transaction id cannot be "
					+ "longer than 17 characters.");
		}
        nvpResponse = new HashMap<String, String>();
        nvpRequest  = new HashMap<String, String>();
        nvpRequest.put("METHOD", METHOD_NAME);
        nvpRequest.put("TRANSACTIONID", transactionId);
        nvpRequest.put("ACTION", action.toString());
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

        StringBuffer str = new StringBuffer("instance of ");
        str.append("MangagePendingTransactionStatus class with the vlues: ");
		str.append("nvpRequest - ");
        str.append(nvpRequest.toString());
		str.append("; nvpResponse - ");
		str.append(nvpResponse.toString());

		return str.toString();
    }
}
