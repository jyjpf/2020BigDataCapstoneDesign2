package com.dictation.controller.dictation;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.dictation.Common.DictationUtils;
import com.dictation.service.BoardService;
import com.dictation.vo.BoardVO;

import com.dictation.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture/{lecture_no}/board")
public class BoardController {

    private static final Logger logger = LogManager.getLogger(BoardController.class);
    private static final String FILEPATH = "/home/dictation/board/";

    @Autowired
    private BoardService boardService;

    // TODO: 권한관리
    @GetMapping(value = "/{no}")
    public List<BoardVO> get(
            @PathVariable("lecture_no") long lecture_no,
            @PathVariable("no") int no) {

        Map<String, Object> params = new HashMap<>();

        params.put("lecture_no", lecture_no);
        params.put("no", no);

        return boardService.get(params);
    }

    @GetMapping
    public List<BoardVO> getList(
            @RequestParam(required = false) String board_cd,
            @PathVariable("lecture_no") long lecture_no) {

        Map<String, Object> params = new HashMap<>();
        params.put("lecture_no", lecture_no);
        params.put("board_cd", board_cd);

        return boardService.getList(params);
    }

    @PostMapping
    public void insert(
            @RequestParam BoardVO board,
            @Param(value = "file") MultipartFile file,
            @PathVariable("lecture_no") long lecture_no,
            @AuthenticationPrincipal UserVO activeUser) throws Exception {

        board.setLecture_no(lecture_no);
        board.setInput_id(activeUser.getUser_id());
        board.setUpdate_id(activeUser.getUser_id());

        if (!file.isEmpty()) {
            String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());
            board.setFile_nm(file.getOriginalFilename());
            board.setSave_file_nm(saveFilename);
            FileOutputStream fos = new FileOutputStream(FILEPATH + saveFilename);
            fos.write(file.getBytes());
            fos.close();
        }
        boardService.insert(board);
    }

    @PutMapping(value = "/{no}")
    public void update(
            @RequestParam BoardVO board,
            @PathVariable("lecture_no") long lecture_no,
            @PathVariable("no") int no,
            @Param(value = "file") MultipartFile file,
            @AuthenticationPrincipal UserVO activeUser) throws Exception {

        board.setLecture_no(lecture_no);
        board.setNo(no);
        board.setInput_id(activeUser.getUser_id());
        board.setUpdate_id(activeUser.getUser_id());

        if (!file.isEmpty()) {
            String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());
            board.setFile_nm(file.getOriginalFilename());
            board.setSave_file_nm(saveFilename);
            FileOutputStream fos = new FileOutputStream(FILEPATH + saveFilename);
            fos.write(file.getBytes());
            fos.close();
        }
        boardService.update(board);
    }

	/*
	@DeleteMapping(value = "/{no}")
	public void delete(
			@PathVariable("lecture_no") String lecture_no,
			@PathVariable("no") String no) throws Exception {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("lecture_no", lecture_no);
		params.put("no", no);

		boardService.delete(params);
	}
	*/
}
