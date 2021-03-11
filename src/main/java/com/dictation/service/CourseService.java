package com.dictation.service;

import com.dictation.mapper.CourseDAO;
import com.dictation.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseService {
	
	@Autowired
	private CourseDAO courseDAO;

	public List<CourseVO> getStudentList(Map<String, Object> params) {
		return courseDAO.getStudentList(params);
	}

	public List<CourseVO> getTeacherList(Map<String, Object> params) {
		return courseDAO.getTeacherList(params);
	}

	public void insert(CourseVO course) throws Exception {
		courseDAO.insert(course);
	}

	public void update(CourseVO course) throws Exception {
		courseDAO.update(course);
	}

}
