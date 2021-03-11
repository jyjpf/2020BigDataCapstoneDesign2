package com.dictation.service;

import java.util.List;
import java.util.Map;

import com.dictation.mapper.LectureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.vo.LectureVO;

@Service
public class LectureService {
	
	@Autowired
	private LectureDAO lectureDAO;

	/**
	 * 강의 정보 조회
	 * @param params
	 * @return LectureVO
	 */
	public LectureVO get(Map<String, Object> params) {
		return lectureDAO.get(params);
	}

	/**
	 * 모든 선생님, 수강신청 가능한 상태의 강좌리스트 조회
	 * @param params
	 * @return List<LectureVO>
	 */
	public List<LectureVO> getStudentMenuList(Map<String, Object> params) {
		return lectureDAO.getStudentMenuList(params);
	}

	/**
	 * 모든 선생님, 수강신청 가능한 상태의 강좌리스트 조회
	 * @param params
	 * @return List<LectureVO>
	 */
	public List<LectureVO> getTeacherMenuList(Map<String, Object> params) {
		return lectureDAO.getTeacherMenuList(params);
	}

	/**
	 * 모든 선생님, 수강신청 가능한 상태의 강좌리스트 조회
	 * @param params
	 * @return List<LectureVO>
	 */
	public List<LectureVO> getList(Map<String, Object> params) {
		return lectureDAO.getList(params);
	}
	
	/**
	 * 강의 생성
	 * @param lecture
	 * @return void
	 */
	public void insert(LectureVO lecture) throws Exception {
		lectureDAO.insert(lecture);
	}

	/**
	 * 강의 업데이트
	 * @param lecture
	 * @return void
	 */
	public void update(LectureVO lecture) throws Exception {
		lectureDAO.update(lecture);
	}

	/**
	 * 강의 업데이트
	 * @param lecture
	 * @return void
	 */
	public void updateLevel(long lecture_no) throws Exception {
		lectureDAO.updateLevel(lecture_no);
	}

	/**
	 * 강의 삭제
	 * @param lecture_no
	 * @return void
	 */
	public void delete(String lecture_no) throws Exception {
		lectureDAO.delete(lecture_no);
	}

}
