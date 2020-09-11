package com.dictation.controller;


import java.util.List;

import com.dictation.service.AdminService;
import com.dictation.service.Dae_cdService;
import com.dictation.service.So_cdService;
import com.dictation.vo.AdminVO;
import com.dictation.vo.DaeVO;
import com.dictation.vo.So_cdVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

	@Autowired
	private AdminService adminService;

	
	// All queries
	@RequestMapping(value = "/user/list")
	public List<AdminVO> user_list() {
		return adminService.list();
	}
}