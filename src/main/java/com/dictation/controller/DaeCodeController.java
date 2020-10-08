package com.dictation.controller;

import java.util.List;

import com.dictation.service.DaeService;
import com.dictation.vo.DaeVO;
import com.dictation.vo.UserVO;

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
@RequestMapping(value = "/api/dae")
@Secured("ROLE_ADMIN")
public class DaeCodeController {

	@Autowired
  	private DaeService daeService;
  
	@GetMapping(value="/{code}")
	public DaeVO get(@PathVariable("code") String code) {
		return daeService.get(code);
	}

	@GetMapping
	public List<DaeVO> getList() {
		return daeService.getList();
	}
	
	@PostMapping
	public void insert(@RequestBody DaeVO dae, @AuthenticationPrincipal UserVO activeUser) throws Exception {
		dae.setInput_id(activeUser.getUser_id());
		dae.setUpdate_id(activeUser.getUser_id());
		daeService.insert(dae);
	}
	
	@PutMapping
	public void updateDae(@RequestBody DaeVO dae, @AuthenticationPrincipal UserVO activeUser) throws Exception {
		dae.setUpdate_id(activeUser.getUser_id());
		daeService.update(dae);
	}

	/*
	@DeleteMapping(value="/{code}")
	public void deleteDae(@PathVariable("code") String code) throws Exception {
		daeService.delete(code);
	}
	*/

}