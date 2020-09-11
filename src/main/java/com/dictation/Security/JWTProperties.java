package com.dictation.Security;

public class JWTProperties {
  public static final String SECRET = "dictation";
  public static final int EXPIRATION_TIME = 24 * 60 * 60 * 1000;
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "X-AUTH-TOKEN";
}   