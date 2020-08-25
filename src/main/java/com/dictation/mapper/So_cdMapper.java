package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.So_cdVO;



@Repository
@Mapper
public interface So_cdMapper {	
	
	//insert
	public void insert(So_cdVO so_cd);

	//according to id delete
	public void delete(String so_cd);

	//according to user Of id modify
	public void update(So_cdVO so_cd);

	//according to id query
	public So_cdVO getById(String so_cd);	

	//All queries
	public List<So_cdVO> list();

	
}
