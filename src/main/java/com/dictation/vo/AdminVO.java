package com.dictation.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminVO {
	
	private String user_id;
	private String school_cd;
	private String position_cd;
	private String kor_nm;
	private String end_nm;
	private int grade;
	private String ban;
	private String birth_dt;
	private String cel_phone_no;
	private String hom_phone_no;
	private String gender_cd; 
	private String email;
	private String loginYn;

	private String register_dt;
	private String approval_cd;
	private String approval_dt;
	private int pass_course_no;
	private int study_time;

	private String dae_p;
	private String so_p;

	private String dae_g;
	private String so_g;

	private String input_id;
		
}
