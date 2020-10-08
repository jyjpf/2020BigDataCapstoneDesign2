package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.BoardVO;

@Repository
@Mapper
public interface BoardMapper {
	
	/**
	 * 
	 * @param board
	 * @return
	 */
	public BoardVO get(Map<String, Object> params);

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
	 */
	public void update(BoardVO board);

	/**
	 *
	 * @param map
	 * @throws Exception
	 */
	public void delete(Map<String, Object> map) throws Exception;

	/**
	 *
	 * @param originalFilename
	 * @return
	 */
	public String getHashFilename(Map params);


}
