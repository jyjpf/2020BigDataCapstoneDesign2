package com.dictation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dictation.service.SoService;
import com.dictation.vo.SoVO;
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
@RequestMapping(value = "/api/dae/{daeCode}/so")
@Secured("ROLE_ADMIN")
public class SoCodeController {

	@Autowired
  	private SoService soService;
  
	@GetMapping(value="/{soCode}")
	public SoVO get(@PathVariable("daeCode") String daeCode, @PathVariable("soCode") String soCode) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("daeCode", daeCode);
		params.put("soCode", soCode);

		return soService.get(params);
	}

	@GetMapping
	public List<SoVO> getList(@PathVariable("daeCode") String daeCode) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("daeCode", daeCode);

		return soService.getList(params);
	}
	
	@PostMapping
	public void insert(@RequestBody SoVO so, @AuthenticationPrincipal UserVO activeUser) throws Exception {
		soService.insert(so);
	}
	
	@PutMapping
	public void updateDae(@RequestBody SoVO so, @AuthenticationPrincipal UserVO activeUser) throws Exception {
		soService.update(so);
	}

	/*
	@DeleteMapping(value="/{code}")
	public void deleteDae(@PathVariable("code") String code) throws Exception {
		soService.delete(code);
	}
	*/
}