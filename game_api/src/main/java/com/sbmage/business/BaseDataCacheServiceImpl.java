package com.sbmage.business;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmage.cache.BaseDataCache;
import com.sbmage.dao.mapper.BaseDataMapper;

@Service("baseDataCacheService")
public class BaseDataCacheServiceImpl implements BaseDataCacheService {
	private BaseDataMapper baseDataMapper = null;

	@Autowired
	public void setSqlSesstion(SqlSession sqlSession) {
		baseDataMapper = sqlSession.getMapper(BaseDataMapper.class);
	}

	public void cacheAllBasicData() {
		BaseDataCache.getInstance().setChildCodeInfoList(baseDataMapper.codeInfoList("N"));
		BaseDataCache.getInstance().setItemList(baseDataMapper.itemList());
		BaseDataCache.getInstance().setScoutList(baseDataMapper.scoutList());
		BaseDataCache.getInstance().setMarketList(baseDataMapper.marketList());
		BaseDataCache.getInstance().setPlayerList(baseDataMapper.playerList());
		BaseDataCache.getInstance().setPlayerLevelList(baseDataMapper.playerLevelList());
		BaseDataCache.getInstance().setPlayerGradeList(baseDataMapper.playerGradeList());
		BaseDataCache.getInstance().setPlayerSkillList(baseDataMapper.playerSkillList());
		BaseDataCache.getInstance().setGameSceneList(baseDataMapper.gameSceneList());		BaseDataCache.getInstance().setParentCodeInfoList(baseDataMapper.codeInfoList("Y"));

	}

	public void insertPlayer(List<Map<String, String>> paramList) {
		for (Map<String, String> param : paramList) {
			baseDataMapper.insertPlayer(param);

			for (int i=1; i < 8; i++) {
				param.put("grade", String.valueOf(i));

				baseDataMapper.insertPlayerGrade(param);
			}
		}
	}

	public void updatePlayerBaseStat(List<Map<String, String>> paramList) {
		for (Map<String, String> param : paramList) {
			baseDataMapper.updatePlayerBaseStat(param);
		}
	}

	public void updatePlayerMaxStat(List<Map<String, String>> paramList) {
		for (Map<String, String> param : paramList) {
			baseDataMapper.updatePlayerMaxStat(param);
		}
	}

	public void insertPlayerSkill(List<Map<String, String>> paramList) {
		for (Map<String, String> param : paramList) {
			baseDataMapper.insertPlayerSkill(param);
		}
	}

	public void insertPlayerAptitude(List<Map<String, String>> paramList) {
		for (Map<String, String> param : paramList) {
			baseDataMapper.insertPlayerAptitude(param);
		}
	}

	public void insertPlayerLevel(List<Map<String, String>> paramList) {
		for (Map<String, String> param : paramList) {
			baseDataMapper.insertPlayerLevel(param);
		}
	}

	public void insertGameScene(List<Map<String, String>> paramList) {
		for (Map<String, String> param : paramList) {
			baseDataMapper.insertGameScene(param);
		}
	}
}
