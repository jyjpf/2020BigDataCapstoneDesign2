package com.dictation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.SoMapper;
import com.dictation.vo.SoVO;



@Service
public class SoService {
	
	@Autowired
	private SoMapper soMapper;

	/**
	 * 소코드 조회
	 * @param Map<String, Object>
	 * @return SoVO
	 */
	public SoVO get(Map<String, Object> params) {
		return soMapper.get(params);
	}

	/**
	 * 소코드 리스트 조회
	 * @param Map<String, Object>
	 * @return List<SoVO>
	 */
	public List<SoVO> getList(Map<String, Object> params) {
		return soMapper.list(params);
	}

	/**
	 * 소코드 추가
	 * @param SoVO
	 * @throws Exception
	 */
	public void insert(SoVO so) throws Exception {
		soMapper.insert(so);
	}

	/**
	 * 소코드 업데이트
	 * @param SoVO
	 * @throws Exception
	 */
	public void update(SoVO so) throws Exception {
		soMapper.update(so);
	}

	/**
	 * 소코드 삭제
	 * @param String
	 * @throws Exception
	 */
	public void delete(String code) throws Exception {
		soMapper.delete(code);
	}

}
