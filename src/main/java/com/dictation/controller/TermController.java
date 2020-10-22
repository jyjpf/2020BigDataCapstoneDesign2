package com.dictation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dictation.service.Term_cdService;
import com.dictation.vo.Term_cdVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/term")
@Secured("ROLE_ADMIN")
public class TermController {

    @Autowired
    private Term_cdService term_cdService;

    @GetMapping
    public Model getTerm(
            @RequestParam String year,
            @RequestParam String term,
            Model model) {

        if (year == null) {
            model.addAttribute("result", term_cdService.getList());
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("year", year);
            params.put("term", term);

            model.addAttribute("result", term_cdService.get(params));
        }

        return model;
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
  @DeleteMapping
  public void delete(
          @RequestParam String year,
          @PathVariable String term) {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("year", year);
    params.put("term", term);

    term_cdService.delete(params);
  }
  */

}