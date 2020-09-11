package com.dictation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dictation.service.BoardService;
import com.dictation.service.CourseService;
import com.dictation.service.EnrollService;
import com.dictation.service.LectureService;
import com.dictation.vo.LectureVO;
import com.dictation.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture")
public class LectureController {

  @Autowired
	private LectureService lectureService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private EnrollService enrollService;
	@Autowired
	private CourseService courseService;
 
	@GetMapping(value="/{lecture_no}")
	public LectureVO get(@PathVariable("lecture_no") String lecture_no, @AuthenticationPrincipal UserVO activeUser) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lecture_no", lecture_no);
		params.put("school_cd", activeUser.getSchool_cd());
		LectureVO lecture = lectureService.get(params);
		return lecture;
	}
	
	@GetMapping
	public List<LectureVO> getList(@AuthenticationPrincipal UserVO activeUser) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("school_cd", activeUser.getSchool_cd());
		return lectureService.list(params);
	}
  
	@PostMapping
	@Secured({"ROLE_TEACHER", "ROLE_ADMIN"})
	public void insert(@RequestBody LectureVO lecture, @AuthenticationPrincipal UserVO activeUser) throws Exception {
		lecture.setTeacher_id(activeUser.getUser_id());
		lecture.setSchool_cd(activeUser.getSchool_cd());
		lectureService.insert(lecture);
  }
	
	@PutMapping
	@Secured({"ROLE_TEACHER", "ROLE_ADMIN"})
	public void update(@RequestBody LectureVO lecture, @AuthenticationPrincipal UserVO activeUser) throws Exception {
		lecture.setTeacher_id(activeUser.getUser_id());
		lecture.setSchool_cd(activeUser.getSchool_cd());
		lectureService.update(lecture);
	}

	// TODO: Lecture 삭제 구현
	// @DeleteMapping(value="/{lecture_no}")
	// @Secured({"ROLE_TEACHER", "ROLE_ADMIN"})
	// public void delete(@PathVariable("lecture_no") String lecture_no, @AuthenticationPrincipal UserVO activeUser) throws Exception {

	// 	Map<String, Object> params = new HashMap<String, Object>();

	// 	params.put("teacher_id", activeUser.getUser_id());

	// 	boardService.lecture_delete(lecture_no);
	// 	enrollService.lecture_delete(lecture_no);
	// 	courseService.lecture_delete(lecture_no);
	// 	lectureService.delete(lecture_no);
	// }

}
