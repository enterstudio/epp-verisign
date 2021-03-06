/***********************************************************
Copyright (C) 2004 VeriSign, Inc.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

http://www.verisign.com/nds/naming/namestore/techdocs.html
***********************************************************/
package com.verisign.epp.codec.lowbalancepoll;

// DOM imports
import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.*;

// Log4j Imports
import org.apache.log4j.Logger;

// EPP imports
import com.verisign.epp.util.*;
import com.verisign.epp.codec.gen.*;



/**
 *
 * The EPPCodec Extension Factory that needs to be configured to encode/decode
 * LowBalancePoll responses.
 *
 * The LowBalance Poll URI is: http://www.verisign.com/epp/lowbalance-poll-1.0

 * <p>Title: EPP 1.0 LowBalance </p>
 * <p>Description: LowBalance Poll Mapping for the EPP SDK</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: VeriSign</p>
 * @author majain
 * @version 1.0
 */

public class EPPLowBalancePollMapFactory
    extends EPPMapFactory {

    /** Namespace URI associated with EPPLowBalanceFactory. */
    public static final String NS = "http://www.verisign.com/epp/lowbalance-poll-1.0";

    /** Namespace prefix associated with EPPLowBalanceFactory. */
    public static final String NS_PREFIX = "lowbalance-poll";

    /** EPP LowBalance XML Schema. */
    public static final String NS_SCHEMA =
        "http://www.verisign.com/epp/lowbalance-poll-1.0 lowbalance-poll-1.0.xsd";

    /**
     * Create a new instance of EPPLowBalancePollMapFactory
     */
    public EPPLowBalancePollMapFactory() {}

    /**
     * Overridden but does nothing in the context of LowBalance Poll.  Only Poll responses
     * are implemented
     *
     * @param aMapElement DOM <code>Element</code> to create command from
     * @return <code>EPPCommand</code> if supported
     * @throws com.verisign.epp.codec.gen.EPPCodecException If cannot create <code>EPPCommand</code>.
     */
    public EPPCommand createCommand(Element aMapElement) throws com.verisign.
        epp.codec.gen.EPPCodecException {

        throw new EPPCodecException("Commands aren't supported for ns: " + NS);
    }

    /**
     * Returns the EPPService instance associated with this ExtFactory. The
     * EPPService instance contains the XML Namespace and XML Schema location
     *
     * @return  the EPPService instance associated with this ExtFactory
     */
    public EPPService getService() {
        return new EPPService(NS_PREFIX, NS,  NS_SCHEMA);
    }

    /**
     * Creates the poll response
     *
     * @param aMapElement DOM <code>Element</code> to create response from
     * @return <code>EPPResponse</code> if supported
     * @throws com.verisign.epp.codec.gen.EPPCodecException If cannot create <code>EPPResponse</code>.
     */
    public EPPResponse createResponse(Element aMapElement) throws com.verisign.
        epp.codec.gen.EPPCodecException {

		String name = aMapElement.getLocalName();

		if (!aMapElement.getNamespaceURI().equals(NS)) {
			throw new EPPCodecException("Invalid mapping type " + name);
		}		

        if (name.equals(EPPUtil.getLocalName(EPPLowBalancePollResponse.ELM_NAME))) {
            return new EPPLowBalancePollResponse();
        }
        else
			throw new EPPCodecException("Invalid response element " + name);
    }
    
	/**
	 * Gets the list of XML schemas that need to be pre-loaded into the 
	 * XML Parser.
	 *
	 * @return <code>Set</code> of <code>String</code> XML Schema names that 
	 * should be pre-loaded in the XML Parser.
	 *   
	 * @see com.verisign.epp.codec.gen.EPPMapFactory#getXmlSchemas()
	 */
	public Set getXmlSchemas() {
		Set theSchemas = new HashSet();
		theSchemas.add("lowbalance-poll-1.0.xsd");
		return theSchemas;
	}
    
}