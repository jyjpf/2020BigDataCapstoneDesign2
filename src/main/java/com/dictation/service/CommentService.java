package com.dictation.service;

import com.dictation.mapper.BoardDAO;
import com.dictation.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentService {
	
	@Autowired
	private BoardDAO boardDAO;

	/**
	 * 게시판에 게시글을 생성합니다
	 * @param board - boardVO
	 * @throws Exception
	 */
	public void insert(BoardVO board) throws Exception {
		boardDAO.insertComment(board);
	}

	/**
	 *
	 * @param board
	 */
	public void update(BoardVO board) throws Exception {
		boardDAO.updateComment(board);
	}

	/**
	 * 게시판의 게시글을 삭제합니다.
	 * @param params - 강의번호, 게시판번호
	 * @throws Exception
	 */
	public void delete(Map<String, Object> params) throws Exception {
		boardDAO.deleteComment(params);
	}

}
