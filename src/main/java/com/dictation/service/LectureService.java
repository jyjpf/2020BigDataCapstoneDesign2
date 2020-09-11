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
	 * @param Map
	 * @return LectureVO
	 */
	public LectureVO get(Map<String, Object> params) {
		return lectureMapper.get(params);
	}

	/**
	 * 강의 리스트 조회
	 * @param String
	 * @return List<LectureVO>
	 */
	public List<LectureVO> list(Map<String, Object> params) {
		return lectureMapper.list(params);
	}
	
	/**
	 * 강의 생성
	 * @param LectureVO
	 * @return void
	 */
	public void insert(LectureVO lecture) throws Exception {
		lectureMapper.insert(lecture);
	}

	/**
	 * 강의 업데이트
	 * @param LectureVO
	 * @return void
	 */
	public void update(LectureVO lecture) throws Exception {
		lectureMapper.update(lecture);
	}

	/**
	 * 강의 삭제
	 * @param String
	 * @return void
	 */
	public void delete(String lecture_no) throws Exception {
		lectureMapper.delete(lecture_no);
	}



	

	public List<LectureVO> teacher_mylec(String user_id) {
		return lectureMapper.teacher_mylec(user_id);
	}
	
	public List<LectureVO> student_lec_list(String user_id) {
		return lectureMapper.student_lec_list(user_id);
	}
	
	public List<LectureVO> student_mylec(String user_id){
		return lectureMapper.student_mylec(user_id);
	}

}
