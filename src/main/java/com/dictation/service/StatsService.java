package com.dictation.service;

import com.dictation.mapper.StudyDAO;
import com.dictation.vo.StudyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatsService {
	
	@Autowired
	private StudyDAO studyDAO;

	public List<Map<String, Object>> get(Map<String, Object> params) {
		return studyDAO.getStatsDetailList(params);
	}

	public List<Map<String, Object>> getList(Map<String, Object> params) {
		return studyDAO.getStatsList(params);
	}



}
