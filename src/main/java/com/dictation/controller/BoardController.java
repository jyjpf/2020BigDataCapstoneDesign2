package com.dictation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dictation.service.BoardService;
import com.dictation.service.CommentService;
import com.dictation.vo.CourseVO;
import com.dictation.vo.BoardVO;
import com.dictation.vo.CommentVO;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/board")
public class BoardController {

	private static final Logger logger = LogManager.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;

	@PostMapping(produces = "application/json;charset=UTF-8")
	public void insert(@RequestParam Map<String, Object> map, @Param(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {

		// map(BoardVO)
		String board_cd = (String) map.get("board_cd");
		String content = (String) map.get("content");
		String title = (String) map.get("title");
		// file
		String originalfileName = null;
		String save_file_nm = null;

		BoardVO board = new BoardVO();
		board.setBoard_cd(board_cd);
		board.setContent(content);
		board.setTitle(title);

		int lecture_no;
		String so_b = null;
		String no = null;

		// lecture_no
		HttpSession session = request.getSession();
		lecture_no = (int) session.getAttribute("lecture_no");
		board.setLecture_no(lecture_no);

		// board_cd, no
		board.setDae_b("006");
		if (board.getBoard_cd().equals("001")) {
			so_b = "001";
			no = lecture_no + "001";
		} else if (board.getBoard_cd().equals("002")) {
			so_b = "002";
			no = lecture_no + "002";
		} else if (board.getBoard_cd().equals("003")) {
			so_b = "003";
			no = lecture_no + "003";
		}
		board.setSo_b(so_b);
		board.setNo(Long.valueOf(no));

		if (file.isEmpty()) {

		} else {

			originalfileName = file.getOriginalFilename();

			// SAVE_FILE_NM
			UUID uuid = UUID.randomUUID();
			save_file_nm = uuid.toString() + "_" + originalfileName;

			File dest = new File("C:/Temp/" + save_file_nm);
			file.transferTo(dest);

		}

		board.setFile_nm(originalfileName);
		board.setSave_file_nm(save_file_nm);

		boardService.insert(board);
	}

	@PostMapping(value = "/insert_nofile")
	public void insert_nofile(@RequestParam Map<String, Object> map, HttpServletRequest request) throws Exception {

		// map(BoardVO)
		String board_cd = (String) map.get("board_cd");
		String content = (String) map.get("content");
		String title = (String) map.get("title");

		BoardVO board = new BoardVO();
		board.setBoard_cd(board_cd);
		board.setContent(content);
		board.setTitle(title);

		int lecture_no;
		String so_b = null;
		String no = null;

		// lecture_no
		HttpSession session = request.getSession();
		lecture_no = (int) session.getAttribute("lecture_no");
		board.setLecture_no(lecture_no);

		// board_cd, no
		board.setDae_b("006");
		if (board.getBoard_cd().equals("001")) {
			so_b = "001";
			no = lecture_no + "001";
		} else if (board.getBoard_cd().equals("002")) {
			so_b = "002";
			no = lecture_no + "002";
		} else if (board.getBoard_cd().equals("003")) {
			so_b = "003";
			no = lecture_no + "003";
		}
		board.setSo_b(so_b);
		board.setNo(Long.valueOf(no));

		boardService.insert(board);
	}

	@PostMapping(value="/insert_comment")
	public void insert_comment(@RequestBody CommentVO comment, HttpServletRequest request) {
	    
		commentService.insert(comment);
	}

	// according to id delete
	@GetMapping(value = "/delete/{board_cd}/{no}/{seq_no}")
	public void delete(@PathVariable("board_cd") String board_cd, @PathVariable("no") long no,
			@PathVariable("seq_no") int seq_no, HttpServletRequest request) {

		HttpSession session = request.getSession();
		int lecture_session = (int) session.getAttribute("lecture_no");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("lecture_no", lecture_session);
		map.put("board_cd", board_cd);
		map.put("no", no);
		map.put("seq_no", seq_no);
		boardService.delete(map);
		boardService.after_delete(map);
	}

	@PostMapping(value = "/update")
	public void update(@RequestParam Map<String, Object> map, @Param(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		int lecture_session = (int) session.getAttribute("lecture_no");

		String board_cd = (String) map.get("board_cd");
		long no = Long.parseLong((String) map.get("no"));
		int seq_no = Integer.parseInt((String) map.get("seq_no"));
		String content = (String) map.get("content");
		String title = (String) map.get("title");
		String originalfileName = null;
		String save_file_nm = null;

		BoardVO board = new BoardVO();
		board.setLecture_no(lecture_session);
		board.setBoard_cd(board_cd);
		board.setNo(no);
		board.setSeq_no(seq_no);
		board.setContent(content);
		board.setTitle(title);

		if (file.isEmpty()) {

		} else {

			originalfileName = file.getOriginalFilename();

			// SAVE_FILE_NM
			UUID uuid = UUID.randomUUID();
			save_file_nm = uuid.toString() + "_" + originalfileName;

			File dest = new File("C:/Temp/" + save_file_nm);
			file.transferTo(dest);

			String delete_filenm = boardService.getById(board).getSave_file_nm();
			File delete_file = new File("C:/Temp/" + delete_filenm);
			delete_file.delete();
		}

		board.setFile_nm(originalfileName);
		board.setSave_file_nm(save_file_nm);

		boardService.update(board);
	}

	@PostMapping(value = "/update_nofile")
	public void update_nofile(@RequestParam Map<String, Object> map, HttpServletRequest request) {

		HttpSession session = request.getSession();
		int lecture_session = (int) session.getAttribute("lecture_no");

		String board_cd = (String) map.get("board_cd");
		long no = Long.parseLong((String) map.get("no"));
		int seq_no = Integer.parseInt((String) map.get("seq_no"));
		String content = (String) map.get("content");
		String title = (String) map.get("title");

		BoardVO board = new BoardVO();
		board.setLecture_no(lecture_session);
		board.setBoard_cd(board_cd);
		board.setNo(no);
		board.setSeq_no(seq_no);
		board.setContent(content);
		board.setTitle(title);

		boardService.update_nofile(board);
	}

	@GetMapping(value = "/list/{board_cd}")
	public List<BoardVO> list(@PathVariable("board_cd") String board_cd, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int lecture_session = (int) session.getAttribute("lecture_no");

		BoardVO board = new BoardVO();
		board.setBoard_cd(board_cd);
		board.setLecture_no(lecture_session);

		List<BoardVO> board_sort=boardService.list(board);
		for(int i=0; i<board_sort.size(); i++) {
		System.out.println(board_sort.get(i).getSeq_no());
		}

		Collections.sort(board_sort);
		for(int i=0; i<board_sort.size(); i++) {
		System.out.println(board_sort.get(i).getSeq_no());
		}		
		return board_sort;
	}

	@PostMapping("/upload")
	public String uploadToLocalFileSystem(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		// FILE_NM
		String originalfileName = file.getOriginalFilename();
		// SAVE_FILE_NM
		UUID uuid = UUID.randomUUID();
		String save_file_nm = uuid.toString() + "_" + originalfileName;

		String fileDownloadUrl = "C:/Temp/";

		File dest = new File(fileDownloadUrl + save_file_nm);
		file.transferTo(dest);

		return save_file_nm;
	}

	@GetMapping(path = "/download/{save_file_nm}")
	public void download_file(@PathVariable("save_file_nm") String save_file_nm, HttpServletRequest request,
		  HttpServletResponse response) throws IOException {
		String file_path = "C:/Temp/";

		String file_name = save_file_nm;
		String fileUrl = file_path + file_name;

		File file = new File(fileUrl);
		if (!file.exists()) {
			return;
		}

		String origin_file_nm;

		if ((origin_file_nm = boardService.getFileNm(file_name)) == null) {
			origin_file_nm = file_name;
		}

		response.setContentType("application/octer-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + origin_file_nm + "\"");
		try {
			OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(file);

			int ncount = 0;
			byte[] bytes = new byte[512];

			while ((ncount = fis.read(bytes)) != -1) {
				os.write(bytes, 0, ncount);
			}
			fis.close();
			os.close();
		} catch (FileNotFoundException ex) {
			logger.error("FileNotFoundException");
		} catch (IOException ex) {
			logger.error("IOException");
		}
	}
}
