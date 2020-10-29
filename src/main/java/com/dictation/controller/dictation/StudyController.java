package com.dictation.controller.dictation;

import com.dictation.service.StudyService;
import com.dictation.vo.StudyVO;
import com.dictation.vo.UserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture/{lecture_no}/study")
public class StudyController {

	private static final Logger logger = LogManager.getLogger(StudyController.class);

	@Autowired
	private StudyService studyService;

	@GetMapping(value = "/{course_no}")
	public int getSequence(
			@PathVariable("lecture_no") long lecture_no,
			@PathVariable("course_no") int course_no,
			@AuthenticationPrincipal UserVO activeUser) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("lecture_no", lecture_no);
		params.put("course_no", course_no);
		params.put("student_id", activeUser.getUser_id());

		return studyService.getSequence(params);
	}

	@PostMapping
	public void insert(
			@RequestBody StudyVO study,
			@PathVariable("lecture_no") long lecture_no,
			@AuthenticationPrincipal UserVO activeUser) throws Exception {

		study.setLecture_no(lecture_no);
		study.setStudent_id(activeUser.getUser_id());
		study.setInput_id(activeUser.getUser_id());
		study.setUpdate_id(activeUser.getUser_id());

		studyService.insert(study);
	}

}
