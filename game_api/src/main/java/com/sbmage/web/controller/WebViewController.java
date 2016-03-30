package com.sbmage.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sbmage.business.WebViewService;
import com.sbmage.util.PortalMailSender;

@Controller("webView")
@RequestMapping("/web/")
public class WebViewController {
	@Autowired
	private WebViewService webViewService;

	@Autowired
	private PortalMailSender mailSender;

	@RequestMapping("test.do")
	public String test() {
		return "test";
	}

	@RequestMapping("termA.do")
	public ModelAndView termA() {
		ModelAndView mav = new ModelAndView("termA");
		return mav;
	}

	@RequestMapping("termB.do")
	public ModelAndView termsB() {
		ModelAndView mav = new ModelAndView("termB");
		return mav;
	}

	@RequestMapping("redTimes.do")
	public ModelAndView redTimes() {
		ModelAndView mav = new ModelAndView("redTimes");
		String url = webViewService.getRedTimesUrl();
		mav.addObject("url", url);
		return mav;
	}

	@RequestMapping("customerMain.do")
	public ModelAndView customermain() {
		ModelAndView mav = new ModelAndView("customerMain");
		mav.addObject("faqList", webViewService.getFaq());
		return mav;
	}

	@RequestMapping("news.do")
	public ModelAndView news() {
		ModelAndView mav = new ModelAndView("news");
		List<Map<String, String>> notice = webViewService.getNotice();
		List<Map<String, String>> noticeTop = new ArrayList<Map<String, String>>();
		List<Map<String, String>> games = webViewService.getGames();
		List<Map<String, String>> event = webViewService.getEvent();
		// two
		while (notice.size() > 0) {
			if (noticeTop.size() >= 4) {
				break;
			}
			// pop
			noticeTop.add(notice.remove(0));
		}
		mav.addObject("noticeTopList", noticeTop);
		mav.addObject("noticeRemainList", notice);

		mav.addObject("gamesList", games);
		mav.addObject("eventList", event);
		return mav;
	}

	@RequestMapping("contentDetail.do")
	public ModelAndView contentDetail(@RequestParam Integer contentNo) {
		ModelAndView mav = new ModelAndView("contentDetail");
		// To avoid Null pointer Exception tricky
		if (contentNo == null) {
			contentNo = 0;
		}
		mav.addAllObjects(webViewService.getContent(contentNo));
		return mav;
	}

	@RequestMapping("dailyCheck.do")
	public ModelAndView dailyCheck(@RequestParam Integer userId) {
		ModelAndView mav = new ModelAndView("dailyCheck");

		mav.addObject("userId", userId);

		List<List<Map<String, String>>> list = webViewService.dailyCheck(userId);
		mav.addObject("list", list);

		Integer gotSize = 0;
		for (List<Map<String, String>> innerList : list) {
			for (Map<String, String> map : innerList) {
				if (map.get("checked").equals("1")) {
					gotSize++;
				}
			}
		}
		// System.out.println("list size = "+gotSize);
		mav.addObject("gotSize", gotSize);
		Boolean isCheckedToday = webViewService.isCheckedToday(userId);
		mav.addObject("isCheckedToday", isCheckedToday);
		return mav;
	}

	@RequestMapping("help.do")
	public ModelAndView help() {
		ModelAndView mav = new ModelAndView("help");
		List<Map<String, String>> list = webViewService.getHelp();
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping("coupon.do")
	public ModelAndView coupon(@RequestParam Integer userId) {
		if (userId == null) {
			// TODO: error handling
		}
		System.out.println("::::coupon:userId=" + userId);

		ModelAndView mav = new ModelAndView("coupon");
		mav.addObject("userId", userId);
		return mav;
	}

	@RequestMapping("couponUse.do")
	public ModelAndView couponUse(@RequestParam String userId, @RequestParam String couponKey) {
		if (userId == null) {
			// TODO: error handling
		}
		if (couponKey == null) {
			// TODO: error handling
		}

		Map<String, String> resultMap = webViewService.couponUse(userId, couponKey);

		ModelAndView mav = new ModelAndView("jsonStr");
		mav.addObject("jsonStr", JSONObject.fromObject(resultMap).toString());

		return mav;
	}

	@RequestMapping("dailyCheckUse.do")
	public ModelAndView dailyCheckUse(@RequestParam Integer userId) {
		if (userId == null) {
			userId = 0;
		}

		ModelAndView mav = new ModelAndView("jsonStr");
		Map<String, String> map = webViewService.dailyCheckUse(userId);
		mav.addObject("jsonStr", JSONObject.fromObject(map).toString());
		return mav;
	}

	@RequestMapping("testUrl.do")
	public ModelAndView testUrl() {
		ModelAndView mav = new ModelAndView("testUrl");
		return mav;
	}

	@RequestMapping("customerNormal.do")
	public ModelAndView customerNormal() {
		ModelAndView mav = new ModelAndView("customerNormal");
		return mav;
	}

	@RequestMapping("customerNormalSave.do")
	public ModelAndView customerNormalSave(@RequestParam String kakaoId, @RequestParam String email, @RequestParam String subject, @RequestParam String body,
			@RequestParam String device) {
		ModelAndView mav = new ModelAndView("jsonStr");
		Map<String, String> map = new HashMap<String, String>();
		try {
			webViewService.customerNormalSave(kakaoId, email, subject, body, device);
			map.put("result", "0");
			mailSender.sendToAdministrator(kakaoId, email, subject, body, device, "", "", "", "");

		} catch (PersistenceException e) {
			map.put("result", "1");
		}
		mav.addObject("jsonStr", JSONObject.fromObject(map).toString());
		return mav;
	}

	@RequestMapping("customerRefundSave.do")
	public ModelAndView customerNormalSave(@RequestParam String kakaoId, @RequestParam String email, @RequestParam String subject, @RequestParam String body,
			@RequestParam String device, @RequestParam String storeAccount, @RequestParam String productInfo, @RequestParam String orderDate,
			@RequestParam String store) {
		ModelAndView mav = new ModelAndView("jsonStr");
		Map<String, String> map = new HashMap<String, String>();
		try {
			webViewService.customerRefundSave(kakaoId, email, subject, body, device, storeAccount, productInfo, orderDate, store);
			map.put("result", "0");

			mailSender.sendToAdministrator(kakaoId, email, subject, body, device, store, storeAccount, productInfo, orderDate);

		} catch (PersistenceException e) {
			map.put("result", "1");
		}
		mav.addObject("jsonStr", JSONObject.fromObject(map).toString());
		return mav;
	}

	@RequestMapping("customerRefund.do")
	public ModelAndView customerRefund() {
		ModelAndView mav = new ModelAndView("customerRefund");
		return mav;
	}

	@RequestMapping("cafe.do")
	public ModelAndView cafe() {
		ModelAndView mav = new ModelAndView("cafe");
		return mav;
	}

}
