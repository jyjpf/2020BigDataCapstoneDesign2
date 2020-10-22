package com.dictation.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseVO {

	private long lecture_no;
	private int course_no;
	private int question_no;
	private String question;
	private String file_nm;
	private String save_file_nm;
	private String open;
	private String input_id;
	private Date input_date;
	private String update_id;
	private Date update_date;

}
