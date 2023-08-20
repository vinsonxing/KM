package com.vinson.study.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generate a keypair from a given privatekey byte array. the generated
 * keypair's private key byte array equals to the one passed in.
 *
 */
public class KeyPairTest {
    private static final Logger logger = LoggerFactory.getLogger(KeyPairTest.class);
    
    public static void main(String[] args) throws Exception {
        KeyPair kp1 = generateSshKeyPair();
        byte[] privateKeyBytes = kp1.getPrivate().getEncoded();
        KeyPair kp2 = generateKeyPairFromAGivenPrivateKey(privateKeyBytes);
        System.out.println("Public keys: " + kp1.getPublic().equals(kp2.getPublic()));
    }
    
    private static KeyPair generateKeyPairFromAGivenPrivateKey(byte[] privateKeyBytes) throws Exception {
        System.out.println("Private key1: " + Base64.getEncoder().encodeToString(privateKeyBytes));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = factory.generatePrivate(spec);
        final RSAPrivateCrtKey privk = (RSAPrivateCrtKey)privateKey;
        
        final RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privk.getModulus(), privk.getPublicExponent());
        final PublicKey myPublicKey = factory.generatePublic(publicKeySpec);

        System.out.println("Private keys " + Arrays.equals(privateKeyBytes, privateKey.getEncoded()));
        return new KeyPair(myPublicKey, privateKey);
    }

    private static KeyPair generateSshKeyPair() throws Exception {
        logger.debug("Generating key pair");
        final KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048, new SecureRandom());
        return gen.generateKeyPair();
    }
}
