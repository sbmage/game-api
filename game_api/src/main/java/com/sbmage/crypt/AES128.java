package com.sbmage.crypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class AES128 {
    private static final String ALGORITHM = "AES";
    private static final String MODE = "CBC";
    private static final String PADDING = "PKCS5Padding";

    /**
     * cipher transformation.
     */
    private static final String TRANSFORMATION =
        ALGORITHM + "/" + MODE + "/" + PADDING;


    /**
     * key size.
     */
    public static final int KEY_SIZE = 128;


    /**
     * key size in bytes.
     */
    public static final int KEY_SIZE_IN_BYTES = KEY_SIZE / 8;


    /**
     * Generates a secret key.
     *
     * @return encoded secret key.
     */
    public static byte[] generateKey() {
        try {
            final KeyGenerator keyGenerator =
                KeyGenerator.getInstance(ALGORITHM);
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }

    /**
     * Processes all bytes from
     * <code>is</code> and writes to
     * <code>os</code>.
     *
     * @param cipher cipher
     * @param source input stream
     * @param target output stream
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static void copy(final Cipher cipher,
    							final InputStream source,
    							final OutputStream target)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

        final byte[] input = new byte[8192];

        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int outputLen;

        for (int inputLen = -1; (inputLen = source.read(input)) != -1;) {
            while (true) {
                try {
                    outputLen = cipher.update(input, 0, inputLen, output);
                    target.write(output, 0, outputLen);
                    break;
                } catch (ShortBufferException sbe) {
                    output = new byte[output.length * 2];
                }
            }
        }

        while (true) {
            try {
                outputLen = cipher.doFinal(output, 0);
                target.write(output, 0, outputLen);
                break;
            } catch (ShortBufferException sbe) {
                output = new byte[output.length * 2];
            }
        }
    }


    /**
     * Decrypts encoded bytes from
     * <code>in</code> and writes decoded bytes to
     * <code>os</code>.
     *
     * @param key secret key
     * @param iv initialization vector
     * @param enctyped input stream for encrypted bytes
     * @param decrypted output stream for decrypted bytes
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static void decrypt(final byte[] key, final byte[] iv,
                               final InputStream enctyped,
                               final OutputStream decrypted)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

        if (key == null) {
            throw new NullPointerException("null key");
        }

        if (iv == null) {
            throw new NullPointerException("null iv");
        }

        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);

            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, ALGORITHM),
                        new IvParameterSpec(iv));

            copy(cipher, enctyped, decrypted);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        } catch (NoSuchPaddingException nspe) {
            throw new RuntimeException(nspe);
        } catch (InvalidKeyException ike) {
            throw new RuntimeException(ike);
        } catch (InvalidAlgorithmParameterException iape) {
            throw new RuntimeException(iape);
        }
    }


    /**
     * Decrypts given
     * <code>encrypted</code>.
     *
     * @param key secret key
     * @param iv initialization vector
     * @param encrypted encrypted bytes
     * @return decrypted bytes
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] decrypt(final byte[] key, final byte[] iv,
                                 final byte[] encrypted)
        throws IllegalBlockSizeException, BadPaddingException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            decrypt(key, iv, new ByteArrayInputStream(encrypted), baos);
            baos.flush();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        return baos.toByteArray();
    }


    public static byte[] decryptFromHEX(final byte[] key, final byte[] iv,
                                        final String encrypted)
        throws IOException, IllegalBlockSizeException, BadPaddingException, Exception {

    	return decrypt(key, iv, Hex.decodeHex(encrypted.toCharArray()));
    }


    /**
     * Encrypts plain bytes from
     * <code>is</code> and writes encrypted bytes to
     * <code>os</code>.
     *
     * @param key secret key
     * @param iv initialization vector
     * @param decrypted input stream for plain bytes
     * @param encrypted output stream for encrypted bytes
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static void encrypt(final byte[] key, final byte[] iv,
                               final InputStream decrypted,
                               final OutputStream encrypted)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

        if (key == null) {
            throw new NullPointerException("null key");
        }

        if (iv == null) {
            throw new NullPointerException("null iv");
        }

        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, ALGORITHM),
                        new IvParameterSpec(iv));
            copy(cipher, decrypted, encrypted);
        } catch (NoSuchAlgorithmException iae) {
            throw new RuntimeException(iae);
        } catch (NoSuchPaddingException ipe) {
            throw new RuntimeException(ipe);
        } catch (InvalidKeyException ike) {
            throw new RuntimeException(ike);
        } catch (InvalidAlgorithmParameterException iape) {
            throw new RuntimeException(iape);
        }
    }


    /**
     * Encrypts given
     * <code>plain</code> bytes.
     *
     * @param key secret key
     * @param iv initialization vector
     * @param decrypted plain bytes to encrypt
     * @return encrypted bytes
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encrypt(final byte[] key,
    								final byte[] iv,
    								final byte[] decrypted)
        throws IllegalBlockSizeException, BadPaddingException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            encrypt(key, iv, new ByteArrayInputStream(decrypted), baos);
            baos.flush();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        return baos.toByteArray();
    }


    public static String encryptToHEX(final byte[] key,
    									final byte[] iv,
    									final byte[] decrypted)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

    	return Hex.encodeHexString(encrypt(key, iv, decrypted));
    }


    /**
     * Creates a new instance.
     */
    protected AES128() {
        super();
    }
}
