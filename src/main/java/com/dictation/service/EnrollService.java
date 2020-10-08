package com.dictation.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.EnrollMapper;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;

@Service
public class EnrollService {
	
	@Autowired
	private EnrollMapper enrollMapper;

	public List<EnrollVO> getMyList(UserVO user) {
		return enrollMapper.getMyList(user);
	}

	public void insert(EnrollVO enroll) {
		enrollMapper.insert(enroll);
	}

	public void update(EnrollVO enroll) {		
		enrollMapper.update(enroll);
	}


}
