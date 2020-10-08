package com.dictation.controller;

import java.util.HashMap;
import java.util.Map;

import com.dictation.Common.Code;
import com.dictation.Security.JWTTokenProvider;
import com.dictation.service.CommonService;
import com.dictation.service.UserService;
import com.dictation.vo.UserVO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/common")
public class CommonController {

	private Logger logger = LogManager.getLogger(CommonController.class);

	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@PostMapping(value = "/login")
	public ResponseEntity<?> signin(@RequestBody Map<String, Object> params) {

		HttpStatus status;
		Map<String, Object> response = new HashMap<String, Object>();
		UserVO user = commonService.login(params);

		if(user == null) {

			status = HttpStatus.FORBIDDEN;
			response.put("msg", "Login Failed");

		} else { 

			status = HttpStatus.OK;

			response.put("token", jwtTokenProvider.createToken(
				user.getUser_id(), 
				user.getSchool_cd(), 
				user.getPosition_cd())
			);

			if(user.getPosition_cd().equals(Code.ROLE_ADMIN)) {
				response.put("role", "ADMIN");
			} else if(user.getPosition_cd().equals(Code.ROLE_TEACHER)) {
				response.put("role", "TEACHER");
			} else if(user.getPosition_cd().equals(Code.ROLE_STUDENT)) {
				response.put("role", "STUDENT");
			} else {
				status = HttpStatus.NOT_FOUND; 
				response.put("msg", "Undefined Position");
			}
			
		}

		return ResponseEntity.status(status).body(response);

	}

	// TODO: 비밀번호 암호화
	@PostMapping(value = "/signup")
	public ResponseEntity<?> signup(@RequestBody UserVO user) {

		HttpStatus status;
		Map<String, Object> result = new HashMap<String, Object>();

		logger.info(user.getPosition_cd());
		try {
			userService.insert(user);
			status = HttpStatus.OK;
			result.put("msg", "");
		} catch(Exception e) {
			result.put("msg", e);
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status).body(result);
	}

}
