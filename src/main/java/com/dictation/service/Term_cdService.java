package com.dictation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.Term_cdMapper;
import com.dictation.vo.Term_cdVO;


@Service
public class Term_cdService {
	
	@Autowired
	private Term_cdMapper term_cdMapper;

	/**
	 * 년도/분기 정보 조회
	 * @param Map
	 * @return Term_cdVO
	 */
	public Term_cdVO get(Map<String, Object> params) {
		return term_cdMapper.get(params);
	}

	/**
	 * 년도/분기 정보 리스트
	 * @return List<Term_cdVO>
	 */
	public List<Term_cdVO> getList() {
		return term_cdMapper.getList();
	}
	
	/**
	 * 년도/분기 정보 삽입
	 * @param Term_cdVO
	 * @throws Exception
	 * @return void
	 */
	public void insert(Term_cdVO term_cd) throws Exception {
		term_cdMapper.insert(term_cd);
	}

	/**
	 * 년도/분기 정보 업데이트
	 * @param Term_cdVO
	 * @throws Exception
	 * @return void
	 */
	public void update(Term_cdVO term_cd) throws Exception {
		term_cdMapper.update(term_cd);
	}

	/**
	 * 년도/분기 정보 삭제
	 * @param Map
	 * @throws Exception
	 * @return void
	 */
	public void delete(Map<String, Object> params) throws Exception {
		term_cdMapper.delete(params);
	}

}
