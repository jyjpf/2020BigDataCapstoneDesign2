package com.dictation.controller;

import javax.servlet.http.HttpSession;

import com.dictation.Common.UserSession;
import com.dictation.service.UserService;
import com.dictation.vo.UserVO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value="/api/user")
public class UserController {

  private Logger logger = LogManager.getLogger(CommonController.class);
  
  @Autowired
  private UserService userService;

  @GetMapping(value = "/{id}")
	public UserVO get(@PathVariable String id, HttpSession session) {
    UserVO user;
    if(id.equals(UserSession.getUser_id(session))) {
      user = userService.get(id);
    } else {
      user = null;
    }
		return user;
  }

	@PostMapping
	public void insert(@RequestBody UserVO user) throws Exception {
    userService.insert(user);
	}
	
	@PutMapping
	public void update(@RequestBody UserVO user, HttpSession session) throws Exception {
    String id = UserSession.getUser_id(session);
    
    user.setUser_id(id);

		userService.update(user);
  }

}
