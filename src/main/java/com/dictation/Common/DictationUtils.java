package com.dictation.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.util.Date;

public class DictationUtils {

    private static final Logger logger = LogManager.getLogger(DictationUtils.class);

    /**
     * Hash에 날짜데이터 들어감 - 파일전용
     * @param 원본파일명
     * @return Date + MD5해싱된 파일명
     */
    public static String toMD5(String originalFilename) {

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

    /**
     * SHA-256 패스워드
     * @param 원본 패스워드
     * @return SHA-256 해싱된 패스워드
     */
    public static String toSHA256(String password) {

        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes("UTF-8"));

            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }

        } catch (Exception e) {
            logger.error(e);
        }

        return sb.toString();
    }
}
