package com.aivarsd.jwtauth.security.utils;

import org.apache.commons.codec.binary.Base64;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

public class RandomUtil {
    public static String genRandom256CharsAsBase64()
    {
        Random random = ThreadLocalRandom.current();
        byte[] r = new byte[256]; //Means 2048 bit
        random.nextBytes(r);
        String random256CharsAsBase64Str = Base64.encodeBase64String(r);
        System.out.println("Random256CharsAsBase64: "+random256CharsAsBase64Str);
        return random256CharsAsBase64Str;
    }
}
