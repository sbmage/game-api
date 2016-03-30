package com.sbmage.dao.mapper;

import java.util.List;
import java.util.Map;

public interface BaseDataMapper {
	List<Map<String, String>> codeInfoList(String isParent);
	List<Map<String, String>> itemList();
	List<Map<String, String>> marketList();
	List<Map<String, String>> playerList();
	List<Map<String, String>> playerLevelList();
	List<Map<String, String>> playerGradeList();
	List<Map<String, String>> playerSkillList();
	List<Map<String, String>> gameSceneList();
	List<Map<String, String>> scoutList();
	
	void insertPlayer(Map<String, String> param);
	void insertPlayerGrade(Map<String, String> param);
	void updatePlayerBaseStat(Map<String, String> param);
	void updatePlayerMaxStat(Map<String, String> param);
	void insertPlayerSkill(Map<String, String> param);
	void insertPlayerAptitude(Map<String, String> param);
	void insertPlayerLevel(Map<String, String> param);
	void insertGameScene(Map<String, String> param);
}
