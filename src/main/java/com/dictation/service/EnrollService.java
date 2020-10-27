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
	public List<Map<String, Object>> getEnrollList(UserVO user) {
		return lectureDAO.getEnrollList(user);
	}

	public void insert(EnrollVO enroll) throws Exception {
		enrollDAO.insert(enroll);
	}

	public void delete(Map<String, Object> params) throws Exception {
		enrollDAO.delete(params);
	}
}
