package com.dictation.controller.dictation;

import java.io.*;
import java.util.*;

import com.dictation.Common.DictationUtils;
import com.dictation.service.*;
import com.dictation.vo.CourseVO;


import com.dictation.vo.StudyVO;
import com.dictation.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture/{lecture_no}/dictation")
public class DictationController {

	private static final Logger logger = LogManager.getLogger(DictationController.class);
	private static final String FILEPATH = "/home/dictation/audio/";

	@Autowired
	private DictationService dictationService;

	@GetMapping
	public List<CourseVO> getList(
			@PathVariable("lecture_no") long lecture_no,
			@RequestParam(required = false, value = "course_no") int course_no) {

		Map<String, Object> params = new HashMap<>();
		params.put("lecture_no", lecture_no);
		params.put("course_no", course_no);

		return dictationService.get(params);
	}

	@PostMapping
	@Secured("ROLE_TEACHER")
	public void insertQuestion(
			@RequestParam CourseVO course,
			@PathVariable("lecture_no") long lecture_no,
			@Param("file") MultipartFile file,
			@AuthenticationPrincipal UserVO activeUser) throws Exception {

		course.setLecture_no(lecture_no);
		course.setInput_id(activeUser.getUser_id());
		course.setUpdate_id(activeUser.getUser_id());

		if (!file.isEmpty()) {
			String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());
			course.setFile_nm(file.getOriginalFilename());
			course.setSave_file_nm(saveFilename);
			FileOutputStream fos = new FileOutputStream(FILEPATH + saveFilename);
			fos.write(file.getBytes());
			fos.close();
		}

		dictationService.insertQuestion(course);
	}

	@PostMapping(value = "/{course_no}")
	@Secured("ROLE_STUDENT")
	public void insertAnswer(
			@RequestParam StudyVO study,
			@PathVariable("lecture_no") long lecture_no,
			@PathVariable("course_no") int course_no,
			@AuthenticationPrincipal UserVO activeUser) throws Exception {

		study.setLecture_no(lecture_no);
		study.setInput_id(activeUser.getUser_id());
		study.setUpdate_id(activeUser.getUser_id());

		dictationService.insertAnswer(study);
	}

	// Update시 삭제 구현?
	@PutMapping
	@Secured("ROLE_TEACHER")
	public void update(
			@RequestParam CourseVO course,
			@PathVariable(value="lecture_no") long lecture_no,
			@Param(value = "file") MultipartFile file,
			@AuthenticationPrincipal UserVO activeUser) throws Exception {

		course.setLecture_no(lecture_no);
		course.setInput_id(activeUser.getUser_id());
		course.setUpdate_id(activeUser.getUser_id());

		if (!file.isEmpty()) {
			String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());
			course.setFile_nm(file.getOriginalFilename());
			course.setSave_file_nm(saveFilename);
			FileOutputStream fos = new FileOutputStream(FILEPATH + saveFilename);
			fos.write(file.getBytes());
			fos.close();
		}

		dictationService.update(course);
	}

	@DeleteMapping
	@Secured("ROLE_TEACHER")
	public void delete() throws Exception {

	}

}
