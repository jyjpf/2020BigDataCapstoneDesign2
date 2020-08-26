package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.Term_cdVO;


@Repository
@Mapper
public interface Term_cdMapper {
	
	//insert
	public void insert(Term_cdVO term_cd);

	//according to id delete
	public void delete(String year, String term);	

	//according to user Of id modify
	public void update(Term_cdVO term_cd);

	//according to id query
	public Term_cdVO getById(String year, String term);

	//All queries
	public List<Term_cdVO> list();

	
}
