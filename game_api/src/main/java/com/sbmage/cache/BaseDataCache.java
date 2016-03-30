package com.sbmage.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import com.sbmage.exception.LogicException;

public class BaseDataCache {
	private static BaseDataCache instance = null;

	private String serverSection = null;
	private Map<String, List<String>> serverURI = null;
	private Map<String, String> serverConfig = null;
	private List<Map<String, String>> parentCodeInfoList = null;
	private List<Map<String, String>> childCodeInfoList = null;
	private List<Map<String, String>> playerLevelList = null;
	private List<Map<String, String>> playerList = null;
	private Map<String, Map<String, String>> playerResourceMap = null;
	private List<Map<String, String>> playerGradeList = null;
	private List<Map<String, String>> playerSkillList = null;
	private List<Map<String, String>> gameSceneList = null;
	private List<Map<String, String>> itemList = null;
	private List<Map<String, String>> scoutList = null;
	private List<Map<String, String>> marketList = null;
	private List<Map<String, String>> dailyEventList = null;
	private List<Map<String, String>> dailyEventRewardList = null;

	static {
		instance = new BaseDataCache();
	}

	private BaseDataCache() {}

	public static BaseDataCache getInstance() {
		return instance;
	}

	public String getServerSection() {
		return serverSection;
	}

	public void setServerSection(String serverSection) {
		this.serverSection = serverSection;
	}

	public void setServerConfig(Map<String, String> serverConfig) {
		this.serverConfig = serverConfig;
	}

	public Map<String, String> getServerConfig() {
		return serverConfig;
	}

	public String getServerURI(String section, long value) throws LogicException {
		List<String> list = serverURI.get(section);

		if (list == null) {
			throw new LogicException("The URI does not exist.");
		} else {
			int num = (int) (value % list.size());

			return list.get(num);
		}
	}

	public Map<String, List<String>> getServerURIListBySection() {
		return serverURI;
	}

	public void setServerURIList(List<Map<String, String>> serverURIList) {
		serverURI = new HashMap<String, List<String>>();
		List<String> list;
		String section;

		for (Map<String, String> map : serverURIList) {
			section = map.get("section");

			if (serverURI.containsKey(section)) {
				list = serverURI.get(section);
				list.add(map.get("uri"));
			} else {
				list = new ArrayList<String>();
				list.add(map.get("uri"));

				serverURI.put(section, list);
			}
		}
	}

	public List<Map<String, String>> getParentCodeInfoList() {
		return parentCodeInfoList;
	}

	public void setParentCodeInfoList(List<Map<String, String>> parentCodeInfoList) {
		this.parentCodeInfoList = parentCodeInfoList;
	}

	public List<Map<String, String>> getChildCodeInfoList() {
		return childCodeInfoList;
	}

	public void setChildCodeInfoList(List<Map<String, String>> childCodeInfoList) {
		this.childCodeInfoList = childCodeInfoList;
	}

	public List<Map<String, String>> getPlayerLevelList() {
		return playerLevelList;
	}

	public void setPlayerLevelList(List<Map<String, String>> playerLevelList) {
		this.playerLevelList = playerLevelList;
	}

	public List<Map<String, String>> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Map<String, String>> playerList) {
		this.playerList = playerList;
		playerResourceMap = new HashMap<String, Map<String, String>>();

		for (Map<String, String> player : playerList) {
			playerResourceMap.put(player.get("file_name"), player);
		}
	}

	public Map<String, Map<String, String>> getPlayerResourceMap() {
		return playerResourceMap;
	}

	public List<Map<String, String>> getPlayerGradeList() {
		return playerGradeList;
	}

	public void setPlayerGradeList(List<Map<String, String>> playerGradeList) {
		this.playerGradeList = playerGradeList;
	}

	public List<Map<String, String>> getPlayerSkillList() {
		return playerSkillList;
	}

	public void setPlayerSkillList(List<Map<String, String>> playerSkillList) {
		this.playerSkillList = playerSkillList;
	}

	public List<Map<String, String>> getGameSceneList() {
		return gameSceneList;
	}

	public void setGameSceneList(List<Map<String, String>> gameSceneList) {
		this.gameSceneList = gameSceneList;
	}

	public List<Map<String, String>> getMarketList() {
		return marketList;
	}

	public void setMarketList(List<Map<String, String>> marketList) {
		this.marketList = marketList;
	}

	public List<Map<String, String>> getItemList() {
		return itemList;
	}

	public void setItemList(List<Map<String, String>> itemList) {
		this.itemList = itemList;
	}

	public List<Map<String, String>> getScoutList() {
		return scoutList;
	}

	public void setScoutList(List<Map<String, String>> scoutList) {
		this.scoutList = scoutList;
	}

	public Map<String, String> getDailyEventList() {
		long currentTime = (new Date()).getTime() / 1000;
		long startDate = 0, endDate = 0;

		for (Map<String, String> map : dailyEventList) {
			startDate = NumberUtils.toLong(map.get("start_dt"));
			endDate = NumberUtils.toLong(map.get("end_dt"));

			if (currentTime > startDate && currentTime < endDate) {
				return map;
			}
		}

		return new HashMap<String, String>();
	}

	public void setDailyEventList(List<Map<String, String>> dailyEventList) {
		this.dailyEventList = dailyEventList;
	}

	public List<Map<String, String>> getDailyEventRewardList(String eventId) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (Map<String, String> map : dailyEventRewardList) {
			if (eventId.equals(map.get("event_id"))) {
				list.add(map);
			}
		}

		return list;
	}

	public void setDailyEventRewardList(List<Map<String, String>> dailyEventRewardList) {
		this.dailyEventRewardList = dailyEventRewardList;
	}
}
