package com.fms.utils;

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class MD5Util {

	 private static final String PASSWORD_CRYPT_KEY = "kEHrDooxWHCWtfeSxvDvgqZq";
	    /** */
	    /** �����㷨,���� DES,DESede,Blowfish. */
	    private final static String ALGORITHM = "DES";

	    /**
	     * ����
	     * @param data
	     * @return
	     */
	    public final static String decryptData(String data) {
	    	String descString = decryptDataDes(data);
	        if (descString == null) {
	            throw new RuntimeException("���ܳ���");
	        }
	        return descString;
	    }

	    /**
	     * ����
	     * @param data
	     * @return
	     */
	    private final static String decryptDataDes(String data) {
	        try {
	            return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
	        } catch (Exception ex) {
	            Logger.getLogger(MD5Util.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }

	    public final static String encryptData(String data) {
	        if (encryptDataDes(data) == null) {
	            throw new RuntimeException("���ܳ���");
	        }
	        return encryptDataDes(data);
	    }

	    /**
	     * ����DES���ܹ������ݽ��н���.
	     * @param data DES��������
	     * @return ���ؽ��ܺ������
	     * @throws Exception
	     * @author <a href="mailto:xiexingxing1121@126.com">AmigoXie</a>
	     * Creation date: 2007-7-31 - ����12:07:54
	     */
	    private final static String encryptDataDes(String data) {
	        try {
	            return byte2hex(encrypt(data.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
	        } catch (Exception ex) {
	            Logger.getLogger(MD5Util.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }

	    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
	        // DES�㷨Ҫ����һ�������ε������Դ
	        SecureRandom sr = new SecureRandom();
	        // ��ԭʼ�ܳ����ݴ���DESKeySpec����
	        DESKeySpec dks = new DESKeySpec(key);
	        // ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
	        // һ��SecretKey����
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
	        SecretKey securekey = keyFactory.generateSecret(dks);
	        // Cipher����ʵ����ɼ��ܲ���
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        // ���ܳ׳�ʼ��Cipher����
	        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
	        // ���ڣ���ȡ���ݲ�����
	        // ��ʽִ�м��ܲ���
	        return cipher.doFinal(data);
	    }

	    /** */
	    /**
	     * ��ָ����key�����ݽ���DES����.
	     * @param data �����ܵ�����
	     * @param key DES���ܵ�key
	     * @return ����DES���ܺ������
	     * @throws Exception
	     * @author <a href="mailto:xiexingxing1121@126.com">AmigoXie</a>
	     * Creation date: 2007-7-31 - ����12:10:34
	     */
	    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
	        // DES�㷨Ҫ����һ�������ε������Դ
	        SecureRandom sr = new SecureRandom();
	        // ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
	        DESKeySpec dks = new DESKeySpec(key);
	        // ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����
	        // һ��SecretKey����
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
	        SecretKey securekey = keyFactory.generateSecret(dks);
	        // Cipher����ʵ����ɽ��ܲ���
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        // ���ܳ׳�ʼ��Cipher����
	        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
	        // ���ڣ���ȡ���ݲ�����
	        // ��ʽִ�н��ܲ���
	        return cipher.doFinal(data);
	    }

	    public static byte[] hex2byte(byte[] b) {
	        byte[] b2 = new byte[b.length / 2];
	        for (int n = 0; n < b.length; n += 2) {
	            String item = new String(b, n, 2);
	            b2[n / 2] = (byte) Integer.parseInt(item, 16);
	        }
	        return b2;
	    }

	    public static String byte2hex(byte[] b) {
	        String hs = "";
	        String stmp = "";

	        for (int n = 0; n < b.length; n++) {
	            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
	            if (stmp.length() == 1) {
	                hs = hs + "0" + stmp;
	            } else {
	                hs = hs + stmp;
	            }
	        }
	        return hs.toUpperCase();
	    }
}
