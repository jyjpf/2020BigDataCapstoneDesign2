package com.dictation.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollVO {

	private long lecture_no;
	private String user_id;
	private Date approval_dt;
	private String approval_cd;
	private int pass_course_no;
	private int study_time;
	private String input_id;
	private Date input_date;
	private String update_id;
	private Date update_date;
	
}
 