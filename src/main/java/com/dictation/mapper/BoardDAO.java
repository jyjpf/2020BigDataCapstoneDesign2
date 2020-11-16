package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.BoardVO;

@Repository
@Mapper
public interface BoardDAO {
	
	/**
	 * 
	 * @param board
	 * @return
	 */
	public List<BoardVO> get(Map<String, Object> params);

	/**
	 * 
	 * @param board
	 * @return
	 */
	public List<BoardVO> getList(Map<String, Object> params);
	
	/**
	 * 
	 * @param board
	 * @throws Exception
	 */
	public void insert(BoardVO board) throws Exception;

	/**
	 *
	 * @param board
	 * @throws Exception
	 */
	public void insertComment(BoardVO board) throws Exception;

	/**
	 *
	 * @param board
	 */
	public void update(BoardVO board) throws Exception;

	/**
	 *
	 * @param board
	 */
	public void updateComment(BoardVO board) throws Exception;

	/**
	 *
	 * @param board
	 */
	public void updateCount(Map<String, Object> params) throws Exception;

	/**
	 *
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List<BoardVO> delete(Map<String, Object> map) throws Exception;

	/**
	 *
	 * @param map
	 * @throws Exception
	 */
	public void deleteComment(Map<String, Object> map) throws Exception;


}
