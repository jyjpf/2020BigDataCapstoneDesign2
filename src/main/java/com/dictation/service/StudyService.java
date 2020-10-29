package com.dictation.service;

import java.util.List;
import java.util.Map;


import com.dictation.mapper.StudyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.vo.StudyVO;

@Service
public class StudyService {
	
	@Autowired
	private StudyDAO studyDAO;

	public int getSequence(Map<String, Object> params) throws Exception {
		return studyDAO.getSequence(params);
	}

	public void insert(StudyVO study) throws Exception {
		studyDAO.insert(study);
	}

}
