package com.dictation.service;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.BoardMapper;
import com.dictation.vo.BoardVO;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	
	//##insert,delete,update,getById,list##
	//insert
	public void insert(BoardVO board) {
		boardMapper.insert(board);
	}

	//according to id delete
	public void delete(HashMap<String, Object> map) {
		boardMapper.delete(map);
	}
	
	public void lecture_delete(int lecture_no) {
		boardMapper.lecture_delete(lecture_no);
	}
	
	//update after delete
	public void after_delete(HashMap<String, Object> map) { 
		boardMapper.after_delete(map); 
	}
	
	public void update(BoardVO board) {
		boardMapper.update(board);
	}
	
	public void update_nofile(BoardVO board) {
		boardMapper.update_nofile(board);
	}

	//according to id query
	public BoardVO getById(BoardVO board) {
		return boardMapper.getById(board);
	}

	//All queries
	public List<BoardVO> list(BoardVO board){
		return boardMapper.list(board);
	}
	
	//search file_nm for file download
	public String getFileNm(String save_file_nm) {
		return boardMapper.getFileNm(save_file_nm);
	}

}
