package com.dictation.Common;

import com.dictation.controller.BoardController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;

public class DictationUtils {

    private static final Logger logger = LogManager.getLogger(DictationUtils.class);

    // TODO: 현재시각 추가
    public static String fileNameToHash(String originalFilename) {

        StringBuilder sb = new StringBuilder();

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
