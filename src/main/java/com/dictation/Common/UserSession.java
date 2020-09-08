package com.dictation.Common;

import javax.servlet.http.HttpSession;

import com.dictation.vo.UserVO;

public class UserSession {

	private static String user_id = "user_id";
	private static String school_cd = "school_cd";
	private static String position_cd = "position_cd";
  private static String kor_nm = "kor_nm";
   
  public static void setUserSession(UserVO user, HttpSession session) {
    session.setAttribute(user_id, user.getUser_id());
    session.setAttribute(school_cd, user.getSchool_cd());
    session.setAttribute(position_cd, user.getPosition_cd());
    session.setAttribute(kor_nm, user.getKor_nm());
  }

  public static boolean isEmpty(HttpSession session) {
    return session.getAttribute(user_id) == null ? true : false;
  }

  public static String getUser_id(HttpSession session) {
    return (String) session.getAttribute(user_id);
  }

  public static String getSchool_cd(HttpSession session) {
    return (String) session.getAttribute(school_cd);
  }

  public static String getPosition_cd(HttpSession session) {
    return (String) session.getAttribute(position_cd);
  }

  public static String getKor_nm(HttpSession session) {
    return (String) session.getAttribute(kor_nm);
  }
}
 