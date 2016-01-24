package com.dev.christopher.events.Hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Christopher on 24/01/2016.
 */
public class Sha512Convert {
    public static String convertByteToHex(byte data[])
    {
        StringBuffer hexData = new StringBuffer();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));
        return hexData.toString();
    }

    public String hash(String textTohash) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(textTohash.getBytes());
        return convertByteToHex(messageDigest.digest());

    }
}
