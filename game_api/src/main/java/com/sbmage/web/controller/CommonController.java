package com.sbmage.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sbmage.common.CommonConfig;
import com.sbmage.common.CommonMessage;
import com.sbmage.exception.LogicException;
import com.sbmage.web.model.ResultModelAndView;

public abstract class CommonController {
	private static final Logger LOGGER = Logger.getLogger(CommonController.class);

	protected String RESULT = "result";

	@SuppressWarnings("unchecked")
	protected <V> Map<String, V> getParameter() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		LOGGER.debug("####" + request.getAttribute(CommonConfig.PARAMETER).toString());
		
		return (Map<String, V>) request.getAttribute(CommonConfig.PARAMETER);
	}
	
	protected Map<String, String> getAddPopParameter() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Map<String, String[]> parameterMap = req.getParameterMap();
		Map<String, String> parameter = new HashMap<String, String>();

		for(String key : parameterMap.keySet()) {
			String[] values = parameterMap.get(key);

			if (values.length == 1) {
				try {
					parameter.put(key, values[0]);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
//		System.out.println("getParameter() : "+ parameter);
		
		return parameter;
	}	

	@ExceptionHandler(LogicException.class)
	protected ModelAndView handlException(LogicException e) {
		ResultModelAndView rmv = new ResultModelAndView(false);
		rmv.addObject("ERROR_CODE", e.getErrorCode());
		rmv.addObject("ERROR_MSG", e.getMessage());

		try { rmv.flush(); } catch (Exception e2) {}

		return rmv;
	}

	@ExceptionHandler(Exception.class)
	protected ModelAndView handlException(Exception e) {
		new LogicException(CommonMessage.ERROR_0000[0], CommonMessage.ERROR_0000[1] + "|" + e.getMessage());

		ResultModelAndView rmv = new ResultModelAndView(false);
		rmv.addObject("ERROR_CODE", CommonMessage.ERROR_0000[0]);
		rmv.addObject("ERROR_MSG", CommonMessage.ERROR_0000[1]);

		try { rmv.flush(); } catch (Exception e2) {}

		return rmv;
	}
}
