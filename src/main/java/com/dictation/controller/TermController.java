package com.dictation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dictation.service.Term_cdService;
import com.dictation.vo.Term_cdVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping(value = "/api/term")
@Secured("ROLE_ADMIN")
public class TermController {

  @Autowired
  private Term_cdService term_cdService;

  @GetMapping(value = "/{year}&{term}")
  public Term_cdVO get(@PathVariable("year") String year, @PathVariable("term") String term) {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("year", year);
    params.put("term", term);

    Term_cdVO term_cd = term_cdService.get(params);

    return term_cd;
  }

  @GetMapping
  public List<Term_cdVO> getList() {
    List<Term_cdVO> list = term_cdService.getList();
    return list;
  }

  @PostMapping
  public void insert(@RequestBody Term_cdVO term_cd) throws Exception {
    term_cdService.insert(term_cd);
  }

  @PutMapping
  public void update(@RequestBody Term_cdVO term_cd) throws Exception {
    term_cdService.update(term_cd);
  }

  /*
  @DeleteMapping(value = "/{year}&{term}")
  public void delete(@PathVariable("year") String year, @PathVariable("term") String term) {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("year", year);
    params.put("term", term);

    term_cdService.delete(params);
  }
  */

}