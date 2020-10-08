package com.dictation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.LectureMapper;
import com.dictation.vo.LectureVO;

@Service
public class LectureService {
	
	@Autowired
	private LectureMapper lectureMapper;

	/**
	 * 강의 정보 조회
	 * @param params
	 * @return LectureVO
	 */
	public LectureVO get(Map<String, Object> params) {
		return lectureMapper.get(params);
	}

	/**
	 * 모든 선생님, 수강신청 가능한 상태의 강좌리스트 조회
	 * @param params
	 * @return List<LectureVO>
	 */
	public List<LectureVO> getList(Map<String, Object> params) {
		return lectureMapper.getList(params);
	}

	/**
	 * 내 강의 리스트 전체 조회
	 * @param params
	 * @return List<LectureVO>
	 */
	public List<LectureVO> getMyList(Map<String, Object> params) {
		return lectureMapper.getMyList(params);
	}
	
	/**
	 * 강의 생성
	 * @param lecture
	 * @return void
	 */
	public void insert(LectureVO lecture) throws Exception {
		lectureMapper.insert(lecture);
	}

	/**
	 * 강의 업데이트
	 * @param lecture
	 * @return void
	 */
	public void update(LectureVO lecture) throws Exception {
		lectureMapper.update(lecture);
	}

	/**
	 * 강의 삭제
	 * @param lecture_no
	 * @return void
	 */
	public void delete(String lecture_no) throws Exception {
		lectureMapper.delete(lecture_no);
	}

}
