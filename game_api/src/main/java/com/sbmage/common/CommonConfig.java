package com.sbmage.common;

public interface CommonConfig {
	String INIT = "488772CB54390C558FC350506ED08F39";
	String DEFAULT_KEY = "7FDC0F4DA832A63BA193F2E02EAC1878";

	String PARAMETER = "PARAMETER";
	String PARAMETER_ARRAY = "PARAMETER_ARRAY";
	String ENC_YN = "encYn";
	String BODY = "body";
	String RESULT = "result";
	String RESULT_CODE = "result_code";
	String IS_SUCCESS = "IS_SUCCESS";
	String ERROR_CODE = "ERROR_CODE";
	String ERROR_MSG = "ERROR_MSG";
	
	String JSON_RESULT= "JsonWrite";
	String JSP_JSON_RESULT= "/json/jspJsonReport";
	
	String [] ADMIN_EMAIL = {
		"sbmage@gmail.co.kr" ,
		"sbmage@gmail.co.kr"
	};
	
	String KAKAO_ID = "kakao_id";
	String PERMISSION_LEVEL = "level";
	
	int DEFAULT_LEVEL = 1;
	int MAX_PERMISSION_LEVLE = 100;

	int NORMAL_NOTICE_CODE = 70001;
	int WEB_VIEW_NOTICE_CODE = 70002;
}
