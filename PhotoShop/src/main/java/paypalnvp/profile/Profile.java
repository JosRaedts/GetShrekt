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

package paypalnvp.profile;

import java.io.Serializable;
import java.util.Map;

/**
 * Represents paypal user - his/her password, user name etc.
 *
 * @author Pete Reisinger <p.reisinger@gmail.com>
 */
public interface Profile extends Serializable {

    /**
     * Creates and returns part of the nvp (name value pair) containing user
     * name, password, signature etc. (obtained from paypal)
     * 
     * @return user part of the nvp request as a Map
     */
    Map<String, String> getNVPMap();
}
