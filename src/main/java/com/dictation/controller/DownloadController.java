package com.dictation.controller;

import com.dictation.service.BoardService;
import com.dictation.service.DownloadService;
import com.dictation.vo.BoardVO;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/download")
public class DownloadController {

	private static final Logger logger = LogManager.getLogger(DownloadController.class);
	private static final String AUDIOPATH = "C:/home/dictation/audio/";
	private static final String BOARDPATH = "C:/home/dictation/board/";

	@Autowired
	DownloadService downloadService;

	@GetMapping(path = "/{type}/{hashfilename}")
	public void download_file(
			@PathVariable("type") String type,
			@PathVariable("hashfilename") String hashfilename,
			HttpServletRequest request,
		  	HttpServletResponse response) throws Exception {

		// if(type.equals("audio"))
		File file = new File(AUDIOPATH + hashfilename);

		if (!file.exists()) {
			return;
		}

		String originalFilename = downloadService.getOriginalFilename(type, hashfilename);
		logger.info(originalFilename);
		response.setContentType("audio/x-wav");
//		response.setHeader("Content-Transfer-Encoding", "binary;");
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");

		try {
			OutputStream out = response.getOutputStream();
			FileInputStream fis = new FileInputStream(file);

			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			logger.error(e);
		}
	}

}