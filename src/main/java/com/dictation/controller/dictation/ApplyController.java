package com.dictation.controller.dictation;

import com.dictation.service.ApplyService;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture/{lecture_no}/apply")
@Secured("ROLE_TEACHER")
public class ApplyController {

    private static final Logger logger = LogManager.getLogger(ApplyController.class);

    @Autowired
    private ApplyService applyService;

    @GetMapping
    @Secured("ROLE_TEACHER")
    public List<Map<String, Object>> getList(
            @PathVariable("lecture_no") String lecture_no) {
        Map<String, Object> params = new HashMap<>();
        params.put("lecture_no", lecture_no);

        return applyService.getList(params);
    }

    @PutMapping
    @Secured("ROLE_TEACHER")
    public void update(
            @RequestBody EnrollVO enroll,
            @PathVariable("lecture_no") long lecture_no,
            @AuthenticationPrincipal UserVO user) throws Exception {

        enroll.setLecture_no(lecture_no);
        enroll.setUpdate_id(user.getUser_id());

        applyService.update(enroll);
    }

}
