package com.sbmage.crypt;

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

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class Verify {
	private static final Logger logger = Logger.getLogger(Verify.class);

	private static Verify instance = null;

	private static String KEY_FACTORY_ALGORITHM = "RSA";
	private static String SIGNATURE_ALGORITHM = "SHA1withRSA";

	static {
		instance = new Verify();
	}

	private Verify() {}

	public static Verify getInstance() {
		return instance;
	}

	public boolean verifyAndroidReceiptData(final PublicKey publicKey, 
			 final String signedData,
			 final String signature)  throws IOException {
		Signature sig;
		
		try {
			sig = Signature.getInstance(SIGNATURE_ALGORITHM);
			sig.initVerify(publicKey);
			sig.update(signedData.getBytes());

			if (!sig.verify(Base64.decodeBase64(signature))) {
				return false;
			}

			return true;
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage());
		} catch (SignatureException e) {
			logger.error(e.getMessage());
		}

		return false;
	}

	public int verifyApple(final String receipt, final String decodedText, final String verification_url) throws IOException {
		String reciptData = verifyAppleReceiptData(verification_url, receipt);
		final int resultVerify = Integer.parseInt(JSONObject.fromObject(reciptData).getString("status"));

		if (resultVerify != 0) {
		     // urus hacking check.
			try {
	    	 	if (decodedText != null && decodedText.startsWith("com.urus")) {
	    	 		return -1;
	    	 	}
	     	} catch (Exception base64DecodeException) {
	     		logger.error(base64DecodeException.getMessage());
	     	}

			return 0;
		 }

		return 1;
	}
	
	public String verifyAppleReceiptData(final String URL, final String receipt) throws IOException {
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

	public static PublicKey generatePublicKey(String encodedPublicKey) {
        try {
            byte[] decodedKey = Base64.decodeBase64(encodedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);

            return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
