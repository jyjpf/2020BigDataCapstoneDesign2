package com.dictation.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LectureVO {
	
	private String year;
	private String term;
	private int lecture_no;
	private String lecture_nm;
	private String school_cd;
	private String lecture_type_cd;
	private int level;
	private int grade;
	private String ban;
	private String enroll_st_dt;
	private String enroll_ed_dt;
	private int max_cnt;
	private String teacher_id;
	private String teacher_nm;
	private String input_id;
	private Date input_date;
	private String update_id;
	private Date update_date;
	
} 
 