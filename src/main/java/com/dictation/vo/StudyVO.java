package com.dictation.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyVO {

	private long lecture_no;
	private int course_no;
	private int question_no;
	private int seq_no;
	private String answer;
	private boolean correct_yn;
	private String input_id;
	private Date input_date;
	private String update_id;
	private Date update_date;
	
	
}
 