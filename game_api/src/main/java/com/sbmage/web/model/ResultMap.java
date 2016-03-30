package com.sbmage.web.model;

import java.io.Reader;
import java.sql.Clob;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ResultMap extends HashMap<Object, Object> {
	private static final long serialVersionUID = -4479821498510987645L;
	private final Log log = LogFactory.getLog(this.getClass());

	public Object put(Object key, Object value) {
		Reader input = null;
		String cValue = "";
		
		if (value instanceof Clob) {
			try {
				Clob clob = (Clob) value;
				StringBuffer output = new StringBuffer();

				input = clob.getCharacterStream();

				char[] buffer = new char[1024];
				int byteRead;

				while ((byteRead = input.read(buffer, 0, 1024)) != -1) {
					output.append(buffer, 0, byteRead);
				}

				//cValue = new String(output.toString().getBytes("iso8859-1"), "euc-kr");
				cValue = output.toString();
		    } catch (Exception e) {
		    	log.error("SYSTEM ERROR", e);
		    } finally {
		    	try {
		    		if (input != null) {
		    			input.close();
		    		}
		    	} catch (Exception e) {
		    		log.error("ResultMap Stream Close Error:", e);
		        }
		    }

		 	return super.put(((String)key).toLowerCase(), cValue);
		} else {
			try {
				//cValue = new String(value.toString().getBytes("iso8859-1"), "euc-kr");
				cValue = value.toString();
			} catch (Exception e) {}

			return super.put(((String)key).toLowerCase(), value == null ? "" : cValue);
		}
	}
}

