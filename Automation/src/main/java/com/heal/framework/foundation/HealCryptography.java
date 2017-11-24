package com.heal.framework.foundation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;



public class HealCryptography {
	private byte[] ivBytes  = new byte[] { 6, 4, 1, 7, 3, 3, 4, 2 };
	
	private String _passPhrase;
	
    public HealCryptography(String passPhrase)
    {
        _passPhrase = passPhrase;
    }
    
    private byte[] GetKey(byte[] key, int minSize)
    {
    	int len = key.length;
        int j =0;
           	
        byte[] keys = new byte[minSize/8];
        for (int i = 0; i < minSize; i += 8)
        {
        	keys[j++] = key[i / 8 % len];
        }
        return keys;
    }

    /**
     * Encrypt string
     * @param property: string to encrypt
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public String encrypt(String property) throws GeneralSecurityException, IOException {
    	return encrypt(property, StandardCharsets.UTF_8, StandardCharsets.UTF_16LE);		 
    }
    
    /**
     * Encrypt string
     * @param property: string to encrypt
     * @param charSet: charset for password and property
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public String encrypt(String property, Charset charSet) throws GeneralSecurityException, IOException {
    	return encrypt(property, charSet, charSet);
    }
        
    
    /**
     * Encrypt string
     * @param property: string to encrypt
     * @param charSetKey: charset for password and property
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public String encrypt(String property, Charset charSetKey, Charset charSetOut) throws GeneralSecurityException, IOException {
    	
    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        KeySpec myKey = new DESKeySpec(GetKey(_passPhrase.toLowerCase().getBytes(charSetKey),64));
        SecretKey key = keyFactory.generateSecret(myKey);
        
        Cipher pbeCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivBytes);   
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        String encoded = Base64.getEncoder().encodeToString(pbeCipher.doFinal(property.getBytes(charSetOut)));
		return encoded;
	}

	/**
     * Decrypt string 
     * @param property: encrypted and base64 encoded string
     * @param charSetKey: Charset for password
     * @param charSetOut: Charset for output String
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public String decrypt(String property, Charset charSetKey, Charset charSetOut) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        KeySpec myKey = new DESKeySpec(GetKey(_passPhrase.toLowerCase().getBytes(charSetKey),64));
        SecretKey key = keyFactory.generateSecret(myKey);
        
        Cipher pbeCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivBytes);

        pbeCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        byte[] buffer = pbeCipher.doFinal(Base64.getDecoder().decode((property)));
        
        return new String(buffer, charSetOut);
    }
    
    /**
     * Decrypt string 
     * @param property: encrypted and base64 encoded string
     * @param charSetKey: Charset for password
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public String decrypt(String property, Charset charSetKey) throws GeneralSecurityException, IOException {
    	return decrypt(property, charSetKey, charSetKey);
    }
    
    /**
     * Decrypt string 
     * @param property: encrypted and base64 encoded string
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public String decrypt(String property) throws GeneralSecurityException, IOException {
    	return decrypt(property, StandardCharsets.UTF_8, StandardCharsets.UTF_16LE);
    }
}
