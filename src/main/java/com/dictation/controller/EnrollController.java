package com.dictation.controller;

import com.dictation.Common.DictationUtils;
import com.dictation.service.BoardService;
import com.dictation.service.EnrollService;
import com.dictation.service.LectureService;
import com.dictation.vo.BoardVO;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.LectureVO;
import com.dictation.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/enroll")
public class EnrollController {

	private static final Logger logger = LogManager.getLogger(EnrollController.class);

	@Autowired
	private EnrollService enrollService;

	@GetMapping(value = "/mylist")
	public List<EnrollVO> getMyList(@AuthenticationPrincipal UserVO activeUser) {
		return enrollService.getMyList(activeUser);
	}

	@PostMapping
	public void insert(
			@RequestBody EnrollVO enroll,
			@AuthenticationPrincipal UserVO activeUser) {

		enroll.setUser_id(activeUser.getUser_id());
		enroll.setInput_id(activeUser.getUser_id());
		enroll.setUpdate_id(activeUser.getUser_id());

		enrollService.insert(enroll);
	}

	@PutMapping
	public void update(
			@RequestBody EnrollVO enroll,
			@AuthenticationPrincipal UserVO activeUser) {

		enroll.setUser_id(activeUser.getUser_id());
		enroll.setUpdate_id(activeUser.getUser_id());

		enrollService.update(enroll);
	}

}
