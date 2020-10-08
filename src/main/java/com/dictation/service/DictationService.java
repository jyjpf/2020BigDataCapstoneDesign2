package com.dictation.service;

import com.dictation.mapper.BoardMapper;
import com.dictation.mapper.DictationDAO;
import com.dictation.vo.BoardVO;
import com.dictation.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DictationService {
	
	@Autowired
	private DictationDAO dictationDAO;
	

	public List<CourseVO> get(Map<String, Object> params) {
		return dictationDAO.get(params);
	}

	public void insert(CourseVO course) throws Exception {
		dictationDAO.insert(course);
	}

	public void update(CourseVO course) throws Exception {
		dictationDAO.update(course);
	}

}
