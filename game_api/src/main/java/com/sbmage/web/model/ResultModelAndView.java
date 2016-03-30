package com.sbmage.web.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sbmage.common.CommonConfig;
import com.sbmage.common.CommonMessage;
import com.sbmage.exception.LogicException;
import com.sbmage.util.Util;

public class ResultModelAndView extends ModelAndView {
	private static final String VIEW_NAME = "jsonReport";
	private static final String IS_SUCCESS = "IS_SUCCESS";
	private static final String BODY = "body";

	private JSONObject body = null;

	public ResultModelAndView() {
		this(true);
	}

	public ResultModelAndView(boolean flag) {
		super(VIEW_NAME);
		super.addObject(IS_SUCCESS, flag ? "Y" : "N");

		body = new JSONObject();
	}

	@SuppressWarnings("unchecked")
	public <V> void addSubObject(String attributeName, V attributeValue) {
		if (attributeValue instanceof List) {
			body.put(attributeName, Util.listToJsonArray((List<Map<String, String>>) attributeValue));
		} else if (attributeValue instanceof Map) {
			body.put(attributeName, Util.mapToJsonObject((Map<String, String>) attributeValue));
		} else {
			body.put(attributeName, attributeValue);
		}
	}

	public <V> void addSubObjectListMap(String attributeName, List<Map<Map<String, String>, List<Map<String, String>>>> attributeValue) {

		for (Map<Map<String, String>, List<Map<String, String>>> tmp : attributeValue) {
			for (Map<String, String> key : tmp.keySet()) {
				for (int i = 0; i < key.size(); i++) {
					JSONObject keys = Util.mapToJsonObject(key);

					body.put(keys, Util.listToJsonArray(tmp.get(key)));
				}
			}
		}
		// body.put(attributeName, body);
	}

	public <V> void addSubObjectMapList(Map<String, List<Map<String, String>>> attributeValue) {
		for (String key : attributeValue.keySet()) {
			for (int i = 0; i < attributeValue.get(key).size(); i++) {
				body.put(key, Util.listToJsonArray((List<Map<String, String>>) attributeValue.get(key)));
			}
		}
	}

	public void addSubObject(Map<String, String> attribute) {
		for (String key : attribute.keySet()) {
			body.put(key, attribute.get(key));
		}
	}

	public void flush() throws LogicException {
		try {
			if (body != null && !body.isEmpty()) {
				HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
				String encYn = StringUtils.defaultString(req.getParameter("encYn"));

				if (encYn.equals("N")) {
					super.addObject(BODY, (Object) body.toString());
				} else {
					String encBody = Util.encrypt(CommonConfig.INIT, body.toString(), CommonConfig.DEFAULT_KEY);

					super.addObject(BODY, encBody);
				}
			}
		} catch (Exception e) {
			throw new LogicException(CommonMessage.ERROR_0000[0], e.getMessage());
		}
	}
}
