package com.dictation.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
	private int grade;
	private String ban;
	private String bunho;
	private Date birth_dt;
	private String cel_phone_no;
	private String gender_cd;
	private String email;
	private String login_date;
	private String logout_date;

	private String input_id;
	private Date input_dt;
	private String update_id;
	private Date update_dt;
	
}
