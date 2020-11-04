package com.dictation.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.util.Date;

public class DictationUtils {

    private static final Logger logger = LogManager.getLogger(DictationUtils.class);

    public static String fileNameToHash(String originalFilename) {

        Date date = new Date();
        StringBuilder sb = new StringBuilder();

        originalFilename += date.getTime();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(originalFilename.getBytes("UTF-8"));

            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }

        } catch (Exception e) {
            logger.error(e);
        }

        return sb.toString();
    }
}
