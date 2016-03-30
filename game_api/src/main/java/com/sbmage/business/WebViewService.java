package com.sbmage.business;

import java.util.List;
import java.util.Map;

public interface WebViewService {

	List<Map<String, String>> getFaq();

	Map<String, String> getContent(int contentNo);

	List<Map<String, String>> getNotice();

	List<Map<String, String>> getGames();

	List<Map<String, String>> getEvent();

	List<Map<String, String>> getHelp();

	Map<String, String> couponUse(String userId, String couponKey);

	List<List<Map<String, String>>> dailyCheck(Integer userId);

	Map<String, String> dailyCheckUse(Integer userId);

	boolean isCheckedToday(Integer userId);

	void customerNormalSave(String kakaoId, String email,
			String subject, String body, String device);

	void customerRefundSave(String kakaoId, String email, String subject,
			String body, String device, String storeAccount,
			String productInfo, String orderDate, String store);

	String getRedTimesUrl();
}
