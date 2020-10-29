package com.dictation.service;

import com.dictation.mapper.DictationDAO;
import com.dictation.mapper.StudyDAO;
import com.dictation.vo.CourseVO;
import com.dictation.vo.StudyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DictationService {
	
	@Autowired
	private DictationDAO dictationDAO;

	public List<CourseVO> getStudentList(Map<String, Object> params) {
		return dictationDAO.getStudentList(params);
	}

	public List<CourseVO> getTeacherList(Map<String, Object> params) {
		return dictationDAO.getTeacherList(params);
	}

	public void insert(CourseVO course) throws Exception {
		dictationDAO.insert(course);
	}

	public void update(CourseVO course) throws Exception {
		dictationDAO.update(course);
	}


}
