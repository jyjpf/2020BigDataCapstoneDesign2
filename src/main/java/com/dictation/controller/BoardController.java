package com.dictation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dictation.Common.DictationUtils;
import com.dictation.service.BoardService;
import com.dictation.vo.BoardVO;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
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
	private static final String filepath = "./board/";

	@Autowired
	private BoardService boardService;

	// TODO: 권한관리
	@GetMapping(value = "/{no}")
	public BoardVO get(@PathVariable("lecture_no") String lecture_no, @PathVariable("no") String no) {
		Map<String, Object> params = new HashMap<>();
		params.put("lecture_no", lecture_no);
		params.put("no", no);

		return boardService.get(params);		
	}

	@GetMapping
	public List<BoardVO> getList(@PathVariable("lecture_no") String lecture_no) {
		Map<String, Object> params = new HashMap<>();
		params.put("lecture_no", lecture_no);

		return boardService.getList(params);
	}

	@PostMapping
	public void insert(
		@RequestParam Map<String, Object> params, 
		@Param(value = "file") MultipartFile file,
		@PathVariable("lecture_no") String lecture_no) throws Exception {

		BoardVO board = new BoardVO();
		board.setLecture_no(lecture_no);
		board.setBoard_cd((String) params.get("board_cd"));
		board.setContent((String) params.get("content"));
		board.setTitle((String) params.get("title"));

		if (!file.isEmpty()) {
			String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());

			board.setFile_nm(file.getOriginalFilename());
			board.setSave_file_nm(saveFilename);

			File dest = new File(filepath + saveFilename);
			file.transferTo(dest);
		}
		boardService.insert(board);
	}

	@PutMapping
	public void update(@RequestParam Map<String, Object> params, @Param(value = "file") MultipartFile file) throws Exception {

		BoardVO board = new BoardVO();

		board.setBoard_cd((String) params.get("board_cd"));
		board.setNo((String) params.get("no"));
		board.setContent((String) params.get("content"));
		board.setTitle((String) params.get("title"));

		if (!file.isEmpty()) {
			String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());

			board.setFile_nm(file.getOriginalFilename());
			board.setSave_file_nm(saveFilename);

			File dest = new File(filepath + saveFilename);
			file.transferTo(dest);
		}
		boardService.update(board);
	}

	@DeleteMapping(value = "/{no}")
	public void delete(
			@PathVariable("lecture_no") String lecture_no,
			@PathVariable("no") String no) throws Exception {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("lecture_no", lecture_no);
		params.put("no", no);

		boardService.delete(params);
	}

}
