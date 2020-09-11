package com.dictation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.DaeMapper;
import com.dictation.vo.DaeVO;


@Service
public class DaeService {
	
	@Autowired
	private DaeMapper daeMapper;

	/**
	 * 대코드 조회
	 * @param String
	 * @return DaeVO
	 */
	public DaeVO get(String code) {
		return daeMapper.get(code);
	}

	/**
	 * 대코드 리스트 조회
	 * @return List<DaeVO>
	 */
	public List<DaeVO> getList() {
		return daeMapper.list();
	}

	/**
	 * 대코드 추가
	 * @param dae
	 * @throws Exception
	 */
	public void insert(DaeVO dae) throws Exception {
		daeMapper.insert(dae);
	}

	/**
	 * 대코드 업데이트
	 * @param dae
	 * @throws Exception
	 */
	public void update(DaeVO dae) throws Exception {
		daeMapper.update(dae);
	}

	/**
	 * 대코드 삭제
	 * @param dae_cd
	 * @throws Exception
	 */
	public void delete(String code) throws Exception {
		daeMapper.delete(code);
	}

}
