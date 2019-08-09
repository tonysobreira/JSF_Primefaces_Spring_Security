package com.jsf.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class CryptoUtil {
	
	static String IV = "AAAAAAAAAAAAAAAA";
	public static String chaveEncriptacao = "pcvil@ctit012017";
	
	public static String encryptAES(String textopuro, String chaveencriptacao) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		final byte[] criptografado = encripta.doFinal(textopuro.getBytes("UTF-8"));

		String textoCriptografado = StringUtils.trim(Base64.encodeBase64String(criptografado));

		return textoCriptografado;
	}

}
