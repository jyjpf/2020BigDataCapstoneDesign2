package com.dictation.controller.dictation;

import java.io.*;
import java.util.*;

import com.dictation.Common.Code;
import com.dictation.Common.DictationUtils;
import com.dictation.service.*;
import com.dictation.vo.CourseVO;

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
@RequestMapping(value = "/api/lecture/{lecture_no}/course")
public class CourseController {

	private static final Logger logger = LogManager.getLogger(CourseController.class);
	private static final String FILEPATH = "/home/dictation/audio/";

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudyService studyService;


	@GetMapping(value = "/{course_no}")
	public List<CourseVO> getCourse(
			@PathVariable("lecture_no") long lecture_no,
			@PathVariable("course_no") int course_no,
			@RequestParam(value = "result", required = false, defaultValue = "false") String result,
			@AuthenticationPrincipal UserVO activeUser) {

		Map<String, Object> params = new HashMap<>();

		params.put("lecture_no", lecture_no);
		params.put("course_no", course_no);

		if (activeUser.getPosition_cd().equals(Code.ROLE_TEACHER)) {
			return courseService.getTeacherList(params);
		} else {
			if (result.equals("true")) {
				params.put("student_id", activeUser.getUser_id());
				return studyService.getNextSequence(params) > 0 ? courseService.getTeacherList(params) : null;
			} else {
				params.put("user_id", activeUser.getUser_id());
				return courseService.getStudentList(params);
			}
		}
	}

	@PostMapping
	@Secured("ROLE_TEACHER")
	public void insert(
			@ModelAttribute CourseVO course,
			@PathVariable("lecture_no") long lecture_no,
			@Param("file") MultipartFile file,
			@AuthenticationPrincipal UserVO activeUser) throws Exception {

		course.setLecture_no(lecture_no);
		course.setInput_id(activeUser.getUser_id());
		course.setUpdate_id(activeUser.getUser_id());

		if (file != null) {
			String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());
			course.setFile_nm(file.getOriginalFilename());
			course.setSave_file_nm(saveFilename);
			FileOutputStream fos = new FileOutputStream(FILEPATH + saveFilename);
			fos.write(file.getBytes());
			fos.close();
		}

		courseService.insert(course);
	}

	// Update시 삭제 구현?
	@PutMapping
	@Secured("ROLE_TEACHER")
	public void update(
			@ModelAttribute CourseVO course,
			@PathVariable(value="lecture_no") long lecture_no,
			@Param(value = "file") MultipartFile file,
			@AuthenticationPrincipal UserVO activeUser) throws Exception {

		course.setLecture_no(lecture_no);
		course.setInput_id(activeUser.getUser_id());
		course.setUpdate_id(activeUser.getUser_id());

		if (file != null) {
			String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());
			course.setFile_nm(file.getOriginalFilename());
			course.setSave_file_nm(saveFilename);
			FileOutputStream fos = new FileOutputStream(FILEPATH + saveFilename);
			fos.write(file.getBytes());
			fos.close();
		}

		courseService.update(course);
	}

	@DeleteMapping
	@Secured("ROLE_TEACHER")
	public void delete() throws Exception {

	}

}
