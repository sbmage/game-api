package com.sbmage.web.controller.ver1_0_0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sbmage.business.BaseDataCacheService;
import com.sbmage.cache.BaseDataCache;
import com.sbmage.web.controller.CommonController;
import com.sbmage.web.model.ResultModelAndView;

@Controller
@RequestMapping("/ver1_0_0/baseData/")
public class BaseDataController extends CommonController {
	private final String P_CODE = "pcode";
	private final String C_CODE = "ccode";
	private final String MARKET = "market";
	private final String ITEM = "item";
	private final String SCOUT = "scout";
	private final String PLAYER = "player";
	private final String PLAYER_LEVEL = "player_level";
	private final String PLAYER_GRADE = "player_grade";
	private final String PLAYER_SKILL = "player_skill";
	private final String GAME_SCENE = "game_scene";
	private final String DAILY_EVENT = "daily_event";
	private final String DAILY_EVENT_REWARD = "daily_event_reward";

	@Autowired
	private BaseDataCacheService service;

	@RequestMapping("all.do")
    public ModelAndView all() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(P_CODE, BaseDataCache.getInstance().getParentCodeInfoList());
		rmv.addSubObject(C_CODE, BaseDataCache.getInstance().getChildCodeInfoList());
		rmv.addSubObject(MARKET, BaseDataCache.getInstance().getMarketList());
		rmv.addSubObject(ITEM, BaseDataCache.getInstance().getItemList());
		rmv.addSubObject(SCOUT, BaseDataCache.getInstance().getScoutList());
		rmv.addSubObject(PLAYER, BaseDataCache.getInstance().getPlayerList());
		//rmv.addSubObject(PLAYER_LEVEL, BaseDataCache.getInstance().getPlayerLevelList());
		rmv.addSubObject(PLAYER_GRADE, BaseDataCache.getInstance().getPlayerGradeList());
		rmv.addSubObject(PLAYER_SKILL, BaseDataCache.getInstance().getPlayerSkillList());
		rmv.addSubObject(GAME_SCENE, BaseDataCache.getInstance().getGameSceneList());
/*
		Map<String, String> dailyEvent = BaseDataCache.getInstance().getDailyEventList();

		rmv.addSubObject(DAILY_EVENT, dailyEvent);

		if (!dailyEvent.isEmpty()) {
			rmv.addSubObject(DAILY_EVENT_REWARD, BaseDataCache.getInstance().getDailyEventRewardList(dailyEvent.get("event_id")));
		}
*/
		rmv.flush();

		return rmv;
    }

	@RequestMapping("codeInfo.do")
    public ModelAndView codeInfo() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(P_CODE, BaseDataCache.getInstance().getParentCodeInfoList());
		rmv.addSubObject(C_CODE, BaseDataCache.getInstance().getChildCodeInfoList());
		rmv.flush();

		return rmv;
    }

	@RequestMapping("market.do")
    public ModelAndView market() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(MARKET, BaseDataCache.getInstance().getMarketList());
		rmv.flush();

		return rmv;
    }

	@RequestMapping("item.do")
    public ModelAndView item() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(ITEM, BaseDataCache.getInstance().getItemList());
		rmv.flush();

		return rmv;
    }

	@RequestMapping("scout.do")
    public ModelAndView scout() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(SCOUT, BaseDataCache.getInstance().getScoutList());
		rmv.flush();

		return rmv;
    }

	@RequestMapping("player.do")
    public ModelAndView player() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(PLAYER, BaseDataCache.getInstance().getPlayerList());
		rmv.flush();

		return rmv;
    }

	@RequestMapping("playerLevel.do")
    public ModelAndView playerLevel() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(PLAYER_LEVEL, BaseDataCache.getInstance().getPlayerLevelList());
		rmv.flush();

		return rmv;
    }

	@RequestMapping("playerGrade.do")
    public ModelAndView playerGrade() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(PLAYER_GRADE, BaseDataCache.getInstance().getPlayerGradeList());
		rmv.flush();

		return rmv;
    }

	@RequestMapping("playerSkill.do")
    public ModelAndView playerSkill() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(PLAYER_SKILL, BaseDataCache.getInstance().getPlayerSkillList());
		rmv.flush();

		return rmv;
    }

	@RequestMapping("gameScene.do")
    public ModelAndView gameScene() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(GAME_SCENE, BaseDataCache.getInstance().getGameSceneList());
		rmv.flush();

		return rmv;
    }

	@RequestMapping("resourceList.do")
    public ModelAndView resourceList() throws Exception {
		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject("res_list", BaseDataCache.getInstance().getPlayerResourceMap());
		rmv.flush();

		return rmv;
    }
}
