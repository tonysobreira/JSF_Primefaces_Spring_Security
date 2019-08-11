package com.jsf.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class CryptoUtil {
	
	static String IV = "AAAAAAAAAAAAAAAA";
	public static String chaveEncriptacao = "pcvil@ctit012017";
	
	public static String encryptAES(String textopuro) {
		
		try {
			SecretKeySpec key = new SecretKeySpec(CryptoUtil.chaveEncriptacao.getBytes("UTF-8"), "AES");
			
			Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			final byte[] criptografado = encripta.doFinal(textopuro.getBytes("UTF-8"));
			
			String textoCriptografado = StringUtils.trim(Base64.encodeBase64String(criptografado));

			return textoCriptografado;
			
		} catch (Exception exception) {
	        throw new RuntimeException(exception);
	    }
		
	}
	
	public static String decryptAES(String textopuro) throws Exception {
	    
		try {
		
			SecretKeySpec key = new SecretKeySpec(CryptoUtil.chaveEncriptacao.getBytes("UTF-8"), "AES");
		    
		    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
		    cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		    final byte[] decriptografado = cipher.doFinal(Base64.decodeBase64(textopuro.getBytes("UTF-8")));
		    
		    String textoDecriptografado = StringUtils.trim(Base64.encodeBase64String(decriptografado));
	
			return textoDecriptografado;
	    
		} catch (Exception exception) {
	        throw new RuntimeException(exception);
	    }
		
	}

}
