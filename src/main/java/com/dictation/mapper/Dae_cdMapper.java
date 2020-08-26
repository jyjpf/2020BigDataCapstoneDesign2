package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.Dae_cdVO;

@Repository
@Mapper
public interface Dae_cdMapper {	
	
	//insert
	public void insert(Dae_cdVO dae_cd);

	//according to id delete
	public void delete(String dae_cd);

	//according to user Of id modify
	public void update(Dae_cdVO dae_cd);

	//according to id query
	public Dae_cdVO getById(String dae_cd);

	//All queries
	public List<Dae_cdVO> list();

	
}
