package com.sbmage.business;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmage.dao.mapper.MarketMapper;

@Service
public class MarketServiceImpl implements MarketService {
	private MarketMapper marketMapper = null;

	@Autowired
	public void setSqlSesstion(SqlSession sqlSession) {
		marketMapper = sqlSession.getMapper(MarketMapper.class);
	}

	public Map<String, String> buySoftCash(Map<String, String> param) {
		System.out.println(param);
		return marketMapper.buySoftCash(param);
	}

	public Map<String, String> buyHardCashForApple(Map<String, String> param) {
		return marketMapper.buyHardCashForApple(param);
	}

	public Map<String, String> buyHardCashForGoogle(Map<String, String> param) {
		return marketMapper.buyHardCashForGoogle(param);
	}

	@Override
	public List<Map<String, String>> getHardCashHistoryForEvent(Map<String, String> param) {
		return marketMapper.getHardCashHistoryForEvent(param);
	}
}
