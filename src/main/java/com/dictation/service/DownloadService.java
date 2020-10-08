package com.dictation.service;

import com.dictation.mapper.BoardMapper;
import com.dictation.mapper.DownloadDAO;
import com.dictation.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DownloadService {
	
	@Autowired
	private DownloadDAO downloadDAO;

	/**
	 * 해시파일명을 원본파일명으로 바꿔줍니다.
	 * @param type, hashfilename
	 * @return originalFilename
	 */
	public String getOriginalFilename(String type, String hashfilename) {
		if(type.equals("audio")) {
			return downloadDAO.getOriginalFilenameByAudio(hashfilename);
		} else {
			return downloadDAO.getOriginalFilenameByBoard(hashfilename);
		}
	}

}
