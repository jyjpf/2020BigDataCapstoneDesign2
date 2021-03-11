package com.dictation.controller.dictation;

import com.dictation.service.StatsService;
import com.dictation.service.StudyService;
import com.dictation.vo.StudyVO;
import com.dictation.vo.UserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture/{lecture_no}/stats")
public class StatsController {

	private static final Logger logger = LogManager.getLogger(StatsController.class);

	@Autowired
	private StatsService statsService;

	@GetMapping(value = "/{course_no}")
	@Secured("ROLE_TEACHER")
	public List<Map<String, Object>> getCourseList(
			@PathVariable("lecture_no") long lecture_no,
			@PathVariable("course_no") int course_no) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("lecture_no", lecture_no);
		params.put("course_no", course_no);

		return statsService.get(params);
	}

	@GetMapping
	@Secured("ROLE_TEACHER")
	public List<Map<String, Object>> getList(
			@PathVariable("lecture_no") long lecture_no) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("lecture_no", lecture_no);

		return statsService.getList(params);
	}

}
