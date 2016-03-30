package com.sbmage.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmage.dao.mapper.WebViewMapper;

@Service
public class WebViewServiceImpl implements WebViewService {

	private WebViewMapper webViewMapper = null;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		webViewMapper = sqlSession.getMapper(WebViewMapper.class);
	}

	@Override
	public List<Map<String, String>> getFaq() {
		return webViewMapper.getFaq();
	}

	@Override
	public Map<String, String> getContent(int contentNo) {
		Map<String, String> result = new HashMap<String, String>();
		Map<String, String> content = webViewMapper.getContent(contentNo);
		if (content != null) {
			result.putAll(content);
		}
		System.out.println("debug:" + result.toString());
		return result;
	}

	@Override
	public List<Map<String, String>> getNotice() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<Map<String, String>> content = webViewMapper.getNotice();
		if (content != null) {
			result.addAll(content);
		}
		return result;
	}

	@Override
	public List<Map<String, String>> getGames() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<Map<String, String>> content = webViewMapper.getGames();
		if (content != null) {
			result.addAll(content);
		}
		return result;
	}

	@Override
	public List<Map<String, String>> getEvent() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<Map<String, String>> content = webViewMapper.getEvent();
		if (content != null) {
			result.addAll(content);
		}
		return result;
	}

	@Override
	public List<Map<String, String>> getHelp() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<Map<String, String>> content = webViewMapper.getHelp();
		if (content != null) {
			result.addAll(content);
		}
		return result;
	}

	@Override
	public Map<String, String> couponUse(String userId, String couponKey) {
		Map<String, String> result = new HashMap<String, String>();
		Map<String, String> param = new HashMap<String, String>();

		param.put("userId", userId);
		param.put("couponKey", couponKey);

		Map<String, String> content = webViewMapper.couponUse(param);
		if (content != null) {
			filterResult(content);
			result.putAll(content);
		}

		return result;
	}

	private void filterResult(Map<String, String> content) {
		String resultStr = content.get("result");
		String filteredResult;
		switch (resultStr) {
		case "0":
			filteredResult = "0";
			break;
		case "2":
			// invalid coupon
			filteredResult = "1";
			break;
		case "3":
			// already used
			filteredResult = "2";
			break;
		case "4":
			// 1 user / 1coupon
			filteredResult = "3";
			break;
		default:
			// unknown error
			filteredResult = "4";
			break;
		}
		content.put("RESULT", filteredResult);
	}

	@Override
	public List<List<Map<String, String>>> dailyCheck(Integer userId) {
		List<Map<String, String>> listMap = webViewMapper.dailyCheck(userId);
		List<List<Map<String, String>>> listListMap = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < 5; i++) {
			List<Map<String, String>> innerList = new ArrayList<Map<String, String>>();
			for (int j = 0; j < 5; j++) {
				if (listMap.size() > 0) {
					Map<String, String> map = listMap.remove(0);
					innerList.add(map);
				}
			}
			listListMap.add(innerList);
		}
		return listListMap;
	}

	@Override
	public Map<String, String> dailyCheckUse(Integer userId) {
		Map<String, String> result = new HashMap<String, String>();
		Map<String, String> map = webViewMapper.dailyCheckUse(userId);
		if (map != null) {
			result.putAll(map);
		}
		return result;
	}

	@Override
	public boolean isCheckedToday(Integer userId) {
		return webViewMapper.isCheckedToday(userId);
	}

	@Override
	public void customerNormalSave(String kakaoId, String email,
			String subject, String body, String device) {
		webViewMapper.customerNormalSave(kakaoId, email, subject, body, device);
	}

	@Override
	public void customerRefundSave(String kakaoId, String email,
			String subject, String body, String device, String storeAccount,
			String productInfo, String orderDate, String store) {
		webViewMapper.customerRefundSave(kakaoId, email, subject, body, device,
				storeAccount, productInfo, orderDate, store);
		return;

	}

	@Override
	public String getRedTimesUrl() {
		return webViewMapper.getRedTimesUrl();
	}

}
