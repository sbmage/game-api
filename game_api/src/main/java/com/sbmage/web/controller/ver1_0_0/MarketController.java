package com.sbmage.web.controller.ver1_0_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.sbmage.business.MarketService;
import com.sbmage.common.CommonMessage;
import com.sbmage.exception.LogicException;
import com.sbmage.kakao.KakaoConnection;
import com.sbmage.util.Util;
import com.sbmage.web.controller.CommonController;
import com.sbmage.web.model.ResultModelAndView;

@Controller("market_ver1.0.0")
@RequestMapping("/ver1_0_0/market/")
public class MarketController extends CommonController {
	private static final Logger LOGGER = Logger.getLogger(MarketController.class);

	//private static BaseProperty prop = BaseProperty.getInstance();

	private static final String PUBLIC_KEY = ""; //google developer key
	private static final String VERIFICATION_URL_REAL = "https://buy.itunes.apple.com/verifyReceipt";
//	private static final String VERIFICATION_URL_SANDBOX = "https://sandbox.itunes.apple.com/verifyReceipt";

	// kakao에서 제공하는 정보
	private static final String CLIENT_ID = "";
	private static final String SECRET_KEY = "";

	private static final String ANDROID	= "G";
	private static final String IOS		= "A";

	@Autowired
	private MarketService service;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("buySoftcash.do")
    public ModelAndView buySoftcash() throws Exception {
		Map<String, String> param = super.getParameter();

		Map<String, String> resultMap = service.buySoftCash(param);

		if (!resultMap.get("result").equals("0")) {
			if (resultMap.get("result").equals("11")) {
				throw new LogicException(CommonMessage.ERROR_0004[0], CommonMessage.ERROR_0004[1]);
			} else {
				throw new LogicException(CommonMessage.ERROR_0000[0], CommonMessage.ERROR_0000[1]);
			}
		}

		ResultModelAndView rmv = new ResultModelAndView();
		rmv.addSubObject(resultMap);
		rmv.flush();

		return rmv;
	}

	@RequestMapping("buyHardcash.do")
	public ModelAndView create(HttpServletRequest req) throws Exception  {
		Map<String , String> param = super.getParameter();
		Map<String , String> userGoodsMap = new HashMap<String, String>();

		String platform = param.get("platform_id");

		if(platform.equals(IOS)) {
			String receipt	= param.get("receipt");

			final String decodedText = new String(Base64.decodeBase64(receipt.getBytes()));
			final int result = verifyApple(receipt, decodedText);

			// �̻��� ������� 1
			if(result == 1) {
				final String receiptInfo = JSONObject.fromObject(decodedText).getString("purchase-info");
				final String purchaseInfo = new String(Base64.decodeBase64(receiptInfo.getBytes()));

				JSONObject object = JSONObject.fromObject(purchaseInfo);
				Map<String, String> dataMap = Util.jsonToMap(object);

				param.putAll(dataMap);

				param.put("platform_id", "2");
				param.put("product_id", param.get("product-id"));
				param.put("transaction_id", param.get("transaction-id"));
				param.put("purchase_date", param.get("purchase-date"));
				param.put("app_item_id", param.get("item-id"));
				param.put("b_id", param.get("bid"));
				param.put("b_vrs", param.get("bvrs"));

				userGoodsMap = service.buyHardCashForApple(param);
				System.out.println("###" + userGoodsMap.get(RESULT));
				
				if (!userGoodsMap.get(RESULT).equals("0")) {
					if (userGoodsMap.get(RESULT).equals("11")) {
						throw new LogicException(CommonMessage.MARKET_0004[0], CommonMessage.MARKET_0004[1]);
					} else if (userGoodsMap.get(RESULT).equals("12")) {
						throw new LogicException(CommonMessage.MARKET_0003[0], CommonMessage.MARKET_0003[1]);
					} else {
						throw new LogicException(CommonMessage.ERROR_0000[0], CommonMessage.ERROR_0000[1]);
					}
				} else {
					String id = String.valueOf(userGoodsMap.get("id"));

					this.sendKakaoPayment(IOS, userGoodsMap, id);

					ResultModelAndView rmv = new ResultModelAndView();
					rmv.addSubObject("golden_shoes", userGoodsMap.get("golden_shoes"));
					rmv.flush();

					return rmv;
				}
			} else {
				throw new LogicException(CommonMessage.MARKET_0001[0], CommonMessage.MARKET_0001[1]);
			}
		} else if(platform.equals(ANDROID)) {
			String purchaseInfo = param.get("purchaseInfo");
			String signature = param.get("signature");
			PublicKey publickey = generatePublicKey(PUBLIC_KEY);

			final boolean result = verifyAndroidReceiptData(publickey, purchaseInfo, signature);

			if(result == true){
				JSONObject object = JSONObject.fromObject(purchaseInfo);
				Map<String, String> dataMap = Util.jsonToMap(object);

				param.putAll(dataMap);

				param.put("platform_id", "1");
				param.put("order_id", param.get("orderId"));
				param.put("package_name", param.get("packageName"));
				param.put("product_id", param.get("productId"));
				param.put("purchase_time", String.valueOf(param.get("purchaseTime"))); // integer���� ��Ʈ������ ����ó��
				param.put("purchase_state", String.valueOf(param.get("purchaseState")));
				param.put("purchase_token", param.get("purchaseToken"));

				userGoodsMap = service.buyHardCashForGoogle(param);
				System.out.println("###" + userGoodsMap.get(RESULT));

				if (!userGoodsMap.get(RESULT).equals("0")) {
					if (userGoodsMap.get(RESULT).equals("11")) {
						throw new LogicException(CommonMessage.MARKET_0004[0], CommonMessage.MARKET_0004[1]);
					} else if (userGoodsMap.get(RESULT).equals("12")) {
						throw new LogicException(CommonMessage.MARKET_0003[0], CommonMessage.MARKET_0003[1]);
					} else {
						throw new LogicException(CommonMessage.ERROR_0000[0], CommonMessage.ERROR_0000[1]);
					}
				} else {
					String id = String.valueOf(userGoodsMap.get("id"));
					LOGGER.debug("before kakao_id:" + userGoodsMap.get("kakao_id"));
					LOGGER.debug("before buyNo:" + id);
					LOGGER.debug("before price:" + userGoodsMap.get("price"));
					this.sendKakaoPayment(ANDROID, userGoodsMap, id);		

					ResultModelAndView rmv = new ResultModelAndView();
					rmv.addSubObject("golden_shoes", userGoodsMap.get("golden_shoes"));
					rmv.flush();

					return rmv;
				}
			} else {
				throw new LogicException(CommonMessage.MARKET_0001[0], CommonMessage.MARKET_0001[1]);
			}
		}  else {
			throw new LogicException(CommonMessage.RECEIPT_0002[0], CommonMessage.RECEIPT_0002[1]);
		}
	}

	private int verifyApple(final String receipt, final String decodedText) throws IOException {
//		final String returnedString = verifyAppleReceiptData(VERIFICATION_URL_SANDBOX, receipt);
		final String returnedString = verifyAppleReceiptData(VERIFICATION_URL_REAL, receipt);
		final int resultVerify = Integer.parseInt(JSONObject.fromObject(returnedString).getString("status"));

		if (resultVerify != 0) {
			try {
	    	 	if (decodedText != null && decodedText.startsWith("com.urus")) {
	    	 		return -1;
	    	 	}
	     	} catch (Exception base64DecodeException) {
	     		LOGGER.error(base64DecodeException.getMessage());
	     	}

			return 0;
		 }

		 return 1;
	}

	private String verifyAppleReceiptData(final String URL, final String receipt) throws IOException {
		OutputStreamWriter outputStreamWriter = null;
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = null;

		try {
			final String jsonData = "{" +
					"\"receipt-data\" : \"" + receipt + "\"," +
					"}";
			final URL url = new URL(URL);
			final URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(jsonData);
			outputStreamWriter.flush();

			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			stringBuffer = new StringBuffer();

			while((line = bufferedReader.readLine()) != null){
				stringBuffer.append(line);
			}
		} finally {
		    if (outputStreamWriter != null) outputStreamWriter.close();
		    if (bufferedReader != null)	bufferedReader.close();			
		}

		return stringBuffer.toString();
	}

	
	private static PublicKey generatePublicKey(String encodedPublicKey) {
        try {
        	byte[] decodedKey = Base64.decodeBase64(encodedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

	private boolean verifyAndroidReceiptData(final PublicKey publicKey, 
											 final String signedData,
											 final String signature)  throws IOException {
		Signature sig;

		try {
			sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(publicKey);

			sig.update(signedData.getBytes());
								
			if (!sig.verify(Base64.decodeBase64(signature))) {
				return false;
			}

			return true;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage());
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage());
		} catch (SignatureException e) {
			LOGGER.error(e.getMessage());
		}

		return false;
	}

	private void sendKakaoPayment(String section, Map<String, String> paymentInfo, String buyNo) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("buy_no", buyNo);
		param.put("service_user_id", paymentInfo.get("kakao_id"));
		param.put("client_id", CLIENT_ID);
		param.put("secret_key", SECRET_KEY);
	
		if (section == IOS) {
			param.put("platform", "apple");
			param.put("os", "ios");
			param.put("price", paymentInfo.get("price"));
			param.put("currency", "USD");
		} else if (section == ANDROID) {
			param.put("platform", "google");
			param.put("os", "android");
			param.put("price", paymentInfo.get("price"));
			param.put("currency", "KRW");
		}

		LOGGER.debug("kakao_id:" + paymentInfo.get("kakao_id"));
		LOGGER.debug("buyNo:" + buyNo);
		LOGGER.debug("price:" + paymentInfo.get("price"));

		KakaoConnection kc = new KakaoConnection(restTemplate, param);
		kc.start();
	}
}
