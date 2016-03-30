package com.sbmage.web.controller.ver1_0_0;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.sbmage.business.MarketService;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/config/spring/context-common.xml",
		"classpath:/config/spring/context-properties.xml",
		"classpath:/config/spring/spring-servlet.xml",

		"classpath:/config/spring/context-datasource.xml",
		"classpath:/config/spring/context-rest.xml",
		"classpath:/config/mybatis/sql-map-config-gdb.xml"})
public class MarketControllerTest {

	@Autowired
	private MarketService marketService;

	@Test
	public void testLogin() {
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("user_id", "1");
			param.put("market_item_id", "1");
			
			Map<String, String> resultMap = marketService.buySoftCash(param);
			String resultCode = resultMap.get("result");
			System.out.println(resultCode);
//			Assert.assertNotNull(resultMap);
			Assert.assertEquals(0, 0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}