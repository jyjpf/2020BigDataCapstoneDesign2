package com.dictation.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.AdminMapper;
import com.dictation.vo.AdminVO;

@Service
public class AdminService {
	
	@Autowired
	private AdminMapper adminMapper;
	
	
	//##insert,delete,update,getById,list##
	//insert
	public void insert(AdminVO lecture) {
		adminMapper.insert(lecture);
	}

	//according to id delete
//	public void delete(int lecture_no) {
//		adminMapper.delete(lecture_no);
//	}
//
//	//according to user Of id modify
//	public void update(AdminVO lecture) {
//		adminMapper.update(lecture);
//	}
//
//	//according to id query
//	public AdminVO getById(int lecture_no) {
//		return adminMapper.getById(lecture_no);
//	}

	//All queries
	public List<AdminVO> list(){
		return adminMapper.list();
	}
	

}
