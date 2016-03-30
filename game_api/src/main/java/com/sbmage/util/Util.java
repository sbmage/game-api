package com.sbmage.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.sbmage.cache.BaseDataCache;
import com.sbmage.crypt.AES128;

public class Util {
	private static final String[] parsePatterns = {"yyyy/MM/dd",
														"yyyy-MM-dd HH:mm:ss ",
														"yyyy.MM.dd",
														"MM/dd/yyyy",
														"MM-dd-yyyy",
														"MM.dd.yyyy"};

	public static String encrypt(final String iv, final String decData, final String secret)
			throws IllegalBlockSizeException, BadPaddingException, DecoderException, IOException {
        if (decData == null) {
            throw new IllegalArgumentException("null encrypted");
        }

        byte[] ivByteArray = Hex.decodeHex(iv.toCharArray());
		byte[] secretByteArray = Hex.decodeHex(secret.toCharArray());

        return AES128.encryptToHEX(secretByteArray, ivByteArray, decData.getBytes("UTF-8"));
    }

	public static String decrypt(final String iv, final String encData, final String secret)
			throws IllegalBlockSizeException, BadPaddingException, Exception {
        if (encData == null) {
            throw new IllegalArgumentException("null encrypted data.");
        }

        byte[] ivByteArray = Hex.decodeHex(iv.toCharArray());
		byte[] secretByteArray = Hex.decodeHex(secret.toCharArray());

        return new String(AES128.decryptFromHEX(secretByteArray, ivByteArray, encData), "UTF-8");
    }

	public static Map<String, String> jsonToMap(JSONObject object) {
		Map<String, String> map = new HashMap<String, String>();

		Map<String, String> data = (Map<String, String>) JSONObject.toBean(object, java.util.HashMap.class, map);		
		
		return data;
	}

	public static <K, V> JSONObject mapToJsonObject(Map<K, V> map) {
		return JSONObject.fromObject(map);
	}

	public static <K, V> JSONArray listToJsonArray(List<Map<K, V>> list) {
		JSONArray array = new JSONArray();
		JSONObject object = null;

		for (Map<K, V> map : list) {
			object = JSONObject.fromObject(map);

			array.add(object);
		}

		return array;
	}

	public static int getThisWeek() {
		Date targetDate = new Date();

		return getWeekOfYear(targetDate.getTime() / 1000L);
	}

	public static long getInitializeServerTime() {
		final Calendar calendar = Calendar.getInstance();

		int days = 0;

		int initDay = NumberUtils.toInt(BaseDataCache.getInstance().getServerConfig().get("init_server_day"));
		int initHour = NumberUtils.toInt(BaseDataCache.getInstance().getServerConfig().get("init_server_hour"));
		int initMinute = NumberUtils.toInt(BaseDataCache.getInstance().getServerConfig().get("init_server_minute"));
		int currDay = calendar.get(Calendar.DAY_OF_WEEK);
		int currHour = calendar.get(Calendar.HOUR_OF_DAY);
		int currMinutes = calendar.get(Calendar.MINUTE);
		//int currSeconds = calendar.get(Calendar.SECOND);

		if ((initDay < currDay)
				|| (initDay == currDay && initHour < currHour)
				|| (initDay == currDay && initHour == currHour && initMinute < currMinutes)) {
			days = 7 - (currDay - initDay);
		} else {
			days = initDay - currDay;
		}

		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);
		calendar.set(Calendar.HOUR_OF_DAY, initHour);
		calendar.set(Calendar.MINUTE, initMinute);
		calendar.set(Calendar.SECOND, 0);

		return (calendar.getTime().getTime() / 1000);
	}

	public static int getWeekOfYear(long targetTime) {
		final Calendar c = Calendar.getInstance();

		Date targetDate = new Date();
		targetDate.setTime(targetTime * 1000L);

		c.setTime(targetDate);

		int initDay = NumberUtils.toInt(BaseDataCache.getInstance().getServerConfig().get("init_server_day"));
		int initHour = NumberUtils.toInt(BaseDataCache.getInstance().getServerConfig().get("init_server_hour"));
		int initMinute = NumberUtils.toInt(BaseDataCache.getInstance().getServerConfig().get("init_server_minute"));
		int currDay = c.get(Calendar.DAY_OF_WEEK);
		int currHour = c.get(Calendar.HOUR_OF_DAY);
		int currMinute = c.get(Calendar.MINUTE);

		int day = 0;

		if ((initDay == currDay && initHour == currHour && initMinute <= currMinute)
				|| (initDay == currDay && initHour < currHour)
				|| (initDay < currDay)) {
			day = 1;
		}

		return (c.get(Calendar.WEEK_OF_YEAR) + day);
	}

	public static boolean isSameWeek(long targetTime) {
		int weekOfYear = getWeekOfYear(targetTime);
		int thisWeekOfYear = getThisWeek();

		if (weekOfYear == thisWeekOfYear) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSameDate(long targetTime) {
		final Calendar c = Calendar.getInstance();

		Date targetDate = new Date();
		targetDate.setTime(targetTime * 1000L);

		c.setTime(targetDate);

		if (c.get(Calendar.DAY_OF_YEAR) < Calendar.getInstance().get(Calendar.DAY_OF_YEAR)) {
			return false;
		} else {
			return true;
		}
	}

	public static String makeStringParam(Map<String, String> param) {
		StringBuffer paramString = new StringBuffer();

		if (param != null) {
			boolean flag = false;

			for (String key : param.keySet()) {
				if (flag) {
					paramString.append("&");
				} else {
					flag = true;
				}

				paramString.append(key);
				paramString.append("=");
				paramString.append(String.valueOf(param.get(key)));
			}
		}

		return paramString.toString();
	}

	public static Object convertValue(Object object, Class<?> aClass) { 
		FastDateFormat df = FastDateFormat.getInstance(parsePatterns[1]);

		if (aClass == Date.class) {
			String source = ((String[]) object)[0];

			if(source.equals("")) return null;

			try {
				Date transfer = DateUtils.parseDate(source, parsePatterns);
				return transfer;
			} catch (ParseException e) {
				throw new RuntimeException("Cannot convert " + source + " to calendar type");
			}
		} else if (aClass == String.class) {
			Date o = (Date) object;
			return df.format(o);
		}

		return null;
	}

	public static HttpHeaders getHttpHeaderJson() {
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(acceptableMediaTypes);

		return headers;
	}
}
