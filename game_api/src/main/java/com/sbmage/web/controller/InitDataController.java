package com.sbmage.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sbmage.business.BaseDataCacheService;
import com.sbmage.web.model.ResultModelAndView;

@Controller
@RequestMapping("/")
public class InitDataController extends CommonController {
	@Autowired
	private BaseDataCacheService service;

	@RequestMapping("insertPlayer.do")
    public ModelAndView insertPlayer() throws Exception {
		final String filePath = "D:/Project/2014Heroes/document/data/player/Player.csv";
		final String[] keyNames = {"birthday", "height", "weight", "player_name", "position"};

		int x = 0;
		Map<String, String> param;
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();

		List<String> list = FileUtils.readLines(new File(filePath));

		for (String line : list) {
			x++;
			String[] array = StringUtils.split(line, ",");

			param = new HashMap<String, String>();
			param.put("player_id", String.valueOf(x));

			for (int z=0; z < keyNames.length; z++) {
				param.put(keyNames[z], array[z]);
			}

			paramList.add(param);
		}

		service.insertPlayer(paramList);

		ResultModelAndView rmv = new ResultModelAndView();
		rmv.flush();
		return rmv;
	}
	
	@RequestMapping("updatePlayerBaseStat.do")
    public ModelAndView updatePlayerBaseStat() throws Exception {
		final String filePath = "D:/Project/2014Heroes/document/data/player/";
		final String[] fileNames = {"NormalBaseStat.csv", "GreatBaseStat.csv"};
		final String[] keyNames = {"player_name", "gk", "defense", "drible", "pass", "shoot", "power", "speed", "hardwork", "fight_spirit", "cleverness", "creativity", "jump"};

		Map<String, String> param;
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();

		for (int i=0; i < fileNames.length; i++) {
			List<String> list = FileUtils.readLines(new File(filePath + fileNames[i]));

			for (String line : list) {
				String[] array = StringUtils.split(line, ",");

				param = new HashMap<String, String>();
				param.put("grade", String.valueOf(i + 1));

				for (int x=0; x < keyNames.length; x++) {
					param.put(keyNames[x], array[x]);
				}

				paramList.add(param);
			}
		}

		service.updatePlayerBaseStat(paramList);

		ResultModelAndView rmv = new ResultModelAndView();
		rmv.flush();
		return rmv;
	}
	
	@RequestMapping("updatePlayerMaxStat.do")
    public ModelAndView updatePlayerMaxStat() throws Exception {
		final String filePath = "D:/Project/2014Heroes/document/data/player/";
		final String[] fileNames = {"NormalMaxStat.csv", "GreatMaxStat.csv", "GoldMaxStat.csv", "StarMaxStat.csv", "SuperStarMaxStat.csv", "PlatinumStarMaxStat.csv", "LegendStarMaxStat.csv"};
		final String[] keyNames = {"player_name", "gk", "defense", "drible", "pass", "shoot", "power", "speed", "hardwork", "fight_spirit", "cleverness", "creativity", "jump"};

		Map<String, String> param;
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();

		for (int i=0; i < fileNames.length; i++) {
			List<String> list = FileUtils.readLines(new File(filePath + fileNames[i]));

			for (String line : list) {
				String[] array = StringUtils.split(line, ",");

				param = new HashMap<String, String>();
				param.put("grade", String.valueOf(i + 1));

				for (int x=0; x < keyNames.length; x++) {
					param.put(keyNames[x], array[x]);
				}

				paramList.add(param);
			}
		}

		service.updatePlayerMaxStat(paramList);

		ResultModelAndView rmv = new ResultModelAndView();
		rmv.flush();
		return rmv;
	}

	@RequestMapping("insertPlayerSkill.do")
    public ModelAndView insertPlayerSkill() throws Exception {
		final String filePath = "D:/Project/2014Heroes/document/data/Skill.csv";
		final String[] keyNamesA = {"skill_id", "category", "type", "level", "skill_name", "game_scene", "rank", "sort_order"};
		final String[] keyNamesB = {"req_skill_type", "req_skill_value", "req_hidden_skill_type", "req_hidden_skill_value"};

		Map<String, String> param;
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();

		List<String> list = FileUtils.readLines(new File(filePath));

		for (int i=0; i < list.size(); i++) {
			if (i != 0) {
				String[] array = StringUtils.split(list.get(i), ",");

				param = new HashMap<String, String>();
				param.put(keyNamesB[0], "0");
				param.put(keyNamesB[1], "0");
				param.put(keyNamesB[2], "0");
				param.put(keyNamesB[3], "0");

				for (int x=0; x < keyNamesA.length; x++) {
					param.put(keyNamesA[x], array[x]);
				}

				for (int x=keyNamesA.length; x < array.length; x++) {
					if (NumberUtils.toInt(array[x].trim()) != 0) {
						if ((x - keyNamesA.length) == 0) {
							param.put("req_skill_type", "5");
							param.put("req_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 1) {
							param.put("req_skill_type", "7");
							param.put("req_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 2) {
							param.put("req_skill_type", "6");
							param.put("req_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 3) {
							param.put("req_skill_type", "4");
							param.put("req_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 4) {
							param.put("req_skill_type", "1");
							param.put("req_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 5) {
							param.put("req_skill_type", "3");
							param.put("req_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 6) {
							param.put("req_skill_type", "2");
							param.put("req_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 7) {
							param.put("req_hidden_skill_type", "1");
							param.put("req_hidden_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 8) {
							param.put("req_hidden_skill_type", "2");
							param.put("req_hidden_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 9) {
							param.put("req_hidden_skill_type", "3");
							param.put("req_hidden_skill_value", array[x]);
						} else if ((x - keyNamesA.length) == 10) {
							param.put("req_hidden_skill_type", "4");
							param.put("req_hidden_skill_value", array[x]);
						}
					}
				}
				
				paramList.add(param);
			}
		}

		service.insertPlayerSkill(paramList);

		ResultModelAndView rmv = new ResultModelAndView();
		rmv.flush();
		return rmv;
	}

	@RequestMapping("insertPlayerAptitude.do")
    public ModelAndView insertPlayerAptitude() throws Exception {
		final String filePath = "D:/Project/2014Heroes/document/data/player/PlayerAptitude.csv";
		final String[] keyNames = {"player_name", "gk", "sw", "cb", "dm", "cm", "am", "cf", "sb", "wb", "sm", "wf", "ls", "rs"};

		Map<String, String> param;
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
		List<String> list = FileUtils.readLines(new File(filePath));

		for (String line : list) {
			String[] array = StringUtils.split(line, ",");

			param = new HashMap<String, String>();

			for (int x=0; x < keyNames.length; x++) {
				param.put(keyNames[x], array[x]);
			}

			paramList.add(param);
		}

		service.insertPlayerAptitude(paramList);

		ResultModelAndView rmv = new ResultModelAndView();
		rmv.flush();
		return rmv;
	}

	@RequestMapping("insertPlayerLevel.do")
    public ModelAndView insertPlayerLevel() throws Exception {
		final String filePath = "D:/Project/2014Heroes/document/data/player/PlayerLevel.csv";
		final String[] keyNames = {"level", "xp", "hp"};

		Map<String, String> param;
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
		List<String> list = FileUtils.readLines(new File(filePath));

		for (String line : list) {
			String[] array = StringUtils.split(line, ",");

			param = new HashMap<String, String>();

			for (int x=0; x < keyNames.length; x++) {
				param.put(keyNames[x], array[x]);
			}

			paramList.add(param);
		}

		service.insertPlayerLevel(paramList);

		ResultModelAndView rmv = new ResultModelAndView();
		rmv.flush();
		return rmv;
	}

	@RequestMapping("insertGameScene.do")
    public ModelAndView insertGameScene() throws Exception {
		final String filePath = "D:/Project/2014Heroes/document/data/GameScene.csv";
		final String[] keyNames = {"command", "player", "attribute", "location", "value", "text"};

		Map<String, String> param;
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
		List<String> list = FileUtils.readLines(new File(filePath));
		int sceneNo = 0;
		int seqNo = 0;

		for (String line : list) {
			if (line.trim().startsWith("START")) {
				sceneNo++;
				seqNo = 0;
			} else if (!line.trim().startsWith("END") && StringUtils.isNotBlank(line)) {
				seqNo++;
				
				String[] array = StringUtils.splitPreserveAllTokens(line, ",");

				param = new HashMap<String, String>();
				param.put("scene_id", String.valueOf(sceneNo));
				param.put("seq_no", String.valueOf(seqNo));

				for (int x=0; x < keyNames.length; x++) {
					if (x == 1) {
						if (StringUtils.defaultString(array[x]).startsWith("teammate")) {
							param.put(keyNames[x], "TM" + array[x].substring(array[x].indexOf("teammate") + 8));
						} else if (StringUtils.defaultString(array[x]).startsWith("enemy")) {
							param.put(keyNames[x], "EM" + array[x].substring(array[x].indexOf("enemy") + 5));
						} else {
							param.put(keyNames[x], array[x]);
						}
					} else if (x == 2) {
						if (StringUtils.defaultString(array[x]).indexOf("����") != -1) {
							param.put("attribute_type", "1");
							param.put(keyNames[x], array[x].substring(0, array[x].indexOf("����")));
						} else if (StringUtils.defaultString(array[x]).indexOf("���") != -1) {
							param.put("attribute_type", "2");
							param.put(keyNames[x], array[x].substring(0, array[x].indexOf("���")));
						} else {
							param.put("attribute_type", "0");
							param.put(keyNames[x], array[x]);
						}
					} else if (x == 3) {
						if (StringUtils.defaultString(array[x]).trim().equalsIgnoreCase("L")) {
							param.put(keyNames[x], "LEFT");
						} else if (StringUtils.defaultString(array[x]).trim().equalsIgnoreCase("R")) {
							param.put(keyNames[x], "RIGHT");
						} else if (StringUtils.defaultString(array[x]).trim().equalsIgnoreCase("CL")) {
							param.put(keyNames[x], "CENTER-LEFT");
						} else if (StringUtils.defaultString(array[x]).trim().equalsIgnoreCase("CR")) {
							param.put(keyNames[x], "CENTER-RIGHT");
						} else {
							param.put(keyNames[x], array[x].toUpperCase());
						}
					} else {
						param.put(keyNames[x], array[x]);
					}
				}

				paramList.add(param);
			}
		}

		service.insertGameScene(paramList);

		ResultModelAndView rmv = new ResultModelAndView();
		rmv.flush();
		return rmv;
	}
}
