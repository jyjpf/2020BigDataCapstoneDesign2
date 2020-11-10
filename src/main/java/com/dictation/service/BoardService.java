package com.dictation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.BoardDAO;
import com.dictation.vo.BoardVO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	/**
	 * 게시글 정보를 가져옵니다
	 * @param params - 강의번호, 게시글번호
	 * @return BoardVO
	 */
	public List<BoardVO> get(Map<String, Object> params) throws Exception {
		boardDAO.updateCount(params);
		return boardDAO.get(params);
	}

	/**
	 * 게시판 게시글 목록을 가져옵니다
	 * @param params - 강의번호
	 * @return List<BoardVO>
	 */
	public List<BoardVO> getList(Map<String, Object> params) {
		return boardDAO.getList(params);
	}
	
	/**
	 * 게시판에 게시글을 생성합니다
	 * @param board - boardVO
	 * @throws Exception
	 */
	public void insert(BoardVO board) throws Exception {
		boardDAO.insert(board);
	}

	/**
	 *
	 * @param board
	 */
	public void update(BoardVO board) throws Exception {
		boardDAO.update(board);
	}

	/**
	 * 게시판의 게시글을 삭제합니다.
	 * @param params - 강의번호, 게시판번호
	 * @throws Exception
	 */
	public void delete(Map<String, Object> params) throws Exception {
		boardDAO.delete(params);
	}

}
