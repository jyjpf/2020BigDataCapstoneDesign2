package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.BoardVO;

@Repository
@Mapper
public interface BoardDAO {

	public List<BoardVO> get(Map<String, Object> params);

	public List<BoardVO> getList(Map<String, Object> params);

	public void insert(BoardVO board) throws Exception;

	public void insertComment(BoardVO board) throws Exception;

	public void update(BoardVO board) throws Exception;

	public void updateComment(BoardVO board) throws Exception;

	public void updateCount(Map<String, Object> params) throws Exception;

	public void delete(Map<String, Object> params) throws Exception;

	public void deleteComment(Map<String, Object> params) throws Exception;

}