package com.sbmage.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WebViewMapper {

	List<Map<String, String>> getFaq();

	Map<String, String> getContent(int contentNo);

	List<Map<String, String>> getNotice();

	List<Map<String, String>> getGames();

	List<Map<String, String>> getEvent();

	List<Map<String, String>> getHelp();

	Map<String, String> couponUse(Map<String, String> param);

	List<Map<String, String>> dailyCheck(@Param("userId") Integer userId);

	Map<String, String> dailyCheckUse(@Param("userId") Integer userId);

	boolean isCheckedToday(@Param("userId") Integer userId);

	void customerNormalSave(String kakaoId, String email,
			String subject, String body, String device);

	void customerRefundSave(String kakaoId, String email, String subject,
			String body, String device, String storeAccount,
			String productInfo, String orderDate, String store);

	String getRedTimesUrl();

}
