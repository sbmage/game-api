<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.sbmage.common.CommonConfig" %>
<%@ page import="com.sbmage.util.Util" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="net.sf.json.JSONArray" %>

<%
	String[] paramName = request.getParameterValues("paramName");
	String[] paramValue = request.getParameterValues("paramValue");

	JSONObject body = new JSONObject();
	JSONArray array;
	String encYn = "";

	for (int i=0; i < paramName.length; i++) {
		if (StringUtils.isNotBlank(paramName[i]) && !paramName[i].equals("encYn")) {
			if (body.containsKey(paramName[i])) {
				if (body.get(paramName[i]) instanceof String) {
					array = new JSONArray();
					array.add(body.get(paramName[i]));
					array.add(paramValue[i]);
				} else if (body.get(paramName[i]) instanceof JSONArray) {
					array = body.getJSONArray(paramName[i]);
					array.add(paramValue[i]);
				}
			} else {
				body.put(paramName[i], paramValue[i]);
			}
		} else if (paramName[i].equals("encYn")) {
			encYn = paramValue[i];
		}
	}

	String encBody = Util.encrypt(CommonConfig.INIT, body.toString(), CommonConfig.DEFAULT_KEY);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Sbmage </title>

<style>
.header {
	width:100px;
	text-align:center;
}
</style>

<script language="javascript">
function init() {
	var form = document.forms[0];

	form.action = "<%= request.getParameter("url") %>";
	form.submit();
}

</script>
</head>

<body onload="init();">
<form name="Sbmage" method="post">

<input type="hidden" name="encYn" value="<%= encYn %>">
<input type="hidden" name="body" value="<%= encBody %>">

</form>
</body>
</html>
