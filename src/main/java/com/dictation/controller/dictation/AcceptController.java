package com.dictation.controller.dictation;

import com.dictation.service.AcceptService;

import com.dictation.vo.EnrollVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture/{lecture_no}/accept")
@Secured("ROLE_TEACHER")
public class AcceptController {

    private static final Logger logger = LogManager.getLogger(BoardController.class);

    @Autowired
    private AcceptService acceptService;

    @GetMapping
    public List<Map<String, Object>> getList(
            @PathVariable("lecture_no") String lecture_no) {
        Map<String, Object> params = new HashMap<>();
        params.put("lecture_no", lecture_no);

        return acceptService.getList(params);
    }

}
