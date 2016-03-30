package com.sbmage.kakao;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class KakaoConnection extends Thread {
	private static final Logger logger = Logger.getLogger(KakaoConnection.class);
	private static final String URL = "https://gameapi.kakao.com/payment_v1/payments.json";

	private RestTemplate restTemplate = null;
	private Map<String, String> param = null;

	public KakaoConnection(RestTemplate restTemplate, Map<String, String> param) {
		this.restTemplate = restTemplate;
		this.param = param;
	}

	@Override
	public void run() {
		try {
			Object request = null;

			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

			for (Object key : param.keySet()) {
				map.add(key.toString(), String.valueOf(param.get(key)));
			}

			request = map;

			String resultString = StringUtils.defaultString(restTemplate.postForObject(URL, request, String.class));
			//Map<String, Object> result = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(resultString), java.util.HashMap.class, new HashMap<String, Object>());
			JSONObject result = JSONObject.fromObject(resultString);

			if (StringUtils.defaultString(String.valueOf(result.get("status"))).equals("0")
					|| StringUtils.defaultString(String.valueOf(result.get("status"))).equals("0")) {
				// SUCCESS!!
				//cp.error(resultString);
			} else {
				// FAIL
				logger.error(resultString);
				logger.error(param);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(param);
		}
	}
}
