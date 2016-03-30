package com.sbmage.business;

import java.util.List;
import java.util.Map;

public interface MarketService {
	Map<String, String> buySoftCash(Map<String, String> param);

	Map<String, String> buyHardCashForApple(Map<String, String> param);

	Map<String, String> buyHardCashForGoogle(Map<String, String> param);
	
	List<Map<String, String>> getHardCashHistoryForEvent(Map<String, String> param);

}
