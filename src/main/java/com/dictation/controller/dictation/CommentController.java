package com.dictation.controller.dictation;

import com.dictation.Common.DictationUtils;
import com.dictation.service.BoardService;
import com.dictation.service.CommentService;
import com.dictation.vo.BoardVO;
import com.dictation.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture/{lecture_no}/comment")
public class CommentController {

    private static final Logger logger = LogManager.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @PostMapping
    public void insert(
            @RequestBody BoardVO board,
            @PathVariable("lecture_no") long lecture_no,
            @AuthenticationPrincipal UserVO activeUser) throws Exception {

        board.setLecture_no(lecture_no);
        board.setInput_id(activeUser.getUser_id());
        board.setUpdate_id(activeUser.getUser_id());

        commentService.insert(board);
    }


    @PutMapping
    public void update(
            @RequestBody BoardVO board,
            @PathVariable("lecture_no") long lecture_no,
            @AuthenticationPrincipal UserVO activeUser) throws Exception {

        board.setLecture_no(lecture_no);
        board.setInput_id(activeUser.getUser_id());
        board.setUpdate_id(activeUser.getUser_id());

        commentService.update(board);
    }


	@DeleteMapping(value = "/{no}")
	public void delete(
			@PathVariable("lecture_no") String lecture_no,
			@PathVariable("no") String no) throws Exception {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("lecture_no", lecture_no);
		params.put("no", no);

        commentService.delete(params);
	}

}
