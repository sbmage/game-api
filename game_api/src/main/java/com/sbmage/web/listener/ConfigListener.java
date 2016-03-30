package com.sbmage.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sbmage.business.BaseDataCacheService;
import com.sbmage.cache.BaseDataCache;

@WebListener
public class ConfigListener implements ServletContextListener {
    public ConfigListener() {}

    public void contextInitialized(ServletContextEvent event) {
    	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());    	

    	try {
			BaseDataCacheService cacheData = (BaseDataCacheService) wac.getBean("baseDataCacheService");
			cacheData.cacheAllBasicData();

    		Map<String, String> serverConfig = new HashMap<String, String>();
    		serverConfig.put("init_server_day", "3");
    		serverConfig.put("init_server_hour", "0");
    		serverConfig.put("init_server_minute", "0");

    		BaseDataCache.getInstance().setServerConfig(serverConfig);

    		//this.loadConfigFile(event.getServletContext());
		} catch (Exception e) {
			System.out.println(e);
		}

    	event.getServletContext().log("[MyServletContextListener] Ready to load all cache data.");
    }

    public void contextDestroyed(ServletContextEvent event) {
    	event.getServletContext().log("[MyServletContextListener] All cache data loaded.");
    }
/*
    private void loadConfigFile(ServletContext context) throws IOException {
		String configLocation = context.getRealPath(context.getInitParameter("ConfigLocation"));
		Properties properties = new Properties();
		properties.load(new FileInputStream(configLocation));

		BaseProperty.getInstance().setProperty(properties);
	}
*/
}
