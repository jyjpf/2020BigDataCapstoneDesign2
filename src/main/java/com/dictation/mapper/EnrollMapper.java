package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;

@Repository
@Mapper
public interface EnrollMapper {	
	
	//insert
	public void insert(EnrollVO enroll);

	//according to id delete
	public void delete(EnrollVO enroll);	
	
	public void lecture_delete(int lecture_no);

	//according to user Of id modify
	public void update(EnrollVO enroll);
	
	public void update_request(EnrollVO enroll);

	//according to id query
	public EnrollVO getById(String user_id);	
	
	public int what_pass_course(EnrollVO enroll);

	//All queries
	public List<EnrollVO> list();
	
	public List<UserVO> list_request(int lecture_no);	
}
