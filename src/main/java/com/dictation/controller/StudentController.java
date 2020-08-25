package com.dictation.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dictation.service.EnrollService;
import com.dictation.service.LectureService;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.LectureVO;
import com.dictation.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/student")
public class StudentController {

	@Autowired
	private EnrollService enrollService;
	@Autowired
	private LectureService lectureService;

	@GetMapping(value="/enroll/insert/{lecture_no}")
	public void insert_student(@PathVariable("lecture_no") int lecture_no, HttpServletRequest request) {
		EnrollVO enroll = new EnrollVO();
		enroll.setLecture_no(lecture_no);
		enroll.setApproval_cd("�̽���");
		
		//user_id
		HttpSession session = request.getSession();
		UserVO user_session=(UserVO)session.getAttribute("user");
		enroll.setUser_id(user_session.getUser_id());
		
		enrollService.insert(enroll);
	}
	
	@GetMapping(value="/enroll/delete/{lecture_no}")
	public void delete_student(@PathVariable("lecture_no") int lecture_no, HttpServletRequest request) {
		EnrollVO enroll = new EnrollVO();
		enroll.setLecture_no(lecture_no);
		
		HttpSession session = request.getSession();
		UserVO user_session=(UserVO)session.getAttribute("user");
		enroll.setUser_id(user_session.getUser_id());
		
		enrollService.delete(enroll);
	}
	
	@RequestMapping(value="/lecture/student_lec_list")
	public List<LectureVO> student_lec_list(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UserVO user_session=(UserVO)session.getAttribute("user");
	
		System.out.println("student_lec_list���� user_id ���ǰ� : "+user_session.getUser_id());
				
		return lectureService.student_lec_list(user_session.getUser_id());
	}

	@RequestMapping(value="/lecture/student_mylec")
	public List<LectureVO> student_mylec(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UserVO user_session=(UserVO)session.getAttribute("user");
	
		System.out.println("student_mylec���� user_id ���ǰ� : "+user_session.getUser_id());
				
		return lectureService.student_mylec(user_session.getUser_id());
	}
	

	@GetMapping(value="/enroll/what_pass_course")
	public Integer what_pass_course(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO user_session = (UserVO)session.getAttribute("user");
		int lecture_session=(int)session.getAttribute("lecture_no");
		
		EnrollVO enroll=new EnrollVO();
		enroll.setUser_id(user_session.getUser_id());
		enroll.setLecture_no(lecture_session);
		
		return enrollService.what_pass_course(enroll);
	}
	

	@GetMapping(value="/enroll/update/{course_step}")
	public void enroll_update(@PathVariable("course_step") int course_step, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO user_session = (UserVO)session.getAttribute("user");
		int lecture_session=(int)session.getAttribute("lecture_no");
		Integer pass_course;//����� �ܰ��ȣ
		
		EnrollVO enroll=new EnrollVO();
		enroll.setUser_id(user_session.getUser_id());
		enroll.setLecture_no(lecture_session);
		pass_course=enrollService.what_pass_course(enroll);
		
		if(pass_course<course_step || pass_course==null) {
			enroll.setPass_course_no(course_step);
			enrollService.update(enroll);
		}
	}
}
