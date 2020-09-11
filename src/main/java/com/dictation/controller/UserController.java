package com.dictation.controller;

import com.dictation.service.UserService;
import com.dictation.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/user") 
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
	public UserVO get(@AuthenticationPrincipal UserVO activeUser) {
    UserVO user = userService.get(activeUser.getUser_id());
    
		return user;
  }
	
  @PutMapping
	public void update(@RequestBody UserVO user, @AuthenticationPrincipal UserVO activeUser) throws Exception {
    user.setUser_id(activeUser.getUser_id());
    userService.update(user);
  }

}
