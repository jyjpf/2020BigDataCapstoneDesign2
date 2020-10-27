package com.dictation.service;

import com.dictation.mapper.BoardMapper;
import com.dictation.mapper.DictationDAO;
import com.dictation.vo.BoardVO;
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

	public void insertQuestion(CourseVO course) throws Exception {
		dictationDAO.insertQuestion(course);
	}

	public void insertAnswer(StudyVO study) throws Exception {
		dictationDAO.insertAnswer(study);
	}

	public void update(CourseVO course) throws Exception {
		dictationDAO.update(course);
	}


}
