package com.dictation.service;

import java.util.List;
import java.util.Map;


import com.dictation.mapper.EnrollDAO;
import com.dictation.mapper.LectureDAO;
import com.dictation.vo.LectureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;

@Service
public class EnrollService {
	
	@Autowired
	private EnrollDAO enrollDAO;

	@Autowired
	private LectureDAO lectureDAO;

	/***
	 *
	 * @param user
	 * @return
	 */
	public EnrollVO getEnroll(EnrollVO enroll) {
		return enrollDAO.getEnroll(enroll);
	}

	/***
	 *
	 * @param user
	 * @return
	 */
	public List<Map<String, Object>> getEnrollList(UserVO user) {
		return lectureDAO.getEnrollList(user);
	}

	/***
	 *
	 * @param user
	 * @return
	 */
	public List<Map<String, Object>> getStudentList(long lecture_no) {
		return enrollDAO.getStudentList(lecture_no);
	}


	public void insert(EnrollVO enroll) throws Exception {
		enrollDAO.insert(enroll);
	}

	public void updateStudent(EnrollVO enroll) throws Exception {
		enrollDAO.updateStudent(enroll);
	}

	public void updateTeacher(EnrollVO enroll) throws Exception {
		enrollDAO.updateTeacher(enroll);
	}

	public void delete(Map<String, Object> params) throws Exception {
		enrollDAO.delete(params);
	}
}
