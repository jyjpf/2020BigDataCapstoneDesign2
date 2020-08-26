package com.dictation.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.BoardVO;

@Repository
@Mapper
public interface BoardMapper {
	
	//insert
	public void insert(BoardVO board);

	//according to id delete
	public void delete(HashMap<String, Object> map);
	
	public void lecture_delete(int lecture_no);

	//update after delete
	public void after_delete(HashMap<String, Object> map);
	
	public void update(BoardVO board);
	
	public void update_nofile(BoardVO board);

	//according to id query
	public BoardVO getById(BoardVO board);

	//All queries
	public List<BoardVO> list(BoardVO board);
	
	//search file_nm for file download
	public String getFileNm(String save_file_nm);
}
