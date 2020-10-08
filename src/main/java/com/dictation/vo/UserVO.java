package com.dictation.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
	
	/* -------TOKEN PAYLOAD -------- */
	private String user_id;
	private String school_cd;
	private String position_cd;
	/* ----------------------------- */
	
	private String pw;
	private String kor_nm;
	private String end_nm; 
	private int grade;
	private String ban;
	private String birth_dt;
	private String cel_phone_no;
	private String hom_phone_no; 
	private String gender_cd;
	private String email; 
	private String register_dt;
	private String login_date;
	private String logout_date;
	
}
