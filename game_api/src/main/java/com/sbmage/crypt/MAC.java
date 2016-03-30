package com.sbmage.crypt;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MAC {


    /**
     * algorithm.
     */
    protected static final String ALGORITHM = "HmacSHA512";


    /**
     *
     * @param key
     * @param input
     * @return byte[]
     * @throws InvalidKeyException
     * @throws IOException
     */
    public static byte[] generate(final byte[] key, final InputStream input)
        throws InvalidKeyException, IOException {

        if (key == null) {
            throw new NullPointerException("null key");
        }

        if (input == null) {
            throw new NullPointerException("null input");
        }

        try {
            final Mac mac = Mac.getInstance(ALGORITHM);

            mac.init(new SecretKeySpec(key, ALGORITHM));

            final byte[] buf = new byte[1024];

            for (int len = -1; (len = input.read(buf)) != -1;) {
                mac.update(buf, 0, len);
            }

            return mac.doFinal();

        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }


    /**
     * Generates mac value.
     *
     * @param key key
     * @param input input
     * @return output
     * @throws InvalidKeyException 
     */
    public static byte[] generate(final byte[] key, final byte[] input)
        throws InvalidKeyException {

        try {
            final Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(key, ALGORITHM));
            return mac.doFinal(input);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }


    /**
     *
     */
    protected MAC() {
        super();
    }
}
