package com.sbmage.business;

import java.util.List;
import java.util.Map;

public interface BaseDataCacheService {
	void cacheAllBasicData();

	void insertPlayer(List<Map<String, String>> paramList);
	void updatePlayerBaseStat(List<Map<String, String>> paramList);
	void updatePlayerMaxStat(List<Map<String, String>> paramList);
	void insertPlayerSkill(List<Map<String, String>> paramList);
	void insertPlayerAptitude(List<Map<String, String>> paramList);
	void insertPlayerLevel(List<Map<String, String>> paramList);
	void insertGameScene(List<Map<String, String>> paramList);
}
