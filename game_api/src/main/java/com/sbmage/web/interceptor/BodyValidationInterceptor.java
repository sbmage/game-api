package com.sbmage.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sbmage.common.CommonConfig;
import com.sbmage.common.CommonMessage;
import com.sbmage.exception.LogicException;
import com.sbmage.util.Util;

@Component
public class BodyValidationInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		String body = req.getParameter(CommonConfig.BODY);
		final String addPopMediaKey = ""; //애드팝 앱 키
		
		if (addPopMediaKey.equals(req.getParameter("mediakey"))){
			//addpop의 경우 일반적인 요청으로 처리함
			return true;
		} else {

			if (StringUtils.isEmpty(body)) {
				throw new LogicException(CommonMessage.ERROR_0001[0], CommonMessage.ERROR_0001[1]);
			}
	
			try {
				String decodedBody = Util.decrypt(CommonConfig.INIT, body, CommonConfig.DEFAULT_KEY);
	
				req.setAttribute(CommonConfig.PARAMETER, JSONObject.fromObject(decodedBody));
			} catch (Exception e) {
				throw new LogicException(CommonMessage.ERROR_0000[0], CommonMessage.ERROR_0000[1] + e.getMessage());
			}
		}
		return true;
	}
}
