package com.dictation.service;

import java.util.List;
import java.util.Map;


import com.dictation.mapper.StudyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.vo.StudyVO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudyService {
	
	@Autowired
	private StudyDAO studyDAO;

	public int getNextSequence(Map<String, Object> params) {
		return studyDAO.getNextSequence(params);
	}

	public Map<String, Object> insert(StudyVO study) throws Exception {
		studyDAO.insert(study);
		return studyDAO.getAnswer(study);
	}

}
