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
	
	//##insert,delete,update,getById,list##
	//insert
	public void insert(EnrollVO enroll) {
		enrollMapper.insert(enroll);
	}

	//according to id delete
	public void delete(EnrollVO enroll) {
		enrollMapper.delete(enroll);
	}

	public void lecture_delete(int lecture_no) {
		enrollMapper.lecture_delete(lecture_no);
	}
		
	//according to user Of id modify
	public void update(EnrollVO enroll) {		
		enrollMapper.update(enroll);
	}
	
	public void update_request(int lecture_no, String user_id) {
		EnrollVO enroll = new EnrollVO();
		enroll.setLecture_no(lecture_no);
		enroll.setUser_id(user_id);
		
		enrollMapper.update_request(enroll);
	}

	//according to id query
	public EnrollVO getById(String user_id) {
		return enrollMapper.getById(user_id);
	}
	
	public int what_pass_course(EnrollVO enroll) {
		return enrollMapper.what_pass_course(enroll);
	}

	//All queries
	public List<EnrollVO> list(){
		return enrollMapper.list();
	}
	
	public List<UserVO> list_request(int lecture_no){
		return enrollMapper.list_request(lecture_no);
	}
	

}
