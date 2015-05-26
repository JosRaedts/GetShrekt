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
 * 3D Secure Request Fields (U.K. Merchants Only)
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public final class Secure3D implements RequestFields {

    /** map that holds name value pair request values */
    private final Map<String, String> nvpRequest;

    public Secure3D() {
        this.nvpRequest = new HashMap<String, String>();
    }

	/**
	 * A value returned by the Cardinal Centinel. If the cmpi_lookup request
	 * returns Y for Enrolled, set this field to the PAResStatus value returned
	 * by cmpi_authenticate; otherwise, set this field to blank.
	 *
	 * @param status
	 */
	public void setStatus3D(String status) {
		nvpRequest.put("AUTHSTATUS3D", status);
	}

	/**
	 * A value returned by the Cardinal Centinel. Set this field to the Enrolled
	 * value returned by cmpi_lookup.
	 *
	 * @param mpi
	 */
	public void setMpiVendor3D(String mpi) {
		nvpRequest.put("MPIVENDOR3DS", mpi);
	}

	/**
	 * A value returned by the Cardinal Centinel. If the cmpi_lookup request
	 * returns Y for Enrolled, set this field to the Cavv value returned by
	 * cmpi_authenticate; otherwise, set this field to blank.
	 *
	 * @param caav
	 */
	public void setCaav(String caav) {
		nvpRequest.put("CAVV", caav);
	}

	/**
	 * A value returned by the Cardinal Centinel. If the cmpi_lookup request
	 * returns Y for Enrolled, set this field to the EciFlag value returned
	 * by cmpi_authenticate; otherwise, set this field to the EciFlag value
	 * returned by cmpi_lookup.
	 *
	 * @param eci
	 */
	public void setEci(String eci) {
		nvpRequest.put("ECI3DS", eci);
	}

	/**
	 * A value returned by the Cardinal Centinel. If the cmpi_lookup request
	 * returns Y for Enrolled, set this field to the Xid value returned by
	 * cmpi_authenticate; otherwise set this field to blank.
	 *
	 * @param xid
	 */
	public void setXid(String xid) {
		nvpRequest.put("XID", xid);
	}

    public Map<String, String> getNVPRequest() {
        return new HashMap<String, String>(nvpRequest);
    }

    @Override
    public String toString() {

        return "instance of Secure3D class with the values: "
                + "nvpRequest: " + nvpRequest.toString();
    }
}
