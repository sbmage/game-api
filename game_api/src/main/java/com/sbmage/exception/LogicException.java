package com.sbmage.exception;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sbmage.common.CommonConfig;
import com.sbmage.util.Util;

public class LogicException extends Exception {
	private static final long serialVersionUID = 2074542044459042219L;
	private static final Logger logger = Logger.getLogger(LogicException.class);

	private String errorCode = null;

	public LogicException(String errorMessage) {
		super(errorMessage);

		this.printErrorLog(errorMessage);
	}

	public LogicException(String errorCode, String errorMessage) {
		super(errorMessage);

		this.errorCode = errorCode;

		this.printErrorLog(errorMessage);
	}

	private void printErrorLog(String errorMessage) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		String encYn = StringUtils.defaultString(request.getParameter("encYn"), "Y");

		StringBuffer errorMessageBuffer = new StringBuffer();
		errorMessageBuffer.append(errorMessage).append("\n");
		errorMessageBuffer.append("IP - ").append(request.getRemoteAddr()).append("\n");
		errorMessageBuffer.append("URL - ").append(request.getRequestURL()).append("\n");

		if (!encYn.equals("N")) {
			String body = StringUtils.defaultString(request.getParameter(CommonConfig.BODY));

			try {
				errorMessageBuffer.append("PARAMETER - ").append(new String(Util.decrypt(CommonConfig.INIT, body, CommonConfig.DEFAULT_KEY)));
			} catch (Exception e) {System.out.println(e);}
		} else {
			Map<String, String[]> paramMap = request.getParameterMap();
			Iterator<String> it = paramMap.keySet().iterator();
			String key = null;
			String[] value = null;
			StringBuffer valueBuffer = new StringBuffer();

			while(it.hasNext()) {
			    key = it.next();
			    value = paramMap.get(key);

			    valueBuffer.append(key).append("=");

			    for(int i=0; i < value.length; i++) {
			    	valueBuffer.append(value[i]);

			    	if (i < (value.length - 1)) {
			    		valueBuffer.append(",");
			    	}
			    }

			    valueBuffer.append("&");
			}

			errorMessageBuffer.append("PARAMETER - ").append(StringUtils.substringBeforeLast(valueBuffer.toString(), "&"));
		}

		logger.error(errorMessageBuffer);
	}

	public String getErrorCode() {
		return errorCode;
	}
}
