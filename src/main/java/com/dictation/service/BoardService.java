package com.dictation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.BoardMapper;
import com.dictation.vo.BoardVO;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	/**
	 * 게시글 정보를 가져옵니다
	 * @param params - 강의번호, 게시글번호
	 * @return BoardVO
	 */
	public BoardVO get(Map<String, Object> params) {
		return boardMapper.get(params);
	}

	/**
	 * 게시판 게시글 목록을 가져옵니다
	 * @param params - 강의번호
	 * @return List<BoardVO>
	 */
	public List<BoardVO> getList(Map<String, Object> params) {
		return boardMapper.getList(params);
	}
	
	/**
	 * 게시판에 게시글을 생성합니다
	 * @param board - boardVO
	 * @throws Exception
	 */
	public void insert(BoardVO board) throws Exception {
		boardMapper.insert(board);
	}

	/**
	 *
	 * @param board
	 */
	public void update(BoardVO board) {
		boardMapper.update(board);
	}

	/**
	 * 게시판의 게시글을 삭제합니다.
	 * @param params - 강의번호, 게시판번호
	 * @throws Exception
	 */
	public void delete(Map<String, Object> params) throws Exception {
		boardMapper.delete(params);
	}

	/**
	 * DB에 저장되 있는 파일명으로 다운받을 파일의 해시값을 불러옴
	 * @param params
	 * @return hashFilename
	 */
	public String getHashFilename(Map params) {
		return boardMapper.getHashFilename(params);
	}

}
