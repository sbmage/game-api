package com.sbmage.util.http;


public class BMHttpClientConnection {
	private static BMHttpClientConnection instance = null;

	static {
		instance = new BMHttpClientConnection();
	}

	private BMHttpClientConnection() {}

	public static BMHttpClientConnection getInstance() {
		return instance;
	}
/*
	public boolean httpClientConnection(RestTemplate restTemplate,
											String section,
											int protocolNo,
											Map<String, String> param) throws LogicException {
		String protocol = CommonConfig.URL[protocolNo];

		return this.httpClientConnection(restTemplate, HttpMethod.GET, section, protocol, param);
	}

	public boolean httpClientConnection(RestTemplate restTemplate,
											HttpMethod httpType,
											String section,
											int protocolNo,
											Map<String, String> param) throws LogicException {
		String protocol = CommonConfig.URL[protocolNo];

		return this.httpClientConnection(restTemplate, httpType, section, protocol, param);
	}

	public boolean httpClientConnection(RestTemplate restTemplate,
											String section,
											String protocol,
											Map<String, String> param) throws LogicException {
		return this.httpClientConnection(restTemplate, HttpMethod.GET, section, protocol, param);
	}

	public boolean httpClientConnection(RestTemplate restTemplate,
											HttpMethod httpType,
											String section,
											String protocol,
											Map<String, String> param) throws LogicException {
		long playerId = NumberUtils.toLong(param.get(Player.PLAYER_ID));

		String uri = ConfigDataCache.getInstance().getServerURI(section, playerId);
		String result = null;

		if (httpType == HttpMethod.GET) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(uri);
			buffer.append(protocol);
			buffer.append(Util.makeStringParam(param));

			result = StringUtils.defaultString(restTemplate.getForObject(buffer.toString(), String.class));
		} else if (httpType == HttpMethod.POST) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(uri);
			buffer.append(protocol);

			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

			for (String key : param.keySet()) {
				map.add(key, String.valueOf(param.get(key)));
			}

			result = StringUtils.defaultString(restTemplate.postForObject(buffer.toString(), map, String.class));
		} else {
			throw new LogicException("Invalid HttpType.");
		}

		if (StringUtils.isBlank(result)) {
			return false;
		} else {
			JSONObject object = JSONObject.fromObject(result);

			if (object == null || StringUtils.defaultString(object.get(BounceManConfig.IS_SUCCESS).toString()).equals("N")) {
				return false;
			}
		}

		return true;
	}

	public JSONObject httpClientConnection(RestTemplate restTemplate,
											HttpHeaders headers,
											HttpMethod httpType,
											String uri,
											String protocol,
											Map<String, String> param) throws LogicException {
		String result = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append(uri);
		buffer.append(protocol);

		if (httpType == HttpMethod.GET) {
			buffer.append(Util.makeStringParam(param));

			ResponseEntity<byte[]> response = restTemplate.exchange(buffer.toString(), HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);

			result = StringUtils.defaultString(new String(response.getBody()));
		} else if (httpType == HttpMethod.POST) {
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

			for (String key : param.keySet()) {
				map.add(key, String.valueOf(param.get(key)));
			}

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

			result = StringUtils.defaultString(restTemplate.postForObject(buffer.toString(), request, String.class));
		} else {
			throw new LogicException("Invalid HttpType.");
		}

		if (StringUtils.isBlank(result)) {
			return new JSONObject();
		} else {
			JSONObject object = JSONObject.fromObject(result);

			if (object == null || StringUtils.defaultString(object.get(BounceManConfig.IS_SUCCESS).toString()).equals("N")) {
				return new JSONObject();
			} else {
				return object;
			}
		}
	}

	public JSONObject httpClientConnection(RestTemplate restTemplate,
												HttpHeaders headers,
												HttpMethod httpType,
												String url,
												Map<String, String> param) throws LogicException {
		String result = null;

		if (httpType == HttpMethod.GET) {
			url += Util.makeStringParam(param);

			ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);

			result = StringUtils.defaultString(new String(response.getBody()));
		} else if (httpType == HttpMethod.POST) {
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

			for (String key : param.keySet()) {
				map.add(key, String.valueOf(param.get(key)));
			}

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

			result = StringUtils.defaultString(restTemplate.postForObject(url, request, String.class));
		} else {
			throw new LogicException("Invalid HttpType.");
		}

		if (StringUtils.isBlank(result)) {
			return new JSONObject();
		} else {
			JSONObject object = JSONObject.fromObject(result);

			if (object == null || StringUtils.defaultString(object.get(BounceManConfig.IS_SUCCESS).toString()).equals("N")) {
				return new JSONObject();
			} else {
				return object;
			}
		}
	}
*/
}
