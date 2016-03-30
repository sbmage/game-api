<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "org.springframework.web.context.WebApplicationContext" %>
<%@ page import = "org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="com.sbmage.business.BaseDataCacheService"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reload</title>
</head>
<body>

<%
	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());

	try {
		
		BaseDataCacheService cacheData = (BaseDataCacheService) wac.getBean("baseDataCacheService");
		cacheData.cacheAllBasicData();
		out.println("Y");
		
	} catch (Exception e) {
		System.out.println(e);
		out.println("N");
	}
%>

</body>
</html>