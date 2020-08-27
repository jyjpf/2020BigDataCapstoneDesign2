package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.AdminVO;

@Repository
@Mapper
public interface AdminMapper {	
	
	//insert
	public void insert(AdminVO user);

	//according to id delete
	public void delete(String user_id);

	//according to user Of id modify
	public void update(AdminVO user);

	//according to id query
	public AdminVO getById(String user_id);

	//All queries
	public List<AdminVO> list();

	
}
