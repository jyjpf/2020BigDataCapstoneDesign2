package com.dictation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dictation.service.CodeService;
import com.dictation.service.Dae_cdService;
import com.dictation.service.Term_cdService;
import com.dictation.vo.Dae_cdVO;
import com.dictation.vo.Term_cdVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/code")
@Secured("ROLE_ADMIN")
public class DaeCodeController {

	@Autowired
  private CodeService codeService;
  
  //getbyid
	@GetMapping(value="/dae/{code}")
	public Dae_cdVO getDae(@PathVariable("code") String code) {
		Dae_cdVO dae_cd2 = codeService.getDae(code);
		return dae_cd2;
	}
	
	@PostMapping(value="/dae")
	public void insertDae(@RequestBody Dae_cdVO dae_cd) {
		codeService.insertDae(dae_cd);
	}

	@DeleteMapping(value="/dae/{code}")
	public void deleteDae(@PathVariable("code") String code) {
		codeService.deleteDae(dae_cd);
	}
	
	@PutMapping(value="/dae/update")
	public void updateDae(@RequestBody Dae_cdVO dae_cd) {
		codeService.updateDae(dae_cd);
	}


	
	//All queries
	@PostMapping(value="/dae_cd/list")
	public List<Dae_cdVO> dae_list(){
		return dae_cdService.list();
	}
	
	//insert user
	@PostMapping(produces = "application/json;charset=UTF-8", value="/so_cd")
	public void so_insert(@RequestBody So_cdVO so_cd) {
		so_cdService.insert(so_cd);
	}


     //according to id delete
	@GetMapping(value="/so_cd/delete/{so_cd}")
	public void so_delete(@PathVariable("so_cd") String so_cd) {
		so_cdService.delete(so_cd);
	}
	//modify
	//
	@PostMapping(value="/so_cd/update")
	public void so_update(@RequestBody So_cdVO so_cd) {
		so_cdService.update(so_cd);
	}

	//according to id Query students
	@GetMapping(value="/so_cd/get/{so_cd}")
	public So_cdVO so_getById(@PathVariable("so_cd") String so_cd) {
		So_cdVO so_cd1 = so_cdService.getById(so_cd);
		return so_cd1;
	}

}