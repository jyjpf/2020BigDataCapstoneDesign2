package com.dictation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dictation.service.LectureService;
import com.dictation.vo.LectureVO;
import com.dictation.vo.UserVO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	private static final Logger logger = LogManager.getLogger(BoardController.class);

  	@Autowired
	private LectureService lectureService;

  	// TODO: QueryString 추가
	@GetMapping(value="/{lecture_no}")
	public LectureVO get(
			@PathVariable("lecture_no") String lecture_no,
			@AuthenticationPrincipal UserVO activeUser) {
		Map<String, Object> params = new HashMap<>();
		params.put("lecture_no", lecture_no);
		params.put("school_cd", activeUser.getSchool_cd());
		LectureVO lecture = lectureService.get(params);
		return lecture;
	}

	@GetMapping
	public List<LectureVO> getList(@AuthenticationPrincipal UserVO activeUser) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", activeUser.getUser_id());
		params.put("school_cd", activeUser.getSchool_cd());
		return lectureService.getList(params);
	}

	@GetMapping(value="/mylist")
	public List<LectureVO> getMyList(@AuthenticationPrincipal UserVO activeUser) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", activeUser.getUser_id());
		params.put("school_cd", activeUser.getSchool_cd());
		return lectureService.getMyList(params);
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
