package com.dictation.service;

import com.dictation.mapper.EnrollDAO;
import com.dictation.vo.EnrollVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApplyService {
	
	@Autowired
	private EnrollDAO enrollDAO;

	/**
	 * 내 수강신청 목록을 가져옵니다
	 * @param params - 강의번호
	 * @return List<Enroll> - 수강신청목록
	 */
	public List<Map<String, Object>> getList(Map<String, Object> params) {
		return enrollDAO.getStudentList(params);
	}

	/**
	 * 내 수강신청 목록을 가져옵니다
	 * @param params - 강의번호
	 * @return List<Enroll> - 수강신청목록
	 */
	public void update(EnrollVO enroll) throws Exception {
		enrollDAO.update(enroll);
	}


}
